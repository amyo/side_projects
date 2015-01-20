package qrmain;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.*;
import java.awt.print.*;

import qrinput.*;
import global.*;
import report.*;
import java.beans.*;

import datastructure.*;
import help.*;
import java.io.*;

public class QRMainFrame extends JFrame
{


    MyFileFilter rtnExt=new MyFileFilter("rtn");

    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenuFile = new JMenu();
    JMenuItem jMenuFileExit = new JMenuItem();
    JMenu insert = new JMenu();
    JMenuItem jMenuHelpAbout = new JMenuItem();
    JToolBar jToolBar = new JToolBar();


    ImageIcon oi;
    ImageIcon hi;
    ImageIcon si;
    ImageIcon noi;
    ImageIcon printer;

    ImageIcon roomT;
    ImageIcon batchT;
    ImageIcon teacherT;
    ImageIcon courseT;

    ImageIcon manualB;
    ImageIcon manualT;
    ImageIcon manualR;

    ImageIcon autoMake;
    ImageIcon autoRemove;
    ImageIcon autoR;
    ImageIcon about;

    void loadIcon() {
        try {
            hi = new ImageIcon(getClass().getResource("icon/help.GIF"));
            oi = new ImageIcon(getClass().getResource("icon/open.GIF"));
            si = new ImageIcon(getClass().getResource("icon/save.GIF"));
            noi = new ImageIcon(getClass().getResource("icon/new.GIF"));
            printer = new ImageIcon(getClass().getResource("icon/printer.GIF"));

            roomT = new ImageIcon(getClass().getResource("icon/roomt.GIF"));
            batchT = new ImageIcon(getClass().getResource("icon/batcht.GIF"));
            teacherT = new ImageIcon(getClass().getResource("icon/teachert.GIF"));
            courseT = new ImageIcon(getClass().getResource("icon/courset.GIF"));

            manualB = new ImageIcon(getClass().getResource("icon/manulb.GIF"));
            manualT = new ImageIcon(getClass().getResource("icon/manult.GIF"));
            manualR = new ImageIcon(getClass().getResource("icon/manulr.GIF"));

            autoMake = new ImageIcon(getClass().getResource("icon/automake.GIF"));
            autoRemove = new ImageIcon(getClass().getResource("icon/autoremove.GIF"));
            autoR = new ImageIcon(getClass().getResource("icon/manulr.GIF"));
            about = new ImageIcon(getClass().getResource("icon/about.GIF"));
        }
        catch (Exception ex) {
            MyError.debug("Cant' Load Icons in QRMainFram.");
        }
    }

    //tree...................
    DefaultMutableTreeNode root =new DefaultMutableTreeNode("Routine");

    DefaultMutableTreeNode tBatch=new DefaultMutableTreeNode("Batch");
    DefaultMutableTreeNode tTeacher=new DefaultMutableTreeNode("Teacher");
    DefaultMutableTreeNode tRoom=new DefaultMutableTreeNode("Room");

    /*DefaultMutableTreeNode lB1=new DefaultMutableTreeNode("1/1-A");
    DefaultMutableTreeNode lB2=new DefaultMutableTreeNode("1/1-B");

    DefaultMutableTreeNode lT1=new DefaultMutableTreeNode("UKB");
    DefaultMutableTreeNode lT2=new DefaultMutableTreeNode("MZI");

    DefaultMutableTreeNode lR1=new DefaultMutableTreeNode("309A");
    DefaultMutableTreeNode lR2=new DefaultMutableTreeNode("309B");*/


    JSplitPane jSplitPane1 = new JSplitPane();

    JPanel contentPane;
    JPanel jPanel5 = new JPanel();
    JPanel jPanel1 = new JPanel();

    BorderLayout borderLayout4 = new BorderLayout();
    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout5 = new BorderLayout();

    GridLayout gridLayout1 = new GridLayout();
    //VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();

    TitledBorder titledBorder1;

