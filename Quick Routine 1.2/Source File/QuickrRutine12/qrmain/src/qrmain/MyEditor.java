package qrmain;


import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

public class MyEditor extends JEditorPane implements Printable {

  public MyEditor() {
  super();
  this.setContentType("text/html");
  //this.setLayout(new FlowLayout());
  }

  public int print(Graphics g, PageFormat pageFormat, int pageIndex)
          throws PrinterException
  {
    if(pageIndex>0)
      return NO_SUCH_PAGE;
    Graphics2D g2D = (Graphics2D) g;
    pageFormat.setOrientation(pageFormat.REVERSE_LANDSCAPE);
    g2D.setClip((int)pageFormat.getImageableX(),(int)pageFormat.getImageableY(),
                (int)pageFormat.getImageableWidth(),(int)pageFormat.getImageableHeight());
    g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
    paint(g2D);
    return PAGE_EXISTS;
  }
}