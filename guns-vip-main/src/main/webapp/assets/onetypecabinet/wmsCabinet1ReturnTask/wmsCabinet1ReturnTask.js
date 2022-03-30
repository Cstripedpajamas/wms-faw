layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅰ类柜归还任务信息表管理
     */
    var WmsCabinet1ReturnTask = {
        tableId: "wmsCabinet1ReturnTaskTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet1ReturnTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'taskNumber', sort: true, title: '任务编号', minWidth: 160},
            // {field: 'returnRequestId', sort: true, title: '归还申请ID'},
            {field: 'processNumber', sort: true, title: '流程单号'},
            // {field: 'materialTypeId', sort: true, title: '物料类型ID'},
            {field: 'materialName', sort: true, title: '物料名称'},
            {field: 'materialSku', sort: true, title: '物料SKU'},
            // {field: 'materialId', sort: true, title: '物料信息ID'},
            {field: 'materialSerialNumber', sort: true, title: '物料编码'},
            // {field: 'stockId', sort: true, title: '库存信息ID'},
            {field: 'locaNumber', sort: true, title: '库位编号'},
            {field: 'operator', sort: true, title: '操作人'},
            {field: 'operationTime', sort: true, title: '操作时间', minWidth: 160},
            {field: 'taskState', sort: true, title: '任务状态',templet: '#taskStateTpl'},
            {field: 'createTime', sort: true, title: '创建时间', minWidth: 160},
            {field: 'updateTime', sort: true, title: '更新时间', minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 160}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet1ReturnTask.search = function () {
        var queryData = {};
        queryData['taskNumber'] = $("#condition").val();
        table.reload(WmsCabinet1ReturnTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet1ReturnTask.openAddDlg = function () {
        func.open({
            title: '添加Ⅰ类柜归还任务信息表',
            content: Feng.ctxPath + '/wmsCabinet1ReturnTask/add',
            tableId: WmsCabinet1ReturnTask.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet1ReturnTask.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅰ类柜归还任务信息表',
            content: Feng.ctxPath + '/wmsCabinet1ReturnTask/edit?id=' + data.id,
            tableId: WmsCabinet1ReturnTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet1ReturnTask.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet1ReturnTask.tableId);
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
    WmsCabinet1ReturnTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCabinet1ReturnTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet1ReturnTask.tableId);
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
        elem: '#' + WmsCabinet1ReturnTask.tableId,
        url: Feng.ctxPath + '/wmsCabinet1ReturnTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet1ReturnTask.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsCabinet1ReturnTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet1ReturnTask.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet1ReturnTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet1ReturnTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet1ReturnTask.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet1ReturnTask.onDeleteItem(data);
        }
    });
});
