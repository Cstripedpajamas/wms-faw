package cn.stylefeng.guns.modular.onetypecabinet.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 条码打印信息表
 * </p>
 *
 * @author liwenya
 * @since 2021-11-24
 */
@TableName("wms_print_info")
public class WmsPrintInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 工具编码
     */
    @TableField("material_serial_number")
    private String materialSerialNumber;

    /**
     * 操作人
     */
    @TableField("operator")
    private String operator;

    /**
     * 打印类型（A补打 B采购）
     */
    @TableField("print_type")
    private String printType;

    /**
     * 打印状态（0生成 1打印 2完成 3失败 4结束）
     */
    @TableField("print_stu")
    private String printStu;

    /**
     * 生成时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 数据时间
     */
    @TableField("data_time")
    private Date dataTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialSerialNumber() {
        return materialSerialNumber;
    }

    public void setMaterialSerialNumber(String materialSerialNumber) {
        this.materialSerialNumber = materialSerialNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getPrintStu() {
        return printStu;
    }

    public void setPrintStu(String printStu) {
        this.printStu = printStu;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    @Override
    public String toString() {
        return "WmsPrintInfo{" +
        "id=" + id +
        ", materialSerialNumber=" + materialSerialNumber +
        ", operator=" + operator +
        ", printType=" + printType +
        ", printStu=" + printStu +
        ", createTime=" + createTime +
        ", dataTime=" + dataTime +
        "}";
    }
}
