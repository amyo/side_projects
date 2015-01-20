package global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2004
 * Company:
 * @author
 * @version 1.0
 */

public class JIntegerField extends JPanel
{
    JScrollBar sb = new JScrollBar();
    JTextField field = new JTextField();

   public JIntegerField()
    {
        setLayout(new BorderLayout());

        sb.setPreferredSize(new Dimension(15 ,23));
        sb.addAdjustmentListener(new AdjustmentListener()
        {
            public void adjustmentValueChanged(AdjustmentEvent e)
            {
                field.setText(""+sb.getValue());
            }
        });

        add(field,BorderLayout.CENTER);
        add(sb,BorderLayout.EAST);
    }
}