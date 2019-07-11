package com.WriteFile;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFileTest {

    @Test
    public  void OutPutStream() throws IOException {
        FileOutputStream fos= new FileOutputStream("C:\\Users\\mayn\\Desktop\\1.txt");
        fos.write(100);
        fos.close();
    }
}
