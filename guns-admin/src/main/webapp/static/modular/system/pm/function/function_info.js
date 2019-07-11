/**
 * 初始化我的周报详情对话框
 */
/**
 * 模块详情对话框（可用于添加和修改对话框）
 */
var funOfModuleInfoDlg = {
    funOfModuleInfoData: {},
    validateFields: {
        functionName: {
            validators: {
                notEmpty: {
                    message: '项目功能名称不能为空'
                }
            }
        },
        functionDescription: {
            validators: {
                notEmpty: {
                    message: '项目功能描述不能为空'
                }
            }
        },
        moduleId: {
            validators: {
                notEmpty: {
                    message: '所在模块不能为空'
                }
            }
        },
        projectName: {
            validators: {
                notEmpty: {
                    message: '所在项目不能为空'
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
funOfModuleInfoDlg.clearData = function() {
    this.funOfModuleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
funOfModuleInfoDlg.set = function(key, val) {
    this.funOfModuleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
funOfModuleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
funOfModuleInfoDlg.close = function() {
    parent.layer.close(window.parent.funOfModule.layerIndex);
}

/**
 * 收集数据
 */
funOfModuleInfoDlg.collectData = function() {
    this
        .set('id')
        .set('functionName')
        .set('functionDescription')
        .set('moduleName')
        .set('moduleId')
        .set('projectName')
        .set('projectId')
        .set('startTime')
        .set('endTime')
        .set('accepterEmployee')
        .set('marks')
        .set('createDate')
        .set('updateDate')
        .set('createUser')
        .set('updateUser')
}
funOfModuleInfoDlg.validate = function () {
    $('#functionInfoForm').data("bootstrapValidator").resetForm();
    $('#functionInfoForm').bootstrapValidator('validate');
    return $("#functionInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 提交添加
 */
funOfModuleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/funOfModule/add", function(data){
        if (data.msg=='200'){
            Feng.success("添加功能记录成功!");
            window.parent.module.table.refresh();
            moduleInfoDlg.close();
        } else if (data.msg=='201') {
            Feng.error("功能研发开始时间不能大于模块开始时间");
        } else if (data.msg=='202') {
            Feng.error("功能研发周期不能大于模块研发周期");
        } else if (data.msg=='203'){
            Feng.error("开始时间不能大于结束时间!");
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.funOfModuleInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
funOfModuleInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/funOfModule/update", function(data){
        if (data.msg=='200'){
            Feng.success("添加功能记录成功!");
            window.parent.module.table.refresh();
            moduleInfoDlg.close();
        } else if (data.msg=='201') {
            Feng.error("功能研发开始时间不能大于模块开始时间");
        } else if (data.msg=='202') {
            Feng.error("功能研发周期不能大于模块研发周期");
        } else if (data.msg=='203'){
            Feng.error("开始时间不能大于结束时间!");
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.funOfModuleInfoData);
    ajax.set("marks",$("#marks1 p").text());
    ajax.start();
}



$(function() {
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
    funOfModuleInfoDlg.editor = editor;

    Feng.initValidator("functionInfoForm", funOfModuleInfoDlg.validateFields);
    var flag;
    var moduleName = $("#moduleName").val();
    $("#moduleid option").each(function () {
        var text = $(this).text();
        if (text==moduleName) {
            $(this).attr("selected","selected");
            flag = $(this).attr("value");
        }
    })
    $("#moduleid option").each(function (index,elememt) {
        $("#moduleId option[value='"+flag+"']").removeAttr('selected');
        var val = $(this).val();
        $("#moduleid").change(function (){
            $("#moduleId").val($(this).val());
            $("#moduleName").val($("#moduleid option[value='"+$(this).val()+"']").text());
        });
    })

});
