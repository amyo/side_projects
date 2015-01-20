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
import java.awt.*;
import java.awt.event.*;

import datastructure.*;
import global.*;
import qrinput.CoursePan;

public class EditorCell extends JPanel
{
    RoutineCell rc;

    String emptyStr = "         ";
    JLabel topLabel = new JLabel(emptyStr);
    JLabel bottomLabel = new JLabel(emptyStr);

    public boolean assigned = false, subAssigned = false;
    public boolean isAnyMoreBusy = false;
    private boolean isBusyLabel[] = {false, false, false, false, false, false};

    private String busyLabelTitle[] = new String[ isBusyLabel.length ];
    private String busyLabelTypeTitle[] = new String[ isBusyLabel.length ];

    public boolean isMouseTop = false, isMouseBottom = false;

    Color
    periodColor = new Color(65, 131, 131),
    dayColor = new Color(150, 203, 203),

    assignColor = new Color(192, 192, 192),
    subAssignColor = new Color(226, 226, 226),

    selectedColor = new Color(0, 73, 108),
    subSelectedColor =  new Color(58, 110, 165),

    hilightColor = new Color(64, 128, 128),
    subHilightColor = new Color(84, 169, 169),

    baseColor = new Color(240, 240, 240),
    busyColor[] = {
        new Color(230, 240, 206),
        new Color(205, 227, 159),
        new Color(169, 207, 88),
        new Color(189, 236, 192),
        new Color(197, 222, 143),
        new Color(169, 207, 88)
    };

    RoutineEditor gridPan;
    int i, j;
    Dimension screenSize;

    public EditorCell(RoutineEditor gridPan, int i, int j)
    {
	  this.gridPan = gridPan;
	  this.i = i;
	  this.j = j;

	  setLayout(new BorderLayout());
	  add(topLabel, BorderLayout.NORTH);
	  add(bottomLabel, BorderLayout.SOUTH);

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        if(screenSize.getWidth()<G.mWidth)
        {
            topLabel.setFont(new Font("DialogInput", 0,10 ));
            bottomLabel.setFont(new Font("DialogInput", 0,10 ));
        }
        else
        {
            topLabel.setFont(new Font("DialogInput", 0, 12));
            bottomLabel.setFont(new Font("DialogInput", 0, 12));
        }
	  topLabel.setHorizontalAlignment(SwingConstants.CENTER);
	  bottomLabel.setHorizontalAlignment(SwingConstants.CENTER);

	  setBackground(baseColor);

	  // Action Listeners are implemented by Design.
	  try {
		jbInit();
	  }
	  catch(Exception e) {
		e.printStackTrace();
	  }
    }

    public void makeTittle() {
	removeAll();
	setLayout(new FlowLayout());
        add(topLabel);
        setFontBlack();

        if(screenSize.getWidth()<G.mWidth)
          topLabel.setFont(new Font("DialogInput", Font.BOLD, 13));
        else topLabel.setFont(new Font("DialogInput", Font.BOLD, 15));

        isAnyMoreBusy = true;
    }

    public void setPeriod(String s) {
	  makeTittle();
	  topLabel.setText(s);
	  setBackground(periodColor);
    }

    public void setDay(String s) {
	  makeTittle();
	  topLabel.setText(s);
	  setBackground(dayColor);
    }

    public RoutineCell getRoutineCell() {
	  if(assigned) return rc;
	  else return null;
    }

    public boolean isClassExsist() {
        return assigned || subAssigned;
    }

    public int howMuchBusy() {
	  int x = -1;
	  for(int i = 0; i < isBusyLabel.length; i++)
		if(isBusyLabel[i])
		    x++;

	  return x;
    }

    public String getBusyReport() {
        String s = "";
        for(int i = 0; i < isBusyLabel.length; i++)
            if(isBusyLabel[i])
                s += (busyLabelTitle[i] + " is " + busyLabelTypeTitle[i] + "\n");

        return s;
    }

    public void setBusy(String title, String typeTitle, int busyLabel) {
	  isBusyLabel[ busyLabel ] = true;
	  busyLabelTitle[ busyLabel ] = new String(title);
	  busyLabelTypeTitle[ busyLabel ] = new String(typeTitle);

	  if(!assigned) {
		topLabel.setText(title);
		bottomLabel.setText(typeTitle);

                setFontBlack();
		setBackground(busyColor[ howMuchBusy() ]);
	  }

	  isAnyMoreBusy = true;
    }

