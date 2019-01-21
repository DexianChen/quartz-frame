import com.exc.job.utils.SchedulerUtil;
import com.exc.pojo.task.TaskVo;
import org.quartz.*;

import java.util.ArrayList;

public class SchedulerUtilTest {
    public static void main(String[] args) throws SchedulerException {
        ArrayList<TaskVo> taskVoList = new ArrayList<>();
        TaskVo taskVo = new TaskVo();

        taskVo.setTaskClass("com.exc.job.scheduler.MyJob1");
        taskVo.setTaskName("myJob1");
        taskVo.setExecuteCron("0/5 * * * * ?");

        TaskVo taskVo1 = new TaskVo();
        taskVo1.setTaskClass("com.exc.job.scheduler.MyJob2");
        taskVo1.setTaskName("myJob2");
        taskVo1.setExecuteCron("0/3 * * * * ?");

        taskVoList.add(taskVo);
        taskVoList.add(taskVo1);

        SchedulerUtil.executeJob(taskVoList);
    }
}
