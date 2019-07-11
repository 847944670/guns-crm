/**
 * 初始化我的周报详情对话框
 */
var taskTestEndInfoDlg = {
    taskTestEndInfoData : {}
};

/**
 * 清除数据
 */
taskTestEndInfoDlg.clearData = function() {
    this.taskTestEndInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
taskTestEndInfoDlg.set = function(key, val) {
    this.taskTestEndInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
taskTestEndInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
taskTestEndInfoDlg.close = function() {
    var frameIndex = parent.layer.getFrameIndex(window.name);
    parent.layer.close(frameIndex);
}

/**
 * 收集数据
 */
taskTestEndInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('employeeRole')
    .set('deptName')
    .set('projectName')
    .set('moduleName')
    .set('funName')
    .set('projectId')
    .set('moduleId')
    .set('funId')
    .set('projectTime')
    .set('moduleTime')
    .set('functionTime')
    .set('isDelayOfProject')
    .set('isDelayOfModule')
    .set('functionTime')
    .set('isDelayOfFunction')
    .set('delayDayOfProject')
    .set('delayDayOfModule')
    .set('delayDayOfFunction')
    .set('status')
    .set('statusName')
    .set('statusOfTest')
    .set('statusOfTestName')
    .set('statusOfOperation')
    .set('redeployStatus')
    .set('redeployStatusName')
    .set('taskType')
    .set('redeployUser')
    .set('getTaskUserName')
    .set('userId')
    .set('testOfUserId')
    .set('redeployStatus')
    .set('testOfUserName')
    .set('operationOfUserId')
    .set('operationOfUserName')
    .set('createTaskUserDeptId')
    .set('createTaskUserId')
    .set('testBug')
    .set('marks')
    .set('createDate')
    .set('updateDate')
    .set('createUser')
    .set('updateUser')
}

/**
 * 提交添加
 */
taskTestEndInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/myTask/problem", function(data){
        Feng.success("问题反馈成功!");
        window.parent.taskTestEnd.table.refresh();
        parent.location.reload();
    },function(data){
        Feng.error("问题反馈失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.taskTestEndInfoData);
    ajax.set("id",$('#id').val());
    ajax.set("testBug",$('#testBug').val());
    ajax.start();
}

/**
 * 提交修改
 */
taskTestEndInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/task/update", function(data){
        Feng.success("修改成功!");
        window.parent.taskTestEnd.table.refresh();
        taskTestEndInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.taskTestEndInfoData);
    ajax.start();
}

$(function() {
    var E = window.wangEditor;
    var editor = new E('#testBug1');
    var $testBug = $('#testBug')
    editor.customConfig.onchange = function (html) {
        var h = editor.txt.html(html);
        var text = editor.txt.text(h);
        // 监控变化，同步更新到 textarea
        $testBug.val(text)
    }
    editor.create()
    editor.txt.text()
    taskTestEndInfoDlg.editor = editor;

});
