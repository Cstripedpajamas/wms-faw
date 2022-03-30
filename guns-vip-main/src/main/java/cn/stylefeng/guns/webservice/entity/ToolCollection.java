package cn.stylefeng.guns.webservice.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ToolCollection
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/3/1 10:10
 * @Version 1.0
 **/
@Data
public class ToolCollection {
    String Employee_NO;
    String Employee_Name;
    List<ToolCollectionInfo> toolCollectionInfos;
}
