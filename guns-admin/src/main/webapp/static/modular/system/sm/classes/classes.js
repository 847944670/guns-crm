/**
 * 我的周报管理初始化
 */
var classes = {
    id: "classesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
classes.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '班级名称', field: 'classesName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '学生姓名', field: 'studentName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},




    ];
};

/**
 * 检查是否选中
 */
classes.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        classes.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
classes.openAddclasses = function () {

    debugger;
    var index = layer.open({
        type: 2,
        title: '添加我的年级',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/classes/classes_add'
    });
    debugger;
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
classes.openClassesDetail = function () {

    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的周报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/classes/classes_update/' + classes.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
classes.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/classes/delete", function (data) {
            Feng.success("删除成功!");
            classes.table.refresh();
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
classes.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    classes.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = classes.initColumn();
    var table = new BSTable(classes.id, "/classes/list", defaultColunms);
    table.setPaginationType("client");
    classes.table = table.init();
});
