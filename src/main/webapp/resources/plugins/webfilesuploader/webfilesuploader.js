/**
 * 基于Jquery的文件上传
 * author beyond
 * Version: 1.0.0
 **********************************************************************
 * @example
 * WebFilesUploader({},callback);
 **********************************************************************
 * WebFilesUploader的参数可配置项：
 *		    _title : [可选] [默认值：文件上传] 窗口标题文字;
 *	  	    _boxID : [不可修改] 弹出层ID(默认随机);
 *	 	  _content : [可选] 内容(可选内容为){ text | id | img | swf | url | iframe};
 *	 	    _width : [可选] [默认值：800px] 窗口宽度;
 *	 	   _height : [可选] [默认值：430px] 窗口离度;
 *	   _titleClass : [可选] 窗口标题样式名称;
 *	 	  _closeID : [可选] 关闭窗口ID;
 *	    _triggerID : [可选] 相对于这个ID定位;[暂时取消此功能]
 *	   _boxBdColor : [可选] [默认值："#E9F3FD"]弹出层外层边框颜色;
 *   _boxBdOpacity : [可选] [默认值："1",不透明]弹出层外层边框透明度,"1",不透明;
 * _boxWrapBdColor : [可选] [默认值：#000000]弹出层内部边框颜色;
 *  _windowBgColor : [可选] [默认值："#000000"]遮罩层背景颜色;
 *_windowBgOpacity : [可选] [默认值："0.5"]遮罩层背景透明度;
 *		     _time : [可选] [默认值：""]自动关闭等待时间;(单位毫秒);
 *		     _drag : [可选] [默认值："_boxTitle"]拖动手柄ID[当指定_triggerID的时候禁止拖动];
 * _dragBoxOpacity : [可选] 设置窗口拖动时窗口透明度(默认值:1,不透明);
 *	    _showTitle : [可选] [布尔值 默认为true]是否显示标题;
 *	    _showBoxbg : [可选] [布尔值 默认为true]是否显示弹出层背景;
 *		   _showbg : [可选] [布尔值 默认为true]是否显示遮罩层;
 *	  	   _button : [可选] [默认值：""]数组，要显示按钮的文字;
 *		 _callback : [可选] 回调函数，默认返回所选按钮显示的文字;
 *		  _offsets : [可选] [默认值："middle"]设定弹出层位置,默认居中;内置固定位置浮动:left-top(左上角);right-top(右上角);left-bottom(左下角);right-bottom(右下角);middle-top(居中置顶);middle-bottom(居中置低);left-middle(靠左居中);right-middle(靠右居中);
 *		      _fns : [可选] 弹出窗口后执行的函数;
 * 
 *    _webuploader : 文件上传的配置
 * 				dnd  {Selector} [不可修改] [默认值：#fileLists] 指定Drag And Drop拖拽的容器，如果不指定，则不启动。
 *  			pick {Selector, Object} [固定值] 指定选择文件的按钮容器，不指定则不创建按钮。
 * 					id [可选] {Seletor} [默认值：'#filePicker'] 指定选择文件的按钮容器，不指定则不创建按钮。
 * 					innerHTML [固定值] [默认值：'选择文件']{String} 指定按钮文字。不指定时优先从指定的容器中看是否自带文字。
 * 					multiple [可选] {Boolean} [默认值：true]{String} 是否开起同时选择多个文件能力。
 *  			accept {Arroy} [可选] [默认值：null] 指定接受哪些类型的文件。 由于目前还有ext转mimeType表，所以这里需要分开指定。
 * 					title {String} [可选] 文字描述
 * 					extensions [可选] {String} 允许的文件后缀，不带点，多个用逗号分割。
 * 					mimeTypes [可选] {String} 多个用逗号分割。
 * 				如：
 *					{
 *   					title: 'Images',
 *   					extensions: 'gif,jpg,jpeg,bmp,png',
 *  					mimeTypes: 'image/*'
 *					}
 * 				swf {String} [必填] ie7-ie9中需要使用flash上传文件,由{项目路径}+{Uploader.swf资源所在路径}
 * 							 如项目路径'/CDFDemo/',Uploader.swf资源所在路径'resources/js/web/plugins/webfilesuploader/Uploader.swf'
 * 							'/CDFDemo/resources/js/web/plugins/webfilesuploader/Uploader.swf'
 * 
 *  			server {String} [必填] 上传到服务器的地址,上传到服务器的地址,项目部署名称+‘/fileUpload'
 *  			auto {Boolean} [可选] [默认值：false] 设置为 true 后，不需要手动调用上传，有文件选择即开始上传。
 *				prepareNextFile {Boolean} [可选] [默认值：true] 是否允许在文件传输时提前把下一个文件准备好。 对于一个文件的准备工作比较耗时，比如图片压缩，md5序列化。 如果能提前在当前文件传输期处理，可以节省总体耗时。
 *				chunked {Boolean} [不可修改] [默认值：false] 是否要分片处理大文件上传。
 *  			fileVal {Object} [可选] [默认值：'file'] 设置文件上传域的name。
 *  			fileNumLimit {Integer} [可选] [默认值：10] 验证文件总数量, 超出则不允许加入队列。
 *				fileSizeLimit {Integer} [可选] [默认值：1G] 验证文件总大小是否超出限制, 超出则不允许加入队列。
 *				fileSingleSizeLimit {Integer} [可选] [默认值：100M] 验证单个文件大小是否超出限制, 超出则不允许加入队列。
 *				duplicate {Boolean} [可选] [默认值：true] 去重， 根据文件名字、文件大小和最后修改时间来生成hash Key.
 *  			disableGlobalDnd:disableGlobalDnd {Selector} [可选] [默认值：true] 是否禁掉整个页面的拖拽功能，如果不禁用，图片拖进来的时候会默认被浏览器打开。
 *
 *********************************************************************
 */
