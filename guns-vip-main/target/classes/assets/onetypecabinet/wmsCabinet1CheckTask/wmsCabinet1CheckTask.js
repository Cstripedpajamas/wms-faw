layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅰ类柜盘点任务信息表管理
     */
    var WmsCabinet1CheckTask = {
        tableId: "wmsCabinet1CheckTaskTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet1CheckTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            // {field: 'id', hide: true, title: '记录ID'},
            {field: 'taskNumber', sort: true, title: '任务编号'},
            // {field: 'taskRmk', sort: true, title: '备用字段'},
            {field: 'userName', sort: true, title: '操作人'},
            {field: 'operationTime', sort: true, title: '操作时间',minWidth: 160},
            {field: 'taskState', sort: true, title: '任务状态',templet: '#taskStateTpl'},
            {field: 'createTime', sort: true, title: '创建时间',minWidth: 160},
            {field: 'updateTime', sort: true, title: '更新时间',minWidth: 160},
            // {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet1CheckTask.search = function () {
        var queryData = {};
        queryData['taskNumber'] = $("#taskNumber").val();
        queryData['userName'] = $("#userName").val();
        queryData['taskState'] = $("#taskState1").val();
        table.reload(WmsCabinet1CheckTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet1CheckTask.openAddDlg = function () {
        func.open({
            title: '添加Ⅰ类柜盘点任务信息表',
            content: Feng.ctxPath + '/wmsCabinet1CheckTask/add',
            tableId: WmsCabinet1CheckTask.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet1CheckTask.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅰ类柜盘点任务信息表',
            content: Feng.ctxPath + '/wmsCabinet1CheckTask/edit?id=' + data.id,
            tableId: WmsCabinet1CheckTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet1CheckTask.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet1CheckTask.tableId);
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
    WmsCabinet1CheckTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCabinet1CheckTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet1CheckTask.tableId);
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
        elem: '#' + WmsCabinet1CheckTask.tableId,
        url: Feng.ctxPath + '/wmsCabinet1CheckTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet1CheckTask.initColumn()
    });
    table.on('checkbox(wmsCabinet1CheckTaskTable)',function (obj) {
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
        WmsCabinet1CheckTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet1CheckTask.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet1CheckTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet1CheckTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet1CheckTask.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet1CheckTask.onDeleteItem(data);
        }
    });
});
