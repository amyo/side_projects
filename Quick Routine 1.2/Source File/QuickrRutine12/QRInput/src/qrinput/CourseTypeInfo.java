package qrinput;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import global.*;
import datastructure.*;

import com.borland.dbswing.*;
import com.borland.jbcl.layout.*;
/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

public class CourseTypeInfo extends JPanel {
    XYLayout xYLayout1 = new XYLayout();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    TitledBorder titledBorder1;
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();

    JComboBox room = new JComboBox();

    JComboBox credit = new JComboBox();
    JComboBox duration = new JComboBox();
    JComboBox nClass = new JComboBox();

    RoomPan roomPanRef;
    CourseInfo leftPan;
    CoursePan coursePanRef;

    public CourseTypeInfo(RoomPan roomPanRef) {
        this.roomPanRef = roomPanRef;

        try {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    void jbInit() throws Exception {
        this.setLayout(xYLayout1);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel1.setText("Credit:");
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel2.setText("Room:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel3.setText("Class Duration:");
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel4.setText("Weekly Class(s):");

        room.setFont(new java.awt.Font("DialogInput", 0, 12));
        room.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                room_mouseEntered(e);
            }
        });
        room.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                room_focusGained(e);
            }
        });
        duration.setFont(new java.awt.Font("DialogInput", 0, 12));
        duration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                duration_mouseEntered(e);
            }
        });
        duration.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                duration_focusGained(e);
            }
        });
        credit.setFont(new java.awt.Font("DialogInput", 0, 12));
        credit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                credit_mouseEntered(e);
            }
        });
        credit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                credit_focusGained(e);
            }
        });
        credit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                credit_itemStateChanged(e);
            }
        });

        nClass.setFont(new java.awt.Font("DialogInput", 0, 12));
        nClass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                nClass_mouseEntered(e);
            }
        });
        nClass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                nClass_focusGained(e);
            }
        });

        xYLayout1.setWidth(400);
        xYLayout1.setHeight(168);
        this.add(jLabel1, new XYConstraints(13, 56, -1, -1));
        this.add(jLabel3, new XYConstraints(14, 83, -1, -1));
        this.add(jLabel4, new XYConstraints(13, 111, -1, -1));
        this.add(jLabel2, new XYConstraints(13, 14, -1, -1));

        this.add(room, new XYConstraints(138, 13, 188, -1));
        this.add(credit, new XYConstraints(138, 55, 187, -1));
        this.add(duration,     new XYConstraints(138, 83, 187, -1));
        this.add(nClass, new XYConstraints(138, 112, 187, -1));
    }

    public int getDuration() {
        /* Theory. */
        if(leftPan.type.getSelectedIndex() == 0) return 1;

        /* Lab */
        else return duration.getSelectedIndex() + 1;
    }

    public int getNClass() {
        /* Theory. */
        if(leftPan.type.getSelectedIndex() == 0) return credit.getSelectedIndex() + 1;

        /* Lab */
        else return nClass.getSelectedIndex() + 1;
    }

    public void reloadRoomCredit() {
        // reading Course type from leftInfoPanael.
        int courseType = leftPan.type.getSelectedIndex();
        int maxDuration = (courseType == Course.THEORY? Basic.maxTheoryDuration: Basic.maxLabDuration);

        if(roomPanRef == null) MyError.show("rompanref null");

        // Set ROOM...
        room.removeAllItems();
        room.addItem("Assign Later.");
        room.setSelectedIndex(0);

        // Set CREDIT...
        int n = 0;
        String creditName[];

        credit.removeAllItems();
        if(courseType == Course.THEORY) {
            n = Basic.maxTheoryCredit;
            creditName = Course.theoryCreditName;
        }
        else {
            n = 2 * Basic.maxLabCredit;
            creditName = Course.labCreditName;
        }

        for(int i = 0; i < n; i++)
            credit.addItem(creditName[i]);

        room.setEnabled(true);
        credit.setEnabled(true);

        credit.setSelectedIndex(-1);

        duration.setEnabled(false);
        nClass.setEnabled(false);

        duration.removeAllItems();
        nClass.removeAllItems();
    }

    /**
     * Assume credits' selected index != -1
     */
    public void reloadDurationNumberofClass() {
        if(credit.getSelectedIndex() == -1) {
            MyError.show("Dont Call reloadDuration() with -1 credit");
            return;
        }

        int courseType = leftPan.type.getSelectedIndex();
        int maxDuration = (courseType == Course.THEORY? Basic.maxTheoryDuration: Basic.maxLabDuration);

        // MyError.show("Type: "+courseType+" maxDuration: "+maxDuration+"\n"+Basic.maxTheoryDuration +", "+ Basic.maxLabDuration);

        duration.removeAllItems();
        nClass.removeAllItems();

        duration.addItem("1 hour");
        for(int i = 1; i < maxDuration; i++)
            duration.addItem(""+(i+1)+" hours");

        nClass.addItem("1 Class per week");
        for(int i = 1; i < Basic.nDay; i++)
            nClass.addItem(+(i+1)+" Classes per week");

        nClass.setSelectedIndex(-1);
        duration.setSelectedIndex(-1);

        /**
         * Late code...
         */
        if(courseType == Course.THEORY) {
            duration.removeAllItems();
            nClass.removeAllItems();

            int cre = credit.getSelectedIndex() + 1;
            duration.addItem("1 hour.");

            nClass.addItem(+cre+" class(s) per week.");

            nClass.setSelectedIndex(0);
            duration.setSelectedIndex(0);
        }

        duration.setEnabled(true);
        nClass.setEnabled(true);
    }

    void credit_itemStateChanged(ItemEvent e) {
        if(credit.getSelectedIndex() != -1)
            reloadDurationNumberofClass();
    }

    String roomTips = "Please dont' think too much about room here, just let it be 'Assign Later' always.";
    String creditTips = "Enter the course' credit.";
    String durationTips = "All theory courses' per class duration should be 1 hour."+
                          " If you have an exception please enter that course as a lab.";

    String nClassTips = "You have to enter this value only for lab course.";

    void room_focusGained(FocusEvent e) {
        G.setTips(roomTips);
    }

    void room_mouseEntered(MouseEvent e) {
        G.setTips(roomTips);
    }

    void credit_focusGained(FocusEvent e) {
        G.setTips(creditTips);
    }

    void credit_mouseEntered(MouseEvent e) {
        G.setTips(creditTips);
    }

    void duration_focusGained(FocusEvent e) {
        G.setTips(durationTips);
    }

    void duration_mouseEntered(MouseEvent e) {
        G.setTips(durationTips);
    }

    void nClass_focusGained(FocusEvent e) {
        G.setTips(nClassTips);
    }

    void nClass_mouseEntered(MouseEvent e) {
        G.setTips(nClassTips);
    }
}