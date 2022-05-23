
layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/wmsToolUse/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('wmsToolUseForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/wmsToolUse/editItem", function (data) {
            Feng.success("更新成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
            window.location.href = Feng.ctxPath + '/wmsToolUse';
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });
    $("#reset").click(function () {
        window.location.href = Feng.ctxPath + '/wmsToolUse';
    });

    $("#materialName").focus(function () {
        layer.open({
            type: 2,
            title: '选择工具',
            area: ['800px', '500px'],
            content: Feng.ctxPath + '/wmsMaterialTool/select',
            end: function () {
                var info = $("#parentIframe").val();
                if (info != "") {
                    var tempList = eval(info);
                    $("#materialId").val(tempList[0].id);
                    $("#materialSerialNumber").val(tempList[0].materialSerialNumber);
                    $("#materialName").val(tempList[0].materialName);
                    $("#materialSku").val(tempList[0].materialSku);
                    $("#materialTypeId").val(tempList[0].materialTypeId);
                    $("#parentIframe").val("");
                }
            }
        });
    });
});