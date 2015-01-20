package datastructure;

import global.*;
import java.io.*;

import qrinput.*;

/**
 * Title:        Data Stucture
 * Description:  Complete Data Structure of QuickRoutine
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author Simanto, Jewel & Milton
 * @version 1.0
 */

public class Teacher extends Data
implements Serializable
{
    public String fName, mName, lName, id;
    public int s[][] = new int[Basic.nDay][Basic.nPeriod];

    public Teacher(String id, String fName, String mName, String lName , int s[][]) {
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.id = id;
        this.s = s;
    }

    public static int index(String id, Data t[], int n) {
        for(int i = 0; i < n; i++)
            if(id.equalsIgnoreCase( ((Teacher)t[i]).id ))
                return i;

        return -1;
    }

    public static Teacher getRef(String id, Data t[], int n) {
        for(int i = 0; i < n; i++)
            if(id.equalsIgnoreCase( ((Teacher)t[i]).id ))
                return (Teacher)t[i];

        return null;
    }

    public Data copy() {
        return new Teacher(id, fName, mName, lName, newCopy(s));
    }

    public int getHourCourseAssign(Data c[], int n) {
        int f = 0;
        Course cRef;

        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if( cRef.tid != null    // Being sure that Course have a teacher...
                && cRef.tid.equalsIgnoreCase(id))
                f += (cRef.nClass * cRef.duration);
         }

        return f;
    }

    public int numberOfCourseAssign(Data c[], int n) {
        int x = 0;
        Course cRef;

        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if( cRef.tid != null    // Being sure that Course have a teacher...
                && cRef.tid.equalsIgnoreCase(id))
                x++;
        }

        return x;
    }

    public void updatePK(Data c[], int n, String newID) {
        String oldID = new String(id);
        Course cRef;

        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if( cRef.tid != null    // Being sure that Course have a teacher...
                && cRef.tid.equalsIgnoreCase(oldID))
                cRef.tid = newID;
        }
    }

    public String getAssignCourseList(Data c[], int n) {
        int nCourse = numberOfCourseAssign(c, n);
        if(nCourse == 0) return null;
        String s = "";

        Course cRef;
        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if( cRef.tid != null    // Being sure that Course have a teacher...
                && cRef.tid.equalsIgnoreCase(id))
                s += cRef.getNickName(c, n)+"\n";
        }

        return s;
    }

    public String[] getRow(Data c[], int n) {
        String s[] = {id, fName, mName, lName,
                    (nFree() - getHourCourseAssign(c, n))+" hour(s)",
                    getHourCourseAssign(c, n)+" hour(s)", nManu()+" hour(s)"};
        return s;
    }

    public int stillFree(Data c[], int n) {
        return (nFree() - getHourCourseAssign(c, n));
    }

    public String getFullName() {
        return fName + " " + mName + " " + lName;
    }

    public String getName(Data c[], int n) {
        return "ID: " + id + ", "+"Name: " + getFullName()+", "+
                    (nFree() - getHourCourseAssign(c, n))+" hour(s) Still Free, "+
                    getHourCourseAssign(c, n)+" hour(s) Course Assign, "+nManu()+" hour(s) manually assigned.";
    }

    public String getNickName(Data c[], int n) {
        return ""+id+", "+(nFree() - getHourCourseAssign(c, n))+" hr free, "+getFullName();
    }

    public RCList getManualRCList(Course c[], int nCourse) {
        RCList rcl = new RCList();
        for(int i = 0; i < nCourse; i++)
            if(c[i].tid != null     // If course have a teacher...
                && c[i].tid.equalsIgnoreCase( id ))
                rcl.add(c[i].manuRCList);

        return rcl;
    }
    
    public RCList getAutoRCList(Course c[], int nCourse) {
        RCList rcl = new RCList();
        for(int i = 0; i < nCourse; i++)
            if(c[i].tid != null     // If course have a teacher...
                && c[i].tid.equalsIgnoreCase( id ))
                rcl.add(c[i].autoRCList);

        return rcl;
    }
    
    public RCList getTotalRCList(Course c[], int nCourse) {
        RCList rcl = getManualRCList(c, nCourse);
        rcl.add( getAutoRCList(c, nCourse) );

        return rcl;
    }
    
    public Session getManualBusySession(Course c[], int nCourse) {
        return getAssignBusySession(getManualRCList(c, nCourse), c, nCourse);
    }
    
    public Session getManualAndSetBusySession(Course c[], int nCourse) {
        return getAssignBusySession(getManualRCList(c, nCourse), c, nCourse).unionValue(new Session(s), Session.BUSY );
    }
    
    public Session getTotalBusySession(Course c[], int nCourse) {
        return getAssignBusySession(getTotalRCList(c, nCourse), c, nCourse);
    }
    
    public int nFree() { return nSession(s, MyCheckBox.FREE); }
    public int nBusy() { return nSession(s, MyCheckBox.BUSY); }
    public int nManu() { return nSession(s, MyCheckBox.MANU); }
}