/**
 * 初始化我的周报详情对话框
 */
var studentInfoDlg = {
    studentInfoData : {}
};

/**
 * 清除数据
 */
studentInfoDlg.clearData = function() {
    this.studentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
studentInfoDlg.set = function(key, val) {
    this.studentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
studentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
studentInfoDlg.close = function() {
    parent.layer.close(window.parent.student.layerIndex);
}

/**
 * 收集数据
 */
studentInfoDlg.collectData = function() {
    this.set('id')
    .set("studentName").set("grade").set("age").set("phone").set("classes").set("school").set("major")

}

/**
 * 提交添加
 */
studentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/studentOf/add", function(data){
        Feng.success("添加成功!");
        window.parent.student.table.refresh();
        studentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
studentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/studentOf/update", function(data){
        Feng.success("修改成功!");
        window.parent.student.table.refresh();
        studentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentInfoData);
    ajax.start();
}

$(function() {

});
