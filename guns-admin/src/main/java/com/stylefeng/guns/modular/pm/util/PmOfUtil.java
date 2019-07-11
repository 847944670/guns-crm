package com.stylefeng.guns.modular.pm.util;

import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.pm.attachment.entity.Attachment;
import com.stylefeng.guns.modular.pm.function.entity.Function;
import com.stylefeng.guns.modular.pm.module.entity.Module;
import com.stylefeng.guns.modular.pm.project.entity.Project;
import com.stylefeng.guns.modular.pm.task.entity.TaskEntity;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sunwei
 * @date 2019/6/15
 */
@Configuration
public class PmOfUtil {
    private final static String ERRORCODE="500";
    @Value("${spring.mail.username}")
    private static String username;
    @Value("${spring.mail.password}")
    private static String password;
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r";// 定义空格回车换行符

    public static String getText(String htmlStr){

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签


        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签 = m_space.replaceAll(""); // 过滤空格回车标签

        return htmlStr;
    }
    /**
     * 校验发送方mail账户是否授权
     * @return
     */
    public static String getAuthorizedMail(){
        String authizationEmail = PmOfUtil.username;
        String authizationPassword =PmOfUtil.password;
        if (authizationEmail==null && authizationPassword==null){
            return ERRORCODE;
        }
        else {
            return authizationEmail;
        }

    }
    /**
     * 比较项目开始时间大于模块开始时间（比较模块开始时间大于功能开始时间）
     * @param lastStartTime
     * @param nextStartTime
     * @return
     */
    public static boolean compareStartTime(String lastStartTime,String nextStartTime){
        long lastTime = DateUtil.parseDate(lastStartTime).getTime();
        long nextTime = DateUtil.parseDate(nextStartTime).getTime();
        if(nextTime<lastTime){
            return false;
        }
        return true;
    }
    /**
     * 研发周期比较
     * @param lastTime
     * @param nextTime
     * @return
     */
    public static boolean compareTime(String lastTime,String nextTime){
        if(Integer.valueOf(nextTime) > Integer.valueOf(lastTime.replaceAll("天", ""))){
            return false;
        }else {
            return true;
        }
    }
    /**
     * 项目延时判断 isDelay
     * @return 延时天数
     */
    public static void getDelayTime(TaskEntity taskEntity,Project project, Module module,Function function){
        String status = taskEntity.getStatus();
        String statusOfTest = taskEntity.getStatusOfTest();
        //正在进行中的任务
        if(status.equals("0")){
            //当前时间
            String todayTime = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
            //项目结束时间
            String endTimeOfProject = project.getEndTime();
            //模块结束时间
            String endTimeOfModule = module.getEndTime();


            if(DateUtil.parseDate(todayTime).getTime()<DateUtil.parseDate(endTimeOfProject).getTime()){
                taskEntity.setDelayDayOfProject("0天");
                taskEntity.setDelayOfProject(false);
            }else {
                taskEntity.setDelayOfProject(true);
                long daySub = DateUtil.getDaySub(endTimeOfProject, todayTime);
                taskEntity.setDelayDayOfProject(daySub+"天");
            }if (DateUtil.parseDate(todayTime).getTime()<DateUtil.parseDate(endTimeOfModule).getTime()){
                taskEntity.setDelayDayOfModule("0天");
                taskEntity.setDelayOfModule(false);
            }else {
                taskEntity.setDelayOfModule(true);
                long daySub = DateUtil.getDaySub(endTimeOfModule, todayTime);
                taskEntity.setDelayDayOfModule(daySub+"天");
            }if (function!=null){
                //功能结束时间
                String endTimeOfFunction = function.getEndTime();
                if (DateUtil.parseDate(todayTime).getTime()<DateUtil.parseDate(endTimeOfFunction).getTime()){
                    taskEntity.setDelayDayOfFunction("0天");
                    taskEntity.setDelayOfFunction(false);
                }else {
                    taskEntity.setDelayOfFunction(true);
                    long daySub = DateUtil.getDaySub(endTimeOfFunction, todayTime);
                    taskEntity.setDelayDayOfFunction(daySub+"天");
                }
            }
            //正在进行中的测试任务
        }else if(statusOfTest.equals("1")){

        }

    }

