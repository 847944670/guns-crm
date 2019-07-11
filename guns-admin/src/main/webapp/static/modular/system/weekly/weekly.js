/**
 * 我的周报管理初始化
 */
var Weekly = {
    id: "WeeklyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Weekly.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '提交周报员工的id', field: 'usernid', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '登录此帐号员工姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '员工周报', field: 'weekly', visible: true, align: 'center', valign: 'middle'},
            {title: '员工提交时间', field: 'time', visible: true, align: 'center', valign: 'middle'},
            {title: '要接收的用户id', field: 'userid', visible: true, align: 'center', valign: 'middle'},
            {title: '要接收的用户角色id', field: 'roleid', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '要接收的用户部门id', field: 'deptid', visible: true, align: 'center', valign: 'middle'},
            {title: '扩展字段一', field: 'extendone', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
            {title: '扩展字段二', field: 'extendtwo', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'}
    ];
};

/**
 * 检查是否选中
 */
Weekly.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Weekly.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
Weekly.openAddWeekly = function () {
    var index = layer.open({
        type: 2,
        title: '添加我的周报',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/weekly/weekly_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
Weekly.openWeeklyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的周报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/weekly/weekly_update/' + Weekly.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
Weekly.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/weekly/delete", function (data) {
            Feng.success("删除成功!");
            Weekly.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("weeklyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询我的周报列表
 */
Weekly.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Weekly.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Weekly.initColumn();
    var table = new BSTable(Weekly.id, "/weekly/list", defaultColunms);
    table.setPaginationType("client");
    Weekly.table = table.init();
});
