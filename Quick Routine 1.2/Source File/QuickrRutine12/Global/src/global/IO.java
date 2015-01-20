package global;

import java.io.*;
import java.util.*;
import datastructure.*;

public class IO
// implements Serializable
{
    static String s;
    static StringTokenizer stok;

    public static void readAll2G() {
        readRoom();
        readBatch();
        readTeacher();
        readCourse();
    }

    public static void writeAllFromG() {
        writeRoom();
        writeBatch();
        writeTeacher();
        writeCourse();
    }

    public IO() {
    }

    public static Basic readBasic() {
        try {
            FileInputStream fis = new FileInputStream(G.loc+"\\"+G.name+".rtn");
            ObjectInputStream ois = new ObjectInputStream(fis);

            G.p("Start reading basic file...\n");

            try {
                return (Basic)ois.readObject();
            } catch(ClassNotFoundException e) {
                MyError.show("Class Dont' Match for basic.");
                return null;
            }

        } catch(IOException e) {
            MyError.show("Cant' read Baisc file!\nError: "+e.getLocalizedMessage());
            return null;
        }
    }

    public static RoutinePath readRoutinePath() {
        try {
            FileInputStream fis = new FileInputStream("QuickRoutine.info");
            ObjectInputStream ois = new ObjectInputStream(fis);

            G.p("Start reading QuickRoutine info file...\n");

            try {
                return (RoutinePath)ois.readObject();
            } catch(ClassNotFoundException e) {
                return null;
            }

        } catch(IOException e) {
            return null;
        }
    }

    public static void writeRoutinePath(RoutinePath routinePathRef) {
      try {
          ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream("QuickRoutine.info"));

          f.writeObject(routinePathRef);
          f.flush();
          f.close();

      } catch(IOException e) {
          MyError.show("QuickRoutine.inf Write exception!\nError: "+e.getMessage());
      }
    }

    public static void writeBasic(Basic basicRef) {
        try {
            ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(G.loc+"\\"+G.name+".rtn"));

            f.writeObject(basicRef);
            f.flush();
            f.close();

        } catch(IOException e) {
            MyError.show("Basic Write exception!\nError: "+e.getMessage());
        }
    }
    public static void readTeacher() {
        try {
            FileInputStream fis = new FileInputStream(G.loc+"\\"+G.name+".thr");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Teacher t;

            G.p("Start reading teacher file...\n");

            G.nTeacher = 0;
            try {
                while(fis.available() > 0) {
                    G.t[ G.nTeacher ] = (Teacher)ois.readObject();
                    G.nTeacher++;
                }
            } catch(ClassNotFoundException e) {
                MyError.show("Class Dont' Match for teacher!");
            }
             fis.close();

            G.p("End reading teacher file.\n");
            G.p("Total number of teacher read "+G.nTeacher+".\n");

        } catch(IOException e) {
            MyError.show("Cant' read Teacher file!\nError: "+e.getLocalizedMessage());
            MyError.show("Total number of teacher read "+G.nTeacher+".\n");
        }
    }

    public static void writeTeacher() {
        try {
            ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(G.loc+"\\"+G.name+".thr"));

            for(int k = 0; k < G.nTeacher; k++) {
                f.writeObject(G.t[k]);
                f.flush();
            }
            f.close();

        } catch(IOException e) {
            MyError.show("Teacher Write exception!\nError: "+e.getMessage());
        }
    }

    public static void readBatch() {
        try {
            FileInputStream fis = new FileInputStream(G.loc+"\\"+G.name+".bch");
            ObjectInputStream ois = new ObjectInputStream(fis);

            G.p("Start reading batch file...\n");

            G.nBatch = 0;
            try {
                while(fis.available() > 0) {
                    G.b[ G.nBatch ] = (Batch)ois.readObject();
                    G.nBatch++;
                }
            } catch(ClassNotFoundException e) {
                MyError.show("Class Dont' Match for batch!");
            }
             fis.close();

            G.p("End reading batch file.\n");
            G.p("Total number of batch read "+G.nBatch+".\n");

        } catch(IOException e) {
            MyError.show("Cant' read batch file!\nError: "+e.getLocalizedMessage());
            MyError.show("Total number of batch read "+G.nBatch+".\n");
        }
    }

    public static void writeBatch() {
        try {
            ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(G.loc+"\\"+G.name+".bch"));

            for(int k = 0; k < G.nBatch; k++) {
                f.writeObject(G.b[k]);
                f.flush();
            }
            f.close();

        } catch(IOException e) {
            MyError.show("Batch Write exception!\nError: "+e.getMessage());
        }
    }

    public static void readRoom() {
        try {
            FileInputStream fis = new FileInputStream(G.loc+"\\"+G.name+".rm");
            ObjectInputStream ois = new ObjectInputStream(fis);

            G.p("Start reading room file...\n");

            G.nRoom = 0;
            try {
                while(fis.available() > 0) {
                    G.r[ G.nRoom ] = (Room)ois.readObject();
                    G.nRoom++;
                }
            } catch(ClassNotFoundException e) {
                MyError.show("Class Dont' Match for Room!");
            }
             fis.close();

            G.p("End reading Room file.\n");
            G.p("Total number of Room read "+G.nRoom+".\n");

        } catch(IOException e) {
            MyError.show("Cant' read Room file!\nError: "+e.getLocalizedMessage());
            MyError.show("Total number of Room read "+G.nRoom+".\n");
        }
    }

    public static void writeRoom() {
        try {
            ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(G.loc+"\\"+G.name+".rm"));

            G.p("Start writing room file...\n");

            int k;
            for(k = 0; k < G.nRoom; k++) {
                f.writeObject(G.r[k]);
                f.flush();
            }
            f.close();

            G.p("End writeing Room file.\n");
            G.p("Total number of Room write "+k+".\n");

        } catch(IOException e) {
            MyError.show("Room Write exception!\nError: "+e.getMessage());
        }
    }

    public static void readCourse() {
        try {
            FileInputStream fis = new FileInputStream(G.loc+"\\"+G.name+".crs");
            ObjectInputStream ois = new ObjectInputStream(fis);

            G.p("Start reading COURSE file...\n");

            G.nCourse = 0;
            try {
                while(fis.available() > 0) {
                    G.c[ G.nCourse ] = (Course)ois.readObject();
                    G.nCourse++;
                }
            } catch(ClassNotFoundException e) {
                MyError.show("Class Dont' Match for COURSE!");
            }
             fis.close();

            G.p("End reading COURSE file.\n");
            G.p("Total number of COURSE read "+G.nCourse+".\n");

        } catch(IOException e) {
            MyError.show("Cant' read COURSE file!\nError: "+e.getLocalizedMessage());
            MyError.show("Total number of COURSE read "+G.nCourse+".\n");
        }
    }

    public static void writeCourse() {
        try {
            ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream(G.loc+"\\"+G.name+".crs"));

            G.p("Start writing COURSE file...\n");

            int k;
            for(k = 0; k < G.nCourse; k++) {
                f.writeObject(G.c[k]);
                f.flush();
            }
            f.close();

            G.p("End writeing COURSE file.\n");
            G.p("Total number of COURSE write "+k+".\n");

        } catch(IOException e) {
            MyError.show("COURSE Write exception!\nError: "+e.getMessage());
        }
    }

}