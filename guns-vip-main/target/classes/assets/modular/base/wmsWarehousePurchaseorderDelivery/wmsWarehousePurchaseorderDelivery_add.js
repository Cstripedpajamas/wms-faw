/**
 * 添加或者修改页面
 */
var WmsWarehousePurchaseorderDeliveryInfoDlg = {
    data: {
        purdocno: "",
        itemno: "",
        mtlno: "",
        qty: "",
        code: "",
        sendtime: "",
        expectedreceivetime: "",
        linecode: "",
        status: "",
        CreateTime: ""
    }
};

layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/wmsWarehousePurchaseorderDelivery/addItem", function (data) {
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


layui.use('laydate',function (){
    var laydate=layui.laydate;
    laydate.render({
        elem:"#sendtime"
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
    document.getElementById('sendtime').value = formattedDate;
});

layui.use('laydate',function (){
    var laydate=layui.laydate;
    laydate.render({
        elem:"#expectedreceivetime"
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
    document.getElementById('expectedreceivetime').value = formattedDate;
});