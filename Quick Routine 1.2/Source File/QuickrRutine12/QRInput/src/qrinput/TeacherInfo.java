package qrinput;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
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

public class TeacherInfo extends JPanel {
    XYLayout xYLayout1 = new XYLayout();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JTextField fName = new JTextField();
    TitledBorder titledBorder1;
    JTextField lName = new JTextField();
    JTextField id = new JTextField();
    JTextField mName = new JTextField();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();

    public TeacherInfo() {
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
        jLabel1.setText("First Name:");
        this.setLayout(xYLayout1);
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel2.setText("Teacher ID:");
        xYLayout1.setWidth(339);
        xYLayout1.setHeight(148);
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel3.setText("Middle Name:");
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel4.setText("Last Name:");
        fName.setFont(new java.awt.Font("DialogInput", 0, 12));
        fName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                fName_mouseEntered(e);
            }
        });
        fName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                fName_focusGained(e);
            }
        });
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
        mName.setFont(new java.awt.Font("DialogInput", 0, 12));
        mName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                mName_mouseEntered(e);
            }
        });
        mName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                mName_focusGained(e);
            }
        });
        lName.setFont(new java.awt.Font("DialogInput", 0, 12));
        lName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                lName_mouseEntered(e);
            }
        });
        lName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                lName_focusGained(e);
            }
        });

        this.add(id, new XYConstraints(138, 13, 188, -1));
        this.add(fName, new XYConstraints(138, 55, 187, -1));
        this.add(mName, new XYConstraints(138, 83, 187, -1));
        this.add(lName, new XYConstraints(138, 112, 187, -1));

        this.add(jLabel1, new XYConstraints(13, 56, -1, -1));
        this.add(jLabel3, new XYConstraints(14, 83, -1, -1));
        this.add(jLabel4, new XYConstraints(13, 111, -1, -1));
        this.add(jLabel2, new XYConstraints(13, 14, -1, -1));
    }

    String idTips = "Teacher ID should be at most three letter long to keep your routine looks slim.";
    String fNameTips = "You must have to enter a teachers' first name.";
    String mNameTips = "Teachers' middle name is optional.";
    String lNameTips = "You must enter a teachers' last name too.";

    void id_focusGained(FocusEvent e) {
        G.setTips(idTips);
    }

    void id_mouseEntered(MouseEvent e) {
        G.setTips(idTips);
    }

    void fName_focusGained(FocusEvent e) {
        G.setTips(fNameTips);
    }

    void fName_mouseEntered(MouseEvent e) {
        G.setTips(fNameTips);
    }

    void mName_focusGained(FocusEvent e) {
        G.setTips(mNameTips);
    }

    void mName_mouseEntered(MouseEvent e) {
        G.setTips(mNameTips);
    }

    void lName_focusGained(FocusEvent e) {
        G.setTips(lNameTips);
    }

    void lName_mouseEntered(MouseEvent e) {
        G.setTips(lNameTips);
    }
}