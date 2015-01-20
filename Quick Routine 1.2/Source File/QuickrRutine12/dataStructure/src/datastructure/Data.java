package datastructure;

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

public class Data
{
    public Data()
    {
    }

    public Data copy() { return null; }
    public String[] getRow(Data c[], int n) { return null; }
    public String getName(Data c[], int n) { return null; }
    public String getNickName(Data c[], int n) { return null; }
    public int getHourCourseAssign() { return -1; }
    public void updatePK(Data c[], int n, String newID) {}

    public int[][] newCopy(int a[][]) {
        int b[][] = new int[a.length][a[0].length];
        for(int i = 0; i < a.length; i++)
            for(int j = 0; j < a[i].length; j++)
                b[i][j] = a[i][j];

        return b;
    }

    public static int delete(Data a[], int k, int n) {
        for(int i = k+1; i < n; i++)
            a[i-1] = a[i];

        return n-1;
    }

    public int nSession(int s[][], int state) {
        int n = 0;
        for(int i = 0; i < s.length; i++)
            for(int j = 0; j < s[i].length; j++)
                n += (s[i][j] == state? 1: 0);

        return n;
    }    

    public Session getAssignBusySession(RCList rcl, Course c[], int nCourse) {
        Session s = new Session(Session.FREE);
        
        for(int k = 0; k < rcl.nCell; k++) {
            int i = rcl.rCell[k].getDay();
            int j = rcl.rCell[k].getPeriod();
            int n = rcl.rCell[k].getDuration(c, nCourse);
            
            for(int x = 0; x < n; x++)
                s.s[i][j + x] = Session.BUSY;
        }
        
        return s;
    }

}