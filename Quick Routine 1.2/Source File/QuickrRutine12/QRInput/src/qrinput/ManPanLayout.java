package qrinput;

/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;

import editor.*;
import java.awt.event.*;
import java.util.*;

import global.*;
import datastructure.*;
import help.*;

public class ManPanLayout extends JPanel {
    JPanel downPan = new JPanel();
    JPanel topPan = new JPanel();
    JPanel rightPan = new JPanel();
    JPanel midPan = new JPanel();
    JPanel leftPan = new JPanel();
    String s[] = {"Simanto", "Amyo Kabirmmmmmmmmm", "Moly", "Joly", "Shell", "BIloi", "sadd"};
    TitledBorder titledBorder1;
    TitledBorder titledBorder2;
    TitledBorder titledBorder3;
    TitledBorder roomTitle;
    TitledBorder titledBorder5;
    TitledBorder titledBorder6;
    JPanel jPanel2 = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JComboBox BTCom = new JComboBox();
    JLabel topLab = new JLabel();
    FlowLayout flowLayout2 = new FlowLayout();
    Border border1;
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel btnPan = new JPanel();
    JPanel upBtnPan = new JPanel();
    JPanel downBtnPan = new JPanel();
    VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
    VerticalFlowLayout verticalFlowLayout3 = new VerticalFlowLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    JButton remove = new JButton();
    JLabel tipsLabel = new JLabel();
    JButton close = new JButton();
    JLabel xxxxxxxxxxxxxxx = new JLabel();
    JButton removeAll = new JButton();
    JButton closeAll = new JButton();
    JButton help = new JButton();

    String catStr[] = {"Batch", "Teacher"};
    public JComboBox catCom = new JComboBox(catStr);
    JLabel catLab = new JLabel();

    RoutineEditorSet res;

    /**
     * Preveous panels ref to access those temp data.
     */
    RoomPan rp;
    BatchPan bp;
    TeacherPan tp;
    CoursePan cp;

    public boolean saved = true;

    GridLayout gridLayout1 = new GridLayout();
    JPanel dumyPan = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JList courseList = new JList();
    JPanel jPanel1 = new JPanel();
    JScrollPane jScrollPane2 = new JScrollPane();
    JList roomList = new JList();

    public ManPanLayout(  RoomPan rp
				, BatchPan bp
				, TeacherPan tp
				, CoursePan cp
				) {
	  this.rp = rp;
	  this.bp = bp;
	  this.tp = tp;
	  this.cp = cp;
    }

    public ManPanLayout() {
      try {
//        jbInit();
      }
      catch (Exception ex) {
      }
    }

    public void callJBInit(int catIndex) {
	  res = new RoutineEditorSet(this, cp, false, null);

	  try {
		jbInit(catIndex);
	  }
          catch(Exception e) {
		e.printStackTrace();
          }
    }

//    private void jbInit() throws Exception {
    private void jbInit(int catIndex) throws Exception {
	  titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(new Color(255, 250, 250),new Color(178, 122, 122)), "Select routine");
	  titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)), "Select Course");
	  titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.lightGray,Color.lightGray), "Course Report");
	  roomTitle = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)), "Select Room");
	  titledBorder5 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)), "Select Routine");
	  titledBorder6 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)), "Select Day and Period");
	  border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178));
	  this.setLayout(borderLayout5);
	  //downPan.setBackground(SystemColor.menu);
	  // downPan.setPreferredSize(new Dimension(804, 266));
	  downPan.setLayout(borderLayout1);
	  topPan.setLayout(gridLayout1);
	  rightPan.setBorder(roomTitle);
	  rightPan.setLayout(borderLayout4);
	  leftPan.setBorder(titledBorder5);
	  leftPan.setLayout(flowLayout2);
	  midPan.setBorder(titledBorder2);
	  midPan.setLayout(borderLayout3);
	  jPanel2.setLayout(xYLayout2);
	  topLab.setText("Catagory:");
	  catLab.setText("###:");
	  btnPan.setLayout(borderLayout2);
	  downBtnPan.setLayout(verticalFlowLayout2);
	  upBtnPan.setLayout(verticalFlowLayout3);
	  remove.setText("Remove");
	  remove.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    remove_actionPerformed(e);
		}
	  });
	  //tipsLabel.setFont(new java.awt.Font("DialogInput", 0, 13));
	  tipsLabel.setText("Do it hurry honey, this way...");
	  close.setText("Close");
	  close.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    close_actionPerformed(e);
		}
	  });
	  xxxxxxxxxxxxxxx.setDisplayedMnemonic('0');
	  xxxxxxxxxxxxxxx.setHorizontalAlignment(SwingConstants.CENTER);
	  xxxxxxxxxxxxxxx.setText("   ");
	  //catCom.setFont(new java.awt.Font("DialogInput", 0, 12));
