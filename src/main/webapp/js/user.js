$('#writeBtn').click(function() {
	$('#nameDiv').empty();
	$('#idDiv').empty();
	$('#pwdDiv').empty();
	
	if($('#name').val() == '')
		$('#nameDiv').html('이름 입력');
	else if($('#id').val() == '')
		$('#idDiv').html('아이디 입력');
	else if($('#pwd').val() == '')
		$('#pwdDiv').html('비밀번호 입력');
	else
		$.ajax({
			url: '/chapter06_SpringWebMaven/user/write',
			type: 'post',
			data: $('#writeForm').serialize(),
			success: function(){
				alert('회원 가입 등록');
			},
			error: function(err) {
				console.log(err);
			}
		});
});
		
$('#id').focusout(function() {
	if($('#id').val() == '')
		$('#idDiv').html('아이디 입력');
	
	else
		$.ajax({
			url: '/chapter06_SpringWebMaven/user/checkId',
			type: 'post',
			data: 'id=' + $('#id').val(),
			dataType: 'text',
			success: function(data) {
				if(data == 'exist') $('#idDiv').text('사용 불가능');
				else $('#idDiv').text('사용 가능');
			},
			error: function(err) {
				console.log(err);
			}
		});
});