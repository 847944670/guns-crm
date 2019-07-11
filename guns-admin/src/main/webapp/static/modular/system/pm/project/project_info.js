/**
 * 初始化我的周报详情对话框
 */
var projectInfoDlg = {
    projectInfoData: {},
    validateFields: {

        projectName: {
            validators: {
                notEmpty: {
                    message: '项目名称不能为空'
                }
            }
        },
        projectDescription: {
            validators: {
                notEmpty: {
                    message: '项目描述不能为空'
                }
            }
        },
        startTime: {
            validators: {
                notEmpty: {
                    message: '项目开始时间不能为空'
                }

            }
        },
        endTime: {
            validators: {
                notEmpty: {
                    message: '项目结束时间不能为空'
                }

            }
        }
    }
};


/**
 * 清除数据
 */
projectInfoDlg.clearData = function() {
    this.projectInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
projectInfoDlg.set = function(key, val) {
    this.projectInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
projectInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
projectInfoDlg.close = function() {
    parent.layer.close(window.parent.project.layerIndex);
}

/**
 * 收集数据
 */
projectInfoDlg.collectData = function() {
    this.set('id')
    .set('projectName')
    .set('projectDescription')
    .set('startTime')
    .set('endTime')
    .set('time')
    .set('attachmentName')
    .set('fileId')
    .set('marks')
    .set('createDate')
    .set('updateDate')
    .set('createUser')
    .set('updateUser');
}
/**
 * 验证数据是否为空
 */
projectInfoDlg.validate = function () {
    $('#projectInfoForm').data("bootstrapValidator").resetForm();
    $('#projectInfoForm').bootstrapValidator('validate');
    return $("#projectInfoForm").data('bootstrapValidator').isValid();
};
projectInfoDlg.fileUpload=function(){

}
/**
 * 提交添加
 */
projectInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/project/add", function(data){
        if (data.msg =='success' ){
            Feng.success("添加成功!");
            window.parent.project.table.refresh();
            projectInfoDlg.close();
        } else {
            Feng.error("项目开始时间不能大于项目结束时间");
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.projectInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
projectInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/project/update", function(data){
        if (data.msg =='success' ){
            Feng.success("修改成功!");
            window.parent.project.table.refresh();
            projectInfoDlg.close();
        } else {
            Feng.error("项目开始时间不能大于项目结束时间");
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.projectInfoData);
    ajax.set("marks",$("#marks1 p").text());
    ajax.start();
}

$(function() {
    Feng.initValidator("projectInfoForm", projectInfoDlg.validateFields);
    var E = window.wangEditor;
    var editor = new E('#marks1');
    var $marks = $('#marks')
    editor.customConfig.onchange = function (html) {
        var h = editor.txt.html(html);
        var text = editor.txt.text(h);
        // 监控变化，同步更新到 textarea
        $marks.val(text)
    }
    editor.create()
    editor.txt.text()
    projectInfoDlg.editor = editor;

    var fileUpload = new $FileUpload("ctl");
    fileUpload.setUploadBarId($("#uploadBarId"));
    fileUpload.init();

});
