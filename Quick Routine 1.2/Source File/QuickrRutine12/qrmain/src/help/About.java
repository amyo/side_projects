package help;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

import global.*;
import help.*;

public class About extends JDialog {
    JPanel panel1 = new JPanel();

    static About myRef;
  private JPanel BASE = new JPanel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JLabel d = new JLabel();
  private JTabbedPane jTabbedPane1 = new JTabbedPane();
  private JPanel jPanel2 = new JPanel();
  private JPanel jPanel3 = new JPanel();
  private JPanel jPanel4 = new JPanel();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JPanel jPanel5 = new JPanel();
  private VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JLabel jLabel12 = new JLabel();
  private BorderLayout borderLayout3 = new BorderLayout();
  private BorderLayout borderLayout4 = new BorderLayout();
  private BorderLayout borderLayout5 = new BorderLayout();
  private JPanel jPanel1 = new JPanel();
  private JPanel jPanel6 = new JPanel();
  private JPanel jPanel7 = new JPanel();
  private JPanel jPanel8 = new JPanel();
  private JPanel jPanel9 = new JPanel();
  private FlowLayout flowLayout1 = new FlowLayout();
  private JButton jButton1 = new JButton();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JPanel jPanel10 = new JPanel();
  private VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  private JLabel jLabel13 = new JLabel();
  private JLabel jLabel14 = new JLabel();
  private JLabel jLabel16 = new JLabel();
  private JLabel jLabel17 = new JLabel();
  private JLabel jLabel18 = new JLabel();
  private JLabel jLabel19 = new JLabel();
  private JScrollPane jScrollPane3 = new JScrollPane();
  private JPanel jPanel11 = new JPanel();
  private VerticalFlowLayout verticalFlowLayout3 = new VerticalFlowLayout();
  private JLabel jLabel20 = new JLabel();
  private JLabel nameVer = new JLabel();
  private JLabel jLabel21 = new JLabel();
  private JLabel jLabel22 = new JLabel();
  private JLabel jLabel4 = new JLabel();

    public About(Frame frame) {
        super(frame, "AboutQuickRoutine", false);

        if(myRef != null) myRef.dispose();
        myRef = this;

        try {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        setSize(400, 290);
        G.setCenter(this);
        show();
        validate();
    }

    private void jbInit() throws Exception {
    panel1.setLayout(borderLayout5);
    BASE.setLayout(borderLayout1);
    d.setText(G.NameVer);
    d.setIcon(new ImageIcon(getClass().getResource("icon/tab.GIF")));
    borderLayout1.setVgap(10);
    jPanel3.setLayout(borderLayout2);
    jPanel5.setLayout(verticalFlowLayout2);
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("Algorithm Design and Core Programming");
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel2.setText("Abdul Kader Jewel, Dept. of CSE, SUST.");
    jLabel5.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel5.setText("E-mail: tojewel@hotmail.com");
    jLabel6.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel6.setText("web: www.justjewel.tk");
    jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel8.setText("Advanced Java Programming");
    jLabel7.setText("  ");
    jLabel9.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel9.setText("Amyo Kabir Simanto, Dept. of CSE, SUST.");
    jLabel12.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel12.setText("E-mail: simantoc@gmail.com");
    jPanel2.setLayout(borderLayout3);
    jPanel4.setLayout(borderLayout4);
    jPanel9.setLayout(flowLayout1);
    jButton1.setText("    OK    ");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    jPanel5.setBackground(new Color(238, 238, 238));
    verticalFlowLayout2.setVgap(0);
    jPanel10.setLayout(verticalFlowLayout1);
    jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel13.setText("Idea of Routine Style Having Consecutive Classes");
    jPanel10.setBackground(new Color(238, 238, 238));
    jLabel14.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel14.setToolTipText("");
    jLabel14.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel14.setText("Dr. Kabir Hossain, Dept. of Statistics, SUST.");
    jLabel16.setText(" ");
    jLabel17.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel17.setText("Requirement Specification and Overall Coordination");
    jLabel18.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel18.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel18.setText("MD. Shahriar Hossain, Dept of CSE, SUST.");
    jLabel19.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel19.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel19.setText("E-mail: shahriar.cse@gamil.com");
    verticalFlowLayout1.setVgap(2);
    jPanel11.setBackground(new Color(238, 238, 238));
    jPanel11.setLayout(verticalFlowLayout3);
    jLabel20.setText(" ");
    nameVer.setHorizontalAlignment(SwingConstants.CENTER);
    nameVer.setText(G.NameVer);
    jLabel21.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel21.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel21.setText("University class scheduler, developed in ");
    jLabel22.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel22.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel22.setText("Dept. of Computer Science and Engineering (CSE),");
    jLabel4.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel4.setText("Shahjalal University of Science and Technology (SUST).");
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    panel1.add(BASE, BorderLayout.CENTER);

    jTabbedPane1.add(jPanel2,   "About");
    jPanel2.add(jScrollPane3, BorderLayout.CENTER);
    jScrollPane3.getViewport().add(jPanel11, null);
    jPanel11.add(jLabel20, null);
    jPanel11.add(nameVer, null);
    jPanel11.add(jLabel21, null);
    jPanel11.add(jLabel22, null);
    jPanel11.add(jLabel4, null);
    jTabbedPane1.add(jPanel3,   "Authors");
    jPanel3.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jPanel5, null);
    jPanel5.add(jLabel1, null);
    jPanel5.add(jLabel2, null);
    jPanel5.add(jLabel5, null);
    jPanel5.add(jLabel6, null);
    jPanel5.add(jLabel7, null);
    jPanel5.add(jLabel8, null);
    jPanel5.add(jLabel9, null);
    jPanel5.add(jLabel12, null);


    jPanel9.add(jButton1, null);
    jTabbedPane1.add(jPanel4,   "Thanks to");
    jPanel4.add(jScrollPane2, BorderLayout.CENTER);
    jScrollPane2.getViewport().add(jPanel10, null);
    jPanel10.add(jLabel13, null);
    jPanel10.add(jLabel14, null);
    jPanel10.add(jLabel16, null);
    jPanel10.add(jLabel17, null);
    jPanel10.add(jLabel18, null);
    jPanel10.add(jLabel19, null);
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    this.getContentPane().add(jPanel6, BorderLayout.WEST);
    this.getContentPane().add(jPanel7, BorderLayout.EAST);
    this.getContentPane().add(jPanel8, BorderLayout.NORTH);
    BASE.add(d, BorderLayout.NORTH);
    BASE.add(jTabbedPane1, BorderLayout.CENTER);
    BASE.add(jPanel9,  BorderLayout.SOUTH);
    }

    void jButton1_actionPerformed(ActionEvent e) {
      G.p(""+getSize());
        dispose();
    }

    void jButton2_actionPerformed(ActionEvent e) {
        new HelpFrame(HelpFrame.HELPUS);
    }
}
