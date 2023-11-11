layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅰ类柜换新任务信息表管理
     */
    var WmsCabinet1RenewTask = {
        tableId: "wmsCabinet1RenewTaskTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet1RenewTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            // {field: 'id', hide: true, title: '记录ID'},
            {field: 'taskNumber', sort: true, title: '任务编号',minWidth: 160},
            {field: 'materialTypeId', hide: true, title: '物料类型ID'},
            {field: 'materialName', sort: true, title: '描述',minWidth: 120},
            {field: 'materialSku', sort: true, title: '物料号',minWidth: 120},
            {field: 'oMaterialId', hide: true, title: '旧物料信息ID'},
            {field: 'oMaterialSerialNumber', sort: true, title: '旧RFID编码',minWidth: 120},
            {field: 'workTeam', sort: true, title: '组织编码',minWidth: 120},
            {field: 'sizes', sort: true, title: '规格型号',minWidth: 120},
            {field: 'operator', sort: true, title: '操作人姓名',minWidth: 120},
            {field: 'nMaterialId', hide: true, title: '新-物料信息ID'},
            {field: 'nMaterialSerialNumber', sort: true, title: '新RFID编码',minWidth: 120},
            {field: 'storageStockId', hide: true, title: '存储-库存信息ID'},
            {field: 'storageLocaSerialNumber', sort: true, title: '存储-库位编号',minWidth: 120},
            {field: 'taskStockId', hide: true, title: '取货-库存信息ID'},
            {field: 'taskLocaSerialNumber', sort: true, title: '取货-库位编号',minWidth: 120},
            {field: 'operationTime', sort: true, title: '操作时间',minWidth: 160},
            {field: 'taskState', sort: true, title: '任务状态',templet: '#taskStateTpl'},
            {field: 'errorTrag', sort: true, title: '异常标记',templet: '#errorTragTpl'},
            {field: 'createTime', sort: true, title: '创建时间',minWidth: 160},
            {field: 'updateTime', sort: true, title: '更新时间',minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth: 160}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet1RenewTask.search = function () {
        var queryData = {};
        queryData['taskNumber'] = $("#condition").val();
        queryData['taskState'] = $("#taskState1").val();
        queryData['sizes'] = $("#sizes").val();
        queryData['operator'] = $("#operator").val();
        queryData['workTeam'] = $("#workTeam").val();
        queryData['materialSku'] = $("#materialSku").val();
        table.reload(WmsCabinet1RenewTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet1RenewTask.openAddDlg = function () {
        func.open({
            title: '添加Ⅰ类柜换新任务信息表',
            content: Feng.ctxPath + '/wmsCabinet1RenewTask/add',
            tableId: WmsCabinet1RenewTask.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet1RenewTask.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅰ类柜换新任务信息表',
            content: Feng.ctxPath + '/wmsCabinet1RenewTask/edit?id=' + data.id,
            tableId: WmsCabinet1RenewTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet1RenewTask.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet1RenewTask.tableId);
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
    WmsCabinet1RenewTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCabinet1RenewTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet1RenewTask.tableId);
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
        elem: '#' + WmsCabinet1RenewTask.tableId,
        url: Feng.ctxPath + '/wmsCabinet1RenewTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet1RenewTask.initColumn()
    });
    table.on('checkbox(wmsCabinet1RenewTaskTable)',function (obj) {
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
        WmsCabinet1RenewTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet1RenewTask.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet1RenewTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet1RenewTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet1RenewTask.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet1RenewTask.onDeleteItem(data);
        }
    });
});
