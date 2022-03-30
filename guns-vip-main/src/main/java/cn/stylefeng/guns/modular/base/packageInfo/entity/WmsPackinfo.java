package cn.stylefeng.guns.modular.base.packageInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 包装信息表
 * </p>
 *
 * @author ll
 * @since 2021-12-14
 */
@TableName("wms_packinfo")
public class WmsPackinfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 包装名称
     */
    @TableField("packge_name")
    private String packgeName;

    /**
     * 包装规格
     */
    @TableField("packge_specif")
    private String packgeSpecif;

    /**
     * 备注
     */
    @TableField("mark")
    private String mark;

    /**
     * 数据时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackgeName() {
        return packgeName;
    }

    public void setPackgeName(String packgeName) {
        this.packgeName = packgeName;
    }

    public String getPackgeSpecif() {
        return packgeSpecif;
    }

    public void setPackgeSpecif(String packgeSpecif) {
        this.packgeSpecif = packgeSpecif;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WmsPackinfo{" +
        "id=" + id +
        ", packgeName=" + packgeName +
        ", packgeSpecif=" + packgeSpecif +
        ", mark=" + mark +
        ", createTime=" + createTime +
        "}";
    }
}
