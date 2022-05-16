package cn.stylefeng.guns.config;

import cn.stylefeng.guns.hikvision.FMSGCallBack_V31;
import cn.stylefeng.guns.hikvision.HCNetSDK;
import cn.stylefeng.guns.hikvision.jna_User;
import cn.stylefeng.guns.modular.WebApi.Entity.BpmSendBody2Entity;
import cn.stylefeng.guns.modular.WebApi.Entity.BpmSendBodyEntity;
import cn.stylefeng.guns.modular.WebApi.Entity.BpmSendHeaderEntity;
import cn.stylefeng.guns.modular.WebApi.WmsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Order(1)
public class AppConfig implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    private WmsApiService wmsApiService;

    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;

    @Override
    public void run(String... strings) {
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        cachedThreadPool.execute(new TaskThreadPool());
        hisvisonStart();
        logger.info("Test Start up complete");
    }

    private void hisvisonStart(){
        boolean initSuc = hCNetSDK.NET_DVR_Init();
        if (initSuc != true) {
            System.out.println("初始化失败");
        }
        // 打印SDK日志
        hCNetSDK.NET_DVR_SetLogToFile(3, ".\\SDKLog\\", false);
        jna_User.startHaK();
    }

    static class TaskThreadPool implements Runnable {
        private final static Logger logger = LoggerFactory.getLogger(TaskThreadPool.class);

        @Override
        public void run() {

            try {

            } catch (Exception e) {
                logger.info("Task-->Start exception");
                e.printStackTrace();
            }
        }

    }

    static class UpDataThreadPool implements Runnable {
        private final static Logger logger = LoggerFactory.getLogger(UpDataThreadPool.class);

        @Override
        public void run() {
            try {

            } catch (Exception e) {
                logger.info("Task-->Start exception");
                e.printStackTrace();
            }
        }

    }
}
