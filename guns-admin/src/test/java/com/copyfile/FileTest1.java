package com.copyfile;

import java.io.File;

public class FileTest1 {


    public static void main(String[] args) {
        File dir = new File("d:\\wechat");
        getAllDir(dir);
    }

    /*
     *  定义方法,实现目录的全遍历
     */
    public static void getAllDir(File dir) {
        System.out.println(dir);
        //调用方法listFiles()对目录,dir进行遍历
        File[] fileArr = dir.listFiles();
        for (File f : fileArr) {
            //判断变量f表示的路径是不是文件夹
            if (f.isDirectory()) {
                //是一个目录,就要去遍历这个目录
                //本方法,getAllDir,就是给个目录去遍历
                //继续调用getAllDir,传递他目录
                getAllDir(f);
            } else {
                System.out.println(f);
            }
        }
    }
}

