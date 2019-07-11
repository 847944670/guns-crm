package com.copyfile;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class readFileTest {
    static Logger Log = LoggerFactory.getLogger(readFileTest.class);

    @Test
    public void readNews() {


        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        try {
            File file = new File("C:\\Users\\mayn\\Desktop\\1.doc");
            inputStreamReader=new InputStreamReader(new FileInputStream(file),"utf-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            int line = 0;
            //一次读一行，读入null时文件结束
            while ((tempString = reader.readLine()) != null) {
            //把当前行号显示出来
                System.out.println(tempString);
                if(tempString.contains("任正非")){
                    //获取索引
                    int target=tempString.indexOf("任正非");
                    System.out.println(target);
                    //截取“任正非”三个字
                     String targetLetter=tempString.substring(target,target+3);
                     //进行标红
                     targetLetter="<font color='red'>"+targetLetter+"</font>";
                     String newString = tempString.replace("任正非",targetLetter);
                    FileWriter out = new FileWriter("C:\\Users\\mayn\\Desktop\\1.doc");
                    out.write(newString,0,newString.length());
                     // FileWriter fw = new FileWriter("C:\\Users\\mayn\\Desktop\\1.txt");
                    //fw.write(newString);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
