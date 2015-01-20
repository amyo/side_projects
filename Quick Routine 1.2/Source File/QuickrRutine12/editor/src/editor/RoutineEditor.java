package editor;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2004
 * Company:
 * @author
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import datastructure.*;
import global.*;
import qrinput.*;
import com.borland.jbcl.layout.*;
import  java.awt.print.*;

public class RoutineEditor extends JPanel implements Printable
{
    /* Contains tittlr of routine.*/
    public int catIndex, BTRIndex;

    /* Contain day-period grid of routine. */
    JPanel gridPan = new JPanel(new GridLayout(Basic.nDay + 1, Basic.nPeriod + 1, 1, 1 ));

    /* Show day and period cell. */
    EditorCell pc[] = new EditorCell[ Basic.nPeriod + 1];
    EditorCell dc[] = new EditorCell[ Basic.nDay ];

    /* Show each session of a routine. */
    EditorCell ec[][] = new EditorCell[ Basic.nDay][ Basic.nPeriod ];

    FlowLayout flay;

    /* Store information of last seted busy session to remove next time. */
    Session lastBusylabelSession[] = new Session[6];

    /* this reference is used to access directly course data. */
    /* from which routine is show. */
    CoursePan cp;

    /* Mother reference at which instance of this claas is decleared. */
    /* Builds comunication between them. */
    ManPanLayout manPanRef;
    boolean isMainEditor;
    public JPanel withTitleGridBtnPan = new JPanel(new BorderLayout());

    public RoutineEditor(ManPanLayout manPanRef, CoursePan cp
                         , int catIndex, int BTRIndex, RCList rcl
                         , boolean isMainEditor, LRPan btnPan)
    {
	  this.manPanRef = manPanRef;
	  this.catIndex = catIndex;
	  this.BTRIndex = BTRIndex;
	  this.cp = cp;
          this.isMainEditor = isMainEditor;

          /* Set all layout to show aroutine */
	  setAllLayout();
          if(isMainEditor) setMainEditorLayout(btnPan);

          /* reload a firs given routine. */
	  reloadRCList(rcl, cp);
        setSize(500, 500);
    }

    public Image getRoutineImage() {
        return withTitleGridBtnPan.createImage(500, 500);
    }

    /**
     * Set a routine data structure to a show it graphically to user
     * @param rcl-routine data structure.
     * @param cp-access course information used by rcl.
     */
    public void reloadRCList(RCList rcl, CoursePan cp) {
	  if(rcl != null) {
		int i, j = -1, k = -1;

		/* Remove Past. */
		for(i = 0; i < ec.length; i++)
		    for(j = 0; j < ec[i].length; j++)
			  if(ec[i][j].isClassExsist())
				ec[i][j].removeRoutineCell();

		/* Set Latest. */
		try {
		for(k = 0; k < rcl.nCell; k++) {
                    i = rcl.rCell[k].getDay();
                    j = rcl.rCell[k].getPeriod();

                    if(ec[i][j].isClassExsist()) MyError.debug("Overlap found at: "+i+", "+j);
                    else ec[i][j].setRoutineCell(rcl.rCell[k], cp);

                    /* If class duration is more that one hours */
                    int dura = ec[i][j].rc.getDuration((Course[])cp.temp, cp.nData);

                    /* Set Xtra class. */
                    for(int x = 1; x < dura; x++)
                        if(ec[i][j + x].isClassExsist()) MyError.debug("Overlap found at"+i+", "+(j+1));
                        else ec[i][j + x].setSubRoutineCell(rcl.rCell[k], cp);
		}

		} catch(Exception e) {
		    MyError.debug("RoutineEditor.reloadRCList(): "
                                  +rcl.rCell[k].getDay()+", "
                                  +rcl.rCell[k].getPeriod()+", "
                                  +rcl.nCell+", "
                                  +k);

                    e.printStackTrace();
		}
	  }
    }

