layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 周转箱绑定货物信息关联表管理
     */
    var WmsCabinet2TurnoverBind = {
        tableId: "wmsCabinet2TurnoverBindTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet2TurnoverBind.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'turnoverId', sort: true, title: '周转箱信息ID'},
            {field: 'materialTypeId', sort: true, title: '物料类型ID'},
            {field: 'materialId', sort: true, title: '物料信息ID'},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '物料名称'},
            {field: 'materialSku', sort: true, title: '物料SKU'},
            {field: 'mBatch', sort: true, title: '批次'},
            {field: 'mUnit', sort: true, title: '单位'},
            {field: 'mNumber', sort: true, title: '数量'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet2TurnoverBind.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(WmsCabinet2TurnoverBind.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet2TurnoverBind.openAddDlg = function () {
        func.open({
            title: '添加周转箱绑定货物信息关联表',
            content: Feng.ctxPath + '/wmsCabinet2TurnoverBind/add',
            tableId: WmsCabinet2TurnoverBind.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet2TurnoverBind.openEditDlg = function (data) {
        func.open({
            title: '修改周转箱绑定货物信息关联表',
            content: Feng.ctxPath + '/wmsCabinet2TurnoverBind/edit?id=' + data.id,
            tableId: WmsCabinet2TurnoverBind.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet2TurnoverBind.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet2TurnoverBind.tableId);
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
    WmsCabinet2TurnoverBind.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCabinet2TurnoverBind/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet2TurnoverBind.tableId);
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
        elem: '#' + WmsCabinet2TurnoverBind.tableId,
        url: Feng.ctxPath + '/wmsCabinet2TurnoverBind/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet2TurnoverBind.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsCabinet2TurnoverBind.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet2TurnoverBind.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet2TurnoverBind.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet2TurnoverBind.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet2TurnoverBind.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet2TurnoverBind.onDeleteItem(data);
        }
    });
});
