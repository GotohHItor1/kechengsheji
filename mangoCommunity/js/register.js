$(document).ready(function() {
    $('#registerForm').submit(function(e) {
        e.preventDefault();
        
        const formData = {
            fullName: $('#fullName').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            confirmPassword: $('#confirmPassword').val(),
            phone: $('#phone').val()
        };

        if (formData.password !== formData.confirmPassword) {
            alert("两次输入的密码不一致！");
            return;
        }

        $.ajax({
            url: 'api/register.php',
            type: 'POST',
            data: formData,
            dataType: 'json',
            success: function(res) {
                if (res.code === 1) {
                    alert(res.msg);
                    window.location.href = 'login.html';
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