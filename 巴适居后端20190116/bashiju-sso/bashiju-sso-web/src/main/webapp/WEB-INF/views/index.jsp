<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ZH">
<head>
	<meta charset="UTF-8">
	<title>巴适居</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
    <meta name="Author" content="larry" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<link rel="Shortcut Icon" href="../favicon.ico" />
	<link rel="stylesheet" href="https://s.bashiju.com/l/css/layui.css"media="all">
    <link rel="stylesheet" type="text/css" href="/src/common/frame/css/global.css" media="all">
    <link rel="stylesheet" type="text/css" href="/src/sso-platform/css/larryms.css" media="all">
    <script type="text/javascript"  src="/src/sso-platform/im/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="https://s.bashiju.com/l/layui.js"></script>
    <script>
        /^http(s*):\/\//.test(location.href) || alert('巴适居提示您 请先部署到 localhost 下再访问！！！');
    </script>
</head>
<body>
<div class="layui-layout layui-layout-admin layui-fluid larryms-layout" id="larry_layout">
	<!-- 顶部导航 -->
	<div class="layui-header larryms-header" id="larry_head">
        <div class="larryms-topbar-left" id="topbarL">
        	<span class="mini-logo"><img src="/src/sso-platform/images/logo_mini.png" alt=""></span>
         	<a class="layui-logo larryms-logo"><img src="/src/sso-platform/images/logo.png" alt=""></a>
         	<span class="larryms-switch larryms-icon-fold" id="menufold"><i class="larry-icon larry-fold7"></i></span>
         	<div class="larryms-mobile-menu" id="larrymsMobileMenu"><i class="larry-icon larry-caidan1"></i></div>
        </div>
        <div class="larryms-extend">
         	<div class="larryms-topbar-menu larryms-hide-xs clearfix">
         	    <ul class="larryms-nav clearfix fl" id="larryms_top_menu" lay-filter='TopMenu'>
         	    	 <!-- 若开启顶部菜单，此处动态生成 -->
                     
         	    </ul>
         	    <div class="dropdown extend-show" id="larryms_topSubMenu">
         	    	 <i class="submenubtn larry-icon larry-sandianshu" id="subMenuButton"></i>
         	    	 <ul class="dropdown-menu larryms-nav" id="dropdown">
         	    	 	
         	    	 </ul>
         	    </div>
            </div>
            <!-- 右侧常用菜单 -->
            <div class="larryms-topbar-right" id="topbarR">
            	<ul class="layui-nav clearfix">
                    <li class="layui-nav-item" lay-unselect>
                        <a id="message" class="message" data-group='0' data-url='html/message.html' data-id='1004' >
                            <i class="larry-icon larry-xiaoxi2" data-icon="larry-xiaoxi2" data-font="larry-icon"></i>
                            <cite>消息</cite>
                            <span class="layui-badge-dot"></span>
                        </a>
                    </li>
                    <li class="layui-nav-item" lay-unselect>
                        <a id="lock"><i class="larry-icon larry-diannao1"></i><cite>锁屏</cite></a>
                    </li>
                    <li class="layui-nav-item" lay-unselect>
                        <a id="clearCached"><i class="larry-icon larry-qingchuhuancun1"></i><cite>清除缓存</cite></a>
                    </li>
                    <li class="layui-nav-item" lay-unselect>
                        <a id="larryTheme"><i class="larry-icon larry-zhutishezhi-"></i><cite>主题设置</cite></a>
                    </li>
                    <!-- added by zuoyuntao 2018-06-01添加ERP系统导航上的个人中心 -->
                     <li class="layui-nav-item" lay-unselect="">
					    <a href="javascript:;" style="width:90px;"><i class="larry-icon larry-gerenxinxi">
					    		</i><cite>个人中心</cite></a>
						<dl class="layui-nav-child layui-anim layui-anim-upbit" >
							<dd id="modifyPsw">
								<a href="javascript:;"><span style="color:#23262e;">
            						<i class="layui-icon layui-icon-edit"></i>修改密码</span></a>
							</dd>
							<dd id="bindChart">
								<a href="javascript:;"><span style="color:#23262e;">
            						<i class="layui-icon layui-icon-login-wechat"></i><cite>绑定微信</cite></span></a>
							</dd>
							<dd id="workAttendance">
								<a href="javascript:;"><span style="color:#23262e;">
            						<i class="larry-icon larry-refresh"></i><cite>个人考勤</cite></span></a>
							</dd>
							<dd id="submitDaybill">
								<a href="javascript:;"><span style="color:#23262e;">
            						<i class="larry-icon larry-refresh"></i><cite>提交日报</cite></span></a>
							</dd>
							<dd id="myJob">
								<a href="javascript:;"><span style="color:#23262e;">
            						<i class="larry-icon larry-refresh"></i><cite>我的任务</cite></span></a>
							</dd>
							<dd id="refreshWorkSpace">
								<a href='/sso/main/index'><span style="color:#23262e;">
            						<i class="larry-icon larry-refresh"></i><cite>系统刷新</cite></span></a>
							</dd>
							<dd id="updateNotice">
								<a href="javascript:;"><span style="color:#23262e;">
            						<i class="layui-icon layui-icon-list"></i><cite>升级公告</cite></span></a>
							</dd>
							<dd id="customSet">
								<a href="javascript:;"><span style="color:#23262e;">
            						<i class="layui-icon layui-icon-set"></i><cite>个性化设置</cite></span></a>
							</dd>
							<dd id="downloadClient">
								<a href="javascript:;"><span style="color:#23262e;">
            						<i class="layui-icon layui-icon-download-circle"></i><cite>下载客户端</cite></span></a>
							</dd>
