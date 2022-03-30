package cn.stylefeng.guns.hikvision;

/**
 * @ClassName jna_test
 * @Description TODO
 * @Author ASD-FuBenHao 人脸控制柜
 * @Date 2021/11/22 9:44
 * @Version 1.0
 **/
public class jna_test {

    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    static int lUserID = -1;//用户句柄
    int iErr = 0;
    static int iCharEncodeType = 0;//设备字符集

    public static void main(String[] args) throws InterruptedException {
        jna_test test01  = new jna_test();
        // 初始化
        boolean initSuc = hCNetSDK.NET_DVR_Init();
        if (initSuc != true) {
            System.out.println("初始化失败");
        }

        // 打印SDK日志
        hCNetSDK.NET_DVR_SetLogToFile(3, ".\\SDKLog\\", false);
        // 用户登陆操作
        test01.Login_V40("192.168.26.3",(short)8000,"admin","faw123456");
        //远程控门 1号门 1开 0关
        boolean openKg=hCNetSDK.NET_DVR_ControlGateway(lUserID,1,1);
        if (openKg){
            System.out.println("打开成功");
        }
        /*
         *实现SDK中其余功能模快
         */
        Thread.sleep(5000);
        //用户注销，释放SDK
        test01.Logout();
    }

    /**
     *
     * @param m_sDeviceIP 设备ip地址
     * @param wPort       端口号，设备网络SDK登录默认端口8000
     * @param m_sUsername 用户名
     * @param m_sPassword 密码
     */
    public void Login_V40(String m_sDeviceIP,short wPort,String m_sUsername,String m_sPassword) {
        /* 注册 */
        // 设备登录信息
        HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();

        // 设备信息
        HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();
        m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());
        m_strLoginInfo.wPort =wPort ;
        m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());
        m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());
        // 是否异步登录：false- 否，true- 是
        m_strLoginInfo.bUseAsynLogin = false;
        // write()调用后数据才写入到内存中
        m_strLoginInfo.write();

        lUserID = hCNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);
        if (lUserID == -1) {
            System.out.println("登录失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
            return;
        } else {
            System.out.println("登录成功！");

            // read()后，结构体中才有对应的数据
            m_strDeviceInfo.read();
            return;
        }
    }

    //设备注销 SDK释放
    public void Logout() {
        if (lUserID>=0)
        {
            if (hCNetSDK.NET_DVR_Logout(lUserID) == false) {
                System.out.println("注销失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
            }
            System.out.println("注销成功");
            hCNetSDK.NET_DVR_Cleanup();
            return;
        }
        else{
            System.out.println("设备未登录");
            hCNetSDK.NET_DVR_Cleanup();
            return;
        }
    }
}
