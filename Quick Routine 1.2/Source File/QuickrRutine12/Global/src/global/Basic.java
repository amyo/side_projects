package global;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2004
 * Company:
 * @author
 * @version 1.0
 */

import java.io.*;

public class Basic
implements Serializable
{
    public static final int nDay = 5, nPeriod = 9;
    public static final int lunchPeriod = 5;


    public static final int maxTheoryDuration = 1;
    public static final int maxLabDuration = 5;

    public static final int maxTheoryCredit = 5;
    public static final int maxLabCredit = 5;

    public static final int maxYear = 5;
    public static int maxSemester = 2;
    public static int maxSection = 2;

    //----------------------
    public int myMaxSemester = 2;
    public int myMaxSection = 2;

    String myNmae = "";
    //---------------------------

    public Basic(int nSem, int nSec, String name) {
        myMaxSemester = nSem;
        myMaxSection = nSec;
        myNmae = name;
    }

    public static void loadBasic(Basic bs) {
        maxSemester = bs.myMaxSemester;
        maxSection = bs.myMaxSection;
        G.name = bs.myNmae;
    }
}