package com.exc.job.config;

import com.exc.job.utils.SchedulerUtil;
import com.exc.pojo.task.TaskVo;
import com.exc.repository.mapper.TaskMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@MapperScan(basePackages = {"com.exc.repository.mapper"})
public class DataListener implements ApplicationContextAware{
    private final Logger logger = LoggerFactory.getLogger(DataListener.class);

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        List<TaskVo> taskList = taskMapper.getTaskListByTypeAndStatus();

        try {
            SchedulerUtil.executeJob(taskList);
        } catch (SchedulerException e) {
            throw new RuntimeException("初始化定时任务异常");
        }
    }
}
