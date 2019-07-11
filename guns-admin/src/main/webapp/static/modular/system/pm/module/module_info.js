/**
 * 模块详情对话框（可用于添加和修改对话框）
 */
var moduleInfoDlg = {
    moduleInfoData: {},
    validateFields: {
        moduleName: {
            validators: {
                notEmpty: {
                    message: '模块名称不能为空'
                }
            }
        },
        projectName: {
            validators: {
                notEmpty: {
                    message: '项目名称不能为空'
                }
            }
        },
        moduleDescription: {
            validators: {
                notEmpty: {
                    message: '模块描述不能为空'
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
moduleInfoDlg.clearData = function() {
    this.moduleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
moduleInfoDlg.set = function(key, val) {
    this.moduleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}


/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
moduleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
moduleInfoDlg.close = function() {
    parent.layer.close(window.parent.module.layerIndex);
}

/**
 * 收集数据
 */
moduleInfoDlg.collectData = function() {

    this.set('id')
        .set('moduleName')
        .set("projectId")
        .set('projectName')
        .set('moduleDescription')
        .set('startTime')
        .set('endTime')
        .set('time')
        .set('marks')
        .set('createDate')
        .set('updateDate')
        .set('createUser')
        .set('updateUser');

}
/**
 * 验证数据是否为空
 */
moduleInfoDlg.validate = function () {
    $('#moduleInfoForm').data("bootstrapValidator").resetForm();
    $('#moduleInfoForm').bootstrapValidator('validate');
    return $("#moduleInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 提交添加
 */
moduleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/module/add", function(data){
        if (data.msg=='200'){
            Feng.success("添加模块记录成功!");
            window.parent.module.table.refresh();
            moduleInfoDlg.close();
        } else if (data.msg=='201') {
            Feng.error("模块研发开始时间不能大于项目开始时间");
        } else if (data.msg=='202') {
            Feng.error("模块研发周期不能大于项目研发周期");
        } else if (data.msg=='203'){
            Feng.error("开始时间不能大于结束时间!");
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.moduleInfoData);
    ajax.start();
}
/**
 * 提交修改
 */
moduleInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/module/update", function(data){
        if (data.msg=='200'){
            Feng.success("修改成功!");
            window.parent.module.table.refresh();
            moduleInfoDlg.close();
        } else if (data.msg=='201') {
            Feng.error("模块研发开始时间不能大于项目开始时间");
        } else if (data.msg=='202') {
            Feng.error("模块研发周期不能大于项目研发周期");
        } else if (data.msg=='203'){
            Feng.error("开始时间不能大于结束时间!");
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.moduleInfoData);
    ajax.set("marks",$("#marks1 p").text());
    ajax.start();
}

function fun(val){
    alert(val)
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
    moduleInfoDlg.editor = editor;

    Feng.initValidator("moduleInfoForm", moduleInfoDlg.validateFields);
    var flag;
    var projectName = $("#projectName").val();
    $("#projectid option").each(function () {
        var text = $(this).text();
        if (text==projectName) {
            $(this).attr("selected","selected");
            flag = $(this).attr("value");
        }
    })
    $("#projectid option").each(function (index,elememt) {
        $("#projectId option[value='"+flag+"']").removeAttr('selected');
        var val = $(this).val();
        $("#projectid").change(function (){
            $("#projectId").val($(this).val());
            $("#projectName").val($("#projectid option[value='"+$(this).val()+"']").text());
        });


    })
});
