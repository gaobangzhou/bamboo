/**
 * 基于Jquery的文件上传
 * author beyond
 * Version: 2.8.0
 **********************************************************************
 * @example
 * $("#example").XYTipsWindow(param1,param2);
 **********************************************************************
 * XYTipsWindow o参数可配置项：
 *		    _title : [可选] [默认值：文件上传] 窗口标题文字;
 *	  	    _boxID : [可选] 弹出层ID(默认随机);
 *	 	  _content : [可选] 内容(可选内容为){ text | id | img | swf | url | iframe};
 *	 	    _width : [可选] [默认值：800px] 窗口宽度;
 *	 	   _height : [可选] [默认值：530px] 窗口离度;
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
 *		 _callback : [可选] 回调函数，默认返回所选按钮显示的文 ;
 *		  _offsets : [可选] [默认值："middle"]设定弹出层位置,默认居中;内置固定位置浮动:left-top(左上角);right-top(右上角);left-bottom(左下角);right-bottom(右下角);middle-top(居中置顶);middle-bottom(居中置低);left-middle(靠左居中);right-middle(靠右居中);
 *		      _fns : [可选] 弹出窗口后执行的函数;
 * 
 *    _webuploader : 文件上传的配置
 * 				dnd  {Selector} [可选] [默认值：true] 指定Drag And Drop拖拽的容器，如果不指定，则不启动。
 *  			pick {Selector, Object} [固定值] 指定选择文件的按钮容器，不指定则不创建按钮。
 * 					id [固定值] {Seletor} [默认值：'#filePicker'] 指定选择文件的按钮容器，不指定则不创建按钮。
 * 					label [固定值] {String} 请采用 innerHTML 代替
 * 					innerHTML [固定值] [默认值：'选择文件']{String} 指定按钮文字。不指定时优先从指定的容器中看是否自带文字。
 * 				multiple [可选] {Boolean} [默认值：true]{String} 是否开起同时选择多个文件能力。
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
 *  			server {String} [可选] [默认值：'/web/file/upload'] 上传到服务器的地址
 *  			auto {Boolean} [可选] [默认值：false] 设置为 true 后，不需要手动调用上传，有文件选择即开始上传。
 *				prepareNextFile {Boolean} [可选] [默认值：true] 是否允许在文件传输时提前把下一个文件准备好。 对于一个文件的准备工作比较耗时，比如图片压缩，md5序列化。 如果能提前在当前文件传输期处理，可以节省总体耗时。
 *				chunked {Boolean} [可选] [默认值：false] 是否要分片处理大文件上传。
 *				chunkSize {Boolean} [可选] [默认值：512 * 1024 * 1024] 如果要分片，分多大一片？ 默认大小为5M.
 *  			fileVal {Object} [可选] [默认值：'file'] 设置文件上传域的name。
 *  			fileNumLimit {Integer} [可选] [默认值：20] 验证文件总数量, 超出则不允许加入队列。
 *				fileSizeLimit {Integer} [可选] [默认值：1G] 验证文件总大小是否超出限制, 超出则不允许加入队列。
 *				fileSingleSizeLimit {Integer} [可选] [默认值：100M] 验证单个文件大小是否超出限制, 超出则不允许加入队列。
 *				duplicate {Boolean} [可选] [默认值：true] 去重， 根据文件名字、文件大小和最后修改时间来生成hash Key.
 *  			min-height {Integer} [可选] [默认值：390px] 文件列表框的最小高度 
 *********************************************************************
 */
