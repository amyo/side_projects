package qrinput;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import datastructure.*;
import global.*;

/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

public class CourseInfo extends JPanel {
    XYLayout xYLayout1 = new XYLayout();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    TitledBorder titledBorder1;
    JTextField id = new JTextField();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();

    JComboBox cTeacher = new JComboBox();
    JComboBox batch = new JComboBox();
    JComboBox type = new JComboBox(Course.typeName);

    CourseTypeInfo rightPan;

    public CourseInfo() {
        try {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    void jbInit() throws Exception {
        // titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),"Teacher Information");
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel1.setText("Batch:");
        this.setLayout(xYLayout1);
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel2.setText("Course ID:");
        xYLayout1.setWidth(339);
        xYLayout1.setHeight(148);
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel3.setText("Course Teacher:");
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel4.setText("Type:");
        id.setFont(new java.awt.Font("DialogInput", 0, 12));
        id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                id_mouseEntered(e);
            }
        });
        id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                id_focusGained(e);
            }
        });
        cTeacher.setFont(new java.awt.Font("DialogInput", 0, 12));
        cTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                cTeacher_mouseEntered(e);
            }
        });
        cTeacher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                cTeacher_focusGained(e);
            }
        });
        batch.setFont(new java.awt.Font("DialogInput", 0, 12));
        batch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                batch_mouseEntered(e);
            }
        });
        batch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                batch_focusGained(e);
            }
        });
        type.setFont(new java.awt.Font("DialogInput", 0, 12));
        type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                type_mouseEntered(e);
            }
        });
        type.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                type_focusGained(e);
            }
        });

        type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                type_itemStateChanged(e);
            }
        });

        this.add(jLabel1, new XYConstraints(13, 56, -1, -1));
        this.add(jLabel3, new XYConstraints(14, 83, -1, -1));
        this.add(jLabel4, new XYConstraints(13, 111, -1, -1));
        this.add(jLabel2, new XYConstraints(13, 14, -1, -1));

        this.add(id, new XYConstraints(138, 13, 188, -1));
        this.add(batch, new XYConstraints(138, 55, 187, -1));
        this.add(cTeacher, new XYConstraints(138, 83, 187, -1));
        this.add(type,    new XYConstraints(138, 112, 187, -1));
    }

    void type_itemStateChanged(ItemEvent e) {
        if(type.getSelectedIndex() != -1)
            rightPan.reloadRoomCredit();
    }

    String idTips = "If you have same course ID for lab and theory,"+
                    " enter an 'L' after the lab course ID. Two sections course can "+
                    "be identified as ECS-123A and ECS-123B.";

    String batchTips = "If you dont' find the required batch, first insert that batch from batch tab.";

    String cTeacherTips = "If you keep a course teacher at 'Assign Later',"+
                          " this courses' automatic routine will not generate.";

    String typeTips = "Remember that lab courses' routine will not generate automatically,"+
                      " so assign the lab courses manually and dont' forget to check the report always.";

    void id_focusGained(FocusEvent e) {
        G.setTips(idTips);
    }

    void id_mouseEntered(MouseEvent e) {
        G.setTips(idTips);
    }

    void batch_focusGained(FocusEvent e) {
        G.setTips(batchTips);
    }

    void batch_mouseEntered(MouseEvent e) {
        G.setTips(batchTips);
    }

    void cTeacher_focusGained(FocusEvent e) {
        G.setTips(cTeacherTips);
    }

    void cTeacher_mouseEntered(MouseEvent e) {
        G.setTips(cTeacherTips);
    }

    void type_focusGained(FocusEvent e) {
        G.setTips(typeTips);
    }

    void type_mouseEntered(MouseEvent e) {
        G.setTips(typeTips);
    }
}