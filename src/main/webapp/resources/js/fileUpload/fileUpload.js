var setting = {
	"status" : "N",
	"orderby" : "none",
	"order" : "none",
	"userName" : "current"
}

var checkedFiles = [];

// 初始化选择文件夹的树的数据和配置信息
var fNodes = [];
var fsetting = {
	async : {
		enable : true,
		url : "../folder/tree/subfolders",// 获取子节点
		autoParam : [ "id", "type", "parentId" ],// 传递的参数
		type : "GET"
	},
	callback : {// 事件回调
		onClick : fTreeOnClick,
		onDblClick : fTreeOnDblClick
	},
	view : {
		dblClickExpand : false
	// 关闭双击展开
	},
	data : {
		keep : {
			parent : true
		}
	}

};

// 文件夹树的双击事件，双击关闭树
function fTreeOnDblClick(event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("treeFolders");
	treeObj.expandNode(treeNode, false, false, false);
}
// 文件夹树的单击事件,点击选中并且展开树
function fTreeOnClick(event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("treeFolders");
	if (!treeNode.open) {
		treeObj.expandNode(treeNode, true, false, false);
	}
	refreshFldPath(treeNode.id);// 文件夹树点击的时候会生成该节点的路径
	// 并且放在id为folderPath的控件中
}

