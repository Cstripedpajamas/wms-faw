package cn.stylefeng.guns.modular.base.redirect;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.stylefeng.guns.modular.WebApi.Entity.runBatch;
import cn.stylefeng.guns.modular.WebApi.WmsApiService;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.packageInfo.entity.WmsPackinfo;
import cn.stylefeng.guns.modular.base.packageInfo.service.WmsPackinfoService;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsSortingTask;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.service.WmsSortingTaskService;
import cn.stylefeng.guns.print.ZplPrinter;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static cn.stylefeng.guns.print.ZplPrinterTest.*;



/**
 * 界面展示接收值，并转发请求
 */
@Controller
@RequestMapping("/redirect")
@CrossOrigin
@Slf4j
public class RedirectConfController extends BaseController {

    private String PREFIX = "/modular/base";

    String url = "http://127.0.0.1:8099/faw/WmsApi";
//    String url = "http://220.185.159.170:9114/faw/WmsApi";

    @Autowired
    private WmsApiService wmsApiService;

    @Autowired
    private WmsSortingTaskService wmsSortingTaskService;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;

    @Autowired
    private WmsPackinfoService wmsPackinfoService;


    /**
     * 跳转到主页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/redirect.html";
    }

    // 功能一回显数据
    String oneList = null;
    String LocationId = null;

    // 功能二回显数据
    String twoOrderId = null;
    String twoHUNumber = null;
    String twoLocationId = null;

    // 功能三回显数据
    String threeOrderId = null;
    String threeHUNumber = null;
    String threeLocationId = null;

    // 功能四回显数据
    String fourOrderId = null;
    String fourScrapNumber = null;

    // 功能五回显数据
    public static String sort_1 = null;
    public static String sort_2 = null;
    public static String sort_3 = null;
    public static String sort_4_1 = null;
    public static String sort_4_2 = null;

    /**
     * 获取所有需要显示的数据
     */
    @RequestMapping("/getData")
    @ResponseBody
    public JSONObject getData() {
        JSONObject object = new JSONObject();
        object.put("oneList", oneList);
        object.put("LocationId", LocationId);
        object.put("twoOrderId", twoOrderId);
        object.put("twoHUNumber", twoHUNumber);
        object.put("twoLocationId", twoLocationId);
        object.put("threeOrderId", threeOrderId);
        object.put("threeHUNumber", threeHUNumber);
        object.put("threeLocationId", threeLocationId);
        object.put("fourOrderId", fourOrderId);
        object.put("fourScrapNumber", fourScrapNumber);

        object.put("sort_1", sort_1);
        object.put("sort_2", sort_2);
        object.put("sort_3", sort_3);
        object.put("sort_4_1", sort_4_1);
        object.put("sort_4_2", sort_4_2);
        return object;
    }

    /**
     * 打开格口
     * 返回
     * <pre>
     *     "Result":1,
     * 	"Message": "成功",
     * 	"LocationIdList": []
     * </pre>
     */
    @RequestMapping("/OpenLock")
    @ResponseBody
    public JSONObject OpenLock(@RequestBody String List) {

        JSONObject jsonObject2 = JSONUtil.parseObj(List);
        List list1 = (java.util.List) jsonObject2.get("List");
        Map<String,String> o = (Map) list1.get(0);
        String locationId1 = o.get("LocationId");
        LocationId = locationId1;
        oneList = List;
        List returnList = new ArrayList();
        returnList.add(locationId1);
        JSONObject object = new JSONObject();
        object.put("Result", 1);
        object.put("Message", "成功");
        object.put("LocationIdList", returnList);
        return object;
    }

    /**
     * 关闭格口
     */
    @RequestMapping("/LockClosed")
    @ResponseBody
    public ResponseData LockClosed() {
        Map map1 = new HashMap();
        map1.put("LocationId", LocationId);
        //发送post请求并接收响应数据
        String result = HttpUtil.createPost(url + "/Receive/LockClosed").body(JSON.toJSONString(map1)).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        Integer code = (Integer) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        if (!Objects.equals(code, 200)) {
            return ResponseData.error(message);
        }
        LocationId = null;
        return ResponseData.success();
    }

