/**
 * 初始化我的周报详情对话框
 */
var taskOfUserInfoDlg = {
    taskOfUserInfoData : {}
};

/**
 * 清除数据
 */
taskOfUserInfoDlg.clearData = function() {
    this.taskOfUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
taskOfUserInfoDlg.set = function(key, val) {
    this.taskOfUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
taskOfUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
taskOfUserInfoDlg.close = function() {
    window.close();



}
/**
 * 收集数据
 */
taskOfUserInfoDlg.collectData = function() {
    this.set('id')
        .set('name')
        .set("roleName")
        .set('deptName')
        .set('email')
        .set('isDown');


}

taskOfUserInfoDlg.getTable=function(){
    var id =$('#id').val();
    var name =$('#name').val();
    var roleName =$('#roleName').val();
    var deptName =$('#deptName').val();
    var isDown =$('#isDown').val();
    return this.id;
}

/**
 * 提交添加
 */
// taskOfUserInfoDlg.addSubmit = function() {
//
//     this.clearData();
//     this.collectData() ;
//
//     //提交信息
//     var ajax = new $ax(Feng.ctxPath + "/taskofuser/taskdown/"+this.get('id'), function(data){
//         Feng.success("下放成功!");
//        // window.parent.module.table.refresh();
//         taskOfUserInfoDlg.close();
//         $('#moduleBtn').addClass('disabled');
//     },function(data){
//         Feng.error("下放失败!" + data.responseJSON.message + "!");
//     });
//     ajax.set(this.taskOfUserInfoData);
//     ajax.start();
// }



// $(document).ready(function () {
//     var id1 = taskOfUserInfoDlg.get("id");
//     var id = taskOfUserInfoDlg.getTable();
//     console.log('id='+id1);
// });


