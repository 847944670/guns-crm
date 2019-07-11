/**
 * 初始化我的周报详情对话框
 */
var taskInfoDlg = {
    taskInfoData : {}
};

/**
 * 清除数据
 */
taskInfoDlg.clearData = function() {
    this.taskInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
taskInfoDlg.set = function(key, val) {
    this.taskInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
taskInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
taskInfoDlg.close = function() {
    parent.layer.close(window.parent.task.layerIndex);
}

/**
 * 收集数据
 */
taskInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('employeeRole')
    .set('deptName')
    .set('projectName')
    .set('moduleName')
    .set('funName')
    .set('projectTime')
    .set('moduleTime')
    .set('functionTime')
    .set('projectDelayTime')
    .set('moduleDelayTime')
    .set('functionDelayTime')
    .set('isDelay')
    .set('marks')
    .set('createDate')
    .set('updateDate')
    .set('createUser')
    .set('updateUser')
}

/**
 * 提交添加
 */
taskInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/task/add", function(data){
        Feng.success("添加成功!");
        window.parent.task.table.refresh();
        taskInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.taskInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
taskInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/task/update", function(data){
        Feng.success("修改成功!");
        window.parent.task.table.refresh();
        taskInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.taskInfoData);
    ajax.start();
}

$(function() {

});
