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

public class BatchPan extends CmnPan
{
    BatchInfo infoPan = new BatchInfo();
    SessionPan sPan = new SessionPan();

    String colName[] = { "Year", "Semester", "Secction", "Class Start",
                         "Still Free", "Course Assign", "Manually Assigned"};

    ManPanLayout manPanRef;

    public BatchPan() {
        //IO.readBatch();

        /* Seting data reference. */
        real = G.b;
        temp = new Batch[ G.MAXB ];
        G.copyArray(temp, real);
        nData = G.nBatch;

        real = (Batch[])real;
        temp = (Batch[])temp;

        /* Add GUI for Teacher panel. */
        setGUI(infoPan, sPan, colName);

        /* Initialize Interface. */
        clearInterface();
    }

    /* Over loaded from parent. */
    void clearInterface() {
        infoPan.sunMor.setSelectedIndex(-1);
        infoPan.yr.setSelectedIndex(-1);
        infoPan.sem.setSelectedIndex(-1);
        infoPan.sec.setSelectedIndex(-1);

        sPan.clearAll();
        clearTable();
    }

    Data readInterface() {
        return new Batch (
            infoPan.sunMor.getSelectedIndex() == 0? true: false,
            infoPan.yr.getSelectedIndex(),
            infoPan.sem.getSelectedIndex(),
            infoPan.sec.getSelectedIndex(),
            sPan.getValue()
        );
    }

    String errorInterface(int k) {
        if(infoPan.sunMor.getSelectedIndex() == -1)
            return "You must provide information about routine style of a batch.";

        int i = Batch.index((Batch)readInterface(), temp, nData);

        /*
         * A new Distinct value at interface
         * so no eror.
         */
        if(i == -1) return null;

        if(i != k) return "Batch: " + temp[i].getNickName(coursePanRef.temp, coursePanRef.nData)+
                            " Already exist";
        return null;
    }

    String deleteError(int k) {
        String cList = ((Batch)temp[k]).getAssignCourseList(coursePanRef.temp, coursePanRef.nData);
        if(cList == null) return null;

        String msg = ((Batch)temp[k]).getNickName() +
            " is already manualy assigned to following cours(s)\n" + cList +
            "Deleting this batch will result those course(s)'s assigned batch will be empty!\n\n" +
            "You must update coresponding course(s) before delete this batch!";

        MyError.show(msg);
        return "Not null";
    }

    void closeOurRoutine() {
        manPanRef.res.closeCat(0);
    }

    void deletePK(int k) {
    }

    String updateError(int k) {
        Batch bRef = (Batch)readInterface();

        if( ((Batch)temp[k]).getManualRCList( (Course[])coursePanRef.temp, coursePanRef.nData ).nCell > 0
            && ((Batch)temp[k]).hashIndex != bRef.hashIndex ) {

            return "This batch was assigned manualy.\n"+
                    "You must update corresponding manual routine before update this batchs'\n" +
                    " Year or Semester or Section.";
        }

        int nFree = bRef.nFree();
        int hoursCourseAssign = bRef.getHourCourseAssign(coursePanRef.temp, coursePanRef.nData );

        if(nFree < hoursCourseAssign)
            return "A batchs' available free hour(s) cant' be less than assigned course(s) hour(s)!\n"+
            bRef.getNickName() + "s' assigned course(s) hour(s) is " + hoursCourseAssign + "\n"+
            bRef.getNickName() + "s' available free hour(s) cant' be updated to " + nFree + ".";


        return null;
    }

    void updatePK(int k) {
        ((Batch)temp[k]).updatePK(coursePanRef.temp, coursePanRef.nData,
        ((Batch)readInterface()).hashIndex );
    }

    void setInterface() {
        int k = t.getSelectedRow();

        if(k == -1) return;

        /* Setting LEFT panel from talbe. */
        infoPan.sunMor.setSelectedIndex(((Batch)temp[k]).sunMor? 0: 1);
        infoPan.yr.setSelectedIndex(((Batch)temp[k]).getYr());
        infoPan.sem.setSelectedIndex(((Batch)temp[k]).getSem());
        infoPan.sec.setSelectedIndex(((Batch)temp[k]).getSec());

        /* Setting RIGHT panel. */
        sPan.setValue(((Batch)temp[k]).s);
        sPan.setManuBusyValue(( (Batch)temp[k]).getManualBusySession(
                (Course[])coursePanRef.temp, coursePanRef.nData ).s );
    };

    void save2Real2File() {
        G.copyArray(G.b, temp);
        G.nBatch = nData;

        IO.writeBatch();
    };

    void helpAction() {
        if(insert.isEnabled()) new HelpFrame(HelpFrame.BATCH);
        else new HelpFrame(HelpFrame.DELUP);
    }
}