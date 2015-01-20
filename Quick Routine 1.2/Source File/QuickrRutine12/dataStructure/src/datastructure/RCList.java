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

public class RCList
implements Serializable
{
    public int nCell;
    public RoutineCell rCell[];

    public RCList() {
        nCell = 0;
        rCell = new RoutineCell[Basic.nDay * Basic.nPeriod];
    }

    public RCList(int size) {
        nCell = 0;
        rCell = new RoutineCell[size];
    }

    public RCList(int nCell, RoutineCell rCell[]) {
        this.nCell = nCell;
        //this.rCell = (RoutineCell[])rCell.clone();
        this.rCell = new RoutineCell[rCell.length];
        
        for(int i = 0; i < this.nCell; i++)
            this.rCell[i] = rCell[i].copy();
    }

    public RCList copy() {
        return new RCList(nCell, rCell);
    }

    public boolean add(RoutineCell newCell) {
        if(nCell >= rCell.length) return false;
        else {
            rCell[ nCell++ ] = newCell;
            return true;
        }
    }

    public void add(RCList rcl) {
        for(int i = 0; i < rcl.nCell; i++)
            add(rcl.rCell[i]);
    }

    public int nClassLeft() {
      return rCell.length - nCell;
    }

    public void deleteAll() {
        nCell = 0;
    }

    public boolean delete(RoutineCell rc) {
        for(int i = 0; i < nCell; i++)
            if(rCell[i].equal(rc)) {
                delete(i);
                return true;
            }

        MyError.debug("Dont' match RoutineCell to delete!");
        return false;
    }

    public void delete(int index) {
        if(index >= rCell.length) MyError.debug("Cant' delete, RCList overflow!");
        else {
            for(int i =  index + 1; i < nCell; i++)
                rCell[i-1] = rCell[i];

            nCell--;
        }
    }
}