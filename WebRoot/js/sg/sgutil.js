function getHeight() {
	return window.innerHeight || document.documentElement
			&& document.documentElement.clientHeight
			|| document.body.clientHeight;
};

function getWidth() {
	return window.innerWidth || document.documentElement
			&& document.documentElement.clientWidth
			|| document.body.clientWidth;
};

function getTop() {
	return window.pageYOffset || document.documentElement
			&& document.documentElement.scrollTop || document.body.scrollTop;
};

function getLeft() {
	window.pageXOffset || document.documentElement
			&& document.documentElement.scrollLeft || document.body.scrollLeft;
};

function getRight() {
	return windowPosition.left() + windowPosition.width();
};

function getBottom() {
	return windowPosition.top() + windowPosition.height();
};

/**
 * 判断非空
 * 
 * @param val
 * @returns {Boolean}
 */
function isEmpty(val) {
	val = $.trim(val);
	if (val == null)
		return true;
	if (val == undefined || val == 'undefined')
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
}

function isNotEmpty(val) {
	return !isEmpty(val);
}

/**
 * 阻止事件冒泡
 * 
 * @param e
 */
function stopBubble(e) {
	// 如果提供了事件对象，则这是一个非IE浏览器
	if (e && e.stopPropagation)
		// 因此它支持W3C的stopPropagation()方法
		e.stopPropagation();
	else
		// 否则，我们需要使用IE的方式来取消事件冒泡
		window.event.cancelBubble = true;
};
// json对象转换为String
function jsonToString(obj) {
	var THIS = this;
	switch (typeof (obj)) {
	case 'string':
		return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';
	case 'array':
		return '[' + obj.map(THIS.jsonToString).join(',') + ']';
	case 'object':
		if (obj instanceof Array) {
			var strArr = [];
			var len = obj.length;
			for (var i = 0; i < len; i++) {
				strArr.push(THIS.jsonToString(obj[i]));
			}
			return '[' + strArr.join(',') + ']';
		} else if (obj == null) {
			return 'null';

		} else {
			var string = [];
			for ( var property in obj)
				string.push(THIS.jsonToString(property) + ':'
						+ THIS.jsonToString(obj[property]));
			return '{' + string.join(',') + '}';
		}
	case 'number':
		return obj;
	case false:
		return obj;
	}
}
// 判断两个元素是否相等
function eqauls(str, tstr) {
	if (str == tstr) {
		return true;
	}
	return false;
};

/* 随机产生ID */
var random = 0;
/* 随机生成随机数 */
function tm_Random() {
	random++;
	return new Date().getTime() + "" + random;
};

function filterTag(str) {
	str = str.replace(/&/ig, "&amp;");
	str = str.replace(/</ig, "&lt;");
	str = str.replace(/>/ig, "&gt;");
	str = str.replace(" ", "&nbsp;");
	return str;
}

function filterScript(str) {
	return str.replace(/(<script)/ig, "&lt;script").replace(/(<script>)/ig,
			"&lt;script&gt;").replace(/(<\/script>)/ig, "&lt;/script&gt;");
}

/* 获取鼠标的坐标 */
function tm_posXY(event) {
	event = event || window.event;
	var posX = event.pageX
			|| (event.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));
	var posY = event.pageY
			|| (event.clientY + (document.documentElement.scrollTop || document.body.scrollTop));
	return {
		x : posX,
		y : posY
	};
}

function getCursortPosition(ctrl) {
	var CaretPos = 0; // IE Support
	if (document.selection) {
		ctrl.focus();
		var Sel = document.selection.createRange();
		Sel.moveStart('character', -ctrl.value.length);
		CaretPos = Sel.text.length;
	}
	// Firefox support
	else if (ctrl.selectionStart || ctrl.selectionStart == '0')
		CaretPos = ctrl.selectionStart;
	return (CaretPos);
}

/**
 * 禁止窗体选中
 */
function forbiddenSelect() {
	$(document).bind("selectstart", function() {
		return false;
	});
	document.onselectstart = new Function("event.returnValue=false;");
	$("*").css({
		"-moz-user-select" : "none"
	});
}

/* 窗体允许选中 */
function autoSelect() {
	$(document).bind("selectstart", function() {
		return true;
	});
	document.onselectstart = new Function("event.returnValue=true;");
	$("*").css({
		"-moz-user-select" : ""
	});
};

/* 获取剪切板中的内容 */
function tm_GetClipboard() {
	if (window.clipboardData) {
		return (window.clipboardData.getData('text'));
	} else {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager
						.enablePrivilege("UniversalXPConnect");
				var clip = Components.classes["@mozilla.org/widget/clipboard;1"]
						.createInstance(Components.interfaces.nsIClipboard);
				if (!clip) {
					return;
				}
				var trans = Components.classes["@mozilla.org/widget/transferable;1"]
						.createInstance(Components.interfaces.nsITransferable);
				if (!trans) {
					return;
				}
				trans.addDataFlavor("text/unicode");
				clip.getData(trans, clip.kGlobalClipboard);
				var str = new Object();
				var len = new Object();
				trans.getTransferData("text/unicode", str, len);
			} catch (e) {
				alert("您的firefox安全限制限制您进行剪贴板操作，请打开'about:config'将signed.applets.codebase_principal_support'设置为true'之后重试，相对路径为firefox根目录/greprefs/all.js");
				return null;
			}
			if (str) {
				if (Components.interfaces.nsISupportsWString) {
					str = str.value
							.QueryInterface(Components.interfaces.nsISupportsWString);
				} else {
					if (Components.interfaces.nsISupportsString) {
						str = str.value
								.QueryInterface(Components.interfaces.nsISupportsString);
					} else {
						str = null;
					}
				}
			}
			if (str) {
				return (str.data.substring(0, len.value / 2));
			}
		}
	}
	return null;
};
/**
 * 往剪切板里赋值
 * 
 * @param txt
 * @returns {Boolean}
 */
function tmSetClipboard(txt) {
	if (window.clipboardData) {
		window.clipboardData.clearData();
		window.clipboardData.setData("Text", txt);
	} else if (navigator.userAgent.indexOf("Opera") != -1) {
		window.location = txt;
	} else if (window.netscape) {
		try {
			netscape.security.PrivilegeManager
					.enablePrivilege("UniversalXPConnect");
		} catch (e) {
			alert("您的firefox安全限制限制您进行剪贴板操作，请打开'about:config'将signed.applets.codebase_principal_support'设置为true'之后重试，相对路径为firefox根目录/greprefs/all.js");
			return false;
		}
		var clip = Components.classes['@mozilla.org/widget/clipboard;1']
				.createInstance(Components.interfaces.nsIClipboard);
		if (!clip)
			return;
		var trans = Components.classes['@mozilla.org/widget/transferable;1']
				.createInstance(Components.interfaces.nsITransferable);
		if (!trans)
			return;
		trans.addDataFlavor('text/unicode');
		var str = Components.classes["@mozilla.org/supports-string;1"]
				.createInstance(Components.interfaces.nsISupportsString);
		var copytext = txt;
		str.data = copytext;
		trans.setTransferData("text/unicode", str, copytext.length * 2);
		var clipid = Components.interfaces.nsIClipboard;
		if (!clip)
			return false;
		clip.setData(trans, null, clipid.kGlobalClipboard);
	}
};