$(function() {

	$("#fileModal").click(function() {
		var folderGuid = $("#rootFolderGuid").attr("name");
		WebFilesUploader({
			_webuploader : {
				server : '/ecm/file/upload/' + folderGuid,
				swf : '/ecm/resources/plugins/webfilesuploader/Uploader.swf',
			// fileSingleSizeLimit : '1024M'
			}
		}, function(jsons) {
			refreshBox(setting);
		});
	});

	// basic initializations
	$('.message-list .message-item input[type=checkbox]').removeAttr('checked');
	$('.message-list')
			.on(
					'click',
					'.message-item input[type=checkbox]',
					function() {
						$(this).closest('.message-item')
								.toggleClass('selected');
						var fileId = $(this).attr("id");
						var fileType = $(this).attr("name");
						var fileRow = {
							id : fileId,
							type : fileType
						}
						if (this.checked) {
						checkedFiles.add(fileRow);
						
						}

						else {

							checkedFiles.remove(fileRow);
						}
						Inbox
								.display_bar($('.message-list input[type=checkbox]:checked').length);
						console.log("获选中的文件信息数据---------")
						console.log(checkedFiles);
					});
	// check/uncheck all messages
	$('#id-toggle-all').removeAttr('checked').on('click', function() {
		if (this.checked) {
			Inbox.select_all();
		} else
			Inbox.select_none();
	});

	// select all
	$('#id-select-message-all').on('click', function(e) {
		e.preventDefault();
		Inbox.select_all();
	});

	$('#deleteAll').on('click', function() {
		deleteAll(checkedFiles);
	});

	$('#downlaodAll').on('click', function() {

	});
	$('#moveAll').on('click', function() {

	});

	var Inbox = {
		// displays a toolbar according to the number of selected messages
		display_bar : function(count) {
			$('#id-message-list-navbar .badge').text(count);
			if (count == 0) {

				$('#id-toggle-all').removeAttr('checked');
				$('#id-message-list-navbar .message-toolbar').addClass('hide');
				$('#id-message-list-navbar .fileupload').removeClass('hide');

			} else {

				$('#id-message-list-navbar .fileupload').addClass('hide');
				$('#id-message-list-navbar .message-toolbar').removeClass(
						'hide');
			}
		},
		select_all : function() {
			var count = 0;
			$('.message-item input[type=checkbox]').each(function() {
				this.checked = true;
				$(this).closest('.message-item').addClass('selected');
				var fileId = $(this).attr("id");
				var fileType = $(this).attr("name");
				var fileRow = {
					id : fileId,
					type : fileType
				}
				checkedFiles.add(fileRow);
				count++;
			});

			$('#id-toggle-all').get(0).checked = true;
			console.log("获取文件信息数据---------")
			console.log(checkedFiles);
			Inbox.display_bar(count);
		},
		select_none : function() {
			$('.message-item input[type=checkbox]').removeAttr('checked')
					.closest('.message-item').removeClass('selected');
			$('#id-toggle-all').get(0).checked = false;
			checkedFiles.splice(0, checkedFiles.length);// 清空数组

			console.log("获取文件信息数据---------")
			console.log(checkedFiles);
			Inbox.display_bar(0);
		},
		select_unread : function() {
			$('.message-item:not(.message-unread) input[type=checkbox]')
					.removeAttr('checked').closest('.message-item')
					.removeClass('selected');

			var count = 0;
			$('.message-unread input[type=checkbox]').each(function() {
				this.checked = true;
				$(this).closest('.message-item').addClass('selected');
				count++;
			});

			Inbox.display_bar(count);
		},
		refresh_inbox : function() {
			refreshBox(setting);
		}
	}
	$.extend($.gritter.options, {
		class_name : 'gritter-light', // for light notifications (can be added
		// directly to $.gritter.add too)
		position : 'bottom-right', // possibilities: bottom-left, bottom-right,
		// top-left, top-right
		fade_in_speed : 1000, // how fast notifications fade in (string or
		// int)
		fade_out_speed : 1000, // how fast the notices fade out
		time : 2000
	// hang on the screen for...
	});

	$('#createFolder').on('click', function() {
		$('#modal-folder').modal('show');
	})
	/**
	 * 新建文件窗口关闭的时候清空form中的数据和样式
	 */
	$("#modal-folder").on("hidden.bs.modal", function() {
		$('#fFolderName').val("");
	});
	$("#submitFolder").on('click', function() {
		var parentGuid = $("#rootFolderGuid").attr("name");
		var folderName = $("#fFolderName").val();
		var params = {
			"parentGuid" : parentGuid,
			"folderName" : folderName
		};

		$.ajax({
			url : "../folder/create",
			type : "POST",
			data : JSON.stringify(params),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if (data.success) {
					$('#modal-folder').modal('hide');
					refreshBox(setting);
					$.gritter.add({
						title : '<h3 class="success">提醒</h3>',
						text : '新建文件夹成功',
						sticky : false,
						class_name : 'gritter-light'
					});
				}
			},
			error : function(msg) {
				alert("服务器异常！" + msg);
			}
		});

	});

	$("#submitShare").on("click", function() {
		var params = {};
		$('#shareForm input[type=checkbox]').each(function() {
			var name = $(this).attr("name");
			var value = this.checked;
			if (value) {
				params[name] = "Y";
			} else {
				params[name] = "N";
			}

		});
		if (params.needPassword == 'Y') {
			var password = $('#password').val();
			if (password.trim() != "") {
				params['password'] = $('#password').val();
			} else {
				return;
			}

		}
		params['expirationDate'] = $('#expirationDate').val();
		params['docId'] = $('#docId').val();
		console.log(params);
		$.ajax({
			url : "../fileinfo/share/update",
			type : "PUT",
			data : JSON.stringify(params),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if (data.success) {
					messageGitter("保存文件分享信息成功")
				} else {
					messageGitter("保存文件分享信息失败！" + data.message);
				}
				$('#modal-share').modal('hide');
			},
			error : function(msg) {
				alert("服务器异常！" + msg);
			}
		});

	});

	Handlebars.registerHelper("transformat", function(value) {
		if (value == 'Y') {
			return "checked";
		}
	});
	
	
	$("#moveAll").on('click',function(){
		var canMove =true;
		for(var i=0;i<checkedFiles.length;i++){
			if(checkedFiles[i].type =='folder'){
				canMove =false;
				break;
			}
		}
		
		
		if(canMove){
			$.ajax({
				url : "../folder/tree/root",
				type : "GET",
				dataType : "json",
				contentType : "application/json",
				success : function(data) {
					if (data.success) {
						console.log("获取文件信息数据---------")
						console.log(data.data);
						$.fn.zTree.init($("#treeFolders"), fsetting, data.data);
						$("#modal-tree").modal('show');

					}
				},
				error : function(msg) {
					alert("服务器出错");
				}
			});
			
			$('#modal-tree').modal('show');
		}else{
			messageGitter("文件夹不允许移动")
		}
		
	});
	
	$("#subMove").on('click',function(){
		 var guid =$("#fFolderGuid").val();
		 if(guid==""){
			 return;
		 }
		$.ajax({
			url : "../folder/move/"+guid,
			type : "PUT",
			data : JSON.stringify(checkedFiles),
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				if (data.success) {
					messageGitter("移动文件成功")
					refreshBox(setting);	
				} else {
					messageGitter("移动文件失败！" + data.message);
				}
				$("#fFolderGuid").val("");
				$("#folderPath").empty();
				$('#modal-tree').modal('hide');
			},
			error : function(msg) {
				alert("服务器异常！" + msg);
			}
		});
	});
	

	$('#fileSearch').bind('keypress', function(event) {
		if (event.keyCode == "13") {
			var keyword = $('#fileSearch').val();
			if (keyword == "") {
				return;
			} else {
				$.ajax({
					url : "../fileinfo/search/" + setting.userName + "?order="
							+ setting.order + "&orderby=" + setting.orderby
							+  "&status=" + setting.status+ "&keyword=" + keyword,
					type : "GET",
					dataType : "json",
					contentType : "application/json",
					success : function(data) {
						if (data.success) {
							console.log("获取文件信息数据---------")
							console.log(data.data);
							$('.message-list').empty();
							$('.message-list').html(messageContenter(data.data));

						}
					},
					error : function(msg) {
						alert("服务器出错");
					}
				});
			}
		}
	});

});

