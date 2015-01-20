package datastructure;

import java.io.*;
import global.*;

/**
 * Title:        Data Stucture
 * Description:  Complete Data Structure of QuickRoutine
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author Simanto, Jewel & Milton
 * @version 1.0
 */

public class Course extends Data
implements Serializable
{
    public String id, tid, rid;
    public int batchHashIndex, type, duration, nClass;
    public float credit;

    /**
     * Last output.
     * mrc:     manual routine cell array.
     * arc:     automatic routine carray.
     */
    public RCList manuRCList;
    public RCList autoRCList;

    public static int THEORY = 0, LAB = 1;

    /**
     * Name.
     */
    public static String typeName[] = {"Theory", "Lab"};
    public static String labCreditName[] =
    {"0.5", "1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0", "4.5", "5.0", "5.5", "6.0"};
    public static String theoryCreditName[] = {"1.0", "2.0", "3.0", "4.0", "5.0", "6.0"};

    public Course(String id, int batchHashIndex, String tid , int type,
                 String rid, float credit, int duration, int nClass)
    {
        initID(id, batchHashIndex, tid, type, rid, credit, duration, nClass);

        manuRCList = new RCList(nClass);
        autoRCList = new RCList(nClass);
    }

    public Course(String id, int batchHashIndex, String tid , int type,
                 String rid, float credit, int duration, int nClass,
                 RCList manuRCList, RCList autoRCList)
    {
        initID(id, batchHashIndex, tid, type, rid, credit, duration, nClass);

        this.manuRCList = manuRCList.copy();
        this.autoRCList = autoRCList.copy();
   }

   void initID(String id, int batchHashIndex, String tid , int type,
                 String rid, float credit, int duration, int nClass) {
        this.id = id;
        this.batchHashIndex = batchHashIndex;
        this.tid = tid;
        this.type = type;

        this.rid = rid;
        this.credit = credit;
        this.duration = duration;
        this.nClass = nClass;
   }

    public Data copy() {
        return new Course(id, batchHashIndex, tid, type, rid, credit, duration, nClass
                        , manuRCList, autoRCList );
    }

    public static int index(String id, Data c[], int n) {
        for(int i = 0; i < n; i++)
            if(id.equalsIgnoreCase( ((Course)c[i]).id ))
                return i;

        return -1;
    }

    public String[] getRow(Data c[], int n) {
        String  s[] = {id, Batch.getNickName(batchHashIndex), tid == null? "Assing Later": tid,
                    typeName[type], rid== null? "Assign Later": rid, ""+credit, ""+duration+" hour(s)", ""+nClass};
        return s;
    }

    public String getName(Data c[], int n) {
        return id +", "+ Batch.getNickName(batchHashIndex) +", "+ (tid == null? "Assing Later": tid) + ", " +
                typeName[type] +", "+ (rid== null? "Assign Later": rid) +", "+ ", " +credit+ " credit, " +
                "duration: " + duration+" hour(s)" +", "+ nClass +" class(s) weekly.";
    }

    public String getNickName(Data c[], int n) {
        return ""+id+", "+typeName[type]+", "+credit+" credit, "
                 + Batch.getNickName(batchHashIndex);
    }

    public String getNickName() {
        return ""+id+", "+typeName[type]+", "+credit+" credit";
    }
    
    /**
     * Filter all one hour course that have assigned a course teacher.
     * @param c-list of course.
     * @param nCourse-number of course in the list.
     * @return number of filtered course.
     */
    public static int getCountTheoryCourse(Course c[], int nCourse) {
        int count = 0;
        for(int i = 0; i < nCourse; i++)
            if(c[i].tid != null && c[i].type == THEORY) 
                count++;
        
        return count;
    }
}