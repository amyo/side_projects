package qrinput;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import datastructure.Room;
import global.*;

/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

public class RoomInfo extends JPanel
{
    XYLayout xYLayout1 = new XYLayout();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JTextField des = new JTextField();
    TitledBorder titledBorder1;
    JTextField id = new JTextField();
    JTextField cap = new JTextField();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JComboBox type = new JComboBox(Room.roomTypeName);

    String idTips = "Insert a room ID to be recognized this room in your class routine."+
                    " A sample room ID is 310A.";

    String desTips = "A brief description will help you recognize this room quickly later"+
                     " during assigning this room.";

    String capTips = "There might be a chance to have a big room for a big batch,"+
                     "so room capacity is important.";

    String typeTips = "Note that QuickRoutine doesnt' assign class to lab room automatically,"+
                      " you can assign class to lab room only manually.";

    public RoomInfo() {

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
        jLabel1.setText("Description");
        this.setLayout(xYLayout1);
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel2.setText("Room ID:");
        xYLayout1.setWidth(339);
        xYLayout1.setHeight(148);
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel3.setText("Capasity:");
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel4.setText("Type:");
        des.setFont(new java.awt.Font("DialogInput", 0, 12));
        des.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                des_focusGained(e);
            }
        });
        des.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                des_mouseEntered(e);
            }
        });
        id.setFont(new java.awt.Font("DialogInput", 0, 12));
        id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                id_focusGained(e);
            }
        });
        id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                id_mouseEntered(e);
            }
        });
        cap.setFont(new java.awt.Font("DialogInput", 0, 12));
        cap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                cap_focusGained(e);
            }
        });
        cap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                cap_mouseEntered(e);
            }
        });
        type.setFont(new java.awt.Font("DialogInput", 0, 12));
        type.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                type_focusGained(e);
            }
        });
        type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                type_mouseEntered(e);
            }
        });
        this.add(des, new XYConstraints(138, 55, 187, -1));
        this.add(id, new XYConstraints(138, 13, 188, -1));
        this.add(cap, new XYConstraints(138, 83, 187, -1));
        this.add(jLabel1, new XYConstraints(13, 56, -1, -1));
        this.add(jLabel3, new XYConstraints(14, 83, -1, -1));
        this.add(jLabel4, new XYConstraints(13, 111, -1, -1));
        this.add(jLabel2, new XYConstraints(13, 14, -1, -1));
        this.add(type,    new XYConstraints(138, 112, 187, -1));
    }

    void id_mouseEntered(MouseEvent e) {
        G.setTips(idTips);
    }

    void des_mouseEntered(MouseEvent e) {
        G.setTips(desTips);
    }

    void cap_mouseEntered(MouseEvent e) {
        G.setTips(capTips);
    }

    void type_mouseEntered(MouseEvent e) {
        G.setTips(typeTips);
    }

    void id_focusGained(FocusEvent e) {
        G.setTips(idTips);
    }

    void des_focusGained(FocusEvent e) {
        G.setTips(desTips);
    }

    void cap_focusGained(FocusEvent e) {
        G.setTips(capTips);
    }

    void type_focusGained(FocusEvent e) {
        G.setTips(typeTips);
    }
}