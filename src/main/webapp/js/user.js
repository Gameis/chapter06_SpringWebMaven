$('#writeBtn').click(function() {
	$('#idDiv').empty();
	$('#pwdDiv').empty();
	
	if($('#name').val() == '')
		$('#nameDiv').html('이름 입력');
	else if($('#id').val() == '')
		$('#idDiv').html('아이디 입력');
	else if($('#pwd').val() == '')
		$('#pwdDiv').html('비밀번호 입력');
	else
		$('#writeForm').submit();
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