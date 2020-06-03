package com.sfs.search;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ES使用DSL搜索的测试类
 *
 * @author yjw
 * @date 2020/3/24 16:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSearch {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    RestClient restClient;

    /**
     * 测试全部搜索
     */
    @Test
    public void testSearchAll() throws IOException, ParseException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("sfs_course");
        //设置类型
        searchRequest.types("doc");
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置全部搜索
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //source源字段过滤(第一个字符数组可设置包含字段，第二个字符数组可设置排除字段)
        searchSourceBuilder.fetchSource(new String[]{}, new String[]{"name"});
        //设置搜索源
        searchRequest.source(searchSourceBuilder);
        //执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //搜索匹配结果(搜索命中数)
        SearchHits hits = searchResponse.getHits();
        //搜索总记录数
        long totalHits = hits.totalHits;
        //获取匹配度较高的前n个文档
        SearchHit[] searchHits = hits.getHits();
        //遍历匹配度较高的前n个文档
        for (SearchHit searchHit : searchHits) {
            //文档id
            String id = searchHit.getId();
            //获取源文档内容
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            //获取文档详细内容
            String name = (String) sourceAsMap.get("name");
            String studymodel = (String) sourceAsMap.get("studymodel");
            String description = (String) sourceAsMap.get("description");

            Double price = (Double) sourceAsMap.get("price");
            Object timestamp = sourceAsMap.get("timestamp");
            System.out.println(name + "  " + studymodel + "  " + price + "  " + description);
            System.out.println(timestamp);
        }
        System.out.println("搜索到的总记录数" + totalHits);
    }

    /**
     * 分页查询
     */
    @Test
    public void testPageSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("sfs_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //分页查询，设置下标起始，从0开始
        searchSourceBuilder.from(0);
        //设置每页显示个数
        searchSourceBuilder.size(1);
        searchSourceBuilder.fetchSource(new String[]{"name", "pic", "description"}, new String[]{});
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取查询到的文档列表
        SearchHits searchHits = searchResponse.getHits();
        //获取查询到的文档个数
        long totalHits = searchHits.totalHits;
        //获得命中率高的前n个文档
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Object name = sourceAsMap.get("name");
            Object pic = sourceAsMap.get("pic");
            System.out.println(name + "  " + pic);
        }
        System.out.println(totalHits);
    }

    /**
     * 测试根据关键字精确搜索，不再对关键字进行分词
     * 一次只能对一个字段进行查询
     */
    @Test
    public void testTermQuery() throws IOException {
        //创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("sfs_course");
        //设置请求文档类型
        searchRequest.types("doc");
        //创建搜索源对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置精确搜索，并设置搜索条件name
        searchSourceBuilder.query(QueryBuilders.termQuery("name", "java"));
        //设置搜索条件
        searchSourceBuilder.fetchSource(new String[]{"name", "pic"}, new String[]{});
        //将搜索源对象设置到搜索请求对象
        searchRequest.source(searchSourceBuilder);
        //使用ES高等级客户端执行搜索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        //获取搜索到的文档列表
        SearchHits hits = searchResponse.getHits();
        //获取搜索到的文档总数
        long totalHits = hits.totalHits;
        //获取命中率高的前n个文档
        SearchHit[] hits1 = hits.getHits();
        //遍历命中率高的前n个文档
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            Object name = sourceAsMap.get("name");
            Object pic = sourceAsMap.get("pic");
            System.out.println(name + " " + pic);
        }
        System.out.println(totalHits);
    }

    /**
     * 根据id精确查询
     */
    @Test
    public void testTremQueryById() throws IOException {
        SearchRequest searchRequest = new SearchRequest("sfs_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        searchSourceBuilder.query(QueryBuilders.termsQuery("_id", list));
        searchSourceBuilder.fetchSource(new String[]{}, new String[]{});
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.totalHits;
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            Object name = sourceAsMap.get("name");
            Object pic = sourceAsMap.get("pic");
            System.out.println(name + " " + pic);
        }
        System.out.println(totalHits);
    }

    /**
     * 根据关键字进行查询，关键字进行分词
     * 一次只能对一个字段进行查询
     */
    @Test
    public void testMatchQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("sfs_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //根据关键字查询，对关键字进行分词，并设置Operator为or（只要分词后的关键字，有一个匹配到，便返回文档）
//        searchSourceBuilder.query(QueryBuilders.matchQuery("name","java开发").operator(Operator.OR));
        //设置关键字查询，对关键字进行分词，设置关键字匹配占比为80%（即文档有80%分词后的关键字）
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name",
                "java开发").minimumShouldMatch("80%");
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.totalHits;
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            Object name = sourceAsMap.get("name");
            Object pic = sourceAsMap.get("pic");
            System.out.println(name + " " + pic);
        }
        System.out.println(totalHits);
    }

    /**
     * 测试关键字进行查询，可匹配多个字段
     * 并可以设置指定字段的权重（使权重高的文档排在前面）
     */
    @Test
    public void testMultiQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("sfs_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置多字段根据关键字查询，并设置关键字占比为50%
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("java开发",
                "name", "description").minimumShouldMatch("50%");
        //设置字段的权重，权重高的文档会排在前面
        multiMatchQueryBuilder.field("description", 10);
        searchSourceBuilder.query(multiMatchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.totalHits;
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            Object name = sourceAsMap.get("name");
            Object description = sourceAsMap.get("description");
            System.out.println(name + " " + description);
        }
        System.out.println(totalHits);
    }

    /**
     * 测试布尔搜索
     * 三个参数：
     * must:相当于and
     * should:相当于or
     * must_not:相当于not
     */
    @Test
    public void testBoolQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("sfs_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(new String[]{"name", "pic", "studymodel"}, new String[]{});
        //设置多字段根据关键字查询，并设置关键字占文档比例为50%
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("开发",
                "name", "description").minimumShouldMatch("50%");
        //设置查询字段的权重，权重高的文档会排在查询结果前面
        multiMatchQueryBuilder.field("name", 10);
        //精确查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("studymodel", "201001");
        //布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //使用must参数，设置必须满足多字段匹配规则
        boolQueryBuilder.must(multiMatchQueryBuilder);
        //设置必须满足精确查询规则
        boolQueryBuilder.must(termQueryBuilder);
        //设置查询对象为布尔查询
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    /**
     * 测试过滤器
     * 和布尔查询嵌套使用
     */
    @Test
    public void testFilter() throws IOException {
        SearchRequest searchRequest = new SearchRequest("sfs_course");
        searchRequest.types("doc");
        //创建搜索源对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置查询字段和查询排除字段
        searchSourceBuilder.fetchSource(new String[]{}, new String[]{});
        //设置多字段匹配，对关键字进行分词
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("开发",
                "name", "description").minimumShouldMatch("50%");
        //设置多字段查询，指定字段权重为10
        multiMatchQueryBuilder.field("name", 10);
        //创建布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //设置必须满足多字段查询
        boolQueryBuilder.must(multiMatchQueryBuilder);
        //对布尔查询结果进行过滤
        boolQueryBuilder.filter(QueryBuilders.termQuery("studymodel", "201001"));
        //过滤价格为60-100之间的文档
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(60).lte(100));
        //执行布尔查询
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }

    /**
     * 测试指定结果排序
     */
    @Test
    public void testSort() throws IOException {
        SearchRequest searchRequest = new SearchRequest("sfs_course");
        searchRequest.types("doc");
        //创建搜索源对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置查询字段和查询排除字段
        searchSourceBuilder.fetchSource(new String[]{}, new String[]{});
        //创建布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //过滤价格为0-100之间的文档
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(0).lte(100));
        //执行布尔查询
        searchSourceBuilder.query(boolQueryBuilder);
        //对搜索数据源对象指定字段进行排序(DESC:倒序;ASC:正序)
        searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.DESC));
        //将搜索源对象和搜索请求类绑定
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }


    /**
     * 测试高亮
     * 对查询结果指定字段高亮显示
     */
    @Test
    public void testHighLight() throws IOException {
        SearchRequest searchRequest = new SearchRequest("sfs_course");
        searchRequest.types("doc");
        //创建搜索源对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置查询字段和查询排除字段
        searchSourceBuilder.fetchSource(new String[]{}, new String[]{});
        //设置多字段匹配，对关键字进行分词
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("开发",
                "name", "description").minimumShouldMatch("50%");

        //创建布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(multiMatchQueryBuilder);
        //过滤价格为0-100之间的文档
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(0).lte(100));
        //执行布尔查询
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        //对搜索数据源对象指定字段进行排序(DESC:倒序;ASC:正序)
        searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.DESC));

        //创建高亮对象
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置高亮前缀
        highlightBuilder.preTags("<tag>");
        //设置高亮后缀
        highlightBuilder.postTags("</tag>");
        //设置高亮显示字段
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        //将高亮对象添加到搜索源对象
        searchSourceBuilder.highlighter(highlightBuilder);

        //向ES发起http请求
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            System.out.println(sourceAsMap);
            String name = (String) sourceAsMap.get("name");
            //从查询结果中取出高亮字段内容
            Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
            //如果文档中存在高亮部分
            if (highlightFields != null) {
                //从高亮内容中获取name字段内容
                HighlightField highlightField = highlightFields.get("name");
                if (highlightField != null) {
                    //获取高亮的文本数组
                    Text[] fragments = highlightField.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    //将高亮文本数组转换为字符串
                    for (Text fragment : fragments) {
                        stringBuffer.append(fragment.toString());
                    }
                    //将name设置为高亮后的字符串
                    name = stringBuffer.toString();
                    System.out.println(name);
                }
            }
        }
    }


}
