package cn.stylefeng.guns.config;

import cn.stylefeng.guns.threads.GuiHuanAppliesThread;
import cn.stylefeng.guns.threads.UseAppliesThread;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
@Order(1)
public class AppConfig implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Override
    public void run(String... strings) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), namedThreadFactory);
        cachedThreadPool.execute(new TaskThreadPool());
        cachedThreadPool.execute(new UpDataThreadPool());
        cachedThreadPool.execute(new UpDataThreadPool2());
        logger.info("Test Start up complete");
    }

    static class TaskThreadPool implements Runnable {
        private final static Logger logger = LoggerFactory.getLogger(TaskThreadPool.class);

        @Override
        public void run() {

            try {

            } catch (Exception e) {
                logger.info("TaskThreadPool-->Start exception");
                e.printStackTrace();
            }
        }

    }

    static class UpDataThreadPool implements Runnable {
        private final static Logger logger = LoggerFactory.getLogger(UpDataThreadPool.class);

        @Override
        public void run() {
            try {
                UseAppliesThread.NotificationStockInfo();
                UseAppliesThread.startThread();
            } catch (Exception e) {
                logger.info("UpDataThreadPool-->Start exception");
                e.printStackTrace();
            }
        }

    }

    static class UpDataThreadPool2 implements Runnable {
        private final static Logger logger = LoggerFactory.getLogger(UpDataThreadPool2.class);

        @Override
        public void run() {
            try {
                GuiHuanAppliesThread.startThread();
            } catch (Exception e) {
                logger.info("UpDataThreadPool2-->Start exception");
                e.printStackTrace();
            }
        }

    }
}
