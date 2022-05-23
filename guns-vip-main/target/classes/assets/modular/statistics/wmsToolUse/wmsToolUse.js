layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 工具领用信息表管理
     */
    var WmsToolUse = {
        tableId: "wmsToolUseTable"
    };

    /**
     * 初始化表格的列
     */
    WmsToolUse.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'operator', sort: true, title: '人员信息'},
            {field: 'materialTypeId', hide: true,sort: true, title: '物料类型ID'},
            {field: 'materialName', sort: true, title: '物料名称'},
            {field: 'materialSku', sort: true, title: '物料SKU'},
            {field: 'materialId', hide: true,sort: true, title: '物料信息ID'},
            {field: 'materialSerialNumber', sort: true, title: '物料编码'},
            {field: 'dataTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsToolUse.search = function () {
        var queryData = {};
        queryData['materialName'] = $("#materialName").val();
        table.reload(WmsToolUse.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsToolUse.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/wmsToolUse/add';
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    WmsToolUse.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/wmsToolUse/edit?id=' + data.id;
    };

    /**
     * 导出excel按钮
     */
    WmsToolUse.exportExcel = function () {
        var checkRows = table.checkStatus(WmsToolUse.tableId);
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
    WmsToolUse.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsToolUse/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsToolUse.tableId);
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
        elem: '#' + WmsToolUse.tableId,
        url: Feng.ctxPath + '/wmsToolUse/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsToolUse.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsToolUse.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsToolUse.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsToolUse.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsToolUse.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsToolUse.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsToolUse.onDeleteItem(data);
        }
    });
});
