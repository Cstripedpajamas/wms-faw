package cn.stylefeng.guns.modular.fawInfo.userInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * faw基本人员信息表
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-14
 */
@TableName("faw_user_info")
public class FawUserInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 记录ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 员工IAM账号（IAMID）
     */
    @TableField("account_code")
    private String accountCode;

    /**
     * 员工ID
     */
    @TableField("employee_id")
    private String employeeId;

    /**
     * 所属组织编码
     */
    @TableField("dep_no")
    private String depNo;

    /**
     * 用户姓名
     */
    @TableField("employee_name")
    private String employeeName;

    /**
     * 电子邮件地址
     */
    @TableField("email_address")
    private String emailAddress;

    /**
     * 职级
     */
    @TableField("class_of_positions")
    private String classOfPositions;

    /**
     * 解放职级
     */
    @TableField("faw_cla_of_pos")
    private String fawClaOfPos;

    /**
     * 岗位
     */
    @TableField("jobs")
    private String jobs;

    /**
     * 直接上级ID
     */
    @TableField("director_id")
    private String directorId;

    /**
     * 直接上级姓名
     */
    @TableField("director_name")
    private String directorName;

    /**
     * 员工状态
     */
    @TableField("object_status")
    private String objectStatus;

    /**
     * 主数据人员类别
     */
    @TableField("mdm_type")
    private String mdmType;

    /**
     * 外部人员解放部门编码
     */
    @TableField("dept_level")
    private String deptLevel;

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

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getClassOfPositions() {
        return classOfPositions;
    }

    public void setClassOfPositions(String classOfPositions) {
        this.classOfPositions = classOfPositions;
    }

    public String getFawClaOfPos() {
        return fawClaOfPos;
    }

    public void setFawClaOfPos(String fawClaOfPos) {
        this.fawClaOfPos = fawClaOfPos;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(String objectStatus) {
        this.objectStatus = objectStatus;
    }

    public String getMdmType() {
        return mdmType;
    }

    public void setMdmType(String mdmType) {
        this.mdmType = mdmType;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    @Override
    public String toString() {
        return "FawUserInfo{" +
        "id=" + id +
        ", accountCode=" + accountCode +
        ", employeeId=" + employeeId +
        ", depNo=" + depNo +
        ", employeeName=" + employeeName +
        ", emailAddress=" + emailAddress +
        ", classOfPositions=" + classOfPositions +
        ", fawClaOfPos=" + fawClaOfPos +
        ", jobs=" + jobs +
        ", directorId=" + directorId +
        ", directorName=" + directorName +
        ", objectStatus=" + objectStatus +
        ", mdmType=" + mdmType +
        ", deptLevel=" + deptLevel +
        ", dataTime=" + dataTime +
        "}";
    }
}
