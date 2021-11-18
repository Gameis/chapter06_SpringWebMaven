$(function() {
    alert('check');
    $.ajax({
        url: '/chapter06_SpringWebMaven/user/getUserList',
        type: 'post',
        dataType: 'json',
        success: function(data) {
            alert('성공!');
            alert(data);

            $.each(data, function(index, items) {
                $('<tr/>').append($('<td/>', {
                    width: 100,
                    align: 'center',
                    text: items.name
                })).append($('<td/>', {
                    width: 100,
                    align: 'center',
                    text: items.id
                })).append($('<td/>', {
                    width: 100,
                    align: 'center',
                    text: items.pwd
                })).appendTo('#table')

            });
        },
        error: function(err) {
            alert('실패!');
            console.log(err);
        }
    });
});