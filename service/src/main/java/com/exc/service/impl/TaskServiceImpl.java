package com.exc.service.impl;

import com.exc.platform.utils.SchedulerUtil;
import com.exc.pojo.request.TaskRequestParamVo;
import com.exc.pojo.task.TaskVo;
import com.exc.repository.mapper.TaskMapper;
import com.exc.service.TaskService;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@MapperScan(basePackages = {"com.exc.repository.mapper"})
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskVo> getTaskList() {
        return taskMapper.getTaskList();
    }

    @Override
    public String startTask(Integer taskId) throws SchedulerException {
        TaskVo taskVo = taskMapper.findById(taskId);
        return "SUCCESS";
    }

    @Override
    public void updateTask(TaskRequestParamVo paramVo) {

    }

    @Override
    public void deleteTask(Integer[] taskIds) {

    }

    @Override
    public void insertTask(TaskRequestParamVo paramVo) {

    }

}
