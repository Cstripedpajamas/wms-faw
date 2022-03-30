/**
 * 详情对话框
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

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/wmsWarehousePurchaseOrderReturn/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('wmsWarehousePurchaseOrderReturnForm', result.data);


    var wmsPurchaseOrderInfoAjax = new $ax(Feng.ctxPath + "/wmsPurchaseOrderInfo/contentionList");
    var wmsPurchaseOrderInfoResult = wmsPurchaseOrderInfoAjax.start().data;
    $('#purchaseId').append(new Option("","请选择采购订单"));
    $.each(wmsPurchaseOrderInfoResult,function(index,item){

        if(result.data.purchaseId === item.purchaseId){
            $('#purchaseId').append(new Option(item.purNumber,item.purNumber,true,true));
        } else {
            $('#purchaseId').append(new Option(item.purNumber,item.purNumber));
        }
    });
    form.render("select");

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/wmsWarehousePurchaseOrderReturn/editItem", function (data) {
            Feng.success("更新成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();

        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

});