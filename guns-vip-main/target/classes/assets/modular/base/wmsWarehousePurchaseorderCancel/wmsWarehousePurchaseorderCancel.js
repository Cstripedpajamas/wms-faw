layui.use(['table', 'admin', 'ax', 'func', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * 订单取消管理
     */
    var WmsWarehousePurchaseorderCancel = {
        tableId: "wmsWarehousePurchaseorderCancelTable"
    };

    /**
     * 初始化表格的列
     */
    WmsWarehousePurchaseorderCancel.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'purdocno', sort: true, title: '采购订单号'},
            {field: 'itemno', sort: true, title: '订单行项号'},
            {field: 'status', sort: true, title: '单状态（I:新增 U:修改 D:删除）'},
            {field: 'createTime', title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsWarehousePurchaseorderCancel.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(WmsWarehousePurchaseorderCancel.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsWarehousePurchaseorderCancel.openAddDlg = function () {
        func.open({
            title: '添加订单取消',
            content: Feng.ctxPath + '/wmsWarehousePurchaseorderCancel/add',
            tableId: WmsWarehousePurchaseorderCancel.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsWarehousePurchaseorderCancel.openEditDlg = function (data) {
        func.open({
            title: '修改订单取消',
            content: Feng.ctxPath + '/wmsWarehousePurchaseorderCancel/edit?id=' + data.id,
            tableId: WmsWarehousePurchaseorderCancel.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsWarehousePurchaseorderCancel.exportExcel = function () {
        var checkRows = table.checkStatus(WmsWarehousePurchaseorderCancel.tableId);
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
    WmsWarehousePurchaseorderCancel.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehousePurchaseorderCancel/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsWarehousePurchaseorderCancel.tableId);
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
        elem: '#' + WmsWarehousePurchaseorderCancel.tableId,
        url: Feng.ctxPath + '/wmsWarehousePurchaseorderCancel/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehousePurchaseorderCancel.initColumn()
    });

    table.on('checkbox(wmsWarehousePurchaseorderCancelTable)',function (obj) {
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
        WmsWarehousePurchaseorderCancel.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsWarehousePurchaseorderCancel.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsWarehousePurchaseorderCancel.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsWarehousePurchaseorderCancel.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsWarehousePurchaseorderCancel.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsWarehousePurchaseorderCancel.onDeleteItem(data);
        }
    });
});