/* 获取光标处内容 */
function setCaretPosition(inputDom, startIndex, endIndex) {
	if (inputDom.setSelectionRange) {
		inputDom.setSelectionRange(startIndex, endIndex);
	} else if (inputDom.createTextRange) // IE
	{
		var range = inputDom.createTextRange();
		range.collapse(true);
		range.moveStart('character', startIndex);
		range.moveEnd('character', endIndex - startIndex - 1);
		range.select();
	}
	inputDom.focus();
}

// 获取选中文本
function getSelectedText(inputDom) {
	if (document.selection) // IE
	{
		return document.selection.createRange().text;
	} else {
		return inputDom.value.substring(inputDom.selectionStart,
				inputDom.selectionEnd);
	}
};

// 在光标处插入字符串
// myField 文本框对象
// myValue 要插入的值
function tm_insertAtCursorxxx(myField, myValue) {
	// IE support
	if (document.selection) {
		myField.focus();
		sel = document.selection.createRange();
		sel.text = myValue;
		sel.select();
	}
	// MOZILLA/NETSCAPE support
	else if (myField.selectionStart || myField.selectionStart == '0') {
		var startPos = myField.selectionStart;
		var endPos = myField.selectionEnd;
		// save scrollTop before insert
		var restoreTop = myField.scrollTop;
		myField.value = myField.value.substring(0, startPos) + myValue
				+ myField.value.substring(endPos, myField.value.length);
		if (restoreTop > 0) {
			// restore previous scrollTop
			myField.scrollTop = restoreTop;
		}
		myField.focus();
		myField.selectionStart = startPos + myValue.length;
		myField.selectionEnd = startPos + myValue.length;
	} else {
		myField.value += myValue;
		myField.focus();
	}
}

function tm_insertAtCursor(tc, str) {
	var tclen = tc.value.length;
	tc.focus();
	if (typeof document.selection != "undefined") {
		document.selection.createRange().text = str;
	} else {
		tc.value = tc.value.substr(0, tc.selectionStart) + str
				+ tc.value.substring(tc.selectionStart, tclen);
	}
}

function tm_numberKey($this, e) {
	var range = $this.attr("range");
	if (isNotEmpty(range)) {
		var ranges = range.split("_");
		var max = ranges[1] * 1, min = ranges[0] * 1;
		var val = parseInt($this.val());
		if (val <= min)
			$this.val(min);
		if (val >= max)
			$this.val(max);
	}
	if (!e)
		e = window.event;
	var code = e.keyCode | e.which | e.charCode;
	if (code >= 48 && code <= 57 || code >= 96 && code <= 105 || code == 9)
		return true; // 数字
	switch (code) {
	case 8:
		// 退格
	case 37:
	case 38:
	case 39:
	case 40:
		// 方向键
	case 13:
		// 回车
	case 46:
		// 删除
	case 45:
	case 110:
		return true;
	}
	return false;
}
// 获取浏览器版本
function tm_getBroswerVersion() {
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	if (ua) {
		window.ActiveXObject ? Sys.version = "ie_"
				+ ua.match(/msie ([\d]+)/)[1]
				: document.getBoxObjectFor ? Sys.version = "firefox_"
						+ ua.match(/firefox\/([\d.]+)/)[1]
						: window.MessageEvent && !document.getBoxObjectFor ? Sys.version = "chrome"
								: window.opera ? Sys.version = "opera_"
										+ ua.match(/opera.([\d.]+)/)[1]
										: window.openDatabase ? Sys.version = ua
												.match(/version\/([\d.]+)/)[1]
												: 0;
	}
	return Sys;
}

/**
 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
 * 可以用 1-2 个占位符 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) eg: (new
 * Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 (new
 * Date()).format("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04 (new
 * Date()).format("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04 (new
 * Date()).format("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04 (new
 * Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.format = function(fmt) {
	var o = {
		"Y+" : this.getFullYear(),
		"M+" : this.getMonth() + 1,
		// 月份
		"d+" : this.getDate(),
		// 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12,
		// 小时
		"H+" : this.getHours(),
		// 小时
		"m+" : this.getMinutes(),
		// 分
		"s+" : this.getSeconds(),
		// 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		// 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
};

/**
 * 将数字转换成对应的中文 将阿拉伯数字翻译成中文的大写数字
 * 
 * @param {Object}
 *            num 比如:1对应一 11：十一 101:一百零一
 * @return {TypeName}
 */
function tm_NumberToChinese(num) {
	var AA = new Array("零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十");
	var BB = new Array("", "十", "百", "仟", "萬", "億", "点", "");
	var a = ("" + num).replace(/(^0*)/g, "").split("."), k = 0, re = "";
	for (var i = a[0].length - 1; i >= 0; i--) {
		switch (k) {
		case 0:
			re = BB[7] + re;
			break;
		case 4:
			if (!new RegExp("0{4}//d{" + (a[0].length - i - 1) + "}$")
					.test(a[0]))
				re = BB[4] + re;
			break;
		case 8:
			re = BB[5] + re;
			BB[7] = BB[5];
			k = 0;
			break;
		}
		if (k % 4 == 2 && a[0].charAt(i + 2) != 0 && a[0].charAt(i + 1) == 0)
			re = AA[0] + re;
		if (a[0].charAt(i) != 0)
			re = AA[a[0].charAt(i)] + BB[k % 4] + re;
		k++;
	}

	if (a.length > 1) // 加上小数部分(如果有小数部分)
	{
		re += BB[6];
		for (var i = 0; i < a[1].length; i++)
			re += AA[a[1].charAt(i)];
	}
	if (re == '一十')
		re = "十";
	if (re.match(/^一/) && re.length == 3)
		re = re.replace("一", "");
	return re;
};

/**
 * 获取窗体可见度高度
 * 
 * @returns
 */
function getClientHeight() {
	var clientHeight = 0;
	if (document.body.clientHeight && document.documentElement.clientHeight) {
		clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight
				: document.documentElement.clientHeight;
	} else {
		clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight
				: document.documentElement.clientHeight;
	}
	return clientHeight;
}

/**
 * 获取窗体可见度宽度
 * 
 * @returns
 */
function getClientWidth() {
	var clientWidth = 0;
	if (document.body.clientWidth && document.documentElement.clientWidth) {
		clientWidth = (document.body.clientWidth < document.documentElement.clientWidth) ? document.body.clientWidth
				: document.documentElement.clientWidth;
	} else {
		clientWidth = (document.body.clientWidth > document.documentElement.clientWidth) ? document.body.clientWidth
				: document.documentElement.clientWidth;
	}
	return clientWidth;
}

function getScrollHeight() {
	return Math.max(getClientHeight(), document.body.scrollHeight,
			document.documentElement.scrollHeight);
}

function getScrollTop() {
	var scrollTop = 0;
	if (document.documentElement && document.documentElement.scrollTop) {
		scrollTop = document.documentElement.scrollTop;
	} else if (document.body) {
		scrollTop = document.body.scrollTop;
	}
	return scrollTop;
}