;
(function (window,undefined){
	// 命名空间
	 var WebFilesUploader = function(o,callback){
		 new  WebFilesUploader.fn.init(o,callback);
	 }; 
	 
	 WebFilesUploader.fn=WebFilesUploader.prototype={
		 constructor: WebFilesUploader,
		 init:function (o,callback) {
			 function getFilesId(obj){
				 var condition_one=typeof(obj._webuploader)!="undefined";
				 var condition_two=condition_one?typeof(obj._webuploader.pick)!="undefined":false;
				 
			     var condition_three=condition_two?typeof(obj._webuploader.pick.id)!="undefined":false;
					 
				 if(condition_one&&condition_two&&condition_three){
					 return obj.pick.id.substr(1);
				 }else{
					 return 'filePicker';
				 }
			 };
			 var id=getFilesId(o);
			 content=
				 '<div class="fileContent" id="fileContent">'+
				 	 '<div class="fileSelect">'+
				 	 '	<div id="'+ id +'" style="float:left">选择文件</div>'+
				 	 '</div>'+

				     '<div class="fileLists" id="fileLists">'+
				     ' <table class="tableQueue" id="tableQueue">'+
				     ' 	<tr>'+
				     ' 		<td>'+
				     ' 			文件名'+
				     '		</td>'+
				     ' 		<td>'+
				     '		 	文件大小'+
				     ' 		</td>'+
				     ' 		<td>'+
				     '    		文件后缀'+
				     ' 		</td>'+
				     ' 		<td>'+
				     ' 	 		上传进度'+
				     '  	</td>'+
				     ' 		<td>'+
				     '      	文件状态'+
				     ' 		</td>'+
				     ' 		<td>'+
				     ' 			操作'+
				     ' 		</td>'+
				     ' 	</tr>'+
				     ' </table>'+
				     '</div>'+
				     '<div class="fileStatusBar">'+
				     '  <div class="fileProgress">'+
				     '      <span class="fileToatalText">文件上传总进度：</span>'+
				     '		<span class="fileProgressText">0%</span>'+
				     '  	<span class="fileProgressPercentage"></span>'+
				     '  </div>'+
				     ' <div class="fileProgressInfo">请选择文件并上传</div>'+
				     '   <div class="fileUploadBtn">开始上传</div>'+
				     ' <div class="completeUploadBtn" style="display:none">已完成</div>'+
				     '</div>'+
				 '</div>',
			defaults = $.extend(true,{
	                _webuploader:$.extend(true,{
	                 	pick:{
		                    id:'#filePicker',
		                    innerHTML:'选择文件',
		                    multiple:true
		                },
		                dnd:'#fileLists',
		                auto:false,
		                fileVal:'file',
		                prepareNextFile:true,
		                duplicate:false,
		                paste: '#fileLists',
		                server:'/*/fileUpload',
		
		                accept: {
		                     title: '可接受文件类型',
		                     extensions: '*',
		                     mimeTypes: '*'
		                },
		
		 				disableGlobalDnd: true,
		                fileNumLimit:10,
		                chunked:false,
		                fileSizeLimit:1024 * 1024 * 1024, // 1G
		                fileSingleSizeLimit:100 * 1024 * 1024    // 100 M
		            }, o._webuploader),
		            _title:"文件上传",
		            _content:"text:"+content,
		            _width:"800",
		            _height:"430",
		            _titleClass:"boxTitle",
		            _closeID:"",
		            _triggerID:"",
		            _boxBdColor:"#E9F3FD",
		            _boxBdOpacity:"1",
		            _boxWrapBdColor:"#A6C9E1",
		            _windowBgColor:"#000000",
		            _windowBgOpacity:"0.5",
		            _time:"",
		            _drag:"_boxTitle",
		            _dragBoxOpacity:"1",
		            _showTitle:true,
		            _showBoxbg:true,
		            _showbg:true,
		            _offsets:"middle",
		            _button:"",
		            _callback:function () {
		            	
		            },
		            _fns:function () {
		            	
		            }
		        }, o);
				
			 	/**
			 	 * 初始化弹出框
			 	 */
				$.XYTipsWindow(defaults);
	            /**
	             * 初始化各个变量
	             * @type {*|HTMLElement}
	             */
	            var $wrap = $('#fileContent'),
	            
	                /**
	                 * 文件容器
	                 */
	                $queue = $wrap.find('.tableQueue'),
	
	                /**
	                 * 状态栏，包括进度和控制按钮
	                 */
	                $statusBar = $wrap.find('.fileStatusBar'),
	
	                /**
	                 * 文件总体选择信息
	                 */
	                $info = $statusBar.find('.fileProgressInfo'),
	
	                /**
	                 * 上传按钮
	                 */
	                $upload = $wrap.find('.fileUploadBtn'),
	
	                /**
	                 * 已完成按钮
	                 */
	                $completeUploadBtn = $wrap.find('.completeUploadBtn'),
	
	
	                /**
	                 * 文件总体进度条
	                 */
	                $progress = $statusBar.find('.fileProgress').hide(),
	
	                /**
	                 * 添加的文件数量
	                 */
	                fileCount = 0,
	
	                /**
	                 * 添加的文件总大小
	                 */
	                fileSize = 0,
	
	                /**
	                 * 自定义文件状态pedding, ready, uploading, confirm, done.
	                 * 默认pedding    表示文件队列需要装载文件
	                 * ready    文件已准备好，可以上传
	                 * uploading    文件正在上传中
	                 *
	                 */
	                state = 'pedding',
	
	                /**
	                 * 所有文件的进度信息，key为file id
	                 */
	                percentages = {},
	
	                /**
	                 * WebUploader实例
	                 */
	                uploader;
	            
	               /**
	                *  检测是否已经安装flash，检测flash的版本
	                * @returns {} 
	                */
		            if ( !WebUploader.Uploader.support() ) {
		                alert( 'Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
		                throw new Error( 'WebUploader does not support the browser you are using.' );
		            }

		            // 实例化
		            uploader = WebUploader.create(defaults._webuploader);
		            WebFilesUploader.uploader=uploader;
		            /**
		             * 当有文件添加进来时执行，负责view的创建
		             * @param file
		             */
		            function addFile(file) {
		                var $tr = $('<tr id="' + file.id + '">' +
		                        '<td>' + file.name + '</td>' +
		                        '<td>' + file.size + '</td>' +
		                        '<td>' + file.ext + '</td>' +
		                        '<td>'+ 
		                        '	<div class="fileSpanProgress">' +
		                        '		<span class="fileSpanProgressText">0%</span>' +
		                        '		<span class="fileSpanProgressPercentage"></span>' +
		                        '	</div>'+
		                        '</td>' +
		                        '<td><span class="fileState">等待上传</span></td>' +
		                        '</tr>'),
		                    $deleteFile = $('<td><span class="deleteFile">删除</span></td>').appendTo($tr);
		                   
		                	
		                    showError = function (code,fileObj) {
		                        var $fileObj=$("#"+fileObj.id);
				                    $fileState = $fileObj.find('.fileState'),
				                    $fileSpanProgress=$fileObj.find('.fileSpanProgress'),
				                	$fileSpanProgressText=$fileObj.find('.fileSpanProgressText'),
				                	$fileSpanProgressPercentage=$fileObj.find('.fileSpanProgressPercentage'),
			                        text = '';
		                        switch (code) {
		                            case 'inited':
		                                text = '初始状态';
		                                break;
		                            case 'queued':
		                                text = '等待上传';
		                                break;
		                            case 'progress':
		                                text = '正在上传';
		                                break;
		                            case 'complete':
		                                text = '上传完成';
		                                break;
		                            case 'interrupt':
		                                text = '上传中断，可续传';
		                                break;
		                            case 'cancelled':
		                            	text = '文件被移除';
			                            break;
		                            case 'abort':
		                            	text = '未连接到服务器';
		                            	$fileSpanProgress.css('border',"1px solid #FF0000");
		                            	$fileSpanProgressText.html('0%');
		                            	$fileSpanProgressText.css('color', '#FF0000');
		                            	$fileSpanProgressPercentage.css('width', '0%');
		                                $fileState.addClass('errorMessage');
			                            break;
		                            default:
		                                text = '上传失败';
		                            	$fileSpanProgress.css('border',"1px solid #FF0000");
		                            	$fileSpanProgressText.html('0%');
		                            	$fileSpanProgressText.css('color', '#FF0000');
		                            	$fileSpanProgressPercentage.css('width', '0%');
		                                $fileState.addClass('errorMessage');
		                                break;
		                        }
		                        $fileState.html(text);
		                    };

		                $deleteFile.on('click', function () {
		                    uploader.removeFile(file,true);
		                });

		                file.on('statuschange', function (cur, prev) {
		                	
		                    //文件状态
		                	if (cur === 'progress') {
		                        showError(cur,this);
		                        $progress.css('display', 'block');
		                    } else {
		                        showError(cur,this);
		                    }
		                });

		                percentages[ file.id ] = [ file.size, 0 ];
		                $tr.appendTo($queue);
		            }

		            // 负责view的销毁
		            WebFilesUploader.removeFile=function(file) {
		            	fileCount--;
		                fileSize -= file.size;

		                if (!fileCount) {
		                    setState('pedding');
		                }
		                var $tr = $('#' + file.id);

		                delete percentages[ file.id ];
		                /**
		                 * 先删除注册在.deleteFile上的事件，然后回到当前元素，在删除此元素
		                 */
		                $tr.find('.deleteFile').off().end().remove();
		                var queue = uploader.getFiles("queued");
		                if (queue.length > 0||fileCount<=0) {
		                    $upload.css('display', 'block');
		                    $completeUploadBtn.css('display', 'none');
		                } else {
		                    $upload.css('display', 'none');
		                    $completeUploadBtn.css('display', 'block');
		                }
		                updateStatus();
		            }

		            function updateTotalProgress() {
		                var loaded = 0,
		                    total = 0,
		                    spans = $progress.children(),
		                    percent;

		                $.each(percentages, function (k, v) {
		                    total += v[ 0 ];
		                    loaded += v[ 0 ] * v[ 1 ];
		                });

		                percent = total ? loaded / total : 0;

		                var $fileProgressText = $progress.find('.fileProgressText');
		                var $fileProgressPercentage = $progress.find('.fileProgressPercentage');

		                $fileProgressText.text(Math.round(percent * 100) + '%');
		                $fileProgressPercentage.css('width', Math.round(percent * 100) + '%');
		                updateStatus();
		            }

		            function updateStatus() {
		                var text = '', stats;

		                if (state === 'ready') {
		                    text = '选中' + fileCount + '份文件，共' +
		                        WebUploader.formatSize(fileSize) + '。';
		                } else if (state === 'confirm') {
		                    stats = uploader.getStats();
		                    if (stats.uploadFailNum) {
		                        text = '已成功上传' + stats.successNum + '份文件，' +
		                            stats.uploadFailNum + '份文件上传失败'
		                    }
		                } else {
		                    stats = uploader.getStats();
		                    text = '共' + fileCount + '份（' +
		                        WebUploader.formatSize(fileSize) +
		                        '），已上传' + stats.successNum + '份';

		                    if (stats.uploadFailNum) {
		                        text += '，失败' + stats.uploadFailNum + '份';
		                    }
		                }

		                $info.html(text);
		            }

		            function setState(val) {
		                var stats;

		                if (val === state) {
		                    return;
		                }

		                state = val;

		                switch (state) {
		                    case 'pedding':
		                    case 'ready':
		                    	 var pausedBtn=$(".fileUploadBtn");
		                    	 pausedBtn.html("开始上传");
		                         uploader.refresh();
		                         break;
		                         
		                    case 'uploading':
		                    	 var pausedBtn=$(".fileUploadBtn");
		                    	 pausedBtn.html("暂停上传");
		                    	 break;
		                    	 
		                    case 'paused':
		                    	var pausedBtn=$(".fileUploadBtn");
		                    	pausedBtn.html("继续上传");
		                        $progress.show();
		                        break;

		                    case 'confirm':
		                        $progress.hide();
		                        $upload.css('display', 'none');
		                        $completeUploadBtn.css('display', 'block');
		                        stats = uploader.getStats();
		                        if (stats.successNum && !stats.uploadFailNum) {
		                            setState('finish');
		                            return;
		                        }
		                        break;
		                    case 'finish':
		                        stats = uploader.getStats();
		                        if (stats.successNum) {
		                        } else {
		                            state = 'done';
		                            location.reload();
		                        }
		                        break;
		                    case 'error':
		                        stats = uploader.getStats();
		                        if (stats.uploadFailNum) {
		                        } else {
		                            state = 'done';
		                            location.reload();
		                        }
		                        break;
		                }

		                updateStatus();
		            };

		            uploader.onUploadProgress = function (file, percentage) {
		                var $tr = $('#' + file.id),
		                    $progressText = $tr.find('.fileSpanProgressText'),
		                    $percent = $tr.find('.fileSpanProgressPercentage');

						if(file.statusText==="error"||file.statusText==="invalid"||file.statusText==="abort"){
							percentage=0;
						}
					    $progressText.html(Math.round(percentage * 100) + '%');
		                $percent.css('width', Math.round(percentage * 100) + '%');
		                percentages[ file.id ][ 1 ] = percentage;
		                updateTotalProgress();
		            };

//		            uploader.onUploadError = function (file, code) {
//		            	file.setStatus(code,code);
//		            };
		            
		            uploader.onUploadAccept = function (object, ret) {
		                var $tr = $('#' + object.file.id).children().eq(0);
		                if(ret!=null){
		                  	var params="{UUID:"+ret.UUID;
		                  	params+=",originalFilename:"+ret.originalFilename;
			                params+=",fileName:"+ret.fileName;
			                params+=",filePrefix:"+ret.filePrefix;
			                params+=",fileSuffix:"+ret.fileSuffix;
			                params+=",fileSize:"+ret.fileSize+"}";
			                var input = "<input class='" + ret.code + "' type='hidden' value='" +ret._raw + "'/>";
			                $(input).appendTo($tr);
//			                callback($.parseJSON(ret._raw));
		                }
		              
		            };

		            uploader.onFileQueued = function (file) {
		                fileCount++;
		                fileSize += file.size;

		                if (fileCount === 1) {
		                    $statusBar.show();
		                }
		                file.setStatus("queued");

		                $upload.css('display', 'block');
		                $completeUploadBtn.css('display', 'none');
		                addFile(file);
		                setState('ready');
		                updateTotalProgress();
		            };

		            uploader.onFileDequeued = function (file) {
//		                fileCount--;
//		                fileSize -= file.size;
//
//		                if (!fileCount) {
//		                    setState('pedding');
//		                }

		            	WebFilesUploader.removeFile(file);
		            };

		            uploader.on('all', function (type) {
		                var stats;
		                switch (type) {
		                    case 'uploadFinished':
		                        setState('confirm');
		                        break;

		                    case 'startUpload':
		                        setState('uploading');
		                        break;

		                    case 'stopUpload':
		                        setState('paused');
		                        break;
		                        
		                    case 'uploadError':
		                    	setState('error');
			                    break;
		                }
		            });

		            uploader.onError = function (code) {
		                var errorMessage = "";
		                switch (code) {
		                    case 'F_EXCEED_SIZE':
		                        errorMessage = '文件大小超出指定值'+(defaults._webuploader.fileSingleSizeLimit/1048576)+"M";
		                        alert(errorMessage);
		                        break;
		                    case 'F_DUPLICATE':
		                        errorMessage = '此文件已被添加';
		                        alert(errorMessage);
		                        break;
		                    case 'Q_TYPE_DENIED':
		                    	errorMessage = '此文件类型不符';
			                    alert(errorMessage);
		                    	break;
		                    default:
		                        errorMessage = '添加文件出错';
		                        alert(code);
		                        break;
		                }

		            };

		            $completeUploadBtn.on('click', function () {
		                var file_success_info = $(".FILE_SUCCESS_CODE");
		                var array = new Array();
		                $.each(file_success_info, function (k, v) {
		                	var value=v.value;
		                	var obj=new Array(value);
		                	array.push(obj);
		                });
		                $.XYTipsWindow.removeBox();
		                var jsonText="["+array.toString()+"]";
//		                var json=arr=eval('('+jsonText+')');
		                callback($.parseJSON(jsonText));
		            });

		            $upload.on('click', function () {

		                if (state === 'ready') {
		                    uploader.upload();
		                } else if (state === 'paused') {
		                    uploader.upload();
		                } else if (state === 'uploading') {
		                    uploader.stop();
		                }
		            });
		            return this;
		        }
	 };
	 
	 window.WebFilesUploader = WebFilesUploader;
})(window);