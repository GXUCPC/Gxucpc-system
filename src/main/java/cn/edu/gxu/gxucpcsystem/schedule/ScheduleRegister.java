package cn.edu.gxu.gxucpcsystem.schedule;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-09 4:02 PM
 */
@Data
@Component
public class ScheduleRegister implements DisposableBean {
    @Autowired
    TaskScheduler taskScheduler;
    private final Map<Runnable, ScheduledTask> scheduledTaskMap = new ConcurrentHashMap<>();

    /***
     *
     * @param task  线程
     * @param cron  周期
     */
    public void addCronTask(Runnable task, String cron) {
        addCronTask(new CronTask(task,cron));
    }

    /***
     *
     * @param task 移除当前任务
     */
    public void removeCronTask(Runnable task) {
        ScheduledTask scheduledTask = this.scheduledTaskMap.remove(task);
        if (scheduledTask != null)
            scheduledTask.cancel();
    }

    /***
     *
     * @param cronTask 定时任务
     * @return
     */
    public ScheduledTask scheduleCronTask(CronTask cronTask) {
        ScheduledTask scheduledTask;
        scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }
    public void addCronTask(CronTask cronTask) {
        if (cronTask != null) {
            Runnable runnable = cronTask.getRunnable();
            if (this.scheduledTaskMap.containsKey(runnable)) {
                removeCronTask(runnable);
            }
            //添加
            this.scheduledTaskMap.put(runnable, scheduleCronTask(cronTask));
        }
    }
    @Override
    public void destroy() throws Exception {
        for (ScheduledTask task: this.scheduledTaskMap.values()){
            task.cancel();
        }
        this.scheduledTaskMap.clear();
    }
}
