/**
 * 初始化我的周报详情对话框
 */
var departmentInfoDlg = {
    departmentInfoData : {}
};

/**
 * 清除数据
 */
departmentInfoDlg.clearData = function() {
    this.departmentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
departmentInfoDlg.set = function(key, val) {
    this.departmentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
departmentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
departmentInfoDlg.close = function() {
    parent.layer.close(window.parent.department.layerIndex);
}

/**
 * 收集数据
 */
departmentInfoDlg.collectData = function() {
    this.set('id')
    .set('deptName').set('marks')

}

/**
 * 提交添加
 */
departmentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/department/add", function(data){
        Feng.success("添加成功!");
        window.parent.department.table.refresh();
        departmentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.departmentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
departmentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/department/update", function(data){
        Feng.success("修改成功!");
        window.parent.department.table.refresh();
        departmentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.departmentInfoData);
    ajax.start();
}

$(function() {

});
