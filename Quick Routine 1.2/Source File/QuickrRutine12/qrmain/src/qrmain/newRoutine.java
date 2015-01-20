package qrmain;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;

import global.*;
import help.*;

public class newRoutine extends JDialog {
    JPanel panel1 = new JPanel();
    private XYLayout xYLayout1 = new XYLayout();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JPanel jPanel1 = new JPanel();
    private TitledBorder titledBorder1;
    private XYLayout xYLayout2 = new XYLayout();
    private JTextField name = new JTextField();
    private JTextField loc = new JTextField();
    private JButton locBtn = new JButton();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private TitledBorder titledBorder2;
    private TitledBorder titledBorder3;
    private JButton ok = new JButton();
    private JButton cancel = new JButton();

    String semName[] = {"Single semester.", "2 semesters.", "3 semesters."};
    String secName[] = {"Single section.", "2 sections.", "3 sections.", "4 sections.", "5 sections."};

    private JButton jButton1 = new JButton();

    static newRoutine myRef;

    public newRoutine(Frame frame, String title, boolean modal) {
        super(frame, title, false);

        if(myRef != null) myRef.dispose();
        myRef = this;

        try {
            jbInit();
            pack();

            G.setCenter(this);
            show();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),"Path information");
        titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),"Department information");
        titledBorder3 = new TitledBorder("");
        panel1.setLayout(xYLayout1);
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel1.setText("Create a new routine.");
        jLabel2.setText("Insert following information then press ok.");
        jPanel1.setBorder(titledBorder1);
        jPanel1.setLayout(xYLayout2);
        loc.setFont(new java.awt.Font("DialogInput", 0, 12));
        loc.setEditable(false);
        loc.setText("D:/MyRoutine");
        locBtn.setFont(new java.awt.Font("Dialog", 1, 15));
        locBtn.setText("...");
        locBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                locBtn_actionPerformed(e);
            }
        });
        jLabel3.setText("Name:");
        jLabel4.setText("Location:");
        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ok_actionPerformed(e);
            }
        });
        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel_actionPerformed(e);
            }
        });
        panel1.setPreferredSize(new Dimension(522, 275));
        name.setFont(new java.awt.Font("DialogInput", 0, 12));
        name.setText("CSE_Routine");
        jButton1.setText("Help");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        getContentPane().add(panel1);
        panel1.add(jLabel1, new XYConstraints(35, 32, -1, -1));
        panel1.add(jLabel2, new XYConstraints(36, 59, 437, -1));
        jPanel1.add(name,    new XYConstraints(145, 0, 282, 23));
        jPanel1.add(jLabel3,  new XYConstraints(16, 6, 117, -1));
        jPanel1.add(loc, new XYConstraints(145, 33, 236, 22));
        jPanel1.add(locBtn,  new XYConstraints(390, 31, 37, 24));
        jPanel1.add(jLabel4, new XYConstraints(15, 37, 119, -1));
    panel1.add(cancel, new XYConstraints(335, 216, -1, -1));
    panel1.add(ok, new XYConstraints(274, 216, -1, -1));
        panel1.add(jButton1,    new XYConstraints(418, 216, -1, -1));
        panel1.add(jPanel1,   new XYConstraints(35, 99, 447, 95));
    }

    void locBtn_actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fc.showOpenDialog(this);

        if(result==JFileChooser.CANCEL_OPTION)
            return;

        if(result == -1)
            return;

        else loc.setText(""+fc.getSelectedFile());
    }

    void cancel_actionPerformed(ActionEvent e) {
        dispose();
    }

    String errorInterface() {
        if(name.getText().trim().length() == 0) return "Please enter a name.";

        File f = new File(loc.getText().trim());
        if(!f.isDirectory()) {
            f.mkdir();
        }

        return null;
    }

    Basic readInterface() {
        return new Basic(
                2, //nSem.getSelectedIndex() + 1,
                2, //nSec.getSelectedIndex() + 1,
                name.getText().trim()
        );
    }

    /**
     * Create a new routinr and open that....
     * @param e
     */
    void ok_actionPerformed(ActionEvent e) {
        if(errorInterface() == null) {
            Basic bs = readInterface();
            G.loc = loc.getText().trim();

            Basic.loadBasic(bs);
            IO.writeBasic(bs);

            G.mfRef.setTitle(G.NameVer + " - " + G.loc + "/" + G.name + ".rtn");
            G.name = new String(name.getText().trim());

            G.nBatch = G.nTeacher = G.nRoom = G.nCourse = 0;

            G.meRef.res.closeAll();
            G.meRef.reloadData(false, false, false);

            dispose();

            G.mfRef.jSplitPane1.add(new NewCreate(), JSplitPane.BOTTOM);
            G.mfRef.setEnableMenu(true);
        }
        else MyError.show(errorInterface());
    }

    void jButton1_actionPerformed(ActionEvent e) {
        new HelpFrame(HelpFrame.START);
    }
}