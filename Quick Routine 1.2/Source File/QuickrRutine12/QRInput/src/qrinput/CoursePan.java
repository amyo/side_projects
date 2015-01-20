package qrinput;

import java.awt.*;
import javax.swing.*;

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

public class CoursePan extends CmnPan {
    CourseInfo infoPan;
    CourseTypeInfo typeInfoPan;

    String colName[] = {"Course ID", "Batch", "Course Teacher", "Course Type",
                         "Room", "Credit", "Class Duration", "Weekly Class(s)"};

    // This data is nessesary for course panel.
    // to keep updated the interface.
    Data tempRoom[], tempBatch[], tempTeacher[];
    int nTempRoom, nTempBatch, nTempTeacher;
    QRIDialog motherRef;

    /* For Main editor */
    public CoursePan() {
        reloadGlobal();
    }

    public void reloadGlobal() {
        // IO.readCourse();

        real = G.c;
        temp = new Course[ G.MAXC ];
        G.copyArray(temp, real);
        nData = G.nCourse;

        real = (Course[])real;
        temp = (Course[])temp;
    }

    public CoursePan(Data tempRoom[], Data tempBatch[], Data tempTeacher[],
                    RoomPan roomPanRef, QRIDialog motherRef)
    {
        this.tempRoom = tempRoom;
        this.tempBatch = tempBatch;
        this.tempTeacher = tempTeacher;

        this.motherRef = motherRef;

        infoPan = new CourseInfo();
        typeInfoPan = new CourseTypeInfo(roomPanRef);

        typeInfoPan.coursePanRef = this;

        // Porichoi... (:->
        infoPan.rightPan = typeInfoPan;
        typeInfoPan.leftPan = infoPan;

        // IO.readCourse();

        /**
         * Setting temp array.
         */
        real = G.c;
        temp = new Course[ G.MAXC ];
        G.copyArray(temp, real);
        nData = G.nCourse;

        real = (Course[])real;
        temp = (Course[])temp;

        setGUI(infoPan, typeInfoPan, colName);
        rightPan.setBorder(BorderFactory.createTitledBorder("Insert Information"));
    }

    public void reloadBatchTeacher(int nTempRoom, int nTempBatch, int nTempTeacher) {
        /**
         * This data is used by
         * setInterface()
         */
        this.nTempBatch = nTempBatch;
        this.nTempTeacher = nTempTeacher;
        this.nTempRoom = nTempRoom;

        // Reload BATCH...
        infoPan.batch.removeAllItems();
        for(int i = 0; i < nTempBatch; i++)
            infoPan.batch.addItem( ((Batch)tempBatch[i]).getNickName(temp, nData) );

        // Reload TEACHER...
        infoPan.cTeacher.removeAllItems();
        infoPan.cTeacher.addItem("Assign Later.");
        for(int i = 0; i < nTempTeacher; i++)
            infoPan.cTeacher.addItem( ((Teacher)tempTeacher[i]).getNickName(temp, nData) );
    }

    /**
     * Reload the input ineterface from current temp variable
     * It helps to reflect the change of list made by user
     * Each time the teacher panel is clicked.
     * @Parameters: #Takes current temp resourse list size.
     */
    public void reloadInterface(int nTempRoom, int nTempBatch, int nTempTeacher) {
        reloadBatchTeacher(nTempRoom, nTempBatch, nTempTeacher);

        // Reload ROOM & CREDIT...
        if(infoPan.type.getSelectedIndex() != -1)
            typeInfoPan.reloadRoomCredit();

        // Reload DURATION & NCLASS...
        if(typeInfoPan.credit.getSelectedIndex() != -1)
            typeInfoPan.reloadDurationNumberofClass();
    }

    void clearInterface() {
        motherRef.relodCourseInterface();

        infoPan.id.setText("");
        infoPan.batch.setSelectedIndex(-1);
        infoPan.cTeacher.setSelectedIndex(-1);
        infoPan.type.setSelectedIndex(-1);

        typeInfoPan.room.setSelectedIndex(-1);
        typeInfoPan.credit.setSelectedIndex(-1);
        typeInfoPan.duration.setSelectedIndex(-1);
        typeInfoPan.nClass.setSelectedIndex(-1);

        typeInfoPan.room.setEnabled(false);
        typeInfoPan.credit.setEnabled(false);
        typeInfoPan.duration.setEnabled(false);
        typeInfoPan.nClass.setEnabled(false);

        clearTable();
    }