    Border border1;
    Border border2;
    private JMenuItem jMenuItem1 = new JMenuItem();
    private JMenuItem jMenuItem2 = new JMenuItem();
    private JMenuItem jMenuItem3 = new JMenuItem();
    private JMenu manu = new JMenu();
    private JMenuItem jMenuItem4 = new JMenuItem();
    private JMenuItem jMenuItem5 = new JMenuItem();
    private JMenu auto = new JMenu();
    private JMenuItem jMenuItem9 = new JMenuItem();
    private JMenuItem sm = new JMenuItem();
    private JMenuItem jMenuItem13 = new JMenuItem();
    private JMenuItem jMenuItem16 = new JMenuItem();
    private JMenu jMenu6 = new JMenu();
    private JMenuItem jMenuItem17 = new JMenuItem();
    private JMenuItem jMenuItem18 = new JMenuItem();
    private JPanel jPanel3 = new JPanel();
    private JSplitPane jSplitPane3 = new JSplitPane();
    private BorderLayout borderLayout6 = new BorderLayout();
    private JTree jTree1 ;

    public MainEditor mainEditorRef = new MainEditor(this);
    private VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
    private Border border3;
    private Border border4;
    private JMenuItem print = new JMenuItem();
    private Border border5;
    private JMenuItem jMenuItem6 = new JMenuItem();
    private JMenuItem jMenuItem7 = new JMenuItem();
    private JPanel jPanel2 = new JPanel();
    private JButton Xopen = new JButton();
    private FlowLayout flowLayout1 = new FlowLayout();
    private JButton save = new JButton();
    private JPanel jPanel8 = new JPanel();
    private FlowLayout flowLayout2 = new FlowLayout();
    private JPanel insertTool = new JPanel();
    private FlowLayout flowLayout3 = new FlowLayout();
    private JButton roomInfo = new JButton();
    private JButton batchInfo = new JButton();
    private JButton teacherInfo = new JButton();
    private JButton courseInfo = new JButton();
    private JPanel jPanel7 = new JPanel();
    private FlowLayout flowLayout4 = new FlowLayout();
    private JButton manT = new JButton();
    private FlowLayout flowLayout5 = new FlowLayout();
    private JButton help1 = new JButton();
    private JPanel jPanel9 = new JPanel();
    private JButton manB = new JButton();
    private JButton manR = new JButton();
    private FlowLayout flowLayout6 = new FlowLayout();
    private JButton autoRe = new JButton();
    private JButton autoReport = new JButton();
    private JPanel jPanel10 = new JPanel();
    private JButton autoM = new JButton();
    private JMenuItem jMenuItem8 = new JMenuItem();
  JButton Xopen1 = new JButton();
  JButton newOpen = new JButton();



    // MainEditor mainEditorRef = new MainEditor();
   // MainEditor mainEditorRef = new MainEditor();

