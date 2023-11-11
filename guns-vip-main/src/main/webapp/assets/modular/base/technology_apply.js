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
                success: function (data) {
                    console.log(data);
                    $('#materialtype').val(data.data["materialType"]);
                    $('#sizes').val(data.data["sizes"]);
                    $('#dibatch').val(data.data["diBatchNo"]);
                    $('#materialname').val(data.data["materialName"]);
                    $('#materialId').val(data.data["id"]);
                    $('#totalMNumber').val(data.data["totalMNumber"]);
                    $('#useTotalMNumber').val(data.data["useTotalMNumber"]);
                    // if (data.data["materialType"]==="GJ"){
                    //     document.getElementById('mainAssetNoDiv').style.display='none';
                    //     document.getElementById('mainAssetNo').removeAttribute('lay-verify');
                    // }else{
                    //     document.getElementById('mainAssetNoDiv').style.display='block';
                    //     document.getElementById('mainAssetNo').setAttribute('lay-verify','required');
                    // }
                }
            });
        });

    // $("#approvedById").click(function(){
    //     var form = layui.form;
    //     $.ajax({
    //         url: Feng.ctxPath + "/apply/listSelectApprovedBy",
    //         dataType: 'json',
    //         type: 'get',
    //         success: function (data) {
    //             console.log(data.data);//下面会提到这个data是什么值
    //             //使用循环遍历，给下拉列表赋值
    //             $.each(data.data, function (index, value) {
    //                 $('#approvedBy').append(new Option(value.approvedBy,value.id));
    //             });
    //             form.render("select","approvedBy");//重新渲染 固定写法
    //         }
    //     })
    // });

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

    $('#operator').blur(function () {
        var operator = $('#operator').val();
        $.ajax({
            url: Feng.ctxPath + "/apply/listSelectApprovedBySerialNumber",
            data:{
                "operator":operator
            },
            dataType: 'json',
            success: function (data) {
                console.log(data.data);//下面会提到这个data是什么值
                //使用循环遍历，给下拉列表赋值
                $('#approvedBy').empty();
                $('#approvedBy').append(new Option("请选择",'0'));
                $.each(data.data, function (index, value) {
                    $('#approvedBy').append(new Option(value.approvedBy,value.id));
                    console.log(value.approvedBy,value.id);
                });
                form.render("select");//重新渲染 固定写法
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
    form.on('submit(btnSubmitTechnology)', function (data) {
        // dd.ready(function() {
        //     dd.runtime.permission.requestAuthCode({
        //         corpId: "dinga8bdd73a3a19347eee0f45d8e4f7c288", // 企业id
        //         onSuccess: function (info) {
        //             data.applyCode = info.code // 通过该免登授权码可以获取用户身份
        //         }});
        // });
        
        console.log(data);
        const totalMNumber = parseInt($('#totalMNumber').val());
        const useTotalMNumber = parseInt($('#useTotalMNumber').val());
        if (totalMNumber<useTotalMNumber+1){
            Feng.error("库存不足")
            return false;
        }
        var ajax = new $ax(Feng.ctxPath + "/apply/addTechnology", function (data) {
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