    /**
     * 校验开始时间是否大于结束时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static boolean compareDate(String startTime,String endTime){
        if(DateUtil.compareDate(startTime, endTime)) {

            return true;
        }else {
            return false;
        }
    }

    /**
     * 设置项目属性的默认值
     * @param project
     */
    public static void setDefaultValue(Project project){
        Assert.notNull(project);
        project.setCreateDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
        project.setUpdateDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
        String account = ShiroKit.getUser().getAccount();
        project.setCreateUser(account);
        project.setUpdateUser(account);
    }
    /**
     * 设置附件属性的默认值
     * @param attachment
     */
    public static void setDefaultValue(Attachment attachment){
        Assert.notNull(attachment);
        attachment.setCreateDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
        attachment.setUpdateDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
        String account = ShiroKit.getUser().getAccount();
        attachment.setCreateUser(account);
        attachment.setUpdateUser(account);
    }


    /**
     * 设置对象默认值
     * @param module
     */
    public static void setDefaultValue(TaskEntity taskUser , Module module, Function function){
        Assert.notNull(taskUser);
        taskUser.setCreateDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
        taskUser.setUpdateDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
        String account = ShiroKit.getUser().getAccount();
        taskUser.setCreateUser(account);
        taskUser.setUpdateUser(account);
        if(module!=null){
            module.setTime(module.getTime());
        }
        else {
            function.setTime(function.getTime());
        }
    }

    /**
     * 设置功能对象默认值
     * @param function
     */
    public static void setDefaultValue(Function function){
        Assert.notNull(function);
        function.setCreateDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
        function.setUpdateDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
        String account = ShiroKit.getUser().getAccount();
        function.setCreateUser(account);
        function.setUpdateUser(account);

    }

    /**
     * 设置模块对象默认值
     * @param module
     */
    public static void setDefaultValue(Module module){
        Assert.notNull(module);
        module.setCreateDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
        module.setUpdateDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm"));
        String account = ShiroKit.getUser().getAccount();
        module.setCreateUser(account);
        module.setUpdateUser(account);

    }
    private static Logger LOG = LoggerFactory.getLogger(PmOfUtil.class);

    /**
     * 下载附件
     * @param s
     * @param sourceFileName
     * @param fileName
     * @return
     */
    public static Map<String,String> downLoad(String s, String sourceFileName,String fileName) {
        Map<String,String> map = new HashMap<>();
        if(sourceFileName.contains(".")){
            sourceFileName =sourceFileName.substring(0,sourceFileName.indexOf(".")) ;
        }
        String downloadFilePath = "D:\\attachment\\"+sourceFileName;
        File file1 = new File(downloadFilePath);
        if (isCreatFile(file1)){
           LOG.info("文件夹已存在！");
        }
        File file = new File(s);
        //设置文件夹权限
        PmOfUtil.setAuthority(file);
        PmOfUtil.setAuthority(file1);
        FileInputStream inputStream =  null;
        FileOutputStream outputStream = null;

       try {
           inputStream=  new FileInputStream(file);

           outputStream = new FileOutputStream(downloadFilePath+"\\"+fileName);
           byte[] byteArr = new byte[1024];
           int len=0;
           while ((len=inputStream.read(byteArr))!=-1){
               outputStream.write(byteArr, 0,len);
           }
       } catch (IOException e) {
           map.put("msg", "error");
           LOG.error("文件找不到", e);
       }
       map.put("msg", "200");
       map.put("path", "文件位置："+downloadFilePath+"\\"+fileName);
       return map;
    }

    private static boolean isCreatFile(File file1) {
        if (!file1.exists()) {
            try {
                file1.mkdirs();

            } catch (Exception e) {
                LOG.error("文件夹创建失败！", e);
            }
            return false;
        }
        else {

            return true;
        }
    }

    /**
     * 获取操作系统
     * @return
     */
    public static String getOperationSystem(){
        return System.getProperty("os.name");
    }

    /**
     * 获取用户主目录
     * @return
     */
    public static String dir(){
        return System.getProperty("user.home");
    }

    public static void setAuthority(File file){
        file.setExecutable(true,false);//设置可执行权限
        file.setReadable(true,false);//设置可读权限
        file.setWritable(true,false);//设置可写权限
    }

}