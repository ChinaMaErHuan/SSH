/**
 * authod:maerhuan
 * date:2017-05-11
 * 后台通用js
 */
var tzAdmin = {
	//静态化内容的方法
	staticContent:function(obj){
		loading("提示：静态化中...");
		$.tzAjax.request({url:basePath+"/json/staticHtml/staticHtml",callback:function(data){
			if(data.result=="success"){
				loading("静态化成功...",4);
			}else{
				loading("静态失败...",4);
			}
		}},{cid:$(obj).data("opid")});
	}
		
};
