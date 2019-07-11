package com.copyfile;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class LetterDemoTest {
    public static Logger LOG = LoggerFactory.getLogger(LetterDemoTest.class);


    /**
     * 将新闻内容中含有‘任正非’的字符串标红
     */
    @Test
    public void readNews(){


        String name="任正非";
        String target = "<font color='red'>"+name+"</font>";

    }

    /**
     *
     * 练习：
     *  1.将26个大写英文字母转换为小写，然后截取g到V范围内的字母，包含g，包含V
     *  2.将得到的英文小写字母写入到text文件中
     */
    @Test
    public void LetterDemo() {

        //定义一个26个字母的字符串
        String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //转为小写
        String trans = letter.toLowerCase();
        //先去判断 letter 里面是否包含 g，v两个字母
        if(trans.contains("g") && trans.contains("v")){
           int g_index= trans.indexOf("g");
           int v_index= trans.indexOf("v");
            //截取相应的字符串
            String cutLetter = trans.substring(g_index, v_index+1);
            //创建文件对象
            File writeFile = new File("D:\\cutLetter" );
            if (!writeFile.exists()) {
                writeFile.mkdir();
            }
            File file = new File("D:\\cutLetter\\1.txt" );
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    LOG.error(file.getName()+"文件无法创建！",e);
                }
            }
            FileWriter out = null;
            try {
                out=new FileWriter(file);
                out.write(cutLetter);

            }catch (Exception e){
                LOG.error("文件找不到！",e);
            }finally {

                try {
                    if(out!=null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }else {
            LOG.error("没有字符串需要转换！");
        }





    }

}
