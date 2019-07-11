/**
 * 客户跟进记录管理初始化
 */
var CrmCusrecord = {
    id: "CrmCusrecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CrmCusrecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '客户UUID', field: 'customerId', visible: true, align: 'center', valign: 'middle'},
            {title: '跟进记录时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            {title: '删除标识符', field: 'IsDelete', visible: true, align: 'center', valign: 'middle'},
            {title: '跟进机会类型', field: 'followType', visible: true, align: 'center', valign: 'middle'},
            {title: '跟进业务', field: 'followBuiness', visible: true, align: 'center', valign: 'middle'},
            {title: '跟进详情', field: 'followDetail', visible: true, align: 'center', valign: 'middle'},
            {title: '销售人员ID', field: 'salerId', visible: true, align: 'center', valign: 'middle'},
            {title: '其他1', field: 'other1', visible: true, align: 'center', valign: 'middle'},
            {title: '其他2', field: 'other2', visible: true, align: 'center', valign: 'middle'},
            {title: '其他3', field: 'other3', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CrmCusrecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CrmCusrecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加客户跟进记录
 */
CrmCusrecord.openAddCrmCusrecord = function () {
	var input=document.getElementById("id");
    var index = layer.open({
        type: 2,
        title: '添加客户跟进记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/crmCusrecord/crmCusrecord_add/'+input.value
    });
    this.layerIndex = index;
};

/**
 * 打开查看客户跟进记录详情
 */
CrmCusrecord.openCrmCusrecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '客户跟进记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/crmCusrecord/crmCusrecord_update/' + CrmCusrecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除客户跟进记录
 */
CrmCusrecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/crmCusrecord/delete", function (data) {
            Feng.success("删除成功!");
            CrmCusrecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("crmCusrecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询客户跟进记录列表
 */
CrmCusrecord.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CrmCusrecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CrmCusrecord.initColumn();
    var table = new BSTable(CrmCusrecord.id, "/crmCusrecord/list", defaultColunms);
    table.setPaginationType("client");
    CrmCusrecord.table = table.init();
});