    /**
     * 入库请求
     * <pre>
     *     {
     * 	"OrderId":"XXXXXXXX",
     * 	"HUNumber":"XXXXXXXX",
     * 	"LocationId":"XXXXXXXX"
     * }
     * </pre>
     */
    @RequestMapping(value = "/Inbound")
    @ResponseBody
    public JSONObject InboundRequest(@RequestBody String body) {
        JSONObject jsonObject2 = JSONUtil.parseObj(body);
        twoOrderId= (String) jsonObject2.get("OrderId");
        twoHUNumber= (String) jsonObject2.get("HUNumber");
        twoLocationId= (String) jsonObject2.get("LocationId");

        JSONObject object = new JSONObject();
        object.put("Result", 1);
        object.put("Message", "成功");
        return object;
    }

    /**
     * 完成入库
     */
    @RequestMapping("/InboundResult")
    @ResponseBody
    public ResponseData InboundResult() {
        Map paramMap = new HashMap<>();
        paramMap.put("OrderId", twoOrderId);
        paramMap.put("HUNumber", twoHUNumber);
        paramMap.put("LocationId", twoLocationId);
        paramMap.put("ReturnCode", "OK");
        paramMap.put("ErrorMessage", "");
        //发送post请求并接收响应数据
        String result = HttpUtil.createPost(url + "/Receive2/InboundResult").body(JSON.toJSONString(paramMap)).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        Integer code = (Integer) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        if (!Objects.equals(code, 200)) {
            return ResponseData.error(message);
        }
        twoOrderId = null;
        twoHUNumber = null;
        twoLocationId = null;
        return ResponseData.success();
    }

    /**
     * 出库请求
     * <pre>
     *     {
     * 	"OrderId":"XXXXXXXX",
     * 	"HUNumber":"XXXXXXXX",
     * 	"LocationId":"XXXXXXXX"
     * }
     * </pre>
     */
    @RequestMapping(value = "/Outbound")
    @ResponseBody
    public JSONObject Outbound(@RequestBody String body) {
        JSONObject jsonObject2 = JSONUtil.parseObj(body);
        threeOrderId= (String) jsonObject2.get("OrderId");
        threeHUNumber= (String) jsonObject2.get("HUNumber");
        threeLocationId= (String) jsonObject2.get("LocationId");

        JSONObject object = new JSONObject();
        object.put("Result", 1);
        object.put("Message", "成功");
        return object;
    }

    /**
     * 完成出库
     * <pre>
     *     {
     * 	"OrderId":"XXXXXXXX",
     * 	"HUNumber":"XXXXXXXXX",
     * 	"LocationId":"XXXXXXXX",
     * 	"ReturnCode":"OK",
     * 	"ErrorMessage":""
     * }
     * </pre>
     */
    @RequestMapping("/OutboundResult")
    @ResponseBody
    public ResponseData OutboundResult() {
        Map paramMap = new HashMap<>();
        paramMap.put("OrderId", threeOrderId);
        paramMap.put("HUNumber", threeHUNumber);
        paramMap.put("LocationId", threeLocationId);
        paramMap.put("ReturnCode", "OK");
        paramMap.put("ErrorMessage", "");
        //发送post请求并接收响应数据
        String result = HttpUtil.createPost(url + "/Receive2/OutboundResult").body(JSON.toJSONString(paramMap)).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        Integer code = (Integer) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        if (!Objects.equals(code, 200)) {
            return ResponseData.error(message);
        }
        threeOrderId = null;
        threeHUNumber = null;
        threeLocationId = null;
        return ResponseData.success();
    }

    /**
     * 开始报废品计数
     * <pre>
     *     {
     * 	"OrderId":"XXXXXXXX",
     * 	"ScrapNumber":"XXXXXXXXX"
     * }
     * </pre>
     */
    @RequestMapping(value = "/StartScrapCount")
    @ResponseBody
    public JSONObject StartScrapCount(@RequestBody String body) {
        JSONObject jsonObject2 = JSONUtil.parseObj(body);
        fourOrderId= (String) jsonObject2.get("OrderId");
        fourScrapNumber= (String) jsonObject2.get("ScrapNumber");

        JSONObject object = new JSONObject();
        object.put("Result", 1);
        object.put("Message", "成功");
        return object;
    }

    /**
     * 完成投入
     * <pre>
     *     {
     * 	"OrderId":"XXXXXXXX",
     * 	"ScrapCount":2
     * }
     * </pre>
     */
    @RequestMapping("/ScrapCountResult")
    @ResponseBody
    public ResponseData ScrapCountResult() {
        Map paramMap = new HashMap<>();
        paramMap.put("OrderId", fourOrderId);
        paramMap.put("ScrapCount", fourScrapNumber);
        //发送post请求并接收响应数据
        String result = HttpUtil.createPost(url + "/Receive2/ScrapCountResult").body(JSON.toJSONString(paramMap)).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        Integer code = (Integer) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        if (!Objects.equals(code, 200)) {
            return ResponseData.error(message);
        }
        fourOrderId = null;
        fourScrapNumber = null;
        return ResponseData.success();
    }

