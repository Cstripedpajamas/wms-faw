package cn.stylefeng.guns.modular.warehousemanage.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ll
 * @Date: 2022/5/16 12:00
 * @Version 1.0
 */
@Data
public class StockSync implements Serializable, BaseValidatingParam {

    private String ContainerCode;

    private String LocationCode;
}
