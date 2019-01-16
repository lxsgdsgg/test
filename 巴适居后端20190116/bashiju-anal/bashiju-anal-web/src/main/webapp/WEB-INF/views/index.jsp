<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <script type="text/javascript" src="https://s.bashiju.com/j/im/util.js"></script>  
    <script type="text/javascript" src="https://s.bashiju.com/j/im/message.js"></script>  
    <script type="text/javascript" src="https://s.bashiju.com/j/im/messagebody.js"></script>  
    <script type="text/javascript" src="https://s.bashiju.com/j/im/client/websocketconfig.js"></script>
    <script type="text/javascript" src="https://s.bashiju.com/j/im/client/chat.js"></script>
        	<script type="text/javascript"  src="/src/sso-platform/im/js/jquery-1.12.4.min.js"></script>
    <input id='msg' type='text'/>

    <script type="text/javascript">
    	$(function(){
    		initLayIm("72e6d891b2b74ab592261a6a94c87248");
    	});
    </script>
    	
        <button onclick=initLayIm.sendMsg($("#msg").val(),"小白","http://pic.qqtn.com/file/2013/2013-5/2013051515113135806.png","42fac217c15d4b5baadd9dbc335c99a3",null)>发送消息</button>
    
</body>
</html>