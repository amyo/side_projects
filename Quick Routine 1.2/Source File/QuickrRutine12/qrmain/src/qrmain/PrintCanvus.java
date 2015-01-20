package qrmain;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;
import global.*;
import java.util.*;

public class PrintCanvus extends JPanel implements Printable{

  int startX=20,startY=80;
  int cellWidth=65,cellHeight=35;
  int boardWidth=cellWidth*10;
  int boardHeight=cellHeight*6;
  int textXSpacing=cellWidth/10,textYSpacing=15;
  int cellFontSize=14;

  public static boolean isColorFull=true;

  Color
    periodColor = new Color(65, 131, 131),
    dayColor = new Color(150, 203, 203),

    assignColor = new Color(192, 192, 192),
    subAssignColor = new Color(226, 226, 226),

    selectedColor = new Color(0, 73, 108),
    subSelectedColor =  new Color(58, 110, 165),

    hilightColor = new Color(64, 128, 128),
    subHilightColor = new Color(84, 169, 169),

    baseColor = new Color(240, 240, 240);
  Font rtnTitleFont=new Font("DialogInput",Font.BOLD,17);

  Font rtnVertionFont=new Font("DialogInput", Font.PLAIN, 12);

  Font cellHeadingFont=new Font("DialogInput",Font.BOLD,15);
  Font cellNormalFont=new Font("DialogInput",Font.PLAIN,11);

  String s[] = G.dName;
  String pn[]=G.getPerName();

  ImageIcon logo=new ImageIcon( getClass().getResource("icon/logo.gif"));

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2=(Graphics2D)g;
    drawPage(g2);
  }

  public int print(Graphics g,PageFormat pf,int pageNum)throws PrinterException{
    if(pageNum>=1)return Printable.NO_SUCH_PAGE;
    Graphics2D g2=(Graphics2D)g;
    g2.setPaint(Color.black);
    g2.translate(pf.getImageableX(),pf.getImageableY());
    drawPage(g2);
    return Printable.PAGE_EXISTS;
  }
  public void drawPage(Graphics2D g2){
    if(isColorFull){
      for(int i=0;i<10;i++)
        fillRect(g2,0,i,periodColor);
      for(int i=1;i<6;i++)
        fillRect(g2,i,0,dayColor);
      fillRtnCell(g2,PrintPreview.rtnData);
    }
    drawRtnBoard(g2);
    setRtnTitle(g2);
    setCell(g2,PrintPreview.rtnData);
    setVertion(g2);
  }
  public void setRtnTitle(Graphics2D g2){
    g2.setFont(rtnTitleFont);
    g2.drawString(PrintPreview.rt,boardWidth/3 - 20, startY-15);
  }
  public void setVertion(Graphics2D g2){
    g2.setFont(rtnVertionFont);

    String barki = "QuickRoutine 1.2 (sited at www.justjewel.tk)";
    g2.drawString(barki, startX,startY+boardHeight+18);

    barki = "Let the authors know your comment tojewel@hotmail.com and simantoc@gmail.com";
    g2.drawString(barki, startX,startY+boardHeight+18+14);

    barki = "";
    g2.drawString(barki, startX,startY+boardHeight+18+2*14);

    barki = "";
    g2.drawString(barki, startX,startY+boardHeight+18+3*14);
  }
  public void setCell(Graphics2D g2, String ss[][][]){

    g2.drawImage(logo.getImage(),startX+textXSpacing+3,startY+textYSpacing/2+4,cellWidth-20,cellHeight-20,this);
    //fillCell(g2,0,0,"####");
    for(int i = 0; i < pn.length; i++) {
      fillCell(g2,0,i+1,pn[i]);
    }
    for(int i=0;i<5;i++) {
      fillCell(g2,i+1,0,s[i]);
      for(int j=0;j<9;j++) {
        fillCell(g2,i+1,j+1,ss[i][j][0]);
        textYSpacing+=15;
        fillCell(g2,i+1,j+1,ss[i][j][1]);
        textYSpacing-=15;
      }
    }
  }
  public void fillRtnCell(Graphics2D g2, String ss[][][]){
    for(int i=0;i<5;i++) {
      for(int j=0;j<9;j++) {
        fillRtnRect(g2,i+1,j+1,ss[i][j][1]);
      }
    }
  }

  public void drawRtnBoard(Graphics2D g2){
    g2.drawRect(startX,startY,boardWidth,boardHeight);
    //Vertical Line
    for(int i=0;i<10;i++)
      g2.drawLine(startX+i*cellWidth,startY,startX+i*cellWidth,startY+cellHeight*6);
    //Horizontal Line
    for(int i=0;i<6;i++)
      g2.drawLine(startX,startY+i*cellHeight,startX+cellWidth*10,startY+i*cellHeight);

  }
  public void fillCell(Graphics2D g2, int row,int col,String text){
    int textYSpacing=this.textYSpacing;
    int textXSpacing=this.textXSpacing;
    //cellheading action

    if(row==0||col==0){
      g2.setFont(cellHeadingFont);

      // row
      if(row == 0) {
        textYSpacing=cellHeight/2 + 6;
        textXSpacing+=5;
      }
      // col
      else {
        textYSpacing=cellHeight/2 + 6;
        textXSpacing+=10;
      }
    }
    //cellnormalaction
    else {
      g2.setFont(cellNormalFont);
    }
    g2.drawString(text,startX+col*cellWidth+textXSpacing,startY+row*cellHeight+textYSpacing);
  }
  public void fillRtnRect(Graphics2D g2, int row,int col,String text){
     if(!text.equals(""))fillRect(g2,row,col,assignColor);

  }
  public void fillRect(Graphics2D g2, int row,int col,Color back){
    g2.setColor(back);
    g2.fillRect(startX+col*cellWidth,startY+row*cellHeight,cellWidth,cellHeight);
    g2.setColor(Color.black);
  }
}
