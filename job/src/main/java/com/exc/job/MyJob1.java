package com.exc.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MyJob1 implements Job{
    private final Logger logger = LoggerFactory.getLogger(MyJob1.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("定时任务开始时间：{}", new Date());
    }
}
