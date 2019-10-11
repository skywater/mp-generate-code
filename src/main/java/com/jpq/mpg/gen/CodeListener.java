package com.jpq.mpg.gen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 监听事件 <br/>
 * 
 * @date 2019年10月11日 下午3:21:33
 * @author jiangpeiquan
 * @version
 */
public class CodeListener implements ActionListener {
    private CodeWindow codeWindow;
    private boolean isExit;

    public CodeListener(CodeWindow codeWindow, boolean isExit) {
        this.codeWindow = codeWindow;
        this.isExit = isExit;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isExit) {
            codeWindow.dispose();
            System.exit(0);
            return;
        }

        if (StringUtils.isBlank(codeWindow.jtParentPkg.getText())) {
            codeWindow.jlTipsValue.setForeground(Color.red);
            codeWindow.jlTipsValue.setText("父包名不能为空！");
            return;
        }
        if (StringUtils.isBlank(codeWindow.jtOutputPath.getText())) {
            codeWindow.jlTipsValue.setForeground(Color.red);
            codeWindow.jlTipsValue.setText("输出目录名不能为空！");
            return;
        }
        if (StringUtils.isBlank(codeWindow.jtDbUrl.getText())) {
            codeWindow.jlTipsValue.setForeground(Color.red);
            codeWindow.jlTipsValue.setText("数据库地址不能为空！");
            return;
        }
        if (StringUtils.isBlank(codeWindow.jtDbAccount.getText())) {
            codeWindow.jlTipsValue.setForeground(Color.red);
            codeWindow.jlTipsValue.setText("数据库账号不能为空！");
            return;
        }
        try {
            DatabaseInfo info = new DatabaseInfo();
            info.setTableNames(codeWindow.jtTableNames.getText());
            info.setPrimaryKeyPolicy(codeWindow.jtParentPkg.getText().toLowerCase());
            info.setOutputPath(codeWindow.jtOutputPath.getText());
            info.setDbUrl(codeWindow.jtDbUrl.getText());
            info.setDbUser(codeWindow.jtDbAccount.getText());
            info.setDbPwd(codeWindow.jtDbPwd.getText());
            info.setAuthor(codeWindow.jtAuthor.getText());
            info.setAuthor(codeWindow.jtVersion.getText());
            info.setDbDriver(codeWindow.jtDbDriver.getSelectedItem().toString());
            info.setFtlDescription(codeWindow.jtDesc.getText());
            MpGeneratorUtil.generateCode(info);
            codeWindow.jlTipsValue.setForeground(Color.red);
            codeWindow.jlTipsValue.setText("Generated successfully！");
        } catch (Exception exception) {
            codeWindow.jlTipsValue.setForeground(Color.red);
            codeWindow.jlTipsValue.setText(exception.getMessage());
        }
    }

}
