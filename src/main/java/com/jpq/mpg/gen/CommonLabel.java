package com.jpq.mpg.gen;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 默认右对齐 <br/>
 * @date 2019年10月11日 下午7:33:25
 * @author jiangpeiquan
 * @version
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CommonLabel extends JLabel {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 1L;
    
    public CommonLabel() {
        setHorizontalAlignment(SwingConstants.RIGHT);
        // 设置长宽失效！！！
//        setBounds(0, 0, 10, 0);
    }

    public CommonLabel(String text) {
        super(text);
        setHorizontalAlignment(SwingConstants.RIGHT);
        setMinimumSize(new Dimension(1, 1));
        setPreferredSize(new Dimension(1, 1));
        setSize(1, 10);
        setMaximumSize(new Dimension(1, 1));
        setBounds(0, 0, 1, 1);
    }
    
    public CommonLabel setHorizontalAlig(int alignment) {
        setHorizontalAlignment(alignment);
        return this;
    }
    
    public CommonLabel setWidth(int width) {
        super.setSize(width, 0);
        return this;
    }

    public CommonLabel setHeight(int height) {
        super.setSize(0, height);
        return this;
    }
}
