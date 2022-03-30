layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅱ类柜补货任务信息表管理
     */
    var WmsCabinet2ReplenishmentTask = {
        tableId: "wmsCabinet2ReplenishmentTaskTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet2ReplenishmentTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {align: 'center',field: 'taskNumber', sort: true, title: '任务编号'},
            {align: 'center',field: 'materialTypeId', sort: true, title: '物料类型ID'},
            {align: 'center',field: 'materialId', sort: true, title: '物料信息ID'},
            {align: 'center',field: 'mBatch', sort: true, title: '批次'},
            {align: 'center',field: 'mNumber', sort: true, title: '数量'},
            {align: 'center',field: 'stockId', sort: true, title: '库存信息ID'},
            {align: 'center',field: 'locaNumber', sort: true, title: '库位编号'},
            {align: 'center',field: 'turnoverId', sort: true, title: '周转箱ID'},
            {align: 'center',field: 'turnoverNumber', sort: true, title: '周转箱编号'},
            {align: 'center',field: 'operator', sort: true, title: '操作人'},
            {align: 'center',field: 'operationTime', sort: true, title: '操作时间'},
            {align: 'center',field: 'taskState', sort: true, title: '任务状态',templet:'#taskState'},
            {align: 'center',field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center',field: 'updateTime', sort: true, title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet2ReplenishmentTask.search = function () {
        var queryData = {};
        queryData['taskNumber'] = $("#condition").val();
        table.reload(WmsCabinet2ReplenishmentTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet2ReplenishmentTask.openAddDlg = function () {
        func.open({
            title: '添加Ⅱ类柜补货任务信息表',
            content: Feng.ctxPath + '/wmsCabinet2ReplenishmentTask/add',
            tableId: WmsCabinet2ReplenishmentTask.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet2ReplenishmentTask.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅱ类柜补货任务信息表',
            content: Feng.ctxPath + '/wmsCabinet2ReplenishmentTask/edit?id=' + data.id,
            tableId: WmsCabinet2ReplenishmentTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet2ReplenishmentTask.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet2ReplenishmentTask.tableId);
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
    WmsCabinet2ReplenishmentTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCabinet2ReplenishmentTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet2ReplenishmentTask.tableId);
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
        elem: '#' + WmsCabinet2ReplenishmentTask.tableId,
        url: Feng.ctxPath + '/wmsCabinet2ReplenishmentTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet2ReplenishmentTask.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsCabinet2ReplenishmentTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet2ReplenishmentTask.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet2ReplenishmentTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet2ReplenishmentTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet2ReplenishmentTask.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet2ReplenishmentTask.onDeleteItem(data);
        }
    });
});
