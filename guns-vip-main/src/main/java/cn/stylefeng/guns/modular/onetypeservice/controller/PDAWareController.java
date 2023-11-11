package cn.stylefeng.guns.modular.onetypeservice.controller;

import cn.hutool.http.webservice.SoapClient;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.config.AppConfig;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.materialspareparts.model.params.WmsMaterialSparePartsParam;
import cn.stylefeng.guns.modular.base.materialspareparts.model.result.WmsMaterialSparePartsResult;
import cn.stylefeng.guns.modular.base.materialspareparts.service.WmsMaterialSparePartsService;
import cn.stylefeng.guns.modular.base.materialtool.entity.WmsMaterialTool;
import cn.stylefeng.guns.modular.base.materialtool.service.WmsMaterialToolService;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.result.WmsWarehousePurchaseorderDeliveryResult;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.service.WmsWarehousePurchaseorderDeliveryService;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.entity.WmsPurchaseOrderInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result.WmsPurchaseOrderInfoResult;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.service.WmsPurchaseOrderInfoService;
import cn.stylefeng.guns.modular.base.user.entity.WmsUser;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.onetypeservice.enums.ApplyType;
import cn.stylefeng.guns.modular.onetypeservice.enums.StateEnum;
import cn.stylefeng.guns.modular.onetypeservice.request.ApplySpareParts;
import cn.stylefeng.guns.modular.onetypeservice.request.WarehouseTurnoverModify;
import cn.stylefeng.guns.modular.onetypeservice.service.OneTypeCabinetService;
import cn.stylefeng.guns.modular.onetypeservice.service.WarehouseService;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.entity.WmsUseApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.WmsUseApplyService;
import cn.stylefeng.guns.modular.statistics.tooluse.entity.WmsToolUse;
import cn.stylefeng.guns.modular.statistics.tooluse.service.WmsToolUseService;
import cn.stylefeng.guns.modular.warehousemanage.entity.*;
import cn.stylefeng.guns.modular.warehousemanage.model.params.*;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsSortingTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseReplenishmentTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.model.result.WmsWarehouseToolUseTaskResult;
import cn.stylefeng.guns.modular.warehousemanage.service.*;
import cn.stylefeng.guns.webservice.client.ExecuteBindQSService;
import cn.stylefeng.guns.webservice.client.Req;
import cn.stylefeng.guns.webservice.client.Resp;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization", maxAge = 36000)
@RequestMapping("/pda/warehouse")
@Validated
public class PDAWareController {

    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    private OneTypeCabinetService oneTypeCabinetService;
    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WmsWarehousePurchaseorderDeliveryService wmsWarehousePurchaseorderDeliveryService;

    @Autowired
    private WmsUserService wmsUserService;
    @Autowired
    private WmsWarehouseReplenishmentTaskService wmsWarehouseReplenishmentTaskService;
    @Autowired
    private WmsSortingTaskService wmsSortingTaskService;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;
    @Autowired
    private WmsWarehouseTaskOutService wmsWarehouseTaskOutService;
    @Autowired
    private WmsMaterialSparePartsService wmsMaterialSparePartsService;

    @Autowired
    private WmsWarehousePurchaseStorageTaskService wmsWarehousePurchaseStorageTaskService;

    @Autowired
    private WmsPurchaseOrderInfoService wmsPurchaseOrderInfoService;

    @Autowired
    private WmsWarehouseToolUseTaskService wmsWarehouseToolUseTaskService;

    @Autowired
    private WmsUseApplyService wmsUseApplyService;

    @Autowired
    private WmsToolUseService wmsToolUseService;

    @Autowired
    private WmsWarehouseTurnoverService wmsWarehouseTurnoverService;

    @Autowired
    private WmsWarehouseTurnoverBindService wmsWarehouseTurnoverBindService;

    @Autowired
    private WmsMaterialToolService wmsMaterialToolService;

    /**
     * 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseData login(String serialNumber, String password){
        WmsUser wmsUser = wmsUserService.getOne(new QueryWrapper<WmsUser>().eq("serial_number",serialNumber));
        if(wmsUser == null){
            return ResponseData.error("账号不存在");
        }
        if(!Objects.equals(wmsUser.getuPwd(),password)){
            return ResponseData.error("账号密码错误");
        }
        return ResponseData.success(wmsUser.getId() + "," + wmsUser.getUserName() + "," + wmsUser.getSerialNumber());
    }

    /**
     * 采购入库任务
     */
    @RequestMapping("/doingTask")
    @ResponseBody
    public ResponseData doingTask() {
        WmsPurchaseOrderInfoResult task = warehouseService.doingTask();
        if (task == null){
            return ResponseData.error(500, "暂无执行中的采购任务", new WmsPurchaseOrderInfoResult());
        }
        return ResponseData.success(task);
    }

    /**
     * 空周转箱出库
     * 任务编号:taskNumber
     */
    @RequestMapping("/out")
    @ResponseBody
    public ResponseData purchaseOutTask(String taskNumber) {
        return warehouseService.purchaseOutTask(taskNumber);
    }

    /**
     * 周转箱信息
     * 周转箱条码:turnoverNumber
     */
    @RequestMapping("/warehouse-turnover")
    @ResponseBody
    public ResponseData padWarehouseTurnover(String turnoverNumber) {
        return oneTypeCabinetService.warehouseTurnover(turnoverNumber);
    }

    /**
     * 周转箱信息
     * 周转箱条码:turnoverNumber
     */
    @RequestMapping("/warehouse-turnover-pur")
    @ResponseBody
    public ResponseData padWarehouseTurnover(String turnoverNumber, String taskNumber) {
        return oneTypeCabinetService.warehouseTurnover(turnoverNumber, taskNumber);
    }

    /**
     * 工具信息
     * 工具条码:materialSerialNumber
     */
    @RequestMapping("/dish-data")
    @ResponseBody
    public ResponseData padWarehouseDishToolData(String materialSerialNumber) {
        WmsMaterialTool wmsMaterialTool = oneTypeCabinetService.findToolByMaterialSerialNumber(materialSerialNumber);
        if (wmsMaterialTool == null) {
            return ResponseData.error(500, "无此工具信息", new WmsMaterialTool());
        }
        return ResponseData.success(wmsMaterialTool);
    }

