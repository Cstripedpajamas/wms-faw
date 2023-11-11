package cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.controller;

import cn.hutool.http.webservice.SoapClient;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.config.AppConfig;
import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.entity.WmsUseApply;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.model.params.WmsUseApplyParam;
import cn.stylefeng.guns.modular.procedureManagement.wmsUseApply.service.WmsUseApplyService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
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
import java.util.UUID;


/**
 * 领用申请信息表控制器
 *
 * @author ll
 * @Date 2021-11-01 17:16:19
 */
@Controller
@RequestMapping("/wmsUseApply")
public class WmsUseApplyController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);
    private String PREFIX = "/modular/procedureManagement/wmsUseApply";

    @Autowired
    private WmsUseApplyService wmsUseApplyService;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;
    /**
     * 跳转到主页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/wmsUseApply.html";
    }

    /**
     * 新增页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/wmsUseApply_add.html";
    }

    /**
     * 编辑页面
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/wmsUseApply_edit.html";
    }

    /**
     * 新增接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(WmsUseApplyParam wmsUseApplyParam) {
        this.wmsUseApplyService.add(wmsUseApplyParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(WmsUseApplyParam wmsUseApplyParam) {
        this.wmsUseApplyService.update(wmsUseApplyParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(WmsUseApplyParam wmsUseApplyParam) {
        this.wmsUseApplyService.delete(wmsUseApplyParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author ll
     * @Date 2021-11-01
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(WmsUseApplyParam wmsUseApplyParam) {
        WmsUseApply detail = this.wmsUseApplyService.getById(wmsUseApplyParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询指定员工列表
     *
     * @author ll
     * @Date 2021-11-01
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(WmsUseApplyParam wmsUseApplyParam) {
        return this.wmsUseApplyService.findPageBySpec(wmsUseApplyParam);
    }

    /**
     *  查询所有的列表
     * */
    @ResponseBody
    @RequestMapping("/list2")
    public LayuiPageInfo list2(WmsUseApplyParam wmsUseApplyParam) {
        return this.wmsUseApplyService.findPageBySpec2(wmsUseApplyParam);
    }

    /**
     * 手动推送接口数据到ERP
     */
    @ResponseBody
    @RequestMapping("/pushDataToErp")
    public ResponseData pushDataToErp(Long id) {
        try{
            WmsUseApply wmsUseApply=wmsUseApplyService.getById(id);
            WmsMaterialType wmsMaterialType=wmsMaterialTypeService.getById(wmsUseApply.getMaterialId());
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
                    invReOutStorageMTItems.addChildElement("postDate").setValue("20220405");
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

                    System.out.println("fffffffffffffffffffffffffffffffffF");
                } catch (SOAPException e) {
                    System.out.println("fffffffffffffffffffffffffffffffffB");
                    return ResponseData.error("推送失败");
                }
                String str= client.send(true);
                logger.info("/tool_apply_commit_over upload ERP interface"+str);
                System.out.println("/tool_apply_commit_over upload ERP interface"+str);
                System.out.println("2222222222222222222----OutErpOutErpOutErp----2222222222222222222");
                // 发送请求，参数true表示返回一个格式化后的XML内容
                // 返回内容为XML字符串，可以配合XmlUtil解析这个响应
            }
            return ResponseData.error("推送失败");
        }catch(Exception exception){
            System.out.println("fffffffffffffffffffffffffffffffff");
            logger.info(exception.getMessage());
            return ResponseData.error("推送失败");
        }

    }

}


