layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    var typeFlag = true;

    var flag = true;
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        if (typeFlag) {
            var type = $("#latticeMouthType").val();
            if (type.length == 0) {
                Feng.error("格口类型不能为空");
                return false;
            }
        }

        if (flag){
          var len =   $("#packageType").val();
            if (len ==  null || len == ""){
                Feng.error("请选择包装类型")
                return  false;
            }
           var number = $("#packageNumber").val();
            console.log(number)
            if (!(/(^[1-9]\d*$)/.test(number))){
                Feng.error("请填写正确的数量")
                return false;
            }
        }
        var ajax = new $ax(Feng.ctxPath + "/wmsMaterialType/addItem", function (data) {
            if (data.code == 200) {
                Feng.success("添加成功！");

                //传给上个页面，刷新table用
                admin.putTempData('formOk', true);

                //关掉对话框
                admin.closeThisDialog();
            } else {
                Feng.error(data.message)
            }
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //radio改变事件
    form.on('radio(typeFilter)', function (data) {
        var choice = data.value;//被点击的radio的value值
        if (choice == 1) {
            //工具
            $("#latticeMouthTypeDiv").show();
            $("#labelDIV").show();
            typeFlag = true;
        } else {
            //备品备件
            $("#latticeMouthTypeDiv").hide();
            $("#latticeMouthType").val("");
            $("#labelDIV").hide();
            $("#label").val("");
            typeFlag = false;
            form.render('select')
        }
    });

    //radio改变事件
    form.on('radio(typeFilter2)', function (data) {
        var choice = data.value;//被点击的radio的value值
        if (choice == "0") {
            $("#packageNumber").val("0");
            $("#packageTypeDiv").hide();
            $("#packageNumberDiv").hide();
            flag = false;

        } else {
            $("#packageTypeDiv").show();
            $("#packageNumberDiv").show();
            flag= true;
            form.render('select')
        }
    });

});