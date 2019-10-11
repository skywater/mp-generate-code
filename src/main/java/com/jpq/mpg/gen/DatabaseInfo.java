package com.jpq.mpg.gen;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class DatabaseInfo {
    private String dbUrl = "jdbc:mysql://localhost:3306/activiti_db?useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
    private String dbDriver = "com.mysql.cj.jdbc.Driver";
    private String dbUser = "root";
    private String dbPwd = "123456";
    private String outputDir = "E:\\opt\\code";
    private String author;
    private String version;
    private String tableNames;
    private String entityPackage;
    private String outputPath;
    
    private String ftlDescription;
    private String primaryKeyPolicy;
    private String sequenceCode;
    private Integer fieldRowNum;
    private Integer searchFieldNum;
    private Integer fieldRequiredNum;
    private Map<?, ?> extendParams;
    
    public void setDbUrl(String dburl) {
        if(StringUtils.isNotBlank(dburl)) {
            // 仅针对mysql
            boolean isEnd = dburl.toLowerCase().endsWith("db");
            if(!dburl.contains("useUnicode")) {
                dburl += (isEnd ? "?" : "&") + "useUnicode=true";
            }
            if(!dburl.contains("useSSL")) {
                dburl += (isEnd ? "?" : "&") + "useSSL=false";
            }
            if(!dburl.contains("serverTimezone")) {
                dburl += (isEnd ? "?" : "&") + "serverTimezone=Asia/Shanghai";
            }
            if(!dburl.contains("characterEncoding")) {
                dburl += (isEnd ? "?" : "&") + "characterEncoding=utf8";
            }
        }
        this.dbUrl = dburl;
    }
}
