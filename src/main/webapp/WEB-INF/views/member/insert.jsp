<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript"> </script>
<script> 
$(document).ready(function () {

    $('#join').submit(function(){
        //alert("알럿!");
       if ($('.password2').val()!=$('.password').val()) {
                $('.pass2div').html("비밀번호가 다릅니다. 확인하고 다시 작성해주세요 ❤❤");
                $('.pass2div').focus();
                return false;
            }
       if($('#user_id2').val()=='1') {
	         alert('아이디 중복');
	         return false; 
	      } 
       
       
       
    });

    
	

	//아이디 중복체크
    $('.idCheck').click(function(){
    	//alert($("#user_id").val());
		$.ajax({
		url : "/member/idCheck",
		type : "post",
		dataType : "json",
		data : {"user_id" : $("#user_id").val()},
		success : function(data){
			//alert(data);
			if(data == 1){
				$('#user_id2').val(1);
				alert("중복된 아이디입니다.");
			}else if(data == 0){
				$('#user_id2').val(0);
				$("#idCheck").attr("value", "Y");
				alert("사용가능한 아이디입니다.");
			}
		}
	});
});
	
	
}); 







</script>
</head>
<body>
	<h1>회원가입</h1>
	<fieldset>
	<legend>회원가입 </legend>
	<form action="/member/insert" id="join" method="post">
		이메일 : <input type="text" name="user_id" id="user_id" class="user_id" placeholder=" @를 포함해서 입력해주세요.">
		<button class="idCheck" type="button" id="idCheck"  value="N" >중복체크</button> <br>
		<input type="hidden" name="user_id2" id="user_id2" value="0">
		비밀번호 : <input type="password" name="user_pw" class="password" placeholder="비밀번호를 입력하세요."><br>
		<div class="passdiv"> </div> <br> 
		비밀번호 확인 : <input type="password" name="user_pw1" class="password2" placeholder="비밀번호를 재입력하세요."><br>
		<div class="pass2div"> </div> <br> 
		이름 : <input type="text" name="user_name" placeholder="이름을 입력하세요."><br>
		휴대번호 : <input type="text" name="user_phone" placeholder=" (-)제외후 입력해주세요."><br>
	<input type="submit" value="회원가입">	
		
		
		
	</form>
	</fieldset>
	
</body>
</html>