<!-- 							<dd id="quit"> -->
<!--             					<a id="logout" data-url='/sso/loginout'><span style="color:#23262e;"> -->
<!--             						<i class="larry-icon larry-exit"></i><cite>退出系统</cite></span></a> -->
<!-- 							</dd> -->
						</dl>
					</li>
					<!-- ended by zuoyuntao -->
            	</ul>
            </div>
        </div>
	</div>
	<!-- 内容主体 -->
	<div class="larryms-body" id="larryms_body">
		<!-- 左侧导航区域 -->
		<div class="layui-side pos-a larryms-left layui-bg-black" id="larry_left">
			<div class="layui-side-scroll">
                <!-- 管理员信息      -->
                <div class="user-info">
                    <div class="photo">
                        <img src="/src/sso-platform/images/user.jpg" id="user_photo" alt="">
                    </div>
                    <p><span id="uname" data-uuid=${userInfo.uuid}>${userInfo.depName} </span> ${userInfo.realName}</p>
                </div>
                <!-- 系统菜单 -->
                <div class="sys-menu-box" >
                    <ul class="larryms-nav larryms-nav-tree" id="larryms_left_menu" lay-filter="LarrySide" data-group='0'>
                        <!-- 此次动态生成 -->
                        
                    </ul>
                </div>    
			</div> 
		</div>
		<!-- 右侧框架内容区域 -->
		<div class="layui-body pos-a larryms-right" id="larry_right">
			<div class="layui-tab larryms-tab" id="larry_tab" lay-filter="larryTab">
                <div class="larryms-title-box clearfix" id="larryms_title">
                    <div class="larryms-btn-default larryms-press larryms-pull-left hide" id="goLeft"><i class="larry-icon larry-top-left-jt"></i></div> 
                    <ul class="layui-tab-title larryms-tab-title" lay-allowclose='false' id="larry_tab_title" lay-filter='larrymsTabTitle'>
                        <li class="layui-this" id="larryms_home" lay-id="0" data-group="0" data-id="larryms-home" fresh="1" data-url="html/main1.html">
                            <i class="larry-icon larry-shouye2" data-icon="larry-shouye2" data-font="larry-icon"></i><cite>后台首页</cite>
                        </li>
                    </ul>
                    <div class="larryms-btn-group clearfix">
                        <div class="larryms-btn-default larryms-press larryms-pull-right hide" id="goRight"><i class="larry-icon larry-gongyongshuangjiantouyou"></i></div>
                        <div class="refresh larryms-press" id="larryms_refresh">
                            <i class="larry-icon larry-shuaxin"></i>
                            <cite>刷新</cite>
                        </div>
                        <div class="larryms-press often" lay-filter="larryOperate" id="buttonRCtrl">
                            <ul class="larryms-nav">
                                <li class="larryms-nav-item">
                                    <a class="top"><i class="larry-icon larry-caozuo"></i><cite>常用操作</cite><span class="larryms-nav-more"></span></a>
                                    <dl class="larryms-nav-child layui-anim layui-anim-upbit">
                                        <dd id="tabCtrD">
                                            <a data-ename="positionCurrent"><i class="larry-icon larry-qianjin1"></i><cite>定位当前选项卡</cite></a>
                                        </dd>
                                        <dd id="tabCtrA">
                                            <a data-ename="closeCurrent"><i class="larry-icon larry-guanbidangqianye"></i><cite>关闭当前选项卡</cite></a>
                                        </dd>
                                        <dd id="tabCtrB">
                                            <a data-ename="closeOther"><i class="larry-icon larry-guanbiqita"></i><cite>关闭其他选项卡</cite></a>
                                        </dd>
                                        <dd id="tabCtrC">
                                            <a data-ename="closeAll"><i class="larry-icon larry-close-all"></i><cite>关闭全部选项卡</cite></a>
                                        </dd>
                                        <dd>
                                            <a data-ename="refreshAdmin"><i class="larry-icon larry-kuangjia_daohang_shuaxin"></i><cite>刷新最外层框架</cite></a>
                                        </dd>
                                    </dl>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div> 
                <!-- tab title end -->
                <div class="layui-tab-content larryms-tab-content" id="larry_tab_content">
                    <div class="layui-tab-item layui-show">
                        <iframe class="larry-iframe" data-id='0' name="ifr_0" id='ifr0'  src="html/main.html" frameborder="no" border="0"></iframe>
                    </div>
                </div>
                <!-- tab content end -->
            </div>
		</div>
		<!-- 移动端支持 -->
		<div class="larryms-mobile-shade" id="larrymsMobileShade"></div>
	</div>
	<!-- 底部固定区域 -->
	<div class="layui-footer larryms-footer" data-show='on' id="larry_footer">
		 <div class="copyright inline-block pos-al">Author larry © <a href="https://www.larrycms.com/" target="_blank">larrycms.com</a></div>
         <p class="block">云南巴适居网络服务有限公司</p>
		 <div class="larryms-info inline-block pos-ar">当前版本：<span id="larryms_version"></span><a href="https://www.larrycms.com/" target="_blank"><i class="layui-icon">&#xe67c;</i></a></div>
	</div>
