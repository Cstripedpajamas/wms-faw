package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.onetypeservice.response.WarehouseTurnoverInfo;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnover;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsWarehouseTurnoverBind;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsWarehouseTurnoverBindService;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by li wen ya on 2021/11/24
 */
@RestController
@CrossOrigin
@RequestMapping("/out-in")
@Api(description = "出库入库业务操作")
public class WarehouseOutInController {

    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;
    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    @ApiOperation(value = "手动出库 - 1.查询物料类型")
    @GetMapping(value = "/out-type")
    public ResponseData findMaterialTypeAll(){
        return warehouseService.findMaterialTypeAll();
    }

    @ApiOperation(value = "手动出库 - 2.查询周转箱信息")
    @GetMapping(value = "/out-type-list")
    public LayuiPageInfo findList(String materialTypeId){
        WmsMaterialTypeResult byId = wmsMaterialTypeService.findById(materialTypeId);
        if (byId.getMaterialSku().startsWith("box_")){
            String turnoverType = byId.getTurnoverType();
            String turnoverLatticeType = byId.getTurnoverLatticeType();
            byId.setTurnoverType(Objects.equals("0",turnoverType)? "A" :Objects.equals("1",turnoverType) ? "B" : "C");
            byId.setTurnoverLatticeType(Objects.equals("0",turnoverLatticeType)?"1" : "4");
            return  wmsWarehouseTurnoverBindService.findTurnoverMsg(byId);
        }
        return warehouseService.findWarehouseList(materialTypeId);
    }

    @ApiOperation(value = "手动出库 - 2.查询周转箱信息")
    @GetMapping(value = "/out-type-list-vue")
    public LayuiPageInfo findListByPara(String sku, String plant, String materialType,String diBatchNo){
        if (materialType.isEmpty() && diBatchNo.isEmpty()){
            String materialTypeId = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                    .eq("material_sku",sku)
                    .eq("plant",plant)
            ).get(0).getId().toString();
            WmsMaterialTypeResult byId = wmsMaterialTypeService.findById(materialTypeId);

            if (byId.getMaterialSku().startsWith("box_")){
                String turnoverType = byId.getTurnoverType();
                String turnoverLatticeType = byId.getTurnoverLatticeType();
                byId.setTurnoverType(Objects.equals("0",turnoverType)? "A" :Objects.equals("1",turnoverType) ? "B" : "C");
                byId.setTurnoverLatticeType(Objects.equals("0",turnoverLatticeType)?"1" : "4");
                return  wmsWarehouseTurnoverBindService.findTurnoverMsg(byId);
            }
            return warehouseService.findWarehouseList(materialTypeId);
        }else if(diBatchNo.isEmpty() && !materialType.isEmpty() && !sku.isEmpty() && !plant.isEmpty() ){
            String materialTypeId = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                    .eq("material_sku",sku)
                    .eq("plant",plant)
                    .eq("material_type",materialType)
            ).get(0).getId().toString();
            WmsMaterialTypeResult byId = wmsMaterialTypeService.findById(materialTypeId);

