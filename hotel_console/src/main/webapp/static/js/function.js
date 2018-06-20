      function showModal(title,url,w,h,id){
          if (title == null || title == '') {
              title=false;
          };
          if (url == null || url == '') {
              url="../404.html";
          };
          if (w == null || w == '') {
              w=($(window).width()*0.9);
          };
          if (h == null || h == '') {
              h=($(window).height() - 50);
          };
          layer.open({
              type: 2,
              area: [w+'px', h +'px'],
              // fix: false, //不固定
              // maxmin: true,
              shadeClose: true,
              shade:0.4,
              title: title,
              content: url+"?id="+id,
          });
          //console.log("W:"+w+"，H:"+h);
      }

      function showOrder(title,url,w,h,id,status){
          if (title == null || title == '') {
              title=false;
          };
          if (url == null || url == '') {
              url="../404.html";
          };
          if (w == null || w == '') {
              w=($(window).width()*0.9);
          };

          if (h == null || h == '') {
              h=($(window).height() - 50);
          };
          var indfrm=layer.open({
              type: 2,
              area: [w+'px', h +'px'],
              // fix: false, //不固定
              // maxmin: true,
              shadeClose: true,
              shade:0.4,
              title: title,
              content: url+"?id="+id+"&status="+status,
              success: function(layero, index){
                var body = layer.getChildFrame('body', index);
                var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                xh=body.find(".wrapper").height();
                // console.log(body.html()) //得到iframe页的body内容
                // body.find('input').val('Hi，我是从父页来的')
                this.area[1]=xh+"px";
                //console.log(this.area);
                // $('#layui-layer1').css('top','');
                //console.log("xh:"+xh+"h:"+h);
                console.log(indfrm);
                if(xh<=h){ 
                  //$('#layui-layer-iframe1').css("height",xh+22);
                  $('#layui-layer'+indfrm).css("height",xh+92);
                  
                }

              }
              
          });
           //var index = parent.layer.getFrameIndex(window.name)
                console.log(indfrm);
          //console.log("W:"+w+"，H:"+h);
      }
      function resizeFrame(){

      }
       function delChecked (argument) {
          layer.confirm('确认要删除选中项吗？',function(index){
              //捉到所有被选中的，发异步进行删除
              layer.msg('删除成功', {icon: 1});
              $(".layui-form-checked").not('.header').parents('tr').remove();
          });
        }
        function scrapChecked (argument) {
          layer.confirm('确认要废除选中的箱体吗？',function(index){
              //捉到所有被选中的，发异步进行删除
              layer.msg('废除成功', {icon: 1});
              $(".layui-form-checked").not('.header').parents('tr').remove();
          });
        }
        function pickChecked (argument) {
          layer.confirm('确认要分配选中的箱体吗？',function(index){
              
              layer.msg('分配成功', {icon: 1});
              $(".layui-form-checked").not('.header').parents('tr').remove();
          });
        }

      function searchReset(){
          $(".search-settings :input").val("");
      };