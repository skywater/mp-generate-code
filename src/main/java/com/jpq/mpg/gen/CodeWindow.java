package com.jpq.mpg.gen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class CodeWindow extends JFrame implements ActionListener {
    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 1L;

    CommonLabel jlTips = new CommonLabel("TIPS").setHorizontalAlig(SwingConstants.RIGHT);
    CommonLabel jlTipsValue = new CommonLabel().setHorizontalAlig(SwingConstants.LEFT);

    CommonLabel jlDesc = new CommonLabel("DESC");
    JTextField jtDesc = new JTextField();

    CommonLabel jlAuthor = new CommonLabel("AUTHOR");
    JTextField jtAuthor = new JTextField();

    CommonLabel jlVersion = new CommonLabel("VERSION");
    JTextField jtVersion = new JTextField("1.0.0-SNAPSHOT");

    CommonLabel jlDbUrl = new CommonLabel("DB_URL");
    JTextField jtDbUrl = new JTextField("jdbc:mysql://localhost:3306/test_db");

    CommonLabel jlDbDriver = new CommonLabel("DB_DRIVER");
    JComboBox<String> jtDbDriver = new JComboBox<String>(new String[]{"com.mysql.cj.jdbc.Driver", 
            "com.mysql.jdbc.Driver"});

    CommonLabel jlDbAccount = new CommonLabel("DB_ACCOUNT");
    JTextField jtDbAccount = new JTextField("root");

    CommonLabel jlDbPwd = new CommonLabel("DB_PWD");
    JTextField jtDbPwd = new JTextField("123456");

    CommonLabel jlParentPkg = new CommonLabel("PARENT_PKG");
    JTextField jtParentPkg = new JTextField("com");

    CommonLabel jlOutputPath = new CommonLabel("OUTPUT_PATH");
    JTextField jtOutputPath = new JTextField("/opt");

    CommonLabel jlTableNames = new CommonLabel("TABLE_NAMES");
    JTextField jtTableNames = new JTextField(20);

    CommonLabel jlPriKey = new CommonLabel("PRI_KEY");
    JComboBox<String> jcbPrikey = new JComboBox<>(new String[]{"AUTO", "UUID", "ID_WORKER", "ID_WORKER_STR", "NONE"});

    JButton jbBtnGenerate = new JButton("BTN_GENERATE");
    JButton jbBtnExit = new JButton("BTN_EXIT");

    public CodeWindow() {
//        FlowLayout flowLayout = new FlowLayout(FlowLayout.TRAILING);
        GridLayout gridLayout = new GridLayout(0, 2, 10, 5);// 垂直（列）间距、水平（行）间距
        BorderLayout borderLayout = new BorderLayout();
        
        CommonPanel contentPanel = new CommonPanel().setCommonLayout(borderLayout);        
        CommonPanel panel = new CommonPanel().setCommonLayout(gridLayout);
        contentPanel.add(panel, BorderLayout.NORTH);
        panel.addAll(jlAuthor, jtAuthor, jlDesc, jtDesc, jlVersion, jtVersion, jlDbUrl, jtDbUrl, 
                jlDbDriver, jtDbDriver, jlDbAccount, jtDbAccount, jlDbPwd, jtDbPwd, jlParentPkg, jtParentPkg, 
                jlOutputPath, jtOutputPath, jlTableNames, jtTableNames, jlPriKey, jcbPrikey);

        JCheckBox var21 = new JCheckBox("Control");
        var21.setSelected(true);
        JCheckBox var23 = new JCheckBox("Service");
        var23.setSelected(true);
        JCheckBox var24 = new JCheckBox("Mapper.xml");
        var24.setSelected(true);
        JCheckBox var25 = new JCheckBox("Dao");
        var25.setSelected(true);
        JCheckBox var26 = new JCheckBox("Entity");
        var26.setSelected(true);
        
        // 必须绑定在一起，才是只能多选一！！！
        ButtonGroup btGroup = new ButtonGroup();
        JRadioButton jrBtnGenFile = new JRadioButton("BTN_GENE_FILE");
        jrBtnGenFile.setSelected(true);
        JRadioButton jrBtnGenZip = new JRadioButton("BTN_GEN_ZIP");
        btGroup.add(jrBtnGenFile);
        btGroup.add(jrBtnGenZip);
        CommonPanel jcbPanel = new CommonPanel().setCommonLayout(new GridLayout(0, 5));
        jcbPanel.addAll(var21, var23, var24, var25, var26);
        contentPanel.add(jcbPanel, BorderLayout.CENTER);

        jbBtnGenerate.addActionListener(new CodeListener(this, false));
        jbBtnExit.addActionListener(this);
        CommonUtil.initText(":", new AbstractButton[] {jbBtnGenerate, jbBtnExit, jrBtnGenFile, jrBtnGenZip}, jlAuthor, jlDesc, jlVersion, jlDbUrl, jlDbDriver, jlDbPwd, jlDbAccount,
                jlParentPkg, jlOutputPath, jlTableNames, jlPriKey, jlTips);        
        CommonPanel jbtPanel = new CommonPanel().setCommonLayout(gridLayout);

        jbBtnGenerate.addActionListener(new CodeListener(this, false));
        jbBtnExit.addActionListener(this);
        jbtPanel.setAlignmentX(CENTER_ALIGNMENT);jbtPanel.setAlignmentY(CENTER_ALIGNMENT);
        jbtPanel.addAll(jrBtnGenFile, jrBtnGenZip, jlTips, jlTipsValue, jbBtnGenerate, jbBtnExit);
        contentPanel.add(jbtPanel, BorderLayout.SOUTH);

//      this.setSize(new Dimension(60, 10));// 失效
        this.setPreferredSize(new Dimension(600, 460));
        this.setContentPane(contentPanel);
        this.setTitle("代码生成器");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);// 缩放
        this.setLocationRelativeTo(this.getOwner());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        System.exit(0);
    }

}
