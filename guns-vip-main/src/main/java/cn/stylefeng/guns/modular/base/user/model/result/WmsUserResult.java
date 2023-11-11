package cn.stylefeng.guns.modular.base.user.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 人员信息表
 * </p>
 *
 * @author lhx
 * @since 2021-11-01
 */
@Data
public class WmsUserResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 员工IAM账号（IAMID）
     */
    private String accountcode;

    /**
     * 电子邮件地址
     */
    private String emailaddress;

    /**
     * 职级
     */
    private String classofpositions;

    /**
     * 解放职级
     */
    private String fawclaofpos;

    /**
     * 直接上级ID
     */
    private String directorid;

    /**
     * 直接上级姓名
     */
    private String directorname;

    /**
     * 员工状态
     */
    private String objectstatus;

    /**
     * 主数据人员类别
     */
    private String mdmtype;

    /**
     * 外部人员解放部门编码
     */
    private String deptlevel;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 班组
     */
    private String workTeam;

    /**
     * 岗位职能
     */
    private String jobResponsibility;

    /**
     * 识别信息码（人脸备用）
     */
    private String idInfo;

    /**
     * 人员类型（A管理员 B维修人员 C操作员）
     */
    private String userType;
    private String userTypeEx;

    /**
     * 数据状态（0使用/1禁用）
     */
    private String dataState;

    /**
     * 数据时间
     */
    private Date createTime;

    /**
     * 访问密码
     * */
    private String uPwd;

    /**
     * 手机号
     * */
    private String phoneNumber;

}
