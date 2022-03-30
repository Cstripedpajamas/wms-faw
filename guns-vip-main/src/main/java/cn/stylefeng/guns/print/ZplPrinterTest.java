package cn.stylefeng.guns.print;

/**
 * @ClassName 打印机
 * @Description TODO
 * @Author ASD-FuBenHao
 * @Date 2021/12/10 21:12
 * @Version 1.0
 **/
public class ZplPrinterTest {
    static String type1 = "1"; // 小箱
    static String type2 = "2"; // 中箱
    static String type3 = "3"; // 大箱
    //远程
   static ZplPrinter p = new ZplPrinter("\\\\DESKTOP-QASNODV\\ZDesigner ZD888-203dpi ZPL");

    public static void main(String[] args) {
//        ZplPrinter p = new ZplPrinter("ZDesigner ZD888-203dpi ZPL"); //本地
        p.resetZpl();//清除
        for (int i = 500; i < 1000; i++) {
           String s = addZeroForNumber(i +"");
            p.resetZpl();//清除
            for (int j = 0; j < 2; j++) { // 大/小: 2 中箱: 4

                    printFawTroue(p,type1+s); // 工具条码
                    p.resetZpl();


            }
//            printBarcode(p);
//            printPicking300DPI(p);
        }
    }

    public static String addZeroForNumber(String str){
        int len = str.length();
            while (len < 9){
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);
                str = sb.toString();
                len = str.length();
            }
            return str;


    }
    public static String addZeroForNumber2(String str){
        int len = str.length();
        while (len < 8){
            StringBuffer sb = new StringBuffer();
            sb.append("0").append(str);
            str = sb.toString();
            len = str.length();
        }
        return str;


    }

    private static boolean printPicking(ZplPrinter p) {
        p.setText("国药控股湖南有限公司", 253, 26, 40, 40, 20, 1, 1, 24);
        p.setChar("CSS0BPKPPR", 253, 66, 20, 20);
        p.setText("公司自配送 公路", 253, 120, 53, 53, 20, 1, 1, 24);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);
        return result;
    }

    private static boolean printBarcode(ZplPrinter p) {
        //1.打印单个条码
        String bar0 = "131ZA010101";//条码内容
//		String bar0Zpl = "^FO110,110^BY6,3.0,280^BCN,,Y,N,N^FD${data}^FS";//条码样式模板
        String bar0Zpl = "^FO100,50^AAN,100,110^BY6,3.0,280^BCN,,N,N,N^FD${data}^FS";//条码样式模板
        p.setBarcode(bar0, bar0Zpl);
        p.setChar(bar0, 13, 26, 140, 110);
        p.setText("上", 13, 66, 60, 60, 30, 4, 4, 24);
        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);//打印
        return result;
    }

    private static boolean printPicking300DPI(ZplPrinter p) {
        //左边的条码
        String bar1 = "07";
        p.setChar(bar1, 190, 130, 60, 60);
        String bar1Zpl = "^FO100,200^BY8,3.0,240^BCR,,N,N,N^FD${data}^FS";//条码样式模板
        p.setBarcode(bar1, bar1Zpl);
        //下边的条码
        String bar2 = "123459999900188ABCDE";//20位
        String bar2Paper = "^FO380,600^BY3,3.0,100^BCN,,Y,N,N^FD${data}^FS";//条码样式模板
        p.setBarcode(bar2, bar2Paper);

        p.setText("国药控股湖南有限公司", 380, 40, 60, 60, 30, 2, 2, 24);
        p.setChar("CSS0BPKPPR", 380, 100, 60, 60);

        p.setText("09件", 940, 80, 60, 60, 30, 2, 2, 24);
        p.setText("补", 1100, 80, 60, 60, 30, 2, 2, 24);

        p.setText("公司自配送 公路", 380, 180, 80, 80, 30, 3, 3, 24);
        p.setChar("03231151", 940, 187, 40, 40);
        p.setChar("2015-10-10", 940, 227, 30, 30);

        p.setText("湖南六谷大药房连锁有限公司", 380, 260, 60, 60, 30, 2, 2, 24);

        p.setText("：长沙市：开福区：捞刀河镇中岭村258号：", 380, 320, 60, 60, 30, 2, 2, 22);

        p.setText("多SKU", 800, 485, 60, 60, 30, 2, 2, 24);

        p.setText("库位:49", 380, 420, 56, 56, 30, 2, 2, 24);
        p.setText("品类:感冒胶囊", 380, 485, 56, 56, 30, 2, 2, 24);

        p.setText("批号:", 380, 550, 56, 56, 30, 2, 2, 24);
        p.setChar("78787878788", 500, 560, 40, 40);

        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);
        return result;
    }

    private static boolean printPicking203DPI(ZplPrinter p) {
        //左边的条码
        /*
         * String bar1 = "07"; p.setChar(bar1, 126, 86, 40, 40); String bar1Zpl =
         * "^FO66,133^BY5,3.0,160^BCR,,N,N,N^FD${data}^FS";//条码样式模板
         * p.setBarcode(bar1,bar1Zpl); //下边的条码 String bar2 =
         * "00000999990018822969";//20位 String bar2Paper =
         * "^FO253,400^BY2,3.0,66^BCN,,Y,N,N^FD${data}^FS";//条码样式模板
         * p.setBarcode(bar2,bar2Paper);
         */

        p.setText("国药控股湖南有限公司", 253, 26, 40, 40, 20, 1, 1, 24);
        p.setChar("CSS0BPKPPR", 253, 66, 20, 20);

        /*
         * p.setText("09件",626, 53, 40, 40, 20, 1, 1, 24); p.setText("补", 733, 53, 40,
         * 40, 20, 1, 1, 24);
         */

        p.setText("公司自配送 公路", 253, 120, 53, 53, 20, 1, 1, 24);
        /*
         * p.setChar("03231151",626, 124, 26, 26); p.setChar("2015-10-10",626, 151, 20,
         * 20);
         *
         * p.setText("湖南六谷大药房连锁有限公司", 253, 173, 40, 40, 20, 1, 1, 24);
         *
         * p.setText("长沙市开福区捞刀河镇中岭村258号", 253, 213, 30, 30, 20, 1, 1, 22);
         *
         * p.setText("多SKU", 533, 323, 40, 40, 20, 1, 1, 24);
         *
         * p.setText("库位:49", 253, 280, 37, 37, 20, 1, 1, 24); p.setText("品类:感冒胶囊", 253,
         * 323, 37, 37, 20, 1, 1, 24);
         *
         * p.setText("批号:", 253, 366, 37, 37, 20, 1, 1, 24); p.setChar("78787878788",
         * 333, 373, 26, 26);
         */

        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);
        return result;
    }

    private static boolean printRestoking(ZplPrinter p) {
        //上方的条码
        String bar1 = "1434455567773344abcd";
        String bar1Zpl = "^FO85,70^BY4,3.0,140^BCN,,Y,N,N^FD${data}^FS";//条码样式模板
        p.setBarcode(bar1, bar1Zpl);
        //源货位
        p.setText("来源:131ZA010101", 100, 320, 60, 60, 30, 2, 2, 24);
        //目标货位
        p.setText("目的:131ZA010102", 640, 320, 60, 60, 30, 2, 2, 24);
        //货品编号
        p.setText("货品编号:YF10001", 100, 440, 60, 60, 30, 2, 2, 24);
        //件装数
        p.setText("补货数量:200", 640, 440, 60, 60, 30, 2, 2, 24);
        //品名
        p.setText("复方一支黄花：", 100, 580, 60, 60, 30, 2, 2, 24);

        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);
        return result;
    }

    private static boolean printFaw(ZplPrinter p) {

        //工具类型
//        p.setText("工具类型:", 200, 30, 30, 30,30, 2, 2, 24);
//        p.setText("测试工具", 430, 30, 30, 30,30, 2, 2, 24);
        //工具SKU
//        p.setChar("SKU:", 200, 100, 30, 30);
//        p.setChar("SD001", 430, 100, 30, 30);

        //上方的条码
        String bar1 = "A10001";
        String bar1Zpl = "^FO200,100^BY2,3.0,100^BCN,,Y,N,N^FD${data}^FS";//条码样式模板
        p.setBarcode(bar1, bar1Zpl);

        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);
        return result;
    }

    public static boolean printFawTroue(ZplPrinter p,String data) {
        //上方的条码
        String bar1 = data; // 1: 左偏移, 2.上下偏移?, 3.条码数字大小:BY3,BY4 越大条码越大 4. 3.0?? 5. 条码高度
//        String bar1Zpl = "^FO130,100^BY2,50^BY4,3.0,190^BCN,,Y,N,N^FD${data}^FS";//条码样式模板(大)
        String bar1Zpl = "^FO240,130^BY2,50^BY2,3.0,160^BCN,,Y,N,N^FD${data}^FS";//条码样式模板(小)
//        String bar1Zpl =  "FO100,50^AAN,100,110^BY6,3.0,280^BCN,,N,N,N^FD${data}^FS";
//        String bar1Zpl = "^FO250,10^BQN,2,100^FDMM,1${data}^FS";//二维码样式模板 ${data} 是数据
        p.setBarcode(bar1, bar1Zpl);

        String zpl = p.getZpl();
        System.out.println(zpl);
        boolean result = p.print(zpl);
        return result;
    }

}
