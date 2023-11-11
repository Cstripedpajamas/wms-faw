package cn.stylefeng.guns.modular.base.purchaseorderinfo.controller;

import cn.hutool.http.webservice.SoapClient;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.config.AppConfig;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.result.WmsWarehousePurchaseorderDeliveryResult;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.service.WmsWarehousePurchaseorderDeliveryService;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.entity.WmsPurchaseOrderInfo;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.service.WmsPurchaseOrderInfoService;
import cn.stylefeng.guns.webservice.client.ExecuteBindQSService;
import cn.stylefeng.guns.webservice.client.Req;
import cn.stylefeng.guns.webservice.client.Resp;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


/**
 * 采购订单信息表控制器
 *
 * @author lhx
 * @Date 2021-11-01 15:08:30
 */
@Controller
@RequestMapping("/wmsPurchaseOrderInfo")
public class WmsPurchaseOrderInfoController extends BaseController {

    private String PREFIX = "/modular/base/wmsPurchaseOrderInfo";

    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);
    @Autowired
    private WmsPurchaseOrderInfoService wmsPurchaseOrderInfoService;

    @Autowired
    private WmsWarehousePurchaseorderDeliveryService wmsWarehousePurchaseorderDeliveryService;

    /**
     * 跳转到主页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsPurchaseOrderInfo.html";
    }

    /**
     * 新增页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsPurchaseOrderInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsPurchaseOrderInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam) {
        // 查看采购单号是否存在
        QueryWrapper<WmsPurchaseOrderInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("pur_number", wmsPurchaseOrderInfoParam.getPurNumber());
        WmsPurchaseOrderInfo one = wmsPurchaseOrderInfoService.getOne(wrapper);
        // 如果不为空，则说明采购单号存在
        if (!Objects.isNull(one)) {
            return ResponseData.error("此采购单号已存在！");
        }
        wmsPurchaseOrderInfoParam.setReceivedQuantity("0");
        wmsPurchaseOrderInfoParam.setAcceptableQuantity(wmsPurchaseOrderInfoParam.getMNumber());
        this.wmsPurchaseOrderInfoService.add(wmsPurchaseOrderInfoParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam) {
        // 查看采购单号是否存在
        QueryWrapper<WmsPurchaseOrderInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("pur_number", wmsPurchaseOrderInfoParam.getPurNumber());
        WmsPurchaseOrderInfo one = wmsPurchaseOrderInfoService.getOne(wrapper);
        // 如果不为空，则说明采购单号存在
        if (!Objects.isNull(one) && !Objects.equals(one.getId(), wmsPurchaseOrderInfoParam.getId())) {
            return ResponseData.error("此采购单号已存在！");
        }
        this.wmsPurchaseOrderInfoService.update(wmsPurchaseOrderInfoParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam) {
        this.wmsPurchaseOrderInfoService.delete(wmsPurchaseOrderInfoParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam) {
        WmsPurchaseOrderInfo detail = this.wmsPurchaseOrderInfoService.getById(wmsPurchaseOrderInfoParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author lhx
     * @Date 2021-11-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam) {
        return this.wmsPurchaseOrderInfoService.findPageBySpec(wmsPurchaseOrderInfoParam);
    }

    /**
     * 查询列表出退还意外的数据
     */
    @ResponseBody
    @RequestMapping("/contentionList")
    public ResponseData contentionList() {
        List<WmsPurchaseOrderInfo> list = this.wmsPurchaseOrderInfoService.list(new QueryWrapper<WmsPurchaseOrderInfo>().ne("arrival_state",3));
        return ResponseData.success(list);
    }

    /**
     * 手动推送接口数据到ERP
     */
    @ResponseBody
    @RequestMapping("/pushDataToErp")
    public ResponseData pushDataToErp(Long id) {
        try{
            WmsPurchaseOrderInfo wmsPurchaseOrderInfo = this.wmsPurchaseOrderInfoService.getById(id);
            WmsWarehousePurchaseorderDeliveryResult wmsWarehouse= this.wmsWarehousePurchaseorderDeliveryService.selectPurDocNo(wmsPurchaseOrderInfo.getPurDocNo(),wmsPurchaseOrderInfo.getItemNo(),wmsPurchaseOrderInfo.getPurchasereqno());
            System.out.println(wmsPurchaseOrderInfo.getClient());
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            String uid=String.valueOf(UUID.randomUUID());

            String storageLocation="R06B901";
            if (wmsPurchaseOrderInfo.getMaterialType().equals("GJ")){
                storageLocation="R06GZ11";
            }
            WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam=new WmsPurchaseOrderInfoParam();
            wmsPurchaseOrderInfoParam.setId(wmsPurchaseOrderInfo.getId());
            wmsPurchaseOrderInfoParam.setArrivalState("3");
            wmsPurchaseOrderInfoParam.setUid(uid);
            this.wmsPurchaseOrderInfoService.update(wmsPurchaseOrderInfoParam);
            System.out.println(storageLocation);
            if (wmsPurchaseOrderInfo.getClient().equals("900"))
            {
                System.out.println("huhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuhuuhuhu");
                if (wmsWarehouse!=null){
                    System.out.println("ppppppppppp0000000000000ppppppppppp00000000000ppppppppppp");
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
                    logger.info("Begin Send ERP Data:"+msgBody);
                    Resp resp = executeBindQSService.getExecuteBindQSPort().execute(req);
                }
            }else{
                if (wmsPurchaseOrderInfo!=null){
                    System.out.println("ppppppppppp111111111111ppppppppppp11111111111ppppppppppp");
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
//                        invRePurInStorageMTItems.addChildElement("postDate").setValue(dateString);
                        invRePurInStorageMTItems.addChildElement("postDate").setValue("20220420");
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
                    System.out.println("2222222222222222222----InErpInErpInErp----2222222222222222");

                }
            }
            return ResponseData.success();
        }catch(Exception exception){
            System.out.println("fffffffffffffffffffffffffffffffff");
            logger.info(exception.getMessage());
            return ResponseData.error("推送失败");
        }

    }

}


