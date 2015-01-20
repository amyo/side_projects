package qrinput;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

import global.*;
import com.borland.jbcl.layout.*;
import help.*;

/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author
 * @version 1.0
 */

public class SessionPan extends JPanel
{
    /**
     * 'pnbox' contains grid of single session,
     * 'btnPan' contains three button to control the grid.
     */
    JPanel panBox = new JPanel();
    LRPan btnPan = new LRPan();

    /**
     * There buttons to make
     * different type of change the grid instantly.
     */
    JButton freeAll = new JButton("Free All");
    JButton busyAll = new JButton("Busy All");
    JButton reset = new JButton("Reset");

    /**
     * Contains of up-left corner of the grid
     * This just occupied the grid, do nothing.
     */
     TitleBox logo = new TitleBox();

    /**
     * Tittle of the colum amd row of the grid,
     * @per[] is tittle of period,
     * @day[] is tittle of day.
     */
    TitleBox per[] = new TitleBox[Basic.nPeriod];
    TitleBox day[] = new TitleBox[Basic.nDay];

    /**
     * Real session cell contains,
     * this is actually a buttons array
     * but designe so that it act as a check box.
     */
    MyCheckBox c[][] = new MyCheckBox[Basic.nDay][Basic.nPeriod];

    public SessionPan()
    {
        /**
         * Set layout.
         */
        setLayout(new VerticalFlowLayout());
        panBox.setLayout(new GridLayout(Basic.nDay + 1, Basic.nPeriod + 1));
        // panBox.setBackground(Color.black);
        panBox.add(logo);
        logo.setText("???");
        logo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HelpFrame(HelpFrame.SESSION);
            }
        });

        /**
         * Add all component.
         */
        btnPan.addRight(freeAll);
        btnPan.addRight(busyAll);
        btnPan.addLeft(reset);
        add(panBox);
        add(btnPan);

        /**
         * Set period name on per[] tittle.
         * and add it to the panel
         */
        for(int j = 0; j < per.length; j++) {
            per[j] = new TitleBox(""+(j+ (j<5? 8: -4))+":00", j, false, c);
            panBox.add(per[j]);
        }

        /**
         * Setting day name on day[] tittle.
         */
        for(int i = 0; i < day.length; i++)
            day[i] = new TitleBox(G.dName[i], i, true, c);

        /**
         * Add DAY tittle and check box to panel.
         */
        for(int i = 0; i < c.length; i++) {
            panBox.add(day[i]);

            for(int j = 0; j < c[i].length; j++) {
                c[i][j] = new MyCheckBox();
                panBox.add(c[i][j]);
            }
        }

        /**
         * Set all session Free
         * without Manu session.
         */
        freeAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setValueWithoutManu(MyCheckBox.FREE);
            }
        });

        /**
         * Set all session Busy
         * without Manu session.
         */
        busyAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setValueWithoutManu(MyCheckBox.BUSY);
            }
        });

        /**
         * Set all seeion Free
         * and Lunch session Busy
         * without changing Manu session.
         */
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
    }

    /**
     * Set all session to value 'v'
     * WITHOUT changing Manu session.
     * @param value that have to set.
     */
    void setValueWithoutManu(int v) {
        for(int i = 0; i < c.length; i++)
            for(int j = 0; j < c[i].length; j++)
                if(c[i][j].getValue() != MyCheckBox.MANU)
                    c[i][j].setValue(v);
    }

    /**
     * Set all session as given in v[][]
     * with Manu session.
     */
    void setValue(int v[][]) {
        for(int i = 0; i < c.length; i++)
            for(int j = 0; j < c[i].length; j++)
                c[i][j].setValue(v[i][j]);
    }

    /**
     * Set all manual session....
     * @param v
     */
    void setManuBusyValue(int v[][]) {
        for(int i = 0; i < c.length; i++)
            for(int j = 0; j < c[i].length; j++)
                if(v[i][j] == MyCheckBox.BUSY)
                    c[i][j].setValue(MyCheckBox.MANU);
    }

    /**
     * Set all seeion Free
     * and Lunch session Busy
     * without changing Manu session.
     */
    void refresh() {
        setValueWithoutManu(MyCheckBox.FREE);
        per[5].setValue(MyCheckBox.BUSY);
    }

    void clearAll() {
        for(int i = 0; i < c.length; i++)
            for(int j = 0; j < c[i].length; j++)
                c[i][j].setValue(j == Basic.lunchPeriod? MyCheckBox.BUSY: MyCheckBox.FREE);
    }

    /**
     * @return a two dimentional array that contain the value of session panel.
     */
    int[][] getValue() {
        int s[][] = new int[c.length][c[0].length];
        for(int i = 0; i < c.length; i++)
            for(int j = 0; j < c[i].length; j++)
                s[i][j] = c[i][j].getValue();

        return s;
    }
}