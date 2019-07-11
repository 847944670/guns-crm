/**
 * web-upload 工具类
 *
 * 约定：
 * 上传按钮的id = 图片隐藏域id + 'BtnId'
 * 图片预览框的id = 图片隐藏域id + 'PreId'
 *
 * @author fengshuonan
 */

(function() {

    var $FileUpload = function(pictureId) {
        this.pictureId = pictureId;
        this.uploadBtnId = pictureId + "BtnId";
        this.uploadUrl = Feng.ctxPath + '/attachment/fileUpload';
        this.fileSizeLimit = 100 * 1024 * 1024;
        this.uploadBarId = null;
    };

    $FileUpload.prototype = {

        /**
         * 初始化FileUploader
         */
        init : function() {
            var uploader = this.create();
            this.bindEvent(uploader);
            return uploader;
        },

        /**
         * 创建webuploader对象
         */
        create : function() {
            var webUploader = WebUploader.create({
                auto : true,
                pick : {
                    id : '#' + this.uploadBtnId,
                    multiple : false,// 只上传一个
                },
                accept : 'file',
                disableGlobalDnd : true,
                duplicate : true,
                server : this.uploadUrl,
                fileSingleSizeLimit : this.fileSizeLimit
            });

            return webUploader;
        },

        /**
         * 绑定事件
         */
        bindEvent : function(bindedObj) {
            var me =  this;
            var fileId = "";
            // 当有文件被添加进队列的时候
            bindedObj.on( 'fileQueued', function( file ) {
                var date = new Date();
                fileId="u_"+date.getTime()
                var $list=$("#thelist");

                $list.append( '<div id="' + fileId + '" class="item">' +
                    '<h4 class="info">' + file.name + '</h4>' +
                    '<font color="#00008b" ><p class="state">等待上传...</p></font>' +
                    '</div>' );
            });


            // 文件上传过程中创建进度条实时显示。
            bindedObj.on('uploadProgress', function(file, percentage) {

                var $li = $( '#'+fileId ),
                    $percent = $li.find('.progress .progress-bar');

                // 避免重复创建
                if ( !$percent.length ) {
                    $("#" + fileId + ".state").text("文件上传中...");

                    if (!$percent.length) {
                        $percent = $('<div " class="progress progress-striped active" style="padding-bottom: 20px;">' +
                            '<div class="progress-bar" style="height:7px;background-color:dodgerblue "  role="progressbar" style="width: 9%">' +
                            '</div>' +
                            '</div>').appendTo($li).find('.progress-bar');
                    }

                    $li.find('p.state').text('上传中');
                    $percent.css('width',  '100%');
                }

            });

            bindedObj.on('uploadSuccess', function(file,response) {
                console.info(response.msg);
                if (response.msg =='202' ) {
                    Feng.error("文件上传失败!")
                    setTimeout(function(){
                        $('#'+fileId).remove();
                    },1000);


                }else {
                    $('.progress-striped').removeAttr('class','active');
                    $("#"+fileId+">font").find("p").text("文件上传成功");
                    value.push(response.msg);
                    fileIds.push(fileId);
                    $("#attachmentName").val(value.toString());
                    $('#fileId').val(fileIds.toString());
                    var attachName= "'"+response.msg+"'";
                    var attachArray="'["+value+"]'";
                    $("#"+fileId+">font").find("p").append('&nbsp;&nbsp;&nbsp;&nbsp;<a  href="javascript:void(0);"  style="text-decoration:underline;"  onclick="deleteFile('+fileId+","+attachName+')"><span class="glyphicon glyphicon-paperclip" aria-hidden="true"></span> 删除附件</a>')
                    $("#" + me.pictureId).val(response.msg);
                }


            });



            // 文件上传失败，显示上传出错。
            bindedObj.on('uploadError', function(file) {
                Feng.error("上传失败");
            });

            // 其他错误
            bindedObj.on('error', function(type) {
                if ("Q_EXCEED_SIZE_LIMIT" == type) {
                    Feng.error("文件大小超出了限制");
                } else if ("Q_TYPE_DENIED" == type) {
                    Feng.error("文件类型不满足");
                } else if ("Q_EXCEED_NUM_LIMIT" == type) {
                    Feng.error("上传数量超过限制");
                } else if ("F_DUPLICATE" == type) {
                    Feng.error("图片选择重复");
                } else {
                    Feng.error("上传过程中出错");
                }
            });

            // 完成上传完了，成功或者失败
            bindedObj.on('uploadComplete', function(file) {
            });
        },


        /**
         * 设置图片上传的进度条的id
         */
        setUploadBarId: function (id) {
            this.uploadBarId = id;
        }
    };
    function sleep(numberMillis) {
        var now = new Date();
        var exitTime = now.getTime() + numberMillis;
        while (true) {
            now = new Date();
            if (now.getTime() > exitTime)
                return;
        }
    }

    window.$FileUpload = $FileUpload;

}());

