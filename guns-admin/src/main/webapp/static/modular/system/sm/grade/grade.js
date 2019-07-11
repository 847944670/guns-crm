/**
 * 我的周报管理初始化
 */
var grade = {
    id: "gradeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
grade.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '年级', field: 'name', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},



    ];
};

/**
 * 检查是否选中
 */
grade.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        grade.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
grade.openAddgrade = function () {

    debugger;
    var index = layer.open({
        type: 2,
        title: '添加我的年级',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/grade/grade_add'
    });
    debugger;
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
grade.openGradeDetail = function () {

    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的周报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/grade/grade_update/' + grade.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
grade.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/grade/delete", function (data) {
            Feng.success("删除成功!");
            grade.table.refresh();
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
grade.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    grade.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = grade.initColumn();
    var table = new BSTable(grade.id, "/grade/list", defaultColunms);
    table.setPaginationType("client");
    grade.table = table.init();
});
