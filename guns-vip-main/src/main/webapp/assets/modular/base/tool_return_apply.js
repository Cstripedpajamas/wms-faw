layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //获取物料类型，显示
    var materialTypesList;
    $.ajax({
        url: Feng.ctxPath + "/apply/listSelectForMaterialType",
        dataType: 'json',
        success: function (data) {
            materialTypesList=data;
            $.each(data, function (index, item) {
                var textMty=item.split(",")[0];
                $('#materialName').append(new Option(textMty,index));
            });
            form.render('select');
        }
    });

    //获取申请人
    $.ajax({
        url: Feng.ctxPath + "/apply/user",
        dataType: 'json',
        success: function (data) {
            $('#operator').val(data.data);
        }
    });

    form.on('select(materialName)', function (data) {
        $.each(materialTypesList, function (index, item) {
            if (data.value == index) {
                var textArr=item.split(",");
                $('#materialType').val(textArr[1]);
                $('#materialSku').val(textArr[2]);
                $('#materialId').val(index);
            }
        });
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