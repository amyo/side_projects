package help;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class NewCreate extends JPanel {
    private VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel10 = new JLabel();
    private JLabel jLabel11 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JPanel jPanel1 = new JPanel();
    private JButton jButton1 = new JButton();
    private TitledBorder titledBorder1;

    public NewCreate() {
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
        jLabel1.setText("You are going to make a new semester routine. ");
        this.setLayout(verticalFlowLayout1);
        jLabel2.setText("To generate a class routine automatically you need to provide some " +
    "informations. ");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel2.setToolTipText("");
        jLabel3.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText("Information about - Available rooms, existing teachers and current " +
    "semester courses.");
        jLabel4.setText("To insert this information - Click \"Insert\" on menu bar and then " +
    "click corresponding bar.");
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel5.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText(" ");
        jLabel6.setText("After that you can make part of your routine manually and let QuickRoutine " +
    "generate the rest.");
        jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel6.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel7.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel7.setText("Note that QuickRoutine does not generate lab courses routine automatically.");
        jLabel8.setText("So you need to assign all your lab courses manually. ");
        jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel8.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel9.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel9.setText("To assign courses manually - Click \"Manual\" on menu bar and then " +
    "click corresponding bar.");
        jLabel10.setText(" ");
        jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel10.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel11.setText("During the process of assigning courses either manually or automatically " +
    "always check statistics.");
        jLabel11.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel11.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel12.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel12.setText("To check statistics - Click Either \"Manual\" or \"Automatic\" on menu " +
    "bar and then click \"Report\".");
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
        this.add(jLabel8, null);
        this.add(jLabel9, null);
        this.add(jLabel10, null);
        this.add(jLabel11, null);
        this.add(jLabel12, null);
        this.add(jPanel1, null);
        jPanel1.add(jButton1, null);
    }

    void jButton1_actionPerformed(ActionEvent e) {
        new HelpFrame(HelpFrame.START);
    }
}