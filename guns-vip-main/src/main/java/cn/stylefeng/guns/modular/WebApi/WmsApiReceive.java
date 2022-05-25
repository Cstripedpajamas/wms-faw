package cn.stylefeng.guns.modular.WebApi;

import cn.stylefeng.guns.modular.base.redirect.RedirectConfController;
import cn.stylefeng.guns.modular.onetypecabinet.entity.WmsIntelligentCabinet1Stock;
import cn.stylefeng.guns.modular.onetypecabinet.model.params.WmsIntelligentCabinet1StockParam;
import cn.stylefeng.guns.modular.onetypecabinet.model.result.WmsIntelligentCabinet1StockResult;
import cn.stylefeng.guns.modular.onetypecabinet.service.WmsIntelligentCabinet1StockService;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.*;


/**
 * @Author: ll
 * @Date: 2021/11/3 9:52
 * @Version 1.0
 * @Des WebApi 接收请求
 */
@Controller
@RequestMapping("/WmsApi/Receive")
@Slf4j
public class WmsApiReceive {


    @Autowired //
    private WmsIntelligentCabinet1StockService wmsIntelligentCabinet1StockService;

    // 认证人员信息 工具柜界面
    @RequestMapping(value = "/StaffId",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData checkUserMsg(@RequestBody String StaffId){
        log.info("First class cabinet face recognition number{}",StaffId);
         WmsApiMethods.I(StaffId);
         return ResponseData.success();
    }


    // 关闭格口(工具类)
    @RequestMapping(value = "/LockClosed",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData LockClosed(@RequestBody String LocationId){
        JSONObject jsonObject = JSON.parseObject(LocationId);
        String str = jsonObject.getString("LocationId");
            log.info("Closing grid number{}",str);
        WmsIntelligentCabinet1Stock wmsIntelligentCabinet1Stock = wmsIntelligentCabinet1StockService.getOne(new QueryWrapper<WmsIntelligentCabinet1Stock>().eq("loca_serial_number",str));
        if (wmsIntelligentCabinet1Stock!=null&&wmsIntelligentCabinet1Stock.getId()!=null){
            WmsIntelligentCabinet1StockParam stockParam = new WmsIntelligentCabinet1StockParam();
            ToolUtil.copyProperties(wmsIntelligentCabinet1Stock,stockParam);
            stockParam.setLatticeState(StateEnum.ONE.getState());
            wmsIntelligentCabinet1StockService.update(stockParam);
        }
        return ResponseData.success();
    }


    /**
     * 打开格口
     */
    @RequestMapping("/open")
    @ResponseBody
    public ResponseData open(WmsIntelligentCabinet1StockParam wmsIntelligentCabinet1StockParam) {
        WmsIntelligentCabinet1Stock detail = this.wmsIntelligentCabinet1StockService.getById(wmsIntelligentCabinet1StockParam.getId());
        Map<String,String> param = new HashMap<>();
        param.put("LocationId",detail.getLocaSerialNumber());
        List list=new ArrayList();
        list.add(param);
        Map<String,Object> map = new HashMap<>();
        map.put("List",list);
        WmsApiService.openDeclension(map);
        wmsIntelligentCabinet1StockParam.setLatticeState("0");
        this.wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1StockParam);
        return ResponseData.success();
    }

    /**
     * 打开全部格口
     */
    @RequestMapping("/openAll")
    @ResponseBody
    public ResponseData openAll() {
        Map<String,String> param = new HashMap<>();
        List<WmsIntelligentCabinet1StockResult> wmsIntelligentCabinet1StockResultList=this.wmsIntelligentCabinet1StockService.findListBySpec(new WmsIntelligentCabinet1StockParam());
        if (wmsIntelligentCabinet1StockResultList!=null&&!wmsIntelligentCabinet1StockResultList.isEmpty()){
            for (int i = 0; i < wmsIntelligentCabinet1StockResultList.size(); i++) {
                WmsIntelligentCabinet1StockResult wmsIntelligentCabinet1StockResult=wmsIntelligentCabinet1StockResultList.get(i);
                param.put("LocationId",wmsIntelligentCabinet1StockResult.getLocaSerialNumber());
            }
            Map<String,Object> map = new HashMap<>();
            map.put("List",param);
            WmsApiService.openDeclension(map);
            for (int i = 0; i < wmsIntelligentCabinet1StockResultList.size(); i++) {
                WmsIntelligentCabinet1StockResult wmsIntelligentCabinet1StockResult=wmsIntelligentCabinet1StockResultList.get(i);
                WmsIntelligentCabinet1StockParam wmsIntelligentCabinet1StockParam=new WmsIntelligentCabinet1StockParam();
                wmsIntelligentCabinet1StockParam.setId(wmsIntelligentCabinet1StockResult.getId());
                wmsIntelligentCabinet1StockParam.setLatticeState("0");
                this.wmsIntelligentCabinet1StockService.update(wmsIntelligentCabinet1StockParam);
            }
        }
        return ResponseData.success();
    }


    @PostMapping( value = "/sorting")
    @ResponseBody
    @ApiOperation(value = "分拣站反馈信息")
   public cn.hutool.json.JSONObject TaskcbackInfo(@RequestBody String param){
        JSONObject jsonObject = JSON.parseObject(param);
        // 任务标识(order_id), 任务状态(task_status:OK / ER), 任务数量(task_number), 实际分拣数量(sorting_number), 异常代码(er_code: ER1/ER2)
        System.out.println(jsonObject.get("order_id"));
        System.out.println(jsonObject.get("task_status"));
        System.out.println(jsonObject.get("task_number"));
        System.out.println(jsonObject.get("sorting_number"));
        System.out.println(jsonObject.get("er_code"));
        RedirectConfController.sort_1= String.valueOf(jsonObject.get("order_id"));
        RedirectConfController.sort_2= String.valueOf(jsonObject.get("task_status"));
        RedirectConfController.sort_3= String.valueOf(jsonObject.get("task_number"));
        RedirectConfController.sort_4_1= String.valueOf(jsonObject.get("sorting_number"));
        RedirectConfController.sort_4_2= String.valueOf(jsonObject.get("er_code"));

        String orderId= String.valueOf(jsonObject.get("order_id"));
        if (orderId.split("-")[0].equals("tool")){
            // todo 工具领用 自动分拣业务

        }
        if (orderId.split("-")[0].equals("spare")){
            // todo 备件补货 自动分拣业务

        }

        cn.hutool.json.JSONObject object = new cn.hutool.json.JSONObject();
        object.put("Code", 200);
        object.put("errMsg", "成功");
        object.put("data", null);
        return object;
    }


    @PostMapping( value = "/test")
    @ResponseBody
    public  String test(@RequestBody String param){
        System.out.println(param);
        JSONObject jsonObject = JSON.parseObject(param);
        // 任务标识(order_id), 任务状态(task_status:OK / ER), 任务数量(task_number), 实际分拣数量(sorting_number), 异常代码(er_code: ER1/ER2)
        System.out.println(jsonObject.get("batch_id"));
        System.out.println(jsonObject.get("task_nums"));
        System.out.println(jsonObject.get("pick_tote"));
        System.out.println(jsonObject.get("dimension"));

        Map<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","success");
        map.put("data",null);

        // todo 业务逻辑
        return  JSONObject.toJSONString(map);
    }
}
