package com.stylefeng.guns.modular.pm.project.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.pm.attachment.entity.Attachment;
import com.stylefeng.guns.modular.pm.attachment.service.AttachmentService;
import com.stylefeng.guns.modular.pm.function.entity.Function;
import com.stylefeng.guns.modular.pm.function.service.FunctionService;
import com.stylefeng.guns.modular.pm.module.entity.Module;
import com.stylefeng.guns.modular.pm.module.service.ModuleService;
import com.stylefeng.guns.modular.pm.project.entity.Project;
import com.stylefeng.guns.modular.pm.project.service.ProjectService;
import com.stylefeng.guns.modular.pm.util.PmOfUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.*;

/**
 * @author sunwei
 * @date 2019/5/29
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {
    private String PREFIX = "/system/pm/project/";

    private static Logger LOG = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private AttachmentService attachmentService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"project.html";
    }
    /**
     * 获取我的项目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String projectName){
        if(projectName==null){

            List<Project> projects = projectService.selectList(null);
            LOG.info("获取我的项目列表-->{}",projects);
            return projects;
        }else {
            LOG.info("查询参数为：{}", projectName);
            List<Project> projects =projectService.selectListCondition(projectName);
            LOG.info("获取我的项目列表-->{}",projects);
            return projects;
        }
    }
    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/project_add")
    public String projectAdd() {

        return PREFIX + "project_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/project_update/{projectId}")
    public String projectUpdate(@PathVariable String projectId, Model model) {
        Project project = projectService.selectById(projectId);
        model.addAttribute("item",project);
        String div = getDiv(project);
        if(div!=null){
            model.addAttribute("html",div);
        }
        LogObjectHolder.me().set(project);
        return PREFIX + "project_edit.html";
    }

    private String getDiv(Project project){
        String html="";
        Map<String, String> map = getData(project);
        if(map.containsKey("msg")){
            html= "";
        }else {
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                Map<String, Object> param = new HashMap<>();
                param.put("sourceAttachmentName", next.getValue());
                String attachmentName = attachmentService.selectByMap(param).get(0).getAttachmentName();
                String name = "\'" + attachmentName + "\'";
                html = html + "<div id=" + next.getKey() + " class='item'>" +
                        "<h4 class='info'>" + next.getValue() + "</h4>" +
                        "<font color='#00008b' >" +
                        " <a  href='javascript:void(0);'  style='text-decoration:underline;' " +
                        " onclick=\"deleteFile(" + next.getKey() + "," + name + ")\">" +
                        "<span class='glyphicon glyphicon-paperclip' aria-hidden='true'></span> 删除附件 " +
                        "</a>" +
                        "</font>" +
                        "</div>";


            }
        }
        return html;
    }

    private Map<String,String> getData(Project project) {
        Map<String,String> map = new HashMap<>();
        if(project.getFileId().equals("")){
            map.put("msg", "null");
            return map;
        }else {
            List<String> fileIds = Arrays.asList(project.getFileId().split(","));
            List<String> attachments = Arrays.asList(project.getAttachmentName().split(","));
            if(fileIds.size()==attachments.size()){
                for (int i=0 ; i< fileIds.size() ; i++){
                    for (int j = 0 ; j <attachments.size() ; j++){
                        if (i==j){
                            HashMap<String, Object> param= new HashMap<>();
                            param.put("attachmentName", attachments.get(i));
                            List<Attachment> attachments1 = attachmentService.selectByMap(param);
                            Attachment attachment = attachments1.get(0);
                            String sourceAttachmentName = attachment.getSourceAttachmentName();
                            if (StringUtils.isEmpty(sourceAttachmentName)){
                                map.put("msg", "null");
                            }else {
                                map.put(fileIds.get(i), sourceAttachmentName);
                            }
                        }
                    }
                }
            }
            return map;
        }

    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Project project) {
        project.setId(UUID.randomUUID().toString().substring(0, 31).replaceAll("-","" ));
        Map<String,String> map = new HashMap<>();
        PmOfUtil.setDefaultValue(project);
        String startTime = project.getStartTime();
        String endTime = project.getEndTime();
        if (!PmOfUtil.compareDate(startTime, endTime)){
            long daySub = DateUtil.getDaySub(startTime, endTime);
            project.setTime(daySub+"天");
            String content= HtmlUtils.htmlUnescape(project.getMarks());
            project.setMarks(content);
            projectService.insert(project);
            String attachmentName = project.getAttachmentName();
            if (!StringUtils.isEmpty(attachmentName)){
                if( attachmentName.contains(",")){
                    List<String> list = Arrays.asList(project.getAttachmentName().split(","));
                    for (int i = 0 ; i<list.size() ; i ++){
                        HashMap<String, Object> parm = new HashMap<>();
                        parm.put("attachmentName", list.get(i));
                        List<Attachment> attachments = attachmentService.selectByMap(parm);
                        Attachment attachment = attachments.get(0);
                        attachment.setProjectId(project.getId());
                        attachmentService.updateById(attachment);
                    }

                }else {

                    HashMap<String, Object> parm = new HashMap<>();
                    parm.put("attachmentName", attachmentName);
                    List<Attachment> attachments = attachmentService.selectByMap(parm);
                    Attachment attachment = attachments.get(0);
                    attachment.setProjectId(project.getId());
                    attachmentService.updateById(attachment);

                }
            }


            map.put("msg", "success");
        }else {

            map.put("msg", "error");
        }


        return map;


    }




    /**
     * 删除我的项目
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String projectId) {
        List<Module> moduleList= moduleService.selectByProjectId(projectId);
        if(moduleList.size()>0){
            for (Module module: moduleList) {
                if (module.getProjectId().equals(projectId)){
                    deleteFunctionOfModule(module);
                    moduleService.deleteById(module.getId());

                }else {
                    continue;
                }
            }
        }
        projectService.deleteById(projectId);
        return SUCCESS_TIP;
    }

    private void deleteFunctionOfModule(Module module) {
        List<Function> functionList= functionService.selectByModuleId(module.getId());
        if (functionList.size()>0){
            for (Function function:functionList) {
                if(function.getModuleId().equals(module.getId())){
                    functionService.deleteById(function.getId());
                }else {
                    continue;
                }
            }
        }
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Project project) {
        Map<String,String> map = new HashMap<>();
        PmOfUtil.setDefaultValue(project);
        String startTime = project.getStartTime();
        String endTime = project.getEndTime();

        if (!PmOfUtil.compareDate(startTime, endTime)){
            long daySub = DateUtil.getDaySub(startTime, endTime);
            project.setTime(daySub+"天");

            Project project1 = projectService.selectById(project.getId());
            String attachmentName = project.getAttachmentName();
            String attachmentNames = project1.getAttachmentName();
            String fileId = project.getFileId();
            String fileIds = project1.getFileId();
            List<String> attachmentNameList = parseString(attachmentNames);
            for (String attachment: attachmentNameList ) {
                attachmentName  = attachmentName+","+attachment;
            }
            List<String> fileIdList = parseString(fileIds);
            for (String fId:fileIdList ) {
                fileId=fileId+","+fId;
            }
            project.setAttachmentName(attachmentName);
            project.setFileId(fileId);

            projectService.updateById(project);
            Map<String , Object> param = new HashMap<>();
            List<String> attachNames = parseString(attachmentName);
            for (String attachName:attachNames) {
                param.put("attachmentName",attachName);
                List<Attachment> attachments = attachmentService.selectByMap(param);
                for (Attachment attachment:attachments){
                    if(StringUtils.isEmpty(attachment.getProjectId())||attachment.getProjectId()==null){
                        attachment.setProjectId(project.getId());
                        attachmentService.updateById(attachment);
                    }
                }
            }
            map.put("msg", "success");
            return map;
        }else {

            map.put("msg", "error");
            return map;
        }

    }

    private List<String> parseString(String item) {
        List<String> fileIdList = Arrays.asList(item.split(","));

        return fileIdList;
    }

}