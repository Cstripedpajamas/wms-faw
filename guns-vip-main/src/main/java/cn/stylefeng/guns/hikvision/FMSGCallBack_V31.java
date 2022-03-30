package cn.stylefeng.guns.hikvision;

import com.sun.jna.Pointer;

import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FMSGCallBack_V31
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2021/12/12 11:30
 * @Version 1.0
 **/
public class FMSGCallBack_V31 implements HCNetSDK.FMSGCallBack_V31 {

    @Override
    public boolean invoke(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
        List<String[]> list = AlarmDataHandle(lCommand, pAlarmer, pAlarmInfo, dwBufLen, pUser);
        if (!list.isEmpty()) {
            for (String[] strings : list) {
                System.out.println(Arrays.toString(strings));
            }
        }
        return true;
    }

    public List<String[]> AlarmDataHandle(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
        String sAlarmType = new String();
        List<String[]> list = new ArrayList<>();
        String[] newRow = new String[3];
        //报警时间
        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String[] sIP = new String[2];

        sAlarmType = new String("lCommand=0x") + Integer.toHexString(lCommand);
        //lCommand是传的报警类型
        switch (lCommand) {
            case HCNetSDK.COMM_ALARM_V40:
                HCNetSDK.NET_DVR_ALARMINFO_V40 struAlarmInfoV40 = new HCNetSDK.NET_DVR_ALARMINFO_V40();
                struAlarmInfoV40.write();
                Pointer pInfoV40 = struAlarmInfoV40.getPointer();
                pInfoV40.write(0, pAlarmInfo.getByteArray(0, struAlarmInfoV40.size()), 0, struAlarmInfoV40.size());
                struAlarmInfoV40.read();

                switch (struAlarmInfoV40.struAlarmFixedHeader.dwAlarmType) {
                    case 0:
                        struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.setType(HCNetSDK.struIOAlarm.class);
                        struAlarmInfoV40.read();
                        sAlarmType = sAlarmType + new String("：信号量报警") + "，" + "报警输入口：" + struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.struioAlarm.dwAlarmInputNo;
                        break;
                    case 1:
                        sAlarmType = sAlarmType + new String("：硬盘满");
                        break;
                    case 2:
                        sAlarmType = sAlarmType + new String("：信号丢失");
                        break;
                    case 3:
                        struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.setType(HCNetSDK.struAlarmChannel.class);
                        struAlarmInfoV40.read();
                        int iChanNum = struAlarmInfoV40.struAlarmFixedHeader.ustruAlarm.sstrualarmChannel.dwAlarmChanNum;
                        sAlarmType = sAlarmType + new String("：移动侦测") + "，" + "报警通道个数：" + iChanNum + "，" + "报警通道号：";

                        for (int i = 0; i < iChanNum; i++) {
                            byte[] byChannel = struAlarmInfoV40.pAlarmData.getByteArray(i * 4, 4);

                            int iChanneNo = 0;
                            for (int j = 0; j < 4; j++) {
                                int ioffset = j * 8;
                                int iByte = byChannel[j] & 0xff;
                                iChanneNo = iChanneNo + (iByte << ioffset);
                            }

                            sAlarmType = sAlarmType + "+ch[" + iChanneNo + "]";
                        }

                        break;
                    case 4:
                        sAlarmType = sAlarmType + new String("：硬盘未格式化");
                        break;
                    case 5:
                        sAlarmType = sAlarmType + new String("：读写硬盘出错");
                        break;
                    case 6:
                        sAlarmType = sAlarmType + new String("：遮挡报警");
                        break;
                    case 7:
                        sAlarmType = sAlarmType + new String("：制式不匹配");
                        break;
                    case 8:
                        sAlarmType = sAlarmType + new String("：非法访问");
                        break;
                }

                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                list.add(newRow);
                break;
            case HCNetSDK.COMM_ALARM_V30:
                HCNetSDK.NET_DVR_ALARMINFO_V30 strAlarmInfoV30 = new HCNetSDK.NET_DVR_ALARMINFO_V30();
                strAlarmInfoV30.write();
                Pointer pInfoV30 = strAlarmInfoV30.getPointer();
                pInfoV30.write(0, pAlarmInfo.getByteArray(0, strAlarmInfoV30.size()), 0, strAlarmInfoV30.size());
                strAlarmInfoV30.read();
                switch (strAlarmInfoV30.dwAlarmType) {
                    case 0:
                        sAlarmType = sAlarmType + new String("：信号量报警") + "，" + "报警输入口：" + (strAlarmInfoV30.dwAlarmInputNumber + 1);
                        break;
                    case 1:
                        sAlarmType = sAlarmType + new String("：硬盘满");
                        break;
                    case 2:
                        sAlarmType = sAlarmType + new String("：信号丢失");
                        break;
                    case 3:
                        sAlarmType = sAlarmType + new String("：移动侦测") + "，" + "报警通道：";
                        for (int i = 0; i < 64; i++) {
                            if (strAlarmInfoV30.byChannel[i] == 1) {
                                sAlarmType = sAlarmType + "ch" + (i + 1) + " ";
                            }
                        }
                        break;
                    case 4:
                        sAlarmType = sAlarmType + new String("：硬盘未格式化");
                        break;
                    case 5:
                        sAlarmType = sAlarmType + new String("：读写硬盘出错");
                        break;
                    case 6:
                        sAlarmType = sAlarmType + new String("：遮挡报警");
                        break;
                    case 7:
                        sAlarmType = sAlarmType + new String("：制式不匹配");
                        break;
                    case 8:
                        sAlarmType = sAlarmType + new String("：非法访问");
                        break;
                }
                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                list.add(newRow);
                break;
            case HCNetSDK.COMM_ALARM_RULE:
                break;
            case HCNetSDK.COMM_UPLOAD_PLATE_RESULT:
                break;
            case HCNetSDK.COMM_ITS_PLATE_RESULT:
                break;
            case HCNetSDK.COMM_ALARM_PDC:
                break;
            case HCNetSDK.COMM_ITS_PARK_VEHICLE:
                break;
            case HCNetSDK.COMM_ALARM_TFS:
                break;
            case HCNetSDK.COMM_ALARM_AID_V41:
                break;
            case HCNetSDK.COMM_ALARM_TPS_V41:
                break;
            case HCNetSDK.COMM_UPLOAD_FACESNAP_RESULT:
                //实时人脸抓拍上传
                HCNetSDK.NET_VCA_FACESNAP_RESULT strFaceSnapInfo = new HCNetSDK.NET_VCA_FACESNAP_RESULT();
                strFaceSnapInfo.write();
                Pointer pFaceSnapInfo = strFaceSnapInfo.getPointer();
                pFaceSnapInfo.write(0, pAlarmInfo.getByteArray(0, strFaceSnapInfo.size()), 0, strFaceSnapInfo.size());
                strFaceSnapInfo.read();
                sAlarmType = sAlarmType + "：人脸抓拍上传，人脸评分：" + strFaceSnapInfo.dwFaceScore + "，年龄段：" + strFaceSnapInfo.struFeature.byAgeGroup + "，性别：" + strFaceSnapInfo.struFeature.bySex;
                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(strFaceSnapInfo.struDevInfo.struDevIP.sIpV4).split("\0", 2);
                newRow[2] = sIP[0];
                list.add(newRow);
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); //设置日期格式
                String time = df.format(new Date()); // new Date()为获取当前系统时间
                //人脸图片写文件
                try {
                    FileOutputStream small = null;
                    try {
                        small = new FileOutputStream("D:\\Hikvision\\fawFace\\pic\\" + time + "small.jpg");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    FileOutputStream big = new FileOutputStream("D:\\Hikvision\\fawFace\\pic\\" + time + "big.jpg");

                    if (strFaceSnapInfo.dwFacePicLen > 0) {
                        try {
                            small.write(strFaceSnapInfo.pBuffer1.getByteArray(0, strFaceSnapInfo.dwFacePicLen), 0, strFaceSnapInfo.dwFacePicLen);
                            small.close();
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }

                    }
                    if (strFaceSnapInfo.dwFacePicLen > 0) {
                        try {
                            big.write(strFaceSnapInfo.pBuffer2.getByteArray(0, strFaceSnapInfo.dwBackgroundPicLen), 0, strFaceSnapInfo.dwBackgroundPicLen);
                            big.close();
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                }
                break;
            case HCNetSDK.COMM_SNAP_MATCH_ALARM:
                //人脸名单比对报警
                HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM strFaceSnapMatch = new HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM();
                strFaceSnapMatch.write();
                Pointer pFaceSnapMatch = strFaceSnapMatch.getPointer();
                pFaceSnapMatch.write(0, pAlarmInfo.getByteArray(0, strFaceSnapMatch.size()), 0, strFaceSnapMatch.size());
                strFaceSnapMatch.read();

                if ((strFaceSnapMatch.dwSnapPicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newName = sf.format(new Date());
                    FileOutputStream fout;
                    try {
                        String filename = "D:\\Hikvision\\fawFace\\pic\\" + newName + "_pSnapPicBuffer" + ".jpg";
                        fout = new FileOutputStream(filename);
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strFaceSnapMatch.pSnapPicBuffer.getByteBuffer(offset, strFaceSnapMatch.dwSnapPicLen);
                        byte[] bytes = new byte[strFaceSnapMatch.dwSnapPicLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        fout.write(bytes);
                        fout.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if ((strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newName = sf.format(new Date());
                    FileOutputStream fout;
                    try {
                        String filename = "D:\\Hikvision\\fawFace\\pic\\" + newName + "_struSnapInfo_pBuffer1" + ".jpg";
                        fout = new FileOutputStream(filename);
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strFaceSnapMatch.struSnapInfo.pBuffer1.getByteBuffer(offset, strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen);
                        byte[] bytes = new byte[strFaceSnapMatch.struSnapInfo.dwSnapFacePicLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        fout.write(bytes);
                        fout.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if ((strFaceSnapMatch.struBlockListInfo.dwBlockListPicLen > 0) && (strFaceSnapMatch.byPicTransType == 0)) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newName = sf.format(new Date());
                    FileOutputStream fout;
                    try {
                        String filename = "D:\\Hikvision\\fawFace\\pic\\" + newName + "_fSimilarity_" + strFaceSnapMatch.fSimilarity + "_struBlockListInfo_pBuffer1" + ".jpg";
                        fout = new FileOutputStream(filename);
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strFaceSnapMatch.struBlockListInfo.pBuffer1.getByteBuffer(offset, strFaceSnapMatch.struBlockListInfo.dwBlockListPicLen);
                        byte[] bytes = new byte[strFaceSnapMatch.struBlockListInfo.dwBlockListPicLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        fout.write(bytes);
                        fout.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                try {
                    sAlarmType = sAlarmType + "：人脸名单比对报警，相识度：" + strFaceSnapMatch.fSimilarity + "，名单姓名：" + new String(strFaceSnapMatch.struBlockListInfo.struBlockListInfo.struAttribute.byName, "GBK").trim() + "，\n名单证件信息：" + new String(strFaceSnapMatch.struBlockListInfo.struBlockListInfo.struAttribute.byCertificateNumber).trim();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                //获取人脸库ID
                byte[] FDIDbytes;
                if ((strFaceSnapMatch.struBlockListInfo.dwFDIDLen > 0) && (strFaceSnapMatch.struBlockListInfo.pFDID != null)) {
                    ByteBuffer FDIDbuffers = strFaceSnapMatch.struBlockListInfo.pFDID.getByteBuffer(0, strFaceSnapMatch.struBlockListInfo.dwFDIDLen);
                    FDIDbytes = new byte[strFaceSnapMatch.struBlockListInfo.dwFDIDLen];
                    FDIDbuffers.rewind();
                    FDIDbuffers.get(FDIDbytes);
                    sAlarmType = sAlarmType + "，人脸库ID:" + new String(FDIDbytes).trim();
                }
                //获取人脸图片ID
                byte[] PIDbytes;
                if ((strFaceSnapMatch.struBlockListInfo.dwPIDLen > 0) && (strFaceSnapMatch.struBlockListInfo.pPID != null)) {
                    ByteBuffer PIDbuffers = strFaceSnapMatch.struBlockListInfo.pPID.getByteBuffer(0, strFaceSnapMatch.struBlockListInfo.dwPIDLen);
                    PIDbytes = new byte[strFaceSnapMatch.struBlockListInfo.dwPIDLen];
                    PIDbuffers.rewind();
                    PIDbuffers.get(PIDbytes);
                    sAlarmType = sAlarmType + "，人脸图片ID:" + new String(PIDbytes).trim();
                }
                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                list.add(newRow);
                break;
            case HCNetSDK.COMM_ALARM_ACS: //门禁主机报警信息
                HCNetSDK.NET_DVR_ACS_ALARM_INFO strACSInfo = new HCNetSDK.NET_DVR_ACS_ALARM_INFO();
                strACSInfo.write();
                Pointer pACSInfo = strACSInfo.getPointer();
                pACSInfo.write(0, pAlarmInfo.getByteArray(0, strACSInfo.size()), 0, strACSInfo.size());
                strACSInfo.read();

                sAlarmType = sAlarmType + "：门禁主机报警信息，卡号：" + new String(strACSInfo.struAcsEventInfo.byCardNo).trim() + "，卡类型：" +
                        strACSInfo.struAcsEventInfo.byCardType + "，报警主类型：" + strACSInfo.dwMajor + "，报警次类型：" + strACSInfo.dwMinor;

                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                list.add(newRow);

                //人脸识别通过
                if (strACSInfo.dwMajor == 5 && strACSInfo.dwMinor == 75) {
                    System.out.println("-------人脸认证通过------");
                    System.out.println("事件流水号 "+strACSInfo.struAcsEventInfo.dwSerialNo);
                    System.out.println("卡号 " +new String(strACSInfo.struAcsEventInfo.byCardNo).trim() );
                }

                if (strACSInfo.dwPicDataLen > 0) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newName = sf.format(new Date());
                    FileOutputStream fout;
                    try {
                        String filename = "D:\\Hikvision\\fawFace\\pic\\" + new String(pAlarmer.sDeviceIP).trim() +
                                "_byCardNo[" + new String(strACSInfo.struAcsEventInfo.byCardNo).trim() +
                                "_" + newName + "_Acs.jpg";
                        fout = new FileOutputStream(filename);
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strACSInfo.pPicData.getByteBuffer(offset, strACSInfo.dwPicDataLen);
                        byte[] bytes = new byte[strACSInfo.dwPicDataLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        fout.write(bytes);
                        fout.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case HCNetSDK.COMM_ID_INFO_ALARM: //身份证信息
                HCNetSDK.NET_DVR_ID_CARD_INFO_ALARM strIDCardInfo = new HCNetSDK.NET_DVR_ID_CARD_INFO_ALARM();
                strIDCardInfo.write();
                Pointer pIDCardInfo = strIDCardInfo.getPointer();
                pIDCardInfo.write(0, pAlarmInfo.getByteArray(0, strIDCardInfo.size()), 0, strIDCardInfo.size());
                strIDCardInfo.read();

                sAlarmType = sAlarmType + "：门禁身份证刷卡信息，身份证号码：" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() + "，姓名：" +
                        new String(strIDCardInfo.struIDCardCfg.byName).trim() + "，报警主类型：" + strIDCardInfo.dwMajor + "，报警次类型：" + strIDCardInfo.dwMinor;

                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                list.add(newRow);

                //身份证图片
                if (strIDCardInfo.dwPicDataLen > 0) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newName = sf.format(new Date());
                    FileOutputStream fout;
                    try {
                        String filename = "D:\\Hikvision\\fawFace\\pic\\" + new String(pAlarmer.sDeviceIP).trim() +
                                "_byCardNo[" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() +
                                "_" + newName + "_IDInfoPic.jpg";
                        fout = new FileOutputStream(filename);
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strIDCardInfo.pPicData.getByteBuffer(offset, strIDCardInfo.dwPicDataLen);
                        byte[] bytes = new byte[strIDCardInfo.dwPicDataLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        fout.write(bytes);
                        fout.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                //抓拍图片
                if (strIDCardInfo.dwCapturePicDataLen > 0) {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String newName = sf.format(new Date());
                    FileOutputStream fout;
                    try {
                        String filename = "D:\\Hikvision\\fawFace\\pic\\" + new String(pAlarmer.sDeviceIP).trim() +
                                "_byCardNo[" + new String(strIDCardInfo.struIDCardCfg.byIDNum).trim() +
                                "_" + newName + "_IDInfoCapturePic.jpg";
                        fout = new FileOutputStream(filename);
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = strIDCardInfo.pCapturePicData.getByteBuffer(offset, strIDCardInfo.dwCapturePicDataLen);
                        byte[] bytes = new byte[strIDCardInfo.dwCapturePicDataLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        fout.write(bytes);
                        fout.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case HCNetSDK.COMM_UPLOAD_AIOP_VIDEO: //设备支持AI开放平台接入，上传视频检测数据
                break;
            case HCNetSDK.COMM_UPLOAD_AIOP_PICTURE: //设备支持AI开放平台接入，上传视频检测数据
                break;
            case HCNetSDK.COMM_ISAPI_ALARM: //ISAPI协议报警信息
                HCNetSDK.NET_DVR_ALARM_ISAPI_INFO struEventISAPI = new HCNetSDK.NET_DVR_ALARM_ISAPI_INFO();
                struEventISAPI.write();
                Pointer pEventISAPI = struEventISAPI.getPointer();
                pEventISAPI.write(0, pAlarmInfo.getByteArray(0, struEventISAPI.size()), 0, struEventISAPI.size());
                struEventISAPI.read();

                sAlarmType = sAlarmType + "：ISAPI协议报警信息, 数据格式:" + struEventISAPI.byDataType +
                        ", 图片个数:" + struEventISAPI.byPicturesNumber;

                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                list.add(newRow);

                SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddHHmmss");
                String curTime = sf1.format(new Date());
                FileOutputStream foutdata;
                try {
                    String jsonfilename = "D:\\Hikvision\\fawFace\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + curTime + "_ISAPI_Alarm_" + ".json";
                    foutdata = new FileOutputStream(jsonfilename);
                    //将字节写入文件
                    ByteBuffer jsonbuffers = struEventISAPI.pAlarmData.getByteBuffer(0, struEventISAPI.dwAlarmDataLen);
                    byte[] jsonbytes = new byte[struEventISAPI.dwAlarmDataLen];
                    jsonbuffers.rewind();
                    jsonbuffers.get(jsonbytes);
                    foutdata.write(jsonbytes);
                    foutdata.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                for (int i = 0; i < struEventISAPI.byPicturesNumber; i++) {
                    HCNetSDK.NET_DVR_ALARM_ISAPI_PICDATA struPicData = new HCNetSDK.NET_DVR_ALARM_ISAPI_PICDATA();
                    struPicData.write();
                    Pointer pPicData = struPicData.getPointer();
                    pPicData.write(0, struEventISAPI.pPicPackData.getByteArray(i * struPicData.size(), struPicData.size()), 0, struPicData.size());
                    struPicData.read();

                    FileOutputStream fout;
                    try {
                        String filename = "D:\\Hikvision\\fawFace\\pic\\" + new String(pAlarmer.sDeviceIP).trim() + curTime +
                                "_ISAPIPic_" + i + "_" + new String(struPicData.szFilename).trim() + ".jpg";
                        fout = new FileOutputStream(filename);
                        //将字节写入文件
                        long offset = 0;
                        ByteBuffer buffers = struPicData.pPicData.getByteBuffer(offset, struPicData.dwPicLen);
                        byte[] bytes = new byte[struPicData.dwPicLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        fout.write(bytes);
                        fout.close();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            default:
                newRow[0] = dateFormat.format(today);
                //报警类型
                newRow[1] = sAlarmType;
                //报警设备IP地址
                sIP = new String(pAlarmer.sDeviceIP).split("\0", 2);
                newRow[2] = sIP[0];
                list.add(newRow);
                break;
        }
        return list;
    }


}
