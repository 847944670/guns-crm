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
        //   {title: '', field: 'id', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '任务名称', field: 'name', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '任务状态', field: 'statusOfTestName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden',
        formatter: function (value, row, index) {
        var a = "";
        if (value == "测试任务进行中..") {
            a = '<span style="color: #ff0000">' + value + '</span>';
        } else if (value == "已完成") {
            a = '<span style="color: #008000">' + value + '</span>';
        }
        return a;
    }
},
        {title: '任务派送者', field: 'getTaskUserName', visible: true, align: 'center', valign: 'middle', hidden: 'hidden'},
        {title: '测试者', field: 'testOfUserName', visible: true, align: 'center', valign: 'middle'},
        {title: '测试项目名称', field: 'projectName', visible: true, align: 'center', valign: 'middle'},
        {title: '测试模块名称', field: 'moduleName', visible: true, align: 'center', valign: 'middle'},
        {
            title: '查看看任务',align: 'center',
            events: operateEvents,//给按钮注册事件
            formatter: addTaskButton//表格中增加按钮  
        },


        {
            title: '操作',align: 'center',
            events: operateEvents,//给按钮注册事件
            formatter: allButton,//表格中增加按钮  ,

        },

    ];
};
function addTaskButton(value, row, index) {
    return [
        '<button id="showTaskBtn" type="button" class="btn-small btn-primary button-margin"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> 查看任务</button>',
    ].join('');
}
function allButton(value, row, index) {
    return [
        '<button id="redeployButton" type="button" class="btn-small btn-info button-margin" attr=\''+row.id+'\'><span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>任务转派</button>',
        '<button id="endButton" type="button" class="btn-small btn-info button-margin" attr=\''+row.id+'\'><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>完成</button>',
    ].join('');
}
window.operateEvents = {

    'click #redeployButton': function () {

        if (task.check()){
            var index = layer.open({
                type: 2,
                title: '选择下放用户',
                area: ['1000px', '500px'], //宽高
                fix: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/taskofuser/index?id=' + task.seItem.id+"&type=redeploy"
            });
            this.layerIndex = index;
        }

    },
    'click #endButton': function () {
        if (task.check()){
            var id = $("#endButton").attr("attr");
            var ajax = new $ax(Feng.ctxPath + "/myTask/changeStatus", function (data) {
                Feng.success("任务已完成!");

                parent.location.reload();
            }, function (data) {
                Feng.error("按钮状态异常!" + data.responseJSON.message + "!");
            });
            ajax.set("taskId",id);
            ajax.set("type","test");
            ajax.start();
        }
    },
    'click #showTaskBtn': function () {
        if (task.check()){
            var index = layer.open({
                type: 2,
                title: '我的任务',
                area: ['1000px', '500px'], //宽高
                fix: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/myTask/showTask/' + task.seItem.id
            });
            this.layerIndex = index;
        }

    }

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
 * 打开查看我的周报详情
 */
task.opentaskTestDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '我的周报详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/myTask/taskTest_detail/' + task.seItem.id
        });
        this.layerIndex = index;
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
    var table = new BSTable(task.id, "/myTask/progress", defaultColunms);
    table.setPaginationType("client");
    task.table = table.init();
});
