package cn.stylefeng.guns.webservice;

import cn.stylefeng.guns.modular.base.materialType.entity.WmsMaterialType;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.model.result.WmsMaterialTypeResult;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.model.params.WmsWarehousePurchaseorderCancelParam;
import cn.stylefeng.guns.modular.base.purchaseorderCancel.service.WmsWarehousePurchaseorderCancelService;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.model.params.WmsWarehousePurchaseorderDeliveryParam;
import cn.stylefeng.guns.modular.base.purchaseorderDelivery.service.WmsWarehousePurchaseorderDeliveryService;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.result.WmsPurchaseOrderInfoResult;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.service.WmsPurchaseOrderInfoService;
import cn.stylefeng.guns.modular.base.user.model.params.WmsUserParam;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.params.FawMtlInfoParam;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.result.FawMtlInfoResult;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.service.FawMtlInfoService;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.params.FawPurchaseOrderParam;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.result.FawPurchaseOrderResult;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.service.FawPurchaseOrderService;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.params.FawUserInfoParam;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.result.FawUserInfoResult;
import cn.stylefeng.guns.modular.fawInfo.userInfo.service.FawUserInfoService;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.params.FawmallPurchaseorderCancelParam;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.params.FawmallPurchaseorderDeliveryParam;
import cn.stylefeng.guns.modular.fawPurchase0rder.model.result.FawmallPurchaseorderCancelResult;
import cn.stylefeng.guns.modular.fawPurchase0rder.service.FawmallPurchaseorderCancelService;
import cn.stylefeng.guns.modular.fawPurchase0rder.service.FawmallPurchaseorderDeliveryService;
import cn.stylefeng.guns.modular.statistics.tooluse.model.params.WmsToolUseParam;
import cn.stylefeng.guns.modular.statistics.tooluse.model.result.WmsToolUseResult;
import cn.stylefeng.guns.modular.statistics.tooluse.service.WmsToolUseService;
import cn.stylefeng.guns.sys.modular.system.model.UserDto;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.guns.webservice.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName FawApiServiceImpl
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2022/2/21 14:39
 * @Version 1.0
 **/
@Component
@WebService(name = "fawApiService",
        targetNamespace = "http://andot.org/webservice/demo/server",
        endpointInterface = "cn.stylefeng.guns.webservice.FawApiService")
public class FawApiServiceImpl implements FawApiService {

    private final static Logger logger = LoggerFactory.getLogger(FawApiServiceImpl.class);

    @Autowired
    private WmsToolUseService wmsToolUseService;

    @Autowired
    private FawUserInfoService fawUserInfoService;
    @Autowired
    private FawmallPurchaseorderCancelService fawmallPurchaseorderCancelService;

    @Autowired
    private FawmallPurchaseorderDeliveryService fawmallPurchaseorderDeliveryService;

    @Autowired
    private WmsWarehousePurchaseorderCancelService wmsWarehousePurchaseorderCancelService;

    @Autowired
    private WmsWarehousePurchaseorderDeliveryService wmsWarehousePurchaseorderDeliveryService;

    @Autowired
    private WmsUserService wmsUserService;

    @Autowired
    private FawPurchaseOrderService fawPurchaseOrderService;

    @Autowired
    private WmsPurchaseOrderInfoService wmsPurchaseOrderInfoService;

    @Autowired
    private FawMtlInfoService fawMtlInfoService;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;

    @Autowired
    private UserService userService;

    @Override
    public RsBody setUserInfo(MsgHeader msgHeader, MsgBody msgBody) {

        logger.info("--setUserInfo--");
        logger.info(msgHeader.toString());
        logger.info(msgBody.toString());

        List<FawUserInfoResult> fawUserInfoResults = this.fawUserInfoService.findListBySpec(new FawUserInfoParam());

        List<YQJFUserInfo> yqjfUserInfo = msgBody.getYqjfUserInfo();
        //新增集合
        List<FawUserInfoParam> fawUserInfoParamList = new ArrayList<>();
        //更新集合
        List<FawUserInfoParam> fawUserInfoParamListUp = new ArrayList<>();

        if (!yqjfUserInfo.isEmpty()) {
            for (YQJFUserInfo userInfo : yqjfUserInfo) {
                FawUserInfoParam fawUserInfoParam = new FawUserInfoParam();
                fawUserInfoParam.setAccountCode(nullSetValue(userInfo.getAccountCode()));
                fawUserInfoParam.setEmployeeId(nullSetValue(userInfo.getEmployeeId()));
                fawUserInfoParam.setDepNo(nullSetValue(userInfo.getDepNo()));
                fawUserInfoParam.setEmployeeName(nullSetValue(userInfo.getEmployeeName()));
                fawUserInfoParam.setEmailAddress(nullSetValue(userInfo.getEmailAddress()));
                fawUserInfoParam.setClassOfPositions(nullSetValue(userInfo.getClassOfPositions()));
                fawUserInfoParam.setFawClaOfPos(nullSetValue(userInfo.getFawClaOfPos()));
                fawUserInfoParam.setJobs(nullSetValue(userInfo.getJobs()));
                fawUserInfoParam.setDirectorId(nullSetValue(userInfo.getDirectorId()));
                fawUserInfoParam.setDirectorName(nullSetValue(userInfo.getDirectorName()));
                fawUserInfoParam.setObjectStatus(nullSetValue(userInfo.getObjectStatus()));
                fawUserInfoParam.setMdmType(nullSetValue(userInfo.getMdmType()));
                fawUserInfoParam.setDeptLevel(nullSetValue(userInfo.getDeptLevel()));
                boolean dataKg = true;
                if (!fawUserInfoResults.isEmpty()) {
                    for (FawUserInfoResult fawUserInfoResult : fawUserInfoResults) {
                        if (fawUserInfoResult.getAccountCode().equals(fawUserInfoParam.getAccountCode())) {
                            fawUserInfoParam.setId(fawUserInfoResult.getId());
                            dataKg = false;
                        }
                    }
                }
                if (dataKg) {
                    fawUserInfoParamList.add(fawUserInfoParam);
                } else {
                    fawUserInfoParamListUp.add(fawUserInfoParam);
                }
            }
        }

        if (!fawUserInfoParamList.isEmpty()) {
            this.fawUserInfoService.insertListBatch(fawUserInfoParamList);
            List<WmsUserParam> wmsUserParams = new ArrayList<>();
            for (FawUserInfoParam fawUserInfoParam : fawUserInfoParamList) {
                WmsUserParam wmsUserParam = new WmsUserParam();
                wmsUserParam.setAccountcode(fawUserInfoParam.getAccountCode());
                wmsUserParam.setEmailaddress(fawUserInfoParam.getEmailAddress());
                wmsUserParam.setClassofpositions(fawUserInfoParam.getClassOfPositions());
                wmsUserParam.setFawclaofpos(fawUserInfoParam.getFawClaOfPos());
                wmsUserParam.setDirectorid(fawUserInfoParam.getDirectorId());
                wmsUserParam.setDirectorname(fawUserInfoParam.getDirectorName());
                wmsUserParam.setMdmtype(fawUserInfoParam.getMdmType());
                wmsUserParam.setDeptlevel(fawUserInfoParam.getDeptLevel());
                wmsUserParam.setObjectstatus(fawUserInfoParam.getObjectStatus());
                wmsUserParam.setSerialNumber(fawUserInfoParam.getEmployeeId());
                wmsUserParam.setUserName(fawUserInfoParam.getEmployeeName());
                wmsUserParam.setWorkTeam(fawUserInfoParam.getDepNo());
                wmsUserParam.setJobResponsibility(fawUserInfoParam.getJobs());
                wmsUserParam.setIdInfo(fawUserInfoParam.getEmployeeId());
                wmsUserParam.setUserType("C");
                wmsUserParam.setDataState("0");
                wmsUserParam.setCreateTime(new Date());
                wmsUserParam.setUPwd("Rz123456!");

                wmsUserParams.add(wmsUserParam);
                UserDto user=new UserDto();
                user.setAccount(fawUserInfoParam.getEmployeeId());
                user.setPassword("Rz123456!");
                user.setName(fawUserInfoParam.getEmployeeName());
                user.setBirthday(new Date());
                user.setSex("M");
                user.setEmail(fawUserInfoParam.getEmailAddress());
                user.setPhone("00000000");
                user.setDeptId(Long.parseLong("1531450546655285250"));
                user.setPosition("1531451053394317313");
                user.setRoleId("1531456276884049922");
                this.userService.addUser(user);
            }
            this.wmsUserService.insertListBatch(wmsUserParams);
        }
        if (!fawUserInfoParamListUp.isEmpty()) {
            for (FawUserInfoParam fawUserInfoParam : fawUserInfoParamListUp) {
                this.fawUserInfoService.update(fawUserInfoParam);
            }
        }

        RsBody rsBody = new RsBody();
        RsMsgHeader rsMsgHeader = new RsMsgHeader();
        rsMsgHeader.setMessageID(nullSetValue(msgHeader.getMessageID()));
        rsMsgHeader.setInterfaceID(nullSetValue(msgHeader.getInterfaceID()));
        rsMsgHeader.setTransID(nullSetValue(msgHeader.getTransID()));
        rsMsgHeader.setResultType("0");
        rsMsgHeader.setResultCode("");
        rsMsgHeader.setResultMessage("接收成功");
        rsMsgHeader.setCount(nullSetValue(msgHeader.getCount()));
        rsMsgHeader.setComment(nullSetValue(msgHeader.getComment()));
        RsMsgBody rsMsgBody = new RsMsgBody();
        rsMsgBody.setCode("0");
        rsMsgBody.setBusinessCode("");
        rsMsgBody.setMessage("");
        rsBody.setMsgBody(rsMsgBody);
        rsBody.setMsgHeader(rsMsgHeader);

        logger.info("--OVER--");
        return rsBody;
    }

