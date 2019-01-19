package com.exc.service;

import com.exc.pojo.request.TaskRequestParamVo;
import com.exc.pojo.task.TaskVo;
import org.quartz.SchedulerException;

import java.util.List;

public interface TaskService {
    List<TaskVo> getTaskList();

    String startTask(Integer taskId) throws SchedulerException;

    void updateTask(TaskRequestParamVo paramVo);

    void deleteTask(Integer[] taskIds);

    void insertTask(TaskRequestParamVo paramVo);
}
