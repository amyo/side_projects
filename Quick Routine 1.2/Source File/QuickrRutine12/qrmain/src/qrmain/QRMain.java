package qrmain;


import javax.swing.*;
import java.awt.*;

import global.*;
import datastructure.*;
import algorithm.RoutineStyle;

import help.*;

public class QRMain extends JPanel {
    boolean packFrame = false;
    private JWindow splashScreen = null;
    private JLabel splashLabel=null;
    private QRMainFrame frame;


    /**Construct the application*/
    public QRMain() {

        // Create and throw the splash screen up. Since this will
        // physically throw bits on the screen, we need to do this
        // on the GUI thread using invokeLater.
        createSplashScreen();

        // do the following on the gui thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showSplashScreen();
            }
        });

        // Show the demo and take down the splash screen. Note that
        // we again must do this on the GUI thread using invokeLater.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame=new QRMainFrame();
                if (packFrame) {
                    frame.pack();
                }
                else {
                    frame.validate();
                }
                //Center the window
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension frameSize = frame.getSize();
                if (frameSize.height > screenSize.height) {
                    frameSize.height = screenSize.height;
                }
                if (frameSize.width > screenSize.width) {
                    frameSize.width = screenSize.width;
                }
                frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
                frame.setVisible(true);

                hideSplash();
            }
        });
    }

    public static void p(String s)
    {
        JOptionPane.showMessageDialog(null,s);
    }
    /**Main method*/
    public static void main(String[] args) {
       /* try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch(Exception e) {
            e.printStackTrace();
        }*/

        new QRMain();
    }

    public void createSplashScreen() {
    try {
        ImageIcon icn=new ImageIcon(getClass().getResource("icon/Splash.jpg"));
        splashLabel = new JLabel(icn);
    }
    catch (Exception ex) {
        MyError.debug("Cant' Load Splash Image.");
        return;
    }

      if(!isApplet()) {
          splashScreen = new JWindow();
          splashScreen.getContentPane().add(splashLabel);
          splashScreen.pack();
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          splashScreen.setLocation(screenSize.width/2 - splashScreen.getSize().width/2,
                             screenSize.height/2 - splashScreen.getSize().height/2);
      }
    }


    public void showSplashScreen() {
         if(!isApplet()) {
             splashScreen.show();
         } else {
             add(splashLabel, BorderLayout.CENTER);
             validate();
             repaint();
         }
  }

  public void hideSplash() {
      if(!isApplet()) {
          splashScreen.setVisible(false);
          splashScreen = null;
          splashLabel = null;
      }
  }

  public boolean isApplet()
  {
      return false;
  }
}