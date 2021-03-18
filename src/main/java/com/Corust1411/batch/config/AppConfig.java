package com.Corust1411.batch.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.PropertyResourceBundle;

@Configuration
@Data
public class AppConfig {


    //private static PropertyResourceBundle prop = (PropertyResourceBundle) PropertyResourceBundle.getBundle("application");
    //public static String getUatCertOnePath() { return prop.getString("uat.cert.0.path"); }

    @Value("${source_directory}")
    private String SourceDirectory;
    @Value("${source_file}")
    private String SourceFile;
    @Value("${export_directory}")
    private String ExportDirectory;
    @Value("${export_file}")
    private String ExportFile;

    /*public String GetSourceDirectory(){
        return prop.getString("source_directory");
    }
    public String GetSourceFile(){
        return prop.getString("source_file");
    }*/

}
