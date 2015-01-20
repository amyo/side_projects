package global;

import java.awt.*;
import javax.swing.*;

public class LRPan extends JPanel
{
    JPanel atRight = new JPanel();
    JPanel atLeft = new JPanel();

    public LRPan() {
        setLayout(new BorderLayout());
        add(atRight, BorderLayout.EAST);
        add(atLeft, BorderLayout.WEST);
    }

    public void addRight(Component btn) {
        atRight.add(btn);
    }

    public void addLeft(Component btn) {
        atLeft.add(btn);
    }
}