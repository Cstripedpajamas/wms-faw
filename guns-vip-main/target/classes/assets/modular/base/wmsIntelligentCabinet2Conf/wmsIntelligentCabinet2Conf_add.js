layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/wmsIntelligentCabinet2Conf/addItem", function (data) {
            if (data.code == 200) {
                Feng.success("添加成功！");

                //传给上个页面，刷新table用
                admin.putTempData('formOk', true);

                //关掉对话框
                admin.closeThisDialog();
                window.location.href = Feng.ctxPath + '/wmsIntelligentCabinet2Conf';
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

    $("#reset").click(function () {
        window.location.href = Feng.ctxPath + '/wmsIntelligentCabinet2Conf';
    });

    $("#materialType").focus(function () {
        layer.open({
            type: 2,
            title: '选择物料类型',
            area: ['800px', '500px'],
            content: Feng.ctxPath + '/wmsMaterialType/select?type=2',
            end: function () {
                var info = $("#parentIframe").val();
                if (info != "") {
                    var tempList = eval(info);
                    $("#materialTypeId").val(tempList[0].id);
                    $("#materialType").val(tempList[0].materialType);
                    $("#materialName").val(tempList[0].materialName);
                    $("#materialSku").val(tempList[0].materialSku);
                    $("#mUnit").val(tempList[0].mUnit);
                    $("#parentIframe").val("");
                }
            }
        });
    });

});