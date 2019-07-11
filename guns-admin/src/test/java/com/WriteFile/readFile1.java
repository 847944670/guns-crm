package com.WriteFile;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class readFile1 {
    static Logger LOG = LoggerFactory.getLogger(readFile1.class);
    @Test
    public void read()  {
        FileReader fr=null;
        FileWriter fw=null;

        try {
            fr=new FileReader("d:\\1.txt");
            fw=new FileWriter("d:\\2.txt");
            char[] ch=new char[1024];
            int len=0;
            while ((len=fr.read(ch))!=-1){
                fw.write(ch,0,len);
                fw.flush();
            }

        }catch (IOException ex){
            System.out.println("复制失败 ");
        }finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                LOG.error("输入流异常！", e);
            }

            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                LOG.error("输出流异常！", e);
            }
        }
    }
}
