(function(doc,win){
    var docEl = doc.documentElement;
    var resizeEvt = "onorientationchange" in win ? "orientationchange" : "resize";
    var Timer = null;
    function recalc(){
        var clientWidth = docEl.clientWidth || win.innerWidth;
        //设计稿是640px
        var initSize = (clientWidth/320) * 100;
        var fontSize = clientWidth > 768 ? 120 : (initSize < 50 ? 50 : initSize);
        docEl.style.fontSize = fontSize + "px";
    }
    doc.addEventListener("DOMContendLoaded",recalc,false);
    //转屏
    win.addEventListener(resizeEvt,function(){
        clearTimeout(Timer);
        Timer = setTimeout(recalc,0)
    },false);
    //pageshow,缓存相关
    win.addEventListener("pageshow",function(e){
        if(e.persisted){
            clearTimeout(Timer);
            Timer = setTimeout(recalc,0)
        }
    },false);
    // 初始化
    recalc();
})(document,window);

//解决Android 手机下, input 或 textarea 元素聚焦时, 输入法遮盖输入框bug
function androidInputBugFix() {
    if (/Android/gi.test(navigator.userAgent)) {
        window.addEventListener('resize', function () {
            if (document.activeElement.tagName == 'INPUT' || document.activeElement.tagName == 'TEXTAREA') {
                window.setTimeout(function () {
                    document.activeElement.scrollIntoViewIfNeeded();
                }, 0);
            }
        })
    }
};
androidInputBugFix();
getResult();
function getResult() {
    $.ajax({
        url: ctx+"/game/getResults",
        type: "get",
        success: function (res) {
            if(!res){
                alert("服务器正在拼命统计结果，稍后重试.");
            } else {
                var html = [];
                var i = 0;
                $.each(res, function (k, user) {
                    var badge = '';
                    if(i == 0){
                        badge = '<img class="badge" src="../img/badge-1.jpg" alt=""/>';
                    } else if(i == 1){
                        badge = '<img class="badge" src="../img/badge-2.jpg" alt=""/>';
                    } else if(i == 2){
                        badge = '<img class="badge" src="../img/badge-3.jpg" alt=""/>';
                    }
                    html.push('<div class="list flex">'
                        +'<img class="pic" src="'+user.avatarUrl+'" alt="man"/>'
                        +'<div class="name">'+user.nickName+'</div>'
                        +'<div class="num">'+user.num+'</div>'
                        +badge
                        +'</div>');
                    i++;
                });
                $(".container").html(html.join(""));
            }

        }    
    });
}