    public JLabel rTitle = new JLabel("Class routine of: ");
    public void setMainEditorLayout(LRPan btnPan) {
        rTitle.setHorizontalAlignment(SwingConstants.CENTER);
        rTitle.setFont(new Font("DialogInput", 1, 20));

        withTitleGridBtnPan.add(rTitle, BorderLayout.NORTH);
        withTitleGridBtnPan.add(gridPan, BorderLayout.CENTER);

        /* Arrangement for make a gap */
        VerticalFlowLayout vfl = new VerticalFlowLayout(
                VerticalFlowLayout.MIDDLE,
                4, 10, true, true);
        setLayout(vfl);

       JPanel jp = new JPanel(flay);
       jp.add(withTitleGridBtnPan);

        add(jp);
        jp.setBorder(BorderFactory.createEtchedBorder());
        setBorder(BorderFactory.createEtchedBorder());

        jp.setBackground(new Color(238, 238, 238));
        withTitleGridBtnPan.setBackground(new Color(238, 238, 238));
        flay.setVgap(40);
    }

    private void setAllLayout() {
	  gridPan.setBackground(Color.black);
	  gridPan.setBorder(BorderFactory.createLineBorder(Color.black, 2));

	  pc[0] = new EditorCell(this, 0, 0);
	  pc[0].setPeriod("  ### ");
	  gridPan.add(pc[0]);

	  String pName[] = G.getPerName();
	  for(int j = 1; j < pc.length; j++) {
		pc[j] = new EditorCell(this, 0, 0);
		pc[j].setPeriod(pName[j-1]);

		gridPan.add(pc[j]);
	  }

	  for(int i = 0; i < ec.length; i++) {
		dc[i] = new EditorCell(this, 0, 0);
		dc[i].setDay(G.dName[i]);
		gridPan.add(dc[i]);

		for(int j = 0; j < ec[i].length; j++) {
		    ec[i][j] = new EditorCell(this, i, j);
		    gridPan.add(ec[i][j]);
		}
	  }

	  setLayout(flay = new FlowLayout());
          G.p("Setting 10...\n");
	  flay.setVgap(10);

	  add(gridPan);
	  setBorder(BorderFactory.createLoweredBevelBorder());
    }

    /**
     * Most COMPLEX method... desides what action will take place after a CLICK
     * occured on any block(i, j).
     */
    public void clickAction(int i, int j) {
        /* A class exist. */
        if(ec[i][j].isClassExsist()) {
		manPanRef.selectCourseRoom(ec[i][j].rc);
		reloadHilight(ec[i][j].rc.cid);
		reloadSelect(i, j);

            G.mainTips.setText("To move a class to another suitable place first select"+
                               " that class and then drag and drop that class to new place.");
        }

        /* If busy: show detail busy report. */
        else if(ec[i][j].isAnyMoreBusy) {
            G.mainTips.setText("If you want to change the room of a class here, "+
                               "please make change at corresponding manual routine.");

            MyError.report( ec[i][j].getBusyReport() );
        }

        /* FAKA MAT...User want to ASSIGN a class...*/
        else {
            /* Select the course to be recognized by assignAction()*/
            reloadSelect(i, j);

            /* If assign successful. */
            if(manPanRef.assignAction()) {
                /* manPanRef.assignAction() set the routine at main panel. */
                /* so let user show it selectted and */
                /* Hilight their brother. */
                reloadHilight(ec[i][j].rc.cid);

                /* After hilight same type just select current one */
                reloadSelect(i, j);
            }
            else {
                  /* Hilight the last selected course. */
                  reloadHilight(lastHilightCid);
            }
        }
    }

