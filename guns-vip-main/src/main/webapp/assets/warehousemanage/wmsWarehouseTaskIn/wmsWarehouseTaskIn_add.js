/**
 * 添加或者修改页面
 */
var WmsWarehouseTaskInInfoDlg = {
    data: {
        messageId: "",
        orderType: "",
        taskMg: "",
        goodsType: "",
        turnoverType: "",
        turnoverNumber: "",
        tBarcode: "",
        aLatticeCode: "",
        aLatticeQr: "",
        aLatticeStatus: "",
        aLatticeMaterialTypeId: "",
        aLatticeMaterialId: "",
        aLatticeMaterialType: "",
        aLatticeMaterialName: "",
        aLatticeMaterialSku: "",
        aLatticeMUnit: "",
        aLatticeMaterialSerialNumber: "",
        aLatticeMBatch: "",
        aLatticeMNumber: "",
        bLatticeCode: "",
        bLatticeQr: "",
        bLatticeStatus: "",
        bLatticeMaterialTypeId: "",
        bLatticeMaterialId: "",
        bLatticeMaterialType: "",
        bLatticeMaterialName: "",
        bLatticeMaterialSku: "",
        bLatticeMUnit: "",
        bLatticeMaterialSerialNumber: "",
        bLatticeMBatch: "",
        bLatticeMNumber: "",
        reqTag: "",
        reqStatus: "",
        resTag: "",
        resStatus: "",
        locaNumber: "",
        taskFeedback: "",
        createTime: "",
        reqTime: "",
        dataTime: ""
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseTaskIn/addItem", function (data) {
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