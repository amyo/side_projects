package qrinput;

import global.*;
import datastructure.*;
import java.awt.event.*;
import help.*;
/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

public class RoomPan extends CmnPan
{
    RoomInfo infoPan = new RoomInfo();
    SessionPan sPan = new SessionPan();
    String colName[] = {"Room ID", "Description", "Capacity", "Type",
                          "Still Free", "Course Assign", "Manually Assigned"};

    ManPanLayout manPanRef;

    public RoomPan() {
        // IO.readRoom();

        /* Seting data reference. */
        real = G.r;
        temp = new Room[ G.MAXR ];
        G.copyArray(temp, real);
        nData = G.nRoom;

        real = (Room[])real;
        temp = (Room[])temp;

        /* Add GUI for Teacher panel. */
        setGUI(infoPan, sPan, colName);

        /* Initialize Interface. */
        clearInterface();

        infoPan.type.setNextFocusableComponent(insert);

        /* Add action listener to input component */
        infoPan.id.addActionListener(this);
    }

    /* Over loaded from parent. */
    void clearInterface() {
        infoPan.id.setText("");
        infoPan.des.setText("");
        infoPan.cap.setText("60");
        infoPan.type.setSelectedIndex(-1);
        sPan.clearAll();

        clearTable();
    }

    String errorInterface(int k) {
        /**
         * Check for ID.
         */
        if(infoPan.id.getText().trim().length() == 0)  return "Room' ID cant' be empty!";

        /**
         * Check for CAPACITY.
         */
        try {
            Integer.parseInt(infoPan.cap.getText().trim());
        } catch(NumberFormatException e) {
            return "You must enter a numner for room capacity!";
        }

        /**
         * Check for TYPE.
         */
         if(infoPan.type.getSelectedIndex() == -1)
            return "Please select a type for room.";

        /**
         * Lab room requirment.
         */
         if(infoPan.type.getSelectedIndex() == 1 && infoPan.des.getText().trim().length() == 0)
            return "You must give a description for lab room!";

        int i = Room.index( infoPan.id.getText().trim(), temp, nData );

        /**
         * A new Distinct value at interface
         * so no eror.
         */
        if(i == -1) return null;

        if(i != k) return "All Room' ID must have to be distinct!\n" +
                            infoPan.id.getText().trim() + " is already ID for "+
                            temp[i].getNickName(coursePanRef.temp, coursePanRef.nData);

        return null;
    }

    String deleteError(int k) {
        RCList rcl = ((Room)temp[k]).getManualRCList( (Course[])coursePanRef.temp, coursePanRef.nData );

        if(rcl.nCell == 0) return null;

        String msg = ((Room)temp[k]).getNickName(coursePanRef.temp, coursePanRef.nData) +
                     " is manually assigned to following courses' \n";

        for(int i = 0; i < rcl.nCell; i++)
            msg += (rcl.rCell[i].getName() + "\n");

        msg += "\nBefore delete this room you must remove corresponding manual class(s).";

        MyError.show(msg);
        return "Not Null";
    }

    void closeOurRoutine() {
        manPanRef.res.closeCat(2);
    }

    void deletePK(int k) {
        ((Room)temp[k]).updatePK(coursePanRef.temp, coursePanRef.nData, null);
    }

    String updateError(int k) {
        Room rRef = (Room)readInterface();
        RCList rcl = ((Room)temp[k]).getManualRCList( (Course[])coursePanRef.temp, coursePanRef.nData );

        if( rcl.nCell > 0 && !((Room)temp[k]).id.equalsIgnoreCase(rRef.id) ) {

            String msg = ((Room)temp[k]).getNickName(coursePanRef.temp, coursePanRef.nData) +
                         " is manually assigned to following courses' \n";

            for(int i = 0; i < rcl.nCell; i++)
                msg += (rcl.rCell[i].getName() + "\n");

            msg += "\nBefore update this rooms' ID you must remove corresponding manual class(s).";

            return msg;
        }

        int nFree = rRef.nFree();
        int hoursCourseAssign = rRef.getHourCourseAssign(coursePanRef.temp, coursePanRef.nData );

        if(nFree < hoursCourseAssign)
            return "A rooms' available free hour(s) cant' be less than assigned course(s) hour(s)!\n"+
            rRef.getNickName(coursePanRef.temp, coursePanRef.nData ) + "s' assigned course(s) hour(s) is " + hoursCourseAssign + "\n"+
            rRef.getNickName(coursePanRef.temp, coursePanRef.nData ) + "s' available free hour(s) cant' be updated to " + nFree + ".";

        return null;
    }

    void updatePK(int k) {
        ((Room)temp[k]).updatePK(coursePanRef.temp, coursePanRef.nData,
        ((Room)readInterface()).id );
    }

    Data readInterface() {
        return new Room(
            infoPan.id.getText().trim(),
            infoPan.des.getText().trim(),
            Integer.parseInt(infoPan.cap.getText().trim()),
            infoPan.type.getSelectedIndex(),
            sPan.getValue()
        );
    }

    void setInterface() {
        int k = t.getSelectedRow();

        if(k == -1) return;

        /* Setting LEFT panel from talbe. */
        infoPan.id.setText(((Room)temp[k]).id);
        infoPan.des.setText(((Room)temp[k]).des);
        infoPan.cap.setText(""+((Room)temp[k]).cap);
        infoPan.type.setSelectedIndex(((Room)temp[k]).type);

        /* Setting RIGHT panel. */
        sPan.setValue(((Room)temp[k]).s);
        sPan.setManuBusyValue(((Room)temp[k]).getManualBusySession(
                (Course[])coursePanRef.temp, coursePanRef.nData ).s );
    };

    String getTips() {
        if(infoPan.id.getText().trim().length() == 0)
            return infoPan.idTips;

        if(infoPan.des.getText().trim().length() == 0)
            return infoPan.desTips;

        if(infoPan.cap.getText().trim().length() == 0)
            return infoPan.capTips;

        if(infoPan.type.getSelectedIndex() == -1)
            return infoPan.typeTips;

        if(insert.isEnabled())
            return "Now set available period 'Free' to let QuickRoutine know that when to assign class at this room.";

        if(t.getSelectedRow() >= 0)
            return "To delete this room click 'delete' button or to update click 'Update' button and to insert new room click 'Clear All' button.";

        return "I think you will agree with me that inserting a room information is so easy";
    }

    void save2Real2File() {
        G.copyArray(G.r, temp);
        G.nRoom = nData;

        IO.writeRoom();
    };

    void helpAction() {
        if(insert.isEnabled()) new HelpFrame(HelpFrame.ROOM);
        else new HelpFrame(HelpFrame.DELUP);
    }
}