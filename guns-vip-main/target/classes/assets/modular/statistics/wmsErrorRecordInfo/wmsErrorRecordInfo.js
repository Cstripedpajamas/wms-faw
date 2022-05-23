layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 异常信息记录表管理
     */
    var WmsErrorRecordInfo = {
        tableId: "wmsErrorRecordInfoTable"
    };

    /**
     * 初始化表格的列
     */
    WmsErrorRecordInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'errorType', sort: true, title: '异常类型'},
            {field: 'errorDescribe', sort: true, title: '异常描述'},
            {field: 'operator', sort: true, title: '操作人'},
            {field: 'operationTime', sort: true, title: '操作时间'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsErrorRecordInfo.search = function () {
        var queryData = {};
        queryData['errorType'] = $("#errorType").val();
        table.reload(WmsErrorRecordInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsErrorRecordInfo.openAddDlg = function () {
        func.open({
            title: '添加异常信息记录表',
            content: Feng.ctxPath + '/wmsErrorRecordInfo/add',
            tableId: WmsErrorRecordInfo.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsErrorRecordInfo.openEditDlg = function (data) {
        func.open({
            title: '修改异常信息记录表',
            content: Feng.ctxPath + '/wmsErrorRecordInfo/edit?id=' + data.id,
            tableId: WmsErrorRecordInfo.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsErrorRecordInfo.exportExcel = function () {
        var checkRows = table.checkStatus(WmsErrorRecordInfo.tableId);
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
    WmsErrorRecordInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsErrorRecordInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsErrorRecordInfo.tableId);
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
        elem: '#' + WmsErrorRecordInfo.tableId,
        url: Feng.ctxPath + '/wmsErrorRecordInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsErrorRecordInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsErrorRecordInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsErrorRecordInfo.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsErrorRecordInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsErrorRecordInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsErrorRecordInfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsErrorRecordInfo.onDeleteItem(data);
        }
    });
});
