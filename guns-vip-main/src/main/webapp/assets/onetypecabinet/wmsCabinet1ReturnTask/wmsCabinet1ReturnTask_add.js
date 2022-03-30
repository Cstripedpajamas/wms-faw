/**
 * 添加或者修改页面
 */
var WmsCabinet1ReturnTaskInfoDlg = {
    data: {
        taskNumber: "",
        returnRequestId: "",
        processNumber: "",
        materialTypeId: "",
        materialName: "",
        materialSku: "",
        materialId: "",
        materialSerialNumber: "",
        stockId: "",
        locaNumber: "",
        operator: "",
        operationTime: "",
        taskState: "",
        createTime: "",
        updateTime: ""
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/wmsCabinet1ReturnTask/addItem", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();

        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

});