function scroll(){
    var scrollTop=$(window).scrollTop();//获取当前窗口距离顶部的高度
    if(scrollTop>=10){
        $('.myiframe').css('background','white');
    }else
    {
        $('.myiframe').css('background','none');
    }
}

$(window).on('scroll',function(){
    scroll()
});
$(document).ready(function() {
    
});