    /**
     * 备品备件信息
     * 工具条码:materialSerialNumber
     */
    @RequestMapping("/dish-spareparts-data")
    @ResponseBody
    public ResponseData padWarehouseMaterialSparePartsData(String materialTypeId) {
        WmsMaterialSparePartsParam param=new WmsMaterialSparePartsParam();
        param.setMaterialTypeId(materialTypeId);
        List<WmsMaterialSparePartsResult> wmsMaterialSparePartsResultList = this.wmsMaterialSparePartsService.findAllByMaterialTypeId(param);
        if (wmsMaterialSparePartsResultList == null||wmsMaterialSparePartsResultList.isEmpty()) {
            return ResponseData.error(500, "无此类型备品备件信息", new ArrayList<>());
        }
        return ResponseData.success(wmsMaterialSparePartsResultList);
    }

    /**
     * 周转箱信息绑定
     * WarehouseTurnoverModify
     */
    @RequestMapping("/dish-bing")
    @ResponseBody
    public ResponseData padWarehouseDishConform(WarehouseTurnoverModify warehouseTurnoverModify) {
        return oneTypeCabinetService.padWarehouseDishConform(warehouseTurnoverModify);
    }

    /**
     * 提交入库
     * serialNumber 用户编号
     * turnoverNumber 周转箱条码
     * taskNumber 采购任务编号
     */
    @RequestMapping("/scan-out")
    @ResponseBody
    public ResponseData purchaseScanOutTask(String serialNumber, String turnoverNumber, String taskNumber) {
        return warehouseService.purchaseScanInTask(serialNumber, turnoverNumber, taskNumber);
    }
    private final ReadWriteLock lock=new ReentrantReadWriteLock();
    /**
     * 采购入库任务 完成
     */
    @RequestMapping("/doingTaskOver")
    @ResponseBody
    public ResponseData doingTaskOver(String taskNumber) {
        try{
            lock.writeLock().lock();
            System.out.println("ppppppppppppppppppppppppppppppppppppppdddddddddddddddddddddddddddddd");
            System.out.println(taskNumber);
            WmsWarehousePurchaseStorageTask wmsWarehousePurchaseStorageTask = wmsWarehousePurchaseStorageTaskService.getOne(new QueryWrapper<WmsWarehousePurchaseStorageTask>().eq("task_number", taskNumber));
            WmsPurchaseOrderInfo wmsPurchaseOrderInfo = wmsPurchaseOrderInfoService.getById(wmsWarehousePurchaseStorageTask.getPurchaseId());

            WmsWarehousePurchaseStorageTaskParam wmsWarehousePurchaseStorageTaskParam=new WmsWarehousePurchaseStorageTaskParam();
            System.out.println("----------------------------------------------------------------------");
            System.out.println(wmsPurchaseOrderInfo.getArrivalState());
            if (wmsPurchaseOrderInfo.getArrivalState().equals("3")){
                System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeehhhhhhhhhhhhhhhhhhhhh");
                lock.writeLock().unlock();
                return  ResponseData.error("重复触发");
            }
            String uid=String.valueOf(UUID.randomUUID());
            wmsWarehousePurchaseStorageTaskParam.setId(wmsWarehousePurchaseStorageTask.getId());
            wmsWarehousePurchaseStorageTaskParam.setTaskState("3");
            wmsWarehousePurchaseStorageTaskService.update(wmsWarehousePurchaseStorageTaskParam);

            System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
            WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam=new WmsPurchaseOrderInfoParam();
            wmsPurchaseOrderInfoParam.setId(wmsPurchaseOrderInfo.getId());
            wmsPurchaseOrderInfoParam.setArrivalState("3");
            wmsPurchaseOrderInfoParam.setUid(uid);
            wmsPurchaseOrderInfoService.update(wmsPurchaseOrderInfoParam);
            WmsWarehousePurchaseorderDeliveryResult wmsWarehouse= wmsWarehousePurchaseorderDeliveryService.selectPurDocNo(wmsPurchaseOrderInfo.getPurDocNo(),wmsPurchaseOrderInfo.getItemNo(),wmsPurchaseOrderInfo.getPurchasereqno());
            System.out.println(wmsPurchaseOrderInfo.getClient());
            System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");

            lock.writeLock().unlock();

            String storageLocation="R06B901";
            if (wmsPurchaseOrderInfo.getMaterialType().equals("GJ")){
                storageLocation="R06GZ11";
            }
            System.out.println(storageLocation);
            if (wmsPurchaseOrderInfo.getClient().equals("900"))
            {
                System.out.println("huhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuuhuhu");
                if (wmsWarehouse!=null){
                    System.out.println("CJCJCJCJCJCCJCJCJJCJCJCJCJJCJCJCJJCJCJCJJCJCJCJCJ");
                    Req req = new Req();
                    Req.MsgHeader msgHeader= new Req.MsgHeader();
                    Req.MsgBody msgBody= new Req.MsgBody();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    Date date = new Date();
                    String dateString = sdf.format(date);
    //                System.out.println("--------------------dateString  :---------------------------- " + dateString);
                    ExecuteBindQSService executeBindQSService = new ExecuteBindQSService();
                    msgHeader.setMessageID(uid);
                    msgHeader.setInterfaceID("TWMS-ERP-001");
                    msgHeader.setSender("JF_TWMS");
                    msgHeader.setReceiver("JF_ERP");
                    msgBody.setCode(wmsWarehouse.getCode());
                    msgBody.setLineCode(wmsWarehouse.getLinecode());
                    msgBody.setQty(wmsWarehouse.getQty());
                    msgBody.setMtlno(wmsWarehouse.getMtlno());
//                    msgBody.setStorageLocation("R06B861");//TODO
                    msgBody.setStorageLocation(storageLocation);
//                    msgBody.setGetDate(sdf.format(wmsWarehouse.getCreateTime()));
                    msgBody.setGetDate(dateString);
                    req.setMsgHeader(msgHeader);
                    req.getMsgBody().add(msgBody);


                    logger.info(msgBody.getCode());
                    logger.info(msgBody.getLineCode());
                    logger.info(msgBody.getQty());
                    logger.info(msgBody.getMtlno());
                    logger.info(msgBody.getStorageLocation());
                    logger.info(msgBody.getGetDate());
                    System.out.println("asasasasasasasasasasasasasasasa");

                    Resp resp = executeBindQSService.getExecuteBindQSPort().execute(req);

                    System.out.println("SCSCSCSCSCSCSCSCSCSCSCSCSCSCSCSCSCSCSCSCSCSCS");
                    System.out.println(msgHeader.getMessageID());
                    System.out.println(msgHeader.getInterfaceID());
                    System.out.println(msgHeader.getSender());
                    System.out.println(msgHeader.getReceiver());
                    System.out.println(msgBody.getCode());
                    System.out.println(msgBody.getLineCode());
                    System.out.println(msgBody.getQty());
                    System.out.println(msgBody.getMtlno());
                    System.out.println(msgBody.getStorageLocation());
                    System.out.println(msgBody.getGetDate());
                    logger.info(resp.getMsgHeader().getResultMessage());
                }
            }else{
                if (wmsPurchaseOrderInfo!=null){
//                    Req req = new Req();
//                    Req.MsgHeader msgHeader= new Req.MsgHeader();
//                    Req.MsgBody msgBody= new Req.MsgBody();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//                    Date date = new Date();
//                    String dateString = sdf.format(date);
//    //                System.out.println("--------------------dateString  :---------------------------- " + dateString);
//                    ExecuteBindQSService executeBindQSService = new ExecuteBindQSService();
//                    msgHeader.setMessageID(String.valueOf(UUID.randomUUID()));
//                    msgHeader.setInterfaceID("TWMS-ERP-001");
//                    msgHeader.setSender("JF_TWMS");
//                    msgHeader.setReceiver("JF_ERP");
////                    msgBody.setCode(wmsPurchaseOrderInfo.getPurDocHeaderId());
////                    msgBody.setLineCode(wmsPurchaseOrderInfo.getPurNumber());
//                    msgBody.setQty(wmsPurchaseOrderInfo.getReceivedQuantity());
//                    msgBody.setMtlno(wmsPurchaseOrderInfo.getMaterialSku());
////                    msgBody.setStorageLocation("R06B861");//TODO
//                    msgBody.setStorageLocation(storageLocation);
//                    msgBody.setGetDate(sdf.format(wmsPurchaseOrderInfo.getCreateTime()));
//
//                    req.setMsgHeader(msgHeader);
//                    req.getMsgBody().add(msgBody);
//                    Resp resp = executeBindQSService.getExecuteBindQSPort().execute(req);
//                    System.out.println("------------------------------------------------------------");
//                    System.out.println(resp.getMsgHeader().getResultMessage());
//                    System.out.println("ERPERPERPERPEREPERPEPRPEPREPRPEPRPEPRPERPEPREPRE");
//                    System.out.println(msgHeader.getMessageID());
//                    System.out.println(msgHeader.getInterfaceID());
//                    System.out.println(msgHeader.getSender());
//                    System.out.println(msgHeader.getReceiver());
//                    System.out.println(msgBody.getCode());
//                    System.out.println(msgBody.getLineCode());
//                    System.out.println(msgBody.getQty());
//                    System.out.println(msgBody.getMtlno());
//                    System.out.println(msgBody.getStorageLocation());
//                    System.out.println(msgBody.getGetDate());
//                    logger.info(resp.getMsgHeader().getResultMessage());


                    //            正式环境IP:10.7.62.76
                    //            测试环境IP:10.6.201.184
                    // 新建客户端 正式环境
                    SoapClient client = SoapClient.create("http://10.7.62.76:8011/ERP/UWMS/UWMS2ERP_SyncInvNoGuardianStorageLocation/ProxyServices/SyncInvNoGuardianStorageLocationPS?wsdl")
                            // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间
                            .setMethod("ser:rePurInStorageMT", "http://service.noGuardianStor.inv.mm.erp.faw_qm.com/");
                    // 设置参数，此处自动添加方法的前缀：web
                    try {
                        SOAPElement msgHeader = client.getMethodEle().addChildElement("msgHeader");
                        msgHeader.addChildElement("comments").setValue("");
                        msgHeader.addChildElement("count").setValue("");
                        msgHeader.addChildElement("interfaceID").setValue("TWMS-ERP-002");
                        msgHeader.addChildElement("messageID").setValue(uid);
                        msgHeader.addChildElement("receiver").setValue("JF_ERP");
                        msgHeader.addChildElement("sender").setValue("JF_TWMS");
                        msgHeader.addChildElement("transID").setValue("");
                        SOAPElement msgBody = client.getMethodEle().addChildElement("msgBody");
                        SOAPElement invRePurInStorageMTItems=msgBody.addChildElement("invRePurInStorageMTItems");

                        //            凭证日期
                        Date date=new Date();
                        SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
                        String dateString=formatter.format(date);
//                        dateString="20220404";

                        String docDate=formatter.format(wmsPurchaseOrderInfo.getDocDate());

//                        凭证日期
                        invRePurInStorageMTItems.addChildElement("docDate").setValue(docDate);
//                        收货者
                        invRePurInStorageMTItems.addChildElement("goodsRecipient").setValue("TWMS");
//                        移动类型
                        invRePurInStorageMTItems.addChildElement("moveMentType").setValue("101");
//                        物料SKU
                        invRePurInStorageMTItems.addChildElement("mtlNO").setValue(wmsPurchaseOrderInfo.getMaterialSku());
//                        记账日期
                        invRePurInStorageMTItems.addChildElement("postDate").setValue(dateString);
//                        采购订单行项号
                        invRePurInStorageMTItems.addChildElement("purDocItem").setValue(wmsPurchaseOrderInfo.getPurdocitemno());
//                        采购订单号
                        invRePurInStorageMTItems.addChildElement("purDocNO").setValue(wmsPurchaseOrderInfo.getPurDocNo());
//                        数量
                        invRePurInStorageMTItems.addChildElement("quantity").setValue(wmsPurchaseOrderInfo.getmNumber());
//                        外部单号
                        invRePurInStorageMTItems.addChildElement("refDocNO").setValue(String.valueOf(UUID.randomUUID()));
//                        外部行号
                        invRePurInStorageMTItems.addChildElement("refItemNO").setValue(wmsPurchaseOrderInfo.getPurdocitemno());
//                        存储地点
                        invRePurInStorageMTItems.addChildElement("storageLocation").setValue(wmsPurchaseOrderInfo.getStorelocation());
                    } catch (SOAPException e) {
                        System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFF");
                        logger.info(e.getMessage());
                        return ResponseData.error("推送失败");
                    }
                    String str= client.send(true);
                    logger.info("/tool_apply_commit_over upload ERP interface"+str);
                    System.out.println("/tool_apply_commit_over upload ERP interface"+str);
                    System.out.println("11111111111111111111----InErpInErpInErp----1111111111111111111111");

                }
            }
            return ResponseData.success();
        }
        catch(Exception exception){
            System.out.println("llllllllllllllllllllllllllllllllllllllllllllll");
            lock.writeLock().unlock();
            return ResponseData.error("重复触发");
        }

//        return ResponseData.success();
    }

