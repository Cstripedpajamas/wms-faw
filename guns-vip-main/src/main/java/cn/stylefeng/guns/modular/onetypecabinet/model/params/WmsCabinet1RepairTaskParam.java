package cn.stylefeng.guns.modular.onetypecabinet.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Ⅰ类柜维修任务信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-01
 */
@Data
public class WmsCabinet1RepairTaskParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 任务编号
     */
    private String taskNumber;

    /**
     * 物料类型ID
     */
    private String materialTypeId;

    /**
     * 物料名称
     */
        private String materialName;

    /**
     * 物料SKU
     */
    private String materialSku;

    /**
     * 物料信息ID
     */
    private String materialId;

    /**
     * 物料编码
     */
    private String materialSerialNumber;

    /**
     * 库存信息ID
     */
    private String stockId;

    /**
     * 库位编号
     */
    private String locaNumber;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 人员姓名
     */
    private String userName;

    /**
     * 操作时间
     */
    private Date operationTime;

    /**
     * 任务状态(0初始 1开始 2开始取货 3取货完成 4结束 )
     */
    private String taskState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 规格型号
     */
    private String sizes;
    /**
     * 组合编码
     */
    private String workTeam;
    /**
     * 物料类型
     */
    private String type;
    @Override
    public String checkParam() {
        return null;
    }

}
