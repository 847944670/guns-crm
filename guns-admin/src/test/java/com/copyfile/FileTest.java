package com.copyfile;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileTest {
    static Logger LOG = LoggerFactory.getLogger(CopyFileTest.class);
    @Test
    public void  Test(){
        //文件过滤器
        File file =new File("C:\\Users\\mayn\\Desktop\\xx");
        File[] fileArr=file.listFiles(new MyFilter());
        for (File f: fileArr) {
            System.out.println(f);
        }

    }
}