    public void removeBusy(int busyLabel) {
	  isBusyLabel[ busyLabel ] = false;

	  // Is any more busy?
	  for(int i = isBusyLabel.length - 1; i >= 0; i--)
		if(isBusyLabel[i]) {
		    setBusy(busyLabelTitle[i], busyLabelTypeTitle[i], i);
		    return;
		}

	  // Not any more busy...
	  if(!assigned) {
		topLabel.setText(emptyStr);
		bottomLabel.setText(emptyStr);
		setBackground(baseColor);
	  }
	  isAnyMoreBusy = false;
    }

    public void setFontBlack() {
        topLabel.setForeground(Color.black);
        bottomLabel.setForeground(Color.black);
    }

    public void setRoutineCell(RoutineCell rc, CoursePan cp) {
        topLabel.setText("" + rc.cid +"");
        bottomLabel.setText("" + rc.getTID((Course[])cp.temp, cp.nData) + "/" + rc.rid + "");
        setFontBlack();

        this.rc = rc.copy();
        assigned = true;
        setBackground(assignColor);
    }

    public void setSubRoutineCell(RoutineCell rc, CoursePan cp) {
	  this.rc = rc.copy();

	  topLabel.setText("" + rc.cid +"");
	  bottomLabel.setText("" + rc.getTID((Course[])cp.temp, cp.nData) + "/" + rc.rid+ "");
          setFontBlack();

	  subAssigned = true;
	  setBackground(subAssignColor);
    }

    public void removeRoutineCell() {
	  rc = null;
	  assigned = false;
          subAssigned = false;

	  topLabel.setText(emptyStr);
	  bottomLabel.setText(emptyStr);
	  setBackground(baseColor);
    }

    public void setSelected(boolean state) {
	if(assigned || (!isAnyMoreBusy) ) {
	    /* Backgraund */
            setBackground(state? selectedColor: assigned? assignColor: baseColor);

            /* Font color*/
            if(state) {
                topLabel.setForeground(Color.white);
                bottomLabel.setForeground(Color.white);
            }
            else {
                topLabel.setForeground(Color.black);
                bottomLabel.setForeground(Color.black);
            }

	}
    }

    public void setSubSelected(boolean state) {
	if(assigned || (!isAnyMoreBusy) ) {
	    setBackground(state? subSelectedColor: subAssigned? subAssignColor: baseColor);

            if(state) {
                topLabel.setForeground(Color.white);
                bottomLabel.setForeground(Color.white);
            }
            else {
                topLabel.setForeground(Color.black);
                bottomLabel.setForeground(Color.black);
            }

	}
    }

    public void setHilight(boolean state) {
        if(rc == null) MyError.debug("A empty cell cant' hilight!");
        else setBackground(state? hilightColor: assignColor);

            if(state) {
                topLabel.setForeground(Color.white);
                bottomLabel.setForeground(Color.white);
            }
            else {
                topLabel.setForeground(Color.black);
                bottomLabel.setForeground(Color.black);
            }

    }

    public void setSubHilight(boolean state) {
        if(rc == null) MyError.debug("A empty cell cant' subHilight!");
        else setBackground(state? subHilightColor: subAssignColor);

            if(state) {
                topLabel.setForeground(Color.white);
                bottomLabel.setForeground(Color.white);
            }
            else {
                topLabel.setForeground(Color.black);
                bottomLabel.setForeground(Color.black);
            }

    }

    private void jbInit() throws Exception {
	  bottomLabel.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    bottomLabel_mousePressed(e);
		}
            public void mouseEntered(MouseEvent e) {
                isMouseBottom = true;
            }
            public void mouseExited(MouseEvent e) {
                isMouseBottom = false;
            }
            public void mouseReleased(MouseEvent e) {
                bottomLabel_mouseReleased(e);
            }
	  });
	  topLabel.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    topLabel_mousePressed(e);
		}
            public void mouseEntered(MouseEvent e) {
                isMouseTop = true;
            }
            public void mouseExited(MouseEvent e) {
                isMouseTop = false;
            }
            public void mouseReleased(MouseEvent e) {
                topLabel_mouseReleased(e);
            }
	  });
	  this.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mousePressed(MouseEvent e) {
		    this_mousePressed(e);
		}
	  });
    }

    void this_mousePressed(MouseEvent e) {
	 gridPan.clickAction(i, j);
    }

    void topLabel_mousePressed(MouseEvent e) {
	  gridPan.clickAction(i, j);
    }

    void bottomLabel_mousePressed(MouseEvent e) {
	  gridPan.clickAction(i, j);
    }

    void topLabel_mouseReleased(MouseEvent e) {
        gridPan.dropAction();
    }

    void bottomLabel_mouseReleased(MouseEvent e) {
        gridPan.dropAction();
    }
}