package com.thread;

import com.stylefeng.guns.modular.pm.util.PmOfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author sunwei
 * @date 2019/7/4
 */
public class MyThreadTest implements Runnable{

    static Logger logger = LoggerFactory.getLogger(MyThreadTest.class);
    public static void main(String[] args) {
        /**
         * param:
         *  --int corePoolSize,核心线程数量
         *  --int maximumPoolSize,最大线程数量
         *  --long keepAliveTime,线程存货时间
         *  --TimeUnit unit,参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：
         *          TimeUnit.DAYS;               //天
         *          TimeUnit.HOURS;             //小时
         *          TimeUnit.MINUTES;           //分钟
         *          TimeUnit.SECONDS;           //秒
         *          TimeUnit.MILLISECONDS;      //毫秒
         *          TimeUnit.MICROSECONDS;      //微妙
         *          TimeUnit.NANOSECONDS;       //纳秒
         *  --BlockingQueue<Runnable> workQueue
         *  一个阻塞队列，用来存储等待执行的任务，
         *  这个参数的选择也很重要，会对线程池的运行过程产生重大影响，
         *  一般来说，这里的阻塞队列有以下几种选择：
         *          ArrayBlockingQueue;
         *          LinkedBlockingQueue;
         *          SynchronousQueue;
         */

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,15,200,
                TimeUnit.MILLISECONDS,  new ArrayBlockingQueue<Runnable>(5));
        Date startTime = new Date();
        MyThreadTest task = new MyThreadTest();
                executor.execute(task);
        Date endTime = new Date();
        System.out.println("使用多线程下载时长："+(endTime.getTime()- startTime.getTime()));
        executor.shutdown();
        MyThreadTest task1 = new MyThreadTest();
        Date startTime1 = new Date();
        task1.downLoadFile();
        Date endTime1 = new Date();
        System.out.println("普通程序下载时长："+(endTime1.getTime()- startTime1.getTime()));
    }

    public void downLoadFile(){
        File attachmentDir = new File("D:\\attachment\\");
        //获取目录中的所有文件
        File[] files = attachmentDir.listFiles();
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        for (int i = 0; i <files.length ; i++) {
            String path = files[i].getAbsolutePath();
            File sourcefile = new File(path);
            File targetFile = new File("D:\\attachmentCopy\\");
            if (!targetFile.exists()){
                targetFile.mkdir();
            }
            PmOfUtil.setAuthority(targetFile);
            PmOfUtil.setAuthority(sourcefile);
            try {
                fileInputStream = new FileInputStream(sourcefile);

                fileOutputStream = new FileOutputStream(targetFile+"\\"+ files[i].getName()+".xls");

                byte[] byteArr = new byte[2048];
                int len=0;
                while ((len=fileInputStream.read(byteArr))!=-1){
                    fileOutputStream.write(byteArr, 0, len);
                }

            }catch (Exception e){
                logger.error("文件找不到！",e);
            }finally {

                if (fileOutputStream!=null){
                    try {
                        fileOutputStream.close();

                    }catch (Exception e){
                        logger.error("输入流没有关闭！",e);
                    }
                }
                if (fileInputStream!=null){
                    try {
                        fileInputStream.close();

                    }catch (Exception e){
                        logger.error("输出流没有关闭！",e);
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        downLoadFile();
    }
}
