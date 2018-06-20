var layer,element;
layui.use(['form', 'element'], function () {
    layer = layui.layer;
    element = layui.element;
})

var zxtComm = {
    //触发事件
    tabAdd: function (title, url, id) {
        //新增一个Tab项
        element.tabAdd('xbs_tab', {
            title: title
            , content: '<iframe tab-id="' + id + '" frameborder="0" src="' + url + '" scrolling="yes" class="x-iframe"></iframe>'
            , id: id
        });
    }
    , tabDelete: function (othis) {
        //删除指定Tab项
        element.tabDelete('xbs_tab', '44'); //删除：“商品管理”
        othis.addClass('layui-btn-disabled');
    }
    , tabChange: function (id) {
        //切换到指定Tab项
        element.tabChange('xbs_tab', id); //切换到：用户管理
        //   console.log(id);
    }
}