
 package qrinput;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;

import global.*;
import datastructure.*;
import com.borland.jbcl.layout.*;
import com.borland.dbswing.*;
import help.*;

public class CmnPan extends JPanel
implements ActionListener
{
    /* All Panel */
    JPanel leftPan = new JPanel();
    JPanel rightPan = new JPanel();
    JPanel topPan = new JPanel();
    JPanel bottomPan = new JPanel();
    LRPan btnPan = new LRPan();

    /* Table */
    JTable t = new JTable();
    DefaultTableModel tm  = new DefaultTableModel();

    /* All Button */
    JButton clearAll = new JButton("Clear All");
    JButton insert = new JButton("Insert");
    JButton delete = new JButton("Delete");
    JButton update = new JButton("Update");
    JButton help = new JButton(" Help ");

    /* Tips Arrangement */
    LRPan tipsPan = new LRPan();

    /**
     * CoursePan reference, used bey get row.
     */
    public CoursePan coursePanRef;

    public int nData;
    public Data real[], temp[];

    public boolean saved = true;

    public CmnPan() {
        setCmnLayout();

        /* Set properties for table */
        t.setModel(tm);
        t.removeEditor();
        t.setRequestFocusEnabled(false);
        t.setFont(new Font("DialogInput", 0, 12));
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        t.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                setInterface();
                t.removeEditor();

                insert.setEnabled(false);
                delete.setEnabled(true);
                update.setEnabled(true);

                G.setTips("To enter a new entry please click 'Clear All' button;");
            }
        });

        /* Set properties for TIPS. */
        // tipsLabel.setFont(new Font("DialogInput", 0, 13));

        /* Add Action Listener. */
        clearAll.addActionListener(this);
        insert.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        help.addActionListener(this);
    }

    void setCmnLayout() {
        /* Title for LEFT and RIGHT panel. */
        leftPan.setBorder(BorderFactory.createTitledBorder("Insert Information"));
        rightPan.setBorder(BorderFactory.createTitledBorder("Insert Session"));

        /* Contains of TOP panel. */
        topPan.setLayout(new GridLayout(1, 2));
        topPan.setPreferredSize(new Dimension(700, 200));
        topPan.add(leftPan);
        topPan.add(rightPan);

        /* Contains of BUTTON panel. */
        btnPan.addLeft(clearAll);
        btnPan.addRight(insert);
        btnPan.addRight(delete);
        btnPan.addRight(update);
        btnPan.addRight(help);

        /* Contains of BOTTOM panel. */
        bottomPan.setLayout(new BorderLayout());
        bottomPan.add(new JScrollPane(t), BorderLayout.CENTER);
        bottomPan.add(btnPan, BorderLayout.SOUTH);
        bottomPan.setBorder(BorderFactory.createTitledBorder("Current Information"));
        bottomPan.setPreferredSize(new Dimension(10, 200));

        /* Contains of TIPS panel */

        /* Contains of MAIN panel. */
        setLayout(new VerticalFlowLayout());
        add(topPan);
        add(bottomPan);
        add(tipsPan);
    }

    /**
     * Load table from temp array
     *
     */
    void reloadTable() {
        int n = tm.getRowCount();
        for(int i = 0; i < n; i++)
            tm.removeRow(0);

        for(int i = 0; i < nData; i++)
            setRowAt(i, temp[i]);
    }

    /**
     * Set data at (k)th row of table.
     */
    void setRowAt(int k, Data row) {
        tm.insertRow(k, row.getRow(coursePanRef.temp, coursePanRef.nData));
    }

    /*
     * Take Childs' Specific panel.
     */
    public void setGUI(JPanel infoPan, JPanel sPan, String colName[]) {
        leftPan.add(infoPan);
        rightPan.add(sPan);
        tm.setColumnIdentifiers(colName);
    }

    public void clearTable() {
        for(int i = 0; i < nData; i++)
            if(t.isRowSelected(i))
                t.removeRowSelectionInterval(i, i);

        insert.setEnabled(true);
        delete.setEnabled(false);
        update.setEnabled(false);
    }

    /*
     * Set of func. have to be overloaded.
     */
    void clearInterface() {}

    String errorInterface(int k) { return null; }

    String deleteError(int k) { return null; }
    void closeOurRoutine() {}
    void deletePK(int k) {}

    String updateError(int k) { return null; }
    void updatePK(int k){}

    Data readInterface() { return null; }
    void setInterface() {};
    void save2Real2File() {};
    void helpAction() {
    }

    public void actionPerformed(ActionEvent e) {

        /* CLEAR ALL input interface */
        if(e.getSource() == clearAll) {
            clearInterface();
            insert.setRequestFocusEnabled(true);

            G.setTips("Now you are ready to enter a new entry.");
        }

        /* INSERT data from Interface. */
        else if(e.getSource() == insert) {
            if(errorInterface(-1) == null) {
                temp[nData] = readInterface();
                setRowAt(nData, temp[nData]);
                nData++;

                clearInterface();

                saved = false;
            }
            else MyError.show(errorInterface(-1));
        }

        /* DELETE all selected rows. */
        else if(e.getSource() == delete)  {
            if(t.getSelectedRowCount() > 0) {
                int k = 0;
                boolean deleted = false;
                String msg = "Are you sure to delete:\n";
                for(int i = 0; i < nData; i++)
                    if(t.isRowSelected(i)
                    && deleteError(i) == null
                    && MyError.yn(msg+temp[i].getName(coursePanRef.temp, coursePanRef.nData))) {
                        // Before delete a ROOM, BATCH, TEACHER...
                        // please close theis routine...
                        closeOurRoutine();

                        // Dleting...
                        deletePK(i);

                        tm.removeRow(i);
                        nData = Data.delete(temp, i, nData);

                        i--;
                        deleted = true;
                    }


                if(deleted) {
                    /* Alarm user to save. */
                    clearInterface();
                    saved = false;
                }
            }
            else MyError.show("Please select at least one row to delete.");
        }

        /* UPDATE a selectetd row. */
        else if(e.getSource() == update) {

            //
            if(t.getSelectedRowCount() == 1) {
                int k = t.getSelectedRow();

                if(errorInterface(k) == null) {

                    // Can update?
                    if(updateError(k) != null) {
                        MyError.show(updateError(k));
                        return;
                    }

                    String msg = "Are you sure to update:\n"
                            + temp[k].getName(coursePanRef.temp, coursePanRef.nData)
                            + " to \n" + readInterface().getName(coursePanRef.temp, coursePanRef.nData);

                    if(MyError.yn(msg)) {
                        //
                        updatePK(k);

                        temp[k] = readInterface();
                        tm.removeRow(k);
                        setRowAt(k, temp[k]);

                        clearInterface();
                        saved = false;
                    }
                } else MyError.show(errorInterface(k));
            }
            else MyError.show(
                "You have selected "+t.getSelectedRowCount()+" row(s)\n"+
                "Please select a single row to update." );
        }

        /* SAVE to file. */
        else if(e.getSource() == help) {
            helpAction();
        }
    }
}