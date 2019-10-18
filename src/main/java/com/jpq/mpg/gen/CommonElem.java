package com.jpq.mpg.gen;

import java.util.Locale;

/**
 * 界面国际化
 * TODO <br/>
 * @date 2019年10月11日 下午8:44:24
 * @author jiangpeiquan
 * @version
 */
public enum CommonElem {
    DB_URL("数据库链接", "Database Url"),
    DB_DRIVER("数据库驱动", "Database Driver"),
    DB_ACCOUNT("数据库链接", "Database Account"),
    DB_PWD("数据库链接", "Database Password"),
    AUTHOR("作者", "Author"),
    VERSION("版本数据库链接", "Version"),
    DESC("项目描述", "Project Desc"),
    TIPS("提示", "tips"),
    PARENT_PKG("父包名（小写）", "Parent Package(lowercase)"),
    TABLE_NAMES("表名（默认全部，逗号隔开）", "Table Names(default all, comma separated)"),
    OPTS_LOCK_VERSION("乐观锁字段名", "Optimistic Lock Version"),
    OUTPUT_PATH("输出路径", "OutputPath"),
    PRI_KEY("主键生成策略", "Primary Key Strategy"),
    BTN_GENERATE("生成", "Generate"),
    BTN_EXIT("退出", "Exit"),
    BTN_GENE_FILE("生成文件", "Generate Files"),
    BTN_GEN_ZIP("生成压缩包", "Generate Zip")
    ;
    private String txtCh;
    private String txtEn;
    CommonElem(String txtCh, String txtEn){
        this.txtCh = txtCh;
        this.txtEn = txtEn;
    }
    public String getTxtCh() {
        return txtCh;
    }
    public String getTxtEn() {
        return txtEn;
    }
    
    public String getTxt(Locale locale) {
        if(Locale.SIMPLIFIED_CHINESE.equals(locale)) {
            return getTxtCh();
        }
        return getTxtEn();
    }
    
    @Override
    public String toString() {
        Locale locale = Locale.getDefault();
        return getTxt(locale);
    }
}
