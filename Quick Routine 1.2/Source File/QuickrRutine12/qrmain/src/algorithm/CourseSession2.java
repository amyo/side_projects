package algorithm;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import global.*;
import datastructure.*;

public class CourseSession2 extends MaxFlow {
    int nTheoryCource;
    int nSession;

    int toGlobalCourseIndex[];

    /* Forbid a batch to have two classes at same session */
    boolean batchFree[][];

    /* Forbid a course teacher to have two classes at same session */
    boolean courseTeacherFree[][];

    /* Forbid a course to be assigned on same day (7 sessions) */
    boolean dayFree[][];

    int toRealSessionIndex(int graphIndex) { return graphIndex - nTheoryCource; }
    int toGraphSessionIndex(int realIndex) { return realIndex + nTheoryCource; }

    Course c[]; int nCourse;
    public CourseSession2(Course c[], int nCourse) {
        this.c = c;
        this.nCourse = nCourse;

        initArray();

        filterCource();
        deleteAllAuto(c, nCourse);

        initCap();
        //p2(cap);

        initAdj();
        initFrees();
        initBusy();

        //p2(cap);

        //pAdj(adj);

        int f = maxFlow();

        //MyError.debug("f: "+f);

        //p2(cap);
        //MyError.debug("Making Routine...f: "+f);
        assign();
    }

    /**
     * This function builds a virtual graph that determine a edge can exist betn.
     * a course and a session index. If a course have assigned a particular session
     * it block(delete edge) that session for that courses' batch and teacher,
     * it also block that sessions' full weak for another class of that perticuler
     * course.
     * @param graphCourseIndex-takes node index of a course in a graph
     * @param graphSessionIndex-takes node nidex of a session ina graph
     * @param value-determine whether edge have to delete or draw. true
     * means draw false means delete.
     * @see isFree()
     */
    void setFrees(int graphCourseIndex, int graphSessionIndex, boolean value) {
        int ts = toRealSessionIndex(graphSessionIndex);
        int k = toGlobalCourseIndex[ graphCourseIndex ];

        /* Extract batch and techer. */
        int batchHashIndex = c[k].batchHashIndex;
        String tid = c[k].tid;

        /* Block all course at this session that have same batch and teacher. */
        for(int i = 0; i < nTheoryCource; i++) {

            /* i is graph course index. */
            k = toGlobalCourseIndex[i];

            /* Block that batch for that session with other course. */
            if(c[k].batchHashIndex == batchHashIndex)
                batchFree[i][ts] = value;

            /* Filtered course must have a teacher. */
            /* Block that teacher for that session with other course. */
            if(c[k].tid.equalsIgnoreCase(tid))
                courseTeacherFree[i][ts] = value;
        }

        /* Block that course for any other day of that weak. */
        int gci = graphCourseIndex;
        for(int j = 0; j < deg[gci]; j++) {
            int gsi = adj[gci][j];
            int rsi = toRealSessionIndex(gsi);

            if(Session.isSameDay(ts, rsi))
                dayFree[gci][rsi] = value;
        }


    }

    /**
     * Determine a path exist betn. a course and a session.
     * @param graphCourseIndex
     * @param graphSessionIndex
     * @return
     */
    boolean isFree(int gci, int gsi) {
        /* Edge: Course --> Session. */
        if(isCourse(gci) && isSession(gsi) ) {
            int rci = gci;
            int rsi = toRealSessionIndex(gsi);

            return cap[gci][gsi] > 0

                    && batchFree[rci][rsi]
                    && courseTeacherFree[rci][rsi]
                    && dayFree[rci][rsi];
        }

        /* Edge: Connection source or sink. */
        else return cap[gci][gsi] > 0;
    }

    boolean isCourse(int graphIndex) {
        return (graphIndex >= 0) && (graphIndex < nTheoryCource);
    }

    boolean isSession(int graphIndex) {
        return (graphIndex >= nTheoryCource) && (graphIndex < n-2);
    }

    boolean isCourseOrSession(int graphIndex) {
        return isCourse( graphIndex ) || isSession( graphIndex );
    }

    void filterCource() {
        int k = 0;
        for(int i = 0; i < nCourse; i++)
            if(
                    /* Only theory courses' is concern for auto routine. */
                    // c[i].type == Course.THEORY &&

               /* A course must required a course teacher to have a routine. */
               c[i].tid != null)

          toGlobalCourseIndex[k++] = i;
    }

    int getNumberOfWithTeacherCourse() {
        int k = 0;
        for(int i = 0; i < nCourse; i++)
            if(c[i].tid != null)
                k++;

        return k;
    }

