/**
 * 初始化客户管理详情对话框
 */
var CrmCustomerInfoDlg = {
    crmCustomerInfoData : {},
    validateFields:{
    	customerName:{
    		validators: {
                notEmpty: {
                    message: '客户名称不能为空'
                }
            }
    	},
    	customerMess:{
    		validators: {
                notEmpty: {
                    message: '客户简称不能为空'
                }
            }
    	},
    	customerHy:{
    		validators: {
                notEmpty: {
                    message: '客户行业不能为空'
                }
            }
    	},
    	customerStar:{
    		validators: {
                notEmpty: {
                    message: '客户星级不能为空'
                }
            }
    	},
    	customerState:{
    		validators: {
                notEmpty: {
                    message: '客户状态不能为空'
                }
            }
    	},
    	customerRelation:{
    		validators: {
                notEmpty: {
                    message: '联系人不能为空'
                }
            }
    	},
    	cusPhoneNum:{
    		validators: {
                notEmpty: {
                    message: '联系电话不能为空'
                }
            }
    	},
    	area:{
    		validators: {
                notEmpty: {
                    message: '地区不能为空'
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
/**
 * 验证电话是否唯一
 */
/*CrmCustomerInfoDlg.validatePwd = function () {
    var password = this.get("cusPhoneNum");
    var ajax = new $ax(Feng.ctxPath + "/crmCustomer/findPhone", function(data){
    	alert(data);
        if(data!=0){
        	Feng.success("电话号重复!");
        }else {
        	Feng.success("电话号重复!");
        }
        
    });
    ajax.set("phone",password);
    ajax.start();
};*/
/**
 * 验证数据是否为空
 */
CrmCustomerInfoDlg.validate = function () {
    $('#MyInfoForm').data("bootstrapValidator").resetForm();
    $('#MyInfoForm').bootstrapValidator('validate');
    return $("#MyInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
CrmCustomerInfoDlg.addSubmit = function() {
	//alert(1)
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
//    var password = this.get("cusPhoneNum");
/*    var ajax = new $ax(Feng.ctxPath + "/crmCustomer/findPhone", function(data){
    	alert(data);
        if(data!=0){
        	Feng.success("电话号重复!");
        	return;
        }
    });*/
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/crmCustomer/add", function(data){
    	if(data==1){
    		Feng.error("联系电话重复 请重新输入联系电话!");
    	}else if(data==2){
    		Feng.error("您的客户数量已达上限 禁止添加！");
    	}
    	else{
    		Feng.success("添加成功！");
    		window.parent.CrmCustomer.table.refresh();
            CrmCustomerInfoDlg.close();
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
    if (!this.validate()) {
        return;
    }
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
	Feng.initValidator("MyInfoForm", CrmCustomerInfoDlg.validateFields);
});
/**
 * select 选择框获取省份信息
 * @author xiaobai
 */
