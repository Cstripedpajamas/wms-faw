package cn.stylefeng.guns.webservice;

import cn.stylefeng.guns.config.AppConfig;
import cn.stylefeng.guns.modular.base.materialType.model.params.WmsMaterialTypeParam;
import cn.stylefeng.guns.modular.base.materialType.service.WmsMaterialTypeService;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.model.params.WmsPurchaseOrderInfoParam;
import cn.stylefeng.guns.modular.base.purchaseorderinfo.service.WmsPurchaseOrderInfoService;
import cn.stylefeng.guns.modular.base.user.model.params.WmsUserParam;
import cn.stylefeng.guns.modular.base.user.service.WmsUserService;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.params.FawMtlInfoParam;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.model.result.FawMtlInfoResult;
import cn.stylefeng.guns.modular.fawInfo.mtlInfo.service.FawMtlInfoService;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.params.FawPurchaseOrderParam;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.model.result.FawPurchaseOrderResult;
import cn.stylefeng.guns.modular.fawInfo.purchaseOrder.service.FawPurchaseOrderService;
import cn.stylefeng.guns.modular.fawInfo.userInfo.entity.FawUserInfo;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.params.FawUserInfoParam;
import cn.stylefeng.guns.modular.fawInfo.userInfo.model.result.FawUserInfoResult;
import cn.stylefeng.guns.modular.fawInfo.userInfo.service.FawUserInfoService;
import cn.stylefeng.guns.modular.statistics.tooluse.entity.WmsToolUse;
import cn.stylefeng.guns.modular.statistics.tooluse.model.params.WmsToolUseParam;
import cn.stylefeng.guns.modular.statistics.tooluse.model.result.WmsToolUseResult;
import cn.stylefeng.guns.modular.statistics.tooluse.service.WmsToolUseService;
import cn.stylefeng.guns.webservice.entity.*;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.api.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private WmsUserService wmsUserService;

    @Autowired
    private FawPurchaseOrderService fawPurchaseOrderService;

    @Autowired
    private WmsPurchaseOrderInfoService wmsPurchaseOrderInfoService;

    @Autowired
    private FawMtlInfoService fawMtlInfoService;

    @Autowired
    private WmsMaterialTypeService wmsMaterialTypeService;

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
                wmsUserParam.setSerialNumber(fawUserInfoParam.getAccountCode());
                wmsUserParam.setUserName(fawUserInfoParam.getEmployeeName());
                wmsUserParam.setDataState("0");
                wmsUserParams.add(wmsUserParam);
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<FawPurchaseOrderResult> fawPurchaseOrderResultList = this.fawPurchaseOrderService.findListBySpec(new FawPurchaseOrderParam());

        List<YQJFPurchaseOrderInfo> yqjfPurchaseOrderInfo = msgBody.getYqjfPurchaseOrderInfo();
        //新增集合
        List<FawPurchaseOrderParam> fawPurchaseOrderParamArrayList = new ArrayList<>();
        //更新集合
        List<FawPurchaseOrderParam> fawPurchaseOrderParamArrayListUp = new ArrayList<>();

        if (yqjfPurchaseOrderInfo != null && !yqjfPurchaseOrderInfo.isEmpty()) {
            for (YQJFPurchaseOrderInfo purchaseOrderInfo : yqjfPurchaseOrderInfo) {
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

        if (!fawPurchaseOrderParamArrayList.isEmpty()) {
            this.fawPurchaseOrderService.insertListBatch(fawPurchaseOrderParamArrayList);
            List<WmsPurchaseOrderInfoParam> wmsPurchaseOrderInfoParams = new ArrayList<>();
            for (FawPurchaseOrderParam fawPurchaseOrderParam : fawPurchaseOrderParamArrayList) {
                WmsPurchaseOrderInfoParam wmsPurchaseOrderInfoParam = new WmsPurchaseOrderInfoParam();
                wmsPurchaseOrderInfoParam.setPurNumber(fawPurchaseOrderParam.getPurdocitemId());
                wmsPurchaseOrderInfoParam.setType("");
                wmsPurchaseOrderInfoParam.setMaterialTypeId("");
                wmsPurchaseOrderInfoParam.setMaterialType("");
                wmsPurchaseOrderInfoParam.setMaterialName("");
                wmsPurchaseOrderInfoParam.setMaterialSku(fawPurchaseOrderParam.getMtlNo());
                wmsPurchaseOrderInfoParam.setMUnit("");
                wmsPurchaseOrderInfoParam.setMNumber(fawPurchaseOrderParam.getQuantity());
                wmsPurchaseOrderInfoParam.setArrivalTime(fawPurchaseOrderParam.getItemDeliveryDate());
                wmsPurchaseOrderInfoParam.setArrivalState("0");
                wmsPurchaseOrderInfoParam.setReceivedQuantity("0");
                wmsPurchaseOrderInfoParam.setPrintNum("0");
                wmsPurchaseOrderInfoParam.setAcceptableQuantity(fawPurchaseOrderParam.getMtlNo());
                wmsPurchaseOrderInfoParams.add(wmsPurchaseOrderInfoParam);
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
                wmsMaterialTypeParam.setType("2");
                wmsMaterialTypeParam.setMaterialType(fawMtlInfoParam.getMtlType());
                wmsMaterialTypeParam.setMaterialName(fawMtlInfoParam.getMtlNodes());
                wmsMaterialTypeParam.setMaterialSku(fawMtlInfoParam.getMtlNo());
                wmsMaterialTypeParam.setMUnit(fawMtlInfoParam.getMeasurebaseunit());
                wmsMaterialTypeParam.setLatticeMouthType("");
                wmsMaterialTypeParam.setDataState("1");
                wmsMaterialTypeParam.setSortType("0");
                wmsMaterialTypeParam.setPackageType("");
                wmsMaterialTypeParam.setPackageNumber("0");
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
        if (sDateTime != null && eDateTime != null) {
            wmsToolUseParam.setOperator(sDateTime);
            wmsToolUseParam.setMaterialTypeId(eDateTime);
            wmsToolUseResultList = this.wmsToolUseService.findListBySpecC(wmsToolUseParam);
        } else if (sDateTime != null) {
            wmsToolUseParam.setOperator(sDateTime);
            wmsToolUseResultList = this.wmsToolUseService.findListBySpecA(wmsToolUseParam);
        } else if (eDateTime != null) {
            wmsToolUseParam.setOperator(eDateTime);
            wmsToolUseResultList = this.wmsToolUseService.findListBySpecB(wmsToolUseParam);
        } else {
            wmsToolUseResultList = this.wmsToolUseService.findListBySpec(new WmsToolUseParam());
        }

        if (operator != null) {
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

    private String nullSetValue(String ds) {
        String rs = "";
        if (ds != null && !"".equals(ds)) {
            rs=ds;
        }
        return rs;
    }

}