/* 文件大小转换为MB GB KB格式 */
function tm_countFileSize(size) {
	var fsize = parseFloat(size, 2);
	var fileSizeString;
	if (fsize < 1024) {
		fileSizeString = fsize.toFixed(2) + "B";
	} else if (fsize < 1048576) {
		fileSizeString = (fsize / 1024).toFixed(2) + "KB";
	} else if (fsize < 1073741824) {
		fileSizeString = (fsize / 1024 / 1024).toFixed(2) + "MB";
	} else if (fsize < 1024 * 1024 * 1024) {
		fileSizeString = (fsize / 1024 / 1024 / 1024).toFixed(2) + "GB";
	} else {
		fileSizeString = "0B";
	}
	return fileSizeString;
};

/* 获取文件后缀 */
function tm_getExt(fileName) {
	if (fileName.lastIndexOf(".") == -1)
		return fileName;
	var pos = fileName.lastIndexOf(".") + 1;
	return fileName.substring(pos, fileName.length).toLowerCase();
}

/* 获取文件名称 */
function tm_getFileName(fileName) {
	var pos = fileName.lastIndexOf("/") + 1;
	if (pos == -1) {
		return fileName;
	} else {
		return fileName.substring(pos, fileName.length);
	}
}

/**
 * 禁止窗体选中
 */
function tm_forbiddenSelect() {
	$(document).bind("selectstart", function() {
		return false;
	});
	document.onselectstart = new Function("event.returnValue=false;");
	$("*").css({
		"-moz-user-select" : "none"
	});
}

/* 窗体允许选中 */
function tm_autoSelect() {
	$(document).bind("selectstart", function() {
		return true;
	});
	document.onselectstart = new Function("event.returnValue=true;");
	$("*").css({
		"-moz-user-select" : ""
	});
}

// 获取几秒钟以前 startTime==== Date
function getTimeFormat(startTime) {
	var startTimeMills = startTime.getTime();
	var endTimeMills = new Date().getTime();
	var diff = parseInt((endTimeMills - startTimeMills) / 1000);// 秒
	var day_diff = parseInt(Math.floor(diff / 86400));// 天
	var buffer = Array();
	if (day_diff < 0) {
		return "[error],时间越界...";
	} else {
		if (day_diff == 0 && diff < 60) {
			if (diff <= 0)
				diff = 1;
			buffer.push(diff + "秒前");
		} else if (day_diff == 0 && diff < 120) {
			buffer.push("1 分钟前");
		} else if (day_diff == 0 && diff < 3600) {
			buffer.push(Math.round(Math.floor(diff / 60)) + "分钟前");
		} else if (day_diff == 0 && diff < 7200) {
			buffer.push("1小时前");
		} else if (day_diff == 0 && diff < 86400) {
			buffer.push(Math.round(Math.floor(diff / 3600)) + "小时前");
		} else if (day_diff == 1) {
			buffer.push("1天前");
		} else if (day_diff < 7) {
			buffer.push(day_diff + "天前");
		} else if (day_diff < 30) {
			buffer.push(Math.round(Math.floor(day_diff / 7)) + " 星期前");
		} else if (day_diff >= 30 && day_diff <= 179) {
			buffer.push(Math.round(Math.floor(day_diff / 30)) + "月前");
		} else if (day_diff >= 180 && day_diff < 365) {
			buffer.push("半年前");
		} else if (day_diff >= 365) {
			buffer.push(Math.round(Math.floor(day_diff / 30 / 12)) + "年前");
		}
	}
	return buffer.toString();
};

// 去除前后空格
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
// 去左空格
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/g, "");
}
// 去右空格
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/g, "");
}
// 去除所有空格
String.prototype.trimAll = function(is_global) {
	var str = this;
	str = str.replace(/(^\s+)|(\s+$)/g, "");
	if (is_global.toLowerCase() == "g") {

		str = str.replace(/\s/g, "");
	}
	return str;
};
String.prototype.isEmpty = function() {
	var val = this;
	val = $.trim(val);
	if (val == null)
		return true;
	if (val == undefined || val == 'undefined')
		return true;
	if (val == "")
		return true;
	if (val.length == 0)
		return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val))
		return true;
	return false;
};

String.prototype.isNotEmpty = function() {
	return !this.isEmpty();
};

String.prototype.replaceAll = function(str, target) {
	return this.replace(new RegExp(str, "ig"), target);
};

var tzUtil = {
	_position : function($dom, amark) {// 居中定位
		var windowWidth = $(window).width();
		var windowHeight = $(window).height();
		var width = $dom.width();
		var height = $dom.height();
		var left = (windowWidth - width) / 2;
		var top = (windowHeight - height) / 2;
		if (!amark)
			$dom.css("top", top).animate({
				left : left
			});
		if (amark == 0)
			$dom.animate({
				left : left,
				"top" : top
			});
		if (amark == 1)
			$dom.css("left", left).animate({
				"top" : top
			});
		if (amark == 2)
			$dom.css({
				left : left,
				"top" : top
			});
		return this;
	},

	_positionParent : function($dom, $parent) {// 居中定位
		var parentWidth = $parent.width();
		var parentHeight = $parent.height();
		var width = $dom.width();
		var height = $dom.height();
		var left = (parentWidth - width) / 2;
		var top = (parentHeight - height) / 2;
		$dom.css({
			left : left,
			top : top
		});
		return this;
	},

	resize : function($dom) {
		var $this = this;
		$(window).resize(function() {
			$this._position($dom);
		});
	},
	animates : function($dom, mark) {
		switch (mark) {
		case "fadeOut":
			$dom.toggleClass(tz_animateOut()).fadeOut("slow", function() {
				$(this).remove();
			});
			break;
		case "slideUp":
			$dom.toggleClass(tz_animateOut()).slideUp("slow", function() {
				$(this).remove();
			});
			break;
		case "fadeIn":
			$dom.toggleClass(tz_animateOut()).fadeIn("slow");
			break;
		case "slideDown":
			$dom.toggleClass(tz_animateOut()).slideDown("slow");
			break;
		case "left":
			$dom.toggleClass(tz_animateOut()).animate({
				left : 0
			}, 300, function() {
				$(this).remove();
			});
			break;
		case "top":
			$dom.toggleClass(tz_animateOut()).animate({
				top : 0
			}, 300, function() {
				$(this).remove();
			});
			break;
		}
	},

	isEmpty : function(str) {
		val = $.trim(val);
		if (val == null)
			return true;
		if (val == undefined || val == 'undefined')
			return true;
		if (val == "")
			return true;
		if (val.length == 0)
			return true;
		if (!/[^(^\s*)|(\s*$)]/.test(val))
			return true;
		return false;
	},

	isNotEmpty : function(str) {
		return !this.isEmpty(str);
	},
	getRandomColor : function() {
		return '#' + Math.floor(Math.random() * 16777215).toString(16);
	},

	forbiddenSelect : function() {
		$(document).bind("selectstart", function() {
			return false;
		});
		document.onselectstart = new Function("event.returnValue=false;");
		$("*").css({
			"-moz-user-select" : "none"
		});
	},

	autoSelect : function() {
		$(document).bind("selectstart", function() {
			return true;
		});
		document.onselectstart = new Function("event.returnValue=true;");
		$("*").css({
			"-moz-user-select" : ""
		});
	}
};

