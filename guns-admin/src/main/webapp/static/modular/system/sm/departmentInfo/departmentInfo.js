/**
 * 我的周报管理初始化
 */
var departmentInfo = {
    id: "departmentInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
departmentInfo.initColumn = function () {
    return [
        //filed:名字与数据库名对应
        {field: 'selectItem', radio: true},
        {title: '系别名称', field: 'deptName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '系别专业', field: 'major', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},





    ];
};

/**
 * 检查是否选中
 */
departmentInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        departmentInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
departmentInfo.openAdddepartmentInfo = function () {

    debugger;
    var index = layer.open({
        type: 2,
        title: '添加我的年级',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/departmentInfo/departmentInfo_add'
    });
    debugger;
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
departmentInfo.openDepartmentInfoDetail = function () {

    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的周报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/departmentInfo/departmentInfo_update/' + departmentInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
departmentInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/departmentInfo/delete", function (data) {
            Feng.success("删除成功!");
            departmentInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询我的周报列表
 */
departmentInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    departmentInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = departmentInfo.initColumn();
    var table = new BSTable(departmentInfo.id, "/departmentInfo/list", defaultColunms);
    table.setPaginationType("client");
    departmentInfo.table = table.init();
});
