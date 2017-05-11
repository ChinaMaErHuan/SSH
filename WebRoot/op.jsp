<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'op.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/sg/jquery-1.11.1.min.js"></script>
  </head>
  
  <body>
  	//静态化文件 测试
  	//第二种方式:servlet+静态化
  	<a href="javascript:void(0)" onclick="staticUrl()" id="static">静态化</a>
  	<script type="text/javascript">
  		//原理是：网页的源代码另存为一个静态文件
  		function staticUrl(){
  			$.ajax({
  				type:"post",
  				url:"static.jsp",
  				success:function(data){
  					tz_main(data);
  				}
  			});	
  		}
  		
  		function tz_main(source){
  			$.ajax({
  				type:"post",
  				url:"sop.jsp",
  				data:{"source":source},
  				success:function(data){
  					alert("静态化成功!!!");
  				}
  			});
  		}
  	</script>
  </body>
</html>
