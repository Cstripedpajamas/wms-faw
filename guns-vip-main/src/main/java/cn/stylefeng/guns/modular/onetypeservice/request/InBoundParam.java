package cn.stylefeng.guns.modular.onetypeservice.request;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ll
 * @Date: 2022/1/11 22:00
 * @Version 1.0
 */
@Data
public class InBoundParam  implements Serializable, BaseValidatingParam {

    private String materialSKU;
    private String batch;
    private Integer number;
}