    /**Construct the frame*/
    public QRMainFrame()
    {
       // MyError.debug(""+getClass().getResource("icon/help.JPG"));
        G.mfRef = this;
        loadIcon();

        //Bangla delay.........................................................
        try {
                   Thread.currentThread().sleep(0);
            }
            catch (InterruptedException ex) {
            }

        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try
        {
            jbInit();
            //setIconImage(title.getImage());
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        setEnableMenu(false);
        jSplitPane1.add(new GettingStart(), JSplitPane.BOTTOM);
        //jSplitPane1.add(mainEditorRef, JSplitPane.BOTTOM);

        try {
            setIconImage( new ImageIcon( getClass().getResource("icon/logo.gif")).getImage() );
        }
        catch (Exception ex) {
            MyError.debug("Cant' Load menu icon.");
        }

        setSize(950, 500);
        G.setCenter(this);
        setEnableMenu(loadRoutine());
        show();
        if(G.helpFrame)
           new HelpFrame(HelpFrame.WHAT).show();

    }

    /* ###################################################################################### */
    public void deleteAllCatChildren() {
        //jTree1.removeAll();
        root.remove(tBatch);
        root.remove(tTeacher);
        root.remove(tRoom);
//        jTree1.add(root);
    }

    public void fakaKor(DefaultMutableTreeNode tNode) {
        while(tNode.getChildCount() > 0)
            tNode.remove(0);
    }

    public void reloadTree() {
        //IO.readAll2G();

        /* Batch. */
        if(root.getChildCount()<0)
        deleteAllCatChildren();
        tBatch.removeAllChildren();
//        fakaKor(tBatch);
        for(int i = 0; i < G.nBatch; i++)
            tBatch.add(new DefaultMutableTreeNode( G.b[i].getNickName() ));

        /* Teacher. */
        tTeacher.removeAllChildren();
//        fakaKor(tTeacher);
        for(int i = 0; i < G.nTeacher; i++)
            tTeacher.add(new DefaultMutableTreeNode( G.t[i].id ));

        /* Room. */
        tRoom.removeAllChildren();
//        fakaKor(tRoom);
        for(int i = 0; i < G.nRoom; i++)
            tRoom.add(new DefaultMutableTreeNode( G.r[i].id ) );

       /* if(jTree1 != null) {
            // jTree1.update(jTree1.getGraphics());
            jTree1.setVisible(false);
            MyError.debug("Updating tree...");
            jTree1.setVisible(true);
        }
        */
        root.add(tBatch);
        root.add(tTeacher);
        root.add(tRoom);
       jTree1= new JTree(root);
       jPanel1.removeAll();
       jPanel1.add(new JScrollPane( jTree1 ), null);
       jTree1.setCellRenderer(new MyTreeCellRenderer());

       jTree1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                //jTextArea1.setText(""+jTree1.getSelectionPath());
            }
            public void mouseClicked(MouseEvent e) {
                jTree1_mouseClicked(e);
            }
        });

       jPanel1.validate();
       setSize(getWidth(), getHeight()+50);
       setSize(getWidth(), getHeight()-50);
    }

    public Point getSelectedTreeIndex() {
        TreePath tp = jTree1.getSelectionPath();

        /* No node selected. */
        if(tp == null) return null;

        int i = -1, j = -1;
        if(tp.getPathCount() == 3) {
            if("Batch".equalsIgnoreCase( ""+tp.getPathComponent(1) )) {
                i = 0;
                for(int k = 0; k < G.nBatch; k++)
                    if(G.b[k].getNickName().endsWith(""+tp.getPathComponent(2))) {
                        j = k;
                        break;
                    }
            }
            else if("Teacher".equalsIgnoreCase( ""+tp.getPathComponent(1) )) {
                i = 1;
                for(int k = 0; k < G.nTeacher; k++)
                    if(G.t[k].id.endsWith(""+tp.getPathComponent(2))) {
                        j = k;
                        break;
                    }
            }
            else if("Room".equalsIgnoreCase( ""+tp.getPathComponent(1) )) {
                i = 2;
                for(int k = 0; k < G.nRoom; k++)
                    if(G.r[k].id.endsWith(""+tp.getPathComponent(2))) {
                        j = k;
                        break;
                    }
            }


            return new Point(i, j);
        }

        return null;
    }

    /* ###################################################################################### */

    /**Component initialization*/
    private void jbInit() throws Exception  {



        border3 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(124, 124, 124),new Color(178, 178, 178));
        border4 = BorderFactory.createEtchedBorder(Color.lightGray,new Color(178, 178, 178));
        border5 = BorderFactory.createEtchedBorder(Color.white,new Color(178, 178, 178));
        this.setForeground(UIManager.getColor("info"));
        jMenuItem1.setToolTipText("Insert all the batch exist current semester");
        jMenuItem1.setIcon(batchT);
        jMenuItem1.setMnemonic('B');
        jMenuItem1.setText("Batch Information");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem1_actionPerformed(e);
            }
        });
        jMenuItem2.setToolTipText("Insert information of all major and minor teacher");
        jMenuItem2.setIcon(teacherT);
        jMenuItem2.setMnemonic('T');
        jMenuItem2.setText("Teacher Information");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem2_actionPerformed(e);
            }
        });
        jMenuItem3.setToolTipText("Insert course information of all the batch");
        jMenuItem3.setIcon(courseT);
        jMenuItem3.setMnemonic('C');
        jMenuItem3.setText("Course Information");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem3_actionPerformed(e);
            }
        });
        manu.setToolTipText("Make a part of routine manually before genarate atumatically by QuickRoutine");
        manu.setMnemonic('M');
        manu.setText("Manual");
        jMenuItem4.setToolTipText("Set manual routine by batch wise");
        jMenuItem4.setIcon(manualB);
        jMenuItem4.setMnemonic('B');
        jMenuItem4.setText("Batch Routine");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem4_actionPerformed(e);
            }
        });
        jMenuItem5.setToolTipText("Set manual routine by teacher wise");
        jMenuItem5.setIcon(manualT);
        jMenuItem5.setMnemonic('T');
        jMenuItem5.setText("Teacher Routine");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem5_actionPerformed(e);
            }
        });
        jMenuFile.setMnemonic('F');
        jMenuFileExit.setToolTipText("Make new routine");
        jMenuFileExit.setIcon(noi);
        jMenuFileExit.setMnemonic('N');
        //jMenuFileExit.setRolloverIcon(image1);
        insert.setToolTipText("Insert basic information to make a routine");
        insert.setMnemonic('I');
        jMenuHelpAbout.setToolTipText("Insert room information about available room");
        jMenuHelpAbout.setIcon(roomT);
        jMenuHelpAbout.setMnemonic('R');
        jMenuHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpAbout_actionPerformed(e);
            }
        });
        auto.setMnemonic('A');
        auto.setText("Automatic");
        jMenuItem9.setToolTipText("Open existing routine file");
        jMenuItem9.setIcon(oi);
        jMenuItem9.setMnemonic('O');
        jMenuItem9.setText("Open Routine");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem9_actionPerformed(e);
            }
        });
        sm.setToolTipText("Overwrite on current file");
        sm.setIcon(si);
        sm.setMnemonic('S');
        sm.setText("Save");
        sm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sm_actionPerformed(e);
            }
        });
        jMenuItem13.setToolTipText("Exit QuickRoutine");
        jMenuItem13.setMnemonic('X');
        jMenuItem13.setText("Exit");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem13_actionPerformed(e);
            }
        });
        jMenuItem16.setToolTipText("Show manual routine information");
        jMenuItem16.setIcon(manualR);
        jMenuItem16.setMnemonic('R');
        jMenuItem16.setText("Report");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem16_actionPerformed(e);
            }
        });
        jMenu6.setMnemonic('H');
        jMenu6.setText("Help");
        jMenuItem17.setIcon(hi);
        jMenuItem17.setMnemonic('T');
        jMenuItem17.setText("Help Topics");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem17_actionPerformed(e);
            }
        });
        jMenuItem18.setIcon(autoR);
        jMenuItem18.setMnemonic('R');
        jMenuItem18.setText("Report");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem18_actionPerformed(e);
            }
        });
        jSplitPane1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(InputMethodEvent e) {
            }
            public void caretPositionChanged(InputMethodEvent e) {
                jSplitPane1_caretPositionChanged(e);
            }
        });
        jSplitPane1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
                jSplitPane1_vetoableChange(e);
            }
        });
        jPanel3.setLayout(borderLayout6);
        jSplitPane3.setBorder(null);
        jSplitPane3.setContinuousLayout(true);
        jSplitPane3.setDividerSize(10);

        verticalFlowLayout2.setHgap(4);
        verticalFlowLayout2.setVgap(10);
        verticalFlowLayout2.setVerticalFill(true);

       /* tBatch.add(lB1);
        tBatch.add(lB2);
        tTeacher.add(lT1);
        tTeacher.add(lT2);
        tRoom.add(lR1);
        tRoom.add(lR2);*/

        jPanel1.setBorder(BorderFactory.createEtchedBorder());
        reloadTree();

        jTree1.setBackground(new Color(255, 255, 245));
        jTree1.setBorder(border5);

        jTree1.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                //jTextArea1.setText(""+jTree1.getSelectionPath());
            }
            public void mouseClicked(MouseEvent e) {
                jTree1_mouseClicked(e);
            }
        });
         jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                jTree1_valueChanged(e);
            }
        });

        jTree1.setCellRenderer(new MyTreeCellRenderer());


        //setIconImage(Toolkit.getDefaultToolkit().createImage(QRMainFrame.class.getResource("[Your Icon]")));
        contentPane = (JPanel) this.getContentPane();
        titledBorder1 = new TitledBorder("");
        border1 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,Color.white,Color.white);
        border2 = BorderFactory.createBevelBorder(BevelBorder.RAISED,new Color(58, 107, 168),UIManager.getColor("Desktop.background"),new Color(28, 52, 82),new Color(19, 36, 57));
        contentPane.setLayout(borderLayout1);
        this.setSize(new Dimension(900, 600));
        this.setTitle(G.NameVer);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
        jMenuFile.setText("File");
        jMenuFileExit.setText("New");
        jMenuFileExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                jMenuFileExit_mousePressed(e);
            }
        });
        jMenuFileExit.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e) {
                jMenuFileExit_actionPerformed(e);
            }
        });
        insert.setText("Insert");
        jMenuHelpAbout.setText("Room Information");

        jToolBar.setBorder(null);
        jToolBar.setFloatable(false);
        jPanel1.setLayout(verticalFlowLayout2);
        jSplitPane1.setBorder(null);
        jSplitPane1.setVerifyInputWhenFocusTarget(false);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setDividerSize(1);
        //mainEditorRef.setLayout(borderLayout4);
        //mainEditorRef.setBackground(SystemColor.menu);
        //mainEditorRef.setLayout(borderLayout5);
        jPanel5.setLayout(gridLayout1);
        jMenuBar1.setBorder(null);
        gridLayout1.setColumns(2);
        gridLayout1.setHgap(-5);
        gridLayout1.setVgap(-5);
        print.setIcon(printer);
        print.setMnemonic('P');
        print.setText("Print");
        print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                print_mousePressed(e);
            }
        });
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                print_actionPerformed(e);
            }
        });
        jMenuItem6.setIcon(autoMake);
        jMenuItem6.setMnemonic('M');
        jMenuItem6.setText("Make Routine");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem6_actionPerformed(e);
            }
        });
        jMenuItem7.setIcon(autoRemove);
        jMenuItem7.setMnemonic('E');
        jMenuItem7.setText("Remove Routine");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem7_actionPerformed(e);
            }
        });
        Xopen.setVerticalAlignment(SwingConstants.TOP);
        Xopen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Xopen_actionPerformed(e);
            }
        });
        Xopen.setBorder(null);
        Xopen.setToolTipText("Print selected routine");
        Xopen.setIcon(printer);
        flowLayout1.setAlignment(FlowLayout.LEFT);
        save.setIcon(si);
        save.setVerticalAlignment(SwingConstants.TOP);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save_actionPerformed(e);
            }
        });
        save.setBorder(null);
        save.setToolTipText("Save routine");
        jPanel8.setLayout(flowLayout1);
        jPanel2.setLayout(flowLayout2);
        flowLayout2.setAlignment(FlowLayout.LEFT);
        flowLayout2.setHgap(-5);
        flowLayout2.setVgap(-5);
        insertTool.setLayout(flowLayout3);
        flowLayout3.setAlignment(FlowLayout.LEFT);
        roomInfo.setToolTipText("Room Information");
        roomInfo.setBorder(null);
        roomInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                roomInfo_actionPerformed(e);
            }
        });
        roomInfo.setVerticalAlignment(SwingConstants.TOP);
        roomInfo.setIcon(roomT);
        batchInfo.setIcon(batchT);
        batchInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                batchInfo_actionPerformed(e);
            }
        });
        batchInfo.setVerticalAlignment(SwingConstants.TOP);
        batchInfo.setBorder(null);
        batchInfo.setToolTipText("Batch Information");
        teacherInfo.setToolTipText("Teacher Information");
        teacherInfo.setBorder(null);
        teacherInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teacherInfo_actionPerformed(e);
            }
        });
        teacherInfo.setVerticalAlignment(SwingConstants.TOP);
        teacherInfo.setIcon(teacherT);
        courseInfo.setToolTipText("Course Information");
        courseInfo.setBorder(null);
        courseInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                courseInfo_actionPerformed(e);
            }
        });
        courseInfo.setVerticalAlignment(SwingConstants.TOP);
        courseInfo.setIcon(courseT);
        jPanel5.setBorder(BorderFactory.createEtchedBorder());
        jPanel8.setBorder(BorderFactory.createEtchedBorder());
        jPanel8.setMinimumSize(new Dimension(165, 15));
        insertTool.setBorder(BorderFactory.createEtchedBorder());
        jPanel7.setLayout(flowLayout4);
        manT.setToolTipText("Manual teacher routine");
        manT.setBorder(null);
        manT.setVerticalAlignment(SwingConstants.TOP);
        manT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manT_actionPerformed(e);
            }
        });
        manT.setIcon(manualT);
        help1.setIcon(hi);
        help1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                help1_actionPerformed(e);
            }
        });
        help1.setVerticalAlignment(SwingConstants.TOP);
        help1.setBorder(null);
        help1.setToolTipText("Help");
        jPanel9.setLayout(flowLayout5);
        jPanel7.setBorder(BorderFactory.createEtchedBorder());
        manB.setIcon(manualB);
        manB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manB_actionPerformed(e);
            }
        });
        manB.setVerticalAlignment(SwingConstants.TOP);
        manB.setBorder(null);
        manB.setToolTipText("Manual batch routine");
        manR.setIcon(manualR);
        manR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manR_actionPerformed(e);
            }
        });
        manR.setVerticalAlignment(SwingConstants.TOP);
        manR.setBorder(null);
        manR.setToolTipText("Manual  routine report");
        autoRe.setIcon(autoRemove);
        autoRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autoRe_actionPerformed(e);
            }
        });
        autoRe.setVerticalAlignment(SwingConstants.TOP);
        autoRe.setBorder(null);
        autoRe.setToolTipText("Remove automatic routine");
        autoReport.setToolTipText("Automatic routine report");
        autoReport.setBorder(null);
        autoReport.setVerticalAlignment(SwingConstants.TOP);
        autoReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autoReport_actionPerformed(e);
            }
        });
        autoReport.setIcon(autoR);
        jPanel10.setBorder(BorderFactory.createEtchedBorder());
        jPanel10.setLayout(flowLayout6);
        autoM.setIcon(autoMake);
        autoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autoM_actionPerformed(e);
            }
        });
        autoM.setVerticalAlignment(SwingConstants.TOP);
        autoM.setBorder(null);
        autoM.setToolTipText("Make routine");
        jMenuItem8.setIcon(about);
        jMenuItem8.setMnemonic('A');
        jMenuItem8.setText("About");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem8_actionPerformed(e);
            }
        });
        Xopen1.setToolTipText("Open existing routine");
    Xopen1.setBorder(null);
    Xopen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Xopen1_actionPerformed(e);
            }
        });
    Xopen1.setVerticalAlignment(SwingConstants.TOP);
    Xopen1.setIcon(oi);
    newOpen.setIcon(noi);
    newOpen.setVerticalAlignment(SwingConstants.TOP);
    newOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newOpen_actionPerformed(e);
            }
        });
    newOpen.setBorder(null);
    newOpen.setToolTipText("Create new routine");
    contentPane.add(jSplitPane1,  BorderLayout.CENTER);
        jSplitPane1.add(jPanel1, JSplitPane.TOP);
        jPanel1.add(jTree1, null);
        jMenuFile.add(jMenuFileExit);
        jMenuFile.add(jMenuItem9);
        jMenuFile.addSeparator();
        jMenuFile.add(sm);
        jMenuFile.add(print);
        jMenuFile.addSeparator();
        jMenuFile.add(jMenuItem13);
        insert.add(jMenuHelpAbout);
        insert.add(jMenuItem1);
        insert.add(jMenuItem2);
        insert.addSeparator();
        insert.add(jMenuItem3);
        jMenuBar1.add(jMenuFile);
        jMenuBar1.add(insert);
        jMenuBar1.add(manu);
        jMenuBar1.add(auto);
        jMenuBar1.add(jMenu6);
        this.setJMenuBar(jMenuBar1);
        contentPane.add(jToolBar, BorderLayout.NORTH);
        jToolBar.add(jPanel5, null);
        jPanel5.add(jPanel2, null);
        jPanel2.add(jPanel8, null);
        jPanel8.add(newOpen, null);
    jPanel8.add(Xopen1, null);
        jPanel8.add(Xopen, null);
        jPanel8.add(save, null);
        jPanel10.add(autoM, null);
        jPanel10.add(autoRe, null);
        jPanel10.add(autoReport, null);
        jPanel2.add(insertTool, null);
        insertTool.add(roomInfo, null);
        insertTool.add(batchInfo, null);
        insertTool.add(teacherInfo, null);
        insertTool.add(courseInfo, null);
        jPanel2.add(jPanel7, null);
        jPanel7.add(manB, null);
        jPanel7.add(manT, null);
        jPanel7.add(manR, null);

        jPanel2.add(jPanel10, null);
        jPanel2.add(jPanel9, null);
        jPanel9.add(help1, null);
        manu.add(jMenuItem4);
        manu.add(jMenuItem5);
        manu.addSeparator();
        manu.add(jMenuItem16);
        jMenu6.add(jMenuItem17);
        jMenu6.addSeparator();
        jMenu6.add(jMenuItem8);
        auto.add(jMenuItem6);
        auto.add(jMenuItem7);
        auto.addSeparator();
        auto.add(jMenuItem18);
        jSplitPane1.setDividerLocation(150);
        jSplitPane3.setDividerLocation(125);
        jSplitPane3.add(jPanel3, JSplitPane.LEFT);
        reloadTree();
    }





    public void jMenuFileExit_actionPerformed(ActionEvent e)
    {
        new newRoutine(this,"NewRoutine",true);
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            jMenuFileExit_actionPerformed(null);
        }
    }

    void jMenuHelpAbout_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 0, -1, mainEditorRef);
    }

    void jMenuItem1_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 1, -1, mainEditorRef);
    }

    void jMenuItem2_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 2, -1, mainEditorRef);
    }

    void jMenuItem3_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 3, -1, mainEditorRef);
    }

    void jMenuItem4_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 4, 0, mainEditorRef);
    }

    void jMenuItem5_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 4, 1, mainEditorRef);
    }

    void jSplitPane1_caretPositionChanged(InputMethodEvent e) {
        G.p("Caret vai\n");
    }

    void jSplitPane1_vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
        G.p("Vota vai\n");
    }
    void jTree2_valueChanged(TreeSelectionEvent e) {

    }
    void jTree2_mouseClicked(MouseEvent e) {

    }
    void jTree2_mousePressed(MouseEvent e) {

    }
    void jTree2_mouseReleased(MouseEvent e) {

    }
    void jTree2_mouseEntered(MouseEvent e) {

    }
    void jTree2_mouseExited(MouseEvent e) {

    }

    void jTree1_valueChanged(TreeSelectionEvent e) {
        // mainEditorRef.openRoutine(getSelectedTreeIndex());
    }

    void jTree1_mouseClicked(MouseEvent e) {
        mainEditorRef.openRoutine(getSelectedTreeIndex());
    }

    public void setEnableMenu(boolean value) {
        sm.setVisible(value);
        print.setVisible(value);

        insert.setVisible(value);
        manu.setVisible(value);
        auto.setVisible(value);

        save.setVisible(value);

        roomInfo.setVisible(value);
        batchInfo.setVisible(value);
        teacherInfo.setVisible(value);
        courseInfo.setVisible(value);

        manB.setVisible(value);
        manT.setVisible(value);
        manR.setVisible(value);



        jPanel10.setVisible(value);
        jPanel7.setVisible(value);
        insertTool.setVisible(value);
    }

    void openRoutine() {

         JFileChooser fc = new JFileChooser();
         fc.addChoosableFileFilter(rtnExt);
         fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
         RoutinePath rtnP=IO.readRoutinePath();
         if(rtnP!=null){
           fc.setSelectedFile(new File(rtnP.loc+"/"+rtnP.name+".rtn"));
         }
         int result = fc.showOpenDialog(this);

         if(result==JFileChooser.CANCEL_OPTION)
             return;

         if(result == -1)
             return;

         setTitle(G.NameVer + " - " + fc.getSelectedFile() );
         setEnableMenu(true);

         G.name =fc.getSelectedFile().getName();
         G.name =G.name.substring(0, G.name.length() - 4);

         G.loc =(fc.getSelectedFile().getParent());
         G.p(" \nFile Name"+G.name+" Loc:"+G.loc);
         RoutinePath rtnPath=new  RoutinePath(G.name,G.loc);
         rtnPath.helpFrame=G.helpFrame;
         IO.writeRoutinePath(rtnPath);

         IO.readAll2G();

         mainEditorRef.res.closeAll();
         mainEditorRef.reloadData(false, false, false);
    }

    boolean loadRoutine() {
      RoutinePath rtnPath=IO.readRoutinePath();
      if(rtnPath!=null){
        if(!rtnPath.loc.equals("")&!rtnPath.name.equals("")){
          G.name=rtnPath.name;
          G.loc=rtnPath.loc;
          IO.readAll2G();
          mainEditorRef.res.closeAll();
          mainEditorRef.reloadData(false, false, false);
          setTitle(G.NameVer + " - " + G.loc + "\\" + G.name + ".rtn");
        }

        G.helpFrame=rtnPath.helpFrame;

        return true;
      }
      return false;
    }

    void jMenuItem9_actionPerformed(ActionEvent e) {
        openRoutine();
    }

    void jMenuFileExit_mousePressed(MouseEvent e) {
        // new newRoutine(this,"NewRoutine",true);
    }






    void print_actionPerformed(ActionEvent e)
    {
       // MyError.debug("Print option is not avilable yet.");
        new PrintPreview(this,"PrintPreview",true);
       // new PPrev(this, "PrintRoutine", true);
    }

    void print_mousePressed(MouseEvent e) {
         //new PrintPreview(this,"Print Preview",true);
    }

    void this_windowClosing(WindowEvent e) {
        System.exit(0);
    }

    void jMenuItem10_actionPerformed(ActionEvent e) {
        G.nRoom = G.nBatch = G.nTeacher = G.nCourse = 0;
        mainEditorRef.res.closeAll();
        mainEditorRef.reloadData(false, false, false);
        setTitle(G.NameVer);
        setEnableMenu(false);
    }

    void sm_actionPerformed(ActionEvent e) {
        IO.writeAllFromG();
    }

    void jMenuItem13_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    void jMenuItem16_actionPerformed(ActionEvent e) {
        new ReportDialog(this, "ManualRoutineReport", true,
                         Course.LAB,
                         (Course[])mainEditorRef.cp.temp,
                         mainEditorRef.cp.nData
                         );
    }

    void jMenuItem18_actionPerformed(ActionEvent e) {
        new ReportDialog(this, "AutomaticRoutineReport", true,
                         Course.THEORY,
                         (Course[])mainEditorRef.cp.temp,
                         mainEditorRef.cp.nData
                         );
    }

    void jMenuItem6_actionPerformed(ActionEvent e) {
        mainEditorRef.makeAuto();
    }

    void jMenuItem7_actionPerformed(ActionEvent e) {
        mainEditorRef.removeAuto();
    }

    void jMenuItem17_actionPerformed(ActionEvent e) {
      new HelpFrame(HelpFrame.HOME);
    }

    void newOpen_actionPerformed(ActionEvent e) {
        new newRoutine(this,"NewRoutine",true);
    }

    void Xopen_actionPerformed(ActionEvent e) {
       new PrintPreview(this,"PrintPreview",true);
    }

    void save_actionPerformed(ActionEvent e) {
        IO.writeAllFromG();
    }

    void manT_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 4, 1, mainEditorRef);
    }
    void roomInfo_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 0, -1, mainEditorRef);
    }
    void batchInfo_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 1, -1, mainEditorRef);
    }
    void teacherInfo_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 2, -1, mainEditorRef);
    }
    void courseInfo_actionPerformed(ActionEvent e) {
        new QRIDialog(this, "Insert information", true, 3, -1, mainEditorRef);
    }
    void help1_actionPerformed(ActionEvent e) {
         new HelpFrame(HelpFrame.HOME);
    }
    void manB_actionPerformed(ActionEvent e) {
         new QRIDialog(this, "Insert information", true, 4, 0, mainEditorRef);
    }
    void manR_actionPerformed(ActionEvent e) {
        new ReportDialog(this, "ManualRoutineReport", true,
                        Course.LAB,
                        (Course[])mainEditorRef.cp.temp,
                        mainEditorRef.cp.nData
                         );
    }
    void autoRe_actionPerformed(ActionEvent e) {
         mainEditorRef.removeAuto();
    }
    void autoReport_actionPerformed(ActionEvent e) {
        new ReportDialog(this, "AutomaticRoutineReport", true,
                        Course.THEORY,
                        (Course[])mainEditorRef.cp.temp,
                        mainEditorRef.cp.nData
                         );
    }
    void autoM_actionPerformed(ActionEvent e) {
        mainEditorRef.makeAuto();
    }

    void jMenuItem8_actionPerformed(ActionEvent e) {
        new About(this);
    }
  void Xopen1_actionPerformed(ActionEvent e) {
    openRoutine();
  }
}
