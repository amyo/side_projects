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
import java.io.*;

public class RoutineCell
implements Serializable
{
    /**
     * Output of this software.
     * to set this timestamp for this course.
     */
    public int timeStamp;
    public String cid, rid;

    public RoutineCell(int timeStamp, String cid, String rid) {
	this.timeStamp = timeStamp;
	initID(cid, rid);
    }

    public RoutineCell(int day, int period, String cid, String rid) {
	this.timeStamp = Session.toTimeStamp(day, period);
	initID(cid, rid);
    }

    void initID(String cid, String rid) {
	this.cid = new String (cid);
	this.rid = new String (rid);
    }

    public RoutineCell copy() {
	return new RoutineCell(timeStamp, cid, rid);
    }

    public boolean equal(RoutineCell rc) {
	return
	    this.timeStamp == rc.timeStamp
	    && this.cid.equalsIgnoreCase(rc.cid)
	    && this.rid.equalsIgnoreCase(rc.rid);
    }

    public int getDay() {
	return Session.getDay( timeStamp );
    }

    public int getPeriod() {
	return Session.getPeriod ( timeStamp );
    }

    public Course getCourseRef(Course c[], int nCourse) {
	for(int i = 0; i < nCourse; i++)
	    if(c[i].id.equalsIgnoreCase(cid))
		return c[i];

	return null;
    }

    public String getTID(Course c[], int nCourse) {
        Course cRef = getCourseRef(c, nCourse);
	return cRef == null? null: cRef.tid;
    }

    public int getDuration(Course c[], int nCourse) {
        Course cRef = getCourseRef(c, nCourse);
	return cRef == null? -1: cRef.duration;
    }

    public String getName() {
        return cid +
                " on " + G.dFullName[ Session.getDay(timeStamp) ] +
                " at " + G.getPerName( Session.getPeriod(timeStamp) );
    }
}