    public void dropAction() {
        RoutineCell src = getSelectedRoutineCell();
        Point dropAt = getDropCell();

        if(src != null && dropAt != null) {
            int di = (int)dropAt.getX();
            int dj = (int)dropAt.getY();

            /* Drop at source */
            if(src.getDay() == di && src.getPeriod() == dj) return;

            Course cRef = src.getCourseRef((Course[])cp.temp, cp.nData);
            int dura = src.getDuration((Course[])cp.temp, cp.nData);

            String cantMoveMsg = "You can not move "+src.cid+" at "+
                                  G.getPerName(dj) + " on "+G.dFullName[di]+".\n"+
                                  "Chose "+(dura == 1? "a single": ""+dura+" consecutive")+
                                  " compltely free period(s) to move successfuly.";

            /* Swap */
            if(ec[di][dj].isClassExsist()) {
                /* For now. */
                MyError.show(cantMoveMsg);
            }

            /* Move */
            else {
                if(free(di, dj, dura)) {
                    RCList rcRef;
                    boolean search = true;

                    /* Do it for Manual and atuomatic both. */
                    for(int times = 0; times < 2 && search; times++) {
                        if(times == 0) rcRef = cRef.manuRCList;
                        else rcRef = cRef.autoRCList;

                        for(int k = 0; k < rcRef.nCell && search; k++)
                            if( rcRef.rCell[k].equal( src ) ) {
                                rcRef.rCell[k].timeStamp = Session.toTimeStamp(di, dj);
                                search = false;

                                manPanRef.reloadRoutine();
                                clickAction(di, dj);
                            }
                    }
                }
                else MyError.show(cantMoveMsg);
            }

        }
        /* */
    }

    public Point getDropCell() {
        for(int i = 0; i < ec.length; i++)
            for(int j = 0; j < ec[i].length; j++)
                if(ec[i][j].isMouseTop || ec[i][j].isMouseBottom)
                    return new Point(i, j);

        return null;
    }

    int xi = -1, xj = -1;
    public void deselectAll() {
	  for(int i = 0; i < ec.length; i++)
		for(int j = 0; j < ec[i].length; j++) {
		    if(ec[i][j].getBackground() == ec[i][j].selectedColor)
			  ec[i][j].setSelected(false);

		    if(ec[i][j].getBackground() == ec[i][j].subSelectedColor)
			  ec[i][j].setSubSelected(false);
		}

	  xi = xj = -1;
    }

    /**
     * Determine whether consecutive (dura) period is free from i, j;
     * @param i-day
     * @param j-period
     * @param dura-duration
     * @return true if free, otherwise false.
     */
    public boolean free(int i, int j, int dura) {
        int k;
        for(k = 0; j + k < ec[0].length && k < dura; k++)
            if(ec[i][j + k].getBackground() != ec[i][j + k].baseColor)
                return false;

        return k == dura? true: false;
    }

    public void reloadSelect(int i, int j) {
	/* Eliminate last select. */
        deselectAll();

        /* User want to SELECT A ASSIGN COURSE...so hilight and subhilight that */
        /* User cant' select a sub assigned cell. */
	if(ec[i][j].assigned) {
	    int n = ec[i][j].rc.getDuration( (Course[])cp.temp, cp.nData );

	    ec[i][j].setSelected(true);
	    for(int k = 1; k < n; k++) {
		ec[i][j + k].setSubSelected(true);
	    }
	}

        /* User want to ASSIGN A CLASS in a FAKA space. */
	else if(manPanRef.getSelectedCourse() != null) {
            /* Lets' see there is enough space */
	    int dura = manPanRef.getSelectedCourse().duration;

            /* If enough space to assign just hilight and subHilight. */
            if(free(i, j, dura)) {
                ec[i][j].setSelected(true);
                for(int k = 1; k < dura; k++)
                    ec[i][j + k].setSubSelected(true);
            }
	}

	  /* Store new selecttion. */
	  xi = i;
	  xj = j;
    }

    String lastHilightCid = null;
    public void reloadHilight(String cid) {
        if(cid != null) {
            setHilight(false, lastHilightCid);
            setHilight(true, cid);
            lastHilightCid = new String(cid);
        }
    }
/*
    private void setHilight(boolean state, String cid) {
	  for(int i = 0; i < ec.length; i++)
		for(int j = 0; j < ec[i].length; j++)

		    if(ec[i][j].assigned && ec[i][j].rc.cid.equalsIgnoreCase( cid )) {
                          int dura = ec[i][j].rc.getDuration((Course[])cp.temp, cp.nData);

                          ec[i][j].setHilight(state);
                          for(int k = 1; k < dura; k++)
                              ec[i][j + k].setSubHilight(state);
		    }
    }
*/

