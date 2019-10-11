package com.jpq.mpg.gen;

import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Arrays;

import javax.swing.JPanel;

/**
 * 
 * 通用panel <br/>
 * @date 2019年10月11日 下午7:46:36
 * @author jiangpeiquan
 * @version
 */
public class CommonPanel extends JPanel {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 1L;

    public CommonPanel setCommonLayout(LayoutManager mgr) {
        super.setLayout(mgr);
        return this;
    }
    
    @Override
    public CommonPanel add(Component comp) {
        super.add(comp);
        return this;
    }
    

    public CommonPanel addAll(Component... comps) {
        if(null != comps && comps.length > 0) {
            Arrays.stream(comps).forEach(elem -> {
                super.add(elem);
            });
        }
        return this;
    }
}
