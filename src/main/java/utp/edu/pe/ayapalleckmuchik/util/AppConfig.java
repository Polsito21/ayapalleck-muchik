package utp.edu.pe.ayapalleckmuchik.util;

import java.util.ResourceBundle;

public class AppConfig {
    static ResourceBundle rb = ResourceBundle.getBundle("app");

    public static String separator(){
        return System.getProperty("file.separator");
    }

    public static String getSourceType(){
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            return rb.getString("tc_sourceType");
        }else{
            return rb.getString("wf_sourceType");
        }
    }

    public static String getDatasource(){
        return rb.getString("datasource");
    }
}
