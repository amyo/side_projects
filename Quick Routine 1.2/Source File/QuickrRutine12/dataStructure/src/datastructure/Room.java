package datastructure;

import global.*;
import java.io.*;

import qrinput.*;

public class Room extends Data
implements Serializable
{
    public int cap;
    public String id, des;
    public int type;
    public int s[][] = new int[Basic.nDay][Basic.nPeriod];

    public static int THEORY = 0, LAB = 1;
    public static String roomTypeName[] = {"Theory", "Lab"};

    public Room(String id, String des, int cap, int type, int s[][])
    {
        this.id = id;
        this.des = des;
        this.cap = cap;
        this.type = type;
        this.s = s;
    }

    public static int index(String id, Data r[], int n) {
        for(int i = 0; i < n; i++)
            if(id.equalsIgnoreCase( ((Room)r[i]).id ))
                return i;

        return -1;
    }

    public static Room getRef(String id, Data r[], int n) {
        for(int i = 0; i < n; i++)
            if(id.equalsIgnoreCase( ((Room)r[i]).id ))
                return ((Room)r[i]);

        return null;
    }

/*
    public static int typeIndex(String id, Data r[], int n, int type) {
        int k = 0;
        for(int i = 0; i < n; i++) {
            if(((Room)r[i]).type == type) {
                if(id.equalsIgnoreCase( ((Room)r[i]).id ) ) return k;
                else k++;
            }
        }

        return -1;
    }

    public static String getXthTypeID(int x, Data r[], int n, int type) {
        int k = 0;
        for(int i = 0; i < n; i++)
            if(((Room)r[i]).type == type) {
                if(k == x) return ((Room)r[i]).id;
                else k++;
            }

        return null;
    }

    public static int getXthTypeIndex(int x, Data r[], int n, int type) {
        int k = 0;
        for(int i = 0; i < n; i++)
            if(((Room)r[i]).type == type) {
                if(k == x) return i;
                else k++;
            }

        return -1;
    }
*/

    public Data copy() {
        return new Room(id, des, cap, type, newCopy(s));
    }

    public int getHourCourseAssign(Data c[], int n) {
        int f = 0;
        Course cRef;

        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if(cRef == null) MyError.show("cRef NULL in ROOM.");

            if( cRef.rid != null    // Being sure that Course have a room...
                && cRef.rid.equalsIgnoreCase(id))
                f += (cRef.nClass * cRef.duration);
        }

        return f;
    }

    public int numberOfCourseAssign(Data c[], int n) {
        int x = 0;
        Course cRef;

        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if( cRef.rid != null    // Being sure that Course have a room...
                && cRef.rid.equalsIgnoreCase(id) )
                x++;
        }

        return x;
    }

    public void updatePK(Data c[], int n, String newID) {
        String oldID = new String(id);
        Course cRef;

        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if( cRef.rid != null    // Being sure that Course have a room...
                && cRef.rid.equalsIgnoreCase(oldID))
                cRef.rid = newID;
        }
    }

    /**
     * Return the lab course only for which this room is fixed.
     * @param c
     * @param n
     * @return
     */
    public String getAssignCourseList(Data c[], int n) {
        int nCourse = numberOfCourseAssign(c, n);
        if(nCourse == 0) return null;
        String s = "";

        Course cRef;
        for(int i = 0; i < n; i++) {
            cRef = ((Course)c[i]);

            if( cRef.rid != null    // Being sure that Course have a teacher...
                && cRef.rid.equalsIgnoreCase(id))
                s += cRef.getNickName(c, n)+"\n";
        }

        return s;
    }

    public static Session getStillFreeTheoryAtSession(Room r[], int nRoom
            , Course c[], int nCourse) {
         Session nFreeAt = new Session(0);

         for(int k = 0; k < nRoom; k++)

             /* Filter THEORY */
             if(r[k].type == Room.THEORY) {
                 Session busySes = new Session( r[k].s );
                 busySes.unionValue(r[k].getManualBusySession(c, nCourse), Session.BUSY);

                 for(int i = 0; i < busySes.s.length; i++)
                     for(int j = 0; j < busySes.s[i].length; j++)
                         if(busySes.s[i][j] != Session.BUSY)
                             nFreeAt.s[i][j]++;
             }

         return nFreeAt;
    }

    public static int[] getStillFreeTheoryAtTimestamp(Room r[], int nRoom
            , Course c[], int nCourse)
    {
        int nTimeStampAt[] = new int[Basic.nDay * Basic.nPeriod];
        Session nFreeAt = getStillFreeTheoryAtSession(r, nRoom, c, nCourse);

        for(int i = 0; i < nFreeAt.s.length; i++)
            for(int j = 0; j < nFreeAt.s[i].length; j++)
                nTimeStampAt[ Session.toTimeStamp(i, j) ] = nFreeAt.s[i][j] ;

        return nTimeStampAt;
    }

    public int getStillFree(Course c[], int nCource) {
        return nFree() - getHourCourseAssign(c, nCource);
    }

    public String[] getRow(Data c[], int n) {
        String s[] = {id, des, ""+cap, type == THEORY? "Theory": "Lab",
                    (nFree() - getHourCourseAssign(c, n))+" hour(s)",
                    getHourCourseAssign(c, n)+" hour(s)", nManu()+" hour(s)"};
        return s;
    }

    public int stillFree(Data c[], int n) {
        return (nFree() - getHourCourseAssign(c, n));
    }

    public String getName(Data c[], int n) {
        return ""+id+", "+(type == THEORY? "Theory": "Lab")+", "+des+", cap: "+cap+", "+
                    (nFree() - getHourCourseAssign(c, n))+" hour(s) Still Free, "
                    +getHourCourseAssign(c, n)+" hour(s) Course Assign, "+nManu()+" hour(s) manually assigned.";
    }

    public String getNickName(Data c[], int n) {
        return ""+id+", "+(nFree() - getHourCourseAssign(c, n))+" hr free, "
                +(type == THEORY? "Theory": "Lab")+", "+des+".";
    }

    public RCList getManualRCList(Course c[], int nCourse) {
        RCList rcl = new RCList(Basic.nDay * Basic.nPeriod);

        int i = -1, j = -1;

            try {
                for(i = 0 ; i < nCourse; i++)
                    for(j = 0; j < c[i].manuRCList.nCell; j++)
                        if(c[i].manuRCList.rCell[j].rid != null &&
                                c[i].manuRCList.rCell[j].rid.equalsIgnoreCase( id ))
                            rcl.add( c[i].manuRCList.rCell[j] );
            }
            catch (Exception ex) {
                if(c[i] == null)
                MyError.debug("Room.getManualRCList(Course c[], int nCourse) c[], i:"+i);
            }

        return rcl;
    }

    public RCList getAutoRCList(Course c[], int nCourse) {
        RCList rcl = new RCList(Basic.nDay * Basic.nPeriod);

            for(int i = 0 ; i < nCourse; i++)
                for(int j = 0; j < c[i].autoRCList.nCell; j++)
                    if(c[i].autoRCList.rCell[j].rid != null &&
                            c[i].autoRCList.rCell[j].rid.equalsIgnoreCase( id ))
                        rcl.add( c[i].autoRCList.rCell[j] );

        return rcl;
    }

    public RCList getTotalRCList(Course c[], int nCourse) {
        RCList rcl = getManualRCList(c, nCourse);
        rcl.add( getAutoRCList(c, nCourse) );

        return rcl;
    }

    //public Session getAssignBusySession() {
    //}

    public Session getManualBusySession(Course c[], int nCourse) {
        return getAssignBusySession(getManualRCList(c, nCourse), c, nCourse);
    }

    public Session getTotalBusySession(Course c[], int nCourse) {
        return getAssignBusySession(getTotalRCList(c, nCourse), c, nCourse);
    }

    public boolean isTotalFreeAt(int ts, Course c[], int nCourse) {
        int i = Session.getDay(ts);
        int j = Session.getPeriod(ts);
        Session ses = getTotalBusySession(c, nCourse);

        return ses.s[i][j] != Session.BUSY;
    }

    public int nFree() { return nSession(s, MyCheckBox.FREE); }
    public int nBusy() { return nSession(s, MyCheckBox.BUSY); }
    public int nManu() { return nSession(s, MyCheckBox.MANU); }
}