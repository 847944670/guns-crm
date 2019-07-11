/**
 * 初始化客户回收管理详情对话框
 */
var CrmCustomerrabinInfoDlg = {
    crmCustomerrabinInfoData : {}
};

/**
 * 清除数据
 */
CrmCustomerrabinInfoDlg.clearData = function() {
    this.crmCustomerrabinInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CrmCustomerrabinInfoDlg.set = function(key, val) {
    this.crmCustomerrabinInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CrmCustomerrabinInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CrmCustomerrabinInfoDlg.close = function() {
    parent.layer.close(window.parent.CrmCustomerrabin.layerIndex);
}

/**
 * 收集数据
 */
CrmCustomerrabinInfoDlg.collectData = function() {
    this
    .set('id')
    .set('customerId')
    .set('cusOldSaler')
    .set('createTime')
    .set('other1')
    .set('other2')
    .set('other3');
}

/**
 * 提交添加
 */
CrmCustomerrabinInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/crmCustomerrabin/add", function(data){
        Feng.success("添加成功!");
        window.parent.CrmCustomerrabin.table.refresh();
        CrmCustomerrabinInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.crmCustomerrabinInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CrmCustomerrabinInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/crmCustomerrabin/update", function(data){
        Feng.success("修改成功!");
        window.parent.CrmCustomerrabin.table.refresh();
        CrmCustomerrabinInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.crmCustomerrabinInfoData);
    ajax.start();
}

$(function() {

});
