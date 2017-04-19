<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
		<link rel="stylesheet" href="css/jhjm.css" />
		<link rel="stylesheet" type="text/css" href="css/easyui.css">
		<link rel="stylesheet" type="text/css" href="css/icon.css">
		<link rel="stylesheet" type="text/css" href="css/demo.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/jhjm.js"></script>
		<title></title>
	</head>

	<body>
		<div id="body">

			<!--头部蓝色部分-->
			<header class="jh_title">
				<div class="jh_title_word">
					<div class="jh_title_word" id="jh_title_xzdw">
						<span class="word">选择单位</span>
						<select class="easyui-combotree" id="jh_text" data-options="url:'json/Search_putTree',method:'get'" style="height: 180%;
	width: 77%;">
						</select>
					</div>

					<div class="jh_title_word" id="jh_title_keyword">
						<span class="word">关键词</span>
						<input type="text" class="jh_text" id="gjc" rows="1" cols="10" placeholder="在此输入关键词..."></textarea>
						<img id="search" src="img/search.png" />
					</div>

					<!--头部选择职务-->
					<div class="jh_title_word" id="jh_zhiwu">
						<input type="radio" class="radio_zw" name="radio_zw" value="全警" checked="checked" /><span>全警</span>
						<input type="radio" class="radio_zw" name="radio_zw" value="民警" /><span>民警</span>
						<input type="radio" class="radio_zw" name="radio_zw" value="现役" /><span>现役</span>
						<input type="radio" class="radio_zw" name="radio_zw" value="辅警" /><span>辅警</span>
					</div>
				</div>
			</header>

			<!--左边的树部分-->
			<nav class="body_dwlb">
				<div class="dwlb_title"><b>单位列表</b></div>
				<ul id="deptTree" class="easyui-tree"></ul>
			</nav>
			
			

			<!--右边的表格部分-->
			<div class="body_bg">
				<div class="jhjm_chart">
					<table id="chart_content" class="chart_content">
					</table>

					<table id="chart_content_1" class="chart_content">
					</table>
				</div>
			<!--下面的分页条-->
				<div class="easyui-panel" style="width: 100%;" >
					<div id="pagination" class="easyui-pagination" >
					</div>
				</div>
			</div>

		</div>
	</body>

</html>