/* 判断一个元素释放包含在数组中。 */
Array.prototype.contains = function(obj) {
	var i = this.length;
	while (i--) {
		if (this[i] === obj) {
			return true;
		}
	}
	return false;
};

var isArray = function(obj) {
	return Object.prototype.toString.call(obj) === '[object Array]';
};

var is_array = function(value) {
	return value && typeof value === 'object'
			&& typeof value.length === 'number'
			&& typeof value.splice === 'function'
			&& !(value.propertyIsEnumerable('length'));
};

/* 日期工具类 */
$.tmDate = {
	/* 转换日期 */
	_transferDate : function(date) {
		if (typeof date == "string") {
			return new Date(date.replace(/-/ig, "/"));
		} else {
			return date;
		}
	},
	/* 格式化日期 */
	_toString : function(date, pattern) {
		var d = this._transferDate(date);
		return d.format(pattern);
	},

	/* 获取两个时间相减的时间 */
	_Date : function(date1, date2) {
		var dateTime = this._numMillSecond(date1, date2);
		return new Date(dateTime).format("yyyy-MM-dd");
	},
	// 获取两个时间相减的年份
	_Datsyear : function(date1, date2) {
		var dateTime = this._numYear(date1, date2);
		return dateTime;
	},
	// 获取两个时间相减的月份
	_Datsmonth : function(date1, date2) {
		var dateTime = this._numMonth(date2, date1);
		return dateTime;
	},

	// 间隔年份
	_numYear : function(date1, date2) {
		var times = this._numDay(date1, date2);
		return Math.floor(times / 365);
	},

	// 间隔月份
	_numMonth : function(date1, date2) {
		var times = this._numDay(date1, date2);
		return Math.floor(times / 30);
	},

	// 间隔天数
	_numDay : function(date1, date2) {
		var times = this._numSecond(date1, date2);
		var hour = this._var().hour;
		var mills = this._var().mills;
		return Math.ceil(times / (mills * hour));
	},

	// 间隔时
	_numHour : function(date1, date2) {
		return Math.floor(this._numMillSecond(date1, date2) / (1000 * 60 * 60));
	},

	// 间隔分
	_numMinute : function(date1, date2) {
		return Math.floor(this._numMillSecond(date1, date2) / (1000 * 60));
	},

	// 间隔秒数
	_numSecond : function(date1, date2) {
		return Math.floor(this._numMillSecond(date1, date2) / 1000);
	},

	// 间隔毫秒
	_numMillSecond : function(date1, date2) {
		var stimes = this._getTime(this._transferDate(date1));
		var etimes = this._getTime(this._transferDate(date2));
		return etimes - stimes;
	},

	_var : function() {
		return {
			hour : 24,
			second : 60,
			mills : 3600,
			format : "yyyy-MM-dd",
			dateFormat : "yyyy-MM-dd HH:mm:ss"
		};
	},

	/* 某个日期加上多少毫秒 */
	_plusMillisSeconds : function(date, millisSeconds) {
		var dateTime = this._getTime(date);
		var mintimes = millisSeconds;
		var rdate = dateTime * 1 + mintimes * 1;
		return this._format(new Date(rdate));
	},
	/* 某个日期加上多少秒 */
	_plusSeconds : function(date, seconds) {
		var dateTime = this._getTime(date);
		var mintimes = seconds * 1000;
		var rdate = dateTime * 1 + mintimes * 1;
		return this._format(new Date(rdate));
	},
	/* 某个日期加上多少分钟 */
	_plusMinutes : function(date, minutes) {
		var dateTime = this._getTime(date);
		var mintimes = minutes * 60 * 1000;
		var rdate = dateTime * 1 + mintimes * 1;
		return this._format(new Date(rdate));
	},
	/* 某个日期加上小时数 */
	_plusHours : function(date, hours) {
		var dateTime = this._getTime(date);
		var mintimes = hours * 60 * 60 * 1000;
		var rdate = dateTime + mintimes;
		return this._format(new Date(rdate));
	},
	/* 某个日期加上天数 */
	_plusDays : function(date, days) {
		var dateTime = this._getTime(date);
		var mintimes = days * 60 * 60 * 1000 * 24;
		var rdate = dateTime * 1 + mintimes * 1;
		return this._format(new Date(rdate));
	},

	/* 某个日期加上多少个月,这里是按照一个月30天来计算天数的 */
	_plusMonths : function(date, months) {
		var dateTime = this._getTime(date);
		var mintimes = months * 30 * 60 * 60 * 1000 * 24;
		var rdate = dateTime + mintimes * 1;
		return this._format(new Date(rdate));
	},

	/* 某个日期加上多少个年,这里是按照一个月365天来计算天数的，如果loop为true则按闰年计算 */
	_plusYears : function(date, years, isLoop) {
		var dateTime = this._getTime(date);
		var day = 365;
		if (isLoop)
			day = 366;
		var mintimes = years * day * 60 * 60 * 1000 * 24;
		var rdate = dateTime + mintimes;
		return this._format(new Date(rdate));
	},

	/* 某个日期加上某个日期，这样的操作视乎没什么意义 */
	_plusDate : function(date1, date2) {
		var dateTime = this._getTime(date1);
		var dateTime2 = this._getTime(date2);
		;
		var rdate = dateTime + dateTime2;
		return this._format(new Date(rdate));
	},

	/* 某个日期减去多少毫秒秒 */
	_minusMillisSeconds : function(date, millisSeconds) {
		var dateTime = this._getTime(date);
		var mintimes = millisSeconds * 1;
		var rdate = dateTime - mintimes;
		return this._format(new Date(rdate));
	},
	/* 某个日期减去多少秒 */
	_minusSeconds : function(date, seconds) {
		var dateTime = this._getTime(date);
		var mintimes = seconds * 1000;
		var rdate = dateTime - mintimes;
		return this._format(new Date(rdate));
	},
	/* 某个日期减去多少分钟 */
	_minusMinutes : function(date, minutes) {
		var dateTime = this._getTime(date);
		var mintimes = minutes * 60 * 1000;
		var rdate = dateTime - mintimes;
		return this._format(new Date(rdate));
	},
	/* 某个日期减去小时数 */
	_minusHours : function(date, hours) {
		var dateTime = this._getTime(date);
		var mintimes = hours * 60 * 60 * 1000;
		var rdate = dateTime - mintimes;
		return this._format(new Date(rdate));
	},
	/* 某个日期减去天数 */
	_minusDays : function(date, days) {
		var dateTime = this._getTime(date);
		var mintimes = days * 60 * 60 * 1000 * 24;
		var rdate = dateTime - mintimes;
		return this._format(new Date(rdate));
	},

	/* 某个日期减去多少个月,这里是按照一个月30天来计算天数的 */
	_minusMonths : function(date, months) {
		var dateTime = this._getTime(date);
		var mintimes = months * 30 * 60 * 60 * 1000 * 24;
		var rdate = dateTime - mintimes;
		return this._format(new Date(rdate));
	},

	/* 某个日期减去多少个年,这里是按照一个月365天来计算天数的 */
	_minusYears : function(date, years, isLoop) {
		var dateTime = this._getTime(date);
		var day = 365;
		if (isLoop)
			day = 366;
		var mintimes = years * day * 60 * 60 * 1000 * 24;
		var rdate = dateTime - mintimes;
		return this._format(new Date(rdate));
	},

	/* 某个日期减去某个日期，这样的操作视乎没什么意义 */
	_minusDate : function(date1, date2) {
		var dateTime = this._getTime(date1);
		var dateTime2 = this._getTime(date2);
		;
		var rdate = dateTime - dateTime2;
		return this._format(new Date(rdate));
	},

	/* 获取一个月有多少天 */
	_getMonthOfDay : function(date1) {
		var currentMonth = this._getFirstDayOfMonth(date1);
		var nextMonth = this._getNextDayOfMonth(date1);
		return this._numDay(currentMonth, nextMonth);
	},

	/* 获取一年又多少天 */
	_getYearOfDay : function(date) {
		var firstDayYear = this._getFirstDayOfYear(date);
		var lastDayYear = this._getLastDayOfYear(date);
		return Math.ceil(this._numDay(firstDayYear, lastDayYear));
	},

	/* 某个日期是当年中的第几天 */
	_getDayOfYear : function(date1) {
		return Math.ceil(this._numDay(this._getFirstDayOfYear(date1), date1));
	},

	/* 某个日期是在当月中的第几天 */
	_getDayOfMonth : function(date1) {
		return Math.ceil(this._numDay(this._getFirstDayOfMonth(date1), date1));
	},

	/* 获取某个日期在这一年的第几周 */
	_getDayOfYearWeek : function(date) {
		var numdays = this._getDayOfYear(date);
		return Math.ceil(numdays / 7);
	},

	/* 某个日期是在当月中的星期几 */
	_getDayOfWeek : function(date1) {
		return this._getWeek(date1);
	},

	/* 获取在当前日期中的时间 */
	_getHourOfDay : function(date) {
		return this._getHour(date);
	},
	_eq : function(date1, date2) {
		var stime = this._getTime(this._transferDate(date1));
		var etime = this._getTime(this._transferDate(date2));
		return stime == etime ? true : false;
	},
	/* 某个日期是否晚于某个日期 */
	_after : function(date1, date2) {
		var stime = this._getTime(this._transferDate(date1));
		var etime = this._getTime(this._transferDate(date2));
		return stime < etime ? true : false;
	},

	/* 某个日期是否早于某个日期 */
	_before : function(date1, date2) {
		var stime = this._getTime(this._transferDate(date1));
		var etime = this._getTime(this._transferDate(date2));
		return stime > etime ? true : false;
	},

	/* 获取某年的第一天 */
	_getFirstDayOfYear : function(date) {
		var year = this._getYear(date);
		var dateString = year + "-01-01 00:00:00";
		return dateString;
	},

	/* 获取某年的最后一天 */
	_getLastDayOfYear : function(date) {
		var year = this._getYear(date);
		var dateString = year + "-12-01 00:00:00";
		var endDay = this._getMonthOfDay(dateString);
		return year + "-12-" + endDay + " 23:59:59";
	},

	/* 获取某月的第一天 */
	_getFirstDayOfMonth : function(date) {
		var year = this._getYear(date);
		var month = this._getMonth(date);
		var dateString = year + "-" + month + "-01 00:00:00";
		return dateString;
	},

	/* 获取某月最后一天 */
	_getLastDayOfMonth : function(date) {
		var endDay = this._getMonthOfDay(date);
		var year = this._getYear(date);
		var month = this._getMonth(date);
		return year + "-" + month + "-" + endDay + " 23:59:59";
	},
	/* 一天的开始时间 */
	_getFirstOfDay : function(date) {
		var year = this._getYear(date);
		var month = this._getMonth(date);
		var date = this._getDay(date);
		return year + "-" + month + "-" + date + " 00:00:00";
	},

	/* 一天的结束时间 */
	_getLastOfDay : function(date) {
		var year = this._getYear(date);
		var month = this._getMonth(date);
		var date = this._getDay(date);
		return year + "-" + month + "-" + date + " 23:59:59";
	},

	/* 获取下个月的第一天 */
	_getNextDayOfMonth : function(date) {
		var year = this._getYear(date);
		var month = this._getMonth(date);
		month = month * 1 + 1;
		if (month > 12) {
			year = year + 1;
			month = month - 12;
		}
		month = month > 9 ? month : "0" + month;
		var dateString = year + "-" + month + "-01 00:00:00";
		return dateString;
	},

	_getFirstOfWeek : function(date1) {
		var week = this._getWeek(date1);
		var date = this._minusDays(date1, week);
		var year = this._getYear(date);
		var month = this._getMonth(date);
		var date = this._getDay(date);
		return year + "-" + month + "-" + date + " 00:00:00";
	},

	_getLastOfWeek : function(date1) {
		var week = 6 - this._getWeek(date1);
		var date = this._minusDays(date1, week);
		var year = this._getYear(date);
		var month = this._getMonth(date);
		var date = this._getDay(date);
		return year + "-" + month + "-" + date + " 23:59:59";
	},

	_getNow : function() {
		return new Date();
	},
	_format : function(date) {
		return this._getYear(date) + "-" + this._getMonth(date) + "-"
				+ this._getDay(date) + " " + this._getHour(date) + ":"
				+ this._getMinute(date) + ":" + this._getSecond(date);
	},
	_getDate : function() {
		return this._getNow();
	},
	/* 年 */
	_getYear : function(date) {
		return this._transferDate(date).getFullYear();
	},

	/* 月 */
	_getMonth : function(date) {
		var month = this._transferDate(date).getMonth() + 1;
		return month > 9 ? month : "0" + month;
	},

	/* 日 */
	_getDay : function(date) {
		var day = this._transferDate(date).getDate();
		return day > 9 ? day : "0" + day;
	},

	/* 获取今天星期几,如果为0代表星期日 */
	_getWeek : function(date) {
		return this._transferDate(date).getDay();
	},

	/* 时 */
	_getHour : function(date) {
		var hour = this._transferDate(date).getHours();
		return hour > 9 ? hour : "0" + hour;
	},

	/* 12小时制时 */
	_getHour12 : function(date) {
		var hour = this._transferDate(date).getHours();
		return hour % 12 == 0 ? 12 : hour % 12
	},

	/* 分 */
	_getMinute : function(date) {
		var minutes = this._transferDate(date).getMinutes();
		return minutes > 9 ? minutes : "0" + minutes;
	},

	/* 秒 */
	_getSecond : function(date) {
		var seconds = this._transferDate(date).getSeconds();
		return seconds > 9 ? seconds : "0" + seconds;
	},

	/* 毫秒 */
	_getMillisecond : function(date) {
		return this._transferDate(date).getMilliseconds();
	},

	/* 获取今天在当年是第几季度 */
	_getPeriod : function(date) {
		var month = this._getMonth(date) * 1;
		return Math.floor((month + 3) / 3)
	},

	/* 星期 */
	_nowWeekChinies : function(date) {
		var nowWeek = this._getWeek(date);
		var day = "";
		switch (nowWeek) {
		case 0:
			day = "日";
			break;
		break;
	case 1:
		day = "一";
		break;
	break;
case 2:
	day = "二";
	break;
break;
case 3:
day = "三";
break;
break;
case 4:
day = "四";
break;
break;
case 5:
day = "五";
break;
break;
case 6:
day = "六";
break;
}
return day;
},
_getMessage : function(date) {
var now = date || new Date();
var hour = now.getHours();
if (hour < 6) {
return "凌晨好！";
} else if (hour < 9) {
return "早上好！";
} else if (hour < 12) {
return "上午好！";
} else if (hour < 14) {
return "中午好！";
} else if (hour < 17) {
return "下午好！";
} else if (hour < 19) {
return "傍晚好！";
} else if (hour < 22) {
return "晚上好！";
} else {
return "夜里好！";
}
},
/* 返回 1970 年 1 月 1 日至今的毫秒数。 */
_getTime : function(date) {
return this._transferDate(date).getTime();
}
};
/* date end */
// //以下进行测试
// if (Sys.ie) document.write('IE: ' + Sys.ie);
// if (Sys.firefox) document.write('Firefox: ' + Sys.firefox);
// if (Sys.chrome) document.write('Chrome: ' + Sys.chrome);
// if (Sys.opera) document.write('Opera: ' + Sys.opera);
// if (Sys.safari) document.write('Safari: ' + Sys.safari);
function tm_check_broswer() {
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	var s;
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : (s = ua
			.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : (s = ua
			.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : (s = ua
			.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : (s = ua
			.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	return Sys;
}
/** *****************************************************************************************统计***************************************************************************** */
var tmStat = {};

function exmayCharset() {
	var charSet = "";
	var oType = getBrowser();
	switch (oType) {
	case "IE":
		charSet = document.charset;
		break;
	case "FIREFOX":
		charSet = document.characterSet;
		break;
	default:
		charSet = document.characterSet;
		break;
	}
	return charSet;
};

function getBrowser() {
	var oType = "";
	if (navigator.userAgent.indexOf("MSIE") != -1) {
		oType = "IE";
	} else if (navigator.userAgent.indexOf("Firefox") != -1) {
		oType = "FIREFOX";
	}
	return oType
};

/** flash版本号 */
function exmayFlashVersion() {
	var f = "", n = navigator;
	if (n.plugins && n.plugins.length) {
		for (var ii = 0; ii < n.plugins.length; ii++) {
			if (n.plugins[ii].name.indexOf('Shockwave Flash') != -1) {
				f = n.plugins[ii].description.split('Shockwave Flash ')[1];
				break;
			}
		}
	} else if (window.ActiveXObject) {
		for (var ii = 10; ii >= 2; ii--) {
			try {
				var fl = eval("new ActiveXObject('ShockwaveFlash.ShockwaveFlash."
						+ ii + "');");
				if (fl) {
					f = ii + '.0';
					break;
				}
			} catch (e) {
			}
		}
	}
	return f;
};

function tmEncode(str) {
	return encodeURI(str).replace(/=/g, "%3D").replace(/\+/g, "%2B").replace(
			/\?/g, "%3F").replace(/\&/g, "%26");
};

function tm_getOs() {
	var sUA = navigator.userAgent.toLowerCase();
	if (sUA.indexOf('win') != -1) {
		if (sUA.indexOf("nt 5.2") != -1) {
			return "Windows 2003";
		}
		if ((sUA.indexOf("nt 5.1") != -1) || (sUA.indexOf("XP") != -1)) {
			return "Windows XP";
		}
		if ((sUA.indexOf('nt 5.0') != -1) || (sUA.indexOf('2000') != -1)) {
			return 'Windows 2000';
		}
		if ((sUA.indexOf("winnt") != -1) || (sUA.indexOf("windows nt") != -1)) {
			return "Windows NT";
		}
		if ((sUA.indexOf("win98") != -1) || (sUA.indexOf("windows 98") != -1)) {
			return "Windows 98";
		}
		return "Windows";
	}
	if (sUA.indexOf('linux') != -1) {
		return 'Linux';
	}
	if (sUA.indexOf("freebsd") != -1) {
		return 'FreeBSD';
	}
	if (sUA.indexOf('x11') != -1) {
		return 'Unix';
	}
	if (sUA.indexOf('mac') != -1) {
		return "Mac";
	}
	if (sUA.indexOf("sunos") != -1) {
		return 'Sun OS';
	}
	if ((sUA.indexOf("os/2") != -1)
			|| (navigator.appVersion.indexOf("OS/2") != -1)
			|| (sUA.indexOf("ibm-webexplorer") != -1)) {
		return "OS 2"
	}
	if (navigator.platform == 'PalmOS') {
		return 'Palm OS';
	}
	if ((navigator.platform == 'WinCE') || (navigator.platform == 'Windows CE')
			|| (navigator.platform == 'Pocket PC')) {
		return 'Windows CE';
	}
	if (sUA.indexOf('webtv') != -1) {
		return 'WebTV Platform';
	}
	if (sUA.indexOf('netgem') != -1) {
		return 'Netgem';
	}
	if (sUA.indexOf('opentv') != -1) {
		return 'OpenTV Platform';
	}
	if (sUA.indexOf('symbian') != -1) {
		return 'Symbian';
	}
	return "Unknown";
}

function tm_getBrowse() {
	var sUA = navigator.userAgent;
	// 检测IE浏览器
	if ((navigator.appName == "Microsoft Internet Explorer")) {
		// 检测模拟IE浏览的OPERA。edit at 2006-11-08(ver 0.1.2)
		if (sUA.indexOf('Opera') != -1) {
			this.browseKernel = 'Presto';
			if (window.opera && document.childNodes) {
				return 'Opera 7+';
			} else {
				return 'Opera 6-';
			}
		}
		this.browseKernel = 'Trident';
		if (sUA.indexOf('Maxthon') != -1) {
			return 'Maxthon';
		}
		if (sUA.indexOf('TencentTraveler') != -1) { // ver 0.1.3
			return '腾迅TT';
		}
		if (document.getElementById) {
			return "IE5+";
		} else {
			return "IE4-";
		}
	}
	// 检测Gecko浏览器
	if (sUA.indexOf('Gecko') != -1) {
		this.browseKernel = 'Gecko';
		if (navigator.vendor == "Mozilla") {
			return "Mozilla";
		}
		if (navigator.vendor == "Firebird") {
			return "Firebird";
		}
		if (navigator.vendor.indexOf('Google') != -1
				|| sUA.indexOf('Google') != -1) {
			return 'Google';
		}
		if (sUA.indexOf('Firefox') != -1) {
			return 'Firefox';
		}
		return "Gecko";
	}
	// Netscape浏览器
	if (sUA.indexOf('Netscape') != -1) {
		this.browseKernel = 'Gecko';
		if (document.getElementById) {
			return "Netscape 6+";
		} else {
			return 'Netscape 5-';
		}
	}
	// 检测Safari浏览器
	if (sUA.indexOf('Safari') != -1) {
		this.browseKernel = 'KHTML';
		return 'Safari';
	}
	if (sUA.indexOf('konqueror') != -1) {
		this.browseKernel = 'KHTML';
		return 'Konqueror';
	}
	// 目前世界公认浏览网页速度最快的浏览器，但它占用的系统资源也很大。
	if (sUA.indexOf('Opera') != -1) {
		this.browseKernel = 'Presto';
		if (window.opera && document.childNodes) {
			return 'Opera 7+';
		} else {
			return 'Opera 6-';
		}
		return 'Opera';
	}
	if ((sUA.indexOf('hotjava') != -1)
			&& typeof (navigator.accentColorName) == 'undefined') {
		return 'HotJava';
	}
	if (document.all && document.getElementById && navigator.savePreferences
			&& (sUA.indexOf('netfront') < 0) && navigator.appName != 'Blazer') {
		return 'Escape 5';
	}
	// Konqueror / Safari / OmniWeb 4.5+
	if (navigator.vendor == 'KDE'
			|| (document.childNodes
					&& (!document.all || navigator.accentColorName) && !navigator.taintEnabled)) {
		this.browseKernel = 'KHTML';
		return 'KDE';
	}
	if (navigator.__ice_version) {
		return 'ICEbrowser';
	}
	if (window.ScriptEngine && ScriptEngine().indexOf('InScript') + 1) {
		if (document.createElement) {
			return 'iCab 3+';
		} else {
			return 'iCab 2-';
		}
	}
	if (document.layers && !document.classes) {
		return 'Omniweb 4.2-';
	}
	if (document.layers && !navigator.mimeTypes['*']) {
		return 'Escape 4';
	}
	if (navigator.appName.indexOf('WebTV') + 1) {
		return 'WebTV';
	}
	if (sUA.indexOf('netgem') != -1) {
		return 'Netgem NetBox';
	}
	if (sUA.indexOf('opentv') != -1) {
		return 'OpenTV';
	}
	if (sUA.indexOf('ipanel') != -1) {
		return 'iPanel MicroBrowser';
	}
	if (document.getElementById && !document.childNodes) {
		return 'Clue browser';
	}
	if (document.getElementById
			&& ((sUA.indexOf('netfront') != -1) || navigator.appName == 'Blazer')) {
		return 'NetFront 3+';
	}
	if ((sUA.indexOf('msie') + 1) && window.ActiveXObject) {
		return 'Pocket Internet Explorer';
	}
	return "Unknown";
}

/* cookie */
$.tmCookie = {
	setCookie : function(name, value, time, option) {
		var str = name + '=' + escape(value);
		var date = new Date();
		date.setTime(date.getTime() + this.getCookieTime(time));
		str += "; expires=" + date.toGMTString();
		if (option) {
			if (option.path)
				str += '; path=' + option.path;
			if (option.domain)
				str += '; domain=' + option.domain;
			if (option.secure)
				str += '; true';
		}
		document.cookie = str;
	},
	getCookie : function(name) {
		var arr = document.cookie.split('; ');
		if (arr.length == 0)
			return '';
		for (var i = 0; i < arr.length; i++) {
			tmp = arr[i].split('=');
			if (tmp[0] == name)
				return unescape(tmp[1]);
		}
		return '';
	},
	delCookie : function(name) {
		$.tmCookie.setCookie(name, '', -1);
		var date = new Date();
		date.setTime(date.getTime() - 10000);
		document.cookie = name + "=; expire=" + date.toGMTString() + "; path=/";
	},

	getCookieTime : function(time) {
		if (time <= 0)
			return time;
		var str1 = time.substring(1, time.length) * 1;
		var str2 = time.substring(0, 1);
		if (str2 == "s") {
			return str1 * 1000;
		} else if (str2 == "m") {
			return str1 * 60 * 1000;
		} else if (str2 == "h") {
			return str1 * 60 * 60 * 1000;
		} else if (str2 == "d") {
			return str1 * 24 * 60 * 60 * 1000;
		}
	}
};
/* array */

$.tmArray = {
	/* each和map的功能是一样的 */
	each : function(arr, fn) {
		fn = fn || Function.K;
		var a = [];
		var args = Array.prototype.slice.call(arguments, 1);
		for (var i = 0; i < arr.length; i++) {
			var res = fn.apply(arr, [ arr[i], i ].concat(args));
			if (res != null)
				a.push(res);
		}
		return a;
	},
	/* each和map的功能是一样的 */
	map : function(arr, fn, thisObj) {
		var scope = thisObj || window;
		var a = [];
		for (var i = 0, j = arr.length; i < j; ++i) {
			var res = fn.call(scope, arr[i], i, this);
			if (res != null)
				a.push(res);
		}
		return a;
	},
	orderBy : function(array, sortFlag) {
		var $arr = array;
		if (sortFlag == 'asc') {
			$arr.sort(this._numAscSort);
		} else if (sortFlag == 'desc') {
			$arr.sort(this._numDescSort);
		} else {
			$arr.sort(this._numAscSort);
		}
		return $arr;
	},
	// 求两个集合的并集
	union : function(a, b) {
		var newArr = a.concat(b);
		return this.unique2(newArr);
	},
	// 求两个集合的补集
	complement : function(a, b) {
		return this.minus(this.union(a, b), this.intersect(a, b));
	},
	// 求两个集合的交集
	intersect : function(a, b) {
		a = this.unique(a);
		return this.each(a, function(o) {
			return b.contains(o) ? o : null;
		});
	},
	// 求两个集合的差集
	minus : function(a, b) {
		a = this.unique(a);
		return this.each(a, function(o) {
			return b.contains(o) ? null : o;
		});
	},
	max : function(arr) {
		return Math.max.apply({}, arr);
	},
	min : function(arr) {
		return Math.min.apply({}, arr);
	},
	unique : function(arr) {
		var ra = new Array();
		for (var i = 0; i < arr.length; i++) {
			if (!ra.contains(arr[i])) {
				// if(this.contains(ra,arr[i])){
				ra.push(arr[i]);
			}
		}
		return ra;
	},
	unique2 : function(arr) {
		for (var i = 0; i < arr.length; i++) {
			for (var j = i + 1; j < arr.length;) {
				if (arr[j] == arr[i]) {
					arr.splice(j, 1);
				} else {
					j++;
				}
			}
		}
		return arr;
	},
	indexOf : function(arr, val) {
		for (var i = 0; i < arr.length; i++) {
			if (arr[i] == val)
				return i;
		}
		return -1;
	},
	contains : function(arr, val) {
		return this.indexOf(arr, val) != -1 ? true : false;
	},
	remove : function(arr, index) {
		var index = this.indexOf(arr, index);
		if (index > -1) {
			arr.splice(index, 1);
		}
		return arr;
	},
	removeObject : function(arr, item) {
		for (var i = 0; i < arr.length; i++) {
			var jsonData = arr[i];
			for ( var key in jsonData) {
				if (jsonData[key] == item) {
					arr.splice(i, 1);
				}
			}
		}
		return arr;
	},
	toArray : function(arrString, sp) {
		if (sp == undefined)
			sp = ",";
		if (arrString == undefined)
			return this;
		var arrs = arrString.split(sp);
		return arrs;
	},
	_numAscSort : function(a, b) {
		return a - b;
	},
	_numDescSort : function(a, b) {
		return b - a;
	},
	_sortAsc : function(x, y) {
		if (x > y) {
			return 1;
		} else {
			return -1;
		}
	},
	_sortDesc : function(x, y) {
		if (x > y) {
			return -1;
		} else {
			return 1;
		}
	}

};
/* array end */
/** *******加密算法*********** */
function encryption(str, k) {
	var string = "";
	for (var i = 0; i < str.length; i++) {
		var c = str.charCodeAt(i);
		if (c >= 97 && c <= 122) {
			c += k % 26;
			if (c < 97) {
				c += 26;
			}
			if (c > 122) {
				c -= 26;
			}
		} else if (c >= 65 && c <= 90) {
			c += k % 26;
			if (c < 65) {
				c += 26;
			}
			if (c > 122) {
				c -= 26;
			}
		}
		string += String.fromCharCode(c);
	}
	return string;
}

function dencryption(str, n) {
	var string = "";
	var k = parseInt("-" + n);
	for (var i = 0; i < str.length; i++) {
		var c = str.charCodeAt(i);
		if (c >= 97 && c <= 122) {
			c += k % 26;
			if (c < 97) {
				c += 26;
			}
			if (c > 122) {
				c -= 26;
			}
		} else if (c >= 65 && c <= 90) {
			c += k % 26;
			if (c < 65) {
				c += 26;
			}
			if (c > 122) {
				c -= 26;
			}
		}
		string += String.fromCharCode(c);
	}
	return string;
}
/* 验证是否为图片 */
function tmCheckImage(fileName) {
	return /(gif|jpg|jpeg|png|GIF|JPG|PNG)$/ig.test(fileName);
};

/* 验证是否为视频 */
function tmCheckVideo(fileName) {
	return /(mp4|mp3|flv|wav)$/ig.test(fileName);
};

/* 验证是否为文档 */
function tmCheckDocument(fileName) {
	return /(doc|docx|xls|xlsx|pdf|txt|ppt|pptx|rar|zip|html|jsp|sql|htm|shtml|xml)$/ig
			.test(fileName);
};

function tmCheckOffice(fileName) {
	return /(doc|docx|xls|xlsx|pdf|txt|ppt|pptx)$/ig.test(fileName);
};

var flag = false;
function DrawImage(ImgD, iwidth, iheight) {
	// 参数(图片,允许的宽度,允许的高度)
	var image = new Image();
	image.src = ImgD.src;
	if (image.width > 0 && image.height > 0) {
		flag = true;
		if (image.width / image.height >= iwidth / iheight) {
			if (image.width > iwidth) {
				ImgD.width = iwidth;
				ImgD.height = (image.height * iwidth) / image.width;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
			ImgD.alt = image.width + "×" + image.height;
		} else {
			if (image.height > iheight) {
				ImgD.height = iheight;
				ImgD.width = (image.width * iheight) / image.height;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
			ImgD.alt = image.width + "×" + image.height;
		}
	}
};

/* 手机 */
function is_cellphoneNum(str) {
	var regExp = /^(\+86)?(13|18|15)\d{9}(?!\d)$/;
	return regExp.test(str);
}

/* 邮件格式 */
function is_email(str) {
	var regExp = /^([\w\.])+@\w+\.([\w\.])+$/;
	return regExp.test(str);
}

/* 初始化验证码,防止读取浏览器缓存 */
function initVerifi(obj) {
	$("#" + obj).trigger("click");
}

function stripscript(s) {
	var pattern = new RegExp(
			"[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？]")
	var rs = "";
	for (var i = 0; i < s.length; i++) {
		rs = rs + s.substr(i, 1).replace(pattern, '');
	}
	return rs;
}

function tz_animateIn(index) {
	var animateIn = [];
	// animateIn.push("animated bounce");//0
	// animateIn.push("animated tada");//1
	animateIn.push("animated swing");// 2
	// animateIn.push("animated wobble");//3
	// animateIn.push("animated flip");//4
	// animateIn.push("animated flipInX");//5
	// animateIn.push("animated flipInY");//6
	animateIn.push("animated fadeIn");// 7
	animateIn.push("animated fadeInUp");// 8
	animateIn.push("animated fadeInDown");// 9
	animateIn.push("animated fadeInLeft");// 10
	animateIn.push("animated fadeInRight");// 11
	animateIn.push("animated fadeInUpBig");// 12
	animateIn.push("animated fadeInDownBig");// 13
	animateIn.push("animated fadeInLeftBig");// 14
	animateIn.push("animated fadeInRightBig");// 15
	animateIn.push("animated bounceIn");// 16
	animateIn.push("animated bounceInUp");// 17
	animateIn.push("animated bounceInDown");// 18
	animateIn.push("animated bounceInLeft");// 19
	animateIn.push("animated bounceInRight");// 20
	animateIn.push("animated rotateIn");// 21
	animateIn.push("animated rotateInUpLeft");// 22
	animateIn.push("animated rotateInDownLeft");// 23
	animateIn.push("animated rotateInUpRight");// 24
	animateIn.push("animated rotateInDownRight");// 25
	animateIn.push("animated rollIn");// 26
	if (!index) {
		var len = animateIn.length;
		var r = Math.floor(Math.random() * (len - 1) + 1);
		return animateIn[r];
	} else {
		return animateIn[index];
	}
}

function tz_animateOut(index) {
	var animateOut = [];
	// animateOut.push("animated flipOutX");//0
	// animateOut.push("animated flipOutY");//1
	animateOut.push("animated fadeOut");// 2
	animateOut.push("animated fadeOutUp");// 3
	animateOut.push("animated fadeOutDown");// 4
	animateOut.push("animated fadeOutLeft");// 5
	animateOut.push("animated fadeOutRight");// 6
	animateOut.push("animated fadeOutUpBig");// 7
	animateOut.push("animated fadeOutDownBig");// 8
	animateOut.push("animated fadeOutLeftBig");// 9
	animateOut.push("animated fadeOutRightBig");// 10
	animateOut.push("animated bounceOut");// 11
	animateOut.push("animated bounceOutUp");// 12
	animateOut.push("animated bounceOutDown");// 13
	animateOut.push("animated bounceOutLeft");// 14
	animateOut.push("animated bounceOutRight");// 15
	animateOut.push("animated rotateOut");// 16
	animateOut.push("animated rotateOutUpLeft");// 17
	animateOut.push("animated rotateOutDownLeft");// 18
	animateOut.push("animated rotateOutDownRight");// 19
	animateOut.push("animated rollOut");// 21
	// animateOut.push("animated hinge");//20
	if (!index) {
		var len = animateOut.length;
		var r = Math.floor(Math.random() * (len - 1) + 1);
		return animateOut[r];
	} else {
		return animateOut[index];
	}
}
//随机颜色
function getRandomColor() {
	return '#' + (function(h) {
		return new Array(7 - h.length).join("0") + h;
	})((Math.random() * 0x1000000 << 0).toString(16));
};

function getTitle(year, month, day) {
	var title = "";
	if (isNotEmpty(year)) {
		title = year + "年度";
	}
	if (isNotEmpty(month)) {
		title += month + "月份";
	}
	if (isNotEmpty(day)) {
		title += day + "日";
	}
	return title;
}

$(function(){
	$(".tzui-tips").tzTip();
});

