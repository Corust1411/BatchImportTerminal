package com.ktb.batch.config;

import java.util.PropertyResourceBundle;

public class AppConfig {
    private static PropertyResourceBundle prop = (PropertyResourceBundle) PropertyResourceBundle.getBundle("application");
    public static String getUatCertOnePath() { return prop.getString("uat.cert.0.path"); }

    private String SourceDirectory;
    private String SourceFile;

    public String GetSourceDirectory(){
        return prop.getString("source_directory");
    }
    public String GetSourceFile(){
        return prop.getString("source_file");
    }

}
