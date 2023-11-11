layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 发货单管理
     */
    var WmsWarehousePurchaseorderDelivery = {
        tableId: "wmsWarehousePurchaseorderDeliveryTable"
    };

    /**
     * 初始化表格的列
     */
    WmsWarehousePurchaseorderDelivery.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'purdocno', sort: true, title: '采购订单号'},
            {field: 'itemno', sort: true, title: '订单行项号'},
            {field: 'mtlno', sort: true, title: '物料号'},
            {field: 'qty', sort: true, title: '发货数量'},
            {field: 'code', sort: true, title: '发货单编号'},
            {field: 'sendtime', sort: true, title: '发货时间'},
            {field: 'expectedreceivetime', sort: true, title: '预计交货时间'},
            {field: 'linecode', sort: true, title: '发货行编号'},
            {field: 'status', sort: true, title: '发货单状态（1正常，0关闭）'},
            {field: 'createTime', title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsWarehousePurchaseorderDelivery.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(WmsWarehousePurchaseorderDelivery.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsWarehousePurchaseorderDelivery.openAddDlg = function () {
        func.open({
            title: '添加发货单',
            content: Feng.ctxPath + '/wmsWarehousePurchaseorderDelivery/add',
            tableId: WmsWarehousePurchaseorderDelivery.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsWarehousePurchaseorderDelivery.openEditDlg = function (data) {
        func.open({
            title: '修改发货单',
            content: Feng.ctxPath + '/wmsWarehousePurchaseorderDelivery/edit?id=' + data.id,
            tableId: WmsWarehousePurchaseorderDelivery.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsWarehousePurchaseorderDelivery.exportExcel = function () {
        var checkRows = table.checkStatus(WmsWarehousePurchaseorderDelivery.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    WmsWarehousePurchaseorderDelivery.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehousePurchaseorderDelivery/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsWarehousePurchaseorderDelivery.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsWarehousePurchaseorderDelivery.tableId,
        url: Feng.ctxPath + '/wmsWarehousePurchaseorderDelivery/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehousePurchaseorderDelivery.initColumn()
    });

    table.on('checkbox(wmsWarehousePurchaseorderDeliveryTable)',function (obj) {
        var isChecked=obj.checked;
        var tr=obj.tr;
        if (isChecked){
            tr.addClass('layui-table-click');
        }else{
            tr.removeClass('layui-table-click');
        }

    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsWarehousePurchaseorderDelivery.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsWarehousePurchaseorderDelivery.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsWarehousePurchaseorderDelivery.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsWarehousePurchaseorderDelivery.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsWarehousePurchaseorderDelivery.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsWarehousePurchaseorderDelivery.onDeleteItem(data);
        }
    });
});
