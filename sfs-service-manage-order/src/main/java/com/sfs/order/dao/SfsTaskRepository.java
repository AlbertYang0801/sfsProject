package com.sfs.order.dao;

import com.sfs.framework.domain.task.SfsTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * @author yjw
 * @date 2020/6/24 23:32
 */
public interface SfsTaskRepository extends JpaRepository<SfsTask, String> {

    /**
     * 分页查询指定时间之前的数据
     *
     * @param pageable
     * @param updateTime
     * @return
     */
    Page<SfsTask> findByUpdateTimeBefore(Pageable pageable, Date updateTime);

    /**
     * 更新任务时间
     *
     * @param taskId
     * @param updateTime
     * @return
     */
    @Modifying
    @Query("update SfsTask t set t.updateTime = :updateTime where t.id = :taskId")
    int updateTaskTime(@Param(value = "taskId") String taskId, @Param(value = "updateTime") Date updateTime);

    /**
     * 使用乐观锁方式校验任务id和版本号是否匹配，匹配成功则版本号加1
     * @param id
     * @param version
     * @return
     */
    @Modifying
    @Query("update SfsTask t set t.version=:version+1 where t.id=:id and t.version=:version")
    int updateTaskVersion(@Param(value = "id") String id, @Param(value = "version") int version);


}