//	  catCom.setSelectedIndex(catIndex);
	  catCom.addItemListener(new java.awt.event.ItemListener() {
		public void itemStateChanged(ItemEvent e) {
		    catCom_itemStateChanged(e);
		}
	  });
	 // BTCom.setFont(new java.awt.Font("DialogInput", 0, 12));
	  BTCom.addItemListener(new java.awt.event.ItemListener() {
		public void itemStateChanged(ItemEvent e) {
		    BTCom_itemStateChanged(e);
		}
	  });
	//  topPan.setPreferredSize(new Dimension(804, 140));
	  dumyPan.setLayout(borderLayout6);
	//  jScrollPane1.setBorder(BorderFactory.createLineBorder(Color.black));
	 // courseList.setFont(new java.awt.Font("DialogInput", 0, 12));
	  courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    courseList.setVisibleRowCount(5);
	  jPanel1.setLayout(borderLayout7);
	 // jPanel1.setBackground(UIManager.getColor("menu"));
	 // jScrollPane2.setBorder(BorderFactory.createLineBorder(Color.black));
	 // roomList.setFont(new java.awt.Font("DialogInput", 0, 12));
	  roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    roomList.setVisibleRowCount(5);
	  removeAll.setText("Remove All");
	  removeAll.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    removeAll_actionPerformed(e);
		}
	  });
	  closeAll.setText("Report");
	  closeAll.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    closeAll_actionPerformed(e);
		}
	  });
	  help.setText("Help");
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HelpFrame(HelpFrame.MANUAL);
            }
        });
    gettis.setText("  ");
    this.add(topPan,  BorderLayout.NORTH);
	  topPan.add(leftPan, null);
	  leftPan.add(jPanel2, null);
	  topPan.add(midPan, null);

	  jPanel2.add(topLab, new XYConstraints(0, 13, -1, -1));
	  jPanel2.add(catLab, new XYConstraints(0, 62, -1, -1));

	  jPanel2.add(catCom,          new XYConstraints(67, 12, 131, -1));
	  jPanel2.add(BTCom,           new XYConstraints(68, 62, 128, -1));
    jPanel2.add(gettis, new XYConstraints(1, 88, -1, -1));
	  topPan.add(rightPan, null);
	  rightPan.add(jPanel1, BorderLayout.CENTER);
	  jPanel1.add(jScrollPane2, BorderLayout.CENTER);
    jPanel1.add(jPanel4,  BorderLayout.SOUTH);
	  jScrollPane2.getViewport().add(roomList, null);
	  this.add(downPan,  BorderLayout.CENTER);
	  downPan.add(btnPan, BorderLayout.EAST);
	  upBtnPan.add(xxxxxxxxxxxxxxx, null);
	  upBtnPan.add(remove, null);
	  upBtnPan.add(removeAll, null);
	  upBtnPan.add(close, null);
	  downPan.add(res, BorderLayout.CENTER);

	  // this.add(tipsLabel, null);  // TIPS.

	  btnPan.add(downBtnPan, BorderLayout.SOUTH);
	  btnPan.add(upBtnPan, BorderLayout.NORTH);
	  midPan.add(dumyPan, BorderLayout.CENTER);
	  dumyPan.add(jScrollPane1, BorderLayout.CENTER);
    dumyPan.add(jPanel3,  BorderLayout.SOUTH);
	  jScrollPane1.getViewport().add(courseList, null);
	  upBtnPan.add(closeAll, null);
	  downBtnPan.add(help, null);

	  courseList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
		    courseList_valueChanged(e);
		}
	  });
	  roomList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
		    roomList_valueChanged(e);
		}
	  });

        reloadBTCom();
	  courseList.setListData(new Vector());
	  G.setTips(getTips());
    }

    public final int BT_BUSY_LEVEL = 0;
    public final int COURSE_BT_BUSY_LEVEL = 1;
    public final int ROOM_BUSY_LEVEL = 2;
    public final int COURSE_ASSIGNED_BT_BUSY_LEVEL = 3;
    public final int ROOM_ASSIGNED_BUSY_LEVEL = 4;

    public Teacher getSelectedTeacher() {
	  if(catCom.getSelectedIndex() != 1 || BTCom.getSelectedIndex() == -1) return null;
	  else return (Teacher)tp.temp[ BTCom.getSelectedIndex() ];
    }
    public Batch getSelectedBatch() {
	  if(catCom.getSelectedIndex() != 0 || BTCom.getSelectedIndex() == -1) return null;
	  else return (Batch)bp.temp[ BTCom.getSelectedIndex() ];
    }
    public Course getSelectedCourse() {
	  if(courseList.getSelectedIndex() == -1) return null;
	  return (Course)cp.temp[ indexLoadCourse[ courseList.getSelectedIndex() ] ];
    }
    public Room getSelectedRoom() {
	  if(roomList.getSelectedIndex() == -1) return null;
	  else return (Room)rp.temp[ indexLoadRoom[ roomList.getSelectedIndex() ] ];
    }
    public Teacher getSelectedCourseTeacher() {
	  if(courseList.getSelectedIndex() == -1) return null;
	  else if(getSelectedCourse().tid == null) return null;
	  else return (Teacher)tp.temp[ Teacher.index(getSelectedCourse().tid, tp.temp, tp.nData) ];
    }
    public Batch getSelectedCourseBatch() {
	  if(courseList.getSelectedIndex() == -1) return null;
	  else return (Batch)bp.temp[ Batch.index(getSelectedCourse().batchHashIndex, bp.temp, bp.nData)];
    }

    /**
     * Reload BATCH list.
     * @see relaodBTCom()
     */
    public void reloadBatch() {
	  BTCom.removeAllItems();
	  for(int i = 0; i < bp.nData; i++) {
		Batch bRef = (Batch)bp.temp[i];
		BTCom.addItem( bRef.getNickName() );
	  }
    }

    /**
     * Reload TEACHER list.
     * @see relaodBTCom()
     */
    public void reloadTeacher() {
	  BTCom.removeAllItems();
	  for(int i = 0; i < tp.nData; i++) {
		Teacher tRef = (Teacher)tp.temp[i];
		BTCom.addItem( tRef.id + ", " + tRef.getFullName() );
	  }
    }

    /**
     * Reload BATCH TEACHER List according to selected index of castagory combobox.
     * @see reloadBatch()
     * @see reloadTeacher()
     */
    public void reloadBTCom() {
	  int k = catCom.getSelectedIndex();

	  routineOpenForbitten = true;

	  if(k == 0) {
		catLab.setText("Batch(s):");
		reloadBatch();
	  }
	  else if(k == 1) {
		catLab.setText("Teacher(s):");
		reloadTeacher();
	  }

	  BTCom.setSelectedIndex(-1);
	  routineOpenForbitten = false;
    }

    public int nLoadCourse, indexLoadCourse[] = new int[G.MAXC];
    /**
     * Reload a selected teachers' COURSE.
     * @param a teacher id whose course have to be loaded.
     */
    public void reloadTeacherCourse(String tid) {
	  Vector v = new Vector();
	  nLoadCourse = 0;

	  for(int i = 0; i < cp.nData; i++) {
		Course cRef = (Course)cp.temp[i];
		if(tid.equalsIgnoreCase( cRef.tid ) ) {
		    v.add(cRef.getNickName() + ", " + Batch.getNickName( cRef.batchHashIndex ));
		    indexLoadCourse[ nLoadCourse++ ] = i;
		}
	  }

	  courseList.setListData(v);
    }

    /**
     * Reload a selectted BATCHS' COURSE.
     * @param a bachs' hash index which courses' have to be loaded.
     */
    public void reloadBatchCourse(int bhi) {
	  Vector v = new Vector();
	  nLoadCourse = 0;

	  for(int i = 0; i < cp.nData; i++) {
		Course cRef = (Course)cp.temp[i];
		if(bhi == cRef.batchHashIndex) {
		    v.add(cRef.getNickName() + ", " + cRef.tid);
		    indexLoadCourse[ nLoadCourse++ ] = i;
		}
	  }

	  courseList.setListData(v);
    }

    /**
     * Reload course list according to catagory combobox.
     * @see reloadBatchCourse(int)
     * @see reloadTeacherCourse(String)
     */
    public void reloadCourse() {
	  int i = catCom.getSelectedIndex();
	  int j = BTCom.getSelectedIndex();

	  if(i != -1 && j != -1) {
		if(i == 0) reloadBatchCourse( ( (Batch)bp.temp[j]).hashIndex );
		else if(i == 1) reloadTeacherCourse( ((Teacher)tp.temp[j]).id );

		reloadTypeRoom();
	  }
    }

    public int nLoadRoom, indexLoadRoom[] = new int[G.MAXR];

    /**
     * Reload room for a selected course.
     * For theory course only theory room will be loaded
     * For lab course only lab room will be loaded
     * If room is fixed for the selected course only those room will be shown
     * to room list.
     */
    public void reloadTypeRoom() {
	  Course cRef = getSelectedCourse();
	  if(cRef == null) return;

	  Vector v = new Vector();
	  nLoadRoom = 0;

	  // Room is NOT FIXED for this course...
	  if( cRef.rid == null ) {

		for(int i = 0; i < rp.nData; i++) {
		    Room rRef = (Room)rp.temp[i];
                    {
			  v.add(""+rRef.getNickName(cp.temp, cp.nData));
			  indexLoadRoom[ nLoadRoom++ ] = i;
		    }
		}

		//rightPan.setBorder(BorderFactory.createTitledBorder("Select a room for " + cRef.id ));
                roomTitle.setTitle("Select a room for " + cRef.id);
	  }

	  // Room is FIXED so show all same type room.
	  else {
		String rid = new String( cRef.rid );

		for(int i = 0; i < rp.nData; i++) {
		    Room rRef = (Room)rp.temp[i];

		    if(rid.equalsIgnoreCase( rRef.id )) {
			  v.add(""+rRef.getNickName(cp.temp, cp.nData));
			  indexLoadRoom[ nLoadRoom++ ] = i;
		    }
		}
                roomList.setSelectedIndex(0);

		rightPan.setBorder(BorderFactory.createTitledBorder(
		    "Folowing room is FIXED for "+ cRef.id ));
	  }

	  roomList.setListData(v);
    }

    /**
     * Message for user when click ASSIGN button without selectting proper data.
     * @return a error masage or null when there is no error.
     */
    public String errorInterface() {
	  int k;
	  String s = "Please select a ";

	  if((k = catCom.getSelectedIndex()) == -1) return s + "catagory (Batch or Techaer).";
	  if(BTCom.getSelectedIndex() == -1)        return s + (k == 0? "Batch.": "Teacher.");
	  if(courseList.getSelectedIndex() == -1)   return s + "course.";
	  if(roomList.getSelectedIndex() == -1)     return s + "room.";

          Course cRef = getSelectedCourse();
	  if(res.getSelectedTimeStamp() == -1)      return s +
       cRef.duration + (cRef.duration > 1? " consecutive": "") +
       " period(s) of a day as required for "+cRef.id;

	  return null;
    }

    /**
     * Colect data from interface.
     * @return a RoutineCell class.
     */
    public RoutineCell readInterface() {
        if(res.getSelectedTimeStamp() == -1) {
            MyError.debug("MyPanLayOut.reloadInterface: ts -1");
            return null;
        }
	else return new RoutineCell(
		res.getSelectedTimeStamp(),
		getSelectedCourse().id,
		getSelectedRoom().id
	  );
    }

    /**
     * Variable use for forbidden routine open.
     * Cause actionlistener try to open routine so frequently.
     */
    private boolean routineOpenForbitten;
  private BorderLayout borderLayout3 = new BorderLayout();
  private BorderLayout borderLayout4 = new BorderLayout();
  private BorderLayout borderLayout5 = new BorderLayout();
  private BorderLayout borderLayout6 = new BorderLayout();
  private JPanel jPanel3 = new JPanel();
  private BorderLayout borderLayout7 = new BorderLayout();
  private JPanel jPanel4 = new JPanel();
  private JLabel gettis = new JLabel();
    public void openRoutine() {
	  if(routineOpenForbitten) return;

	  // Open BATCHS' routine...
	  if(getSelectedBatch() != null) {
		res.open(
		    this,

		    catCom.getSelectedIndex(),
		    BTCom.getSelectedIndex(),

		    "Batch: " + getSelectedBatch().getNickName(),
		    getSelectedBatch().getManualRCList( (Course[])cp.temp, cp.nData)
		);

		res.reloadBusyLabel("Batch", "Busy", new Session( getSelectedBatch().s ), BT_BUSY_LEVEL);
	  }

	  // Open TEACHERS' routine...
	  else if(getSelectedTeacher() != null) {
		res.open(
		    this,

		    catCom.getSelectedIndex(),
		    BTCom.getSelectedIndex(),

		    "Teacher: " + getSelectedTeacher().id,
		    getSelectedTeacher().getManualRCList( (Course[])cp.temp, cp.nData)
		);
		res.reloadBusyLabel("Teacher", "Busy", new Session( getSelectedTeacher().s ), BT_BUSY_LEVEL);
	  }
    }

    public void reloadRoutine() {
	  if(catCom.getSelectedIndex() == 0)
		res.reloadRoutine(getSelectedBatch().getManualRCList((Course[])cp.temp, cp.nData));

	  if(catCom.getSelectedIndex() == 1)
		res.reloadRoutine(getSelectedTeacher().getManualRCList((Course[])cp.temp, cp.nData));
    }

    /**
     * Changed busy state when click to course list.
     * If catagory is BATCH: Course teacher busy state have set.
     * If catagory is TEACHER: Courses' batch busy state have set.
     */
    public boolean reloadCourseBusyState() {
	  res.deselectTimeStamp();

	  int k = catCom.getSelectedIndex();
	  Course cRef = getSelectedCourse();

	  if(cRef == null) {
		res.reloadBusyLabel(null, null, null, COURSE_BT_BUSY_LEVEL);
		res.reloadBusyLabel(null, null, null, COURSE_ASSIGNED_BT_BUSY_LEVEL);
		return false;
	  }

	  // Catagory is BATCH.
	  if(k == 0) {
		if(cRef.tid == null) {
		    MyError.show("Course teacher for "+cRef.id+" have not assigned yet.\n"+
				    "Befor assigning "+cRef.id+" manually in routine "+
				    "you must assign a course teacher for "+cRef.id+".");

		    reloadCourse();
		    return false;
		}

		res.reloadBusyLabel("Teacher", "Busy", new Session( getSelectedCourseTeacher().s ), COURSE_BT_BUSY_LEVEL);

		res.reloadBusyLabel("Teacher", "Assigned",
                                    getSelectedCourseTeacher().getManualBusySession((Course[])cp.temp, cp.nData),
                                    COURSE_ASSIGNED_BT_BUSY_LEVEL);
	  }
	  // Catagory is TEACHER.
	  else if(k == 1) {
              res.reloadBusyLabel("Batch", "Busy", new Session( getSelectedCourseBatch().s ),
                  COURSE_BT_BUSY_LEVEL);

              res.reloadBusyLabel("Batch", "Assigned",
                                  getSelectedCourseBatch().getManualBusySession((Course[])cp.temp, cp.nData) ,
                                  COURSE_ASSIGNED_BT_BUSY_LEVEL);
	  }

	  return true;
    }

    public void relaodRoomBusyState() {
	  if(getSelectedRoom() == null) {
              res.reloadBusyLabel(null, null, null, ROOM_BUSY_LEVEL);
              res.reloadBusyLabel(null, null, null, ROOM_ASSIGNED_BUSY_LEVEL);
	  }
	  else {
              res.reloadBusyLabel("Room", "Busy", new Session( getSelectedRoom().s ), ROOM_BUSY_LEVEL);
              res.reloadBusyLabel("Room", "Assigned",
                                  getSelectedRoom().getManualBusySession((Course[])cp.temp, cp.nData) ,
                                  ROOM_ASSIGNED_BUSY_LEVEL);
	  }
    }

    public void sessionClickAction(RoutineCell rc) {
	  if(rc == null) assignAction();
	  else {
		// Select COURSE...
		for(int i = 0; i < nLoadCourse; i++) {
		    int k = indexLoadCourse[i];

		    if( ((Course )cp.temp[k]).id.equalsIgnoreCase( rc.cid ) ) {
			  courseList.setSelectedIndex(i);
			  break;
		    }
		}

		// Select ROOM...
		for(int i = 0; i < nLoadRoom; i++) {
		    int k = indexLoadRoom[i];

		    if( ((Room)rp.temp[k]).id.equalsIgnoreCase( rc.rid ) ) {
			  roomList.setSelectedIndex(i);
			  break;
		    }
		}
	  }
    }

    public void selectCourseRoom(RoutineCell rc) {
		if(rc == null) {
		    MyError.debug("reloadInterface(RoutineCell rc): rc null");
		    return;
		}

		// Select COURSE...
		for(int i = 0; i < nLoadCourse; i++) {
		    int k = indexLoadCourse[i];

		    if( ((Course )cp.temp[k]).id.equalsIgnoreCase( rc.cid ) ) {
			  courseList.setSelectedIndex(i);
			  break;
		    }
		}

		// Select ROOM...
		for(int i = 0; i < nLoadRoom; i++) {
		    int k = indexLoadRoom[i];

		    if( ((Room)rp.temp[k]).id.equalsIgnoreCase( rc.rid ) ) {
			  roomList.setSelectedIndex(i);
			  break;
		    }
		}
    }

    public boolean assignAction() {

        /* Ensures that a course and a room is selected. */
        if(errorInterface() == null) {

              /* Making routine. */
            if( getSelectedCourse().manuRCList.add( readInterface() ) == false) {
                  MyError.show("All class(s) of "+getSelectedCourse().id+" have assigned.\n"+
                               "Please select another course or select this course class to remove.\n"+
                               "You also can drag and drop a course to a new suitable place.");

                  /* Assigned faild cause all classes have assigned. */
                  return false;
            }

            /* Refresh new routine. */
            reloadRoutine();

            /* Ask for save. */
            saved = false;

            /* Assign success. */
            return true;
	  }
	  else {
              /* Lack of available information to assign a class. */
              MyError.show(errorInterface());

              /* Assign faild. */
              return false;
	  }
    }

    /**
     * Reload whole interface for refreshing data.
     * @see reloadBTCom()
     * @see reloadCourse()
     * @see reloadTypeRoom()
     */
    public void reloadInterface(int catIndex, int BTInedx) {
	  if(catIndex == -1 || BTInedx == -1) return;

	  catCom.setSelectedIndex(catIndex);
	  reloadBTCom();
	  BTCom.setSelectedIndex(BTInedx);

	  courseList.setSelectedIndex(-1);
	  reloadCourseBusyState();

	  roomList.setListData(new Vector());
	  relaodRoomBusyState();

	  reloadRoutine();
    }

    public void reloadInterface() {
	  reloadInterface(catCom.getSelectedIndex(), BTCom.getSelectedIndex());
    }

    public String getTips() {
	if(catCom.getSelectedIndex() == -1)
	   return "Please select a catagory from up-left comboBox first.";

	String cat = catCom.getSelectedIndex() == 0? "batch": "teacher";

	if(BTCom.getSelectedIndex() == -1)
	  return "Please select a "+cat+" from "+cat+" list below.";

	if(courseList.getSelectedIndex() == -1) {
	  if(nLoadCourse > 0) return "Please select a course from top-middle list to assign a class.";
	  else return "No course have assigned for this "+cat+
			 ", please assign a course before to make routine of this "+cat+".";
	}

	String assignedFinishedMsg = "All class(s) of this course have assigned, "+
	"you can select a class of this course. To remove that class press 'Remove' button at right\n";
        //+"or you can drag and drop this course to a new suitable day and priod.";


	if(nLoadRoom == 0) return "There is no theory room available, please insert room before.";

	Course cRef = getSelectedCourse();
	int x = cRef.manuRCList.nClassLeft();

	if(x == 0) return assignedFinishedMsg;
	if(x > 0 && roomList.getSelectedIndex() == -1)
	  return "This course have "+x+" class(s) left to assign out of "+cRef.nClass+" class(s), "+
	  "each class duration is "+cRef.duration+" hour(s). "+
			   "Please select a free room before assignig a class.";


	if(x == 0) return assignedFinishedMsg;

	if(res.getSelectedTimeStamp() == -1)
	  return "Now select "+(cRef.duration == 1? "a single": ""+cRef.duration+"consecutive")+
	  " free block(s) of routine to assign a class of"+
	  " selected course in selected room.";

	return "Need more tips?";
    }
    /**
     * ACTION LISTENER.
     */
    void catCom_itemStateChanged(ItemEvent e) {
	  reloadBTCom();
	  courseList.setListData(new Vector());
	  G.setTips(getTips());
    }

    void BTCom_itemStateChanged(ItemEvent e) {
	  reloadCourse();
	  openRoutine();
	  G.setTips(getTips());
    }

    void courseList_valueChanged(ListSelectionEvent e) {
	  if(reloadCourseBusyState()) {
		reloadTypeRoom();
		res.reloadHightLight( getSelectedCourse().id );
		G.setTips(getTips());
	  }
	  else roomList.setListData(new Vector());
    }

    void roomList_valueChanged(ListSelectionEvent e) {
	  relaodRoomBusyState();
	  G.setTips(getTips());
    }

    void close_actionPerformed(ActionEvent e) {
	  res.close();
	  if(res.getTabCount() == 0) {
		catCom.setSelectedIndex(-1);
		BTCom.removeAllItems();
	  }
    }

    void closeAll_actionPerformed(ActionEvent e) {
        /*
	  res.closeAll();
	  catCom.setSelectedIndex(-1);
	  BTCom.removeAllItems();
        */
        new report.ReportDialog(G.mfRef, "ManualRoutineReport", true,
                                Course.LAB, (Course[])cp.temp, cp.nData);
    }

    void remove_actionPerformed(ActionEvent e) {
	  if(res.getSelectedRoutineCell() == null) {
		MyError.debug("Please select a class to delete.");
	  }
	  else {
		getSelectedCourse().manuRCList.delete( res.getSelectedRoutineCell() );
		reloadRoutine();
		saved = false;
	  }
	  G.setTips(getTips());
    }

    void removeAll_actionPerformed(ActionEvent e) {
	  if(getSelectedCourse() == null) MyError.show("Please select a course to delete.");
	  else {
		getSelectedCourse().manuRCList.deleteAll();
		reloadRoutine();
		saved = false;
	  }
	  G.setTips(getTips());
    }
}