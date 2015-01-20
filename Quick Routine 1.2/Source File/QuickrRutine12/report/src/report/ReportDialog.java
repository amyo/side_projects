package report;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import global.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;

import datastructure.*;
import help.*;

public class ReportDialog extends JDialog {
    private JPanel panel1 = new JPanel();
    private VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
    private JPanel jPanel1 = new JPanel();
    private VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
    private JPanel jPanel2 = new JPanel();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JLabel jLabel1 = new JLabel();
    private JLabel leftLebel = new JLabel();
    private JPanel jPanel3 = new JPanel();
    private JLabel tHourLabel = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private JPanel jPanel4 = new JPanel();
    private JLabel tCreditLabel = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private BorderLayout borderLayout3 = new BorderLayout();
    private TitledBorder titledBorder1;
    private TitledBorder titledBorder2;
    private JPanel jPanel6 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private TitledBorder listTittle;
    private JLabel jLabel7 = new JLabel();
    private JPanel jPanel5 = new JPanel();
    private BorderLayout borderLayout5 = new BorderLayout();
    private VerticalFlowLayout verticalFlowLayout3 = new VerticalFlowLayout();

    /* Table */
    JTable t = new JTable();
    DefaultTableModel tm  = new DefaultTableModel();

    Frame frame;
    int type, _type, nCourse;
    String rName = "", _rName = "", ctName = "";
    Course c[];

    float tCredit = 0;
    int tHour = 0, thLeft = 0;

    void makeReport(int type, Course c[], int nCourse) {
        if(type == Course.THEORY) {
            rName = "Automatic";
            _rName = "Manual";
            ctName = "Theory";
            _type = Course.LAB;
        }
        else {
            rName = "Manual";
            _rName = "Automatic";
            ctName = "Lab";
            _type = Course.THEORY;
        }

        for(int i = 0; i < nCourse; i++)
            if(c[i].type == type) {
                tCredit += c[i].credit;
                tHour += (c[i].nClass * c[i].duration);

                int cl =  c[i].nClass - c[i].manuRCList.nCell - c[i].autoRCList.nCell;
                int hl = (cl * c[i].duration);
                thLeft += hl;

                if(hl > 0) {
                    String s[] = {c[i].id, ""+c[i].credit, ""+c[i].nClass, ""+cl};
                    tm.insertRow(0, s);
                }
            }
    }

    static ReportDialog myRef;

