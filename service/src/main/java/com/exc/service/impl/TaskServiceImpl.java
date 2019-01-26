package com.exc.service.impl;

import com.exc.job.utils.SchedulerUtil;
import com.exc.pojo.request.TaskRequestParamVo;
import com.exc.pojo.task.TaskVo;
import com.exc.repository.mapper.TaskMapper;
import com.exc.service.TaskService;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@MapperScan(basePackages = {"com.exc.repository.mapper"})
public class TaskServiceImpl implements TaskService{
    private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskVo> getTaskList() {
        return taskMapper.getTaskList();
    }

    @Override
    public void startTask(Integer taskId) throws SchedulerException {
        TaskVo taskVo = taskMapper.findById(taskId);

        //运行定时任务
        logger.info("开始运行定时任务: {}", taskVo.getTaskName());
        SchedulerUtil.executeSingleJob(taskVo);
        logger.info("运行定时任务成功: {}", taskVo.getTaskName());

        //更新定时任务加载时间
        taskVo.setRunStatus(0);
        taskVo.setLoadTime(new Date());
        taskMapper.updateTask(taskVo);
        logger.info("更新定时任务启动状态和加载时间");
    }

    @Override
    public void updateTask(TaskRequestParamVo paramVo) {

    }

    @Override
    public void deleteTask(Integer[] taskIds) {

    }

    @Override
    public void insertTask(TaskRequestParamVo paramVo) {
        try {
            taskMapper.insertTask(paramVo);
            logger.info("定时任务{}插入成功", paramVo.getTaskName());
        } catch (Exception e) {
            throw new RuntimeException("定时任务插入失败");
        }
    }

    @Override
    public List<TaskVo> getTaskListByTypeAndStatus() {
        return taskMapper.getTaskListByTypeAndStatus();
    }

}
