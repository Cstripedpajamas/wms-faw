package cn.stylefeng.guns.threads;

import cn.stylefeng.guns.hikvision.jna_test;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseToolUseTask;
import cn.stylefeng.guns.modular.warehousemanage.model.params.WmsWarehouseToolUseTaskParam;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseToolUseTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseToolUseTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName DoorCtonThread
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/5/16 11:42
 * @Version 1.0
 **/
@Component
public class DoorCtonThread {

    private final static Logger logger = LoggerFactory.getLogger(DoorCtonThread.class);

    public static DoorCtonThread thread;

    private static long oldOkTime=0;

    private static String olduser="";

    @Autowired
    private WmsWarehouseToolUseTaskService wmsWarehouseToolUseTaskService;

    @PostConstruct
    public void init() {
        thread = this;
    }

    public static void openDoor(String dwSerialNo,String byCardNo) {
        logger.info("人脸认证通过 -- 事件流水号 : "+dwSerialNo);
        logger.info("人脸认证通过 -- 卡号 : "+byCardNo);

        Date dateNew=new Date();
        long dateNewNow=dateNew.getTime();

        if (olduser.equals(byCardNo)){
            long timeBj=dateNewNow-oldOkTime;
            if (timeBj> 30000){
                checkUserTask(byCardNo,dateNewNow);
            }
        }else {
            checkUserTask(byCardNo,dateNewNow);
        }
    }

    private static void checkUserTask(String userId,long dateNewNow){
        boolean rs=false;
        WmsWarehouseToolUseTaskParam wmsWarehouseToolUseTaskParam=new WmsWarehouseToolUseTaskParam();
        wmsWarehouseToolUseTaskParam.setOperator(userId);
        List<WmsWarehouseToolUseTaskResult> wmsWarehouseToolUseTaskResult=thread.wmsWarehouseToolUseTaskService.findByTaskStateOfOperator(wmsWarehouseToolUseTaskParam);
        if (!wmsWarehouseToolUseTaskResult.isEmpty()){
            rs=true;
        }
        if (rs){
            try {
                jna_test.SendDoorKz(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            olduser=userId;
            oldOkTime=dateNewNow;
            logger.info("人脸认证通过 -- 开门完成");
        }
    }
}
