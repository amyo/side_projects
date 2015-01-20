package qrinput;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.sun.java.swing.plaf.windows.*;

import global.*;
import datastructure.*;

/**
 * Title:        QuickRoutine Input Module
 * Description:  This Module take basic input data for QuickRoutine.
 * Copyright:    Copyright (c) 2004
 * Company:      Slog Overs
 * @author Simanto, Jewel and Milton
 * @version 1.0
 */

public class QRIMain extends JFrame
{
    JMenuBar jMenuBar1 = new JMenuBar();
    WindowsMenuBarUI wmb = new WindowsMenuBarUI();

    JMenu jMenu1 = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    JMenuItem jMenuItem2 = new JMenuItem();
    JMenuItem jMenuItem3 = new JMenuItem();
    JMenuItem jMenuItem4 = new JMenuItem();
    JMenuItem jMenuItem5 = new JMenuItem();
    JMenuItem jMenuItem6 = new JMenuItem();
    JMenuItem jMenuItem7 = new JMenuItem();
    JMenu jMenu3 = new JMenu();
    JMenuItem jMenuItem8 = new JMenuItem();

    public QRIMain()
    {
        super("QuickRoutine Main (for test)");
        setIconImage(new ImageIcon("icon/menu.JPG").getImage());

        try
        {
            jbInit();
            setSize(900, 600);
            G.setCenter(this);
            show();
            //new QRIDialog();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exc) {
            System.err.println("Error: Cant' Load System Look n Feel\n");
            System.err.println("Error loading L&F: " + exc);
        }

        //new QRIDialog();
        //QRIMain QRIMain = new QRIMain();
    }
    private void jbInit() throws Exception
    {
        jMenu1.setText("File");
        jMenuItem1.setText("Exit");
        jMenu2.setText("Insert Data");
        jMenuItem2.setText("Initialization");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                jMenuItem2_actionPerformed(e);
            }
        });
        jMenuItem3.setText("Batch Informations");
        jMenuItem4.setText("Room Informations");
        jMenuItem5.setText("Teacher Information");
        jMenuItem6.setText("Course Information");
        jMenuItem7.setText("Report");
        jMenu3.setText("Help");
        jMenuItem8.setText("About");

        this.setJMenuBar(jMenuBar1);

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu3);
        jMenu1.add(jMenuItem1);
        jMenu2.add(jMenuItem2);
        jMenu2.add(jMenuItem3);
        jMenu2.add(jMenuItem4);
        jMenu2.add(jMenuItem5);
        jMenu2.add(jMenuItem6);
        jMenu2.add(jMenuItem7);
        jMenu3.add(jMenuItem8);
    }

    void jMenuItem2_actionPerformed(ActionEvent e)
    {
        G.p("Inside ...\n");
        //new QRIDialog();
    }
}