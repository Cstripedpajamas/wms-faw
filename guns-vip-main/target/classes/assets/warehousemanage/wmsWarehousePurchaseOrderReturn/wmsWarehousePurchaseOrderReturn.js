layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 立库-采购订单退还信息表管理
     */
    var WmsWarehousePurchaseOrderReturn = {
        tableId: "wmsWarehousePurchaseOrderReturnTable"
    };

    /**
     * 初始化表格的列
     */
    WmsWarehousePurchaseOrderReturn.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'purchaseId', sort: true, title: '采购订单信息'},
            {field: 'mNumber', sort: true, title: '数量'},
            {field: 'operator', sort: true, title: '操作人'},
            {field: 'operationTime', sort: true, title: '操作时间',minWidth: 160},
            {field: 'taskState', sort: true, title: '任务状态', templet: '#taskStateTpl'},
            {field: 'createTime', sort: true, title: '创建时间',minWidth: 160},
            {field: 'updateTime', sort: true, title: '更新时间',minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsWarehousePurchaseOrderReturn.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(WmsWarehousePurchaseOrderReturn.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsWarehousePurchaseOrderReturn.openAddDlg = function () {
        func.open({
            title: '添加立库-采购订单退还信息表',
            content: Feng.ctxPath + '/wmsWarehousePurchaseOrderReturn/add',
            tableId: WmsWarehousePurchaseOrderReturn.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsWarehousePurchaseOrderReturn.openEditDlg = function (data) {
        func.open({
            title: '修改立库-采购订单退还信息表',
            content: Feng.ctxPath + '/wmsWarehousePurchaseOrderReturn/edit?id=' + data.id,
            tableId: WmsWarehousePurchaseOrderReturn.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsWarehousePurchaseOrderReturn.exportExcel = function () {
        var checkRows = table.checkStatus(WmsWarehousePurchaseOrderReturn.tableId);
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
    WmsWarehousePurchaseOrderReturn.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehousePurchaseOrderReturn/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsWarehousePurchaseOrderReturn.tableId);
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
        elem: '#' + WmsWarehousePurchaseOrderReturn.tableId,
        url: Feng.ctxPath + '/wmsWarehousePurchaseOrderReturn/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehousePurchaseOrderReturn.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsWarehousePurchaseOrderReturn.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsWarehousePurchaseOrderReturn.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsWarehousePurchaseOrderReturn.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsWarehousePurchaseOrderReturn.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsWarehousePurchaseOrderReturn.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsWarehousePurchaseOrderReturn.onDeleteItem(data);
        }
    });
});
