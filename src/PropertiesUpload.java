import java.io.*;
import java.util.Enumeration;

public class PropertiesUpload {
    public static final SafeProperties p = new SafeProperties();
    public static  String path = "C:\\Users\\admin\\Desktop\\upload\\sysConfig.properties";

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        PropertiesUpload.init();

      /* //修改
        PropertiesUpload.update("versionCode","105");
        PropertiesUpload.update("version","1.0.5");
        PropertiesUpload.update("versionDesc","1.修复在隐患/三违录入时人员展示错误问题 2.修复整改/验收列表数据错误问题 3.增加离线整改和验证功能 4.其他同步服务端优化内容");
        PropertiesUpload.update("appName","v105_sxdb.apk");*/
       //新增
        PropertiesUpload.add("is_callback","false","是否开启回调");
        PropertiesUpload.add("uploadUrl_mj","http://47.92.243.83:9527","上传煤监路径");
        //删除
        p.remove("mineGPS");
        p.remove("layerUrl");
        p.remove("riskPointInfoUrl");
        p.remove("riskPointNewUrl");
        p.remove("riskAreaUrl");
        p.remove("riskIdentTaskUrl");
        p.remove("riskIdentTaskRefUrl");
        p.remove("riskInfoUrl");
        p.remove("controlMeasureInfoUrl");
        p.remove("riskControlInfoUrl");
        p.remove("riskControlInfoRel");
        p.remove("riskControlResultUrl");
        p.remove("hideDangerInfoUrl");
        p.remove("hideDangerSupUrl");
        p.remove("threeVioUrl");
        p.remove("dailyReportUrl");
        p.remove("hdImproveInfoUrl");
        p.remove("quarterAnalysisReportInfoUrl");
        p.remove("mechanismImproveInfoUrl");
        p.remove("majorRiskReportInfoUrl");
        p.remove("majorHdReportInfoUrl");
        p.remove("trainInfoUrl");
        p.remove("networkingUrl");
        p.remove("openlayersUrl");
        p.remove("riskAreaOpenlayersUrl");
        p.remove("tbRiskPointInfoUrl");
        p.remove("riskHiddenUrl");
        p.remove("riskHiddenControlUrl");
        PropertiesUpload.delete("is_active");

        //获取所有
        //PropertiesSxdb.list();
    }
    /**
     * 通过类装载器 初始化Properties
     */
    public static void init() throws FileNotFoundException {
        //转换成流
        InputStream inputStream =new FileInputStream(path);
        try {
            //从输入流中读取属性列表（键和元素对）
            p.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过key获取value
     * @param key
     * @return
     */
    public static String get(String key) {
        return p.getProperty(key);
    }

    /**
     * 修改或者新增key
     * @param key
     * @param value
     */
    public static void update(String key, String value) {
        p.setProperty(key, value);
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(path);
            //将Properties中的属性列表（键和元素对）写入输出流
            p.store(oFile, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 新增key，带注释
     * @param key
     * @param value
     */
    public static void add(String key, String value,String comment) throws UnsupportedEncodingException {
        String newStr = new String(comment.getBytes("GBK"), "ISO8859_1");
        p.addComment(newStr);
        p.setProperty(key, value);
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(path);
            //将Properties中的属性列表（键和元素对）写入输出流
            p.store(oFile, comment);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 通过key删除value
     * @param key
     */
    public static void delete(String key) {
        p.remove(key);
        FileOutputStream oFile = null;
        try {
            oFile = new FileOutputStream(path);
            p.store(oFile, "");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 循环所有key value
     */
    public static void list() {
        Enumeration en = p.propertyNames(); //得到配置文件的名字
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = p.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
    }
}
