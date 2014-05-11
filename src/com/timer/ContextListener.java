package com.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Administrator
 */
public class ContextListener implements ServletContextListener {

    private java.util.Timer timer = null;

    
    public void contextInitialized(ServletContextEvent event) {
        timer = new java.util.Timer(true);
        event.getServletContext().log("定时器已启动");
        MyTask task = new MyTask(event.getServletContext());
        timer.schedule(task, 0, 1000 * 60 );
        event.getServletContext().log("已经添加任务调度表");
    }

    
    public void contextDestroyed(ServletContextEvent event) {
        timer.cancel();
        event.getServletContext().log("定时器销毁");
    }
}
