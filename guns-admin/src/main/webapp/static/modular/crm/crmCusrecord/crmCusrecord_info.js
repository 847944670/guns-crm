/**
 * 初始化客户跟进记录详情对话框
 */
var CrmCusrecordInfoDlg = {
    crmCusrecordInfoData : {},
    validateFields:{
    	followBuiness:{
    		validators: {
                notEmpty: {
                    message: '跟进业务不能为空'
                }
            }
    	},
    	followDetail:{
    		validators: {
                notEmpty: {
                    message: '跟进详情不能为空'
                }
            }
    	}
    }
    
};

/**
 * 清除数据
 */
CrmCusrecordInfoDlg.clearData = function() {
    this.crmCusrecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CrmCusrecordInfoDlg.set = function(key, val) {
    this.crmCusrecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CrmCusrecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CrmCusrecordInfoDlg.close = function() {
    parent.layer.close(window.parent.CrmCusrecord.layerIndex);
}

/**
 * 收集数据
 */
CrmCusrecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('customerId')
    .set('createDate')
    .set('IsDelete')
    .set('followType')
    .set('followBuiness')
    .set('followDetail')
    .set('salerId')
    .set('other1')
    .set('other2')
    .set('other3');
}
/**
 * 验证数据是否为空
 */
CrmCusrecordInfoDlg.validate = function () {
    $('#GJjhInfo').data("bootstrapValidator").resetForm();
    $('#GJjhInfo').bootstrapValidator('validate');
    return $("#GJjhInfo").data('bootstrapValidator').isValid();
};
/**
 * 提交添加
 */
CrmCusrecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/crmCusrecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.CrmCusrecord.table.refresh();
        CrmCusrecordInfoDlg.close();
        window.parent.location.reload();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.crmCusrecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CrmCusrecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/crmCusrecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.CrmCusrecord.table.refresh();
        CrmCusrecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.crmCusrecordInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("GJjhInfo", CrmCusrecordInfoDlg.validateFields);
});
