/**
 * 初始化我的周报详情对话框
 */
var gradeInfoDlg = {
    gradeInfoData : {}
};

/**
 * 清除数据
 */
gradeInfoDlg.clearData = function() {
    this.gradeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
gradeInfoDlg.set = function(key, val) {
    this.gradeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
gradeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
gradeInfoDlg.close = function() {
    parent.layer.close(window.parent.grade.layerIndex);
}

/**
 * 收集数据
 */
gradeInfoDlg.collectData = function() {
    this.set('id')
    .set('name')

}

/**
 * 提交添加
 */
gradeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/grade/add", function(data){
        Feng.success("添加成功!");
        window.parent.grade.table.refresh();
        gradeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.gradeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
gradeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/grade/update", function(data){
        Feng.success("修改成功!");
        window.parent.grade.table.refresh();
        gradeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.gradeInfoData);
    ajax.start();
}

$(function() {

});