    /**
     * 所有的工具类型信息
     */
    @RequestMapping("/apply-tool-all")
    @ResponseBody
    public ResponseData padApplyToolAll() {
//        List<WmsMaterialType> tools = this.wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>().eq("type",StateEnum.ONE.getState()).eq("data_state",StateEnum.ZERO.getState()));
        List<WmsMaterialType> tools = wmsMaterialTypeService.findAllMaterialType();
        if(tools.isEmpty()){
            return ResponseData.error(500, "无工具类型", new ArrayList());
        }
        return ResponseData.success(tools);
    }

    /**
     * 工具申请
     * serialNumber  人员编号
     * reason 申请原因
     * materialTypeId 物料类型id
     */
    @RequestMapping("/apply-tool-conform")
    @ResponseBody
    public ResponseData padApplyToolConform(String serialNumber, String reason, String materialTypeId) {
        oneTypeCabinetService.padToolConform(serialNumber, reason, materialTypeId);
        return ResponseData.success();
    }

    /**
     * 所有的备品备件
     */
    @RequestMapping("/apply-spare-all")
    @ResponseBody
    public ResponseData padApplySpareAll() {
        List<WmsMaterialType> types = wmsMaterialTypeService.list(new QueryWrapper<WmsMaterialType>().eq("type",StateEnum.TWO.getState()).eq("data_state",StateEnum.ZERO.getState()));
        if(types.isEmpty()){
            return ResponseData.error(500, "无备品备件类型", new ArrayList());
        }
        return ResponseData.success(types);
    }

