package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ll
 * @Date: 2022/6/23 11:06
 * @Version 1.0
 */
@Data
public class BatchEnt implements Serializable {

    /**
     * 批次
     * */
    private String mBatch;

    /**
     * 数量
     * */
    private String total;

    /**
     * 单位
     * */

    private String mUnit;
}