    public ReportDialog(Frame frame, String title, boolean modal,
                        int type, Course c[], int nCourse) {

        super(frame, title, false);

        if(myRef != null) myRef.dispose();
        myRef = this;

        this.frame = frame;
        this.type = type;
        this.c = c;
        this.nCourse = nCourse;


        t.setModel(tm);
        t.removeEditor();
        t.setRequestFocusEnabled(false);
        t.setFont(new Font("DialogInput", 0, 12));
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        String cn[] = {"Course ID", "Credit", "Total Class(s)", "Left Class(s)"};
        tm.setColumnIdentifiers(cn);

        makeReport(type, c, nCourse);

        try {
            jbInit();
            pack();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        G.setCenter(this);
        show();
    }

    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.red,new Color(178, 0, 0)),"Assing Information");
        titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),"Assign Information");
        listTittle = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),"Following Course(s) are Left to Assigned");
        titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),"Note");
        panel1.setLayout(verticalFlowLayout1);
        jPanel1.setLayout(verticalFlowLayout2);
        jPanel2.setLayout(borderLayout1);
        jLabel1.setBackground(Color.white);
        jLabel1.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel1.setText("Total  Course(s) Left to assign:");
        leftLebel.setFont(new java.awt.Font("DialogInput", 0, 12));
        leftLebel.setText(+thLeft+" Hour(s)");
        jPanel3.setLayout(borderLayout2);
        tHourLabel.setFont(new java.awt.Font("DialogInput", 0, 12));
        tHourLabel.setText(+tHour+" Hour(s)");
        jLabel4.setBackground(Color.white);
        jLabel4.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel4.setText("Total "+ctName+" Course\'s Hour(s):");
        jPanel4.setLayout(borderLayout3);
        tCreditLabel.setFont(new java.awt.Font("DialogInput", 0, 12));
        tCreditLabel.setText(+tCredit+" Credit(s)");
        jLabel6.setBackground(Color.white);
        jLabel6.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel6.setText("Total "+ctName+" Course\'s Credit(s):");
        verticalFlowLayout1.setHgap(10);
        jPanel1.setBorder(titledBorder2);
        jPanel6.setLayout(verticalFlowLayout3);
        jPanel6.setBorder(listTittle);
        jPanel6.setPreferredSize(new Dimension(520, 150));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel7.setText("Report of "+rName+" Routine:");
        jPanel5.setLayout(borderLayout5);
        verticalFlowLayout3.setVerticalFill(true);
        jScrollPane1.setPreferredSize(new Dimension(500, 110));
        listTittle.setTitle("Following "+ctName+" Course(s) are Left to Assigned");
        jPanel7.setBorder(titledBorder3);
        jPanel7.setLayout(verticalFlowLayout4);
        jLabel2.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel2.setText("QuickRoutine does not generate lab courses\' routine automatically.");
        jLabel3.setFont(new java.awt.Font("DialogInput", 0, 12));
        jLabel3.setText("So you must assign all lab courses manually.");
        jPanel8.setLayout(flowLayout1);
        jButton2.setMnemonic('H');
        jButton2.setText("Help");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        jButton3.setMnemonic('C');
        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton3_actionPerformed(e);
            }
        });
        jPanel9.setLayout(flowLayout2);
        flowLayout2.setAlignment(FlowLayout.LEFT);
        jButton1.setMnemonic('S');
        jButton1.setText("Show "+_rName+" Routine Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        getContentPane().add(panel1);
        panel1.add(jLabel7, null);
        panel1.add(jPanel1, null);
        jPanel3.add(jLabel4, BorderLayout.WEST);
        jPanel3.add(tHourLabel, BorderLayout.EAST);
        jPanel4.add(jLabel6, BorderLayout.WEST);
        jPanel4.add(tCreditLabel, BorderLayout.EAST);
        jPanel1.add(jPanel4, null);
        jPanel1.add(jPanel3, null);
        jPanel1.add(jPanel2, null);
        jPanel2.add(jLabel1, BorderLayout.WEST);
        jPanel2.add(leftLebel, BorderLayout.EAST);
        panel1.add(jPanel6, null);
        jPanel6.add(jScrollPane1, null);
        panel1.add(jPanel7, null);
        jPanel7.add(jLabel2, null);
        jPanel7.add(jLabel3, null);
        panel1.add(jPanel5, null);
        jPanel5.add(jPanel8,  BorderLayout.EAST);
        jPanel8.add(jButton3, null);
        jPanel8.add(jButton2, null);
        jPanel5.add(jPanel9,  BorderLayout.WEST);
        jPanel9.add(jButton1, null);
        jScrollPane1.getViewport().add(t,  null);
    }

    void ok_actionPerformed(ActionEvent e) {
        dispose();
    }

    void jButton1_actionPerformed(ActionEvent e) {
        dispose();
        new ReportDialog(frame, _rName+"RoutineReport", true, _type, c, nCourse);
    }
    private JPanel jPanel7 = new JPanel();
    private TitledBorder titledBorder3;
    private VerticalFlowLayout verticalFlowLayout4 = new VerticalFlowLayout();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JPanel jPanel8 = new JPanel();
    private FlowLayout flowLayout1 = new FlowLayout();
    private JButton jButton2 = new JButton();
    private JButton jButton3 = new JButton();
    private JPanel jPanel9 = new JPanel();
    private FlowLayout flowLayout2 = new FlowLayout();
    private JButton jButton1 = new JButton();

    void jButton3_actionPerformed(ActionEvent e) {
        dispose();
    }

    void jButton2_actionPerformed(ActionEvent e) {
        new HelpFrame(HelpFrame.REPORT);
    }
}