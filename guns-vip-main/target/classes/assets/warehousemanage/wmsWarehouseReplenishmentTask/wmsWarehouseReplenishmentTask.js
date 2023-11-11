layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 立库-备品备件补货任务信息表管理
     */
    var WmsWarehouseReplenishmentTask = {
        tableId: "wmsWarehouseReplenishmentTaskTable"
    };

    /**
     * 初始化表格的列
     */
    WmsWarehouseReplenishmentTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'taskNumber', sort: true, title: '任务编号',minWidth: 160},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '描述',minWidth: 120},
            {field: 'materialSku', sort: true, title: '物料号'},
            {field: 'materialSizes', sort: true, title: '规格型号'},
            {field: 'mBatch', sort: true, title: '批次'},
            {field: 'mUnit', sort: true, title: '基本计量单位'},
            {field: 'mNumber', sort: true, title: '数量'},
            // {field: 'stockId', sort: true, title: '库存信息ID'},
            // {field: 'locaNumber', sort: true, title: '库位编号',minWidth: 160},
            // {field: 'turnoverId', sort: true, title: '周转箱信息ID'},
            // {field: 'latticeCode', sort: true, title: '格口编号',minWidth: 120},
            {field: 'operator', sort: true, title: '操作人'},
            // {field: 'operationTime', sort: true, title: '操作时间',minWidth: 160},
            {field: 'taskState', sort: true, title: '任务状态',templet: '#taskStateTpl'},
            {field: 'createTime', sort: true, title: '创建时间',minWidth: 160},
            {field: 'updateTime', sort: true, title: '更新时间',minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth: 160}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsWarehouseReplenishmentTask.search = function () {
        var queryData = {};
        queryData['materialName'] = $("#condition").val();
        queryData['taskState'] = $("#taskState1").val();
        queryData['taskNumber'] = $("#taskNumber").val();
        queryData['materialSku'] = $("#materialSku").val();
        queryData['materialType'] = $("#materialType").val();
        queryData['materialSizes'] = $("#materialSizes").val();
        queryData['operator'] = $("#operator").val();
        table.reload(WmsWarehouseReplenishmentTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsWarehouseReplenishmentTask.openAddDlg = function () {
        func.open({
            title: '添加立库-备品备件补货任务信息表',
            content: Feng.ctxPath + '/wmsWarehouseReplenishmentTask/add',
            tableId: WmsWarehouseReplenishmentTask.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsWarehouseReplenishmentTask.openEditDlg = function (data) {
        func.open({
            title: '修改立库-备品备件补货任务信息表',
            content: Feng.ctxPath + '/wmsWarehouseReplenishmentTask/edit?id=' + data.id,
            tableId: WmsWarehouseReplenishmentTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsWarehouseReplenishmentTask.exportExcel = function () {
        var checkRows = table.checkStatus(WmsWarehouseReplenishmentTask.tableId);
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
    WmsWarehouseReplenishmentTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseReplenishmentTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsWarehouseReplenishmentTask.tableId);
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
        elem: '#' + WmsWarehouseReplenishmentTask.tableId,
        url: Feng.ctxPath + '/wmsWarehouseReplenishmentTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehouseReplenishmentTask.initColumn()
    });

    table.on('checkbox(wmsWarehouseReplenishmentTaskTable)',function (obj) {
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
        WmsWarehouseReplenishmentTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsWarehouseReplenishmentTask.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsWarehouseReplenishmentTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsWarehouseReplenishmentTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsWarehouseReplenishmentTask.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsWarehouseReplenishmentTask.onDeleteItem(data);
        }
    });
});
