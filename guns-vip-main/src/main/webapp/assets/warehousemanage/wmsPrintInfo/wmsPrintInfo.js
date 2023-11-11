layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 条码打印信息表管理
     */
    var WmsPrintInfo = {
        tableId: "wmsPrintInfoTable"
    };

    /**
     * 初始化表格的列
     */
    WmsPrintInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'materialSerialNumber', sort: true, title: '工具编码'},
            {field: 'operator', sort: true, title: '操作人'},
            {field: 'printType', sort: true, title: '打印类型（A补打 B采购）'},
            {field: 'printStu', sort: true, title: '打印状态（0生成 1打印 2完成 3失败 4结束）'},
            {field: 'createTime', sort: true, title: '生成时间'},
            {field: 'dataTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsPrintInfo.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(WmsPrintInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsPrintInfo.openAddDlg = function () {
        func.open({
            title: '添加条码打印信息表',
            content: Feng.ctxPath + '/wmsPrintInfo/add',
            tableId: WmsPrintInfo.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsPrintInfo.openEditDlg = function (data) {
        func.open({
            title: '修改条码打印信息表',
            content: Feng.ctxPath + '/wmsPrintInfo/edit?id=' + data.id,
            tableId: WmsPrintInfo.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsPrintInfo.exportExcel = function () {
        var checkRows = table.checkStatus(WmsPrintInfo.tableId);
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
    WmsPrintInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsPrintInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsPrintInfo.tableId);
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
        elem: '#' + WmsPrintInfo.tableId,
        url: Feng.ctxPath + '/wmsPrintInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsPrintInfo.initColumn()
    });
    table.on('checkbox(wmsPrintInfoTable)',function (obj) {
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
        WmsPrintInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsPrintInfo.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsPrintInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsPrintInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsPrintInfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsPrintInfo.onDeleteItem(data);
        }
    });
});
