/**
 * 客户回收管理管理初始化
 */
var CrmCustomerrabin = {
    id: "CrmCustomerrabinTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CrmCustomerrabin.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '回收客户表id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '客户UUID 外键', field: 'customerId', visible: true, align: 'center', valign: 'middle'},
            {title: '前销售人', field: 'cusOldSaler', visible: true, align: 'center', valign: 'middle'},
            {title: '回收时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '其他1', field: 'other1', visible: true, align: 'center', valign: 'middle'},
            {title: '其他2', field: 'other2', visible: true, align: 'center', valign: 'middle'},
            {title: '其他3', field: 'other3', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CrmCustomerrabin.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CrmCustomerrabin.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加客户回收管理
 */
CrmCustomerrabin.openAddCrmCustomerrabin = function () {
    var index = layer.open({
        type: 2,
        title: '添加客户回收管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/crmCustomerrabin/crmCustomerrabin_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看客户回收管理详情
 */
CrmCustomerrabin.openCrmCustomerrabinDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '客户回收管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/crmCustomerrabin/crmCustomerrabin_update/' + CrmCustomerrabin.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除客户回收管理
 */
CrmCustomerrabin.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/crmCustomerrabin/delete", function (data) {
            Feng.success("删除成功!");
            CrmCustomerrabin.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("crmCustomerrabinId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询客户回收管理列表
 */
CrmCustomerrabin.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CrmCustomerrabin.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CrmCustomerrabin.initColumn();
    var table = new BSTable(CrmCustomerrabin.id, "/crmCustomerrabin/list", defaultColunms);
    table.setPaginationType("client");
    CrmCustomerrabin.table = table.init();
});
