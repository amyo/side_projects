package qrinput;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.Cursor.*;

import global.*;

/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

public class TitleBox extends JButton
{
    /**
     * if row is TRUE it works for a period tittle
     * else if row is FALSE it works as a day tittle.
     */
    boolean row;

    /**
     * v contain value of row or colum
     * depending on the value of row.
     * If v is TRUE row/colum is assumed to be Free,
     * Otherwise Busy.
     */
    boolean v = true;

    /**
     * i contain row or colum number
     * depending on the value of row.
     */
    int i;

    /**
     * Takes reference of MyCheckBox grid that the tittle will
     * perform its' action.
     */
    MyCheckBox c[][];

    public TitleBox() {
        setBorder(BorderFactory.createEtchedBorder());
        setFont(new Font("Courier New", Font.BOLD, 12));
    }

    public TitleBox(String text, int i, boolean row, MyCheckBox c[][]) {
        setText(text);
        this.i = i;
        this.row = row;
        this.c = c;

        setBorder(BorderFactory.createEtchedBorder());
        setFont(new Font("Courier New", Font.BOLD, 12));

        /**
         * Alter the (i)th row/colum
         * without MANU session.
         */
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                v = !v;
                setValue(v? MyCheckBox.FREE: MyCheckBox.BUSY);
            }
        });

        /**
         * Change the cursor when mouse entered.
         */
        addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                Cursor c=new Cursor(Cursor.HAND_CURSOR);
                setCursor(c);
            }
        });
    }

    /**
     * Set value of row/colum
     * without the Manu session.
     * @param takes value that have to set.
     */
    void setValue(int v) {
        if(row) {
            /* Set period */
            for(int j = 0; j < c[i].length; j++)
                if(c[i][j].getValue() != MyCheckBox.MANU)
                    c[i][j].setValue(v);
        }
        else {
            /* Set day */
            for(int j = 0; j < Basic.nDay; j++)
                if(c[j][i].getValue() != MyCheckBox.MANU)
                    c[j][i].setValue(v);
        }
    }
}