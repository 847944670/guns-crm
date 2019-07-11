@/*
表单中input框标签中各个参数的说明:

hidden : input hidden框的id
id : input框id
name : input框名称
readonly : readonly属性
clickFun : 点击事件的方法名
style : 附加的css属性
@*/
    <div class="col-sm-9">
        <textarea class="form-control" id="${id}" name="${id}" cols="30" rows="20"
               @if(isNotEmpty(value)){
               value="${tool.dateType(value)}"
               @}

               @if(isNotEmpty(placeholder)){
               placeholder="${placeholder}"
               @}
        />

    </div>

