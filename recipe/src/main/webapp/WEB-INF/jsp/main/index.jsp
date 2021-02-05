<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<title>test</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-ui-1.11.4.min.js"></script>
<script defer type="text/javascript" src="//translate.google.com/translate_a/element.js?cd=googleTranslateElementInit"></script>
<style type="text/css">
.wrap-loading { /*화면 전체를 어둡게 합니다.*/
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	/* background: rgba(0, 0, 0, 0.2); */ /*not in ie */
}
.wrap-loading div { /*로딩 이미지*/
	z-index: 9999;
	position: absolute;
	top: 50%;
	left: 50%;
	/* margin-left: -2000px;
	margin-top: -200px;  */
}
.display-none { /*감추기*/
	display: none;
}
</style>
</head>

<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<div id="wrap">
	<div class="wrap-loading display-none">
		<img src="${pageContext.request.contextPath}/images/loading2.gif" />
	</div>
	
	<input type="text" name="meterial" value="">
	<button onclick="getRecipe();">확인</button>
	
	<table class="">
		<thead>
			<tr>
				<th scope="col">레시피 번호</th>
				<th scope="col">레시피 명</th>
				<th scope="col">만드는 법</th>
				
			</tr>
		</thead>
		<tbody id="recipe">
			
		</tbody>
	</table>
</div>
<script type="text/javascript">

function getRecipe() {
	var meterial = $('input[name=meterial]').val();

	console.log(meterial);
	params = {
		meterial : meterial
	}

	$.ajax({
		url : "/cmm/getRecipe.ajax",
		type : "GET",
		data : params,
		dataType: "json",
		success : function(data) {
			var recipeList = data.recipeList;
			var row = "";
			console.log(data);
			console.log(recipeList);
			
			$("#recipe").children("tr").remove();
			 for(var i=0; i<recipeList.length; i++) {
				 var recipe = recipeList[i].cooking_DC.split('/');
				 
				 row += '<tr class="line" id="index'+ recipeList[i].recipe_ID +'">';
	             row += '<td>';
	             row +=  recipeList[i].recipe_ID;
	             row += '</td>';
	             row += '<td>';
	             row += recipeList[i].recipe_NM_KO;
	             row += '</td>';
	             row += '<td>';
	             for(var k=0; k<recipe.length; k++){
		             row += recipe[k] + "<br>";
	             }
	             row += '</td>';
	             row += '</tr>';
			 }
			 $("#recipe").append(row);
			
		}, beforeSend:function(){
			 
			var width = 0;
            var height = 0;
            var left = 0;
            var top = 0;
            width = 100;
            height = 100;
            top = ( $(window).height() - width*1.5) / 2 + $(window).scrollTop();
            left = ( $(window).width() - height*1.5) / 2 + $(window).scrollLeft();
            if($(".wrap-loading").length != 0) {
                   $(".wrap-loading").css({
                        "top": top +"px",
                        "left": left +"px"
                   });
                   //$("#div_ajax_load_image").show();
                   $('.wrap-loading').removeClass('display-none');
            } 
            
            //$('.wrap-loading').removeClass('display-none');
            $('#deleteArticle').attr('disabled', true);
	    },complete:function(){
	    	setTimeout(function () {
		        $('.wrap-loading').addClass('display-none');
	            $('#deleteArticle').attr('disabled', false);
			}, 1000);
	    },
		error : function(request, status, error) {
			console.log(request.status);
			console.log(status);
			console.log(error);
			alert("ERROR");
		},
		timeout : 30000 //제한시간 지정
		});
	}
</script>
</body>
</html>

