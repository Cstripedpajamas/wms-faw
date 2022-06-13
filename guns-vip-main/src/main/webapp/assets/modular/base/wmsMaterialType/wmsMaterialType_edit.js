layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/wmsMaterialType/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('wmsMaterialTypeForm', result.data);
    var typeFlag = true;
    var flag = true;
    if (result.data.type==1){
        //工具
        $("#latticeMouthTypeDiv").show();
        $("#labelDIV").show();
        typeFlag = true;
    }else {
        //备品备件
        $("#latticeMouthTypeDiv").hide();
        $("#labelDIV").hide();
        $("#latticeMouthTypeDiv").hide();
        $("#turnoverLatticeDiv").hide();
        typeFlag = false;
    }

    if (result.data.sortType == "0"){
        $("#packageTypeDiv").hide();
        $("#packageNumberDiv").hide();
        flag = false;
    }
    else {
        $("#packageTypeDiv").show();
        $("#packageNumberDiv").show();
        form.render('select')
    }

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
            if (!(/(^[1-9]\d*$)/.test(number))){
                Feng.error("请填写正确的数量")
                return false;
            }
            if (+number < 0){
                Feng.error("请填写大于0的数量");
                return false;
            }
        }
        data.field["dataState"] = "0";
        var ajax = new $ax(Feng.ctxPath + "/wmsMaterialType/editItem", function (data) {
            if (data.code == 200) {
                Feng.success("更新成功！");

                //传给上个页面，刷新table用
                admin.putTempData('formOk', true);

                //关掉对话框
                admin.closeThisDialog();
            } else {
                Feng.error(data.message)
            }
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
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
            $("#latticeMouthTypeDiv").val("");
            $("#latticeMouthType").val("");
            typeFlag = false;
            $("#labelDIV").hide();
            $("#labelDIV").val("0");
            form.render('select')
        }
    });

    //radio改变事件
    form.on('radio(typeFilter2)', function (data) {
        var choice = data.value;//被点击的radio的value值
        if (choice == "0") {
            $("#packageNumber").val("0");
            $("#packageType").val("");
            $("#packageTypeDiv").hide();
            $("#packageNumberDiv").hide();
            flag = false;

        } else {
            $("#packageTypeDiv").show();
            $("#packageNumberDiv").show();
            $("#turnoverLatticeType").val("0");
            flag =true;
            form.render('select')
        }
    });

});