    private void setHilight(boolean state, String cid) {
        for(int i = 0; i < ec.length; i++)
            for(int j = 0; j < ec[i].length; j++)
                if(ec[i][j].isClassExsist() && ec[i][j].rc.cid.equalsIgnoreCase( cid )) {
                      if(ec[i][j].assigned) ec[i][j].setHilight(state);
                      if(ec[i][j].subAssigned) ec[i][j].setSubHilight(state);
                }
    }

    public RoutineCell getSelectedRoutineCell() {
	  int ts = getSelectedTimeStamp();
	  if(ts == -1) return null;

	  int i = Session.getDay(ts);
	  int j = Session.getPeriod(ts);
	  return ec[i][j].getRoutineCell();
    }

    /**
     * Used to get list of cell for delete.
     * @return a list of same cource routine cell.
     */
    public RoutineCell[] getHilighttedRoutineCell() {
	  int nHilight = 0;
	  for(int i = 0; i < ec.length; i++)
		for(int j = 0; j < ec[i].length; j++)
		    if(ec[i][j].getBackground() == ec[i][j].hilightColor)
			  nHilight++;

	  RoutineCell rc[] = new RoutineCell[ nHilight ];
	  nHilight = 0;

	  for(int i = 0; i < ec.length; i++)
		for(int j = 0; j < ec[i].length; j++)
		    if(ec[i][j].getBackground() == ec[i][j].hilightColor)
			  rc[ nHilight++ ] = ec[i][j].rc;
	  return rc;
    }

    private void setBusy(boolean state, String title, String typeTitle, Session s, int busyLabel) {
        for(int i = 0; i < s.s.length; i++)
            for(int j = 0; j < s.s[i].length; j++)
                if(s.s[i][j] == Session.BUSY && !ec[i][j].isClassExsist()) {
                    if(state) ec[i][j].setBusy(title, typeTitle, busyLabel);
                    else ec[i][j].removeBusy(busyLabel);
                }
    }

    public void reloadBusyLabel(String title, String typeTitle, Session s, int busyLabel) {
	  /* Eliminate last level. */
	  if(lastBusylabelSession[ busyLabel ] != null)
		setBusy(false, title, typeTitle, lastBusylabelSession[ busyLabel ], busyLabel);

	  /* NULL value of s will eliminete this lavel busy completely. */
	  if(s != null) setBusy(true, title, typeTitle, s, busyLabel);

	  /* Store history to recover */
	  lastBusylabelSession[ busyLabel ] = (s == null? s: s.copy());
    }

    public int getSelectedTimeStamp() {
	  for(int i = 0; i < ec.length; i++)
		for(int j = 0; j < ec[i].length; j++)
		    if(ec[i][j].getBackground() == ec[i][j].selectedColor)
			  return Session.toTimeStamp(i, j);

	  return -1;
    }

    public RoutineCell[][] getRCGrid() {
        RoutineCell rcg[][] = new RoutineCell[Basic.nDay][Basic.nPeriod];
        for(int i = 0; i < ec.length; i++)
            for(int j= 0; j < ec[i].length; j++)
                rcg[i][j] = ec[i][j].rc;

        return rcg;
    }

   public void printRoutine()
   {
       PrinterJob printJob = PrinterJob.getPrinterJob();
      PageFormat pageFormat = printJob.defaultPage();
      pageFormat.setOrientation(pageFormat.REVERSE_LANDSCAPE);
      printJob.setPrintable(this ,pageFormat);
      if(printJob.printDialog())
      {
        try
        {
          printJob.print();
        }
        catch(PrinterException pe)
        {
          System.err.println(pe);
          MyError.debug("Error Printing.");
        }
      }
   }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex)
         throws PrinterException {

        if(pageIndex>0)
            return NO_SUCH_PAGE;

        Graphics2D g2D = (Graphics2D) g;
        pageFormat.setOrientation(pageFormat.REVERSE_LANDSCAPE);
        g2D.setClip((int)pageFormat.getImageableX(),(int)pageFormat.getImageableY(),
                    (int)pageFormat.getImageableWidth(),(int)pageFormat.getImageableHeight());

        g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        paint(g2D);
        return PAGE_EXISTS;
    }
}