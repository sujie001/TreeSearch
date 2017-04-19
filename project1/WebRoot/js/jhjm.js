$(function() {
	jhjm_chart("建湖县公安局");
	widAndHei('#body');
	page();
	tree_click();
})

function widAndHei(obj) {
	/*初始宽高*/
	$(obj).width($(window).width());
	$(obj).height($(window).height());
	/*窗口变化时大小*/
	$(window).resize(function() {
		$(obj).width($(window).width());
		$(obj).height($(window).height());
	});
}

/*点击树调用函数*/
function tree_click(){
	$('#deptTree').tree({
    url: 'json/Search_putTree2',
    method:'get',animate:true,
    onClick:function(node){
    	$("#jh_text").combotree("setValue",node.text);
    	jhjm_chart();
    	}
    });
};

/*设置鼠标点击或者覆盖表格某行颜色变化*/
function chart_click() {
	$('tbody>tr').click(function() {
		$(this).addClass('hilite') //为选中项添加高亮
			.siblings().removeClass('hilite') //去除其他项的高亮形式
			.end();
	});
}

/*搜索的鼠标覆盖以及点击效果*/
$(function(){
	$("#search").mousemove(function(){
		$("#search").attr("src",  "img/search1.png");
	})
	.mouseout(function(){
		$("#search").attr("src",  "img/search.png");   
	})
	.click(function(){
		jhjm_chart();
	});
})

/*获取表格数据*/
function jhjm_chart() {
	var DW=$("input.textbox-text.validatebox-text:text").val();
	if ($("input.textbox-text.validatebox-text:text").val()!="建湖县公安局"){
		var DW=("建湖县"+DW);
	}

	var KEY=$("#gjc").val();
	var JINGZHONG=$('input[name="radio_zw"]:checked').val();
	var PAGE=$(".pagination-num").val();
	$.ajax({
		type: "post",
		url: "json/Search_search",
		data:{
			'dw':DW,
			'key':KEY,
			'jingzhong':JINGZHONG,
			'page':PAGE
		},
		async: false,
		dataType: 'json',
		success: function(data) {
	    	alert(DW+KEY+JINGZHONG+PAGE);
			var total = data.total;
			var list = data.data;
			var sb = new Array();
			sb.push('<tr>');
			sb.push('<th>'+"警号"+'</th>'+
					'<th>'+"姓名"+'</th>'+
					'<th>'+"身份证号码"+'</th>'+
					'<th>'+"所在单位"+'</th>'+
					'<tr>');
			for(var i = 0; i < data.data.length; i++) {
				if((i % 2) == 0) sb.push('<tr>');
				else sb.push('<tr class="alt">');
				sb.push('<td>' + list[i].jh+ '</td>' +
					'<td>' + list[i].xm+ '</td>' +
					'<td>' + list[i].sfzhm + '</td>' +
					'<td>' + list[i].mc + '</td>' +
					'</tr>');
			}
			page(total);
			$('#chart_content').html(sb.join(''));
			chart_click();
		}
	});
}

/*分页条*/
function page(a) {
	$('#pagination').pagination({
		total: a, //数据总条数  
		pageSize: 13, //页大小，即每页显示多少条数据  
		showPageList: false,
		showRefresh: false,
		displayMsg: '',
		onSelectPage:function(){
		jhjm_chart();
		}
	});
}