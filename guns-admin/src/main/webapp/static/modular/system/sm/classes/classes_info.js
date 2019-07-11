/**
 * 初始化我的周报详情对话框
 */
var classesInfoDlg = {
    classesInfoData : {}
};

/**
 * 清除数据
 */
classesInfoDlg.clearData = function() {
    this.classesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
classesInfoDlg.set = function(key, val) {
    this.classesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
classesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
classesInfoDlg.close = function() {
    parent.layer.close(window.parent.classes.layerIndex);
}

/**
 * 收集数据
 */
classesInfoDlg.collectData = function() {
    this.set('id')
        .set('classesName').set('studentName')

}

/**
 * 提交添加
 */
classesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/classes/add", function(data){
        Feng.success("添加成功!");
        window.parent.classes.table.refresh();
        classesInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.classesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
classesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/classes/update", function(data){
        Feng.success("修改成功!");
        window.parent.classes.table.refresh();
        classesInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.classesInfoData);
    ajax.start();
}

$(function() {

});