var share = " <a href='#' onclick='shareFile(&apos;{{id}}&apos;)' class='blue' style='margin-left: 16px' title='分享'><i class='ace-icon fa fa-rss bigger-125'></i></a>";
var link = " <a href='#' onclick='showLink(&apos;{{id}}&apos;)' class='pink' style='margin-left: 16px' title='链接'> <i class='ace-icon fa fa-link bigger-125'></i></a>";
var folderLink = "  <span class='sender' title='{{name}}'><a href='#' onclick='goSubFolder(&apos;{{id}}&apos;)'> {{name}}</a></span>";
var fileName = "  <span class='sender' title='{{name}}'> {{name}}</span>";
function messageContenter(data) {
	var messageList = "";
	for ( var i = 0; i < data.length; i++) {
		var message = "<div class='message-item profile-activity'><label class='inline'> <input type='checkbox' id='{{id}}' name='{{type}}' class='ace'>"
				+ "	<span class='lbl'></span></label> <i class='message-star ace-icon fa {{icon}} bigger-150 '></i>  "
				+ "	{{namespan}} <span class='time'>{{time}}</span> <span class='summary'>"
				+ "	<span class='text'> {{size}} </span></span><span class='oper-btn'><div class='tools action-buttons'>"
				+ "	<a href='../{{type}}/download/{{id}}'  class='green' style='margin-left: 16px' title='下载'> <i class='ace-icon fa fa-download bigger-125'></i></a> "
				+ "	<a href='#' onclick='deleteSingle(&apos;{{type}}&apos;,&apos;{{id}}&apos;)' class='red' style='margin-left: 16px' title='删除'><i class='ace-icon fa fa-times bigger-125'></i></a>{{share}}{{link}} </div></span></div>";
		if (data[i].type == "folder") {
			message = message.replaceAll("{{icon}}",
					"fa-folder orange2 bigger-160");
			message = message.replaceAll("{{namespan}}", folderLink);
		} else
			(data[i].type == "file")
		{
			var icon = data[i].icon;
			message = message.replaceAll("{{icon}}", icon)
			message = message.replaceAll("{{namespan}}", fileName);
		}
		if (data[i].type == "file") {
			if (data[i].share != "Y") {
				message = message.replaceAll("{{share}}", share);
				message = message.replaceAll("{{link}}", "");
			} else {
				message = message.replaceAll("{{share}}", "");
				message = message.replaceAll("{{link}}", link);
			}

		} else {
			message = message.replaceAll("{{share}}", "");
			message = message.replaceAll("{{link}}", "");
		}

		message = message.replaceAll("{{id}}", data[i].id);
		message = message.replaceAll("{{name}}", data[i].name);
		message = message.replaceAll("{{type}}", data[i].type);
		message = message.replaceAll("{{time}}", data[i].time);
		message = message.replaceAll("{{size}}", data[i].size);

		messageList += message;
	}
	return messageList;
}

