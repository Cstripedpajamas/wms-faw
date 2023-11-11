package cn.stylefeng.guns.modular.WebApi;

import cn.stylefeng.guns.modular.WebApi.Entity.*;
import cn.stylefeng.guns.modular.base.packageInfo.entity.WmsPackinfo;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import com.alibaba.fastjson.JSONObject;
//import jdk.internal.org.jline.utils.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

import static cn.stylefeng.guns.modular.utils.HttpUtil.restTemplate;

/**
 * @Author: ll
 * @Date: 2021/11/3 11:20
 * @Version 1.0
 */
@Component
public class WmsApiService {

    // 基础url
    private static final  String basicUrl ="http://127.0.0.1:8099/faw/redirect";

    // 一类柜格口接口地址
    private static final  String openUrl ="http://192.168.26.30:9091/Lock";
    private static final  String openUrl2 ="http://192.168.26.30:9091/ColumnLight";

    // 二类柜接口地址
    private static final  String startScrapUrl ="http://192.168.26.31:9092/Plc";

    // 机械手 发送波次分拣任务
    private static final String runBatchUrl = "http://192.168.26.50:7002/api/cmd/run_batch";
//    private static final String runBatchUrl = "http://127.0.0.1:8099/faw/WmsApi/Receive/test";

    // 立库 基本url地址
    private static final  String requestWarehouse = "http://192.168.26.96:8092/PHS";
    // 立库 入库申请
    private static final  String inWarehouseReq = requestWarehouse+"/Inbound";
    // 立库 出库申请
    private static final  String outWarehouseReq = requestWarehouse+"/Outbound";


    // 打开格口url 真实: openUrl 测试: basicUrl
    private static final String openDeclension = openUrl+"/OpenLock";

    //发送报警信息
    private static final String sendAlarmMessage = openUrl2+"/OpenOrCloseLight";

    // 备品备件-出库 startScrapUrl
    private static final String outboundDeclension = startScrapUrl+"/Outbound";

    // 备品备件-入库
    private static final String inboundDeclension = startScrapUrl+"/Inbound";



    // 备品备件-开始入库
    private static final String startInboundDeclension = basicUrl+"/req";

    // 备品备件-开始报废品计数
    private static final String startScrapCount = startScrapUrl+"/StartScrapCount";


//            正式环境IP:10.7.62.76
//            测试环境IP:10.6.201.184
    //bpm 发起流程
    private static final String bpmSend = "http://10.7.62.76:8011/BPM/TWMS/TWMS2BPM_InitiatingProcess/ProxyServices/InitiatingProcessPS";

    //bpm 流程查询
    private static final String bpmQuery = "http://10.7.62.76:8011/BPM/TWMS/TWMS2BPM_SelectProcessStatus/Proxyservices/SelectProcessStatusPS";





    // 设置请求头部信息
    private static HttpHeaders HttpClientHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    // 参数转换
    private static HttpEntity toJSON(Object obj){
        HttpEntity<Object> HttpEntitys = new HttpEntity<Object>(obj,HttpClientHeader());
        return HttpEntitys;
    }

    /**
     * 工具类接口
     * */
    // 打开格口
    public static Declension openDeclension(Object obj){
        ResponseEntity<String> exchange  = restTemplate().postForEntity(openDeclension, toJSON(obj), String.class);
        String resultRemote = exchange.getBody();
        Declension declension = JSONObject.parseObject(resultRemote, Declension.class);
        return declension;
    }

    /**
     *  工具类柜接口
     *  发送报警信息
     * */
    public static Declension sendAlarmMessage(Object obj){
        ResponseEntity<String> exchange  = restTemplate().postForEntity(sendAlarmMessage, toJSON(obj), String.class);
        String resultRemote = exchange.getBody();
        Declension declension = JSONObject.parseObject(resultRemote, Declension.class);
        return declension;
    }

    /**
     * 备品备件类接口
     * */

    // 出库
    public  Declension Outbound(Object obj){
        ResponseEntity<String> exchange  = restTemplate().postForEntity(outboundDeclension, toJSON(obj), String.class);
        String resultRemote = exchange.getBody();
        Declension declension = JSONObject.parseObject(resultRemote, Declension.class);
        return declension;
    }

    // 开始入库
    public static Declension StartInbound(Object obj){
        ResponseEntity<String> exchange  = restTemplate().postForEntity(startInboundDeclension, toJSON(obj), String.class);
        String resultRemote = exchange.getBody();
        Declension declension = JSONObject.parseObject(resultRemote, Declension.class);
        return declension;
    }

    // 入库
    public  Declension Inbound(Object obj){
        ResponseEntity<String> exchange  = restTemplate().postForEntity(inboundDeclension, toJSON(obj), String.class);
        String resultRemote = exchange.getBody();
        Declension declension = JSONObject.parseObject(resultRemote, Declension.class);
        return declension;
    }

