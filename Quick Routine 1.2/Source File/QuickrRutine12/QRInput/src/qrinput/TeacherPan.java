package qrinput;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;

import global.*;
import datastructure.*;
import help.*;

/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

public class TeacherPan extends CmnPan
{
    TeacherInfo infoPan = new TeacherInfo();
    SessionPan sPan = new SessionPan();

    String colName[]={"Teachers' ID", "First Name", "Middle Name", "Last Name",
                        "Still Free", "Course Assign", "Manually Assigned"};

    ManPanLayout manPanRef;

    public TeacherPan() {
        // IO.readTeacher();

        /* Seting data reference. */
        real = G.t;
        temp = new Teacher[ G.MAXT ];
        G.copyArray(temp, real);
        nData = G.nTeacher;

        real = (Teacher[])real;
        temp = (Teacher[])temp;

        /* Add GUI for Teacher panel. */
        setGUI(infoPan, sPan, colName);

        /* Initialize Interface. */
        clearInterface();

        infoPan.lName.setNextFocusableComponent(insert);
    }

    /* Over loaded from parent. */
    void clearInterface() {
        infoPan.id.setText("");
        infoPan.fName.setText("");
        infoPan.mName.setText("");
        infoPan.lName.setText("");
        sPan.clearAll();

        clearTable();
    }

    String errorInterface(int k) {
        if(infoPan.id.getText().trim().length() == 0)  return "Teachers' ID cant' de empty!";
        if(infoPan.fName.getText().trim().length() == 0) return "Teachers' first name cant' de empty!";
        if(infoPan.lName.getText().trim().length() == 0) return "Teachers' last name cant' de empty!";

        int i = Teacher.index( infoPan.id.getText().trim(), temp, nData );

        /*
         * A new Distinct value at interface
         * so no eror.
         */
        if(i == -1) return null;

        /* For update. */
        if(i != k) return "All teachers' ID must have to be distinct!\n" +
                            infoPan.id.getText().trim() + " is already ID for "+
                            ((Teacher)temp[i]).getFullName();

        return null;
    }

    String deleteError(int k) {
        String cList = ((Teacher)temp[k]).getAssignCourseList(coursePanRef.temp, coursePanRef.nData);

        String msg = ((Teacher)temp[k]).getFullName() +
            " is already manualy assigned to following cours(s)\n" + cList +
            "If you delete now, those course(s)'s assigned teacher will be empty!\n\n" +
            "Do you want to delete?";

        if(cList == null) return null;
        else if(MyError.yn(msg)) {
            String tid = ((Teacher)readInterface()).id;

            for(int i = 0; i < coursePanRef.nData; i++) {
                Course cRef = (Course)coursePanRef.temp[i];

                if( cRef.tid != null && cRef.tid.equalsIgnoreCase(tid) )
                    cRef.manuRCList.deleteAll();
            }

            return null;
        }
        else return  "Not null";
    }

    void closeOurRoutine() {
        manPanRef.res.closeCat(1);
    }

    void deletePK(int k) {
        ((Teacher)temp[k]).updatePK(coursePanRef.temp, coursePanRef.nData, null);
    }

    String updateError(int k) {
        Teacher tRef = (Teacher)readInterface();

        if( ((Teacher)temp[k]).getManualRCList( (Course[])coursePanRef.temp, coursePanRef.nData ).nCell > 0
            && !((Teacher)temp[k]).id.equalsIgnoreCase(tRef.id) ) {
            return "This teacher was assigned manualy.\n"+
                    "You must remove corresponding manual routine before update this teachers' ID";
        }

        int nFree = tRef.nFree();
        int hoursCourseAssign = tRef.getHourCourseAssign(coursePanRef.temp, coursePanRef.nData );

        if(nFree < hoursCourseAssign)
            return "A teachers' available free hour(s) cant' be less than assigned course(s) hour(s)!\n"+
            tRef.getFullName() + "s' assigned course(s) hour(s) is " + hoursCourseAssign + "\n"+
            tRef.getFullName() + "s' available free hour(s) cant' be updated to " + nFree + ".";

        return null;
    }

    void updatePK(int k) {
        ((Teacher)temp[k]).updatePK(coursePanRef.temp, coursePanRef.nData,
        ((Teacher)readInterface()).id );
    }

    Data readInterface() {
        return new Teacher(
            infoPan.id.getText().trim(),
            infoPan.fName.getText().trim(),
            infoPan.mName.getText().trim(),
            infoPan.lName.getText().trim(),
            sPan.getValue()
        );
    }

    void setInterface() {
        int k = t.getSelectedRow();

        if(k == -1) return;

        /* Setting LEFT panel from talbe. */
        infoPan.id.setText(((Teacher)temp[k]).id);
        infoPan.fName.setText(((Teacher)temp[k]).fName);
        infoPan.mName.setText(((Teacher)temp[k]).mName);
        infoPan.lName.setText(((Teacher)temp[k]).lName);

        /* Setting RIGHT panel. */
        sPan.setValue(((Teacher)temp[k]).s);
        sPan.setManuBusyValue(( (Teacher)temp[k]).getManualBusySession(
                (Course[])coursePanRef.temp, coursePanRef.nData ).s );
    };

    void save2Real2File() {
        G.copyArray(G.t, temp);
        G.nTeacher = nData;

        IO.writeTeacher();
    };

    void helpAction() {
        if(insert.isEnabled()) new HelpFrame(HelpFrame.TEACHER);
        else new HelpFrame(HelpFrame.DELUP);
    }
}