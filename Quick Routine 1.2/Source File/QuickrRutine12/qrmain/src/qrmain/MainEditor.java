package qrmain;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import global.*;
import qrinput.*;
import editor.*;
import datastructure.*;
import algorithm.*;
import help.*;

public class MainEditor extends ManPanLayout
implements ActionListener
{
    public CoursePan cp = new CoursePan();

    LRPan btnPan = new LRPan();
    RoutineEditorSet res = new RoutineEditorSet(this, cp, true, btnPan);

    boolean remove = true;
    JButton regenarateRoutine = new JButton("Remove Automatic Periods");

    JButton report = new JButton("Report");
    JButton close = new JButton("Close");
    JButton help = new JButton("Help");

    QRMainFrame motherRef;

    public MainEditor(QRMainFrame motherRef) {
        G.meRef = this;
        this.motherRef = motherRef;

        setLayout(new BorderLayout());
        add(res, BorderLayout.CENTER);
        add(btnPan, BorderLayout.SOUTH);

        /* Button arangmant */
        btnPan.addLeft(regenarateRoutine);

        btnPan.addRight(report);
        btnPan.addRight(close);
        btnPan.addRight(help);

        regenarateRoutine.addActionListener(this);

        report.addActionListener(this);
        close.addActionListener(this);
        help.addActionListener(this);
    }

    /**
     * This function caled when input interface closed
     */
    public void reloadData(boolean closeBatch, boolean closeTeacher, boolean closeRoom) {
        cp.reloadGlobal();
        motherRef.reloadTree();

        if(closeBatch) res.closeCat(0);
        if(closeTeacher) res.closeCat(1);
        if(closeRoom) res.closeCat(2);

        btnAction();
        btnAction();

        G.mfRef.jSplitPane1.add( this, JSplitPane.BOTTOM);
        G.mfRef.jSplitPane1.setDividerLocation(150);
    }

    public Room getRoomRef(RoutineCell rc) {
        if(rc == null) return null;
        else return Room.getRef(rc.rid, G.r, G.nRoom);
    }

    public Batch getBatchRef(RoutineCell rc) {
        if(rc == null) return null;
        else return Batch.getRef(rc.getCourseRef( (Course[])cp.temp, cp.nData ).batchHashIndex
                                 , G.b, G.nBatch);
    }

    public Teacher getTeacherRef(RoutineCell rc) {
        if(rc == null) return null;
        else return Teacher.getRef(rc.getCourseRef( (Course[])cp.temp, cp.nData ).tid
                                , G.t, G.nTeacher);
    }

    public void makeAuto() {
        new MakeRoutine( (Course[])cp.temp, cp.nData );
        regenarateRoutine.setText("Remove Automatic Periods");
        remove = true;

        reloadRoutine();
        res.removeAllBusyLabel();
    }

    public void removeAuto() {
        CourseSession.deleteAllAuto( (Course[])cp.temp, cp.nData );
        regenarateRoutine.setText("Generate Automatic Routine");
        remove = false;

        reloadRoutine();
        res.removeAllBusyLabel();
    }

    void btnAction() {
        if(remove) removeAuto();
        else makeAuto();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == regenarateRoutine) btnAction();
        if(e.getSource() == report) new report.ReportDialog(G.mfRef, "AutomaticRoutineReport", true,
                Course.THEORY, (Course[])cp.temp, cp.nData );
        if(e.getSource() == close) res.close();

        if(e.getSource() == help) {
            if(res.getTabCount() == 0) new HelpFrame(HelpFrame.START);
            else new HelpFrame(HelpFrame.EDIT);
        }
    }

    String catName[] = {"Batch: ", "Teacher: ", "Room: "};

    public void openRoutine(Point p) {
        if(p != null) {
            int i = (int)p.getX();
            int j = (int)p.getY();

            switch(i) {
                case 0:
                    res.open(this, i, j, catName[i]+G.b[j].getNickName()
                             , G.b[j].getTotalRCList( (Course[])cp.temp, cp.nData ));
                    break;
                case 1:
                    res.open(this, i, j, catName[i]+G.t[j].id
                             , G.t[j].getTotalRCList( (Course[])cp.temp, cp.nData ));
                    break;
                case 2:
                    res.open(this, i, j, catName[i]+G.r[j].id
                             , G.r[j].getTotalRCList( (Course[])cp.temp, cp.nData ));
                    break;
            }
        }
    }

    public RCList getRoutine(int i, int j) {
        switch(i) {
            case 0: return G.b[j].getTotalRCList( (Course[])cp.temp, cp.nData );
            case 1: return G.t[j].getTotalRCList( (Course[])cp.temp, cp.nData );
            case 2: return G.r[j].getTotalRCList( (Course[])cp.temp, cp.nData );
            default: return null;
        }
    }

    public RCList getRoutine(Point p) {
        return getRoutine((int)p.getX(), (int)p.getY());
    }

    public void reloadRoutine() {
        G.p("reloadRoutine() of mainEditor..\n");
        if(res.getSelectedRoutineIndex() != null)
            res.reloadRoutine ( getRoutine( res.getSelectedRoutineIndex() ) );
    }

    public Image getRoutineImage() {
        return res.getRoutineImage();
    }

    public String getTitle() {
        String s = "Class routine of ";
        Point p = res.getSelectedRoutineIndex();
        int i = (int)p.getX();
        int j = (int)p.getY();

        switch(i) {
            case 0:
                s += catName[i]+G.b[j].getNickName();
                break;
            case 1:
                s += catName[i]+G.t[j].id;
                break;
            case 2:
                s += catName[i]+G.r[j].id;
                break;
        }

        return s;
    }

    public void reloadBusyLabel(RoutineCell rc){
        Room rRef = getRoomRef(rc);
        Teacher tRef = getTeacherRef(rc);
        Batch bRef = getBatchRef(rc);

        res.reloadBusyLabel("Room", "Busy", new Session( rRef.s ), 0);
        res.reloadBusyLabel("Room", "Assigned", rRef.getTotalBusySession( (Course[])cp.temp, cp.nData) , 1);

        res.reloadBusyLabel("Batch", "Busy", new Session( bRef.s ), 4);
        res.reloadBusyLabel("Batch", "Assigned", bRef.getTotalBusySession( (Course[])cp.temp, cp.nData) , 5);

        res.reloadBusyLabel("Teacher", "Busy", new Session( tRef.s ), 2);
        res.reloadBusyLabel("Teacher", "Assigned", tRef.getTotalBusySession( (Course[])cp.temp, cp.nData) , 3);
    }

    public void reloadInterface(int catIndex, int BTRIndex) {
        G.p("Callint reloadInterface of mainEditor..\n");
        reloadRoutine();
        res.removeAllBusyLabel();
    }

    public void selectCourseRoom(RoutineCell rc) {
        G.p("Callint selectCourseRoom(EditorCell ec) of mainEditor..\n");
        reloadBusyLabel(rc);
    }

    public boolean assignAction() {
        G.mainTips.setText("To vew the report click on the 'Report' button.");

        G.p("Callint assignAction() of mainEditor..\n");
        res.removeAllBusyLabel();
        res.deselectAll();
        return false;
    }

    public Course getSelectedCourse() {
        G.p("Callint getSelectedCourse() of mainEditor..\n");
        RoutineCell rc = res.getSelectedRoutineCell();
        try {
            if(rc.getCourseRef( (Course[])cp.temp, cp.nData ) == null) MyError.debug("NUll C");
            return rc.getCourseRef( (Course[])cp.temp, cp.nData );
        }
        catch (Exception ex) {
            return null;
        }
    }


    public  String[][][] getHtmlRoutine() {
        RoutineCell rcg[][] = res.getRCG();
        if(rcg == null) {
            MyError.show("There is no routine open to print.");
            return null;
        }

        String s[][][] = new String[Basic.nDay][Basic.nPeriod][2];

        for(int i = 0; i < Basic.nDay; i++)
            for(int j = 0; j < Basic.nPeriod; j++)
                if(rcg[i][j] == null) {
                  s[i][j][0] = "";
                  s[i][j][1] = "";
                }
                else {
                    s[i][j][0] = rcg[i][j].cid;
                    s[i][j][1]= rcg[i][j].getTID( (Course[])cp.temp, cp.nData ).trim() + "/"+rcg[i][j].rid;

                }

        return s;
    }
}