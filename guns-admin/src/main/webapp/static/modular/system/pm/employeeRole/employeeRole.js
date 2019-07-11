/**
 * 我的周报管理初始化
 */
var employeeRole = {
    id: "employeeRoleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
employeeRole.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '角色名称', field: 'roleName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},

    ];
};

/**
 * 检查是否选中
 */
employeeRole.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        employeeRole.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
employeeRole.openAddemployeeRole = function () {
    var index = layer.open({
        type: 2,
        title: '添加我的周报',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/employeeRole/employeeRole_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
employeeRole.openEmployeeRoleDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的周报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/employeeRole/employeeRole_update/' + employeeRole.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
employeeRole.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/employeeRole/delete", function (data) {
            Feng.success("删除成功!");
            employeeRole.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("employeeRoleId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询我的周报列表
 */
employeeRole.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    employeeRole.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = employeeRole.initColumn();
    var table = new BSTable(employeeRole.id, "/employeeRole/list", defaultColunms);
    table.setPaginationType("client");
    employeeRole.table = table.init();
});
