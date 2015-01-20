package qrinput;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import global.*;
import editor.*;
import qrmain.*;

import com.borland.jbcl.layout.*;

/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

public class QRIDialog extends JDialog
implements ActionListener
{
    JButton close = new JButton("   Close   ");

    JButton ok = new JButton("    OK    ");
    JButton cancel = new JButton("Cancel");
    JButton saveAll = new JButton("Save All");

    JTabbedPane tabPan = new JTabbedPane();

    RoomPan rp = new RoomPan();
    BatchPan bp = new BatchPan();
    TeacherPan tp = new TeacherPan();
    CoursePan cp = new CoursePan(rp.temp, bp.temp, tp.temp, rp, this);
    public ManPanLayout mp = new ManPanLayout(rp, bp, tp, cp);

    LRPan btnPan = new LRPan();
    MainEditor mainEditorRef;

    static QRIDialog myRef;

    public QRIDialog(Frame frame, String title, boolean modal
                     , int selectedIndex, int catIndex
                     , MainEditor mainEditorRef)
    {
        super(frame, title, false);


        if(myRef != null) myRef.dispose();
        myRef = this;


        this.mainEditorRef = mainEditorRef;

        /**
         *  Cross references to know eatch-other...
         */
        rp.coursePanRef = cp;
        bp.coursePanRef = cp;
        tp.coursePanRef = cp;
        cp.coursePanRef = cp;

        rp.manPanRef = mp;
        bp.manPanRef = mp;
        tp.manPanRef = mp;
        mp.callJBInit(catIndex);

        Container c = getContentPane();
        c.setLayout(new VerticalFlowLayout());

        tabPan.add(rp,  "  Room   ");
        tabPan.add(bp,  "  Batch   ");
        tabPan.add(tp,  "  Teacher   ");
        tabPan.add(cp,  "  Course   ");
        tabPan.add(mp,  "  Manual   ");

        try {
            tabPan.setIconAt(0, new ImageIcon(getClass().getResource("icon/roomtab.GIF")));
            tabPan.setIconAt(1, new ImageIcon(getClass().getResource("icon/batchtab.GIF")));
            tabPan.setIconAt(2, new ImageIcon(getClass().getResource("icon/teachertab.GIF")));
            tabPan.setIconAt(3, new ImageIcon(getClass().getResource("icon/coursetab.GIF")));
            tabPan.setIconAt(4, new ImageIcon(getClass().getResource("icon/manutab.GIF")));
        }
        catch (Exception ex) {
            MyError.debug("Cant' Load Icons!");
        }

        c.add(tabPan);

        btnPan.addLeft(close);

        btnPan.addRight(ok);
        btnPan.addRight(cancel);
        btnPan.addRight(saveAll);
        c.add(btnPan);

        pack();
        final String initTips = "Move the cursor to a input field to get the tips.";

        tabPan.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {

                // Action when ROOM panel cliked.
                if(tabPan.getSelectedComponent() == rp) {
                    rp.reloadTable();
                    rp.clearInterface();
                    rp.tipsPan.addLeft(G.tips);
                    G.setTips(rp.getTips());
                }
                // Action when BATCH panel cliked.
                if(tabPan.getSelectedComponent() == bp) {
                    bp.reloadTable();
                    bp.clearInterface();
                    bp.tipsPan.addLeft(G.tips);
                    G.setTips(initTips);
                }
                // Action when TEACHER panel cliked.
                if(tabPan.getSelectedComponent() == tp) {
                    tp.reloadTable();
                    tp.clearInterface();
                    tp.tipsPan.addLeft(G.tips);
                    G.setTips(initTips);
                }

                // Action when COURSE panel cliked.
                if(tabPan.getSelectedComponent() == cp) {
                    cp.reloadTable();
                    relodCourseInterface();
                    cp.tipsPan.addLeft(G.tips);
                    G.setTips(initTips);
                }

                // Action when MANUAL panel cliked.
                if(tabPan.getSelectedComponent() == mp) {
                    mp.reloadInterface();
                    mp.add(G.tips, BorderLayout.SOUTH);
                    G.setTips(mp.getTips());
                }
            }
        });

        tabPan.setSelectedIndex(tabPan.getTabCount()-1);
        tabPan.setSelectedIndex(selectedIndex);
        if(selectedIndex == 0) rp.reloadTable();

        close.addActionListener(this);
        ok.addActionListener(this);
        cancel.addActionListener(this);
        saveAll.addActionListener(this);


        setSize(910, 565);
        G.setCenter(this);
        show();

    }

/*
    public QRIDialog() {
        this(null, "", false, 0, -1, mainEditorRef);
        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
*/

    public void reloadBatchTeacherToCoursePan() {
        cp.reloadBatchTeacher(rp.nData, bp.nData, tp.nData);
    }

    public void relodCourseInterface() {
        cp.reloadInterface(rp.nData, bp.nData, tp.nData);
    }

    public boolean isAllsave() {
        return
            (rp.saved
            && bp.saved
            && tp.saved
            && cp.saved
            && mp.saved);
    }

    public void saveAllAction() {
        rp.save2Real2File();
        bp.save2Real2File();
        tp.save2Real2File();
        cp.save2Real2File();

        /* Take it honey, I am dieing... */
        // mainEditorRef.cp = cp;
        mainEditorRef.reloadData(!bp.saved, !tp.saved, !rp.saved);
    }

    public void ask4save() {
        if(!isAllsave())
            switch(MyError.ync("Save changes to modyfied file?")) {
                case 0: saveAllAction(); dispose(); break;
                case 1: dispose(); break;
            }
        else dispose();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == close) ask4save();
        else if(e.getSource() == ok) { saveAllAction(); dispose(); }
        else if(e.getSource() == cancel) dispose();
        else if(e.getSource() == saveAll) saveAllAction();
    }

    private void jbInit() throws Exception {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });
    }

    void this_windowClosing(WindowEvent e) {
        ask4save();
    }
}