;
(function () {
    $.XYTipsWindow = function (o) {
        defaults = $.extend({
            _title:"文件上传",
            _boxID:boxID(10),
            _content:"text:文件上传插件",
            _width:"800",
            _height:"530",
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
        $.XYTipsWindow.init(defaults);
    };
    var BOXID, isIE6 = !-[1, ] && !window.XMLHttpRequest;
    var $XYTipsWindowarr = new Array();
    var boxID = function (n) {
        var Str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (var i = 0, r = ""; i < n; i++) {
            r += Str.charAt(Math.floor(Math.random() * 62));
        }
        ;
        return r;
    };
    $.extend($.XYTipsWindow, {
        //初始化
        init:function (o) {
            BOXID = o;
            if ($("#" + o._boxID).length > 0) {
                alert("对不起，创建弹出层失败！窗口“" + o._boxID + "”已存在！");
                return false;
            }
            ;
            var $box = $("#" + o._boxID);
            $.XYTipsWindow.showBox(o);
            $("._closeBox", $box).off().live("click",function () {
                $.XYTipsWindow.removeBox();
            }).css({zIndex:"870618"});
            if (o._closeID != "") {
                $("#" + o._closeID, $box).die().live("click", function () {
                    $.XYTipsWindow.removeBox();
                });
            }
            ;
            if (o._time != "") {
                setTimeout($.XYTipsWindow.removeBox, o._time);
            }
            ;
            if (o._showbg != "" && o._showbg == true) {
                var $boxBgDom = "<div id=\"XYTipsWindowBg\" style=\"position:absolute;background:" + o._windowBgColor + ";filter:alpha(opacity=0);opacity:0;width:100%;left:0;top:0;z-index:870618\"><iframe src=\"about:blank\" style=\"width=100%;height:" + $(document).height() + "px;filter:alpha(opacity=0);opacity:0;scrolling=no;z-index:870610\"></iframe></div>";
                $($boxBgDom).appendTo("body").animate({opacity:o._windowBgOpacity}, 200);
            }
            ;
            if (o._drag != "") {
                $.XYTipsWindow.dragBox(o);
            }
            ;
            if (o._fns != "" && $.isFunction(o._fns)) {
                o._fns.call(this);
            }
            ;
            $.XYTipsWindow.contentBox(o);
            if (o._button != "") {
                $.XYTipsWindow.ask(o);
            }
            ;
            $.XYTipsWindow.keyDown(o);
            $.XYTipsWindow.setBoxzIndex(o);
            if (o._showbg != true) {
                $("#" + o._boxID).addClass("shadow");
            }
            ;
            $("#" + o._boxID).die().live("mouseenter", function () {
                BOXID = o;
            });
            var $wrap = $('#fileContent'),
            	$fileContentMinHight=(parseInt(o._height))-140;
            	$wrap.find('.fileLists').css('min-height',$fileContentMinHight+"px");
        },
        getID:function () {
            return thisID = BOXID._boxID;
        },
        //构造弹出层
        showBox:function (o) {
            var $titleHeight = o._showTitle != true ? 1 : 33,
                $borderHeight = o._showTitle != true ? 0 : 10,
                $boxDialogHeight = o._button != "" ? 45 : 0,
                $boxDialogBorder = $boxDialogHeight == "0" ? "0" : "1";
            var $width = parseInt(o._width) > 1000 ? 1000 : parseInt(o._width),
                $height = parseInt(o._height) > 550 ? 550 : parseInt(o._height);
            var $boxDom = "<div id=\"" + o._boxID + "\" class=\"XYTipsWindow\">";
            $boxDom += "<div class=\"_boxWrap\">";
            $boxDom += "<div class=\"_boxTitle\"><h3></h3><span class=\"_closeBox\">关闭</span></div>";
            $boxDom += "<div class=\"_boxContent\"></div>";
            $boxDom += "<div class=\"_boxDialog\"></div>";
            $boxDom += "</div>";
            $boxDom += "<div class=\"_boxBd\"></div>";
            $boxDom += "<iframe src=\"about:blank\" style=\"position:absolute;left:0;top:0;filter:alpha(opacity=0);opacity:0;scrolling=no;z-index:10714\"></iframe>";
            $boxDom += "</div>";
            $($boxDom).appendTo("body");
            var $box = $("#" + o._boxID);
            $box.css({
                position:"relative",
                width:$width + 12 + "px",
                height:$height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                zIndex:"891208"
            });
            var $iframe = $("iframe", $box);
            $iframe.css({
                width:$width + 12 + "px",
                height:$height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px"
            });
            var $boxWrap = $("._boxWrap", $box);
            $boxWrap.css({
                position:"relative",
                top:"5px",
                margin:"0 auto",
                width:$width + 2 + "px",
                height:$height + $titleHeight + $boxDialogHeight + 1 + "px",
                overflow:"hidden",
                zIndex:"20590"
            });
            var $boxContent = $("._boxContent", $box);
            $boxContent.css({
                position:"relative",
                width:$width + "px",
                height:$height + "px",
                padding:"0",
                borderWidth:"1px",
                borderStyle:"solid",
                borderColor:o._boxWrapBdColor,
                overflow:"auto",
                backgroundColor:"#fff"
            });
            var $boxDialog = $("._boxDialog", $box);
            $boxDialog.css({
                width:$width + "px",
                height:$boxDialogHeight + "px",
                borderWidth:$boxDialogBorder + "px",
                borderStyle:"solid",
                borderColor:o._boxWrapBdColor,
                borderTop:"none",
                textAlign:"right"
            });
            var $boxBg = $("._boxBd", $box);
            $boxBg.css({
                position:"absolute",
                width:$width + 12 + "px",
                height:$height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                left:"0",
                top:"0",
                opacity:o._boxBdOpacity,
                background:o._boxBdColor,
                zIndex:"10715"
            });
            var $title = $("._boxTitle>h3", $box);
            $title.html(o._title);
            $title.parent().css({
                position:"relative",
                width:$width + "px",
                borderColor:o._boxWrapBdColor
            });
            if (o._titleClass != "") {
                $title.parent().addClass(o._titleClass);
                $title.parent().find("span").hover(function () {
                    $(this).addClass("hover");
                }, function () {
                    $(this).removeClass("hover");
                });
            }
            ;
            if (o._showTitle != true) {
                $("._boxTitle", $box).remove();
            }
            if (o._showBoxbg != true) {
                $("._boxBd", $box).remove();
                $box.css({
                    width:$width + 2 + "px",
                    height:$height + $titleHeight + $boxDialogHeight + 1 + "px"
                });
                $boxWrap.css({left:"0", top:"0"});
            }
            ;
            //定位弹出层
            var TOP = -1;
            $.XYTipsWindow.getDomPosition(o);
            var $location = o._offsets;
            var $wrap = $("<div id=\"" + o._boxID + "parent\"></div>");
            var est = isIE6 ? (o._triggerID != "" ? 0 : document.documentElement.scrollTop) : "";
            if (o._offsets == "" || o._offsets.constructor == String) {
                switch ($location) {
                    case("left-top")://左上角
                        $location = {left:"0px", top:"0px" + est};
                        TOP = 0;
                        break;
                    case("left-bottom")://左下角
                        $location = {left:"0px", bottom:"0px"};
                        break;
                    case("right-top")://右上角
                        $location = {right:"0px", top:"0px" + est};
                        TOP = 0;
                        break;
                    case("right-bottom")://右下角
                        $location = {right:"0px", bottom:"0px"};
                        break;
                    case("middle-top")://居中置顶
                        $location = {left:"50%", marginLeft:-parseInt($box.width() / 2) + "px", top:"0px" + est};
                        TOP = 0;
                        break;
                    case("middle-bottom")://居中置低
                        $location = {left:"50%", marginLeft:-parseInt($box.width() / 2) + "px", bottom:"0px"};
                        break;
                    case("left-middle")://左边居中
                        $location = {left:"0px", top:"50%" + est, marginTop:-parseInt($box.height() / 2) + "px" + est};
                        TOP = $getPageSize[1] / 2 - $box.height() / 2;
                        break;
                    case("right-middle")://右边居中
                        $location = {right:"0px", top:"50%" + est, marginTop:-parseInt($box.height() / 2) + "px" + est};
                        TOP = $getPageSize[1] / 2 - $box.height() / 2;
                        break;
                    default://默认为居中
                        $location = {left:"50%", marginLeft:-parseInt($box.width() / 2) + "px", top:"50%" + est, marginTop:-parseInt($box.height() / 2) + "px" + est};
                        TOP = $getPageSize[1] / 2 - $box.height() / 2;
                        break;
                }
                ;
            } else {
                var str = $location.top;
                $location.top = $location.top + est;
                if (typeof(str) != 'undefined') {
                    str = str.replace("px", "");
                    TOP = str;
                }
                ;
            }
            ;
            if (o._triggerID != "") {
                var $offset = $("#" + o._triggerID).offset();
                var triggerID_W = $("#" + o._triggerID).outerWidth(), triggerID_H = $("#" + o._triggerID).outerHeight();
                var triggerID_Left = $offset.left, triggerID_Top = $offset.top;
                var vL = $location.left, vT = $location.top;
                if (typeof(vL) != 'undefined' || typeof(vT) != 'undefined') {
                    vL = parseInt(vL.replace("px", ""));
                    vT = parseInt(vT.replace("px", ""));
                }
                ;
                var _left = vL >= 0 ? parseInt(vL) + triggerID_Left : parseInt(vL) + triggerID_Left - $getPageSize[2];
                var _top = vT >= 0 ? parseInt(vT) + triggerID_Top : parseInt(vT) + triggerID_Top - $getPageSize[3];
                $location = {left:_left + "px", top:_top + "px"};
            }
            ;
            if (isIE6) {
                if (o._triggerID == "") {
                    if (TOP >= 0) {
                        $.XYTipsWindow.addStyle(".ui_fixed_" + o._boxID + "{width:100%;height:100%;position:absolute;left:expression(documentElement.scrollLeft+documentElement.clientWidth-this.offsetWidth);top:expression(documentElement.scrollTop+" + TOP + ")}");
                        $wrap = $("<div class=\"" + o._boxID + "IE6FIXED\" id=\"" + o._boxID + "parent\"></div>");
                        $box.appendTo($wrap);
                        $("body").append($wrap);
                        $("." + o._boxID + "IE6FIXED").css($location).css({
                            position:"absolute",
                            width:$width + 12 + "px",
                            height:$height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                            zIndex:"891208"
                        }).addClass("ui_fixed_" + o._boxID);
                    } else {
                        $.XYTipsWindow.addStyle(".ui_fixed2_" + o._boxID + "{width:100%;height:100%;position:absolute;left:expression(documentElement.scrollLeft+documentElement.clientWidth-this.offsetWidth);top:expression(documentElement.scrollTop+documentElement.clientHeight-this.offsetHeight)}");
                        $wrap = $("<div class=\"" + o._boxID + "IE6FIXED\"  id=\"" + o._boxID + "parent\"></div>");
                        $box.appendTo($wrap);
                        $("body").append($wrap);
                        $("." + o._boxID + "IE6FIXED").css($location).css({
                            position:"absolute",
                            width:$width + 12 + "px",
                            height:$height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                            zIndex:"891208"
                        }).addClass("ui_fixed2_" + o._boxID);
                    }
                    ;
                    $("body").css("background-attachment", "fixed").css("background-image", "url(n1othing.txt)");
                } else {
                    $wrap.css({
                        position:"absolute",
                        left:_left + "px",
                        top:_top + "px",
                        width:$width + 12 + "px",
                        height:$height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                        zIndex:"891208"
                    });
                }
                ;
            } else {
                $wrap.css($location).css({
                    position:"fixed",
                    width:$width + 12 + "px",
                    height:$height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                    zIndex:"891208"
                });
                if (o._triggerID != "") {
                    $wrap.css({position:"absolute"})
                }
                ;
                $("body").append($wrap);
                $box.appendTo($wrap);
            }
            ;
        },
        //装载弹出层内容
        contentBox:function (o) {
            var $box = $("#" + o._boxID);
            var $width = parseInt(o._width) > 1000 ? 1000 : parseInt(o._width),
                $height = parseInt(o._height) > 550 ? 550 : parseInt(o._height);
            var $contentID = $("._boxContent", $box);
            $contentType = o._content.substring(0, o._content.indexOf(":"));
            $content = o._content.substring(o._content.indexOf(":") + 1, o._content.length);
            $.ajaxSetup({global:false});
            switch ($contentType) {
                case "text":
                    $contentID.html($content);
                    break;
                case "id":
                    $("#" + $content).children().appendTo($contentID);
                    break;
                case "img":
                    $.ajax({
                        beforeSend:function () {
                            $contentID.html("<p class='boxLoading'>loading...</p>");
                        },
                        error:function () {
                            $contentID.html("<p class='boxError'>加载数据出错...</p>");
                        },
                        success:function (html) {
                            $contentID.html("<img src=" + $content + " alt='' />");
                        }
                    });
                    break;
                case "swf":
                    $.ajax({
                        beforeSend:function () {
                            $contentID.html("<p class='boxLoading'>loading...</p>");
                        },
                        error:function () {
                            $contentID.html("<p class='boxError'>加载数据出错...</p>");
                        },
                        success:function (html) {
                            $contentID.html("<div id='" + o._boxID + "swf'><h1>Alternative content</h1><p><a href=\"http://www.adobe.com/go/getflashplayer\"><img src=\"http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif\" alt=\"Get Adobe Flash player\" /></a></p></div><script type=\"text/javascript\" src=\"swfobject.js\" ></script><script type=\"text/javascript\">swfobject.embedSWF('" + $content + "', '" + o._boxID + "swf', '" + $width + "', '" + $height + "', '9.0.0', 'expressInstall.swf');</script>");
                            $("#" + o._boxID + "swf").css({
                                position:"absolute",
                                left:"0",
                                top:"0",
                                textAlign:"center"
                            });
                        }
                    });
                    break;
                case "url":
                    var contentDate = $content.split("?");
                    $.ajax({
                        beforeSend:function () {
                            $contentID.html("<p class='boxLoading'>loading...</p>");
                        },
                        type:contentDate[0],
                        url:contentDate[1],
                        data:contentDate[2],
                        error:function () {
                            $contentID.html("<p class='boxError'><em></em><span>加载数据出错...</span></p>");
                        },
                        success:function (html) {
                            $contentID.html(html);
                        }
                    });
                    break;
                case "iframe":
                    $contentID.css({overflowY:"hidden"});
                    $.ajax({
                        beforeSend:function () {
                            $contentID.html("<p class='boxLoading'>loading...</p>");
                        },
                        error:function () {
                            $contentID.html("<p class='boxError'>加载数据出错...</p>");
                        },
                        success:function (html) {
                            $contentID.html("<iframe src=\"" + $content + "\" width=\"100%\" height=\"" + parseInt(o._height) + "px\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
                        }
                    });
            }
            ;
        },
        //对话模式
        ask:function (o) {
            var $box = $("#" + o._boxID);
            $boxDialog = $("._boxDialog", $box);
            if (o._button != "") {
                var map = {}, answerStrings = [];
                if (o._button instanceof Array) {
                    for (var i = 0; i < o._button.length; i++) {
                        map[o._button[i]] = o._button[i];
                        answerStrings.push(o._button[i]);
                    }
                    ;
                } else {
                    for (var k in o._button) {
                        map[o._button[k]] = k;
                        answerStrings.push(o._button[k]);
                    }
                    ;
                }
                ;
                $boxDialog.html($.map(answerStrings,function (v) {
                    return "<input class='dialogBtn' type='button'  value='" + v + "' />";
                }).join(' '));
                $(".dialogBtn", $boxDialog).hover(function () {
                    $(this).addClass("hover");
                },function () {
                    $(this).removeClass("hover");
                }).click(function () {
                        var $this = this;
                        if (o._callback != "" && $.isFunction(o._callback)) {
                            //设置回调函数返回值很简单，就是回调函数名后加括号括住的返回值就可以了。
                            o._callback(map[$this.value]);
                        }
                        ;
                        $.XYTipsWindow.removeBox(o);
                    });
            }
            ;
        },
        //获取要吸附的ID的left和top值并重新计算弹出层left和top值
        getDomPosition:function (o) {
            var $box = $("#" + o._boxID);
            var cw = document.documentElement.clientWidth, ch = document.documentElement.clientHeight;
            var sw = $box.outerWidth(), sh = $box.outerHeight();
            var $soffset = $box.offset(), sl = $soffset.left, st = $soffset.top;
            $getPageSize = new Array();
            $getPageSize.push(cw, ch, sw, sh, sl, st);
        },
        //设置窗口的zIndex
        setBoxzIndex:function (o) {
            $XYTipsWindowarr.push(document.getElementById(o._boxID + "parent"));//存储窗口到数组
            var _event = "mousedown" || "click";
            var $box = $("#" + o._boxID + "parent");
            $box.die().live("click", function () {
                for (var i = 0; i < $XYTipsWindowarr.length; i++) {
                    $XYTipsWindowarr[i].style.zIndex = 870618;
                }
                ;
                this.style.zIndex = 891208;
            });
        },
        //写入CSS样式
        addStyle:function (s) {
            var T = this.style;
            if (!T) {
                T = this.style = document.createElement('style');
                T.setAttribute('type', 'text/css');
                document.getElementsByTagName('head')[0].appendChild(T);
            }
            ;
            T.styleSheet && (T.styleSheet.cssText += s) || T.appendChild(document.createTextNode(s));
        },
        //绑定拖拽
        dragBox:function (o) {
            var $moveX = 0, $moveY = 0,
                drag = false;
            var $ID = $("#" + o._boxID),
                $Handle = $("." + o._drag, $ID);
            $Handle.mouseover(function () {
                if (o._triggerID != "") {
                    $(this).css("cursor", "default");
                } else {
                    $(this).css("cursor", "move");
                }
                ;
            });
            $Handle.mousedown(function (e) {
                drag = o._triggerID != "" ? false : true;
                if (o._dragBoxOpacity) {
                    if (o._boxBdOpacity != "1") {
                        $ID.children("div").css("opacity", o._dragBoxOpacity);
                        $ID.children("div._boxBd").css("opacity", o._boxBdOpacity);
                    } else {
                        $ID.children("div").css("opacity", o._dragBoxOpacity);
                    }
                    ;
                }
                ;
                e = window.event ? window.event : e;
                var _ID = document.getElementById(o._boxID);
                $moveX = e.clientX - _ID.offsetLeft;
                $moveY = e.clientY - _ID.offsetTop;
                $(document).mousemove(function (e) {
                    if (drag) {
                        e = window.event ? window.event : e;
                        window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
                        var _x = e.clientX - $moveX;
                        var _y = e.clientY - $moveY;
                        $(_ID).css({
                            left:_x,
                            top:_y
                        });
                    }
                    ;
                });
                $(document).mouseup(function () {
                    drag = false;
                    if (o._dragBoxOpacity) {
                        if (o._boxBdOpacity != "1") {
                            $ID.children("div").css("opacity", "1");
                            $ID.children("div._boxBd").css("opacity", o._boxBdOpacity);
                        } else {
                            $ID.children("div").css("opacity", "1");
                        }
                        ;
                    }
                    ;
                });
            });
        },
        //关闭弹出层
        removeBox:function () {
            var $box = $("#" + BOXID._boxID);
            var $boxbg = $("#XYTipsWindowBg");
            if ($box != null || $boxbg != null) {
                var $contentID = $("._boxContent", $box);
                $contentType = BOXID._content.substring(0, BOXID._content.indexOf(":"));
                $content = BOXID._content.substring(BOXID._content.indexOf(":") + 1, BOXID._content.length);
                if ($contentType == "id") {
                    $contentID.children().appendTo($("#" + $content));
                    $box.parent().removeAttr("style").remove();
                    $boxbg.animate({opacity:"0"}, 500, function () {
                        $(this).remove();
                    });
                    $("body").css("background", "#fff");
                } else {
                    $box.parent().removeAttr("style").remove();
                    $boxbg.animate({opacity:"0"}, 500, function () {
                        $(this).remove();
                    });
                    $("body").css("background", "#fff");
                }
                ;
            }
            ;
        },
        //健盘事件，当按Esc的时候关闭弹出层
        keyDown:function (o) {
            document.onkeydown = function (e) {
                e = e || event;
                if (e.keyCode == 27) {
                    $.XYTipsWindow.removeBox();
                }
                ;
            };
        }
    });
})(jQuery);