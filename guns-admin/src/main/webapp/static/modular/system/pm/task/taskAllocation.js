/**
 * 我的周报管理初始化
 */
var task = {
    id: "taskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
task.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '职位', field: 'employeeRole', visible: true, align: 'center', valign: 'middle'},
            {title: '所属部门', field: 'deptName', visible: true, align: 'center', valign: 'middle'},
            {title: '所在项目', field: 'projectName', visible: true, align: 'center', valign: 'middle'},
            {title: '所在模块', field: 'moduleName', visible: true, align: 'center', valign: 'middle'},

            {title: '需要完成的功能', field: 'funName', visible: true, align: 'center', valign: 'middle'},
            {title: '项目研发周期(单位:天)', field: 'projectTime', visible: true, align: 'center', valign: 'middle'},
            {title: '模块研发周期(单位:天)', field: 'moduleTime', visible: true, align: 'center', valign: 'middle'},
            {title: '功能开发周期(单位:天)', field: 'functionTime', visible: true, align: 'center', valign: 'middle'},
            {title: '项目延时(单位:天)', field: 'projectDelayTime', visible: true, align: 'center', valign: 'middle'},
            {title: '功能延时(单位:天)', field: 'moduleDelayTime', visible: true, align: 'center', valign: 'middle'},
            {title: '所处项目是否延时', field: 'isDelay', visible: true, align: 'center', valign: 'middle'},

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
task.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        task.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
task.openAddtask = function () {
    var index = layer.open({
        type: 2,
        title: '添加我的周报',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/myTask/task_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
task.opentaskDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的周报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/myTask/task_update/' + task.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
task.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/myTask/delete", function (data) {
            Feng.success("删除成功!");
            task.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("taskId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询我的周报列表
 */
task.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    task.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = task.initColumn();
    var table = new BSTable(task.id, "/myTask/list", defaultColunms);
    table.setPaginationType("client");
    task.table = table.init();
});
