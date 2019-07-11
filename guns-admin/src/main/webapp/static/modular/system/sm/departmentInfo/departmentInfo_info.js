/**
 * 初始化我的周报详情对话框
 */
var departmentInfoInfoDlg = {
    departmentInfoInfoData : {}
};

/**
 * 清除数据
 */
departmentInfoInfoDlg.clearData = function() {
    this.departmentInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
departmentInfoInfoDlg.set = function(key, val) {
    this.departmentInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
departmentInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
departmentInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.departmentInfo.layerIndex);
}

/**
 * 收集数据
 */
departmentInfoInfoDlg.collectData = function() {
    this.set('id')
        .set('deptName').set("major")

}

/**
 * 提交添加
 */
departmentInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/departmentInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.departmentInfo.table.refresh();
        departmentInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.departmentInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
departmentInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/departmentInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.departmentInfo.table.refresh();
        departmentInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.departmentInfoInfoData);
    ajax.start();
}

$(function() {

});
