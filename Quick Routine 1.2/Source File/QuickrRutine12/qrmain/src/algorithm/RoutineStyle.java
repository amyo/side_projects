package algorithm;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import global.*;

public class RoutineStyle {
/*
   static int sunMorning[] = {
       0, 1, 2, 3, 4,
       15, 16, 17,
       18, 19, 20, 21, 22,
       33, 34, 35,
       36, 37, 38, 39, 40,
       5, 14, 23, 32, 41,
       13, 31, 12, 30, 6, 11, 24, 29, 42,
       7, 10, 25, 28, 43,
       8, 9, 26, 27, 44
   };
*/
    public static int sunMornPrio[] = {
         0,  1,  2,  3,  4, 21, 30, 35, 40,
        41, 36, 31, 28, 26, 22,  5,  6,  7,
         8,  9, 10, 11, 12, 23, 32, 37, 42,
        43, 38, 33, 29, 27, 24, 13, 14, 15,
        16, 17, 18, 19, 20, 25, 34, 39, 44
    };

    public static int sunEveningPrio[] = {
       40, 35, 30, 27, 24, 19,  0,  1,  2,
        3,  4,  5,  6,  7, 20, 31, 36, 41,
       42, 37, 32, 28, 25, 21,  8,  9, 10,
       11, 12, 13, 14, 15, 22, 33, 38, 43,
       44, 39, 34, 29, 26, 23, 16, 17, 18
    };

  public RoutineStyle() {
  }

  public static int[] encode(int priority[]) {
      int index[] = new int[ priority.length ];

      for(int i = 0; i < priority.length; i++)
          index[ priority[i] ] = i;

      return index;
  }

  public static int[] getSunMornStyle() {
      return encode(sunMornPrio);
  }

  public static int[] getSunEveningStyle() {
      return encode(sunEveningPrio);
  }
}