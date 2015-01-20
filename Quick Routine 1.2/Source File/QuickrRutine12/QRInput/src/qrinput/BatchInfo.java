package qrinput;

import java.awt.*;
import com.borland.jbcl.layout.*;
import com.sun.java.swing.plaf.windows.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import datastructure.*;
import global.Basic;
import global.*;

import help.*;
/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

public class BatchInfo extends JPanel {
    XYLayout xYLayout1 = new XYLayout();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    TitledBorder titledBorder1;
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();

    JComboBox yr = new JComboBox();
    JComboBox sem = new JComboBox();
    JComboBox sec = new JComboBox();

    String s[] = {"Sunday Morning", "Sunday Evening"};
    public JComboBox sunMor = new JComboBox(s);

    public BatchInfo() {
        reloadBatchInfo();

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
        jLabel1.setText("Year:");
        this.setLayout(xYLayout1);
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel2.setText("Class Start at:");
        xYLayout1.setWidth(339);
        xYLayout1.setHeight(148);
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel3.setText("Semester:");
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 13));
        jLabel4.setText("Section:");

        /// nStu.add(new JSlider());



        sunMor.setFont(new java.awt.Font("DialogInput", 0, 12));
        sunMor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                sunMor_focusGained(e);
            }
        });
        sunMor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                sunMor_mouseEntered(e);
            }
        });
        yr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                yr_focusGained(e);
            }
        });
        yr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                yr_mouseEntered(e);
            }
        });
        sem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                sem_mouseEntered(e);
            }
        });
        sem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                sem_focusGained(e);
            }
        });
        sec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                sec_mouseEntered(e);
            }
        });
        sec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) {
                sec_focusGained(e);
            }
        });
        jButton1.setFont(new java.awt.Font("DialogInput", 1, 10));
        jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton1.setText("???");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        this.add(jLabel1, new XYConstraints(13, 56, -1, -1));
        this.add(jLabel3, new XYConstraints(14, 83, -1, -1));
        this.add(jLabel4, new XYConstraints(13, 111, -1, -1));
        this.add(jLabel2, new XYConstraints(13, 14, -1, -1));
        this.add(yr, new XYConstraints(138, 55, 187, -1));
        this.add(sem, new XYConstraints(138, 83, 187, -1));
        this.add(sec, new XYConstraints(138, 112, 187, -1));
        this.add(sunMor,          new XYConstraints(137, 16, 131, -1));
        this.add(jButton1,                       new XYConstraints(273, 16, 52, 21));
    }

    void reloadBatchInfo() {
        yr.removeAllItems();
        /*
        for(int i = 0; i < Basic.maxYear; i++)
            yr.addItem(Batch.yrSemName( i));
        */
        for(int i = 0; i < 4; i++)
            yr.addItem(Batch.yrSemName( i));
        yr.addItem("Masters");

        sem.removeAllItems();
        for(int i = 0; i < Basic.maxSemester; i++)
            sem.addItem(Batch.yrSemName(i));

        sec.removeAllItems();
        //  if(Basic.maxSection == 1) sec.addItem("Single Section"); else
        for(int i = 0; i < Basic.maxSection; i++)
            sec.addItem(Batch.secName(i));
    }

    String sunMorTips = "A 'Sunday morning' value will let QuickRoutine know that this batches'"+
                        " class will start always on Sunday at morning.";

    String yrTips = "Enter the year of the batch.";
    String semTips = "Enter semester.";
    String secTips = "If you dont' find required section at here, remember that"+
                     " you entered the maximum section value during create a new routine.";
    private JButton jButton1 = new JButton();

    void sunMor_mouseEntered(MouseEvent e) {
        G.setTips(sunMorTips);
    }

    void sunMor_focusGained(FocusEvent e) {
        G.setTips(sunMorTips);
    }

    void yr_focusGained(FocusEvent e) {
        G.setTips(yrTips);
    }

    void yr_mouseEntered(MouseEvent e) {
        G.setTips(yrTips);
    }

    void sem_mouseEntered(MouseEvent e) {
        G.setTips(semTips);
    }

    void sem_focusGained(FocusEvent e) {
        G.setTips(semTips);
    }

    void sec_mouseEntered(MouseEvent e) {
        G.setTips(secTips);
    }

    void sec_focusGained(FocusEvent e) {
        G.setTips(secTips);
    }

    void jButton1_actionPerformed(ActionEvent e) {
        new HelpFrame(HelpFrame.SUNMOR);
    }
}