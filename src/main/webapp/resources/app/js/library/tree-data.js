
var setting = {
	async : {
		enable : true,
		url : "tree/getSubFolders",// 获取子节点
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
	treeData.targetGuid = treeNode.id;// 将选中的值放在临时的json中
	refreshFldPath(treeNode.id);// 文件夹树点击的时候会生成该节点的路径
	// 并且放在id为folderPath的控件中
}


jQuery(function($) {
$.fn.zTree.init($("#treeDemo"), setting, zNodes);
})