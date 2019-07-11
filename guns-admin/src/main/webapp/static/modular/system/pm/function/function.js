/**
 * 我的周报管理初始化
 */
var funOfModule = {
    id: "funOfModuleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
funOfModule.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       // {title: '', field: 'id', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '项目功能名称', field: 'functionName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '项目功能描述', field: 'functionDescription', visible: true, align: 'center', valign: 'middle'},
        {title: '所在模块', field: 'moduleName', visible: true, align: 'center', valign: 'middle'},
        {title: '所在项目', field: 'projectName', visible: true, align: 'center', valign: 'middle'},
        {title: '开始时间', field: 'startTime', visible: true, align: 'center', valign: 'middle'},
        {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},

        {title: '功能研发周期', field: 'time', visible: true, align: 'center', valign: 'middle'},
        {title: '领任务的员工', field: 'accepterEmployee', visible: true, align: 'center', valign: 'middle'},
        {
            title: '操作',align: 'center',
            events: operateEvents,//给按钮注册事件
            formatter: addFunctionButton//表格中增加按钮  
        },
        {title: '备注', field: 'marks', visible: true, align: 'center', valign: 'middle'},
        {title: '创建者', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
        {title: '更新者', field: 'updateUser', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
        {title: '更新时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},

    ];
};
function addFunctionButton(value, row, index) {
    return [
        '<button id="fun" type="button" class="btn-small btn-info button-margin"><span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>任务下放</button>',
    ].join('');
}
window.operateEvents = {
    'click #fun': function () {
        if (funOfModule.check()) {
            var index = layer.open({
                type: 2,
                title: '选择下放用户',
                area: ['1000px', '500px'], //宽高
                fix: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/taskofuser/index?id=' + funOfModule.seItem.id+"&type=fun"
            });
            this.layerIndex = index;
        }
    }
};
/**
 * 检查是否选中
 */
funOfModule.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        funOfModule.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
funOfModule.openAddfunOfModule = function () {
    var index = layer.open({
        type: 2,
        title: '添加我的功能',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/funOfModule/funOfModule_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
funOfModule.openfunOfModuleDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的功能详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/funOfModule/funOfModule_update/' + funOfModule.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
funOfModule.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/funOfModule/delete", function (data) {
            Feng.success("删除成功!");
            funOfModule.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("funOfModuleId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询我的周报列表
 */
funOfModule.search = function () {
    var queryData = {};
    queryData['functionName'] = $("#functionName").val();
    funOfModule.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = funOfModule.initColumn();
    var table = new BSTable(funOfModule.id, "/funOfModule/list", defaultColunms);
    table.setPaginationType("client");
    funOfModule.table = table.init();

});
