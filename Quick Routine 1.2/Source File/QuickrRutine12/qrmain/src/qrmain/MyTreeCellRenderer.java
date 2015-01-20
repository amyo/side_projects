package qrmain;

import javax.swing.*;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

import global.*;

public class MyTreeCellRenderer extends JLabel
implements TreeCellRenderer
{
    /** Font used if the string to be displayed isn't a font. */
    static protected Font             defaultFont;

    /**Icon for the tree node*/
    //static protected ImageIcon        icnRoot;
    ImageIcon        icnRoot;
    ImageIcon        icnBatch;
    ImageIcon        icnTeacher;
    ImageIcon        icnRoom;
    //ImageIcon        icnBatchLeaf = new ImageIcon(getClass().getResource("Icon/batchLeaf.JPG"));
    //ImageIcon        icnTeacherLeaf=new ImageIcon(getClass().getResource("Icon/teacherLeaf.JPG"));
    //ImageIcon        icnRoomLeaf=new ImageIcon(getClass().getResource("Icon/roomLeaf.JPG"));

    /** Color to use for the background when selected. */
    static protected final Color SelectedBackgroundColor=new Color(141,175,118);

    static
    {
	try{
	    defaultFont = new Font("DialogInput", Font.BOLD, 12);
	}
        catch (Exception e) {
            MyError.debug("Cant' load tree font.");
        }

    }

    /** Whether or not the item that was last configured is selected. */
    protected boolean            selected;

    /**
      * This is messaged from JTree whenever it needs to get the size
      * of the component or it wants to draw it.
      * This attempts to set the font based on value, which will be
      * a TreeNode.
      */
    public MyTreeCellRenderer()
    {
        try {
            icnRoot=new ImageIcon(getClass().getResource("icon/root.JPG"));
            icnBatch= new ImageIcon(getClass().getResource("icon/batch.JPG"));
            icnTeacher=new ImageIcon(getClass().getResource("icon/teacher.JPG"));
            icnRoom=new ImageIcon(getClass().getResource("icon/room.JPG"));
        }
        catch (Exception ex) {
            MyError.debug("Cant' Load Tree Image.");
        }
    }
    public Component getTreeCellRendererComponent(JTree tree, Object value,
					  boolean selected, boolean expanded,
					  boolean leaf, int row,
						  boolean hasFocus) {
	Font            font;
	String          stringValue = tree.convertValueToText(value, selected,
					   expanded, leaf, row, hasFocus);

	/* Set the text. */
	setText(stringValue);
	/* Tooltips used by the tree. */
	setToolTipText(stringValue);


	/* Set the image. */

        String s=""+value;
	if(s.equalsIgnoreCase("Routine"))
	    setIcon(icnRoot);
        else if(s.equalsIgnoreCase("Batch"))
	    setIcon(icnBatch);
        else if(s.equalsIgnoreCase("Teacher"))
	    setIcon(icnTeacher);
        else if(s.equalsIgnoreCase("Room"))
	    setIcon(icnRoom);
        else setIcon(null);

	/* Update the selected flag for the next paint. */
	this.selected = selected;

	return this;
    }

    /**
      * paint is subclassed to draw the background correctly.  JLabel
      * currently does not allow backgrounds other than white, and it
      * will also fill behind the icon.  Something that isn't desirable.
      */
    public void paint(Graphics g) {
	Color            bColor;
	Icon             currentI = getIcon();

	if(selected)
	    bColor = SelectedBackgroundColor;
	else if(getParent() != null)
	    /* Pick background color up from parent (which will come from
	       the JTree we're contained in). */
	    bColor = getParent().getBackground();
	else
	    bColor = getBackground();
	g.setColor(bColor);
	if(currentI != null && getText() != null) {
	  int          offset = (currentI.getIconWidth() + getIconTextGap());

	   g.fillRect(offset, 0, getWidth() - 1 - offset,
		      getHeight() - 1);

	}
	else
	    g.fillRect(0, 0, getWidth()-1, getHeight()-1);
	super.paint(g);
    }
}
