package cn.stylefeng.guns.modular.warehousemanage.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 立库-仓库库存信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-02
 */
@Data
public class WmsWarehouseStockParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 库位编号
     */
    private String locaNumber;

    /**
     * 巷道（A=1巷道 B=2巷道 C=3巷道）
     */
    private String roadway;

    /**
     * 地址-排
     */
    private String locaRow;

    /**
     * 地址-列
     */
    private String locaCol;

    /**
     * 地址-层
     */
    private String locaLayer;

    /**
     * 库位设备码
     */
    private String locaEquipment;

    /**
     * 库位状态（0空闲/1有货/2锁定）
     */
    private String locaState;

    /**
     * 周转箱信息ID
     */
    private String turnoverId;

   /**
    * 备注
    * */
   private String mark;

   /**
    * 备用字段
    * */
   private String spareField;

    /**
     * 数据时间
     */
    private Date createTime;

    @Override
    public String checkParam() {
        return null;
    }

}
