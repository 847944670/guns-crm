/**
 * 我的周报管理初始化
 */
var school = {
    id: "schoolTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
school.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '学校名称', field: 'schoolName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '学校地址', field: 'address', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '学校系别', field: 'department', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},




    ];
};

/**
 * 检查是否选中
 */
school.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        school.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
school.openAddschool = function () {

    debugger;
    var index = layer.open({
        type: 2,
        title: '添加我的年级',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/school/school_add'
    });
    debugger;
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
school.openSchoolDetail = function () {

    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的周报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/school/school_update/' + school.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
school.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/school/delete", function (data) {
            Feng.success("删除成功!");
            school.table.refresh();
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
school.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    school.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = school.initColumn();
    var table = new BSTable(school.id, "/school/list", defaultColunms);
    table.setPaginationType("client");
    school.table = table.init();
});
