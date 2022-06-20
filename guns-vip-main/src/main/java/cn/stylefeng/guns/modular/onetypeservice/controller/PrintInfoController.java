package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.onetypeservice.enums.ApplyType;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by li wen ya on 2021/11/24
 */
@RestController
@CrossOrigin
@RequestMapping("/print")
@Api(description = "条码打印业务")
public class PrintInfoController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/list")
    @ApiOperation(value = "1.打印内容获取")
    public LayuiPageInfo printList(){
        return warehouseService.printList();
    }

    @GetMapping("/print")
    @ApiOperation(value = "2.提交打印内容 如：GH001,GH002,GH003.....")
    public ResponseData print(@ApiParam(value = "多个工具编号逗号隔开") @RequestParam String materialSerialNumber){
        warehouseService.print(materialSerialNumber);
        return ResponseData.success();
    }

    @GetMapping("/order")
    @ApiOperation(value = "3.采购订单信息获取")
    public LayuiPageInfo printPurchase(){
        return warehouseService.printPurchase();
    }

    @GetMapping("/generator-code")
    @ApiOperation(value = "4.采购生成条码任务")
    public ResponseData generatorCode(@ApiParam(value = "用户编号") @RequestParam String serialNumber,
                                           @ApiParam(value = "采购单号") @RequestParam String purNumber,
                                           @ApiParam(value = "打印数量") @RequestParam String printNum){
        warehouseService.printGeneratorCode(serialNumber,purNumber,printNum);
        return ResponseData.success();
    }

    @GetMapping("/generator-code-print")
    @ApiOperation(value = "5.采购打印生成条码")
    public ResponseData generatorCodePrint(){
        return warehouseService.printSupplement(ApplyType.B);

    }

    @GetMapping("/use-tool")
    @ApiOperation(value = "6.获取所有领用工具信息")
    public LayuiPageInfo useTool(@ApiParam(value = "用户编号") @RequestParam String serialNumber){
        return warehouseService.useToolInfo(serialNumber);
    }

    @GetMapping("/supplement")
    @ApiOperation(value = "7.生成补打任务")
    public ResponseData supplement(@ApiParam(value = "用户编号") @RequestParam String serialNumber,
                                   @ApiParam(value = "领用工具ID") @RequestParam String toolUseId,
                                   @ApiParam(value = "标志：A:人员 B:编码") @RequestParam String flag){
        return warehouseService.supplement(serialNumber,toolUseId,flag);
    }

    @GetMapping("/print-supplement")
    @ApiOperation(value = "8.补打打印")
    public ResponseData printSupplement(){
        return warehouseService.printSupplement(ApplyType.A);
    }

    @GetMapping("/print-material-type")
    @ApiOperation(value = "9.获取所有工具类型信息")
    public ResponseData printMaterialType(){
        return warehouseService.printMaterialType();
    }

    @GetMapping("/print-material")
    @ApiOperation(value = "10.获取所有工具信息")
    public LayuiPageInfo printMaterial(@ApiParam(value = "物料类型Id") @RequestParam String materialTypeId){
        return warehouseService.printMaterial(materialTypeId);
    }








}
