layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 工具信息汇总表管理
     */
    var WmsToolInfo = {
        tableId: "wmsToolInfoTable"
    };

    /**
     * 初始化表格的列
     */
    WmsToolInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'materialTypeId', hide: true, sort: true, title: '物料类型ID'},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '物料名称'},
            {field: 'materialSku', sort: true, title: '物料SKU'},
            {field: 'total', sort: true, title: '总数'},
            {field: 'existNumber', sort: true, title: '现存数量'},
            {field: 'repairNumber', sort: true, title: '维修数量'},
            {field: 'scrapNumber', sort: true, title: '报废数量'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsToolInfo.search = function () {
        var queryData = {};
        queryData['materialName'] = $("#materialName").val();
        table.reload(WmsToolInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsToolInfo.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/wmsToolInfo/add';
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    WmsToolInfo.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/wmsToolInfo/edit?id=' + data.id;
    };

    /**
     * 导出excel按钮
     */
    WmsToolInfo.exportExcel = function () {
        var checkRows = table.checkStatus(WmsToolInfo.tableId);
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
    WmsToolInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsToolInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsToolInfo.tableId);
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
        elem: '#' + WmsToolInfo.tableId,
        url: Feng.ctxPath + '/wmsToolInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsToolInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsToolInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsToolInfo.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsToolInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsToolInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsToolInfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsToolInfo.onDeleteItem(data);
        }
    });
});