function refreshBox(setting) {
	var folderGuid = $("#rootFolderGuid").attr("name");
	$.ajax({
		url : "../fileinfo/folderfiles/" + setting.userName + "?order="
				+ setting.order + "&orderby=" + setting.orderby
				+ "&folderGuid=" + folderGuid + "&status=" + setting.status,
		type : "GET",
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			if (data.success) {
				console.log("获取文件信息数据---------")
				console.log(data.data);
				$('.message-list').empty();
				$('.message-list').html(messageContenter(data.data));
				
				checkedFiles.splice(0, checkedFiles.length);// 清空数组

				$('#id-toggle-all').removeAttr('checked');
				$('#id-message-list-navbar .message-toolbar').addClass('hide');
				$('#id-message-list-navbar .fileupload').removeClass('hide');
			}
		},
		error : function(msg) {
			alert("服务器出错");
		}
	});
}

function deleteSingle(type, id) {
	$.ajax({
		url : "../fileinfo/" + type + "/" + id,
		type : "DELETE",
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			var result = "";
			if (data.success) {
				result += "成功"
				refreshBox(setting);
			} else {
				result += "失败" + data.message;
			}

			$.gritter.add({
				title : '<h3>提醒</h3>',
				text : '删除数据' + result,
				sticky : false,
				class_name : 'gritter-light'
			});
		},
		error : function(msg) {
			alert('服务器处理出错');
		}
	});
}

function shareFile(id) {
	$.ajax({
		url : "../fileinfo/share/" + id,
		type : "PUT",
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			var result = "";
			if (data.success) {
				result += "成功"
			} else {
				result += "失败" + data.message;
			}
			$.gritter.add({
				title : '<h3>提醒</h3>',
				text : '分享成功' + result,
				sticky : false,
				class_name : 'gritter-light'
			});

			$('.action-buttons .pink').addClass('hide');
			link.relace('{{id}}', id)
			$('.tools .action-buttons').push(link);
		},
		error : function(msg) {
			alert('服务器处理出错');
		}
	});
}

function showLink(id) {

	$.ajax({
		url : "../fileinfo/link/" + id,
		type : "GET",
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			var result = "";
			if (data.success) {
				console.log(data.data);
				var myTemplate = Handlebars.compile($('#shareTpl').html());

				$('#shareForm').html(myTemplate(data.data));
				$('#expirationDate').datetimepicker({
					format : "yyyy-mm-dd hh:ii",
					language : 'zh-CN',
					autoclose : true,
					todayBtn : true,
					minuteStep : 10
				});
				$("#needPassword").on("click", function() {
					if (this.checked) {
						$('#password').removeAttr('readonly');
					} else {
						$('#password').attr('readonly', 'true');
					}
				});

				$('#modal-share').modal('show');
			}
		},
		error : function(msg) {
			alert('服务器处理出错');
		}
	});
}

function removeDatetime() {
	$('#expirationDate').val("");
}
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
}

