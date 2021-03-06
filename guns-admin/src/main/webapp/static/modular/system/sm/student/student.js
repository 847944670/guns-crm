/**
 * 我的周报管理初始化
 */
var student = {
    id: "studentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
student.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '姓名', field: 'studentName', visible: true, align: 'center', valign: 'middle'},
        {title: '年级', field: 'grade', visible: true, align: 'center', valign: 'middle' },
        {title: '年龄', field: 'age', visible: true, align: 'center', valign: 'middle'},
        {title: '电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
        {title: '班级', field: 'classes', visible: true, align: 'center', valign: 'middle'},
        {title: '学校', field: 'school', visible: true, align: 'center', valign: 'middle'},
        {title: '专业', field: 'major', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'}

    ];
};

/**
 * 检查是否选中
 */
student.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        student.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加我的周报
 */
student.openAddstudent = function () {
    debugger;
    var index = layer.open({
        type: 2,
        title: '添加我的学生信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/studentOf/student_add'
    });
    debugger;
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
student.openStudentDetail = function () {

    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的学生信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/studentOf/student_update/' + student.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
student.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/studentOf/delete", function (data) {
            Feng.success("删除成功!");
            student.table.refresh();
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
student.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    student.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = student.initColumn();
    var table = new BSTable(student.id, "/studentOf/list", defaultColunms);
    table.setPaginationType("client");
    student.table = table.init();
});
