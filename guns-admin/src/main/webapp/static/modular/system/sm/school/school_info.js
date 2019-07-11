/**
 * 初始化我的周报详情对话框
 */
var schoolInfoDlg = {
    schoolInfoData : {}
};

/**
 * 清除数据
 */
schoolInfoDlg.clearData = function() {
    this.schoolInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
schoolInfoDlg.set = function(key, val) {
    this.schoolInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
schoolInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
schoolInfoDlg.close = function() {
    parent.layer.close(window.parent.school.layerIndex);
}

/**
 * 收集数据
 */
schoolInfoDlg.collectData = function() {
    this.set('id')
        .set('schoolName').set("address").set("department")

}

/**
 * 提交添加
 */
schoolInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/school/add", function(data){
        Feng.success("添加成功!");
        window.parent.school.table.refresh();
        schoolInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.schoolInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
schoolInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/school/update", function(data){
        Feng.success("修改成功!");
        window.parent.school.table.refresh();
        schoolInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.schoolInfoData);
    ajax.start();
}

$(function() {

});
