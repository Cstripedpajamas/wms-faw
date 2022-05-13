package cn.stylefeng.guns.modular.WebApi;

import cn.hutool.json.JSONUtil;
import cn.stylefeng.guns.modular.WebApi.Entity.*;
import cn.stylefeng.guns.modular.base.packageInfo.entity.WmsPackinfo;
import cn.stylefeng.guns.modular.warehousemanage.entity.WmsSortingTask;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 二类柜接口地址
    private static final  String startScrapUrl ="http://192.168.26.31:9092/Plc";

    // 机械手 发送波次分拣任务
//    private static final String runBatchUrl = "http://192.168.8.102:7002/api/cmd/run_batch";
    private static final String runBatchUrl = "http://127.0.0.1:8099/faw/WmsApi/Receive/test";

    // 立库 基本url地址
    private static final  String requestWarehouse = "http://192.168.26.96:8092/PHS";
    // 立库 入库申请
    private static final  String inWarehouseReq = requestWarehouse+"/Inbound";
    // 立库 出库申请
    private static final  String outWarehouseReq = requestWarehouse+"/Outbound";


    // 打开格口url
    private static final String openDeclension = openUrl+"/OpenLock";

    // 备品备件-出库 startScrapUrl
    private static final String outboundDeclension = startScrapUrl+"/Outbound";

    // 备品备件-入库
    private static final String inboundDeclension = startScrapUrl+"/Inbound";



    // 备品备件-开始入库
    private static final String startInboundDeclension = basicUrl+"/req";

    // 备品备件-开始报废品计数
    private static final String startScrapCount = startScrapUrl+"/StartScrapCount";

    //bpm 发起流程
    private static final String bpmSend = "http://10.6.201.184:8011/BPM/TWMS/TWMS2BPM_InitiatingProcess/ProxyServices/InitiatingProcessPS";

    //bpm 流程查询
    private static final String bpmQuery = "http://10.6.201.184:8011/BPM/TWMS/TWMS2BPM_SelectProcessStatus/Proxyservices/SelectProcessStatusPS";





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
        Map<String, Object> map = new HashMap<>();
        map.put("batch_id",""+wmsSortingTaskResult.getId()); // 唯一识别码
        map.put("task_nums",Integer.parseInt(wmsSortingTaskResult.getSortingNum())); // 任务数量
        if (wmsSortingTaskResult.getTurnoverType().equals("A")){
            map.put("pick_tote",1); // 料箱类型(0.大 1.小)
        }else {
            map.put("pick_tote",0); // 料箱类型(0.大 1.小)
        }
        map.put("dimension",splitByS(wmsPackinfo.getPackgeSpecif()));
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
    public boolean sendBpm(BpmSendHeaderEntity bpmSendHeaderEntity,BpmSendBodyEntity bpmSendBodyEntity) {
        Map<String,Object> param = new HashMap<>();
        param.put("msgHeader",bpmSendHeaderEntity);
        param.put("msgBody",bpmSendBodyEntity);
        ResponseEntity<String> exchange  = restTemplate().postForEntity(bpmSend, toJSON(param), String.class);
        String resultRemote = exchange.getBody();
        Map map = JSONObject.parseObject(resultRemote,Map.class);
        Map map1 = JSONObject.parseObject(String.valueOf(map.get("msgHeader")), Map.class);
        if (map1.get("resultType")!=null){
            if (map1.get("resultType").equals("0")){
                return true;
            }
        }
        return false;
    }

    // bpm 查询
    public List<Map<String, String>> queryBpm(BpmSendHeaderEntity bpmSendHeaderEntity,BpmSendBody2Entity bpmSendBody2Entity) {
        Map<String,Object> param = new HashMap<>();
        param.put("msgHeader",bpmSendHeaderEntity);
        param.put("msgBody",bpmSendBody2Entity);
        ResponseEntity<String> exchange  = restTemplate().postForEntity(bpmQuery, toJSON(param), String.class);
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