    void initArray() {
        /* MAJOR CHANGE. */
        nTheoryCource = getNumberOfWithTeacherCourse();

        nSession = (Basic.nDay * Basic.nPeriod);

        n = nTheoryCource + nSession + 2;
        src = n-2;
        sink = n-1;

        cap = new int[n][n];
        adj = new int[n][n];
        deg = new int[n];

        p = new int[n];
        visited = new boolean[n];

        toGlobalCourseIndex = new int[nTheoryCource];

        /* Forbid a batch to have two classes at same session */
        batchFree = new boolean[ nCourse ][ nSession ];

        /* Forbid a course teacher to have two classes at same session */
        courseTeacherFree = new boolean[ nCourse ][ nSession ];

        /* Forbid a course to be assigned on same day (7 sessions) */
        dayFree = new boolean[ nCourse ][ nSession ];
    }

    void initCap() {
        memsetCap(cap);

        /* Source to course. */
        for(int i= 0; i < nTheoryCource; i++) {
            int j = toGlobalCourseIndex[i];

            /* Only which are left. */
            cap[src][i] = c[j].nClass - c[j].manuRCList.nCell;
        }

        /* Course to Session. */
        for(int i = 0; i < nTheoryCource; i++)
            for(int j = 0; j < nSession; j++) {
                int k = toGraphSessionIndex(j);
                    cap[i][k] = 1;
            }

        /* Session to sink. */
        int nFreeRoomAt[] = Room.getStillFreeTheoryAtTimestamp(G.r, G.nRoom, c, nCourse);
        for(int i = 0; i < nSession; i++) {
            int j = toGraphSessionIndex(i);
            cap[j][sink] = nFreeRoomAt[i];
        }
    }

    /**
     * This function builds adj arry that will be used by bfs().
     * adj[][] array doesnt' used for efficiency, cause graph is complete.
     * It only helps to set priority the path and eventually session.
     */
    void initAdj() {
        memsetAdj(deg);

        /* Source to course. */
        for(int i= 0; i < nTheoryCource; i++) {
            connectDirect(src, i);
        }

        /* Course to Session. */
        for(int i = 0; i < nTheoryCource; i++) {
            int gci = toGlobalCourseIndex[i];
            int gbi = Batch.index(G.c[gci].batchHashIndex, G.b, G.nBatch);

            int style[], k;
            if(G.b[gbi].sunMor) style = RoutineStyle.getSunMornStyle();
            else style = RoutineStyle.getSunEveningStyle();

            for(int j = 0; j < nSession; j++) {
                k = toGraphSessionIndex( style[j] );
                connectDirect(i, k);

                k = toGraphSessionIndex( style[nSession - j - 1] );
                connectDirect(k, i);
            }
        }



        /* Session to sink. */
        int nFreeRoomAt[] = Room.getStillFreeTheoryAtTimestamp(G.r, G.nRoom, c, nCourse);
        for(int i = 0; i < nSession; i++) {
            int j = toGraphSessionIndex(i);
            //cap[j][sink] = nFreeRoomAt[i];
            connectDirect(j, sink);
        }
    }

    void initBusy() {
        for(int i = 0; i < nTheoryCource; i++) {
            int k = toGlobalCourseIndex[i];

            int courseBatchHashIndex = c[k].batchHashIndex;
            String courseTeacherID = c[k].tid;

            // int dura = c[k].duration;

            int gbi = Batch.index(courseBatchHashIndex, G.b, G.nBatch);
            int gti = Teacher.index(courseTeacherID, G.t, G.nTeacher);

            int ts[];
            for(int times = 0; times < 2; times++) {
                if(times == 0) {
                    ts = G.b[ gbi ].getManualAndSetBusySession(c, nCourse).toTimeStamp();
                }
                else {
                    ts = G.t[ gti ].getManualAndSetBusySession(c, nCourse).toTimeStamp();
                }

                for(int j = 0; j < nSession; j++)
                    if(ts[j] == Session.BUSY)
                        cap[i][ toGraphSessionIndex(j) ] = 0;
            }
        }
    }

    void initFrees() {
        memsetFree(batchFree);
        memsetFree(courseTeacherFree);
        memsetFree(dayFree);

        /* Adjust graph with already made routine. */
        /* For THEORY. */
        for(int i = 0; i < nTheoryCource; i++) {
            int k = toGlobalCourseIndex[i];

            for(int j = 0; j < c[k].manuRCList.nCell; j++) {
                int ts = c[k].manuRCList.rCell[j].timeStamp;
                int gsi = toGraphSessionIndex(ts);

                /* Off corespanding edge. */
                setFrees(i, gsi, false);
            }
        }
    }

