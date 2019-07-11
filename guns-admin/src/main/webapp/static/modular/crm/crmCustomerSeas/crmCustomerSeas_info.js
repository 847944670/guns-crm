/**
 * 初始化客户管理详情对话框
 */
var CrmCustomerInfoDlg = {
    crmCustomerInfoData : {}
};

/**
 * 清除数据
 */
CrmCustomerInfoDlg.clearData = function() {
    this.crmCustomerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CrmCustomerInfoDlg.set = function(key, val) {
    this.crmCustomerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CrmCustomerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CrmCustomerInfoDlg.close = function() {
    parent.layer.close(window.parent.CrmCustomer.layerIndex);
}

/**
 * 收集数据
 */
CrmCustomerInfoDlg.collectData = function() {
    this
    .set('saleId')
    .set('customerId');
  /*  .set('customerName')
    .set('customerHy')
    .set('customerStar')
    .set('createTime')
    .set('cusPhoneNum')
    .set('customerState')
    .set('customerMess')
    .set('customerRelation')
    .set('area')
    .set('address')
    .set('www')
    .set('customerRemark')
    .set('customerFrom')
    .set('salerName')
    .set('salerCreateTime')
    .set('salerId')
    .set('IsDelete')
    .set('other1')
    .set('other2')
    .set('other3');*/
}

/**
 * 提交添加
 */
CrmCustomerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/crmCustomerSeas/assignSubmit", function(data){
    	if(data.code == 200 || data.code == '200'){
    		Feng.success("分配成功!");
            window.parent.CrmCustomer.table.refresh();
            CrmCustomerInfoDlg.close();
    	} else {
    		Feng.error("分配失败!" + data.message + "!");
    	}
    },function(data){
        Feng.error("分配失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.crmCustomerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CrmCustomerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/crmCustomer/update", function(data){
        Feng.success("修改成功!");
        window.parent.CrmCustomer.table.refresh();
        CrmCustomerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.crmCustomerInfoData);
    ajax.start();
}

$(function() {

	var ajax = new $ax(Feng.ctxPath + "/crmCustomerSeas/getCustomManger", function(data) {
		// 成功
		var html = "";
		$(data).each(
				function(i, userList) {
					html += "<option value=\"" + userList.id + "\">"
							+ userList.name + "</option>";
				})
		$("#saleId").append(html);
		// 如果data不为空，则读取第一个规则类型的规则列表
		/*
		 * if (data != null) { DecisionRulesInfoDlg.getRule(1, data[0].rdtId); }
		 */
	}, function(data) {
		// 失败
	});
	ajax.start();
	
});
