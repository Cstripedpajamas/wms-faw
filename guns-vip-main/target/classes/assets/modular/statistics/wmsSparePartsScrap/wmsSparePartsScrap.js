layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 备品备件报废信息汇总表管理
     */
    var WmsSparePartsScrap = {
        tableId: "wmsSparePartsScrapTable"
    };

    /**
     * 初始化表格的列
     */
    WmsSparePartsScrap.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'materialTypeId',hide: true, sort: true, title: '物料类型ID'},
            {field: 'materialName', sort: true, title: '物料名称'},
            {field: 'materialSku', sort: true, title: '物料SKU'},
            {field: 'scrapNumber', sort: true, title: '报废总数'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsSparePartsScrap.search = function () {
        var queryData = {};
        queryData['materialName'] = $("#materialName").val();
        table.reload(WmsSparePartsScrap.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsSparePartsScrap.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/wmsSparePartsScrap/add';
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    WmsSparePartsScrap.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/wmsSparePartsScrap/edit?id=' + data.id;
    };

    /**
     * 导出excel按钮
     */
    WmsSparePartsScrap.exportExcel = function () {
        var checkRows = table.checkStatus(WmsSparePartsScrap.tableId);
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
    WmsSparePartsScrap.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsSparePartsScrap/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsSparePartsScrap.tableId);
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
        elem: '#' + WmsSparePartsScrap.tableId,
        url: Feng.ctxPath + '/wmsSparePartsScrap/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsSparePartsScrap.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsSparePartsScrap.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsSparePartsScrap.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsSparePartsScrap.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsSparePartsScrap.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsSparePartsScrap.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsSparePartsScrap.onDeleteItem(data);
        }
    });
});
