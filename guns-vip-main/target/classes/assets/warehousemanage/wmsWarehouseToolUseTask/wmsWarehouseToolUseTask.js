layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 立库-工具领用任务信息表管理
     */
    var WmsWarehouseToolUseTask = {
        tableId: "wmsWarehouseToolUseTaskTable"
    };

    /**
     * 初始化表格的列
     */
    WmsWarehouseToolUseTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'taskNumber', sort: true, title: '任务编号',minWidth: 160},
            // {field: 'useRequestId', sort: true, title: '领用申请ID'},
            {field: 'processNumber', sort: true, title: '流程单号',minWidth: 160},
            // {field: 'materialTypeId', sort: true, title: '物料类型ID'},
            {field: 'materialName', sort: true, title: '物料名称',minWidth: 120},
            {field: 'materialSku', sort: true, title: '物料SKU',minWidth: 120},
            // {field: 'materialId', sort: true, title: '物料信息ID'},
            {field: 'materialSerialNumber', sort: true, title: '物料编码',minWidth: 120},
            // {field: 'stockId', sort: true, title: '库存信息ID'},
            {field: 'locaNumber', sort: true, title: '库位编号',minWidth: 120},
            // {field: 'turnoverId', sort: true, title: '周转箱信息ID'},
            {field: 'latticeCode', sort: true, title: '格口编号',minWidth: 120},
            {field: 'operator', sort: true, title: '操作人'},
            {field: 'operationTime', sort: true, title: '操作时间',minWidth: 160},
            {field: 'taskState', sort: true, title: '任务状态',templet: '#taskStateTpl',minWidth: 120},
            {field: 'interfaceState', sort: true, title: '接口状态',templet: '#interfaceStateTpl',minWidth: 120},
            {field: 'createTime', sort: true, title: '创建时间',minWidth: 160},
            {field: 'updateTime', sort: true, title: '更新时间',minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth: 160}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsWarehouseToolUseTask.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(WmsWarehouseToolUseTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsWarehouseToolUseTask.openAddDlg = function () {
        func.open({
            title: '添加立库-工具领用任务信息表',
            content: Feng.ctxPath + '/wmsWarehouseToolUseTask/add',
            tableId: WmsWarehouseToolUseTask.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsWarehouseToolUseTask.openEditDlg = function (data) {
        func.open({
            title: '修改立库-工具领用任务信息表',
            content: Feng.ctxPath + '/wmsWarehouseToolUseTask/edit?id=' + data.id,
            tableId: WmsWarehouseToolUseTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsWarehouseToolUseTask.exportExcel = function () {
        var checkRows = table.checkStatus(WmsWarehouseToolUseTask.tableId);
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
    WmsWarehouseToolUseTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseToolUseTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsWarehouseToolUseTask.tableId);
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
        elem: '#' + WmsWarehouseToolUseTask.tableId,
        url: Feng.ctxPath + '/wmsWarehouseToolUseTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehouseToolUseTask.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsWarehouseToolUseTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsWarehouseToolUseTask.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsWarehouseToolUseTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsWarehouseToolUseTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsWarehouseToolUseTask.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsWarehouseToolUseTask.onDeleteItem(data);
        }
    });
});
