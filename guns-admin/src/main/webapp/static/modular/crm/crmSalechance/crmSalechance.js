/**
 * 销售机会管理初始化
 */
var CrmSalechance = {
    id: "CrmSalechanceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CrmSalechance.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '客户UUID', field: 'customerId', visible: true, align: 'center', valign: 'middle'},
            {title: '销售机会时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            {title: '销售机会类型', field: 'changeType', visible: true, align: 'center', valign: 'middle'},
            {title: '销售人员ID', field: 'saleId', visible: true, align: 'center', valign: 'middle'},
            {title: '销售金额', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '销售状态', field: 'fstate', visible: true, align: 'center', valign: 'middle'},
            {title: '结单日期', field: 'finishDate', visible: true, align: 'center', valign: 'middle'},
            {title: '删除标识符', field: 'IsDelete', visible: true, align: 'center', valign: 'middle'},
            {title: '其他1', field: 'other1', visible: true, align: 'center', valign: 'middle'},
            {title: '其他2', field: 'other2', visible: true, align: 'center', valign: 'middle'},
            {title: '其他3', field: 'other3', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CrmSalechance.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CrmSalechance.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加销售机会
 */
CrmSalechance.openAddCrmSalechance = function () {
    var index = layer.open({
        type: 2,
        title: '添加销售机会',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/crmSalechance/crmSalechance_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看销售机会详情
 */
CrmSalechance.openCrmSalechanceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '销售机会详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/crmSalechance/crmSalechance_update/' + CrmSalechance.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除销售机会
 */
CrmSalechance.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/crmSalechance/delete", function (data) {
            Feng.success("删除成功!");
            CrmSalechance.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("crmSalechanceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询销售机会列表
 */
CrmSalechance.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CrmSalechance.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CrmSalechance.initColumn();
    var table = new BSTable(CrmSalechance.id, "/crmSalechance/list", defaultColunms);
    table.setPaginationType("client");
    CrmSalechance.table = table.init();
});