    /**
     * 备品备件申请
     */
    @RequestMapping("/apply-spare-conform")
    @ResponseBody
    public ResponseData padApplySpareConform(ApplySpareParts apply) {
        return oneTypeCabinetService.padApplySpareConform(apply);
    }

    /**
     * 归还申请
     */
    @RequestMapping("/apply-back")
    @ResponseBody
    public ResponseData padToolGiveBack(String serialNumber, String reason, String materialTypeId) {
        oneTypeCabinetService.padToolGiveBack(serialNumber, reason, materialTypeId);
        return ResponseData.success();
    }

    /**
     * 工具领用列表
     * serialNumber 人员编号
     * */
    @RequestMapping("/tool_apply_list")
    @ResponseBody
    public ResponseData toolApplyList(String serialNumber) {
        LayuiPageInfo layuiPageInfo = warehouseService.claimListA(serialNumber);
        final List<WmsWarehouseToolUseTask> data = layuiPageInfo.getData();
        if (layuiPageInfo.getData().size() == 0){
            return ResponseData.error(500, "无工具领用任务", new ArrayList());
        }
        return ResponseData.success(data);
    }

    /**
     * 工具领用列表
     * serialNumber 人员编号
     * */
    @RequestMapping("/tool_apply")
    @ResponseBody
    public ResponseData toolApply(String taskCode) {
        final List<WmsWarehouseToolUseTask> data = warehouseService.claim(taskCode);
        if (data.size() == 0){
            return ResponseData.error(500, "无工具领用任务", new ArrayList());
        }
        return ResponseData.success(data);
    }

    /**
     * 工具领用
     * serialNumber 用户编号
     * taskNumber 任务编号
     * */
    @RequestMapping("/tool_apply_config")
    @ResponseBody
    public ResponseData toolApplyConfig(String serialNumber, String taskNumber) {
        return warehouseService.claimConform(serialNumber,taskNumber);
    }

