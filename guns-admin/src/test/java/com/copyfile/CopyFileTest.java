package com.copyfile;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;


public class CopyFileTest {
    static Logger LOG = LoggerFactory.getLogger(CopyFileTest.class);
    //获取单个文件
    @Test
    public void copySingleFile(){
        //创建一个xx文件夹对象
        File readSingleFile = new File("C:/Users/mayn/Desktop/xx");
        //获取xx文件夹下的所有所有文件装配到文件数组对象中去
        File[] files = readSingleFile.listFiles();

       /* for (File file:files ) {

        }*/
       //在D盘下创建copySingle文件夹对象
        File writeSingleFile = new File("D:\\copySingle");
        //判断文件夹是否存在，不存在创建目录
        if(!writeSingleFile.exists()){
            //将创建好的文件夹对象创建成文件夹
            writeSingleFile.mkdir();
        }
        FileOutputStream out =null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(readSingleFile);
            out=new FileOutputStream(writeSingleFile+"\\"+readSingleFile.getName());
            byte[] arr1= new byte[2048];
            int length=0;
            while ((length = in.read(arr1)) != -1) {
                out.write(arr1,0,length);
            }

        }catch (Exception e){
            LOG.error("文件找不到！",e);
        }finally {

                try {
                    if(in!=null) {
                    in.close();
                    }
                    if(out!=null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }





    }

    @Test
    public void copyFileTest() {
        //获取桌面的文件夹
        File readFileOfXX = new File("C:/Users/mayn/Desktop/xx");
        HashMap<String,Object> item = CopyFileTest.getFiles(readFileOfXX);
        if(item.get("msg").equals("400")){
            LOG.info(readFileOfXX+"文件夹为空！");
        }else {
            File[] files = (File[]) item.get("msg");
            if(files.length==0){
                LOG.info(readFileOfXX+"文件夹为空！");
            }else {
                for (File f : files) {
                    File readFile = new File(f.getAbsolutePath());
                    File outFile = new File("D:\\copyFile");
                    if (!outFile.exists()) {
                        outFile.mkdir();
                        LOG.info(outFile.getName() + "创建成功");
                    }
                    //输入流
                    FileInputStream in = null;
                    //输出流
                    FileOutputStream out = null;
                    try {
                        in = new FileInputStream(readFile);
                        out = new FileOutputStream(outFile + "\\" + readFile.getName());
                        byte[] arr = new byte[2];
                        int len = 0;
                        while ((len = in.read(arr)) != -1) {
                            out.write(arr, 0, len);
                        }
                    } catch (Exception e) {
                        LOG.error("文件不存在！", e);
                    } finally {
                        try {
                            if (in != null) {
                                in.close();
                            }
                        } catch (Exception e) {
                            LOG.error("输入流异常！", e);
                        }

                        try {
                            if (out != null) {
                                out.close();
                            }
                        } catch (Exception e) {
                            LOG.error("输出流异常！", e);
                        }
                    }
                }
            }
        }

    }

    /**
     * 获取文件列表
     * @param readFile 文件夹
     * @return
     */
    public static HashMap<String,Object> getFiles(File readFile) {
        HashMap<String,Object> map = new HashMap<>();
        if (readFile.exists()) {
            File[] files = readFile.listFiles();
            map.put("msg",files);
        }else{
            map.put("msg","400");
        }
        return map;
    }

}



