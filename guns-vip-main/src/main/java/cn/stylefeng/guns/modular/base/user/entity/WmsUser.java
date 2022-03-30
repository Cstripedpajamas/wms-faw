package cn.stylefeng.guns.modular.base.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 人员信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@TableName("wms_user")
public class WmsUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编号
     */
    @TableField("serial_number")
    private String serialNumber;

    /**
     * 姓名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 班组
     */
    @TableField("work_team")
    private String workTeam;

    /**
     * 岗位职能
     */
    @TableField("job_responsibility")
    private String jobResponsibility;

    /**
     * 识别信息码（人脸备用）
     */
    @TableField("id_info")
    private String idInfo;

    /**
     * 人员类型（A管理员 B维修人员 C操作员）
     */
    @TableField("user_type")
    private String userType;

    /**
     * 数据状态（0使用/1禁用）
     */
    @TableField("data_state")
    private String dataState;

    /**
     * 数据时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

      /**
       * 访问密码
       * */
      @TableField(value = "u_pwd")
      private String uPwd;

    @Override
    public String toString() {
        return "WmsUser{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", workTeam='" + workTeam + '\'' +
                ", jobResponsibility='" + jobResponsibility + '\'' +
                ", idInfo='" + idInfo + '\'' +
                ", userType='" + userType + '\'' +
                ", dataState='" + dataState + '\'' +
                ", createTime=" + createTime +
                ", uPwd='" + uPwd + '\'' +
                '}';
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkTeam() {
        return workTeam;
    }

    public void setWorkTeam(String workTeam) {
        this.workTeam = workTeam;
    }

    public String getJobResponsibility() {
        return jobResponsibility;
    }

    public void setJobResponsibility(String jobResponsibility) {
        this.jobResponsibility = jobResponsibility;
    }

    public String getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(String idInfo) {
        this.idInfo = idInfo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
