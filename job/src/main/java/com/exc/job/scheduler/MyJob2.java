package com.exc.job.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MyJob2 implements Job{
    private final Logger logger = LoggerFactory.getLogger(MyJob2.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("定时任务MyJob2开始时间：{}", new Date());
    }
}
