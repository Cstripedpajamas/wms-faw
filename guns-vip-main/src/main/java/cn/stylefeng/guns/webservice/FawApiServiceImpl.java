package cn.stylefeng.guns.webservice;

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
        List<FawUserInfoResult> fawUserInfoResults = this.fawUserInfoService.findListBySpec(new FawUserInfoParam());

        List<YQJFUserInfo> yqjfUserInfo = msgBody.getYqjfUserInfo();
        //新增集合
        List<FawUserInfoParam> fawUserInfoParamList = new ArrayList<>();
        //更新集合
        List<FawUserInfoParam> fawUserInfoParamListUp = new ArrayList<>();

        if (!yqjfUserInfo.isEmpty()) {
            for (YQJFUserInfo userInfo : yqjfUserInfo) {
                FawUserInfoParam fawUserInfoParam = new FawUserInfoParam();
                fawUserInfoParam.setAccountCode(userInfo.getAccountCode());
                fawUserInfoParam.setEmployeeId(userInfo.getEmployeeId());
                fawUserInfoParam.setDepNo(userInfo.getDepNo());
                fawUserInfoParam.setEmployeeName(userInfo.getEmployeeName());
                fawUserInfoParam.setEmailAddress(userInfo.getEmailAddress());
                fawUserInfoParam.setClassOfPositions(userInfo.getClassOfPositions());
                fawUserInfoParam.setFawClaOfPos(userInfo.getFawClaOfPos());
                fawUserInfoParam.setJobs(userInfo.getJobs());
                fawUserInfoParam.setDirectorId(userInfo.getDirectorId());
                fawUserInfoParam.setDirectorName(userInfo.getDirectorName());
                fawUserInfoParam.setObjectStatus(userInfo.getObjectStatus());
                fawUserInfoParam.setMdmType(userInfo.getMdmType());
                fawUserInfoParam.setDeptLevel(userInfo.getDeptLevel());
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
        rsMsgHeader.setMessageID(msgHeader.getMessageID());
        rsMsgHeader.setInterfaceID(msgHeader.getInterfaceID());
        rsMsgHeader.setTransID(msgHeader.getTransID());
        rsMsgHeader.setResultType("0");
        rsMsgHeader.setResultCode("");
        rsMsgHeader.setResultMessage("接收成功");
        rsMsgHeader.setCount(msgHeader.getCount());
        rsMsgHeader.setComment(msgHeader.getComment());
        RsMsgBody rsMsgBody = new RsMsgBody();
        rsMsgBody.setCode("0");
        rsMsgBody.setBusinessCode("");
        rsMsgBody.setMessage("");
        rsBody.setMsgBody(rsMsgBody);
        rsBody.setMsgHeader(rsMsgHeader);
        return rsBody;
    }

    @Override
    public RsErpBody setPurchaseOrder(MsgHeader msgHeader, MsgBodyErp msgBody) {
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
                fawPurchaseOrderParam.setClient(purchaseOrderInfo.getClient());
                fawPurchaseOrderParam.setPurDocitemNo(purchaseOrderInfo.getPurDocItemNO());
                fawPurchaseOrderParam.setPurDocNo(purchaseOrderInfo.getPurDocNO());
                fawPurchaseOrderParam.setPurchaseReqNo(purchaseOrderInfo.getPurchaseReqNO());
                fawPurchaseOrderParam.setItemNo(purchaseOrderInfo.getItemNO());
                fawPurchaseOrderParam.setPurStockBillId(purchaseOrderInfo.getPURStockBillID());
                fawPurchaseOrderParam.setBuyListStrDes(purchaseOrderInfo.getBuyListStrDes());
                fawPurchaseOrderParam.setCreatedBy(purchaseOrderInfo.getCreatedBy());
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
                fawPurchaseOrderParam.setEstimatedPriceIndic(purchaseOrderInfo.getEstimatedPriceIndic());
                fawPurchaseOrderParam.setMatBrand(purchaseOrderInfo.getMatBrand());
                fawPurchaseOrderParam.setMtlNo(purchaseOrderInfo.getMtlNO());
                fawPurchaseOrderParam.setNetValue(purchaseOrderInfo.getNetValue());
                fawPurchaseOrderParam.setOrdPriceUnit(purchaseOrderInfo.getOrdPriceUnit());
                fawPurchaseOrderParam.setOrdType(purchaseOrderInfo.getOrdType());
                fawPurchaseOrderParam.setPlant(purchaseOrderInfo.getPlant());
                fawPurchaseOrderParam.setPrice(purchaseOrderInfo.getPrice());
                fawPurchaseOrderParam.setPromotion(purchaseOrderInfo.getPromotion());
                fawPurchaseOrderParam.setProposerDesc(purchaseOrderInfo.getProposerDesc());
                fawPurchaseOrderParam.setProposerId(purchaseOrderInfo.getProposerID());
                fawPurchaseOrderParam.setPurGrp(purchaseOrderInfo.getPurGrp());
                fawPurchaseOrderParam.setPurGrpDesc(purchaseOrderInfo.getPurGrpDesc());
                fawPurchaseOrderParam.setPurOrg(purchaseOrderInfo.getPurOrg());
                fawPurchaseOrderParam.setPurdocheaderId(purchaseOrderInfo.getPurdocheaderid());
                fawPurchaseOrderParam.setPurdocitemId(purchaseOrderInfo.getPurdocitemid());
                fawPurchaseOrderParam.setQuantity(purchaseOrderInfo.getQuantity());
                fawPurchaseOrderParam.setRemark(purchaseOrderInfo.getRemark());
                fawPurchaseOrderParam.setRemark1(purchaseOrderInfo.getRemark1());
                fawPurchaseOrderParam.setStoreLocation(purchaseOrderInfo.getStoreLocation());
                fawPurchaseOrderParam.setVendorDesc(purchaseOrderInfo.getVendorDesc());
                fawPurchaseOrderParam.setVendorNo(purchaseOrderInfo.getVendorNO());
                fawPurchaseOrderParam.setPhone(purchaseOrderInfo.getPhone());
                fawPurchaseOrderParam.setReqPhone(purchaseOrderInfo.getReqPhone());
                fawPurchaseOrderParam.setPlantDes(purchaseOrderInfo.getPlantDes());
                fawPurchaseOrderParam.setStoreLocationDes(purchaseOrderInfo.getStoreLocationDes());
                fawPurchaseOrderParam.setSizecoL(purchaseOrderInfo.getSizecoL());
                fawPurchaseOrderParam.setUnitDes(purchaseOrderInfo.getUnitDes());
                fawPurchaseOrderParam.setCreatedByDesc(purchaseOrderInfo.getCreatedByDesc());
                fawPurchaseOrderParam.setStateDesc(purchaseOrderInfo.getStateDesc());
                fawPurchaseOrderParam.setDiOpertype(purchaseOrderInfo.getDI_OPERTYPE());
                fawPurchaseOrderParam.setDiBatch(purchaseOrderInfo.getDI_BATCHNO());
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
        rsMsgHeader.setMessageID(msgHeader.getMessageID());
        rsMsgHeader.setInterfaceID(msgHeader.getInterfaceID());
        rsMsgHeader.setTransID(msgHeader.getTransID());
        rsMsgHeader.setResultType("0");
        rsMsgHeader.setResultCode("");
        rsMsgHeader.setResultMessage("接收成功");
        rsMsgHeader.setCount(msgHeader.getCount());
        rsMsgHeader.setComment(msgHeader.getComment());
        RsMsgErpBody rsMsgErpBody = new RsMsgErpBody();
        rsMsgErpBody.setMSG("");
        rsMsgErpBody.setMSGNO("");
        rsMsgErpBody.setRESULT("");
        rsErpBody.setMsgBody(rsMsgErpBody);
        rsErpBody.setMsgHeader(rsMsgHeader);
        return rsErpBody;
    }

    @Override
    public RsBody getMaterielInfos(MsgHeader msgHeader, MsgBodyByEsb msgBody) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<FawMtlInfoResult> fawMtlInfoResultList = this.fawMtlInfoService.findListBySpec(new FawMtlInfoParam());

        List<YQJFMaterialInfo> yqjfMaterialInfo = msgBody.getYqjfMaterialInfo();
        //新增集合
        List<FawMtlInfoParam> fawMtlInfoParams = new ArrayList<>();
        //更新集合
        List<FawMtlInfoParam> fawMtlInfoParamsUp = new ArrayList<>();

        if (yqjfMaterialInfo != null && !yqjfMaterialInfo.isEmpty()) {
            for (YQJFMaterialInfo materialInfo : yqjfMaterialInfo) {
                FawMtlInfoParam fawMtlInfoParam=new FawMtlInfoParam();
                fawMtlInfoParam.setDiOpertype(materialInfo.getDI_OPERTYPE());
                fawMtlInfoParam.setDiBatch(materialInfo.getDI_BATCHNO());
                if (materialInfo.getDI_UPDATETIME()!=null){
                    try {
                        fawMtlInfoParam.setDiUpdatetime(sdf.parse(materialInfo.getDI_UPDATETIME()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                fawMtlInfoParam.setPlant(materialInfo.getPLANT());
                fawMtlInfoParam.setMtlNo(materialInfo.getMTLNO());
                fawMtlInfoParam.setMtlNodes(materialInfo.getMTLNODES());
                fawMtlInfoParam.setMtlType(materialInfo.getMTLTYPE());
                fawMtlInfoParam.setDelflagforclientmtl(materialInfo.getDELFLAGFORCLIENTMTL());
                fawMtlInfoParam.setMeasurebaseunit(materialInfo.getMEASUREBASEUNIT());
                fawMtlInfoParam.setIndustrystnddes(materialInfo.getINDUSTRYSTNDDES());
                fawMtlInfoParam.setPagefromat(materialInfo.getPAGEFROMAT());
                fawMtlInfoParam.setIsconfflag(materialInfo.getISCONFFLAG());
                fawMtlInfoParam.setDatauser(materialInfo.getDATAUSER());
                fawMtlInfoParam.setPurgrp(materialInfo.getPURGRP());
                fawMtlInfoParam.setProcuretype(materialInfo.getPROCURETYPE());
                fawMtlInfoParam.setSpecprocuretype(materialInfo.getSPECPROCURETYPE());
                fawMtlInfoParam.setMrpcontroller(materialInfo.getMRPCONTROLLER());
                fawMtlInfoParam.setValctg(materialInfo.getVALCTG());
                fawMtlInfoParam.setCroplant(materialInfo.getCROPLANT());
                fawMtlInfoParam.setSizes(materialInfo.getSIZES());
                fawMtlInfoParam.setSpmtlstatus(materialInfo.getSPMTLSTATUS());
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
            List<WmsMaterialTypeParam> wmsMaterialTypeParamList=new ArrayList<>();
            for (FawMtlInfoParam fawMtlInfoParam : fawMtlInfoParams) {
                WmsMaterialTypeParam wmsMaterialTypeParam=new WmsMaterialTypeParam();
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
        rsMsgHeader.setMessageID(msgHeader.getMessageID());
        rsMsgHeader.setInterfaceID(msgHeader.getInterfaceID());
        rsMsgHeader.setTransID(msgHeader.getTransID());
        rsMsgHeader.setResultType("0");
        rsMsgHeader.setResultCode("");
        rsMsgHeader.setResultMessage("接收成功");
        rsMsgHeader.setCount(msgHeader.getCount());
        rsMsgHeader.setComment(msgHeader.getComment());
        rsBody.setMsgHeader(rsMsgHeader);
        return rsBody;
    }

    @Override
    public RsMomBody getToolCollection(MsgHeader msgHeader, MsgBodyByMom msgBody) {
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
        return rsBody;
    }

}
