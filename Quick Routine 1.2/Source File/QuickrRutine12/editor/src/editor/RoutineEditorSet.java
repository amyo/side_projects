package editor;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2004
 * Company:
 * @author
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import com.borland.jbcl.layout.*;

import global.*;
import datastructure.*;
import qrinput.*;

public class RoutineEditorSet extends JTabbedPane
{
    RoutineEditor reRef[] = new RoutineEditor[500];

    CoursePan cp;
    //ManPanLayout manPanRef;
    boolean isMainEditor;
    LRPan btnPan;

    public RoutineEditorSet(ManPanLayout manPanRef, CoursePan cp

                            /* Information of mainEditor. */
                            , boolean isMainEditor, LRPan btnPan)
    {
	  final ManPanLayout finalManPanRef = manPanRef;
          final boolean finalIsMainEditor = isMainEditor;
          final LRPan finalBtnPan = btnPan;

	  //this.manPanRef = manPanRef;
	  this.cp = cp;
          this.isMainEditor = isMainEditor;
          this.btnPan = btnPan;

	  setFont(new Font("DialogInput", 0, 12));

	  addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
		    if(closing) return;

		    RoutineEditor selectedPan = getPan( getSelectedIndex() );
		    finalManPanRef.reloadInterface( selectedPan.catIndex, selectedPan.BTRIndex );

                    if(finalIsMainEditor) {
                        selectedPan.withTitleGridBtnPan.add(G.mainTips, BorderLayout.SOUTH);
                        G.mainTips.setText("Select a course...");
                    }
		}
	  });

    }

    public int getSelectedTimeStamp() {
	  int k = getSelectedIndex();
	  if(k == -1) {
	    //MyError.debug("Error in RoutineEditorSet: there is no routine get timeStamp!");
	    return -1;
	  }

	  return getPan(k).getSelectedTimeStamp();
    }

    public void deselectTimeStamp() {
	  int k = getSelectedIndex();

	  if(k == -1) ;//MyError.debug("Error in RoutineEditorSet: there is no routine to deselectTimeStamp!");
	  else getPan(k).deselectAll();
    }

    public RoutineCell getSelectedRoutineCell() {
	  if(getTabCount() == 0) return null;
	  else return getPan( getSelectedIndex() ).getSelectedRoutineCell();
    }

    public RoutineCell[] getHilightedRoutineCell() {
	  if(getTabCount() == 0) return null;
	  else return getPan( getSelectedIndex() ).getHilighttedRoutineCell();
    }

    public void reloadBusyLabel(String title, String typeTitle, Session s, int busyLabel) {
	  int k = getSelectedIndex();
	  if(k == -1);// MyError.debug("Error in RoutineEditorSet: there is no routine to set Busy!");
          else getPan(k).reloadBusyLabel(title, typeTitle, s, busyLabel);
    }

    public void removeAllBusyLabel() {
        for(int k = 0; k < 6; k++)
            reloadBusyLabel(null, null, null, k);
    }

    public void deselectAll() {
          int k = getSelectedIndex();
          if(k == -1) ;//MyError.debug("Error in RoutineEditorSet: there is no routine to deselectAll!");
          else getPan(k).deselectAll();
    }

    public int index(String title) {
	  int n = getTabCount();
	  for(int i = 0; i < n; i++)
		if(getTitleAt(i).equalsIgnoreCase(title))
		    return i;

	  return -1;
    }

    public void addPan(RoutineEditor re, String title) {
	  /*
	  add(sp = new JScrollPane( re ), title);
	  sp.setBorder(BorderFactory.createEmptyBorder());
	  */
	  add(re, title);
        try {
            setIconAt(getTabCount()-1, new ImageIcon(getClass().getResource("icon/tab.GIF")));
        }
        catch (Exception ex) {
            MyError.debug("Cant' Load tab icon.");
        }
    }

    public RoutineEditor getPan(int k) {
	  try {
		if(k >= getTabCount()) return null;
		else return (RoutineEditor)getComponent(k);
	  } catch(Exception e) {
		//MyError.debug("Error: RoutineEditorSet.getPan(int): "+e.getMessage());
		return null;
	  }
    }

    /**
     * Open a new Routine with a new title.
     * If same title exist it just hilight that tab.
     * So dont' use it for reload routine because it keep old RCList for reopen.
     * @param manPanRef
     * @param catIndex
     * @param BTRIndex
     * @param title
     * @param rcl
     */
    public void open(ManPanLayout manPanRef, int catIndex, int BTRIndex, String title, RCList rcl) {
	  int k = index(title);
	  if(k == -1) {
		addPan(new RoutineEditor(manPanRef, cp, catIndex, BTRIndex, rcl, isMainEditor, btnPan)
                       , title);

                getPan(getTabCount() - 1).rTitle.setText("Class routine of "+title);
		setSelectedIndex(getTabCount() - 1);
	  }
	  else setSelectedIndex(k);
    }

    public void reloadRoutine(RCList rcl) {
          if(getTabCount() > 0) getPan( getSelectedIndex() ).reloadRCList(rcl, cp);
    }

    public void printRoutine() {
          if(getTabCount() > 0) getPan( getSelectedIndex() ).printRoutine();
    }

    public RoutineCell[][] getRCG() {
          if(getTabCount() > 0) return getPan( getSelectedIndex() ).getRCGrid();
          else return null;
    }

    /**
     * Used by Main Editor.
     * @return
     */
    public Point getSelectedRoutineIndex() {
        if(getTabCount() == 0) {
            // MyError.debug("RoutineEditorSet.getSelectedRoutineIndex(): 0.");
            return null;
        }
        else {
            RoutineEditor re = getPan( getSelectedIndex() );
            return new Point(re.catIndex, re.BTRIndex);
        }
    }

    public void reloadHightLight(String cid) {
	  if(getTabCount() > 0) getPan( getSelectedIndex() ).reloadHilight(cid);
    }

    public Image getRoutineImage() {
        if(getTabCount() > 0) return getPan( getSelectedIndex() ).getRoutineImage();
        else {
           // MyError.debug("There is no routine to return Image.");
            return null;
        }
    }

    // Bangla.
    public boolean closing;

    public void close() {
	  closing = true;
	  if( getTabCount() > 0 )
		remove(getSelectedIndex());
          else
                System.exit(0);
	  closing = false;
    }

    public void closeCat(int catIndex) {
	  int n = getTabCount();
	  closing = true;

	  for(int i = 0; i < n; i++) {
		RoutineEditor REPan = getPan(i);
		    if(REPan.catIndex == catIndex) {
			  remove(i);

			  // Start over...
			  n = getTabCount();
			  i = -1;
		    }
	  }
	  closing = false;
    }

    public void closeAll() {
	  closing = true;
	  while( getTabCount() > 0 )
		remove(0);
	  closing = false;
    }
}