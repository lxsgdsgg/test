	var tabDiv="";
	var context="";
$(function(){
	var menu=$(".menudiv").first();
	var tab=$(".tab").first();
		tabDiv=$(".tabDiv").first();
		context=$(".context").first();
		document.onclick=function(){ $(".childrenPanel").hide()};
	$(".openTab").click(function(){
		openTab(this);
	});
	/*菜单展示*/
		$.getJSON("menuData",function (data){
			$("<div class='expandDiv menu'></div>").hover(function(){
				if($(this).find(".expandDivChildrenPanel").length==0){
					menu.find(".menuItem").clone(true).appendTo($("<div class='expandDivChildrenPanel'></div>").appendTo($(this)));
				}
			},function(){
				$(this).find(".expandDivChildrenPanel").remove();
			}).html("<div class='mt'><i class='Hui-iconfont'>&#xe667;</i></div>").appendTo(menu);
			for(var i=0;i<data.data.length;i++){
				var tmp=data.data[i];
				$("<div class='menuItem'></div>").hover(function(){
					if($(this).find(".childrenPanel").length==0){
						$(this).find(".mt").addClass("mthover");
						var childPanel=$("<div class='childrenPanel'></div>").appendTo($(this));
						for(var j=0;j<$(this).data("children").length;j++){
							var tp=$(this).data("children")[j];
							var d=$("<div class='childrenPanelNode'></div>").appendTo(childPanel);
							$("<span class='nodeTitle'></span>").html(tp.title).appendTo(d);
							var childTmp=$("<span class='nodePanel'></span>").appendTo(d);
							if(tp.children!=undefined){
								for(var k=0;k<tp.children.length;k++){
									$("<span  onclick='openTab(this)' id="+tp.children[k].id+" url="+tp.children[k].url+"></span>").html(tp.children[k].title).appendTo(childTmp);
								}
							}
						}
						childPanel.find(".childrenPanelNode").last().css("border","none");
					}
				},function(){
					$(this).find(".mt").removeClass("mthover");
					$(this).find(".childrenPanel").remove();
				}).html("<div class='mt'>"+tmp.title+"</div>").data("children",tmp.children).appendTo($("<div class='hengChild menu'></div>").appendTo(menu));
			}
		});		
});
	/*打开Tab*/
	function openTab(obj){
			if(tabDiv.find("#"+obj.id).length==0)
			{
				var addMenu=$("<div class='tabMenu' id="+obj.id+" onclick='switchTab(this)'>"+obj.innerHTML+"</div>").addClass("selected");
				$(".tabDiv > div").removeClass("selected");
				addMenu.append($("<span id='"+obj.id+"' onclick='closeTab(this)'><i class='Hui-iconfont'>&#xe6a6;</i></span>")).appendTo(tabDiv);
				$("<div id="+obj.id+"></div>").load(obj.getAttribute("url")).appendTo(context);
				$(".context").find("#"+obj.id).show().siblings().hide();
			}
			else{
				switchTab(obj);
			}
	}
	
	/* 切换Tab*/
	function switchTab(objTab){
		if(objTab.id=='indexTab'){
			$(objTab).addClass("selected").siblings().removeClass("selected");
		}
		else{
			$(".tabDiv > div").find("#"+objTab.id).parent().addClass("selected").siblings().removeClass("selected");			
		}
		$(".context").find("#"+objTab.id).show().siblings().hide();
	}
	
	/*关闭单个Tab*/
	function closeTab(obj){
		if($(obj).parent().hasClass("selected")){			
			var objPrev=$(obj).parent().prev()[0];
			$(".tabDiv > div").find("#"+objPrev.id).parent().addClass("selected").siblings().removeClass("selected");
			$(".context > div").find("#"+objPrev.id).show().siblings().hide();
			$(".tab #"+obj.id).each(function(index,elem){
				$(elem).remove();
			})
		}
	}
		/*关闭所有Tab*/
		function closeAllTab(){			
				$("#indexTab").addClass("selected").siblings().removeClass("selected");
				$(".tabDiv #indexTab").show().siblings().remove();
				$(".context #indexTab").show().siblings().remove();

	}
