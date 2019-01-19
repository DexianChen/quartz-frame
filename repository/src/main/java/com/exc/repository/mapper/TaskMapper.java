package com.exc.repository.mapper;

import com.exc.pojo.task.TaskVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper {
    List<TaskVo> getTaskList();

    TaskVo findById(Integer taskId);
}
