package com.sfs.search.service.impl;

import com.sfs.framework.domain.course.CoursePub;
import com.sfs.framework.domain.course.TeachplanMediaPub;
import com.sfs.framework.domain.search.CourseSearchParam;
import com.sfs.framework.model.response.CommonCode;
import com.sfs.framework.model.response.QueryResponseResult;
import com.sfs.framework.model.response.QueryResult;
import com.sfs.search.service.EsCourseService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yjw
 * @date 2020/3/27 22:06
 */
@Service
public class EsCourseServiceImpl implements EsCourseService {

    @Value("${sfs.course.index}")
    private String courseIndex;

    @Value("${sfs.course.type}")
    private String courseType;

    @Value("${sfs.course.includes_field}")
    private String courseIncludesField;

    @Value("${sfs.media.index}")
    private String mediaIndex;

    @Value("${sfs.media.type}")
    private String mediaType;

    @Value("${sfs.media.includes_field}")
    private String mediaIncludesField;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public QueryResponseResult<CoursePub> list(int page, int size, CourseSearchParam courseSearchParam) {
        //设置索引
        SearchRequest searchRequest = new SearchRequest(courseIndex);
        //设置类型
        searchRequest.types(courseType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //创建布尔查询对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        String[] includeFilds = courseIncludesField.split(",");
        //设置查询包含字段和排除字段
        searchSourceBuilder.fetchSource(includeFilds, new String[]{});
        //若关键字不为空，根据关键字进行搜索
        if (StringUtils.isNotEmpty(courseSearchParam.getKeyword())) {
            //创建多字段根据关键字匹配，使用关键字匹配课程名称、课程计划、课程描述等字段内容
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(courseSearchParam.getKeyword(),
                    "name", "teachplan", "description");
            multiMatchQueryBuilder.minimumShouldMatch("70%");
            //提高name字段的权重
            multiMatchQueryBuilder.field("name", 10);
            //使用布尔查询，设置必须满足多字段查询
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }
        //根据级别进行搜索,通过设置过滤器实现
        if (StringUtils.isNotEmpty(courseSearchParam.getGrade())) {
            //设置根据grade级别精确查询进行结果过滤
            boolQueryBuilder.filter(QueryBuilders.termQuery("grade", courseSearchParam.getGrade()));
        }
        //根据大分类进行搜索
        if (StringUtils.isNotEmpty(courseSearchParam.getMt())) {
            //设置根据大分类mt精确查询进行结果过滤
            boolQueryBuilder.filter(QueryBuilders.termQuery("mt", courseSearchParam.getMt()));
        }
        //根据小分类进行搜索
        if (StringUtils.isNotEmpty(courseSearchParam.getSt())) {
            //设置根据小分类st精确查询结果进行过滤
            boolQueryBuilder.filter(QueryBuilders.termQuery("st", courseSearchParam.getSt()));
        }
        //设置分页默认参数
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 20;
        }
        int start = (page - 1) * size;
        //设置分页查询起始记录数
        searchSourceBuilder.from(start);
        //设置分页查询查询记录数
        searchSourceBuilder.size(size);
        //将布尔查询设置到搜索源中
        searchSourceBuilder.query(boolQueryBuilder);
        //设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置高亮前缀和后置
//        highlightBuilder.preTags("<font class='eslight'>");
//        highlightBuilder.postTags("</font>");
        highlightBuilder.preTags("");
        highlightBuilder.postTags("");
        //设置高亮字段name
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        //添加高亮到搜索数据源
        searchSourceBuilder.highlighter(highlightBuilder);
        //请求搜索
        searchRequest.source(searchSourceBuilder);
        QueryResult queryResult = new QueryResult();
        List list = new ArrayList();
        SearchResponse searchResponse = null;
        try {
            //使用高级客户端发起搜索请求
            searchResponse = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
            //提示查询失败
            return new QueryResponseResult<>(CommonCode.FAIL, new QueryResult());
        }
        //获取匹配到的文档列表
        SearchHits hits = searchResponse.getHits();
        //获取文档总数
        long totalHits = hits.totalHits;
        //获取匹配度高的文档列表
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit hit : hits1) {
            //取出文档
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            CoursePub coursePub = new CoursePub();
            //取出课程id
            String courseId = (String) sourceAsMap.get("id");
            coursePub.setId(courseId);
            //设置课程名称
            String name = (String) sourceAsMap.get("name");
            //从文档中取出高亮字段内容
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields != null) {
                //从高亮字段中取出name
                HighlightField field = highlightFields.get("name");
                if (field != null) {
                    //获取高亮内容文本列表
                    Text[] fragments = field.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    //将高亮文本列表转换为字符串
                    for (Text fragment : fragments) {
                        stringBuffer.append(fragment);
                    }
                    name = stringBuffer.toString();
                }
            }
            coursePub.setName(name);
            //设置课程图片
            String pic = (String) sourceAsMap.get("pic");
            coursePub.setPic(pic);
            //设置课程价格
            Float price = null;
            if (sourceAsMap.get("price") != null) {
                price = Float.parseFloat(String.valueOf(sourceAsMap.get("price")));
            }
            //设置课程原始价格
            coursePub.setPrice(price);
            Float oldPrice = null;
            if (sourceAsMap.get("price_old") != null) {
                oldPrice = Float.parseFloat(String.valueOf(sourceAsMap.get("price_old")));
            }
            coursePub.setPrice_old(oldPrice);
            //向查询结果列表添加该文档信息
            list.add(coursePub);
        }
        //设置查询结果集
        queryResult.setList(list);
        queryResult.setTotal(totalHits);
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    @Override
    public Map<String, CoursePub> getAllById(String courseId) {
        //创建搜索请求
        SearchRequest searchRequest = new SearchRequest(courseIndex);
        searchRequest.types(courseType);
        //创建搜索源对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //创建根据id的精确查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("id", courseId);
        //设置精确查询到搜索源对象
        searchSourceBuilder.query(termQueryBuilder);
        //设置搜索源对象
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            //使用高等级客户端执行搜索
            searchResponse = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //从搜索响应中获取搜索结果集
        SearchHits searchHits = searchResponse.getHits();
        //从搜索结果集中获取匹配度高的数据
        SearchHit[] hits = searchHits.getHits();
        Map<String, CoursePub> map = new HashMap<>();
        for (SearchHit hit : hits) {
            //获取记录
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            CoursePub coursePub = new CoursePub();
            String id = (String) sourceAsMap.get("id");
            String name = (String) sourceAsMap.get("name");
            String grade = (String) sourceAsMap.get("grade");
            String charge = (String) sourceAsMap.get("charge");
            String pic = (String) sourceAsMap.get("pic");
            String description = (String) sourceAsMap.get("description");
            String teachplan = (String) sourceAsMap.get("teachplan");
            coursePub.setId(id);
            coursePub.setName(name);
            coursePub.setGrade(grade);
            coursePub.setPic(pic);
            coursePub.setCharge(charge);
            coursePub.setDescription(description);
            coursePub.setTeachplan(teachplan);
            map.put(courseId, coursePub);
        }
        return map;
    }

    @Override
    public TeachplanMediaPub getMedia(String teachplanId) {
        //设置课程计划媒资信息索引
        SearchRequest searchRequest = new SearchRequest(mediaIndex);
        searchRequest.types(mediaType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        String[] mediaFields = mediaIncludesField.split(",");
        //设置搜索包含字段和过滤字段
        searchSourceBuilder.fetchSource(mediaFields, new String[]{});
        searchSourceBuilder.query(QueryBuilders.termsQuery("teachplan_id", teachplanId));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            //使用高等级ES客户端执行搜索
            searchResponse = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] hits = searchHits.getHits();
        List<TeachplanMediaPub> teachplanMediaPubList = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            TeachplanMediaPub teachplanMediaPub = new TeachplanMediaPub();
            //取出课程计划媒资信息
            String courseid = (String) sourceAsMap.get("courseid");
            String media_id = (String) sourceAsMap.get("media_id");
            String media_url = (String) sourceAsMap.get("media_url");
            String teachplan_id = (String) sourceAsMap.get("teachplan_id");
            String media_fileoriginalname = (String) sourceAsMap.get("media_fileoriginalname");
            teachplanMediaPub.setCourseId(courseid);
            teachplanMediaPub.setMediaUrl(media_url);
            teachplanMediaPub.setMediaFileOriginalName(media_fileoriginalname);
            teachplanMediaPub.setMediaId(media_id);
            teachplanMediaPub.setTeachplanId(teachplan_id);
            //将数据加入列表
            teachplanMediaPubList.add(teachplanMediaPub);
        }
        //返回查询到结果的第一条数据
        return teachplanMediaPubList.get(0);
    }


}


/**
 * 课程搜索的顺序：
 * 1.按照关键字进行搜素，关键字匹配课程名称、课程计划、课程描述。
 * 2.按照课程等级查询
 * 3.按照课程分类查询
 * 4.设置分页
 * 5.设置高亮
 * 6.取出高亮
 */