package global;

import javax.swing.*;

public class MyError
{

    public MyError() {
    }

    public static void show(String msg) {
        JOptionPane.showMessageDialog(null, msg,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public static void report(String msg) {
        JOptionPane.showMessageDialog(null, msg,"Report",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void debug(String msg) {
        JOptionPane.showMessageDialog(null, msg,"Debug",JOptionPane.ERROR_MESSAGE);
    }

    public static void msg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static boolean yn(String msg) {
        return JOptionPane.showConfirmDialog(null, msg,"Confirm",JOptionPane.YES_NO_OPTION) == 0? true: false;
    }

    public static int ync(String msg) {
        return JOptionPane.showConfirmDialog(null, msg,"Confirm",JOptionPane.YES_NO_CANCEL_OPTION);
    }
}