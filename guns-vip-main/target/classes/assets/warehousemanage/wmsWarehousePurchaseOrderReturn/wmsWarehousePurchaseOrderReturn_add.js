/**
 * 添加或者修改页面
 */
var WmsWarehousePurchaseOrderReturnInfoDlg = {
    data: {
        purchaseId: "",
        mNumber: "",
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

    var wmsPurchaseOrderInfoAjax = new $ax(Feng.ctxPath + "/wmsPurchaseOrderInfo/contentionList");
    var wmsPurchaseOrderInfoResult = wmsPurchaseOrderInfoAjax.start().data;
    $('#purchaseId').append(new Option("","请选择采购订单"));
    $.each(wmsPurchaseOrderInfoResult,function(index,item){
        $('#purchaseId').append(new Option(item.purNumber,item.purNumber));//往下拉菜单里添加元素
    });
    form.render("select");

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/wmsWarehousePurchaseOrderReturn/addItem", function (data) {
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