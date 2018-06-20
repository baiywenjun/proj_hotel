$(document).ready(function () {
    var islogin=true;
    //var islogin=undefined;
    if(islogin){
        $('#admin,#user').removeClass("hide");
        $('#login').addClass("hide");
    }else{
        $('#admin,#user').addClass("hide");
        $('#login').removeClass("hide");

    }


    layui.use('layer', function () {
        var layer = layui.layer;
        $('.tologin').click(function () {
            //showModal('用户登录','components/shoplogin.html','460','296');
            var login = layer.open({
                type: 2,
                content: ['components/shoplogin.html', 'no'],
                area: ['460px', '306px'],
                title: "用户登录",
                btn: ['登录', '取消']
                , yes: function (index, layero) {
                    layer.msg("你已经登录了!")
                }
                , btn2: function (index, layero) {
                    layer.msg("你取消了操作");

                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        });



        $('#register').click(function () {
           var i=parent.layer.getFrameIndex(window.name);
           parent.layer.close(i);
           
           parent.layer.open({
                type: 2,
                content: ['components/shopregister.html', 'no'],
                area: ['460px', '296px'],
                title: "用户注册",
                btn: ['注册', '重置']
                , yes: function (index, layero) {
                    alert("你已经注册了");
                }
                , btn2: function (index, layero) {
                   

                    return false 
                }
            });
        });

    });


});