            if (byId.getMaterialSku().startsWith("box_")){
                String turnoverType = byId.getTurnoverType();
                String turnoverLatticeType = byId.getTurnoverLatticeType();
                byId.setTurnoverType(Objects.equals("0",turnoverType)? "A" :Objects.equals("1",turnoverType) ? "B" : "C");
                byId.setTurnoverLatticeType(Objects.equals("0",turnoverLatticeType)?"1" : "4");
                return  wmsWarehouseTurnoverBindService.findTurnoverMsg(byId);
            }
            return warehouseService.findWarehouseList(materialTypeId);
        }else {
            String materialTypeId = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                    .eq("material_sku",sku)
                    .eq("plant",plant)
                    .eq("material_type",materialType)
                    .eq("di_batchNo",diBatchNo)
            ).get(0).getId().toString();
            WmsMaterialTypeResult byId = wmsMaterialTypeService.findById(materialTypeId);

            if (byId.getMaterialSku().startsWith("box_")){
                String turnoverType = byId.getTurnoverType();
                String turnoverLatticeType = byId.getTurnoverLatticeType();
                byId.setTurnoverType(Objects.equals("0",turnoverType)? "A" :Objects.equals("1",turnoverType) ? "B" : "C");
                byId.setTurnoverLatticeType(Objects.equals("0",turnoverLatticeType)?"1" : "4");
                return  wmsWarehouseTurnoverBindService.findTurnoverMsg(byId);
            }
            return warehouseService.findWarehouseList(materialTypeId);
        }
    }

    @ApiOperation(value = "手动出库 - 3.出库接口")
    @GetMapping(value = "/out-warehouse")
    public ResponseData outWarehouse(String sku, String plant, String materialType,String diBatchNo){
        if (materialType.isEmpty() && diBatchNo.isEmpty()){
            String materialTypeId = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                    .eq("material_sku",sku)
                    .eq("plant",plant)
            ).get(0).getId().toString();
            WmsMaterialTypeResult byId = wmsMaterialTypeService.findById(materialTypeId);
            if (byId.getMaterialSku().startsWith("box_")){
                String turnoverType = byId.getTurnoverType();
                String turnoverLatticeType = byId.getTurnoverLatticeType();
                byId.setTurnoverType(Objects.equals("0",turnoverType)? "A" :Objects.equals("1",turnoverType) ? "B" : "C");
                byId.setTurnoverLatticeType(Objects.equals("0",turnoverLatticeType)?"1" : "4");
                LayuiPageInfo turnoverMsg = wmsWarehouseTurnoverBindService.findTurnoverMsg(byId);
                if (turnoverMsg.getData().size() > 0){
                    return  warehouseService.outWarehouse_empty(byId); // 空周转箱出库
                }
                else return ResponseData.error("空周转箱不足~");
            }
            return warehouseService.outWarehouse(materialTypeId);
        } else if(diBatchNo.isEmpty() && !materialType.isEmpty() && !sku.isEmpty() && !plant.isEmpty() ){
            String materialTypeId = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                    .eq("material_sku",sku)
                    .eq("plant",plant)
                    .eq("material_type",materialType)
            ).get(0).getId().toString();
            WmsMaterialTypeResult byId = wmsMaterialTypeService.findById(materialTypeId);
            if (byId.getMaterialSku().startsWith("box_")){
                String turnoverType = byId.getTurnoverType();
                String turnoverLatticeType = byId.getTurnoverLatticeType();
                byId.setTurnoverType(Objects.equals("0",turnoverType)? "A" :Objects.equals("1",turnoverType) ? "B" : "C");
                byId.setTurnoverLatticeType(Objects.equals("0",turnoverLatticeType)?"1" : "4");
                LayuiPageInfo turnoverMsg = wmsWarehouseTurnoverBindService.findTurnoverMsg(byId);
                if (turnoverMsg.getData().size() > 0){
                    return  warehouseService.outWarehouse_empty(byId); // 空周转箱出库
                }
                else return ResponseData.error("空周转箱不足~");
            }
            return warehouseService.outWarehouse(materialTypeId);
        }else {
            String materialTypeId = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>()
                    .eq("material_sku",sku)
                    .eq("plant",plant)
                    .eq("material_type",materialType)
                    .eq("di_batchNo",diBatchNo)
            ).get(0).getId().toString();
            WmsMaterialTypeResult byId = wmsMaterialTypeService.findById(materialTypeId);
            if (byId.getMaterialSku().startsWith("box_")){
                String turnoverType = byId.getTurnoverType();
                String turnoverLatticeType = byId.getTurnoverLatticeType();
                byId.setTurnoverType(Objects.equals("0",turnoverType)? "A" :Objects.equals("1",turnoverType) ? "B" : "C");
                byId.setTurnoverLatticeType(Objects.equals("0",turnoverLatticeType)?"1" : "4");
                LayuiPageInfo turnoverMsg = wmsWarehouseTurnoverBindService.findTurnoverMsg(byId);
                if (turnoverMsg.getData().size() > 0){
                    return  warehouseService.outWarehouse_empty(byId); // 空周转箱出库
                }
                else return ResponseData.error("空周转箱不足~");
            }
            return warehouseService.outWarehouse(materialTypeId);
        }
    }

    @ApiOperation(value = "手动入库 - 1.获取周转型信息")
    @GetMapping(value = "/in-info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = WarehouseTurnoverInfo.class),
            @ApiResponse(code = 200, message = "OK", response = WmsWarehouseTurnover.class),
            @ApiResponse(code = 200, message = "OK", response = WmsWarehouseTurnoverBind.class)
    })
    public ResponseData warehouseTurnoverInfo(@ApiParam(value = "周转箱编号") @RequestParam String turnoverNumber){
        return warehouseService.warehouseTurnoverInfo(turnoverNumber);
    }

    @ApiOperation(value = "手动入库 - 2.入库接口")
    @GetMapping(value = "/in-warehouse")
    public ResponseData inWarehouse(@ApiParam(value = "周转箱编号") @RequestParam String turnoverNumber){
        return warehouseService.inWarehouse(turnoverNumber);
    }

    @ApiOperation(value = "手动出库 - 3.WCS请求入库成功")
    @GetMapping(value = "/in-complement")
    public ResponseData inComplement(@ApiParam(value = "库位信息") @RequestParam String locaNumber,
                                     @ApiParam(value = "流程单号") @RequestParam String messageId){
        warehouseService.inComplement(locaNumber,messageId);
        return ResponseData.success();
    }


}
