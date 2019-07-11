/**
 * 我的周报管理初始化
 */
var module = {
    id: "moduleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
module.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
     //   {title: '', field: 'id', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '模块名称', field: 'moduleName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '所在项目', field: 'projectName', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '模块描述', field: 'moduleDescription', visible: true, align: 'center', valign: 'middle'},
        {title: '开始时间', field: 'startTime', visible: true, align: 'center', valign: 'middle'},
        {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
        {title: '模块研发周期', field: 'time', visible: true, align: 'center', valign: 'middle'},
        {title: '领任务的员工', field: 'accepterEmployee', visible: true, align: 'center', valign: 'middle'},
        {
            title: '操作',align: 'center',
            events: operateEvents,//给按钮注册事件
            formatter: addModuleButton//表格中增加按钮  
        },
        {title: '备注', field: 'marks', visible: true, align: 'center', valign: 'middle'},
        {title: '创建者', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
        {title: '更新者', field: 'updateUser', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
        {title: '更新时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},

    ];
};
function addModuleButton(value, row, index) {
    return [
        '<button id="moduleBtn" type="button" class="btn-small btn-info button-margin"><span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>任务下放</button>',
    ].join('');
}
window.operateEvents = {

    'click #moduleBtn': function () {
        if (module.check()){
            var index = layer.open({
                type: 2,
                title: '选择下放用户',
                area: ['1000px', '500px'], //宽高
                fix: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/taskofuser/index?id=' + module.seItem.id+"&type=moudle"
            });
            this.layerIndex = index;
        }

    }
};

/**
 * 检查是否选中
 */
module.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        module.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
module.openAddmodule = function () {
    var index = layer.open({
        type: 2,
        title: '添加我的模块',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/module/module_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
module.openModuleDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的模块详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/module/module_update/' + module.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
module.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/module/delete", function (data) {
            Feng.success("删除成功!");
            module.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("moduleId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询我的周报列表
 */
module.search = function () {
    var queryData = {};
    queryData['moduleName'] = $("#moduleName").val();
    module.table.refresh({query: queryData});
};
module.onClickDept = function (e, treeId, treeNode) {
    module.projectId = treeNode.id;
    module.search();
};
$(function () {
    var defaultColunms = module.initColumn();
    var table = new BSTable(module.id, "/module/list", defaultColunms);
    table.setPaginationType("client");
    module.table = table.init();

});
