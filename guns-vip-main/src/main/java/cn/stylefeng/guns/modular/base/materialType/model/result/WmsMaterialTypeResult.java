package cn.stylefeng.guns.modular.base.materialType.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 物料类型信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsMaterialTypeResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 类型（1工具 2备品备件）
     */
    private String type;
    private String typeEx;

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
     * 格口类型(仅工具)
     */
    private String latticeMouthType;
    private String typeName;

    /**
     * 数据状态（0使用/1禁用）
     */
    private String dataState;

    /**
     * 数据时间
     */
    private Date createTime;


    /**
     * 分拣类型
     * */
    private String sortType;

    /**
     * 包装类型
     * */
    private String packageType;

    /**
     * 包装数量
     * */
    private String packageNumber;


    /**
     * 周转箱类型
     * */
    private String turnoverType;

    /**
     * @author  ll
     * @Date 2021年11月8日14:20:10
     * @Des 投入报废计数,将数量返回给前台;
     * */
    private String number;


    /**
     * 周转箱格口数量
     * */
    private String turnoverLatticeType;

    /**
     *  RFID 标识
     * */
    private String label;




}
