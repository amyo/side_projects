package datastructure;

/**
 * Title:        Data Stucture
 * Description:  Complete Data Structure of QuickRoutine
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author Simanto, Jewel & Milton
 * @version 1.0
 */

import global.*;

public class Session
{
    public int s[][] = new int[Basic.nDay][Basic.nPeriod];
    public static final int FREE = 0, BUSY = 1;

    public Session(int s[][]) {
         // this.s = (int[][])s.clone();
        for(int i = 0; i < s.length; i++)
            for(int j = 0; j < s[i].length; j++)
                this.s[i][j] = s[i][j];
    }

    public Session(int VALUE) {
        for(int i = 0; i < s.length; i++)
            for(int j = 0; j < s[i].length; j++)
                s[i][j] = VALUE;
    }

    public Session copy() {
        return new Session(s);
    }

    public static int toTimeStamp(int day, int period) {
	return (day * Basic.nPeriod) + period;
    }

    public static int[] toTimeStamp(int s[][]) {
        int ts[] = new int[ Basic.nDay * Basic.nPeriod ];
        for(int i = 0; i < s.length; i++)
            for(int j = 0; j < s[i].length; j++)
                ts[ toTimeStamp(i, j) ] = s[i][j];

        return ts;
    }

    public int[] toTimeStamp() {
        return toTimeStamp(s);
    }

    public static int getDay(int timeStamp) {
	return timeStamp / Basic.nPeriod;
    }

    public static int getPeriod(int timeStamp) {
	return timeStamp % Basic.nPeriod;
    }

    public static boolean isSameDay(int ts1, int ts2) {
        return getDay(ts1) == getDay(ts2);
    }

/*
    public static int getFirstPeriodTimeStamp(int timeStamp) {
        return timeStamp - getPeriod(timeStamp);
    }

    public static int getLastPeriodTimeStamp(int timeStamp) {
        return getFirstPeriodTimeStamp(timeStamp) + Basic.nPeriod - 1;
    }
*/

    public int nType(int type) {
        int k = 0;
        for(int i = 0; i < s.length; i++)
            for(int j = 0; j < s[i].length; j++)
                if(s[i][j] == type)
                    k++;

        return k;
    }

    public Session unionValue(Session s, int value) {
        for(int i = 0; i < s.s.length; i++)
            for(int j = 0; j < s.s[i].length; j++)
                if(s.s[i][j] == value)
                    this.s[i][j] = value;

        return this;
    }

    public int nFree() {
        return nType(FREE);
    }

    public int nBusy() {
        return nType(BUSY);
    }

    public int nStillFree() {
        return nFree() - nBusy();
    }
}