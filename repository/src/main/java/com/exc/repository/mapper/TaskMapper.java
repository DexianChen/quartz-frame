package com.exc.repository.mapper;

import com.exc.pojo.request.TaskRequestParamVo;
import com.exc.pojo.task.TaskVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper {
    List<TaskVo> getTaskList();

    TaskVo findById(Integer taskId);

    void insertTask(TaskRequestParamVo paramVo);

    void updateTask(TaskVo taskVo);

    List<TaskVo> getTaskListByTypeAndStatus();
}
