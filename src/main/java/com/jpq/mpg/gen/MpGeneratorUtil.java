package com.jpq.mpg.gen;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.ImmutableMap;

public class MpGeneratorUtil {
    public static void main(String[] args) {
        String driverType = "com.mysql.cj.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost:3306/activiti_db?useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
        String dbUser = "root";
        String dbPwd = "123456";
        String outputDir = "E:\\opt\\code";
        generateCode(driverType, dbUrl, dbUser, dbPwd, outputDir, null);
        
    }

    public static void generateCode(DatabaseInfo info) {
        generateCode(info.getDbDriver(), info.getDbUrl(), info.getDbUser(), info.getDbPwd(), info.getOutputDir(), info.getOptsLockVersion(), info.getTableNames().split(","));
    }
    
    public static void generateCode(String driverType, String dbUrl, String dbUser, String dbPwd, String outputDir,String optsLockVersion, String... tableNames) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(dbUrl);
        dataSourceConfig.setDriverName(driverType);
        dataSourceConfig.setUsername(dbUser);
        dataSourceConfig.setPassword(dbPwd);
        
        String rootDir = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig();
        if (StringUtils.isBlank(outputDir)) {
            outputDir = rootDir + "/src/main/java";
        }
        globalConfig.setOutputDir(outputDir);
        globalConfig.setAuthor("jpq");
        globalConfig.setOpen(true);// 打开输出目录
        globalConfig.setFileOverride(true);// 覆盖原文件
        globalConfig.setSwagger2(true);
        globalConfig.setBaseResultMap(true);// 生成xml通过结果集
        globalConfig.setBaseColumnList(true);// 生成xml列名
        globalConfig.setIdType(IdType.ID_WORKER);
        globalConfig.setEntityName("%sDO");// 文件名、类名都以DO结尾
        globalConfig.setActiveRecord(true);// 简单来说就是DO extends Model<>

        // 策略配置，设置entity.java.vm的值
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setVersionFieldName(optsLockVersion);
//        strategyConfig.setLogicDeleteFieldName("enable");// @TableLogic
        strategyConfig.setEntityTableFieldAnnotationEnable(true);// @TableField
        // 类文件命名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);// 去掉好像无影响
        // 表名，不写则默认生成所有的表
        if(null != tableNames && tableNames.length > 0) {
        	Arrays.stream(tableNames).forEach(elem -> {
        		elem = elem.trim();
        		if(StringUtils.isNotBlank(elem)) {
                    strategyConfig.setInclude(elem);
        		}
        	});
            strategyConfig.setInclude(Arrays.stream(tableNames).filter(e -> StringUtils.isNotBlank(e))
            		.map(e -> e.trim()).collect(Collectors.toList()).toArray(new String[] {}));
        }
        // 是否生成字段常量（默认 false），public static final String BUSINESS_KEY_ = "BUSINESS_KEY_";
//        strategyConfig.setEntityColumnConstant(true);
//        strategyConfig.setInclude(tablesArray) // 需要生成的表，支持正则表达式
//        strategyConfig.setExclude(new String[]{"test"}) // 排除生成的表，支持正则表达式
        // 【实体】是否为构建者模型（默认 false），public User setName(String name) {this.name = name; return this;}
//        strategyConfig.setEntityBuilderModel(true);
     // Boolean类型字段是否移除is前缀处理，mysql没有该类型，暂无法测试
//        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);
        
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.jpq");// 父包名
        packageConfig.setXml("xml");// 修改xml包，文件路径也会修改
//        packageConfig.setController("");
//        packageConfig.setService("");
//        packageConfig.setServiceImpl("");
//        packageConfig.setMapper("");
//        packageConfig.setEntity("");
//        packageConfig.setModuleName("");
        
        // 指定自定义模板路径, 位置：/resources/templates/entity.java.ftl(或者是.vm)
        // 注意不要带上.ftl(或者是.vm), 会根据使用的模板引擎自动识别
        // 写一个entity就可以，不用全部写（controller、service），会自动读
        TemplateConfig templateConfig = new TemplateConfig().setEntity("template/entity.java");

        InjectionConfig injectionConfig = new InjectionConfig() {
            // 自定义属性注入:abc
            // 在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        AutoGenerator mpg = new AutoGenerator();
        mpg.setDataSource(dataSourceConfig);
        mpg.setGlobalConfig(globalConfig);
        mpg.setStrategy(strategyConfig);
        mpg.setPackageInfo(packageConfig);
        // 配置自定义模板
        mpg.setTemplate(templateConfig);
        // 配置自定义属性注入
        mpg.setCfg(injectionConfig);
        // 执行生成
        mpg.execute();
    }
    
}