    // 开始报废品计数
    public  Declension StartScrapCount(Object obj){
        ResponseEntity<String> exchange  = restTemplate().postForEntity(startScrapCount, toJSON(obj), String.class);
        String resultRemote = exchange.getBody();
        Declension declension = JSONObject.parseObject(resultRemote, Declension.class);
        return declension;
    }

    // 波次分拣任务请求
    public runBatch getRunBatchRe(WmsSortingTaskResult wmsSortingTaskResult, WmsPackinfo wmsPackinfo){
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        Map<String, Object> map = new HashMap<>();
        map.put("batch_id",""+wmsSortingTaskResult.getId()); // 唯一识别码
        map.put("task_nums",Integer.parseInt(wmsSortingTaskResult.getSortingNum())); // 任务数量
        if (wmsSortingTaskResult.getTurnoverType().equals("A")){
            map.put("pick_tote",1); // 料箱类型(0.大 1.小)
        }else {
            map.put("pick_tote",0); // 料箱类型(0.大 1.小)
        }
        double[] doubles = splitByS(wmsPackinfo.getPackgeSpecif());
        System.out.println(Arrays.toString(doubles));
        map.put("dimension",doubles);
        System.out.println(map);
        ResponseEntity<String> exchange = restTemplate().postForEntity(runBatchUrl, toJSON(map), String.class);
        String resultRemote = exchange.getBody();
        runBatch runBatch = JSONObject.parseObject(resultRemote, runBatch.class);
        System.out.println("-------------- 接口返回的数据 ---------------" + runBatch);
        return runBatch;
    }

    // 立库 出库申请
    public String sendOutReq(Object obj) {
        ResponseEntity<String> exchange  = restTemplate().postForEntity(outWarehouseReq, toJSON(obj), String.class);
        String resultRemote = exchange.getBody();
        String string = JSONObject.parseObject(resultRemote, String.class);
        return string;
    }

    // 立库 入库申请
    public String sendInReq(Object obj) {
        ResponseEntity<String> exchange  = restTemplate().postForEntity(inWarehouseReq, toJSON(obj), String.class);
        String resultRemote = exchange.getBody();
        String string = JSONObject.parseObject(resultRemote, String.class);
        return string;
    }

    public double[] splitByS(String gs){
        double[] ds = new double[3];
        String[] split = gs.split("\\*");
        for (int i = 0; i < 3; i++) {
            ds[i] = Double.parseDouble(split[i])/100;
        }
        return ds;

    }

    // bpm 发起
    public String sendBpm(BpmSendHeaderEntity bpmSendHeaderEntity,BpmSendBodyEntity bpmSendBodyEntity) {
        Map<String,Object> param = new HashMap<>();
        param.put("msgHeader",bpmSendHeaderEntity);
        param.put("msgBody",bpmSendBodyEntity);
        ResponseEntity<String> exchange  = restTemplate().postForEntity(bpmSend, toJSON(param), String.class);
        String resultRemote = exchange.getBody();
        Map map = JSONObject.parseObject(resultRemote,Map.class);
        Map map1 = JSONObject.parseObject(String.valueOf(map.get("msgHeader")), Map.class);
        if (exchange.getStatusCode().is2xxSuccessful()){
            String body=exchange.getBody();
            return body;
        }
        return "false";
    }

    // bpm 查询
    public List<Map<String, String>> queryBpm(BpmSendHeaderEntity bpmSendHeaderEntity,BpmSendBody2Entity bpmSendBody2Entity) {
        Map<String,Object> param = new HashMap<>();
        param.put("msgHeader",bpmSendHeaderEntity);
        param.put("msgBody",bpmSendBody2Entity);
        System.out.println("queryBpmS-------------------------->queryBpmS");
        ResponseEntity<String> exchange  = restTemplate().postForEntity(bpmQuery, toJSON(param), String.class);
        System.out.println("queryBpmE-------------------------->queryBpmE");
        String resultRemote = exchange.getBody();
        Map map = JSONObject.parseObject(resultRemote,Map.class);
        Map map1 = JSONObject.parseObject(String.valueOf(map.get("msgHeader")), Map.class);
        if (map1.get("resultType")!=null){
            if (map1.get("resultType").equals("0")){
                Map map2 = JSONObject.parseObject(String.valueOf(map.get("msgBody")), Map.class);
                Integer count=JSONObject.parseObject(String.valueOf(map2.get("count")), Integer.class);
                List list = JSONObject.parseObject(String.valueOf(map2.get("result")), List.class);
                List<Map<String, String>> mapList=new ArrayList<>();
                for (Object o : list) {
                    Map listMap = JSONObject.parseObject(String.valueOf(o), Map.class);
                    mapList.add(listMap);
                }
                return mapList;
            }
        }
        return null;
    }
}
