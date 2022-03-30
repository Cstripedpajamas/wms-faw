package cn.stylefeng.guns.modular.sparePartsManagement.requestMsg.task;

import cn.stylefeng.roses.kernel.model.response.ResponseData;

/**
 * @Author: ll
 * @Date: 2021/11/8 17:12
 * @Version 1.0
 */
public interface TaskThreadInf {
    ResponseData  receiveFlow(String id,String scrapNumber);
//    void replenFlow();
}
