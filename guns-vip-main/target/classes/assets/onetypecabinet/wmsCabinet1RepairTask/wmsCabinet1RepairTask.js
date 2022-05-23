layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅰ类柜维修任务信息表管理
     */
    var WmsCabinet1RepairTask = {
        tableId: "wmsCabinet1RepairTaskTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet1RepairTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'taskNumber', sort: true, title: '任务编号', minWidth: 160},
            // {field: 'materialTypeId', sort: true, title: '物料类型ID'},
            {field: 'materialName', sort: true, title: '物料名称'},
            {field: 'materialSku', sort: true, title: '物料SKU'},
            // {field: 'materialId', sort: true, title: '物料信息ID'},
            {field: 'materialSerialNumber', sort: true, title: '物料编码', minWidth: 160},
            // {field: 'stockId', sort: true, title: '库存信息ID'},
            {field: 'locaNumber', sort: true, title: '库位编号'},
            {field: 'operator', sort: true, title: '操作人'},
            {field: 'operationTime', sort: true, title: '操作时间', minWidth: 160},
            {field: 'taskState', sort: true, title: '任务状态',templet: '#taskStateTpl'},
            {field: 'createTime', sort: true, title: '创建时间', minWidth: 160},
            {field: 'updateTime', sort: true, title: '更新时间', minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet1RepairTask.search = function () {
        var queryData = {};
        queryData['taskNumber'] = $("#condition").val();
        table.reload(WmsCabinet1RepairTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet1RepairTask.openAddDlg = function () {
        func.open({
            title: '添加Ⅰ类柜维修任务信息表',
            content: Feng.ctxPath + '/wmsCabinet1RepairTask/add',
            tableId: WmsCabinet1RepairTask.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet1RepairTask.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅰ类柜维修任务信息表',
            content: Feng.ctxPath + '/wmsCabinet1RepairTask/edit?id=' + data.id,
            tableId: WmsCabinet1RepairTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet1RepairTask.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet1RepairTask.tableId);
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
    WmsCabinet1RepairTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCabinet1RepairTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet1RepairTask.tableId);
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
        elem: '#' + WmsCabinet1RepairTask.tableId,
        url: Feng.ctxPath + '/wmsCabinet1RepairTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet1RepairTask.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsCabinet1RepairTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet1RepairTask.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet1RepairTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet1RepairTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet1RepairTask.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet1RepairTask.onDeleteItem(data);
        }
    });
});
