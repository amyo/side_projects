package global;

import java.awt.*;
import javax.swing.*;
import datastructure.*;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2004
 * Company:
 * @author
 * @version 1.0
 */

import qrmain.*;

public class G
{
  public static String NameVer = "QuickRoutine 1.2";
    public static String loc = "", name = "ecs";

    public static QRMainFrame mfRef;
    public static MainEditor meRef;

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int sWidth = 1024, sHeight = 768, mWidth=1024;

    public static boolean helpFrame=true;

    public static void getScrSize() {
        sWidth = screenSize.width;
        sHeight = screenSize.height;
    }

    public static String dName[] = {"Sun.", "Mon.", "Tue.", "Wed.", "Thu."};
    public static String dFullName[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};

    public static String[] getPerName() {
        String s[] = new String[ Basic.nPeriod ];
        for(int j = 0; j < s.length; j++)
            s[j] = ""+(j + (j<5? 8: -4))+":00";

        return s;
    }

    public static String getPerName(int i) {
        String s[] = getPerName();
        return s[i];
    }

    /**
     * Variable that will be used by All pakage.
     */
    public static int MAXR = 100, nRoom = 0;
    public static Room r[] = new Room[MAXR];

    public static int MAXB = 100, nBatch = 0;
    public static Batch b[] = new Batch[MAXB];

    public static int MAXT = 100, nTeacher = 0;
    public static Teacher t[] = new Teacher[MAXT];

    public static int MAXC = 500, nCourse = 0;
    public static Course c[] = new Course[MAXC];

    ImageIcon ticon = new ImageIcon( getClass().getResource("icon/tips.GIF") );
    ImageIcon mticon = new ImageIcon( getClass().getResource("icon/mtips.JPG") );

    static G g = new G();
    static ImageIcon ti = g.ticon;
    static ImageIcon mti = g.mticon;

    public static JLabel tips = new JLabel(ti, SwingConstants.LEFT);
    public static JLabel mainTips = new JLabel(mti, SwingConstants.LEFT);

    public static void setTips(String t) {
      tips.setText(t);
    }


    public static void copyArray(Data to[], Data from[]) {
        int i;
        for(i = 0; from[i] != null; i++)
            to[i] = from[i].copy();
    }

    public static void p(String s) {
        System.out.print(s);
    }

    public static void setCenter(JFrame f) {
        getScrSize();
        if(f.getWidth() > sWidth) f.setSize(sWidth, f.getHeight());
        if(f.getHeight() > sHeight) f.setSize(f.getWidth(), sHeight);

        f.setLocation((sWidth - f.getWidth()) / 2, (sHeight - f.getHeight()) / 2);
    }
    public static void setCenter(JDialog f) {
        getScrSize();
        if(f.getWidth() > sWidth) f.setSize(sWidth, f.getHeight());
        if(f.getHeight() > sHeight) f.setSize(f.getWidth(), sHeight);

        f.setLocation((sWidth - f.getWidth()) / 2, (sHeight - f.getHeight()) / 2);
    }
}
