$(document).ready(function() {
    $('#loginForm').submit(function(e) {
        e.preventDefault();
        
        const formData = {
            email: $('#email').val(),
            password: $('#password').val(),
            rememberMe: $('#rememberMe').is(':checked') ? 1 : 0
        };

        $.ajax({
            url: 'api/login.php',
            type: 'POST',
            data: formData,
            dataType: 'json',
            success: function(res) {
                if (res.code === 1) {
                    alert(res.msg);
                    window.location.href = 'index.html';
                } else {
                    alert(res.msg);
                }
            },
            error: function() {
                alert("网络错误，请重试！");
            }
        });
    });
});