    /**
     * 一类柜面容登录
     * <pre>
     *     {
     * 	"StaffId":"XXXXXXXX"
     * }
     * </pre>
     */
    @RequestMapping("/StaffInfoOne")
    @ResponseBody
    public ResponseData StaffInfoOne(String userId) {
        System.out.println("一类柜面容登录");
        Map paramMap = new HashMap<>();
        paramMap.put("StaffId", userId);
        String result = HttpUtil.createPost(url + "/Receive/StaffId").body(JSON.toJSONString(paramMap)).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        Integer code = (Integer) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        if (!Objects.equals(code, 200)) {
            return ResponseData.error(message);
        }
        return ResponseData.success();
    }

    /**
     * 二类柜面容登录
     * <pre>
     *     {
     * 	"StaffId":"XXXXXXXX"
     * }
     * </pre>
     */
    @RequestMapping("/StaffInfoTwo")
    @ResponseBody
    public ResponseData StaffInfoTwo(String userId) {
        System.out.println("二类柜面容登录");
        Map paramMap = new HashMap<>();
        paramMap.put("StaffId", userId);
        String result = HttpUtil.createPost(url + "/Receive2/StaffId").body(JSON.toJSONString(paramMap)).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        Integer code = (Integer) jsonObject.get("code");
        String message = (String) jsonObject.get("message");
        if (!Objects.equals(code, 200)) {
            return ResponseData.error(message);
        }
        return ResponseData.success();
    }


    @RequestMapping(value = "/run_batch")
    @ResponseBody
    public ResponseData sendBatchRun(String userId) {

        // 查出分拣任务
        WmsSortingTaskResult wmsSortingTaskResult=this.wmsSortingTaskService.findByTaskStateOne();

        // 物料类型 绑定的是SKU
        WmsMaterialTypeParam wmsMaterialTypeParam=new WmsMaterialTypeParam();
        wmsMaterialTypeParam.setMaterialSku(wmsSortingTaskResult.getSortingMaterialType());

        // 根据物料SKU 查询出物料类型
        WmsMaterialTypeResult wmsMaterialTypeResult=this.wmsMaterialTypeService.findByMaterialSku(wmsMaterialTypeParam);

        WmsPackinfo wmsPackinfo=this.wmsPackinfoService.getById(wmsMaterialTypeResult.getPackageType());
        runBatch runBatchRe = wmsApiService.getRunBatchRe(wmsSortingTaskResult,wmsPackinfo);
        return ResponseData.success();
    }

    // 立库入库申请测试
    @PostMapping(value = "/reqSubmit")
    @ResponseBody
    public ResponseData reqSubmit(@RequestBody String result) {
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String id =  (String) jsonObject.get("Id"); // 消息识别id
        String type =  (String) jsonObject.get("Type"); // 入仓类型
        String boxType =  (String) jsonObject.get("BoxType"); // 周转箱类型
        String BoxCode =  (String) jsonObject.get("BoxCode"); // 周转箱编号
        String latticeType =  (String) jsonObject.get("LatticeType"); // 格口类型
        String sku =  (String) jsonObject.get("Sku"); // 物料sku
        String batch =  (String) jsonObject.get("Batch"); // 批次
        String qty =  (String) jsonObject.get("Qty"); // 数量
        String sortingPosition =  (String) jsonObject.get("Hits"); // 分拣位
        log.info("Message identification ID:{},Warehousing type:{},Turnover box type:{},Lattice type:{},Material SKU:{},batch:{},quantity:{},Sorting position:{}",id,type,boxType,latticeType,sku,batch,qty,sortingPosition);
        Map<String, Object> map = new HashMap<>();
        map.put("InboundId",id);
        map.put("Type",Byte.parseByte(type));
        map.put("BoxType",boxType);
        map.put("BoxCode",BoxCode);
        map.put("LatticeType",latticeType);
        map.put("Sku",sku);
        map.put("Batch",batch);
        map.put("Qty",Integer.parseInt(qty));
        map.put("Hits",sortingPosition);
        System.out.println(map);
       String str =  wmsApiService.sendOutReq(map);
       log.info("Returned results{}",str);
        return ResponseData.success();
    }

