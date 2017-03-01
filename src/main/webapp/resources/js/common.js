function EasyConfirm(title,content,callbak){
	$.teninedialog({
		title : title,
		content : content,
		otherButtons : [ "确定", "取消" ],
		otherButtonStyles : [ 'btn-danger', 'btn-success' ],
		showCloseButton : false,
		bootstrapModalOption : {
			keyboard : false
		},
		clickButton : function(sender, modal, index) {
			$(this).closeDialog(modal);
			if(index==0)callbak.apply();
		}
	});
}

function EasyAlert(title,content){
	$.teninedialog({
		title : title,
		content : content,
		otherButtons : [ "确定" ],
		otherButtonStyles : [ 'btn-danger' ],
		showCloseButton : false,
		bootstrapModalOption : {
			keyboard : false
		},
		clickButton : function(sender, modal, index) {
			$(this).closeDialog(modal);
		}
	});
}

function DateFmt(date){
	var d = date == undefined?new Date():new Date(date);
	var year = d.getFullYear();
	var month = d.getMonth()+1;
	var day = d.getDate();
	return year + "-" + (month<10?"0":"") + month + "-" + (day<10?"0":"")+ day;
}

function DateTimeFmt(date){
	var d = date == undefined?new Date():new Date(date);
	var year = d.getFullYear();
	var month = d.getMonth()+1;
	var day = d.getDate();
	var hour = d.getHours();
	var minute = d.getMinutes();
	var second = d.getSeconds();
	return year + "-" + (month<10?"0":"") + month + "-" + (day<10?"0":"") + day + " " +
		(hour<10?"0":"") + hour + ":" + (minute<10?"0":"") + minute + ":" +(second<10?"0":"")+ second;
}