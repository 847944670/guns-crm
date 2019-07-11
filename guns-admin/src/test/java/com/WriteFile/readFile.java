package com.WriteFile;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

public class readFile {
    @Test
    public void read() throws IOException {
        FileReader fr= new FileReader("d:\\1.txt");
      //  int len = 0;
      /*  while ((len=fr.read())!=-1){
            System.out.print((char)len);
        }*/
      char[] ch= new char[1024];
      int len=0;
      while ((len=fr.read())!=-1){
          System.out.println(new String(ch,0,len));
      }
      fr.close();
    }
}
