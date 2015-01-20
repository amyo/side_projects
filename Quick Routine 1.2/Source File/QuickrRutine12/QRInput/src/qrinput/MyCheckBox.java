package qrinput;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

import global.*;

/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

public class MyCheckBox extends JButton
//implements ActionListener
{
    /**
     * Three state of MyCheckBox
     * that will be stored in 'value'.
     * User can toggle FREE-BUSY state
     * but cant' change MANU state
     * MANU setting can be done by only setValue(MyChekBox.MANU) command.
     */
    public static final int FREE = 0, BUSY = 1, MANU = 2;
    public int value;

    /**
     * Message that will be shown to user when user
     * will try to alter a session that was asinged manually.
     */
    String mMsg = "This session was asigned manually.\n So you have to remove"+
                  " corresponding manual routine first to alter this session here.";

    public MyCheckBox() {
        setBorder(BorderFactory.createEtchedBorder());
        setFont(new Font("Courier New", Font.PLAIN, 12));


        setValue(value = FREE);

        /**
         * Perform user command, when Mouse CLICKED
         * on MyCheckBox component.
         * Remember MyCheckBox is originaly a JButton.
         */
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(value == MANU) {
                    setToolTipText(mMsg);
                    MyError.show(mMsg);
                }
                else {
                    setValue(value = (value == 0? 1: 0));
                    setToolTipText("");
                }
            }
        });

        /**
         * This mouse listener change cursor, when
         * enterd on any MyChekBox component.
         */
        addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                Cursor c=new Cursor(Cursor.HAND_CURSOR);
                setCursor(c);
            }
        });
    }

    /**
     * Change state of MyCheckBox.
     * @param value that user want to set to this component.
     * @return void
     */
    void setValue(int value) {
        switch(this.value = value) {
            case FREE:
                setBackground(Color.white);
                setForeground(Color.black);
                setText("Free");
                break;

            case BUSY:
                setBackground(new Color(10, 36, 106));
                setForeground(Color.white);
                setText("Busy");
                break;

            case MANU:
                setBackground(new Color(58, 110, 165));
                setForeground(Color.white);
                setText("Manu");
                break;
        }
    }

    /**
     * @return current state of the check box.
     * sate can be FREE, BUSY or MANU.
     */
     public int getValue() {
        return value;
     }
}