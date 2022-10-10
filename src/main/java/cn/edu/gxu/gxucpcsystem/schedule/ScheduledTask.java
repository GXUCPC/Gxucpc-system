package cn.edu.gxu.gxucpcsystem.schedule;

import java.util.concurrent.ScheduledFuture;

/**
 * @author MaoMao
 * @Description
 * @create 2022-10-09 4:24 PM
 */


public class ScheduledTask {
    public volatile ScheduledFuture<?> future;
    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