    @PostMapping(value = "/printTest")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData printTest(@RequestBody String str) {
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(str);
       String type = jsonObject.getString("type");
        ZplPrinter p = new ZplPrinter("ZDesigner ZD888-203dpi ZPL"); //本地
        p.resetZpl();//清除
        if (Objects.equals("1",type)){
            for (int i = 500; i < 501; i++) {
                String s = addZeroForNumber(i +"");
                for (int j = 0; j < 2; j++) { // 大/小: 2 中箱: 4
                    printFawTroue(p,"1"+s); // 工具条码
                    p.resetZpl();
                }
            }
      }
      if (Objects.equals("2",type)){
          for (int i = 500; i < 501; i++) {
              String s = addZeroForNumber2(i +"");
              for (int j = 0; j < 4; j++) { // 大/小: 2 中箱: 4
                  if (j<2){
                  printFawTroue(p,"21"+s); // 工具条码
                  p.resetZpl();
                  }else {
                      printFawTroue(p,"22"+s); // 工具条码
                      p.resetZpl();
                  }
              }
          }
      }
        if (Objects.equals("3",type)){
            for (int i = 500; i < 501; i++) {
                String s = addZeroForNumber(i +"");
                for (int j = 0; j < 2; j++) { // 大/小: 2 中箱: 4
                    printFawTroue(p,"3"+s); // 工具条码
                    p.resetZpl();
                }
            }
        }


        return ResponseData.success();
    }
    @PostMapping(value = "/print")
    @ResponseBody
    public ResponseData print(@RequestBody String obj) {
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(obj);
        String start = jsonObject.getString("start");
        String end = jsonObject.getString("end");
        String type = jsonObject.getString("type");
        try{
        int i1 = Integer.parseInt(start);
        int  i2 = Integer.parseInt(end);
            ZplPrinter p = new ZplPrinter("ZDesigner ZD888-203dpi ZPL"); //本地
            p.resetZpl();//清除
            if (Objects.equals("1",type)){
                for (int i = i1; i < i2; i++) {
                    String s = addZeroForNumber(i +"");
                    for (int j = 0; j < 2; j++) { // 大/小: 2 中箱: 4
                        printFawTroue(p,"1"+s); // 工具条码
                        p.resetZpl();
                    }
                }
            }
            if (Objects.equals("2",type)){
                for (int i = i1; i < i2; i++) {
                    String s = addZeroForNumber2(i +"");
                    for (int j = 0; j < 4; j++) { // 大/小: 2 中箱: 4
                        if (j<2){
                            printFawTroue(p,"21"+s); // 工具条码
                            p.resetZpl();
                        }else {
                            printFawTroue(p,"22"+s); // 工具条码
                            p.resetZpl();
                        }
                    }
                }
            }
            if (Objects.equals("3",type)){
                for (int i = i1; i < i2; i++) {
                    String s = addZeroForNumber(i +"");
                    for (int j = 0; j < 2; j++) { // 大/小: 2 中箱: 4
                        printFawTroue(p,"3"+s); // 工具条码
                        p.resetZpl();
                    }
                }
            }
//            for (int i = i1; i < i2; i++) {
//                String s = addZeroForNumber(i +"");
//                for (int j = 0; j < 2; j++) { // 大/小: 2 中箱: 4
//                    printFawTroue(p,"1"+s); // 工具条码
//                    p.resetZpl();
//                }
//            }
        }catch (Exception e){
            return ResponseData.error("请填写数字");
        }
        return ResponseData.success();
    }

    /**
     * 门禁 常闭
     */
    @RequestMapping("/KeppClose")
    @ResponseBody
    public ResponseData KeppClose() {
        // TODO: 2022/5/17 门禁控制 3
        return ResponseData.success();
    }

    /**
     * 门禁 常开
     */
    @RequestMapping("/KeppOpen")
    @ResponseBody
    public ResponseData KeppOpen() {
        // TODO: 2022/5/17 门禁控制 2
        return ResponseData.success();
    }

    /**
     * 门禁 常闭
     */
    @RequestMapping("/SendDoor")
    @ResponseBody
    public ResponseData SendDoor(String doorCodeId) {
        int code=999;
        switch (doorCodeId){
            case "0":
                code=0;
                break;
            case "1":
                code=1;
                break;
            case "2":
                code=2;
                break;
            case "3":
                code=3;
                break;
            default:
                break;
        }
        if (code!=999){
            // TODO: 2022/5/17 门禁控制 code
        }
        return ResponseData.success();
    }
}


