package com.stylefeng.guns.modular.pm.attachment.controller;

import com.mysql.cj.util.StringUtils;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.core.util.FileUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.pm.attachment.entity.Attachment;
import com.stylefeng.guns.modular.pm.attachment.service.AttachmentService;
import com.stylefeng.guns.modular.pm.project.entity.Project;
import com.stylefeng.guns.modular.pm.project.service.ProjectService;
import com.stylefeng.guns.modular.pm.util.PmOfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @author sunwei
 * @date 2019/6/24
 */

@Controller
@RequestMapping("/attachment")
public class AttachmentController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AttachmentController.class);
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private ProjectService projectService;



    /**
     * 上传图片
     */
    @RequestMapping(method = RequestMethod.POST, path = "/fileUpload")
    @ResponseBody
    public Map<String,Object> upload(@RequestPart("file") MultipartFile file, HttpServletRequest request){
        HashMap<String,Object> map = new HashMap<>();
        String fileSavePath="";
        String operationSystem = PmOfUtil.getOperationSystem();
        if(operationSystem.toLowerCase().startsWith("windows")){
            fileSavePath="D:\\attachment\\";

        }else {
            fileSavePath="/home/spider/service-crm/file";
        }

        File file1 = new File(fileSavePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //设置文件夹权限
        PmOfUtil.setAuthority(file1);
        String fileName = DateUtil.format(new Date(), "yyyyMMddHHmmss")+"."+ ToolUtil.getFileSuffix(file.getOriginalFilename());
        Attachment attachment = new Attachment();
        attachment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        attachment.setAttachmentName(fileName);
        attachment.setSourceAttachmentName(file.getOriginalFilename());
        attachment.setFilePath(fileSavePath+fileName);
        PmOfUtil.setDefaultValue(attachment);

        try {
            file.transferTo(new File(fileSavePath+fileName));
            attachmentService.insert(attachment);
            map.put("msg",fileName);
        } catch (Exception e) {
            map.put("msg", "202");
        }
        return map;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String attachName, String type){
        Map<String,String> map = new HashMap<>();
        Map<String,Object> param = new HashMap<>();
        param.put("attachmentName", attachName);
        //删除磁盘文件部分
        List<Attachment> attachments = attachmentService.selectByMap(param);
        if (attachments.size()==1){
            Attachment attachment = attachments.get(0);
            FileUtil.deleteDir(new File(attachment.getFilePath()));
            if (type.equals("edit")){
                //删除project中attachmentName中包含的attachName信息
                String projectId = attachment.getProjectId();
                if(!StringUtils.isNullOrEmpty(projectId)){
                    Project project = projectService.selectById(projectId);
                    String attachmentNames = project.getAttachmentName();
                    String fileIds = project.getFileId();

                    //删除sys_pm_project表中的attachmentName
                    String attachmentName = returnAttmentNames(attachmentNames, attachName);
                    project.setAttachmentName(attachmentName);



                    //删除sys_pm_project表中的fileId
                    String fileId = returnFileIdS(fileIds, attachmentNames, attachName);
                    project.setFileId(fileId);

                    project.setAttachmentName(attachmentName);
                    project.setFileId(fileId);
                    projectService.updateById(project);
                    map.put("msg", "success");
                }else {
                    map.put("msg", "success");
                }

            }
            attachmentService.deleteByMap(param);
        }
        return map;
    }
    /**
     * 通过下标删除下标所在的attachmentNames返回处理过的attachmentname字符串
     * @return
     */
    public String returnAttmentNames(String attachmentNames,String attachName){
        Assert.notNull(attachmentNames, "数据库中attachmentname字段为空！");
        Assert.notNull(attachName, "attachName不存在！");
        if(attachmentNames.contains(",")){
            List<String> list = Arrays.asList(attachmentNames.split(","));
            List<String> attachmentNameList = new ArrayList<>();
            for (String item:list){
                attachmentNameList.add(item);
            }
            int i = returnIndex(attachmentNames, attachName);
            attachmentNameList.remove(i);
            return listAsString(attachmentNameList);
        }
        else {
            return "";
        }

    }

    /**
     * 将list集合变为字符串
     * @return
     */
    public String listAsString(List<String> list){
        Assert.notNull(list, "集合对象为空");
        String string = "";
        if(list.size()==1){
            return list.get(0);
        }else if(list.size()>1) {
            for (int i = 0 ; i< list.size() ; i++){
                string=string+","+list.get(i);
            }
            return string.substring(1, string.length());
        }else {
            return string;
        }
    }
    /**
     * 通过下标删除下标所在的fileId返回处理过的fileIds字符串
     * @param fileIds 删除之前的
     * @return
     */
    public String returnFileIdS(String fileIds,String attachmentNames,String attachName){
        if (fileIds.contains(",")) {
            Assert.notNull(attachmentNames, "数据库中attachmentname字段为空！");
            Assert.notNull(attachName, "attachName不存在！");
            Assert.notNull(fileIds, "数据库中fileId字段为空！");
            List<String> fileIdList = new ArrayList<>();
            List<String> list = Arrays.asList(fileIds.split(","));
            for (String item : list) {
                fileIdList.add(item);
            }
            fileIdList.remove(fileIdList.get(returnIndex(attachmentNames, attachName)));
            return listAsString(fileIdList);
        }else {
            return "";
        }

    }

    /**
     * 返回attachName所在的下标位置
     * 因为attachmentName与fileId是一一对应的所以通过attachName在attachmentName的下标得到fileIds的下标位置，删除fileId
     * @param attachmentNames 多个附件名称的字符串
     * @param attachName 单个附件名称
     * @return
     */
    private int returnIndex(String attachmentNames,String attachName) {
        int index = 0;
        //1.将attachmentNames字符串转换为数组
        List<String> attachmentNameList = Arrays.asList(attachmentNames.split(","));
        for (int i = 0; i < attachmentNameList.size(); i++) {
            if (attachName.equals(attachmentNameList.get(i))) {
                index = i;
            } else {
                continue;
            }
        }
        return index;
    }
}