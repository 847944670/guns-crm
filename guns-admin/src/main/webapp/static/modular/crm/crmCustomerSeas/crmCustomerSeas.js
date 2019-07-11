/**
 * 客户管理管理初始化
 */
var CrmCustomer = {
    id: "CrmCustomerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CrmCustomer.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            /*{title: '客户UUID', field: 'id', visible: true, align: 'center', valign: 'middle'},*/
            {title: '客户名称', field: 'customerName', visible: true, align: 'center', valign: 'middle'},
            {title: '客户简称', field: 'customerMess', visible: true, align: 'center', valign: 'middle'},
            {title: '客户行业', field: 'customerHy', visible: true, align: 'center', valign: 'middle'},
            {title: '重要程度', field: 'customerStar', visible: true, align: 'center', valign: 'middle'},
            {title: '销售阶段', field: 'customerState', visible: true, align: 'center', valign: 'middle'},
            {title: '所在地区', field: 'area', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'cusPhoneNum', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'customerRelation', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '前负责人', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'customerFrom', visible: true, align: 'center', valign: 'middle'},
            /*{title: '客户官网', field: 'www', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'customerRemark', visible: true, align: 'center', valign: 'middle'}*/

            /*{title: '销售人名称', field: 'salerName', visible: true, align: 'center', valign: 'middle'},
            {title: '销售时间', field: 'salerCreateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '销售人ID', field: 'salerId', visible: true, align: 'center', valign: 'middle'},*/
            /*{title: '删除标识符', field: 'IsDelete', visible: true, align: 'center', valign: 'middle'},
            {title: '其他1', field: 'other1', visible: true, align: 'center', valign: 'middle'},
            {title: '其他2', field: 'other2', visible: true, align: 'center', valign: 'middle'},
            {title: '其他3', field: 'other3', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
CrmCustomer.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CrmCustomer.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加客户管理
 */
CrmCustomer.openAddCrmCustomer = function () {
    var index = layer.open({
        type: 2,
        title: '添加客户管理',
        area: ['100%', '100%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/crmCustomerSeas/customer_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看客户管理详情
 */
CrmCustomer.openCrmCustomerDetail = function () {
	if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '客户详情',
            area: ['100%', '100%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/crmCustomer/detailPage/' + CrmCustomer.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 分配客户
 */
CrmCustomer.openCrmCustomerAssign = function () {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '选择要分配的人员',
			area: ['600px', '260px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/crmCustomerSeas/assign/' + CrmCustomer.seItem.id
		});
		this.layerIndex = index;
	}
};
/**
 * 捞取客户
 */
CrmCustomer.openCrmCustomerGain = function () {
	if (this.check()) {
		var ajax = new $ax(Feng.ctxPath + "/crmCustomerSeas/gain/" + CrmCustomer.seItem.id, function (data) {
			if(data.code == 200 || data.code == '200'){
	            Feng.success("捞取成功!");
	            CrmCustomer.table.refresh();
			}else {
	    		Feng.error("捞取失败!" + data.message + "!");
	    	}
        }, function (data) {
            Feng.error("捞取失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
	}
};

/**
 * 删除客户管理
 */
CrmCustomer.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/crmCustomer/delete", function (data) {
            Feng.success("删除成功!");
            CrmCustomer.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("crmCustomerId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询客户管理列表
 */
CrmCustomer.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CrmCustomer.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CrmCustomer.initColumn();
    var table = new BSTable(CrmCustomer.id, "/crmCustomerSeas/list", defaultColunms);
    table.setPaginationType("client");
    CrmCustomer.table = table.init();
});
