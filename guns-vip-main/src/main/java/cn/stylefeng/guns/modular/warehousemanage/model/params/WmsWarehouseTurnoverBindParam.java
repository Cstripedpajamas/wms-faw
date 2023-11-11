package cn.stylefeng.guns.modular.warehousemanage.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 立库-周转箱绑定货物信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehouseTurnoverBindParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;

    /**
     * 格口编号
     * */
    public  static  enum Type{
        A000
    }

    /**
     * 记录ID
     */
    private Long id;

    /**
     * 周转箱信息ID
     */
    private String turnoverId;

    /**
     * 工厂
     */
    private String plant;

    /**
     * 格口编号
     */
    private String latticeCode;

    /**
     * 货物类型（1工具/2备品备件）
     */
    private String goodsType;

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
     * 单位
     */
    private String mUnit;

    /**
     * 物料编码
     */
    private String materialSerialNumber;

    /**
     * 批次
     */
    private String mBatch;

    /**
     * 数量
     */
    private String mNumber;

    /**
     * 数据时间
     */
    private Date createTime;

    /**
     * 开始时间
     * */
    private String startTime;

    /**
     * 结束时间
     * */

    private String endTime;

    /**
     * 格口状态
     * */
    private String latticeState;

    /**
     * 格口状态
     * */
    private String barcode;

    /**
     * 格口状态
     * */
    private String sizes;

    @Override
    public String checkParam() {
        return null;
    }

}
