$(document).ready(function() {
    
    let isLogin = false;
    checkLoginStatus();

    
    loadActivities();

    
    if (isLogin) {
        loadApplyList();
    }

    
    $(document).on('click', '.apply-btn', function() {
        if (!isLogin) {
            const goLogin = confirm("您还未登录，是否立即登录？");
            if (goLogin) {
                window.location.href = 'login.html';
            }
            return;
        }

        const activityId = $(this).data('id');
        $.ajax({
            url: 'api/apply_volunteer.php',
            type: 'POST',
            data: {activity_id: activityId},
            dataType: 'json',
            success: function(res) {
                alert(res.msg);
                if (res.code === 1) {
                    loadApplyList(); 
                }
            },
            error: function() {
                alert('网络错误，请重试！');
            }
        });
    });

    
    function checkLoginStatus() {
        $.ajax({
            url: 'api/check_login.php',
            type: 'GET',
            dataType: 'json',
            async: false, 
            success: function(res) {
                if (res.code === 1) {
                    isLogin = true;
                } else {
                    isLogin = false;
                }
            },
            error: function() {
                isLogin = false;
            }
        });
    }

    
    function loadActivities() {
        $.ajax({
            url: 'api/get_volunteer.php',
            type: 'GET',
            dataType: 'json',
            success: function(res) {
                if (res.code === 1 && res.data.length > 0) {
                    let html = '';
                    res.data.forEach(item => {
                        html += `
                        <div class="volunteer-card">
                            <h4 class="volunteer-title">${item.title}</h4>
                            <p><strong>活动描述：</strong> ${item.description}</p>
                            <p><strong>服务时间：</strong> ${item.service_time}</p>
                            <p><strong>招募要求：</strong> ${item.requirements}</p>
                            <button class="btn btn-success apply-btn" data-id="${item.id}">立即报名</button>
                        </div>
                        `;
                    });
                    $('#activityList').html(html);
                } else {
                    $('#activityList').html('<p class="text-center">暂无志愿活动</p>');
                }
            },
            error: function() {
                $('#activityList').html('<p class="text-center">活动数据加载失败</p>');
            }
        });
    }

    
    function loadApplyList() {
        $.ajax({
            url: 'api/get_apply_list.php',
            type: 'GET',
            dataType: 'json',
            success: function(res) {
                if (res.code === 1 && res.data.length > 0) {
                    let html = '<ul class="list-group">';
                    res.data.forEach(item => {
                        html += `
                        <li class="list-group-item">
                            <strong>${item.title}</strong> 
                            <span class="text-muted">报名时间：${item.apply_time}</span>
                            <br>
                            <small>服务时间：${item.service_time}</small>
                        </li>
                        `;
                    });
                    html += '</ul>';
                    $('#applyList').html(html);
                } else {
                    $('#applyList').html('<p class="text-muted">暂无报名记录</p>');
                }
            },
            error: function() {
                $('#applyList').html('<p class="text-danger">报名记录加载失败</p>');
            }
        });
    }
});