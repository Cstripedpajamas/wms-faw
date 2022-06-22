layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 包装信息表管理
     */
    var WmsPackinfo = {
        tableId: "wmsPackinfoTable"
    };

    /**
     * 初始化表格的列
     */
    WmsPackinfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: 'ID'},
            {field: 'packgeName', sort: true, title: '包装名称'},
            {field: 'packgeSpecif', sort: true, title: '包装规格'},
            {field: 'mark', sort: true, title: '备注'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsPackinfo.search = function () {
        var queryData = {};
        queryData['packgeName'] = $("#packgeName").val();
        table.reload(WmsPackinfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsPackinfo.openAddDlg = function () {
        func.open({
            title: '添加包装信息表',
            content: Feng.ctxPath + '/wmsPackinfo/add',
            tableId: WmsPackinfo.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsPackinfo.openEditDlg = function (data) {
        func.open({
            title: '修改包装信息表',
            content: Feng.ctxPath + '/wmsPackinfo/edit?id=' + data.id,
            tableId: WmsPackinfo.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsPackinfo.exportExcel = function () {
        var checkRows = table.checkStatus(WmsPackinfo.tableId);
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
    WmsPackinfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsPackinfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsPackinfo.tableId);
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
        elem: '#' + WmsPackinfo.tableId,
        url: Feng.ctxPath + '/wmsPackinfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsPackinfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsPackinfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsPackinfo.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsPackinfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsPackinfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsPackinfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsPackinfo.onDeleteItem(data);
        }
    });
});
