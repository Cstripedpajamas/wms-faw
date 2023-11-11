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
                    $('#dibatch').val(data.data["diBatchNo"]);
                    $('#materialname').val(data.data["materialName"]);
                    $('#materialId').val(data.data["id"]);
                    $('#totalMNumber').val(data.data["totalMNumber"]);
                    $('#useTotalMNumber').val(data.data["useTotalMNumber"]);
                    if (data.data["materialType"]==="GJ"){
                        document.getElementById('mainAssetNoDiv').style.display='none';
                        document.getElementById('mainAssetNo').removeAttribute('lay-verify');
                    }else{
                        document.getElementById('mainAssetNoDiv').style.display='block';
                        document.getElementById('mainAssetNo').setAttribute('lay-verify','required');
                    }
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
    form.render('radio');
    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        // dd.ready(function() {
        //     dd.runtime.permission.requestAuthCode({
        //         corpId: "dinga8bdd73a3a19347eee0f45d8e4f7c288", // 企业id
        //         onSuccess: function (info) {
        //             data.applyCode = info.code // 通过该免登授权码可以获取用户身份
        //         }});
        // });

        const totalMNumber = parseInt($('#totalMNumber').val());
        const useTotalMNumber = parseInt($('#useTotalMNumber').val());
        const mNumber = parseInt($('#mNumber').val());
        if (totalMNumber<useTotalMNumber+mNumber){
            Feng.error("库存不足")
            return false;
        }
        console.log(data);
        var ajax = new $ax(Feng.ctxPath + "/apply/addToolNumber", function (data) {
            if (data.code == 200){
                Feng.success("提交成功！");
            } else {
                Feng.error(data.message)
            }
            // ResponseData.success().data;
            location.reload();
        }, function (data) {
            Feng.error("提交失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
        return false;
    });
});


layui.use('laydate',function (){
    var laydate=layui.laydate;
    laydate.render({
        elem:"#postDate"
    });
    // 获取当前日期
    var currentDate = new Date();

    // 获取年、月、日
    var year = currentDate.getFullYear();
    var month = currentDate.getMonth() + 1; // 注意月份是从 0 开始的，所以要加 1
    var day = currentDate.getDate();

    // 格式化日期字符串（如果需要的话）
    var formattedDate = year + '-' + month + '-' + day;

    // 将日期字符串设置为输入框的值
    document.getElementById('postDate').value = formattedDate;
});