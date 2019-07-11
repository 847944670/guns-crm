/**
 * 我的周报管理初始化
 */
var taskofUser = {
    id: "taskOfUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
taskofUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        //   {title: '', field: 'id', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '员工姓名', field: 'name', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '员工职务', field: 'roleName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '部门名称', field: 'deptName', visible: true, align: 'center', valign: 'middle'},
        {title: 'email', field: 'email', visible: true, align: 'center', valign: 'middle'},
        {title: '是否下放', field: 'isDown', visible: true, align: 'center', valign: 'middle'},

    ];
};


/**
 * 检查是否选中
 */
taskofUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        taskofUser.seItem = selected[0];
        return true;
    }
};

/**
 */
taskofUser.openAddtaskofUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加我的周报',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/taskofUser/taskofUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看我的周报详情
 */
taskofUser.opentaskofUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的周报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/taskofUser/taskofUser_update/' + taskofUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除我的周报
 */
taskofUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/taskofUser/delete", function (data) {
            Feng.success("删除成功!");
            taskofUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("taskofUserId",this.seItem.id);
        ajax.start();
    }
};
/**
 * 点击确定按钮
 */
taskofUser.taskdown = function (idP,typeP) {
    var taskId;
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/taskofuser/taskdown", function (data) {
            if (data.msg =="success" ){
                parent.location.reload();
                taskofUser.close();
                taskId=data.id;
            }else{
                Feng.error(data.msg);
            }
        }, function (data) {
            Feng.error("下放失败!" + data.responseJSON.message + "!");
        });
        ajax.set("taskOfUserId", this.seItem.id);
        ajax.set("id", idP);//此id为functionId 或者 moudleId
        ajax.set("type", typeP);
        ajax.start();
    }
    if (taskId!='') {
        $.ajax({
            url: Feng.ctxPath + "/taskofuser/sendMessage",
            type:'post',
            async:false,
            data: {"taskId":taskId, "type":typeP},
            dataType: "json",
            success: function(massage){
                if (massage.msg =='moudleId' ){
                    parent.Feng.success("下放[模块任务]成功,请注意查看邮箱!");
                }else if (massage.msg =='funId' ){
                    parent.Feng.success("下放[功能任务]成功,请注意查看邮箱!");
                }else if (massage.msg =='test' ){
                    parent.Feng.success("下放[测试任务]成功,请注意查看邮箱!");
                }else if (massage.msg =='redeploy' ){
                    parent.Feng.success("下放[转派任务]成功,请注意查看邮箱!");
                }else if (massage.msg =='operation' ){
                    parent.Feng.success("下放[运维任务]成功,请注意查看邮箱!");
                }
                else if (massage.msg =='errorMail' ){
                    parent.Feng.error("此用户没有邮箱账户！");
                } else if (massage.msg =='errorTask' ){
                    parent.Feng.error("此任务不存在");
                }

            },error:function(massage){
                parent.Feng.error("发送QQ邮箱消息失败!" + massage.responseJSON.message + "!");
            }
        });
    }
};

taskofUser.close=function(){
    var frameIndex = parent.layer.getFrameIndex(window.name);
    parent.layer.close(frameIndex);
};
/**
 * 查询我的周报列表
 */
taskofUser.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    taskofUser.table.refresh({query: queryData});
};





$(function () {
    var defaultColunms = taskofUser.initColumn();
    var table = new BSTable(taskofUser.id, "/taskofuser/list", defaultColunms);
    table.setPaginationType("client");
    taskofUser.table = table.init();
});



