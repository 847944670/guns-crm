/**
 * 初始化客户管理详情对话框
 */
var CrmCustomerInfoDlg = {
    crmCustomerInfoData : {},
    validateFields: {
    	customerName: {
	        validators: {
	            notEmpty: {
	                message: '客户名称不能为空'
	            }
	        }
	    },
	    customerMess: {
	        validators: {
	            notEmpty: {
	                message: '客户简称不能为空'
	            }
	        }
	    },
	    customerHy: {
	        validators: {
	            notEmpty: {
	                message: '客户行业不能为空'
	            }
	        }
	    },
	    customerStar: {
	        validators: {
	            notEmpty: {
	                message: '客户星级不能为空'
	            }
	        }
	    }
	   
}




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
    .set('id')
    .set('customerName')
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
    .set('other3');
}

CrmCustomerInfoDlg.validate = function () {
//    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 提交添加
 */
CrmCustomerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/crmCustomerSeas/add", function(data){
       /* Feng.success("添加成功!");
        window.parent.CrmCustomer.table.refresh();
        CrmCustomerInfoDlg.close();*/
        
        if(data.code == 200 || data.code == '200'){
    		Feng.success("添加成功!");
            window.parent.CrmCustomer.table.refresh();
            CrmCustomerInfoDlg.close();
    	} else {
    		Feng.error("添加失败!" + data.message + "!");
    	}
        
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
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
	Feng.initValidator("userInfoForm", CrmCustomerInfoDlg.validateFields);
});
/**
 * select 选择框获取省份信息
 * @author xiaobai
 */
