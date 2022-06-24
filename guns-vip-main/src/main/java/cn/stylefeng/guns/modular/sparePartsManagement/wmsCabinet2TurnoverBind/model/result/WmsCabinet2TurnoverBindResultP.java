package cn.stylefeng.guns.modular.sparePartsManagement.wmsCabinet2TurnoverBind.model.result;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ll
 * @Date: 2022/6/23 10:17
 * @Version 1.0
 */
@Data
public class WmsCabinet2TurnoverBindResultP implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 周转箱信息ID
     */
    private String turnoverId;

    /**
     * 周转箱编号
     * */
    private String turnoverNumber;

    /**
     * 物料编码
     * */
    private  String materialSerialNumber;
    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料信息ID
     */
    private String materialId;

    /**
     * 物料类型
     */
    private String materialType;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料SKU
     */
    private String materialSku;

    /**
     * 批次
     */
    private String mBatch;

    /**
     * 单位
     */
    private String mUnit;

    /**
     * 数量
     */
    private int mNumber;

    /**
     * 数据时间
     */
    private Date createTime;

}
