layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 设备故障统计表管理
     */
    var WmsEquipmentFailureStatistics = {
        tableId: "wmsEquipmentFailureStatisticsTable"
    };

    /**
     * 初始化表格的列
     */
    WmsEquipmentFailureStatistics.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'equipmentName', sort: true, title: '设备名称'},
            {field: 'failureType', sort: true, title: '故障类型'},
            {field: 'failureDescribe', sort: true, title: '故障描述'},
            {field: 'createTime', sort: true, title: '故障时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsEquipmentFailureStatistics.search = function () {
        var queryData = {};
        queryData['equipmentName'] = $("#equipmentName").val();
        table.reload(WmsEquipmentFailureStatistics.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsEquipmentFailureStatistics.openAddDlg = function () {
        func.open({
            title: '添加设备故障统计表',
            content: Feng.ctxPath + '/wmsEquipmentFailureStatistics/add',
            tableId: WmsEquipmentFailureStatistics.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsEquipmentFailureStatistics.openEditDlg = function (data) {
        func.open({
            title: '修改设备故障统计表',
            content: Feng.ctxPath + '/wmsEquipmentFailureStatistics/edit?id=' + data.id,
            tableId: WmsEquipmentFailureStatistics.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsEquipmentFailureStatistics.exportExcel = function () {
        var checkRows = table.checkStatus(WmsEquipmentFailureStatistics.tableId);
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
    WmsEquipmentFailureStatistics.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsEquipmentFailureStatistics/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsEquipmentFailureStatistics.tableId);
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
        elem: '#' + WmsEquipmentFailureStatistics.tableId,
        url: Feng.ctxPath + '/wmsEquipmentFailureStatistics/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsEquipmentFailureStatistics.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsEquipmentFailureStatistics.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsEquipmentFailureStatistics.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsEquipmentFailureStatistics.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsEquipmentFailureStatistics.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsEquipmentFailureStatistics.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsEquipmentFailureStatistics.onDeleteItem(data);
        }
    });
});