    @Override
    public RsErpBody setPurchaseOrder(MsgHeader msgHeader, MsgBodyErp msgBody) {

        logger.info("--setPurchaseOrder--");
        logger.info(msgHeader.toString());
        logger.info(msgBody.toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<FawPurchaseOrderResult> fawPurchaseOrderResultList = this.fawPurchaseOrderService.findListBySpec(new FawPurchaseOrderParam());

        List<YQJFPurchaseOrderInfo> yqjfPurchaseOrderInfo = msgBody.getYqjfPurchaseOrderInfo();
        //新增集合
        List<FawPurchaseOrderParam> fawPurchaseOrderParamArrayList = new ArrayList<>();
        //更新集合
        List<FawPurchaseOrderParam> fawPurchaseOrderParamArrayListUp = new ArrayList<>();

        if (yqjfPurchaseOrderInfo != null && !yqjfPurchaseOrderInfo.isEmpty()) {
            for (YQJFPurchaseOrderInfo purchaseOrderInfo : yqjfPurchaseOrderInfo) {
                if (!purchaseOrderInfo.getPurDocNO().startsWith("PO")){
                FawPurchaseOrderParam fawPurchaseOrderParam = new FawPurchaseOrderParam();
                fawPurchaseOrderParam.setClient(nullSetValue(purchaseOrderInfo.getClient()));
                fawPurchaseOrderParam.setPurDocitemNo(nullSetValue(purchaseOrderInfo.getPurDocItemNO()));
                fawPurchaseOrderParam.setPurDocNo(nullSetValue(purchaseOrderInfo.getPurDocNO()));
                fawPurchaseOrderParam.setPurchaseReqNo(nullSetValue(purchaseOrderInfo.getPurchaseReqNO()));
                fawPurchaseOrderParam.setItemNo(nullSetValue(purchaseOrderInfo.getItemNO()));
                fawPurchaseOrderParam.setPurStockBillId(nullSetValue(purchaseOrderInfo.getPURStockBillID()));
                fawPurchaseOrderParam.setBuyListStrDes(nullSetValue(purchaseOrderInfo.getBuyListStrDes()));
                fawPurchaseOrderParam.setCreatedBy(nullSetValue(purchaseOrderInfo.getCreatedBy()));
                try {
                    if (purchaseOrderInfo.getCreatedDate() != null) {
                        fawPurchaseOrderParam.setCreatedDate(sdf.parse(purchaseOrderInfo.getCreatedDate()));
                    }
                    if (purchaseOrderInfo.getDocDate() != null) {
                        fawPurchaseOrderParam.setDocDate(sdf.parse(purchaseOrderInfo.getDocDate()));
                    }
                    if (purchaseOrderInfo.getItemDeliveryDate() != null) {
                        fawPurchaseOrderParam.setItemDeliveryDate(sdf.parse(purchaseOrderInfo.getItemDeliveryDate()));
                    }
                    if (purchaseOrderInfo.getDI_UPDATETIME() != null) {
                        fawPurchaseOrderParam.setDiUpdatetime(sdf.parse(purchaseOrderInfo.getDI_UPDATETIME()));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fawPurchaseOrderParam.setEstimatedPriceIndic(nullSetValue(purchaseOrderInfo.getEstimatedPriceIndic()));
                fawPurchaseOrderParam.setMatBrand(nullSetValue(purchaseOrderInfo.getMatBrand()));
                fawPurchaseOrderParam.setMtlNo(nullSetValue(purchaseOrderInfo.getMtlNO()));
                fawPurchaseOrderParam.setNetValue(nullSetValue(purchaseOrderInfo.getNetValue()));
                fawPurchaseOrderParam.setOrdPriceUnit(nullSetValue(purchaseOrderInfo.getOrdPriceUnit()));
                fawPurchaseOrderParam.setOrdType(nullSetValue(purchaseOrderInfo.getOrdType()));
                fawPurchaseOrderParam.setPlant(nullSetValue(purchaseOrderInfo.getPlant()));
                fawPurchaseOrderParam.setPrice(nullSetValue(purchaseOrderInfo.getPrice()));
                fawPurchaseOrderParam.setPromotion(nullSetValue(purchaseOrderInfo.getPromotion()));
                fawPurchaseOrderParam.setProposerDesc(nullSetValue(purchaseOrderInfo.getProposerDesc()));
                fawPurchaseOrderParam.setProposerId(nullSetValue(purchaseOrderInfo.getProposerID()));
                fawPurchaseOrderParam.setPurGrp(nullSetValue(purchaseOrderInfo.getPurGrp()));
                fawPurchaseOrderParam.setPurGrpDesc(nullSetValue(purchaseOrderInfo.getPurGrpDesc()));
                fawPurchaseOrderParam.setPurOrg(nullSetValue(purchaseOrderInfo.getPurOrg()));
                fawPurchaseOrderParam.setPurdocheaderId(nullSetValue(purchaseOrderInfo.getPurdocheaderid()));
                fawPurchaseOrderParam.setPurdocitemId(nullSetValue(purchaseOrderInfo.getPurdocitemid()));
                fawPurchaseOrderParam.setQuantity(nullSetValue(purchaseOrderInfo.getQuantity()));
                fawPurchaseOrderParam.setRemark(nullSetValue(purchaseOrderInfo.getRemark()));
                fawPurchaseOrderParam.setRemark1(nullSetValue(purchaseOrderInfo.getRemark1()));
                fawPurchaseOrderParam.setStoreLocation(nullSetValue(purchaseOrderInfo.getStoreLocation()));
                fawPurchaseOrderParam.setVendorDesc(nullSetValue(purchaseOrderInfo.getVendorDesc()));
                fawPurchaseOrderParam.setVendorNo(nullSetValue(purchaseOrderInfo.getVendorNO()));
                fawPurchaseOrderParam.setPhone(nullSetValue(purchaseOrderInfo.getPhone()));
                fawPurchaseOrderParam.setReqPhone(nullSetValue(purchaseOrderInfo.getReqPhone()));
                fawPurchaseOrderParam.setPlantDes(nullSetValue(purchaseOrderInfo.getPlantDes()));
                fawPurchaseOrderParam.setStoreLocationDes(nullSetValue(purchaseOrderInfo.getStoreLocationDes()));
                fawPurchaseOrderParam.setSizecoL(nullSetValue(purchaseOrderInfo.getSizecoL()));
                fawPurchaseOrderParam.setUnitDes(nullSetValue(purchaseOrderInfo.getUnitDes()));
                fawPurchaseOrderParam.setCreatedByDesc(nullSetValue(purchaseOrderInfo.getCreatedByDesc()));
                fawPurchaseOrderParam.setStateDesc(nullSetValue(purchaseOrderInfo.getStateDesc()));
                fawPurchaseOrderParam.setDiOpertype(nullSetValue(purchaseOrderInfo.getDI_OPERTYPE()));
                fawPurchaseOrderParam.setDiBatch(nullSetValue(purchaseOrderInfo.getDI_BATCHNO()));
                boolean dataKg = true;
                if (!fawPurchaseOrderResultList.isEmpty()) {
                    for (FawPurchaseOrderResult fawPurchaseOrderResult : fawPurchaseOrderResultList) {
                        if (fawPurchaseOrderResult.getPurDocitemNo().equals(fawPurchaseOrderParam.getPurDocitemNo()) && fawPurchaseOrderResult.getPurDocNo().equals(fawPurchaseOrderParam.getPurDocNo()) && fawPurchaseOrderResult.getPurchaseReqNo().equals(fawPurchaseOrderParam.getPurchaseReqNo()) && fawPurchaseOrderResult.getItemNo().equals(fawPurchaseOrderParam.getItemNo())) {
                            fawPurchaseOrderParam.setId(fawPurchaseOrderResult.getId());
                            dataKg = false;
                        }
                    }
                }
                if (dataKg) {
                    fawPurchaseOrderParamArrayList.add(fawPurchaseOrderParam);
                } else {
                    fawPurchaseOrderParamArrayListUp.add(fawPurchaseOrderParam);
                 }
             }
            }
        }

        if (!fawPurchaseOrderParamArrayList.isEmpty()) {
            this.fawPurchaseOrderService.insertListBatch(fawPurchaseOrderParamArrayList);
            List<WmsPurchaseOrderInfoParam> wmsPurchaseOrderInfoParams = new ArrayList<>();
            for (FawPurchaseOrderParam fawPurchaseOrderParam : fawPurchaseOrderParamArrayList) {
            if (!fawPurchaseOrderParam.getPurDocNo().startsWith("PO")){
                WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam = new WmsPurchaseOrderInfoParam();
                wmsPurchaseOrderInfoParam.setPurNumber(fawPurchaseOrderParam.getPurdocitemId());
                wmsPurchaseOrderInfoParam.setClient(nullSetValue(fawPurchaseOrderParam.getClient()));
                wmsPurchaseOrderInfoParam.setPurdocitemno(nullSetValue(fawPurchaseOrderParam.getPurDocitemNo()));
                wmsPurchaseOrderInfoParam.setPurchasereqno(nullSetValue(fawPurchaseOrderParam.getPurchaseReqNo()));
                wmsPurchaseOrderInfoParam.setPurdocno(nullSetValue(fawPurchaseOrderParam.getPurDocNo()));
                wmsPurchaseOrderInfoParam.setItemno(nullSetValue(fawPurchaseOrderParam.getItemNo()));
                wmsPurchaseOrderInfoParam.setPurstockbillid(nullSetValue(fawPurchaseOrderParam.getPurStockBillId()));
                wmsPurchaseOrderInfoParam.setBuyliststrdes(nullSetValue(fawPurchaseOrderParam.getBuyListStrDes()));
                wmsPurchaseOrderInfoParam.setCreatedby(nullSetValue(fawPurchaseOrderParam.getCreatedBy()));
                wmsPurchaseOrderInfoParam.setCreateddate(fawPurchaseOrderParam.getCreatedDate());
                wmsPurchaseOrderInfoParam.setDocDate(fawPurchaseOrderParam.getDocDate());
                wmsPurchaseOrderInfoParam.setEstimatedpriceindic(nullSetValue(fawPurchaseOrderParam.getEstimatedPriceIndic()));
                wmsPurchaseOrderInfoParam.setMatBrand(nullSetValue(fawPurchaseOrderParam.getMatBrand()));
                wmsPurchaseOrderInfoParam.setNetvalue(nullSetValue(fawPurchaseOrderParam.getNetValue()));
                wmsPurchaseOrderInfoParam.setOrdpriceunit(nullSetValue(fawPurchaseOrderParam.getOrdPriceUnit()));
                wmsPurchaseOrderInfoParam.setOrdtype(nullSetValue(fawPurchaseOrderParam.getOrdType()));
                wmsPurchaseOrderInfoParam.setPlant(nullSetValue(fawPurchaseOrderParam.getPlant()));
                wmsPurchaseOrderInfoParam.setPrice(nullSetValue(fawPurchaseOrderParam.getPrice()));
                wmsPurchaseOrderInfoParam.setPromotion(nullSetValue(fawPurchaseOrderParam.getPromotion()));
                wmsPurchaseOrderInfoParam.setProposerdesc(nullSetValue(fawPurchaseOrderParam.getProposerDesc()));
                wmsPurchaseOrderInfoParam.setProposerid(nullSetValue(fawPurchaseOrderParam.getProposerId()));
                wmsPurchaseOrderInfoParam.setPurgrp(nullSetValue(fawPurchaseOrderParam.getPurGrp()));
                wmsPurchaseOrderInfoParam.setPurOrg(nullSetValue(fawPurchaseOrderParam.getPurOrg()));
                wmsPurchaseOrderInfoParam.setPurdocheaderid(nullSetValue(fawPurchaseOrderParam.getPurdocheaderId()));
                wmsPurchaseOrderInfoParam.setPurgrpdesc(nullSetValue(fawPurchaseOrderParam.getPurGrpDesc()));
                wmsPurchaseOrderInfoParam.setRemark(nullSetValue(fawPurchaseOrderParam.getRemark()));
                wmsPurchaseOrderInfoParam.setRemark1(nullSetValue(fawPurchaseOrderParam.getRemark1()));
                wmsPurchaseOrderInfoParam.setStorelocation(nullSetValue(fawPurchaseOrderParam.getStoreLocation()));
                wmsPurchaseOrderInfoParam.setVendordesc(nullSetValue(fawPurchaseOrderParam.getVendorDesc()));
                wmsPurchaseOrderInfoParam.setPlantdes(nullSetValue(fawPurchaseOrderParam.getPlantDes()));
                wmsPurchaseOrderInfoParam.setStorelocationdes(nullSetValue(fawPurchaseOrderParam.getStoreLocationDes()));
                wmsPurchaseOrderInfoParam.setSizecol(nullSetValue(fawPurchaseOrderParam.getSizecoL()));
                wmsPurchaseOrderInfoParam.setUnitdes(nullSetValue(fawPurchaseOrderParam.getUnitDes()));
                wmsPurchaseOrderInfoParam.setCreatedbydesc(nullSetValue(fawPurchaseOrderParam.getCreatedByDesc()));
                wmsPurchaseOrderInfoParam.setStatedesc(nullSetValue(fawPurchaseOrderParam.getStateDesc()));
                wmsPurchaseOrderInfoParam.setVendorno(nullSetValue(fawPurchaseOrderParam.getVendorNo()));
                wmsPurchaseOrderInfoParam.setPhone(nullSetValue(fawPurchaseOrderParam.getPhone()));
                wmsPurchaseOrderInfoParam.setReqphone(nullSetValue(fawPurchaseOrderParam.getReqPhone()));
                wmsPurchaseOrderInfoParam.setDiOperType(nullSetValue(fawPurchaseOrderParam.getDiOpertype()));
                wmsPurchaseOrderInfoParam.setDiBatchNo(nullSetValue(fawPurchaseOrderParam.getDiBatch()));
                wmsPurchaseOrderInfoParam.setDiUpdatetime(fawPurchaseOrderParam.getDiUpdatetime());
                WmsMaterialTypeResult wmsMaterialTypeResult= wmsMaterialTypeService.findByMaterialSku(fawPurchaseOrderParam.getMtlNo());
                if (wmsMaterialTypeResult!=null)
                {
                    wmsPurchaseOrderInfoParam.setMaterialTypeId(wmsMaterialTypeResult.getId().toString());
                    wmsPurchaseOrderInfoParam.setType(wmsMaterialTypeResult.getType());
                    wmsPurchaseOrderInfoParam.setMaterialType(wmsMaterialTypeResult.getMaterialType());
                    wmsPurchaseOrderInfoParam.setMaterialName(wmsMaterialTypeResult.getMaterialName());
                    wmsPurchaseOrderInfoParam.setMUnit(wmsMaterialTypeResult.getMUnit());
                }else {
                    wmsPurchaseOrderInfoParam.setMaterialTypeId("");
                    wmsPurchaseOrderInfoParam.setType("");
                    wmsPurchaseOrderInfoParam.setMaterialType("");
                    wmsPurchaseOrderInfoParam.setMaterialName("");
                    wmsPurchaseOrderInfoParam.setMUnit("");
                }
                wmsPurchaseOrderInfoParam.setMaterialSku(nullSetValue(fawPurchaseOrderParam.getMtlNo()));
                wmsPurchaseOrderInfoParam.setMNumber(nullSetValue(fawPurchaseOrderParam.getQuantity()));
                wmsPurchaseOrderInfoParam.setArrivalTime(fawPurchaseOrderParam.getItemDeliveryDate());
                wmsPurchaseOrderInfoParam.setReceivedQuantity("0");
                wmsPurchaseOrderInfoParam.setPrintNum("0");
                wmsPurchaseOrderInfoParam.setAcceptableQuantity(nullSetValue(fawPurchaseOrderParam.getQuantity()));
//                if(fawPurchaseOrderParam.getPurDocNo().startsWith("PO")){
//                    wmsPurchaseOrderInfoParam.setArrivalState("5");
//                }else {
                    wmsPurchaseOrderInfoParam.setArrivalState("0");
//                }
                wmsPurchaseOrderInfoParams.add(wmsPurchaseOrderInfoParam);

            }
         }

            this.wmsPurchaseOrderInfoService.insertListBatch(wmsPurchaseOrderInfoParams);
        }

        if (!fawPurchaseOrderParamArrayListUp.isEmpty()) {
            for (FawPurchaseOrderParam fawPurchaseOrderParam : fawPurchaseOrderParamArrayListUp) {
                this.fawPurchaseOrderService.update(fawPurchaseOrderParam);
            }
        }


        RsErpBody rsErpBody = new RsErpBody();
        RsMsgHeader rsMsgHeader = new RsMsgHeader();
        rsMsgHeader.setMessageID(nullSetValue(msgHeader.getMessageID()));
        rsMsgHeader.setInterfaceID(nullSetValue(msgHeader.getInterfaceID()));
        rsMsgHeader.setTransID(nullSetValue(msgHeader.getTransID()));
        rsMsgHeader.setResultType("0");
        rsMsgHeader.setResultCode("");
        rsMsgHeader.setResultMessage("接收成功");
        rsMsgHeader.setCount(nullSetValue(msgHeader.getCount()));
        rsMsgHeader.setComment(nullSetValue(msgHeader.getComment()));
        RsMsgErpBody rsMsgErpBody = new RsMsgErpBody();
        rsMsgErpBody.setMSG("");
        rsMsgErpBody.setMSGNO("");
        rsMsgErpBody.setRESULT("");
        rsErpBody.setMsgBody(rsMsgErpBody);
        rsErpBody.setMsgHeader(rsMsgHeader);

        logger.info("--OVER--");
        return rsErpBody;
    }

    @Override
    public RsBody getMaterielInfos(MsgHeader msgHeader, MsgBodyByEsb msgBody) {

        logger.info("--getMaterielInfos--");
        logger.info(msgHeader.toString());
        logger.info(msgBody.toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        List<FawMtlInfoResult> fawMtlInfoResultList = this.fawMtlInfoService.findListBySpec(new FawMtlInfoParam());

        List<YQJFMaterialInfo> yqjfMaterialInfo = msgBody.getYqjfMaterialInfo();
        //新增集合
        List<FawMtlInfoParam> fawMtlInfoParams = new ArrayList<>();
        //更新集合
        List<FawMtlInfoParam> fawMtlInfoParamsUp = new ArrayList<>();

        if (yqjfMaterialInfo != null && !yqjfMaterialInfo.isEmpty()) {
            for (YQJFMaterialInfo materialInfo : yqjfMaterialInfo) {
                FawMtlInfoParam fawMtlInfoParam = new FawMtlInfoParam();
                fawMtlInfoParam.setDiOpertype(nullSetValue(materialInfo.getDI_OPERTYPE()));
                fawMtlInfoParam.setDiBatch(nullSetValue(materialInfo.getDI_BATCHNO()));
                if (materialInfo.getDI_UPDATETIME() != null) {
                    try {
                        fawMtlInfoParam.setDiUpdatetime(sdf.parse(materialInfo.getDI_UPDATETIME()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                fawMtlInfoParam.setPlant(nullSetValue(materialInfo.getPLANT()));
                fawMtlInfoParam.setMtlNo(nullSetValue(materialInfo.getMTLNO()));
                fawMtlInfoParam.setMtlNodes(nullSetValue(materialInfo.getMTLNODES()));
                fawMtlInfoParam.setMtlType(nullSetValue(materialInfo.getMTLTYPE()));
                fawMtlInfoParam.setDelflagforclientmtl(nullSetValue(materialInfo.getDELFLAGFORCLIENTMTL()));
                fawMtlInfoParam.setMeasurebaseunit(nullSetValue(materialInfo.getMEASUREBASEUNIT()));
                fawMtlInfoParam.setIndustrystnddes(nullSetValue(materialInfo.getINDUSTRYSTNDDES()));
                fawMtlInfoParam.setPagefromat(nullSetValue(materialInfo.getPAGEFROMAT()));
                fawMtlInfoParam.setIsconfflag(nullSetValue(materialInfo.getISCONFFLAG()));
                fawMtlInfoParam.setDatauser(nullSetValue(materialInfo.getDATAUSER()));
                fawMtlInfoParam.setPurgrp(nullSetValue(materialInfo.getPURGRP()));
                fawMtlInfoParam.setProcuretype(nullSetValue(materialInfo.getPROCURETYPE()));
                fawMtlInfoParam.setSpecprocuretype(nullSetValue(materialInfo.getSPECPROCURETYPE()));
                fawMtlInfoParam.setMrpcontroller(nullSetValue(materialInfo.getMRPCONTROLLER()));
                fawMtlInfoParam.setValctg(nullSetValue(materialInfo.getVALCTG()));
                fawMtlInfoParam.setCroplant(nullSetValue(materialInfo.getCROPLANT()));
                fawMtlInfoParam.setSizes(nullSetValue(materialInfo.getSIZES()));
                fawMtlInfoParam.setSpmtlstatus(nullSetValue(materialInfo.getSPMTLSTATUS()));
                boolean dataKg = true;
                WmsMaterialType wmsMaterialType= this.wmsMaterialTypeService.getOne(new QueryWrapper<WmsMaterialType>().eq("material_sku",fawMtlInfoParam.getMtlNo()));
                if (wmsMaterialType!=null){
                    dataKg = false;
                }
                if (!fawMtlInfoResultList.isEmpty()) {
                    for (FawMtlInfoResult fawMtlInfoResult : fawMtlInfoResultList) {
                        if (fawMtlInfoResult.getMtlNo().equals(fawMtlInfoParam.getMtlNo())) {
                            fawMtlInfoParam.setId(fawMtlInfoResult.getId());
                            dataKg = false;
                        }
                    }
                }
                if (dataKg) {
                    fawMtlInfoParams.add(fawMtlInfoParam);
                } else {
                    fawMtlInfoParamsUp.add(fawMtlInfoParam);
                }
            }
        }

        if (!fawMtlInfoParams.isEmpty()) {
            this.fawMtlInfoService.insertListBatch(fawMtlInfoParams);
            List<WmsMaterialTypeParam> wmsMaterialTypeParamList = new ArrayList<>();
            for (FawMtlInfoParam fawMtlInfoParam : fawMtlInfoParams) {
                WmsMaterialTypeParam wmsMaterialTypeParam = new WmsMaterialTypeParam();
                if (fawMtlInfoParam.getMtlType().equals("GJ")){
                    wmsMaterialTypeParam.setType("1");
                }
                if (fawMtlInfoParam.getMtlType().equals("BJ")){
                    wmsMaterialTypeParam.setType("2");
                }
                wmsMaterialTypeParam.setMaterialType(nullSetValue(fawMtlInfoParam.getMtlType()));
                wmsMaterialTypeParam.setMaterialName(nullSetValue(fawMtlInfoParam.getMtlNodes()));
                wmsMaterialTypeParam.setMaterialSku(nullSetValue(fawMtlInfoParam.getMtlNo()));
                wmsMaterialTypeParam.setMUnit(nullSetValue(fawMtlInfoParam.getMeasurebaseunit()));
                wmsMaterialTypeParam.setLatticeMouthType("");
                wmsMaterialTypeParam.setDataState("1");
                wmsMaterialTypeParam.setCreateTime(new Date());
                wmsMaterialTypeParam.setSortType("0");
                wmsMaterialTypeParam.setPackageType("");
                wmsMaterialTypeParam.setPackageNumber("0");
                wmsMaterialTypeParam.setSource("1");
                wmsMaterialTypeParam.setDiOperType(fawMtlInfoParam.getDiOpertype());
                wmsMaterialTypeParam.setDiBatchNo(nullSetValue(fawMtlInfoParam.getDiBatch()));
                wmsMaterialTypeParam.setDiUpdatetime(new Date());
                wmsMaterialTypeParam.setPlant(nullSetValue(fawMtlInfoParam.getPlant()));
                wmsMaterialTypeParam.setDelflagforclientmtl(nullSetValue(fawMtlInfoParam.getDelflagforclientmtl()));
                wmsMaterialTypeParam.setIndustrystnddes(nullSetValue(fawMtlInfoParam.getIndustrystnddes()));
                wmsMaterialTypeParam.setPagefromat(nullSetValue(fawMtlInfoParam.getPagefromat()));
                wmsMaterialTypeParam.setIsconfflag(nullSetValue(fawMtlInfoParam.getIsconfflag()));
                wmsMaterialTypeParam.setDatauser(nullSetValue(fawMtlInfoParam.getDatauser()));
                wmsMaterialTypeParam.setPurgrp(nullSetValue(fawMtlInfoParam.getPurgrp()));
                wmsMaterialTypeParam.setProcuretype(nullSetValue(fawMtlInfoParam.getProcuretype()));
                wmsMaterialTypeParam.setSpecprocuretype(nullSetValue(fawMtlInfoParam.getSpecprocuretype()));
                wmsMaterialTypeParam.setMrpcontroller(nullSetValue(fawMtlInfoParam.getMrpcontroller()));
                wmsMaterialTypeParam.setValctg(nullSetValue(fawMtlInfoParam.getValctg()));
                wmsMaterialTypeParam.setCroplant(nullSetValue(fawMtlInfoParam.getCroplant()));
                wmsMaterialTypeParam.setSizes(fawMtlInfoParam.getSizes());
                wmsMaterialTypeParam.setSpmtlstatus(nullSetValue(fawMtlInfoParam.getSpmtlstatus()));
                wmsMaterialTypeParamList.add(wmsMaterialTypeParam);
            }
            this.wmsMaterialTypeService.insertListBatch(wmsMaterialTypeParamList);
        }

        if (!fawMtlInfoParamsUp.isEmpty()) {
            for (FawMtlInfoParam fawMtlInfoParam : fawMtlInfoParamsUp) {
                this.fawMtlInfoService.update(fawMtlInfoParam);
            }
        }


        RsBody rsBody = new RsBody();
        RsMsgHeader rsMsgHeader = new RsMsgHeader();
        rsMsgHeader.setMessageID(nullSetValue(msgHeader.getMessageID()));
        rsMsgHeader.setInterfaceID(nullSetValue(msgHeader.getInterfaceID()));
        rsMsgHeader.setTransID(nullSetValue(msgHeader.getTransID()));
        rsMsgHeader.setResultType("0");
        rsMsgHeader.setResultCode("");
        rsMsgHeader.setResultMessage("接收成功");
        rsMsgHeader.setCount(nullSetValue(msgHeader.getCount()));
        rsMsgHeader.setComment(nullSetValue(msgHeader.getComment()));
        rsBody.setMsgHeader(rsMsgHeader);

        logger.info("--OVER--");
        return rsBody;
    }

    @Override
    public RsMomBody getToolCollection(MsgHeader msgHeader, MsgBodyByMom msgBody) {

        logger.info("--getToolCollection--");
        logger.info(msgHeader.toString());
        logger.info(msgBody.toString());

        String sDateTime = msgBody.getStartTime();
        String eDateTime = msgBody.getEndTime();
        String operator = msgBody.getAccountCode();
        List<WmsToolUseResult> wmsToolUseResultList;
        WmsToolUseParam wmsToolUseParam = new WmsToolUseParam();
        if (StringUtils.isNotBlank(sDateTime) &&StringUtils.isNotBlank(eDateTime)) {
            wmsToolUseParam.setOperator(sDateTime);
            wmsToolUseParam.setMaterialTypeId(eDateTime);
            wmsToolUseResultList = this.wmsToolUseService.findListBySpecC(wmsToolUseParam);
        } else if (StringUtils.isNotBlank(sDateTime)) {
            wmsToolUseParam.setOperator(sDateTime);
            wmsToolUseResultList = this.wmsToolUseService.findListBySpecA(wmsToolUseParam);
        } else if (StringUtils.isNotBlank(eDateTime)) {
            wmsToolUseParam.setOperator(eDateTime);
            wmsToolUseResultList = this.wmsToolUseService.findListBySpecB(wmsToolUseParam);
        } else {
            wmsToolUseResultList = this.wmsToolUseService.findListBySpec(new WmsToolUseParam());
        }

        if (StringUtils.isNotBlank(operator)) {
            if (wmsToolUseResultList != null && !wmsToolUseResultList.isEmpty()) {
                List<WmsToolUseResult> wmsToolUseResultList1 = new ArrayList<>();
                for (WmsToolUseResult wmsToolUseResult : wmsToolUseResultList) {
                    if (wmsToolUseResult.getOperator().equals(operator)) {
                        wmsToolUseResultList1.add(wmsToolUseResult);
                    }
                }
                wmsToolUseResultList = wmsToolUseResultList1;
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<ToolCollection> toolCollections = new ArrayList<>();
        Map<String, ToolCollection> toolCollectionMap = new HashMap<>();
        for (WmsToolUseResult wmsToolUseResult : wmsToolUseResultList) {
            ToolCollection toolCollection = toolCollectionMap.get(wmsToolUseResult.getOperator());
            ToolCollectionInfo toolCollectionInfo = new ToolCollectionInfo();
            toolCollectionInfo.setTool_No(wmsToolUseResult.getMaterialSku());
            toolCollectionInfo.setTool_Name(wmsToolUseResult.getMaterialName());
            toolCollectionInfo.setNum("1");
            toolCollectionInfo.setTime(sdf.format(wmsToolUseResult.getDataTime()));

            if (toolCollection != null) {
                List<ToolCollectionInfo> toolCollectionInfoList = toolCollection.getToolCollectionInfos();
                toolCollectionInfoList.add(toolCollectionInfo);
            } else {
                toolCollection = new ToolCollection();
                List<ToolCollectionInfo> toolCollectionInfoList = new ArrayList<>();
                toolCollectionInfoList.add(toolCollectionInfo);
                toolCollection.setToolCollectionInfos(toolCollectionInfoList);
                toolCollection.setEmployee_NO(wmsToolUseResult.getOperator());
                toolCollection.setEmployee_Name(wmsToolUseResult.getOperator());
                toolCollectionMap.put(wmsToolUseResult.getOperator(), toolCollection);
            }
        }

        for (Map.Entry<String, ToolCollection> entry : toolCollectionMap.entrySet()) {
            toolCollections.add(entry.getValue());
        }

        RsMomBody rsBody = new RsMomBody();
        RsMsgHeader rsMsgHeader = new RsMsgHeader();
        rsMsgHeader.setMessageID(msgHeader.getMessageID());
        rsMsgHeader.setInterfaceID(msgHeader.getInterfaceID());
        rsMsgHeader.setTransID(msgHeader.getTransID());
        rsMsgHeader.setResultType("0");
        rsMsgHeader.setResultCode("");
        rsMsgHeader.setResultMessage("接收成功");
        rsMsgHeader.setCount(String.valueOf(wmsToolUseResultList.size()));
        rsMsgHeader.setComment(msgHeader.getComment());
        rsBody.setMsgHeader(rsMsgHeader);
        RsMsgMomBody rsMsgMomBody = new RsMsgMomBody();
        rsMsgMomBody.setToolCollections(toolCollections);
        rsBody.setMsgBody(rsMsgMomBody);

        logger.info("--OVER--");
        return rsBody;
    }

    @Override
    public RsBody getPurchaseorderDelivery(MsgHeader msgHeader, MsgBodyDelivery msgBody) throws ParseException {

//        WmsWarehousePurchaseorderDeliveryResult wmsWarehousea= wmsWarehousePurchaseorderDeliveryService.selectPurDocNo("PO230414000001","21501");
        logger.info("--getPurchaseorderDelivery--");
        logger.info(msgHeader.toString());
        logger.info(msgBody.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<FawmallPurchaseorderCancelResult> fawPurchaseorderyResultList = this.fawmallPurchaseorderCancelService.findListBySpec(new FawmallPurchaseorderCancelParam());

        List<YQJFDeliveryOrder> yqjfdeliverorder = msgBody.getYqjfdeliverorder();
        //新增集合
        List<FawmallPurchaseorderDeliveryParam> fawmallPurchaseorderDeliveryParams = new ArrayList<>();
        //更新集合
        List<FawmallPurchaseorderDeliveryParam> fawmallPurchaseorderDeliveryParamsUp = new ArrayList<>();

//        List<WmsPurchaseOrderInfoParam> wmsPurchaseOrderInfoParamList = new ArrayList<>();
        if (yqjfdeliverorder != null && !yqjfdeliverorder.isEmpty()) {
            for (YQJFDeliveryOrder deliverorder : yqjfdeliverorder) {
                FawmallPurchaseorderDeliveryParam fawmallPurchaseorderDeliveryParam = new FawmallPurchaseorderDeliveryParam();
                fawmallPurchaseorderDeliveryParam.setPurdocno(nullSetValue(deliverorder.getPurDocNo()));
                fawmallPurchaseorderDeliveryParam.setItemno(nullSetValue(deliverorder.getItemNo()));
                fawmallPurchaseorderDeliveryParam.setMtlno(nullSetValue(deliverorder.getMtlNo()));
                fawmallPurchaseorderDeliveryParam.setQty(nullSetValue(deliverorder.getQTY()));
                fawmallPurchaseorderDeliveryParam.setCode(nullSetValue(deliverorder.getCODE()));
                if (deliverorder.getSENDTIME() != null) {
                    fawmallPurchaseorderDeliveryParam.setSendtime(deliverorder.getSENDTIME());
                }
                if (deliverorder.getEXPECTEDRECEIVETIME() != null) {
                    fawmallPurchaseorderDeliveryParam.setExpectedreceivetime(deliverorder.getEXPECTEDRECEIVETIME());
                }
                fawmallPurchaseorderDeliveryParam.setLinecode(nullSetValue(deliverorder.getLINECODE()));
                fawmallPurchaseorderDeliveryParam.setStatus(nullSetValue(deliverorder.getSTATUS()));
                boolean dataKg = true;

                if (dataKg) {
                    fawmallPurchaseorderDeliveryParams.add(fawmallPurchaseorderDeliveryParam);
                } else {
                    fawmallPurchaseorderDeliveryParamsUp.add(fawmallPurchaseorderDeliveryParam);
                }
            }
        }
//        王盼宇修改于20230519，修改内容：根据发货单创建采购订单，不对之前的采购订单做修改
        List<WmsPurchaseOrderInfoParam> wmsPurchaseOrderInfoParamList = new ArrayList<>();

        if (!fawmallPurchaseorderDeliveryParams.isEmpty()) {
            this.fawmallPurchaseorderDeliveryService.insertListBatch(fawmallPurchaseorderDeliveryParams);
            List<WmsWarehousePurchaseorderDeliveryParam> wmsWarehousePurchaseorderParamList = new ArrayList<>();
            for (FawmallPurchaseorderDeliveryParam fawPurchaseorderParam : fawmallPurchaseorderDeliveryParams) {
                WmsWarehousePurchaseorderDeliveryParam wmsWarehousePurchaseorderDeliveryParam = new WmsWarehousePurchaseorderDeliveryParam();
                wmsWarehousePurchaseorderDeliveryParam.setPurdocno(fawPurchaseorderParam.getPurdocno());
                wmsWarehousePurchaseorderDeliveryParam.setItemno(fawPurchaseorderParam.getItemno());
                wmsWarehousePurchaseorderDeliveryParam.setMtlno(fawPurchaseorderParam.getMtlno());
                wmsWarehousePurchaseorderDeliveryParam.setQty(fawPurchaseorderParam.getQty());
                wmsWarehousePurchaseorderDeliveryParam.setCode(fawPurchaseorderParam.getCode());
                Date date = new Date();
                date=sdf.parse(fawPurchaseorderParam.getSendtime());
                wmsWarehousePurchaseorderDeliveryParam.setSendtime(sdf.parse(fawPurchaseorderParam.getSendtime()));
                wmsWarehousePurchaseorderDeliveryParam.setExpectedreceivetime(sdf.parse(fawPurchaseorderParam.getExpectedreceivetime()));
                wmsWarehousePurchaseorderDeliveryParam.setLinecode(fawPurchaseorderParam.getLinecode());
                wmsWarehousePurchaseorderDeliveryParam.setStatus(fawPurchaseorderParam.getStatus());
                wmsWarehousePurchaseorderDeliveryParam.setCreateTime(new Date());
                WmsPurchaseOrderInfoResult wmsPurchaseOrder= wmsPurchaseOrderInfoService.selectPurdocno(fawPurchaseorderParam.getPurdocno(),fawPurchaseorderParam.getItemno());
                WmsMaterialTypeResult wmsMaterialTypeResult= wmsMaterialTypeService.findByMaterialSku(fawPurchaseorderParam.getMtlno());
//                if (wmsPurchaseOrder!=null){
                        String string="";
                        WmsPurchaseOrderInfoParam wmsWarehouse= new WmsPurchaseOrderInfoParam();
//                        wmsWarehouse.setId(wmsPurchaseOrder.getId());
                        wmsWarehouse.setClient(nullSetValue("900"));
                        wmsWarehouse.setPurdocitemno(nullSetValue(fawPurchaseorderParam.getLinecode()));
                        wmsWarehouse.setPurdocno(nullSetValue(fawPurchaseorderParam.getPurdocno()));
                        wmsWarehouse.setPurchasereqno(nullSetValue(fawPurchaseorderParam.getCode()));
                        wmsWarehouse.setItemno(nullSetValue(fawPurchaseorderParam.getItemno()));
                        wmsWarehouse.setPurstockbillid(nullSetValue(fawPurchaseorderParam.getLinecode()));
                        wmsWarehouse.setBuyliststrdes(nullSetValue("商城采购订单"));
                        wmsWarehouse.setCreatedby("商城订单");
                        wmsWarehouse.setCreateddate(sdf.parse(fawPurchaseorderParam.getSendtime()));
                        wmsWarehouse.setDocDate(sdf.parse(fawPurchaseorderParam.getSendtime()));
                        wmsWarehouse.setEstimatedpriceindic("");
                        wmsWarehouse.setMatBrand("");
                        wmsWarehouse.setNetvalue("");
                        wmsWarehouse.setOrdpriceunit("");
                        wmsWarehouse.setOrdtype("");
                        wmsWarehouse.setPrice("");
                        wmsWarehouse.setPromotion("");
                        wmsWarehouse.setProposerdesc("");
                        wmsWarehouse.setProposerid("");
                        wmsWarehouse.setPurgrp("");
                        wmsWarehouse.setPurgrpdesc("");
                        wmsWarehouse.setPurOrg("");
                        wmsWarehouse.setPurOrg("");
                        wmsWarehouse.setPurdocheaderid("");
                        wmsWarehouse.setRemark("");
                        wmsWarehouse.setRemark1("");
                        wmsWarehouse.setStorelocation("R06B861");
                        wmsWarehouse.setVendordesc("");
                        wmsWarehouse.setPhone("");
                        wmsWarehouse.setReqphone("");
                        wmsWarehouse.setPlantdes("");
                        wmsWarehouse.setStorelocationdes("");
                        wmsWarehouse.setStorelocationdes("");
                        wmsWarehouse.setSizecol("");
                        wmsWarehouse.setUnitdes("");
                        wmsWarehouse.setUnitdes("");
                        wmsWarehouse.setCreatedby("");
                        wmsWarehouse.setStatedesc(nullSetValue(fawPurchaseorderParam.getStatus()));
                        wmsWarehouse.setDiOperType("");
                        wmsWarehouse.setDiUpdatetime(sdf.parse(nullSetValue(fawPurchaseorderParam.getSendtime())));
                        wmsWarehouse.setVendorno("");
                        wmsWarehouse.setPurNumber(nullSetValue(fawPurchaseorderParam.getItemno()));
                        wmsWarehouse.setMNumber(nullSetValue(fawPurchaseorderParam.getQty()));
                        wmsWarehouse.setArrivalTime(sdf.parse(fawPurchaseorderParam.getExpectedreceivetime()));
                        wmsWarehouse.setArrivalState("0");
                        wmsWarehouse.setCreateTime(sdf.parse(fawPurchaseorderParam.getSendtime()));
                        wmsWarehouse.setReceivedQuantity("0");
                        wmsWarehouse.setPrintNum("0");
                        wmsWarehouse.setAcceptableQuantity(nullSetValue(fawPurchaseorderParam.getQty()));
                        wmsWarehouse.setMaterialSku(nullSetValue(fawPurchaseorderParam.getMtlno()));
                        if (wmsMaterialTypeResult!=null)
                        {
                            wmsWarehouse.setMaterialTypeId(wmsMaterialTypeResult.getId().toString());
                            wmsWarehouse.setType(wmsMaterialTypeResult.getType());
                            wmsWarehouse.setMaterialType(wmsMaterialTypeResult.getMaterialType());
                            wmsWarehouse.setMaterialName(wmsMaterialTypeResult.getMaterialName());
                            wmsWarehouse.setMUnit(wmsMaterialTypeResult.getMUnit());
                            wmsWarehouse.setPlant(wmsMaterialTypeResult.getPlant());
                        }else {
                            wmsWarehouse.setMaterialTypeId("");
                            wmsWarehouse.setType("");
                            wmsWarehouse.setMaterialType("");
                            wmsWarehouse.setMaterialName("");
                            wmsWarehouse.setMUnit("");
                            wmsWarehouse.setPlant("");
                        }
                    wmsPurchaseOrderInfoParamList.add(wmsWarehouse);

//                        wmsPurchaseOrderInfoService.updatePurdocno(wmsWarehouse.getPurdocno(),wmsWarehouse.getItemno(), wmsWarehouse.getMaterialSku(),
//                                wmsWarehouse.getAcceptableQuantity(),wmsWarehouse.getArrivalTime(),wmsWarehouse.getPurchasereqno(),wmsWarehouse.getDiUpdatetime(),
//                                wmsWarehouse.getPurstockbillid(),wmsWarehouse.getStatedesc(),wmsWarehouse.getArrivalState(),wmsWarehouse.getCreatedby(),
//                                wmsWarehouse.getMaterialTypeId(),wmsWarehouse.getType(),wmsWarehouse.getMaterialType(),wmsWarehouse.getMaterialName(),
//                                wmsWarehouse.getMUnit());
//                        wmsPurchaseOrderInfoService.update(wmsWarehouse);
//                    wmsPurchaseOrderInfoService.updatePurdocno(
//                            wmsWarehouse.getClient(),wmsWarehouse.getPurdocitemno(),wmsWarehouse.getBuyliststrdes(),
//                            wmsWarehouse.getStorelocation(),wmsWarehouse.getMNumber(),
//                            wmsWarehouse.getPurdocno(),wmsWarehouse.getItemno(),
//                            wmsWarehouse.getMaterialSku(),wmsWarehouse.getAcceptableQuantity(),wmsWarehouse.getArrivalTime(),
//                            wmsWarehouse.getPurchasereqno(),wmsWarehouse.getDiUpdatetime(),wmsWarehouse.getPurstockbillid(),
//                            wmsWarehouse.getStatedesc(),wmsWarehouse.getArrivalState(),wmsWarehouse.getCreatedby(),
//                            wmsWarehouse.getMaterialTypeId(),wmsWarehouse.getType(),wmsWarehouse.getMaterialType(),
//                            wmsWarehouse.getMaterialName(),wmsWarehouse.getMUnit(),wmsWarehouse.getPurNumber());
//                }

                wmsWarehousePurchaseorderParamList.add(wmsWarehousePurchaseorderDeliveryParam);
            }
            this.wmsPurchaseOrderInfoService.insertListBatch(wmsPurchaseOrderInfoParamList);
            this.wmsWarehousePurchaseorderDeliveryService.insertList(wmsWarehousePurchaseorderParamList);
        }

        if (!fawmallPurchaseorderDeliveryParamsUp.isEmpty()) {
            for (FawmallPurchaseorderDeliveryParam fawmallPurchaseorderDeliveryParam : fawmallPurchaseorderDeliveryParamsUp) {
                this.fawmallPurchaseorderDeliveryService.update(fawmallPurchaseorderDeliveryParam);
            }
        }

        RsBody rsBody = new RsBody();
        RsMsgHeader rsMsgHeader = new RsMsgHeader();
        rsMsgHeader.setResultType("0");
        rsMsgHeader.setResultCode("");
        rsMsgHeader.setResultMessage("接收成功");
        rsBody.setMsgHeader(rsMsgHeader);

        logger.info("--OVER--");
        return rsBody;
    }

    @Override
    public RsBody getPurchaseorderCancel(MsgHeader msgHeader, MsgBodyCancel msgBody) {
        logger.info("--getPurchaseorderCancel--");
        logger.info(msgHeader.toString());
        logger.info(msgBody.toString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<FawmallPurchaseorderCancelResult> fawPurchaseorderyResultList = this.fawmallPurchaseorderCancelService.findListBySpec(new FawmallPurchaseorderCancelParam());

        List<YQJFCancelOrder> yqjfCancelorder = msgBody.getYqjfCancelorder();
        //新增集合
        List<FawmallPurchaseorderCancelParam> fawmallPurchaseorderCancelParams = new ArrayList<>();
//新增集合
        List<WmsWarehousePurchaseorderCancelParam> wmsWarehousePurchaseorderCancelParams = new ArrayList<>();

        List<WmsPurchaseOrderInfoParam> wmsPurchaseOrderInfoParamList = new ArrayList<>();
        //更新集合
        List<FawmallPurchaseorderCancelParam> fawmallPurchaseorderCancelParamsUp = new ArrayList<>();


        if (yqjfCancelorder != null && !yqjfCancelorder.isEmpty()) {
            for (YQJFCancelOrder cancelorder : yqjfCancelorder) {
                FawmallPurchaseorderCancelParam fawmallPurchaseorderCancelParam = new FawmallPurchaseorderCancelParam();
                fawmallPurchaseorderCancelParam.setPurdocno(nullSetValue(cancelorder.getPurDocNo()));
                fawmallPurchaseorderCancelParam.setItemno(nullSetValue(cancelorder.getItemNo()));
                fawmallPurchaseorderCancelParam.setStatus(nullSetValue(cancelorder.getSTATUS()));

                WmsWarehousePurchaseorderCancelParam wmsWarehousePurchaseorderCancelParam=new WmsWarehousePurchaseorderCancelParam();
                wmsWarehousePurchaseorderCancelParam.setPurdocno(nullSetValue(cancelorder.getPurDocNo()));
                wmsWarehousePurchaseorderCancelParam.setItemno(nullSetValue(cancelorder.getItemNo()));
                wmsWarehousePurchaseorderCancelParam.setStatus(nullSetValue(cancelorder.getSTATUS()));
                wmsWarehousePurchaseorderCancelParam.setCreateTime(new Date());
                boolean dataKg = true;

                if (dataKg) {
                    fawmallPurchaseorderCancelParams.add(fawmallPurchaseorderCancelParam);
                    wmsWarehousePurchaseorderCancelParams.add(wmsWarehousePurchaseorderCancelParam);
                } else {
                    fawmallPurchaseorderCancelParamsUp.add(fawmallPurchaseorderCancelParam);
                }
            }
        }

        if (!fawmallPurchaseorderCancelParams.isEmpty()) {
            this.fawmallPurchaseorderCancelService.insertListBatch(fawmallPurchaseorderCancelParams);
            this.wmsWarehousePurchaseorderCancelService.insertList(wmsWarehousePurchaseorderCancelParams);
            List<WmsWarehousePurchaseorderCancelParam> wmsWarehousePurchaseorderParamList = new ArrayList<>();
            for (FawmallPurchaseorderCancelParam fawPurchaseorderParam : fawmallPurchaseorderCancelParams) {
                WmsWarehousePurchaseorderCancelParam wmsWarehousePurchaseorderCancelParam = new WmsWarehousePurchaseorderCancelParam();
                wmsWarehousePurchaseorderCancelParam.setPurdocno(fawPurchaseorderParam.getPurdocno());
                wmsWarehousePurchaseorderCancelParam.setItemno(fawPurchaseorderParam.getItemno());
                wmsWarehousePurchaseorderCancelParam.setStatus(fawPurchaseorderParam.getStatus());
                wmsWarehousePurchaseorderCancelParam.setCreateTime(new Date());
                WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParamModel = new WmsPurchaseOrderInfoParam();
                if (Objects.equals(fawPurchaseorderParam.getStatus(), "I"))
                {
                    wmsPurchaseOrderInfoParamModel.setPurdocno(fawPurchaseorderParam.getPurdocno());
                    wmsPurchaseOrderInfoParamModel.setItemno(fawPurchaseorderParam.getItemno());
                    wmsPurchaseOrderInfoParamModel.setArrivalState("5");
                    wmsPurchaseOrderInfoParamModel.setCreateddate(new Date());
                    wmsPurchaseOrderInfoParamModel.setDocDate(new Date());
                    wmsPurchaseOrderInfoParamModel.setDiUpdatetime(new Date());
                    wmsPurchaseOrderInfoParamModel.setArrivalTime(new Date());
                    wmsPurchaseOrderInfoParamModel.setCreateddate(new Date());
                    wmsPurchaseOrderInfoParamList.add(wmsPurchaseOrderInfoParamModel);

                }
                if (Objects.equals(fawPurchaseorderParam.getStatus(), "D"))
                {
                    wmsPurchaseOrderInfoParamModel.setPurdocno(fawPurchaseorderParam.getPurdocno());
                    wmsPurchaseOrderInfoParamModel.setItemno(fawPurchaseorderParam.getItemno());
                    wmsPurchaseOrderInfoParamModel.setArrivalState("4");
                    wmsPurchaseOrderInfoParamModel.setCreateddate(new Date());
                    wmsPurchaseOrderInfoParamModel.setDocDate(new Date());
                    wmsPurchaseOrderInfoParamModel.setDiUpdatetime(new Date());
                    wmsPurchaseOrderInfoParamModel.setArrivalTime(new Date());
                    wmsPurchaseOrderInfoParamModel.setCreateddate(new Date());
//                    wmsPurchaseOrderInfoParamList.add(wmsPurchaseOrderInfoParamModel);
                    wmsPurchaseOrderInfoService.update(wmsPurchaseOrderInfoParamModel);
                }
                if(Objects.equals(fawPurchaseorderParam.getStatus(), "U")) {
                    wmsPurchaseOrderInfoParamModel.setPurdocno(fawPurchaseorderParam.getPurdocno());
                    wmsPurchaseOrderInfoParamModel.setItemno(fawPurchaseorderParam.getItemno());
                    wmsPurchaseOrderInfoParamModel.setCreateddate(new Date());
                    wmsPurchaseOrderInfoParamModel.setDocDate(new Date());
                    wmsPurchaseOrderInfoParamModel.setDiUpdatetime(new Date());
                    wmsPurchaseOrderInfoParamModel.setArrivalTime(new Date());
                    wmsPurchaseOrderInfoParamModel.setCreateddate(new Date());
                    wmsPurchaseOrderInfoService.update(wmsPurchaseOrderInfoParamModel);
                }
            }
                wmsPurchaseOrderInfoService.insertListBatch(wmsPurchaseOrderInfoParamList);
        }


        if (!fawmallPurchaseorderCancelParamsUp.isEmpty()) {
            for (FawmallPurchaseorderCancelParam fawmallPurchaseorderDeliveryParam : fawmallPurchaseorderCancelParamsUp) {
                this.fawmallPurchaseorderCancelService.update(fawmallPurchaseorderDeliveryParam);
            }
        }

        RsBody rsBody = new RsBody();
        RsMsgHeader rsMsgHeader = new RsMsgHeader();
        rsMsgHeader.setResultType("0");
        rsMsgHeader.setResultCode("");
        rsMsgHeader.setResultMessage("接收成功");
        rsBody.setMsgHeader(rsMsgHeader);

        logger.info("--OVER--");
        return rsBody;
    }

    private String nullSetValue(String ds) {
        String rs = "";
        if (ds != null && !"".equals(ds)) {
            rs=ds;
        }
        return rs;
    }

}
