package datastructure;

import java.io.*;
import global.*;

import qrinput.*;

/**
 * Title:        Data Stucture
 * Description:  Complete Data Structure of QuickRoutine
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author Simanto, Jewel & Milton
 * @version 1.0
 */

public class Batch extends Data
implements Serializable
{
    public static int NO_SEC = 0;

    public boolean sunMor;
    public int hashIndex;
    public int s[][] = new int[Basic.nDay][Basic.nPeriod];

    static String postfix[] = {"st", "nd", "rd"};

    public static String yrSemName(int i) {
        return i < 3? (""+(i+1)+postfix[i]): (""+(i+1)+"th");
    }

    static String secNameString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String secName(int i) {
        return secNameString.charAt(i)+"";
    }

    /**
     * Hash index is a single integer that represent the batch
     * from which we can compute a batchs' year, semester and section index.
     * @param takes batchs' year index.
     * @param takes batchs' semester index.
     * @param takes batchs' section index.
     * @return hash index of batch.
     */
    public int getHashIndex(int yr, int sem, int sec) {
        return
            yr * (Basic.maxSemester * Basic.maxSection)
            + sem * (Basic.maxSection)
            + sec;
    }

    /**
     * Calculate YEAR index
     * from batch hash index;
     * @param batchs' hash index
     * @return batchs' year
     */
    public static int getYr(int hashIndex) {
        return hashIndex / (Basic.maxSemester * Basic.maxSection);
    }

    /**
     * Calculate SEMESTER index
     * from batch hash index;
     * @param batchs' hash index
     * @return batchs' semester
     */
    public static int getSec(int hashIndex) {
        return (hashIndex % (Basic.maxSemester * Basic.maxSection)) % Basic.maxSection;
    }

    /**
     * Calculate SECTION index
     * from batch hash index;
     * @param batchs' hash index
     * @return batchs' section
     */
    public static int getSem(int hashIndex) {
        return (hashIndex % (Basic.maxSemester * Basic.maxSection)) / Basic.maxSection;
    }

    public int getYr() { return getYr(hashIndex); }
    public int getSec() { return getSec(hashIndex); }
    public int getSem() { return getSem(hashIndex); }

    /**
     * Test HASH.
     */
    public void testHash() {
        int n = (Basic.maxYear * Basic.maxSemester * Basic.maxSection);
        for(int i = 0; i < n; i++) {
            G.p(+i+": "+getYr(i)+"-"+getSem(i)+", "+getSec(i)+" --->");
            G.p(getHashIndex(getYr(i), getSem(i), getSec(i))+"\n");
        }
    }

    /**
     * Takes dierct hash index and store.
     */
    public Batch(boolean sunMor, int hashIndex, int s[][]) {
        this.sunMor = sunMor;
        this.hashIndex = hashIndex;
        this.s = s;
    }

    /**
     * Takes yr, sem, sec and
     * Store hash index after calculation.
     */
    public Batch(boolean sunMor, int yr, int sem, int sec, int s[][])
    {
        this.sunMor = sunMor;
        hashIndex = getHashIndex(yr, sem, sec);
        this.s = s;
    }

    public static int index(Batch item, Data b[], int n) {
        for(int i = 0; i < n; i++)
            if(item.hashIndex == ((Batch)b[i]).hashIndex)
                return i;

        return -1;
    }

    public static int index(int hashIndex, Data b[], int n) {
        for(int i = 0; i < n; i++)
            if(hashIndex == ((Batch)b[i]).hashIndex)
                return i;

        return -1;
    }

    public static Batch getRef(int hashIndex, Data b[], int n) {
        for(int i = 0; i < n; i++)
            if(hashIndex == ((Batch)b[i]).hashIndex)
                return (Batch)b[i];

        return null;
    }

    public Data copy() {
        return new Batch(sunMor, hashIndex, newCopy(s));
    }

    public int getHourCourseAssign(Data c[], int n) {
        int f = 0;
        Course cRef;

        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if(cRef.batchHashIndex == hashIndex)
                f += (cRef.nClass * cRef.duration);
        }

        return f;
    }


    public int numberOfCourseAssign(Data c[], int n) {
        int x = 0;
        Course cRef;

        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if( cRef.batchHashIndex == hashIndex )
                x++;
        }

        return x;
    }

    public void updatePK(Data c[], int n, int newHashIndex) {
        int oldHashIndex = hashIndex;
        Course cRef;

        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if( cRef.batchHashIndex == oldHashIndex )
                cRef.batchHashIndex = newHashIndex ;
        }
    }

    public String getAssignCourseList(Data c[], int n) {
        int nCourse = numberOfCourseAssign(c, n);
        if(nCourse == 0) return null;
        String s = "";

        Course cRef;
        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if( cRef.batchHashIndex == hashIndex )
                s += cRef.getNickName(c, n)+"\n";
        }

        return s;
    }

    public String[] getRow(Data c[], int n) {
        String s[] = { yrSemName(getYr()), yrSemName(getSem()), secName(getSec()), ""
            +(sunMor? "Sunday Morning": "Sunday Evening"),
                    (nFree() - getHourCourseAssign(c, n))+" hour(s)",
                    getHourCourseAssign(c, n)+" hour(s)", nManu()+" hour(s)"};
        return s;
    }

    public int stillFree(Data c[], int n) {
        return (nFree() - getHourCourseAssign(c, n));
    }

    public String getName(Data c[], int n) {
        return yrSemName(getYr()) + " year, " + yrSemName(getSem()) + " semester " +
            "section "+secName(getSec())+
            " "+(nFree() - getHourCourseAssign(c, n))+" hour(s) Still Free, "+
            getHourCourseAssign(c, n)+" hour(s) Course Assign, "+nManu()+" hour(s) manually assigned.";
    }

    public String getNickName(Data c[], int n) {
        return ""+getNickName(hashIndex)+", "+(nFree() - getHourCourseAssign(c, n))+" hr free.";
    }

    /**
     * Calculate name from a hash index,
     * used bu Course class.
     * @param a hash index
     * @return a string that corespond to the hash index.
     */
    public static String getNickName(int hi) {
        return ( getYr(hi) + 1)+"/"+( getSem(hi) + 1 )+"-"+secName( getSec(hi) );
    }

    public String getNickName() {
        int hi = hashIndex;
        return ( getYr(hi) + 1)+"/"+( getSem(hi) + 1 )+"-"+secName( getSec(hi) );
    }

    public int nFree() { return nSession(s, MyCheckBox.FREE); }
    public int nBusy() { return nSession(s, MyCheckBox.BUSY); }
    public int nManu() { return nSession(s, MyCheckBox.MANU); }

    public RCList getManualRCList(Course c[], int nCourse) {
        RCList rcl = new RCList(Basic.nDay * Basic.nPeriod);

        int i = -1;
        try {
            for(i = 0 ; i < nCourse; i++)
                if(c[i].batchHashIndex == hashIndex)
                    rcl.add( c[i].manuRCList );
        }
        catch (Exception ex) {
            MyError.debug("Batch.getManualRCList(Course c[], int nCourse): "
                          +c[i]+" i:"+i+" nCourse: "+nCourse);
        }

        return rcl;
    }

    public RCList getAutoRCList(Course c[], int nCourse) {
        RCList rcl = new RCList(Basic.nDay * Basic.nPeriod);

        for(int i = 0 ; i < nCourse; i++)
            if(c[i].batchHashIndex == hashIndex)
                rcl.add(c[i].autoRCList);

        //MyError.debug("Batch.getAutoRCList(Course c[], int nCourse): "+rcl.nCell);
        return rcl;
    }

    public RCList getTotalRCList(Course c[], int nCourse) {
        RCList rcl = getManualRCList(c, nCourse);
        rcl.add( getAutoRCList(c, nCourse) );

        //MyError.debug("Batch.getTotalRCList(Course c[], int nCourse): "+rcl.nCell);
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
}