    String errorInterface(int k) {
        // Primary key cant' be EMPTY!
        String s = "You must select ";

        if(infoPan.id.getText().trim().length() == 0) return "Course ID cant' be empty!";
        if(infoPan.batch.getSelectedIndex() == -1) return s + "a batch!";
        if(infoPan.cTeacher.getSelectedIndex() == -1) return s + "a teacher!";
        if(infoPan.type.getSelectedIndex() == -1) return s + "a type!";

        if(typeInfoPan.room.getSelectedIndex() == -1) return s + "a room!";
        if(typeInfoPan.credit.getSelectedIndex() == -1) return s + "credit for a course!";
        if(typeInfoPan.duration.getSelectedIndex() == -1) return s + "duration for a course!";
        if(typeInfoPan.nClass.getSelectedIndex() == -1) return s + "weekly class(s) for a course!";

        int i;

        // Required free session...
        int reqFree = typeInfoPan.getDuration() * typeInfoPan.getNClass();

        // UPDATE...
        /*
        if(k > -1) {
            i = t.getSelectedRow();
            reqFree -= ( ((Course)temp[i]).duration * ((Course)temp[i]).nClass );
        }
        */

        // Required BATCH sessin...
        i = infoPan.batch.getSelectedIndex();
        if( ((Batch)tempBatch[i]).stillFree(temp, nData) < reqFree )
            return ((Batch)tempBatch[i]).getNickName() + " is not much free as required!\n" +
                    ((Course)readInterface()).getNickName(temp, nData) +
                    " required to have " + reqFree + " hour(s) free weekly\n" +
                    ((Batch)tempBatch[i]).getNickName() + " have only " +((Batch)tempBatch[i]).stillFree(temp, nData) +
                    " hour(s) free per week.";

        // Required TEACHER sessin...
        i = infoPan.cTeacher.getSelectedIndex();
        if(i > 0 && ((Teacher)tempTeacher[i - 1]).stillFree(temp, nData) < reqFree )
            return "Teacher Not Free";

        if(infoPan.type.getSelectedIndex() == Course.LAB) {

            i = typeInfoPan.room.getSelectedIndex();
            if( ((Room)tempRoom[i]).stillFree(temp, nData) < reqFree )
                return "Room Not Free";
        }

        // Test for PRIMARY KEY...
        i = Course.index(infoPan.id.getText().trim(), temp, nData);

        if(i == -1) return null;
        else if(i != k) return "All Couse ID must have to be distinct!\n"+
                        infoPan.id.getText().trim() + " is already ID for "+
                        ((Course)temp[i]).getName(temp, nData);
        return null;
    }

    Data readInterface() {
        int type = infoPan.type.getSelectedIndex();
        float credit = Float.parseFloat( ((String)typeInfoPan.credit.getSelectedItem()).trim() );
        String rid = "";

        if(type == Course.THEORY) rid = null;
        else rid = new String( ((Room)tempRoom[ typeInfoPan.room.getSelectedIndex() ]).id );

        return new Course(
            infoPan.id.getText().trim(),
            ((Batch)tempBatch[ infoPan.batch.getSelectedIndex() ]).hashIndex,

            infoPan.cTeacher.getSelectedIndex() == 0? null:
            ((Teacher)tempTeacher[ infoPan.cTeacher.getSelectedIndex() - 1 ]).id,

            type,

            null,

            credit,

            typeInfoPan.getDuration(),

            typeInfoPan.getNClass()
        );
    }

    void setInterface() {
        int k = t.getSelectedRow();
        if(k == -1) return;
        Course cRef = (Course)temp[k];

        // Set ID.
        infoPan.id.setText(cRef.id);

        // Set TYPE...
        infoPan.type.setSelectedIndex(cRef.type);
        typeInfoPan.reloadRoomCredit();

        // Set CREDIT...
        float mul = (cRef.type == Course.THEORY? 1: 2);
        int creditIndex = (int)((cRef.credit * mul) - 1);
        typeInfoPan.credit.setSelectedIndex(creditIndex);
        typeInfoPan.reloadDurationNumberofClass();

        /**
         * reload BATCH & TEACHER...
         * from QRIDialog.
         */
        motherRef.reloadBatchTeacherToCoursePan();

        // Set BATCH...
        infoPan.batch.setSelectedIndex( Batch.index(cRef.batchHashIndex, tempBatch, nTempBatch) );

        // Set TEACHER...
        if(cRef.tid == null) infoPan.cTeacher.setSelectedIndex(0);
        else infoPan.cTeacher.setSelectedIndex( Teacher.index(cRef.tid, tempTeacher, nTempTeacher) + 1 );

        // Set ROOM...
        typeInfoPan.room.setSelectedIndex(0);

        // Set DURATION...
        // Set NUMBER OF CLASS.

        if(infoPan.type.getSelectedIndex() == 0) {
            typeInfoPan.duration.setSelectedIndex(0);
            typeInfoPan.nClass.setSelectedIndex(0);
        }
        else {
            typeInfoPan.duration.setSelectedIndex(cRef.duration - 1);
            typeInfoPan.nClass.setSelectedIndex(cRef.nClass - 1);
        }
    };

    public void save2Real2File() {
        G.copyArray(G.c, temp);
        G.nCourse = nData;

        IO.writeCourse();
    };

    void helpAction() {
        if(insert.isEnabled()) new HelpFrame(HelpFrame.COURSE);
        else new HelpFrame(HelpFrame.DELUP);
    }
}