function goSubFolder(folderGuid) {

	$.ajax({
		url : "../fileinfo/folderfiles/" + setting.userName + "?order="
				+ setting.order + "&orderby=" + setting.orderby
				+ "&folderGuid=" + folderGuid + "&status=" + setting.status,
		type : "GET",
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			if (data.success) {
				console.log("获取文件信息数据---------")
				console.log(data.data);
				$('.message-list').empty();
				$('.message-list').html(messageContenter(data.data));
				
				checkedFiles.splice(0, checkedFiles.length);// 清空数组

				$('#id-toggle-all').removeAttr('checked');
				$('#id-message-list-navbar .message-toolbar').addClass('hide');
				$('#id-message-list-navbar .fileupload').removeClass('hide');
			}
		},
		error : function(msg) {
			alert("服务器出错");
		}
	});
	refreshFolderPath(folderGuid)
}
function refreshFolderPath(rootFolderGuid) {
	$.ajax({
		url : "../folder/path/" + rootFolderGuid,
		type : "GET",
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			if (data.success) {
				console.log("获取文件信息数据---------")
				console.log(data.data);
				$(".breadcrumb").html(folderPath(data.data));
			}
		},
		error : function(msg) {
			alert("服务器出错");
		}
	});
}

function folderPath(data) {
	var folderPathTmp = "<li><i class='ace-icon fa fa-map-marker bigger-130 blue'></i></li>";
	var folderNode = "<li id='rootFolderGuid' name='{{folderGuid}}' class='active'>{{folderName}}</li>";
	var folderLink = "<li name='{{folderGuid}}' ></i> <a href='#' onclick='goSubFolder(&apos;{{folderGuid}}&apos;)' >{{folderName}}</a></li>";

	for ( var i = data.length - 1; i >= 0; i--) {
		if (i != 0 ) {
			folderPathTmp += folderLink.replaceAll("{{name}}", "").replaceAll(
					"{{folderGuid}}", data[i].folderguid).replaceAll(
					"{{class}}", "").replaceAll("{{folderName}}",
					data[i].foldername);

		} else {
			if(data[i].foldername=="我的云盘"){
				folderPathTmp +="<li id='rootFolderGuid'  name='{{folderGuid}}' ></i> <a href='#' onclick='goSubFolder(&apos;{{folderGuid}}&apos;)' >{{folderName}}</a></li>".replaceAll("{{name}}", "").replaceAll(
						"{{folderGuid}}", data[i].folderguid).replaceAll(
								"{{folderName}}", data[i].foldername);
			}else{
				folderPathTmp += folderNode.replaceAll("{{name}}", "").replaceAll(
						"{{folderGuid}}", data[i].folderguid).replaceAll(
						"{{folderName}}", data[i].foldername);
			}
			
		}
	}
	return folderPathTmp;
}

function deleteAll(list) {

	$.ajax({
		type : "DELETE",
		url : "../fileinfo/delete/all",
		dataType : "json",
		contentType : "application/json",
		data : JSON.stringify(list),
		success : function(data) {
			if(data.success){
				messageGitter("删除选中项成功");
				refreshBox(setting);	
			}else{
				messageGitter("删除选中项失败！"+data.message);
			}
		}
	});
}

function messageGitter(message) {

	$.gritter.add({
		title : '<h3>提醒</h3>',
		text : message,
		sticky : false,
		class_name : 'gritter-light'
	});
}

function refreshFldPath(id){
	$("#fFolderGuid").val(id);
	$.ajax({
		url : "../folder/path/" + id,
		type : "GET",
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			if (data.success) {
				console.log("获取文件信息数据---------")
				console.log(data.data);
				$("#folderPath").html(folderStrPath(data.data));
			}
		},
		error : function(msg) {
			alert("服务器出错");
		}
	});
}

function folderStrPath(data){
	var path ="";
	for ( var i = data.length - 1; i >= 0; i--) {
		path +=data[i].foldername+"/"
	}
	return path;
}

/**
 * 重新Array中获取对象的序号
 * 
 * @param val
 * @returns {Number}
 */
Array.prototype.indexOf = function(val) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i].id == val.id)
			return i;
	}
	return -1;
};
/**
 * 重写Array的删除某个对象
 * 
 * @param val
 */
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};
/**
 * 重写Array添加某个对象，重复不添加
 * 
 * @param val
 */
Array.prototype.add = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		return;
	} else {
		this.push(val);
	}
};

