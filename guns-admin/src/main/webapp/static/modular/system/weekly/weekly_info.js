/**
 * 初始化我的周报详情对话框
 */
var WeeklyInfoDlg = {
    weeklyInfoData : {}
};

/**
 * 清除数据
 */
WeeklyInfoDlg.clearData = function() {
    this.weeklyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WeeklyInfoDlg.set = function(key, val) {
    this.weeklyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WeeklyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WeeklyInfoDlg.close = function() {
    parent.layer.close(window.parent.Weekly.layerIndex);
}

/**
 * 收集数据
 */
WeeklyInfoDlg.collectData = function() {
    this
    .set('id')
    .set('usernid')
    .set('name')
    .set('weekly')
    .set('time')
    .set('userid')
    .set('roleid')
    .set('deptid')
    .set('extendone')
    .set('extendtwo');
}

/**
 * 提交添加
 */
WeeklyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/weekly/add", function(data){
        Feng.success("添加成功!");
        window.parent.Weekly.table.refresh();
        WeeklyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.weeklyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WeeklyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/weekly/update", function(data){
        Feng.success("修改成功!");
        window.parent.Weekly.table.refresh();
        WeeklyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.weeklyInfoData);
    ajax.start();
}

$(function() {

});
