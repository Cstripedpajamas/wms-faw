
layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/wmsToolInfo/addItem", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
            window.location.href = Feng.ctxPath + '/wmsToolInfo';

        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $("#reset").click(function () {
        window.location.href = Feng.ctxPath + '/wmsToolInfo';
    });

    $("#materialType").focus(function () {
        layer.open({
            type: 2,
            title: '选择物料类型',
            area: ['800px', '500px'],
            content: Feng.ctxPath + '/wmsMaterialType/select?type=1',
            end: function () {
                var info = $("#parentIframe").val();
                if (info != "") {
                    var tempList = eval(info);
                    $("#materialTypeId").val(tempList[0].id);
                    $("#materialType").val(tempList[0].materialType);
                    $("#materialName").val(tempList[0].materialName);
                    $("#materialSku").val(tempList[0].materialSku);
                    $("#parentIframe").val("");
                }
            }
        });
    });


});