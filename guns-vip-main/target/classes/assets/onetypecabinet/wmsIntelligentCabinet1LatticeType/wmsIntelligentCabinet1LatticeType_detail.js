/**
 * 详情对话框
 */
var WmsIntelligentCabinet1LatticeTypeInfoDlg = {
    data: {
        typeName: "",
        typeStandards: "",
        remarks: "",
        dataState: "",
        createTime: ""
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/wmsIntelligentCabinet1LatticeType/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('wmsIntelligentCabinet1LatticeTypeForm', result.data);

});