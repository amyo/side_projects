package qrmain;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;
import javax.swing.border.*;
import global.*;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;






public class PrintPreview extends JDialog {
  private BorderLayout borderLayout1 = new BorderLayout();
  //private JPanel myCanvus = new JPanel();
  private PrintCanvus myCanvus = new PrintCanvus();
  public PageFormat pf=null;
  QRMainFrame motherRef;
  public static String rt = "Class Routine of: ";
  public static String rtnData[][][];
  PrinterJob printJob=PrinterJob.getPrinterJob();
  private TitledBorder titledBorder1;
  private JPanel jPanel1 = new JPanel();
  private JPanel jPanel3 = new JPanel();
  private JPanel jPanel4 = new JPanel();
  private JPanel downPan = new JPanel();
  private BorderLayout borderLayout2 = new BorderLayout();
  private JButton jButton3 = new JButton();
  private FlowLayout flowLayout1 = new FlowLayout();
  private JButton jButton1 = new JButton();
  private JPanel buttons = new JPanel();
  private JPanel jPanel2 = new JPanel();
  private JRadioButton jRadioButton1 = new JRadioButton();
  private JLabel jLabel1 = new JLabel();
  private JRadioButton jRadioButton2 = new JRadioButton();
  private ButtonGroup buttonGroup1 = new ButtonGroup();

    public PrintPreview(Frame frame, String title, boolean modal) {
        super(frame, title, modal);
        motherRef = (QRMainFrame)frame;
        if(motherRef.mainEditorRef.getHtmlRoutine() == null) {
            dispose();
            return;
        }
         rt = motherRef.mainEditorRef.getTitle();
        rtnData=motherRef.mainEditorRef.getHtmlRoutine();
      if(pf==null)
      pf=printJob.defaultPage();
      pf.setOrientation(pf.LANDSCAPE);


    try {
      jbInit();

      if(PrintCanvus.isColorFull) jRadioButton1.setSelected(true);
      else jRadioButton2.setSelected(true);

      setSize(717,440);
      G.setCenter(this);
      show();

    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(176, 175, 177)),"Print preview");
    this.getContentPane().setLayout(borderLayout1);
    myCanvus.setBackground(new Color(252, 251, 253));
    myCanvus.setBorder(BorderFactory.createLoweredBevelBorder());
    downPan.setLayout(borderLayout2);
    jButton3.setText("Close");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    jButton1.setText("Print");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    buttons.setLayout(flowLayout1);
    jRadioButton1.setSelected(true);
    jRadioButton1.setText("Colorful Life?");
    jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton1_actionPerformed(e);
      }
    });
    jLabel1.setText("  ");
    jRadioButton2.setText("No, thanks! Black & White. ");
    jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioButton2_actionPerformed(e);
      }
    });
    this.getContentPane().add(myCanvus, BorderLayout.CENTER);
    this.getContentPane().add(jPanel1, BorderLayout.WEST);
    this.getContentPane().add(jPanel3, BorderLayout.EAST);
    this.getContentPane().add(jPanel4, BorderLayout.NORTH);
    this.getContentPane().add(downPan,  BorderLayout.SOUTH);
    buttons.add(jButton1, null);
    buttons.add(jButton3, null);
    downPan.add(jPanel2,  BorderLayout.WEST);
    jPanel2.add(jLabel1, null);
    jPanel2.add(jRadioButton1, null);
    jPanel2.add(jRadioButton2, null);
    downPan.add(buttons,  BorderLayout.EAST);
    buttonGroup1.add(jRadioButton1);
    buttonGroup1.add(jRadioButton2);
  }

  void jButton1_actionPerformed(ActionEvent e) {

    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
    aset.add(OrientationRequested.LANDSCAPE);
    aset.add(MediaSizeName.ISO_A4);
    pf=printJob.pageDialog(aset);
    printJob.setPrintable(myCanvus,pf);
    if(printJob.printDialog(aset)){
      try {
        printJob.print();
      }
      catch (PrinterException ex) {
      }
    }

  }

  void jButton2_actionPerformed(ActionEvent e) {
    pf=printJob.pageDialog(pf);
  }

  void jButton3_actionPerformed(ActionEvent e) {
    dispose();
    System.out.println(""+getSize());
  }

  void jRadioButton1_actionPerformed(ActionEvent e) {
    radiBtnAction();
  }
  public void radiBtnAction(){
    if(jRadioButton1.isSelected())PrintCanvus.isColorFull=true;
    else if(jRadioButton2.isSelected()) PrintCanvus.isColorFull=false;
    myCanvus.repaint();
  }

  void jRadioButton2_actionPerformed(ActionEvent e) {
    radiBtnAction();
  }
}
