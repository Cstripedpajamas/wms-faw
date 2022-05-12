package cn.stylefeng.guns.config;

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

    @Override
    public void run(String... strings) {
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        cachedThreadPool.execute(new UpDataThreadPool());
        logger.info("Test Start up complete");

        BpmSendHeaderEntity bpmSendHeaderEntity=new BpmSendHeaderEntity();
        bpmSendHeaderEntity.setSender("JF_TWMS");
        bpmSendHeaderEntity.setReceiver("JF_BPM");
        bpmSendHeaderEntity.setTransID("1");
        bpmSendHeaderEntity.setMessageID("1");
        bpmSendHeaderEntity.setInterfaceID("TWMS-BPM-002");
        bpmSendHeaderEntity.setComment("1");
        bpmSendHeaderEntity.setCount("1");
        BpmSendBodyEntity bpmSendBodyEntity=new BpmSendBodyEntity();
        bpmSendBodyEntity.setApplyType(1);
        bpmSendBodyEntity.setMaterialName("物料名称");
        bpmSendBodyEntity.setMaterialNumber("物料编号");
        bpmSendBodyEntity.setApplyReason("原因");
        bpmSendBodyEntity.setEmployeeId("10191572");
        bpmSendBodyEntity.setMaterialQuantity(2);
        bpmSendBodyEntity.setProcessNo("111");
        bpmSendBodyEntity.setMaterialSku("物料SKU");
//        System.out.println(wmsApiService.sendBpm(bpmSendHeaderEntity,bpmSendBodyEntity));

        BpmSendHeaderEntity bpmSendHeaderEntity2=new BpmSendHeaderEntity();
        bpmSendHeaderEntity.setSender("JF_TWMS");
        bpmSendHeaderEntity.setReceiver("JF_BPM");
        bpmSendHeaderEntity.setTransID("1");
        bpmSendHeaderEntity.setMessageID("1");
        bpmSendHeaderEntity.setInterfaceID("TWMS-BPM-001");
        bpmSendHeaderEntity.setComment("1");
        bpmSendHeaderEntity.setCount("1");
        BpmSendBody2Entity bpmSendBody2Entity=new BpmSendBody2Entity();
        bpmSendBody2Entity.setEmployeeId("10191572");
        bpmSendBody2Entity.setPageNo(1);
        bpmSendBody2Entity.setPageSize(3);
//        System.out.println(wmsApiService.queryBpm(bpmSendHeaderEntity2,bpmSendBody2Entity));
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
