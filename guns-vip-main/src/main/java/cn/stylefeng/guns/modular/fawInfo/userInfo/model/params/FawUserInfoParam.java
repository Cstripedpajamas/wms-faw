package cn.stylefeng.guns.modular.fawInfo.userInfo.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * faw基本人员信息表
 * </p>
 *
 * @author fubenhao
 * @since 2022-03-14
 */
@Data
public class FawUserInfoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 记录ID
     */
    private Long id;

    /**
     * 员工IAM账号（IAMID）
     */
    private String accountCode;

    /**
     * 员工ID
     */
    private String employeeId;

    /**
     * 所属组织编码
     */
    private String depNo;

    /**
     * 用户姓名
     */
    private String employeeName;

    /**
     * 电子邮件地址
     */
    private String emailAddress;

    /**
     * 职级
     */
    private String classOfPositions;

    /**
     * 解放职级
     */
    private String fawClaOfPos;

    /**
     * 岗位
     */
    private String jobs;

    /**
     * 直接上级ID
     */
    private String directorId;

    /**
     * 直接上级姓名
     */
    private String directorName;

    /**
     * 员工状态
     */
    private String objectStatus;

    /**
     * 主数据人员类别
     */
    private String mdmType;

    /**
     * 外部人员解放部门编码
     */
    private String deptLevel;

    /**
     * 数据时间
     */
    private Date dataTime;

    @Override
    public String checkParam() {
        return null;
    }

}
