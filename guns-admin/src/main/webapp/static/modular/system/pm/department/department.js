/**
 * 我的周报管理初始化
 */
var department = {
    id: "departmentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
department.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '部门名称', field: 'deptName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '备注', field: 'marks', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},


    ];
};

/**
 * 检查是否选中
 */
department.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        department.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
department.openAdddepartment = function () {
    var index = layer.open({
        type: 2,
        title: '添加我的周报',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/department/department_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
department.openDepartmentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的周报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/department/department_update/' + department.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
department.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/department/delete", function (data) {
            Feng.success("删除成功!");
            department.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("departmentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询我的周报列表
 */
department.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    department.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = department.initColumn();
    var table = new BSTable(department.id, "/department/list", defaultColunms);
    table.setPaginationType("client");
    department.table = table.init();
});
