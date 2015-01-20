package datastructure;

import java.io.*;

public class RoutinePath implements Serializable{
  public String name="";
  public String loc="";
  public boolean helpFrame=false;
  public RoutinePath(String name,String loc){
    this.name=name;
    this.loc=loc;
  }
}