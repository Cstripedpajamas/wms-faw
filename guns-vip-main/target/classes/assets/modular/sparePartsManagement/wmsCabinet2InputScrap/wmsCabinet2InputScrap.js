layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅱ类柜投入报废信息表管理
     */
    var WmsCabinet2InputScrap = {
        tableId: "wmsCabinet2InputScrapTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet2InputScrap.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            // {field: 'useTask', sort: true, title: '领用任务ID'},
            {field: 'taskNumber', sort: true, title: '领用任务编号'},
            // {field: 'materialTypeId', sort: true, title: '报废物料类型ID'},
            {field: 'materialType', sort: true, title: '报废物料类型'},
            {field: 'sizes', sort: true, title: '规格型号'},
            {field: 'operators', sort: true, title: '操作人工号'},
            {field: 'materialName', sort: true, title: '报废物料描述'},
            {field: 'materialSku', sort: true, title: '报废物料号'},
            {field: 'mNumber', sort: true, title: '报废数量'},
            {field: 'operator', sort: true, title: '操作人'},
            {field: 'operationTime', sort: true, title: '操作时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet2InputScrap.search = function () {
        var queryData = {};
        queryData['operator'] = $("#condition").val();
        queryData['taskNumber'] = $("#taskNumber").val();
        queryData['sizes'] = $("#sizes").val();
        queryData['operators'] = $("#operators").val();
        queryData['materialName'] = $("#materialName").val();
        queryData['materialSku'] = $("#materialSku").val();
        table.reload(WmsCabinet2InputScrap.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet2InputScrap.openAddDlg = function () {
        func.open({
            title: '添加Ⅱ类柜投入报废信息表',
            content: Feng.ctxPath + '/wmsCabinet2InputScrap/add',
            tableId: WmsCabinet2InputScrap.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet2InputScrap.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅱ类柜投入报废信息表',
            content: Feng.ctxPath + '/wmsCabinet2InputScrap/edit?id=' + data.id,
            tableId: WmsCabinet2InputScrap.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet2InputScrap.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet2InputScrap.tableId);
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
    WmsCabinet2InputScrap.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCabinet2InputScrap/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet2InputScrap.tableId);
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
        elem: '#' + WmsCabinet2InputScrap.tableId,
        url: Feng.ctxPath + '/wmsCabinet2InputScrap/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet2InputScrap.initColumn()
    });
    table.on('checkbox(wmsCabinet2InputScrapTable)',function (obj) {
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
        WmsCabinet2InputScrap.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet2InputScrap.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet2InputScrap.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet2InputScrap.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet2InputScrap.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet2InputScrap.onDeleteItem(data);
        }
    });
});