    /**
     * 工具领用提交
     * WarehouseTurnoverModify
     * */
    @RequestMapping("/tool_apply_commit")
    @ResponseBody
    public ResponseData toolApplyCommit(WarehouseTurnoverModify modify) {
        if (modify.getMaterialSerialNumber() == null){
            return ResponseData.error("请先绑定工具信息");
        }
        WmsWarehouseTaskIn wmsWarehouseTaskIn = oneTypeCabinetService.padSortingConform(modify);
        warehouseService.sendTask(wmsWarehouseTaskIn.getMessageId());
        //更新任务完成
        WmsWarehouseToolUseTask wmsWarehouseToolUseTask = wmsWarehouseToolUseTaskService.getOne(new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number", modify.getTaskNumber()));
        wmsWarehouseToolUseTask.setTaskState(StateEnum.THREE.getState());
        wmsWarehouseToolUseTask.setSortingNum(modify.getNumber());
        wmsWarehouseToolUseTaskService.updateById(wmsWarehouseToolUseTask);
        System.out.println("00000000000000000000000："+modify.getNumber()+"11111111111111111111111111111："+modify.getTaskNumber());
//        WmsWarehouseToolUseTask toolUseTask = new WmsWarehouseToolUseTask();
//        toolUseTask.setTaskState(StateEnum.THREE.getState());
//        wmsWarehouseToolUseTaskService.update(toolUseTask,new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number",modify.getTaskNumber()));
        return ResponseData.success();
    }

    /**
     * 工具领用 完成提交 自动分拣
     * WarehouseTurnoverModify
     * */
    @RequestMapping("/tool_apply_commit_over")
    @ResponseBody
    public ResponseData toolApplyCommitRib(String materialSerialNumber,String taskNumber) {

        System.out.println("33333333333333333333333："+materialSerialNumber+"44444444444444444444444："+taskNumber);
        //更新任务完成
        WmsWarehouseToolUseTask toolUseTask = new WmsWarehouseToolUseTask();
        toolUseTask.setTaskState(StateEnum.THREE.getState());
        wmsWarehouseToolUseTaskService.update(toolUseTask,new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number",taskNumber));
        //添加领用工具信息
        WmsWarehouseToolUseTask wmsWarehouseToolUseTask = wmsWarehouseToolUseTaskService.getOne(new QueryWrapper<WmsWarehouseToolUseTask>().eq("task_number", taskNumber));
        WmsToolUse wmsToolUse = new WmsToolUse();
        wmsToolUse.setOperator(wmsWarehouseToolUseTask.getOperator());// 人员信息
        wmsToolUse.setMaterialTypeId(wmsWarehouseToolUseTask.getMaterialTypeId());// 物料类型ID
        wmsToolUse.setMaterialName(wmsWarehouseToolUseTask.getMaterialName());// 物料名称
        wmsToolUse.setMaterialSku(wmsWarehouseToolUseTask.getMaterialSku());// 物料SKU
        wmsToolUse.setMaterialId(wmsWarehouseToolUseTask.getMaterialId());// 物料信息ID
        wmsToolUse.setMaterialSerialNumber(materialSerialNumber);// 物料编码
        wmsToolUse.setDataTime(new Date());// 数据时间
        wmsToolUseService.save(wmsToolUse);


        String processNumber=wmsWarehouseToolUseTask.getProcessNumber();
        WmsUseApply wmsUseApply=wmsUseApplyService.getOne(new QueryWrapper<WmsUseApply>().eq("process_number", processNumber));
//        TODO 调用ERP接口反馈出库信息 王盼宇修改

        return ResponseData.success();
    }

    /**
     * 备件执行中的补货任务
     * */
    @RequestMapping("/spare_in_execution")
    @ResponseBody
    public ResponseData spareInExecution(){
        WmsWarehouseReplenishmentTaskResult wr =  wmsWarehouseReplenishmentTaskService.inExecution();
        if (wr == null){
            return ResponseData.error(500, "暂无执行中的补货任务", new WmsWarehouseReplenishmentTaskResult());
        }
        return ResponseData.success(wr);
    }

    /**
     * 备件执行中的补货任务
     * */
    @RequestMapping("/tool_in_execution")
    @ResponseBody
    public ResponseData toolInExecution(){
        WmsWarehouseToolUseTaskResult wr =  wmsWarehouseToolUseTaskService.inExecution();
        if (wr == null){
            return ResponseData.error(500, "暂无执行中的补货任务", new WmsWarehouseToolUseTaskResult());
        }
        return ResponseData.success(wr);
    }

    /**
     * 备件补货出库
     * taskNumber 补货任务编号
     * */
    @RequestMapping("/spare_out")
    @ResponseBody
    public ResponseData spareOut(String taskNumber){
        return warehouseService.replenishmentCreateOutTask(taskNumber);
    }

    /**
     * 备件补货 分拣-提交
     * WarehouseTurnoverModify
     * */
    @RequestMapping("/sorting-commit")
    @ResponseBody
    public ResponseData sortingCommit(WarehouseTurnoverModify modify){
        logger.info("/sorting-commit "+modify.getNumber());
        return oneTypeCabinetService.padSortingConform2(modify);
    }

    /**
     * 备件补货 分拣-提交
     * WarehouseTurnoverModify
     * */
    @RequestMapping("/sorting-commit-tool")
    @ResponseBody
    public ResponseData sortingCommitTool(WarehouseTurnoverModify modify){
        System.out.println("6666666666666666666666666666666666666666666666666666666");
        logger.info("/sorting-commit-tool "+modify.getNumber());
        return oneTypeCabinetService.padSortingConformTool(modify);
    }

    /**
     * 备件补货 分拣-入库
     * taskNumber 补货任务编号
     * */
    @RequestMapping("/sorting-in")
    @ResponseBody
    public ResponseData sortingIn(String taskNumber,String turnoverNumber){
        return warehouseService.replenishmentInTask2(turnoverNumber,taskNumber);
    }

    /**
     * [备件补货  完成]
     * @author       : [ASD-FuBenHao]
     * @version      : [v1.0]
     * @createTime   : [2022/5/27 15:42]
     **/
    @RequestMapping("/sorting-in-over")
    @ResponseBody
    public ResponseData sortingOver(String taskNumber) {
        WmsWarehouseReplenishmentTaskResult result = wmsWarehouseReplenishmentTaskService.findByTaskNumber(taskNumber);
        result.setTaskState("3");
        WmsWarehouseReplenishmentTaskParam wmsWarehouseReplenishmentTaskParam = new WmsWarehouseReplenishmentTaskParam();
        ToolUtil.copyProperties(result, wmsWarehouseReplenishmentTaskParam);
        wmsWarehouseReplenishmentTaskService.update(wmsWarehouseReplenishmentTaskParam);
        return ResponseData.success();
    }

    /**
     * [备件补货  完成]
     * @author       : [ASD-FuBenHao]
     * @version      : [v1.0]
     * @createTime   : [2022/5/27 15:42]
     **/
    @RequestMapping("/sorting-in-over-tool")
    @ResponseBody
    public ResponseData sortingOverTool(String taskNumber) {
        WmsWarehouseToolUseTaskResult result = wmsWarehouseToolUseTaskService.findByTaskNumber(taskNumber);
        System.out.println("777777777777777777777777777777777777777777777777777");
        if (result.getMNumber().equals(result.getSortingNum()))
        {
            result.setTaskState("3");
            //        TODO 调用ERP接口反馈出库信息 王盼宇修改

            String processNumber=result.getProcessNumber();
            WmsUseApply wmsUseApply=wmsUseApplyService.getOne(new QueryWrapper<WmsUseApply>().eq("process_number", processNumber));
//            王盼宇修改于2023-06-04，修改内容：判断工具是否选择了结算，选择了结算的工具和备件才会给ERP反馈
            WmsMaterialType wmsMaterialType=wmsMaterialTypeService.getById(result.getMaterialTypeId());

            if ((wmsMaterialType.getMaterialType().equals("GJ")&&wmsUseApply.getJieSan())||wmsMaterialType.getMaterialType().equals("BJ")) {
                String storageLocation="R06B901";
//                移动类型
                String moveMentType="G01";
                String getOrdNOStr="";
                String budgetCenter="";
                String commitmentItem="";
                String getOrdNO= wmsUseApply.getOrdNO();
                if (getOrdNO!=null&&getOrdNO!=""){
                    getOrdNOStr=getOrdNO;
                }

                if (wmsMaterialType.getMaterialType().equals("GJ")){
                    storageLocation="R06GZ11";
                    moveMentType="Z01";
                }

                if (wmsUseApply.getCostCenter().equals("Z28")){
//                    移动类型
                    moveMentType="G09";
//                    项目成本单号
                    getOrdNOStr="KC9600042301";
//                    预算中心
                    budgetCenter="Z28";
//                    预算项目
                    commitmentItem="YS66022207";
                }
                //            正式环境IP:10.7.62.76
                //            测试环境IP:10.6.201.184
                // 新建客户端
                SoapClient client = SoapClient.create("http://10.7.62.76:8011/ERP/UWMS/UWMS2ERP_SyncInvNoGuardianStorageLocation/ProxyServices/SyncInvNoGuardianStorageLocationPS?wsdl")
                        // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间
                        .setMethod("ser:reOutStorageMT", "http://service.noGuardianStor.inv.mm.erp.faw_qm.com/");
                // 设置参数，此处自动添加方法的前缀：web
                try {
                    SOAPElement msgHeader = client.getMethodEle().addChildElement("msgHeader");
                    msgHeader.addChildElement("comments").setValue("");
                    msgHeader.addChildElement("count").setValue("");
                    msgHeader.addChildElement("interfaceID").setValue("TWMS-ERP-002");
                    msgHeader.addChildElement("messageID").setValue(String.valueOf(UUID.randomUUID()));
                    msgHeader.addChildElement("receiver").setValue("JF_ERP");
                    msgHeader.addChildElement("sender").setValue("JF_TWMS");
                    msgHeader.addChildElement("transID").setValue("");
                    SOAPElement msgBody = client.getMethodEle().addChildElement("msgBody");
                    SOAPElement invReOutStorageMTItems=msgBody.addChildElement("invReOutStorageMTItems");
                    invReOutStorageMTItems.addChildElement("assetSubNO").setValue("");
//                    预算中心
                    invReOutStorageMTItems.addChildElement("budgetCenter").setValue(budgetCenter);
                    invReOutStorageMTItems.addChildElement("busArea").setValue("");
                    invReOutStorageMTItems.addChildElement("commitmentItem").setValue(commitmentItem);
                    //            成本中心
                    invReOutStorageMTItems.addChildElement("costCenter").setValue(wmsUseApply.getCostCenter());
                    //            凭证日期
                    Date date=new Date();
                    SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");
                    String dateString=formatter.format(date);
                    invReOutStorageMTItems.addChildElement("docDate").setValue(dateString);
                    invReOutStorageMTItems.addChildElement("equipmentNR").setValue("");
                    //            领用者
                    invReOutStorageMTItems.addChildElement("goodsRecipient").setValue(wmsUseApply.getOperator());
                    invReOutStorageMTItems.addChildElement("itemText").setValue("");
                    //            固定资产编码
                    String mainAssetNO="";
                    String mainAssetNOStr= wmsUseApply.getMainAssetNo();
                    if (mainAssetNOStr!=null&&mainAssetNOStr!=""){
                        mainAssetNO=mainAssetNOStr;
                    }
                    invReOutStorageMTItems.addChildElement("mainAssetNO").setValue(mainAssetNO);
                    //            移动类型
                    invReOutStorageMTItems.addChildElement("moveMentType").setValue(moveMentType);
                    //            物料号
                    invReOutStorageMTItems.addChildElement("mtlNO").setValue(wmsMaterialType.getMaterialSku());
//                    invReOutStorageMTItems.addChildElement("mtlNO").setValue("A05172331");
                    //            项目成品单号

                    invReOutStorageMTItems.addChildElement("ordNO").setValue(getOrdNOStr);
                    //            工厂
                    invReOutStorageMTItems.addChildElement("plant").setValue("R06");
                    //            记账日期
                    String dates=wmsUseApply.getPostDate();
                    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    Date postDate=dateFormat.parse(dates);
                    String postStr=formatter.format(postDate);
                    invReOutStorageMTItems.addChildElement("postDate").setValue(dateString);
                    //            数量
                    invReOutStorageMTItems.addChildElement("quantity").setValue(wmsUseApply.getmNumber());
                    //            出库单号
                    invReOutStorageMTItems.addChildElement("refDocNO").setValue(wmsUseApply.getProcessNumber());
                    invReOutStorageMTItems.addChildElement("refItemNO").setValue("");
                    invReOutStorageMTItems.addChildElement("remark1").setValue("");
                    //            存储地点
                    invReOutStorageMTItems.addChildElement("storageLocation").setValue(storageLocation);
                    //            产品编码
                    invReOutStorageMTItems.addChildElement("TPBusArea").setValue(wmsUseApply.getBusArea());
                } catch (SOAPException e) {
                    System.out.println("fffffffffffffffffffffffffffffffffD");
                    return ResponseData.error("推送失败");
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                String str= client.send(true);
                logger.info("/tool_apply_commit_over upload ERP interface"+str);
                System.out.println("/tool_apply_commit_over upload ERP interface"+str);
                System.out.println("2255555555555552222222----OutErpOutErpOutErp----2222255555555555555552222222");
                // 发送请求，参数true表示返回一个格式化后的XML内容
                // 返回内容为XML字符串，可以配合XmlUtil解析这个响应
            }
        }else {
            result.setTaskState("2");
        }
        WmsWarehouseToolUseTaskParam wmsWarehouseToolUseTaskParam = new WmsWarehouseToolUseTaskParam();
        ToolUtil.copyProperties(result, wmsWarehouseToolUseTaskParam);
        wmsWarehouseToolUseTaskService.update(wmsWarehouseToolUseTaskParam);

        return ResponseData.success();
    }

    /**
     *  备件补货 自动分拣记录
     * */
    @RequestMapping("/recentTask")
    @ResponseBody
    public ResponseData recentTask() {
        List<WmsSortingTaskResult> list = this.wmsSortingTaskService.findRecentTask();
        if (list.size() == 0){
            return ResponseData.error("暂无分拣记录");
        }
        return ResponseData.success(list);
    }

    /**
     * todo 自动分拣 提交
     * 1. 创建出库任务并执行
     *
     * 其他接口回调
     * 2. 出库完成 - 创建自动分拣任务 - 关联补货任务
     * 3. 发送分拣任务并执行
     * 4. 分拣完成 - 回调 更新周转箱信息 更新补货任务信息 更新分拣任务信息
     * 5. 创建入库任务并执行
     * 6. 入库完成 - 跟新周转箱和库位信息
     * */
    @RequestMapping("/autoSort")
    @ResponseBody
    public ResponseData autoSort(String taskNumber) {

        WmsWarehouseReplenishmentTaskResult task = wmsWarehouseReplenishmentTaskService.findByTaskNumber(taskNumber);
        if (!Objects.equals("1", task.getTaskState())) {
            return ResponseData.error("此任务未执行");
        }


        WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(task.getMaterialTypeId());

        // 创建出库任务
        WmsWarehouseTaskOut out = new WmsWarehouseTaskOut();
        String messageId = RandomStringUtils.randomNumeric(12);
        out.setMessageId(messageId);// 消息识别ID
        out.setOrderType(ApplyType.B.getType());// 订单类别(A工具领用 B补货出库 C出库)
        out.setTaskMg(task.getTaskNumber());// 任务信息
        out.setGoodsType(ApplyType.B.getType());// 出仓货物类型（A工具/B备品备件/C空周转箱）
        out.setMaterialTypeId(task.getMaterialTypeId());// 物料类型ID
        out.setMaterialSku(wmsMaterialType.getMaterialSku());// 物料SKU
        out.setMaterialType(wmsMaterialType.getMaterialType());// 物料类型
        out.setMaterialName(task.getMaterialName());// 物料名称
        out.setmBatch(task.getMBatch()); // 批次
        out.setmNumber(task.getMNumber());// 数量
        out.setResTag(StateEnum.ZERO.getState());// 请求标记（0初始 1请求）
        out.setReqStatus(StateEnum.ZERO.getState());// 请求状态（0初始 1成功 2失败）
        out.setResTag(StateEnum.ZERO.getState());// 结果标记（0初始 1更新 2结束）
        out.setResStatus(StateEnum.ZERO.getState());// 结果状态（0初始 1正在执行 2任务完成 3任务失败）
        out.setSortingInfo("B");
        out.setDataTime(new Date());// 数据时间
        wmsWarehouseTaskOutService.save(out);
        // 发送出库任务
        warehouseService.sendTaskOut(messageId);
        return ResponseData.success();
    }

    /**
     * 补货完成
     * taskNumber 补货任务编号
     * */
    @RequestMapping("/task_finished")
    @ResponseBody
    public ResponseData taskFinished(String taskNumber) {
        return warehouseService.replenishmentConformTask(taskNumber);
    }

    /**
     * 补货完成
     * taskNumber 补货任务编号
     * */
    @RequestMapping("/task_finished-tool")
    @ResponseBody
    public ResponseData taskFinishedTool(String taskNumber) {
        return warehouseService.toolUseConformTask(taskNumber);
    }

    /**
     * 清空格口
     * taskNumber 补货任务编号
     * */
    @RequestMapping("/clear_latter")
    @ResponseBody
    public ResponseData clearLatter(String turnoverId,String latticeCode) {

        oneTypeCabinetService.padWarehouseTurnoverCabinetOneCabinet(Long.valueOf(turnoverId),latticeCode);

        return ResponseData.success();
    }

    @RequestMapping("/modify_latter")
    @ResponseBody
    public ResponseData modifyLatter(String turnoverId, String latticeCode, String materialType, String mNumber){
        WarehouseTurnoverModify turnoverModify = new WarehouseTurnoverModify();
        turnoverModify.setId(Long.valueOf(turnoverId));
        turnoverModify.setLatticeCode(latticeCode);
        turnoverModify.setSparePartsId(Long.valueOf(materialType));
        turnoverModify.setTaskType(StateEnum.TWO.getState());
        turnoverModify.setNumber(mNumber);
        return oneTypeCabinetService.padWarehouseModify(turnoverModify);
    }


    /**
     * 周转箱ID latticeCode
     * 物料编码
     * 备品备件ID 数量
     * */
    @RequestMapping("/add_latter")
    @ResponseBody
    public ResponseData addLatter(WarehouseTurnoverModify modify) {
        boolean upTurnoverKg=false;
        boolean upTurnoverBindKg=false;

        String goodsType = "";
        String materialTypeId = "";
        String materialId = "";
        String materialType = "";
        String materialName = "";
        String materialSku = "";
        String mUnit = "";
        String materialSerialNumber = "";
        String mBatch = "";
        String mNumber = "";
        String sizes = "";

        WmsWarehouseTurnoverBind wmsWarehouseTurnoverBind = wmsWarehouseTurnoverBindService.getOne(new QueryWrapper<WmsWarehouseTurnoverBind>().eq("turnover_id", modify.getId()).eq("lattice_code", modify.getLatticeCode()));
        WmsWarehouseTurnover wmsWarehouseTurnover = wmsWarehouseTurnoverService.getById(modify.getId());
        WmsMaterialType wmsMaterialType = wmsMaterialTypeService.getById(modify.getSparePartsId());
        if("BJ".equals(wmsMaterialType.getMaterialType())){
            if(Integer.parseInt(wmsWarehouseTurnover.getTurnoverMouthQuantity()) > 1){
                return ResponseData.error("备件组盘：周转箱必须为单格口");
            }
        }
        if ("0".equals(wmsWarehouseTurnoverBind.getLatticeState())){
//            if("BJ".equals(wmsMaterialType.getMaterialType())){
//                if(Integer.parseInt(modify.getNumber()) > 1){
//                    return ResponseData.error("备件组盘：数量不能大于1");
//                }
//            }
            upTurnoverBindKg=true;
            goodsType="2";
            materialTypeId=wmsMaterialType.getId().toString();
            materialId=String.valueOf(wmsMaterialType.getId());
            materialType=wmsMaterialType.getMaterialType();
            materialName=wmsMaterialType.getMaterialName();
            materialSku=wmsMaterialType.getMaterialSku();
            sizes=wmsMaterialType.getSizes();
            mUnit=wmsMaterialType.getmUnit();
            materialSerialNumber="";
            mBatch=wmsMaterialType.getDiBatchNo();
            mNumber=modify.getNumber();

            upTurnoverKg=true;
        }
        if ("1".equals(wmsWarehouseTurnoverBind.getLatticeState())){
//            if("BJ".equals(wmsMaterialType.getMaterialType())){
//                return ResponseData.error("格口已有备件，备件组盘数量不能大于1");
//            }
            int num=Integer.parseInt(wmsWarehouseTurnoverBind.getmNumber())+Integer.parseInt(modify.getNumber());
            upTurnoverBindKg=true;
            goodsType="2";
            materialTypeId=wmsWarehouseTurnoverBind.getMaterialTypeId();
            materialId=String.valueOf(wmsWarehouseTurnoverBind.getId());
            materialType=wmsWarehouseTurnoverBind.getMaterialType();
            materialName=wmsWarehouseTurnoverBind.getMaterialName();
            materialSku=wmsWarehouseTurnoverBind.getMaterialSku();
            mUnit=wmsWarehouseTurnoverBind.getmUnit();
            materialSerialNumber="";
            mBatch=wmsWarehouseTurnoverBind.getmBatch();
            mNumber=String.valueOf(num);
        }

        if (upTurnoverKg){
            WmsWarehouseTurnoverParam wmsWarehouseTurnoverParam=new WmsWarehouseTurnoverParam();
            wmsWarehouseTurnoverParam.setId(wmsWarehouseTurnover.getId());
            wmsWarehouseTurnoverParam.setTurnoverState("1");
            wmsWarehouseTurnoverService.update(wmsWarehouseTurnoverParam);
        }

        if (upTurnoverBindKg){
            WmsWarehouseTurnoverBindParam wmsWarehouseTurnoverBindParam=new WmsWarehouseTurnoverBindParam();
            wmsWarehouseTurnoverBindParam.setId(wmsWarehouseTurnoverBind.getId());
            wmsWarehouseTurnoverBindParam.setGoodsType(goodsType);
            wmsWarehouseTurnoverBindParam.setMaterialTypeId(materialTypeId);
            wmsWarehouseTurnoverBindParam.setMaterialId(materialId);
            wmsWarehouseTurnoverBindParam.setMaterialType(materialType);
            wmsWarehouseTurnoverBindParam.setMaterialName(materialName);
            wmsWarehouseTurnoverBindParam.setSizes(sizes);
            wmsWarehouseTurnoverBindParam.setMaterialSku(materialSku);
            wmsWarehouseTurnoverBindParam.setMUnit(mUnit);
            wmsWarehouseTurnoverBindParam.setMaterialSerialNumber(materialSerialNumber);
            wmsWarehouseTurnoverBindParam.setMBatch(mBatch);
            wmsWarehouseTurnoverBindParam.setMNumber(mNumber);
            wmsWarehouseTurnoverBindParam.setLatticeState("1");
            wmsWarehouseTurnoverBindService.update(wmsWarehouseTurnoverBindParam);
        }
        return ResponseData.success();
    }

    /**
     * 备品备件信息
     * 工具条码:materialSerialNumber
     */
    @RequestMapping("/spare-data-all")
    @ResponseBody
    public ResponseData padWarehouseMaterialSparePartsDataAll() {
//        List<WmsMaterialSparePartsResult> wmsMaterialSparePartsResultList = this.wmsMaterialSparePartsService.findAll();
//        if (wmsMaterialSparePartsResultList == null||wmsMaterialSparePartsResultList.isEmpty()) {
//            return ResponseData.error(500, "无此类型备品备件信息", new ArrayList<>());
//        }
//        return ResponseData.success(wmsMaterialSparePartsResultList);

        List<WmsMaterialType> wmsMaterialTypeList = this.wmsMaterialTypeService.findAll();
        if (wmsMaterialTypeList == null || wmsMaterialTypeList.isEmpty()) {
            return ResponseData.error(500, "无此类型备品备件信息", new ArrayList<>());
        }
        return ResponseData.success(wmsMaterialTypeList);
    }

    /**
     * 备品备件信息
     * 工具条码:materialSerialNumber
     */
    @RequestMapping("/material-type-all")
    @ResponseBody
    public ResponseData padWarehouseMaterialTypeAll() {
//        List<WmsMaterialSparePartsResult> wmsMaterialSparePartsResultList = this.wmsMaterialSparePartsService.findAll();
//        if (wmsMaterialSparePartsResultList == null||wmsMaterialSparePartsResultList.isEmpty()) {
//            return ResponseData.error(500, "无此类型备品备件信息", new ArrayList<>());
//        }
//        return ResponseData.success(wmsMaterialSparePartsResultList);

        List<WmsMaterialType> wmsMaterialTypeList = this.wmsMaterialTypeService.findAllMaterialType();
        if (wmsMaterialTypeList == null || wmsMaterialTypeList.isEmpty()) {
            return ResponseData.error(500, "无物料信息", new ArrayList<>());
        }
        return ResponseData.success(wmsMaterialTypeList);
    }

    /**
     * 备品备件信息
     * 工具条码:materialSerialNumber
     */
    @RequestMapping("/material-no")
    @ResponseBody
    public ResponseData padWarehouseMaterialNoAll(String plant, String materialType) {
//        List<WmsMaterialSparePartsResult> wmsMaterialSparePartsResultList = this.wmsMaterialSparePartsService.findAll();
//        if (wmsMaterialSparePartsResultList == null||wmsMaterialSparePartsResultList.isEmpty()) {
//            return ResponseData.error(500, "无此类型备品备件信息", new ArrayList<>());
//        }
//        return ResponseData.success(wmsMaterialSparePartsResultList);

        WmsMaterialTypeParam param = new WmsMaterialTypeParam();
        param.setPlant(plant);
        param.setMaterialType(materialType);
        List<WmsMaterialType> wmsMaterialTypeList = this.wmsMaterialTypeService.findAllMaterialNo(param);
        if (wmsMaterialTypeList == null || wmsMaterialTypeList.isEmpty()) {
            return ResponseData.error(500, "无物料信息", new ArrayList<>());
        }
        return ResponseData.success(wmsMaterialTypeList);
    }

    /**
     * 备品备件信息
     * 工具条码:materialSerialNumber
     */
    @RequestMapping("/material-result")
    @ResponseBody
    public ResponseData padWarehouseMaterialRes(String plant, String materialType, String materialSku) {
//        List<WmsMaterialSparePartsResult> wmsMaterialSparePartsResultList = this.wmsMaterialSparePartsService.findAll();
//        if (wmsMaterialSparePartsResultList == null||wmsMaterialSparePartsResultList.isEmpty()) {
//            return ResponseData.error(500, "无此类型备品备件信息", new ArrayList<>());
//        }
//        return ResponseData.success(wmsMaterialSparePartsResultList);

        WmsMaterialTypeParam param = new WmsMaterialTypeParam();
        param.setPlant(plant);
        param.setMaterialType(materialType);
        param.setMaterialSku(materialSku);
        List<WmsMaterialType> wmsMaterialTypeList = this.wmsMaterialTypeService.findMaterialRes(param);
        if (wmsMaterialTypeList == null || wmsMaterialTypeList.isEmpty()) {
            return ResponseData.error(500, "无物料信息", new ArrayList<>());
        }
        return ResponseData.success(wmsMaterialTypeList);
    }
}