</div>

<script type="text/javascript">
layui.cache.menusUrl ='menuData' ;//这里设置 菜单数据项接口地址 或data参数
layui.cache.page = 'index'; 
layui.cache.rightMenu = false;
//说明：并非一个页面只能加载一个模块 可以这样定义：'index,common';也并非每个页面都要定义一个模块，事实上模块根据功能需要可以公用
//layui.cache.rightMenu = 'custom'; //默认开启页面右键菜单，设置为 custom 时需要自定义右键菜单，设置为false 关闭右键菜单
layui.config({
   version:"2.0.7",
   base:'/src/common/'  //实际使用时，建议改成绝对路径
}).extend({
    larry:'js/base'
}).use('larry');
</script>

</body>

    <script type="text/javascript" src="https://s.bashiju.com/j/im/util.js"></script>  
    <script type="text/javascript" src="https://s.bashiju.com/j/im/message.js"></script>  
    <script type="text/javascript" src="https://s.bashiju.com/j/im/messagebody.js"></script>  
    <script type="text/javascript" src="https://s.bashiju.com/j/im/websocketconfig.js"></script>
    <script type="text/javascript" src="https://s.bashiju.com/j/im/chat.js"></script>
    <script type="text/javascript">
    	$(function(){
    		initLayIm($("#uname").data("uuid")+"","/");
    	})
    </script>
</html>

