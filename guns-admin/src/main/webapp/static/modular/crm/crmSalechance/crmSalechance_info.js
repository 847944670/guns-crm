/**
 * 初始化销售机会详情对话框
 */
var CrmSalechanceInfoDlg = {
	crmSalechanceInfoData : {},
	validateFields : {
		changeType : {
			validators : {
				notEmpty : {
					message : '机会类型不能为空'
				}
			}
		},
		number : {
			validators : {
				notEmpty : {
					message : '销售数量不能为空'
				},
				between : {
					message : "销售数量必须大于1",
					// inclusive:false,
					min : 1,
					max : 9999999999999
				}
			}
		},
		unitPrice : {
			validators : {
				notEmpty : {
					message : '销售单价不能为空'
				},
				between : {
					message : "销售单价必须大于1",
					// inclusive:false,
					min : 1,
					max : 9999999999999
				}
			}
		},
		amount : {
			validators : {
				notEmpty : {
					message : '销售金额不能为空'
				},
				between : {
					message : "销售金额必须大于1",
					// inclusive:false,
					min : 1,
					max : 9999999999999
				}
			}
		},
		/*
		 * finishDate : { validators : { notEmpty : { message : '请选择结单日期' } } },
		 */
		fstate : {
			validators : {
				notEmpty : {
					message : '请选择销售阶段'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
CrmSalechanceInfoDlg.clearData = function() {
	this.crmSalechanceInfoData = {};
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
CrmSalechanceInfoDlg.set = function(key, val) {
	this.crmSalechanceInfoData[key] = (typeof val == "undefined") ? $("#" + key)
			.val()
			: val;
	return this;
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
CrmSalechanceInfoDlg.get = function(key) {
	return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CrmSalechanceInfoDlg.close = function() {
	parent.layer.closeAll();
	//parent.layer.close(window.parent.CrmSalechance.layerIndex);
}

/**
 * 收集数据
 */
CrmSalechanceInfoDlg.collectData = function() {
	this.set('id').set('customerId').set('createDate').set('changeType').set(
			'saleId').set('amount').set('fstate').set('IsDelete').set('other1')
			.set('other2').set('other3').set('number').set('unitPrice');
			/*.set('finishDate');*/
}

/**
 * 验证数据是否为空
 */
CrmSalechanceInfoDlg.validate = function() {
	$('#saleChanceForm').data("bootstrapValidator").resetForm();
	$('#saleChanceForm').bootstrapValidator('validate');
	return $("#saleChanceForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
CrmSalechanceInfoDlg.addSubmit = function() {

	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}

	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/crmSalechance/add", function(data) {
		if (data.code == 200 || data.code == '200') {
			Feng.success("添加成功!");
			setTimeout(
					"parent.layer.closeAll();window.parent.location.reload();",
					500)

		} else {
			Feng.error("添加失败!" + data.message + "!");
		}
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	// console.info(this.crmSalechanceInfoData);
	ajax.set(this.crmSalechanceInfoData);
	ajax.start();
}

/**
 * 提交修改
 */
CrmSalechanceInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();

	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/crmSalechance/update", function(data) {
		Feng.success("修改成功!");
		window.parent.CrmSalechance.table.refresh();
		CrmSalechanceInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.crmSalechanceInfoData);
	ajax.start();
}

$(function() {
	Feng.initValidator("saleChanceForm", CrmSalechanceInfoDlg.validateFields);
});
