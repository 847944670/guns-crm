/**
 * 初始化我的周报详情对话框
 */
var employeeRoleInfoDlg = {
    employeeRoleInfoData : {}
};

/**
 * 清除数据
 */
employeeRoleInfoDlg.clearData = function() {
    this.employeeRoleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
employeeRoleInfoDlg.set = function(key, val) {
    this.employeeRoleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
employeeRoleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
employeeRoleInfoDlg.close = function() {
    parent.layer.close(window.parent.employeeRole.layerIndex);
}

/**
 * 收集数据
 */
employeeRoleInfoDlg.collectData = function() {
    this.set('id')
    .set('roleName')

}

/**
 * 提交添加
 */
employeeRoleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/employeeRole/add", function(data){
        Feng.success("添加成功!");
        window.parent.employeeRole.table.refresh();
        employeeRoleInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.employeeRoleInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
employeeRoleInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/employeeRole/update", function(data){
        Feng.success("修改成功!");
        window.parent.employeeRole.table.refresh();
        employeeRoleInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.employeeRoleInfoData);
    ajax.start();
}

$(function() {

});
