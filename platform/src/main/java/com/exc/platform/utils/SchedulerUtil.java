package com.exc.platform.utils;

import com.exc.job.MyJob1;
import com.exc.pojo.task.TaskVo;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SchedulerUtil {

    public static void executeJob(TaskVo taskVo) throws SchedulerException {
        Scheduler scheduler = initScheduler();
        JobDetail jobDetail = initJobDetail(taskVo.getTaskClass());
        CronTrigger cronTrigger = initTrigger(taskVo.getTaskClass(), taskVo.getExecuteCron());

        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    private static CronTrigger initTrigger(String taskClass, String executeCron) {
        return newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule(executeCron))
                .forJob("myJob", "group1")
                .build();
    }

    private static JobDetail initJobDetail(String taskClass) {
        return newJob(MyJob1.class)
                .withIdentity("myJob1", "group1")
                .build();
    }

    private static Scheduler initScheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }
}
