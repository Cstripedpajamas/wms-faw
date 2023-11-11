layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    $('#materialsku').blur(function () {
        var sku = $('#materialsku').val();
        $.ajax({
            url: Feng.ctxPath + "/apply/listSelectForplant",
            data:{
                "sku":sku
            },
            dataType: 'json',
            // success: function (data) {
            //     $("#plant").empty();
            //     $.each(data, function (index, item) {
            //         $('#plant').append(new Option(item,index));
            //     });
            //     //渲染一下表单
            //     form.render('select');
            // }
            success: function (data) {
                console.log(data);
                $('#materialtype').val(data.data["materialType"]);
                $('#sizes').val(data.data["sizes"]);
                $('#RFID').val(data.data["RFID"]);
                $('#toolStatus').val(data.data["toolStatus"]);
                $('#dibatch').val(data.data["diBatchNo"]);
                $('#materialname').val(data.data["materialName"]);
                $('#materialId').val(data.data["id"]);
            }
        });
    });

    form.on('select(plant)', function (data) {
        var sku = $('#materialsku').val();
        var plant = $('#plant').val();
        $.ajax({
            url: Feng.ctxPath + "/apply/listSelectFormaterialtype",
            data:{
                "sku":sku,
                "plant":plant
            },
            dataType: 'json',
            success: function (data) {
                $("#materialtype").empty();
                $.each(data, function (index, item) {
                    $('#materialtype').append(new Option(item,index));
                });
                //渲染一下表单
                form.render('select');
            }
        });
    });

    layui.use(['form'], function() {
        var form = layui.form;
        $.ajax({
            url: Feng.ctxPath + "/apply/listSelectApprovedBy",
            dataType: 'json',
            type: 'get',
            success: function (data) {
                console.log(data.data);//下面会提到这个data是什么值
                //使用循环遍历，给下拉列表赋值
                $.each(data.data, function (index, value) {
                    $('#approvedBy').append(new Option(value.approvedBy,value.id));
                });
                form.render("select");//重新渲染 固定写法
            }
        })
    });

    form.on('select(materialtype)', function (data) {
        var sku = $('#materialsku').val();
        var plant = $('#plant').val();
        var materialtype = $('#materialtype').val();
        $.ajax({
            url: Feng.ctxPath + "/apply/listSelectForDibatch",
            data:{
                "sku":sku,
                "plant":plant,
                "materialType":materialtype
            },
            dataType: 'json',
            success: function (data) {
                $("#dibatch").empty();
                $.each(data, function (index, item) {
                    $('#dibatch').append(new Option(item,index));
                });
                //渲染一下表单
                form.render('select');
            }
        });
    });

    form.on('select(dibatch)', function (data) {
        var sku = $('#materialsku').val();
        var plant = $('#plant').val();
        var materialtype = $('#materialtype').val();
        var dibatch = $('#dibatch').val();
        $.ajax({
            url: Feng.ctxPath + "/apply/listSelectForName",
            data:{
                "sku":sku,
                "plant":plant,
                "materialType":materialtype,
                "diBatch":dibatch
            },
            dataType: 'json',
            success: function (data) {
                $('#materialname').val(data.data["materialName"]);
                $('#materialId').val(data.data["id"]);
            }
        });
    });

    //获取申请人
    $.ajax({
        url: Feng.ctxPath + "/apply/user",
        dataType: 'json',
        success: function (data) {
            $('#operator').val(data.data);
        }
    });


    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        console.log(data);
        var ajax = new $ax(Feng.ctxPath + "/apply/addToolReturn", function (data) {
            Feng.success("提交成功！");
            location.reload();
        }, function (data) {
            Feng.error("提交失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
        return false;
    });



});