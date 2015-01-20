package help;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;
import javax.swing.filechooser.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;

import global.*;

import global.*;

public class HelpFrame extends JFrame {
    private BorderLayout borderLayout1 = new BorderLayout();
    private TitledBorder titledBorder1;
    private JPanel jPanel2 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JPanel jPanel1 = new JPanel();
    private VerticalFlowLayout verticalFlowLayout2 = new VerticalFlowLayout();
    private TitledBorder titledBorder2;
    private JEditorPane html = new JEditorPane();
    private BorderLayout borderLayout2 = new BorderLayout();
    private Border border1;
    private Border border2;
    private JPanel jPanel4 = new JPanel();
    private JPanel jPanel5 = new JPanel();

    Stack bStack = new Stack();
    Stack fStack = new Stack();
    String lastURL = null;

    String path = "/index.htm";

    public static String HOME = "home";
    public static String WHAT = "what";
    public static String START = "start";

    public static String ROOM = "room";
    public static String BATCH = "batch";
    public static String TEACHER = "teacher";
    public static String COURSE = "course";

    public static String SESSION = "session";
    public static String MANUAL = "manual";
    public static String DELUP = "Delete/Update";
    public static String EDIT = "edit";
    public static String REPORT = "Report";
    public static String CONSTRAINTS = "Constraints";
    public static String SUNMOR = "Sunday Morning";
    public static String HELPUS = "Help Us";

    public void goToBookMark(String bookMark) {
        try {
            goToURL(getClass().getResource(path) + "#" + bookMark);
        }
        catch (Exception ex) {
            MyError.debug("Cant' Load Help.");
        }
    }

    public void goToURL(String url) {
        try {
            html.setPage(url);
            lastURL = url;
        }
        catch (IOException ex) {
        }
    }

    static HelpFrame onlyHelpFrameRef = null;
    private TitledBorder titledBorder3;
  private JPanel jPanel3 = new JPanel();
  private BorderLayout borderLayout3 = new BorderLayout();
  private JPanel jPanel6 = new JPanel();
  private JButton back = new JButton();
  private JButton forward = new JButton();
  private FlowLayout flowLayout1 = new FlowLayout();
  private JButton close = new JButton();
  private JCheckBox jCheckBox1 = new JCheckBox();
  private TitledBorder titledBorder4;

    public HelpFrame(String bookMark) {
        if(onlyHelpFrameRef != null) onlyHelpFrameRef.dispose();
        onlyHelpFrameRef = this;

        try {
            jbInit();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        goToBookMark(bookMark);

        back.setEnabled(false);
        forward.setEnabled(false);

        html.addHyperlinkListener(createHyperLinkListener());
        try {
            setIconImage(new ImageIcon(getClass().getResource("icon/qmark.JPG")).getImage());
        }
        catch (Exception ex) {
            MyError.debug("Cant' Load Help Icon.");
        }

        setSize(730, 370);
        G.setCenter(this);
        show();

        setVisible(true);
    }

    public HyperlinkListener createHyperLinkListener() {
      return new HyperlinkListener() {
          public void hyperlinkUpdate(HyperlinkEvent e) {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {

                if (e instanceof HTMLFrameHyperlinkEvent) {
                  ((HTMLDocument)html.getDocument()).processHTMLFrameHyperlinkEvent(
                      (HTMLFrameHyperlinkEvent)e);
                } else {

                    bStack.push(lastURL);
                    goToURL(""+e.getURL());
                    back.setEnabled(true);

                    fStack.removeAllElements();
                    forward.setEnabled(false);
                }
            }
          }
      };
    }

    void back_actionPerformed(ActionEvent e) {
        if(!bStack.empty()) {
            fStack.push( lastURL );
            forward.setEnabled(true);

            goToURL( (String)bStack.pop() );
        }

        if(bStack.empty()) back.setEnabled(false);
    }

    void forward_actionPerformed(ActionEvent e) {
        if(!fStack.empty()) {
            bStack.push( lastURL );
            back.setEnabled(true);

            goToURL( (String)fStack.pop() );
        }

        if(fStack.empty()) forward.setEnabled(false);
    }

    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder("");
        titledBorder2 = new TitledBorder("");
        border1 = BorderFactory.createLineBorder(UIManager.getColor("Panel.background"),15);
        border2 = BorderFactory.createLineBorder(Color.black,10);
        titledBorder3 = new TitledBorder("");
        titledBorder4 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,Color.black),"");
    this.setTitle(G.NameVer + " - Help");
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        this_windowClosing(e);
      }
    });
        this.getContentPane().setLayout(borderLayout1);
        jPanel2.setLayout(borderLayout2);
        jPanel1.setLayout(verticalFlowLayout2);
        verticalFlowLayout2.setHgap(20);
        verticalFlowLayout2.setVgap(0);
        html.setBackground(new Color(238, 238, 238));
        html.setFont(new java.awt.Font("Serif", 0, 13));
        html.setBorder(null);
        html.setEditable(false);
        jScrollPane1.setBorder(titledBorder4);
        jPanel2.setBackground(Color.white);
        jPanel2.setBorder(border1);
        jPanel3.setLayout(borderLayout3);
    jPanel6.setLayout(flowLayout1);
    back.setMnemonic('B');
    back.setText("<Back");
    back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back_actionPerformed(e);
            }
        });
    forward.setMnemonic('F');
    forward.setText("Foward>");
    forward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                forward_actionPerformed(e);
            }
        });
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    close.setMnemonic('C');
    close.setText("Close");
    close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close_actionPerformed(e);
            }
        });
    jCheckBox1.setSelected(G.helpFrame);
    jCheckBox1.setText("Show this at start up.");
    jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBox1_actionPerformed(e);
      }
    });
    this.getContentPane().add(jPanel2,  BorderLayout.CENTER);
        jPanel2.add(jScrollPane1, BorderLayout.CENTER);
        this.getContentPane().add(jPanel1,  BorderLayout.SOUTH);
        jPanel1.add(jPanel3, null);
    jPanel3.add(jPanel6,  BorderLayout.EAST);
    jPanel6.add(back, null);
    jPanel6.add(forward, null);
    jPanel6.add(close, null);
    jPanel3.add(jCheckBox1, BorderLayout.WEST);
    jPanel1.add(jPanel5, null);
        jScrollPane1.getViewport().add(html, null);
    }

    void close_actionPerformed(ActionEvent e) {
        updateFile();
        dispose();
    }

  void jCheckBox1_actionPerformed(ActionEvent e) {

  }
  void updateFile() {
    datastructure.RoutinePath rtnPath=new  datastructure.RoutinePath(G.name,G.loc);
    rtnPath.helpFrame=jCheckBox1.isSelected();
    G.helpFrame=rtnPath.helpFrame;
         IO.writeRoutinePath(rtnPath);
  }

  void this_windowClosing(WindowEvent e) {
    updateFile();
  }
}