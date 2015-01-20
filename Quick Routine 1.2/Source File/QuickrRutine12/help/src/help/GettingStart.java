package help;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class GettingStart extends JPanel {
    private VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JPanel jPanel1 = new JPanel();
    private JButton jButton1 = new JButton();
    private TitledBorder titledBorder1;

    public GettingStart() {
        try {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    void jbInit() throws Exception {
        titledBorder1 = new TitledBorder("");
        jLabel1.setFont(new java.awt.Font("DialogInput", 1, 12));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel1.setText("Hello welcome to QuickRoutine.  ");
        this.setLayout(verticalFlowLayout1);
        jLabel2.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel2.setToolTipText("");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("This software will make extremely easy and exciting your one of the " +
    "most time consuming task  - ");
        jLabel3.setText("Making a Class Routine.");
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel4.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setText("And I am always with you as if looking over your shoulder, telling " +
    "what to do when.");
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText("   ");
        jLabel5.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel6.setText("1. To create a new routine - Click \"File\" on menu bar and then click " +
    "\"New\".");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel7.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setText("2. To open a existing routine - Click \"File\" on menu bar and then " +
    "click \"Open Routine\".");
        jButton1.setBorder(titledBorder1);
        jButton1.setText("How to start?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        this.add(jLabel1, null);
        this.add(jLabel2, null);
        this.add(jLabel3, null);
        this.add(jLabel4, null);
        this.add(jLabel5, null);
        this.add(jLabel6, null);
        this.add(jLabel7, null);
        this.add(jPanel1, null);
        jPanel1.add(jButton1, null);
    }

    void jButton1_actionPerformed(ActionEvent e) {
        new HelpFrame(HelpFrame.START);
    }
}