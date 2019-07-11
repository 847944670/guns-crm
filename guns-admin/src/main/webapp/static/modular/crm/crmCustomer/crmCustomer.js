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
            {title: '客户星级', width: 140, field: 'customerStar', visible: true, align: 'center', valign: 'middle', formatter: setRate},
            {title: '客户状态', field: 'customerState', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'customerRelation', visible: true, align: 'center', valign: 'middle'},
            {title: '客户联系电话', field: 'cusPhoneNum', visible: true, align: 'center', valign: 'middle'},
            {title: '客户创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '地区', field: 'area', visible: true, align: 'center', valign: 'middle'},
            {title: '详细地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '客户来源', field: 'customerFrom', visible: true, align: 'center', valign: 'middle'},
            {title: '客户官网', field: 'www', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'customerRemark', visible: true, align: 'center', valign: 'middle'}

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

function setRate(value, row, index){
	var starNum = value;
	var enptyStarNum = 5- value;
	var str = '<p>';
	if(starNum > 0){
		for(var i = 0;i < starNum;i++){
			str = str + '<span class="glyphicon glyphicon-star" style="color: #ffb800;font-size:18px;"></span>';
		}
	}
	if(enptyStarNum > 0){
		for(var i = 0;i < enptyStarNum;i++){
			str = str + '<span class="glyphicon glyphicon-star glyphicon-star-empty" style="color: #ffb873;font-size:18px;"></span>';
		}
	}
	
	return str;
}
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
        content: Feng.ctxPath + '/crmCustomer/crmCustomer_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看客户管理详情
 */
CrmCustomer.openCrmCustomerDetail = function () {
    if (this.check()) {
    	var ajax = new $ax(Feng.ctxPath + "/crmCustomer/checkUpdate", function (data) {
            if(data==1){
            	var index = layer.open({
                    type: 2,
                    title: '客户管理详情',
                    area: ['100%', '100%'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/crmCustomer/crmCustomer_update/' + CrmCustomer.seItem.id
                });
                this.layerIndex = index;
            }
            else{
            	Feng.error("超过修改时间  禁止修改!");
            }
        });
        ajax.set("crmCustomerId",this.seItem.id);
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
CrmCustomer.look = function () {
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
CrmCustomer.tuihui = function(){
	if (this.check()) {
		var operation = function(){
			var userId = CrmCustomer.seItem.id;
        var ajax = new $ax(Feng.ctxPath + "/crmCustomer/tuihui", function (data) {
            Feng.success("退回成功!");
            CrmCustomer.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("crmCustomerId",userId);
        ajax.start();
    };
    Feng.confirm("是否退回该客户?",operation);
    }
};
/**
 * 转移
 */
CrmCustomer.transfer = function(){
	if (this.check()) {
	 
	var bgbox = layer.open({
		type: 1
		,title: false //不显示标题栏
	    ,closeBtn: false
		,area: '25%'
		,shade: 0.4
		,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		,resize: false
	    ,content: $("#zhbox")
	});
	}
}
CrmCustomer.transferTijiao = function(){
	var options=$("#newXyrId option:selected");
	var salerId = options.val();
	var salerName = options.text();
	var customId = CrmCustomer.seItem.id;
	var ajax = new $ax(Feng.ctxPath + "/crmCustomer/transferTijiao", function (data) {
		Feng.success("转移成功!");
		layer.closeAll();
		CrmCustomer.table.refresh();
    }, function (data) {
    	Feng.error("转移失败!" + data.responseJSON.message + "!");
    });
	ajax.set("salerId", salerId);
	ajax.set("salerName", salerName);
	ajax.set("id", customId);
    ajax.start();
}
/**
 * 查询客户管理列表
 */
CrmCustomer.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CrmCustomer.table.refresh({query: queryData});
};

function initSelect(){
	var ajax = new $ax(Feng.ctxPath + "/mgr/getManager", function (data) {
        for(var i=0;i<data.length;i++){
       	 $("#newXyrId").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
        }
    }, function (data) {
    });
    ajax.start();
}
$(function () {
    var defaultColunms = CrmCustomer.initColumn();
    var table = new BSTable(CrmCustomer.id, "/crmCustomer/Mylist", defaultColunms);
    table.setPaginationType("client");
    CrmCustomer.table = table.init();
    initSelect();
});
