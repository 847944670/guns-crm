/**
 * 我的周报管理初始化
 */
var project = {
    id: "projectTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
project.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
         //   {title: '', field: 'id', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '项目名称', field: 'projectName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '项目描述', field: 'projectDescription', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'startTime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '项目研发周期', field: 'time', visible: true, align: 'center', valign: 'middle'},

            {title: '备注', field: 'marks', visible: true, align: 'center', valign: 'middle'},
            {title: '创建者', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
            {title: '更新者', field: 'updateUser', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},

    ];
};

/**
 * 检查是否选中
 */
project.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        project.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
project.openAddproject = function () {
    var index = layer.open({
        type: 2,
        title: '添加我的项目',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/project/project_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
project.openProjectDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的项目详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/project/project_update/' + project.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
project.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/project/delete", function (data) {
            Feng.success("删除成功!");
            project.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("projectId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询我的周报列表
 */

project.search = function () {
    var queryData = {};
    queryData['projectName'] = $("#projectName").val();
    project.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = project.initColumn();
    var table = new BSTable(project.id, "/project/list", defaultColunms);
    table.setPaginationType("client");
    project.table = table.init();
});