    boolean dfs() {
        memsetVisited(visited);

        pathFound = false;
        //G.p("dfs start--->");
        dfs_visit(src);
      //  G.p("\n");
        return pathFound;
    }

    void dfs_visit(int u) {
        if(u == sink) pathFound = true;
       // G.p("-"+u);

        if(pathFound) {
      //      G.p(".End\n");
            return;
        }

        visited[u] = true;

        /* Make Block. */
        // setFreeCourseToSession(p[u], u, true);
        if(isCourse(p[u]) && isSession(u)) {
            setFrees(p[u], u, false);
        }
        if(isCourse(u) && isSession(p[u])) {
            setFrees(u, p[u], true);
        }

        for(int j = 0; j < deg[u]; j++) {
            int v = adj[u][j];

            //if(u == 0 && v == 2)
            //MyError.debug("0->2 "+!visited[v]+" "+isFree(u, v));

            if(!visited[v] && isFree(u, v)) {
                p[v] = u;
                dfs_visit(v);
            }
        }

    //    G.p(" faild("+u+") ");
        /* Free Block. */
        // setFreeCourseToSession(p[u], u, false);
        if(isCourse(p[u]) && isSession(u)) {
            setFrees(p[u], u, true);
        }
        if(isCourse(u) && isSession(p[u])) {
            setFrees(u, p[u], false);
        }
    }

    void augmentPath() {
        int u;
        for(int v = sink; v != src; v = u) {
            u = p[v];
            cap[u][v]--;
            cap[v][u]++;

            if(isCourse(u) && isSession(v)) setFrees(u, v, false);
            if(isCourse(v) && isSession(u)) setFrees(v, u, true);
        }
    }

    int maxFlow() {
        int f = 0;
        while(dfs()) {
            augmentPath();
            f++;
        }

        return f;
    }

    int nTheoryRoomAt[];
    int nTheoryRoomTakenAt[];

    String ridList[][];

    void makeRoomList() {
        nTheoryRoomAt = new int[nSession];
        nTheoryRoomTakenAt = new int[nSession];

        memsetAdj(nTheoryRoomAt);
        memsetAdj(nTheoryRoomTakenAt);

        ridList = new String[nSession][G.nRoom];

        for(int i = 0; i < G.nRoom; i++)
            /* If room is for theory. */
            if(G.r[i].type == Room.THEORY) {
                /* First load set busy. */
                Session busySes = new Session(G.r[i].s);

                /* Add manual busy. */
                busySes.unionValue(G.r[i].getManualBusySession(c, nCourse), Session.BUSY);

                /* Transfer to timestamp. */
                int ts[] = Session.toTimeStamp(busySes.s);

                int k = - 1;
                try {
                for(k = 0; k < ts.length; k++)
                    if(ts[k] == Session.FREE)
                        ridList[ k ][ nTheoryRoomAt[k]++ ] = new String(G.r[i].id);
                } catch(Exception e) {
                    MyError.debug("["+ridList.length+","+ridList[0].length+"] ("+k+","+nTheoryRoomAt.length+"");
                }
            }
    }

    String takeARoomIDAt(int ts) {
        try {
        if(nTheoryRoomTakenAt[ts] >= nTheoryRoomAt[ts]) {
            MyError.debug("All room taken at: "+ts);
            return null;
        }
        else return ridList[ts][ nTheoryRoomTakenAt[ts]++ ];
        } catch(Exception e) {
            return null;
        }
    }

    public static void deleteAllAuto(Course c[], int n) {
        for(int i = 0; i < n; i++) {
            c[i].autoRCList.deleteAll();
        }
    }

    void assign() {
        makeRoomList();

        /* Assign timeStamp */
        for(int i = 0; i < nTheoryCource; i++) {
            int k = toGlobalCourseIndex[i];
            // c[k].autoRCList.deleteAll();

            for(int j = 0; j < nSession; j++)
                if(cap[ toGraphSessionIndex(j) ][i] > 0) {
                    c[k].autoRCList.add(new RoutineCell(j, c[k].id, takeARoomIDAt(j) ));
                }
        }
    }

    String getATotalFreeRIDAt(int ts) {
        for(int i = 0; i < G.nRoom; i++)
            if(G.r[i].type == Room.THEORY && G.r[i].isTotalFreeAt(ts, c, nTheoryCource))
                return new String( G.r[i].id );

        return null;
    }
}