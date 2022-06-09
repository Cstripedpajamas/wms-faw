layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅰ类柜库存信息表管理
     */
    var WmsIntelligentCabinet1Stock = {
        tableId: "wmsIntelligentCabinet1StockTable"
    };

    /**
     * 初始化表格的列
     */
    WmsIntelligentCabinet1Stock.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'locaSerialNumber', sort: true, title: '库位编号',minWidth: 160},
            {field: 'locaRow', sort: true, title: '地址-排'},
            {field: 'locaCol', sort: true, title: '地址-列'},
            {field: 'promptDirection', sort: true, title: '提示方向',minWidth: 120},
            {field: 'latticeType', hide: true, title: '格口类型ID'},
            {field: 'latticeTypeName', sort: true, title: '格口类型',minWidth: 120},
            {field: 'locaEquipment', sort: true, title: '库位设备码',minWidth: 120},
            {field: 'locaState', sort: true, title: '库位状态',templet: '#locaStateTpl',minWidth: 120},
            {field: 'latticeState', sort: true, title: '格口状态',templet: '#latticeStateTpl',minWidth: 120},
            // {field: 'materialTypeId', sort: true, title: '库存-物料类型ID'},
            // {field: 'materialId', sort: true, title: '库存-物料信息ID'},
            {field: 'materialName', sort: true, title: '物料名称',minWidth: 120},
            {field: 'materialSku', sort: true, title: '物料SKU',minWidth: 120},
            {field: 'materialSerialNumber', sort: true, title: '物料编码',minWidth: 120},
            {field: 'mNumber', sort: true, title: '数量'},
            {field: 'toolState', sort: true, title: '大件物料状态',templet: '#toolStateTpl'},
            {field: 'createTime', sort: true, title: '数据时间',minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth: 160}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsIntelligentCabinet1Stock.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(WmsIntelligentCabinet1Stock.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsIntelligentCabinet1Stock.openAddDlg = function () {
        func.open({
            title: '添加Ⅰ类柜库存信息表',
            content: Feng.ctxPath + '/wmsIntelligentCabinet1Stock/add',
            tableId: WmsIntelligentCabinet1Stock.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsIntelligentCabinet1Stock.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅰ类柜库存信息表',
            content: Feng.ctxPath + '/wmsIntelligentCabinet1Stock/edit?id=' + data.id,
            tableId: WmsIntelligentCabinet1Stock.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsIntelligentCabinet1Stock.exportExcel = function () {
        var checkRows = table.checkStatus(WmsIntelligentCabinet1Stock.tableId);
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
    WmsIntelligentCabinet1Stock.onCabinetItem = function (data) {
        func.open({
            title: '格口详情',
            content: Feng.ctxPath + '/wmsIntelligentCabinet1LatticeType/details?id=' + data.latticeType,
            tableId: WmsIntelligentCabinet1Stock.tableId
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsIntelligentCabinet1Stock.tableId,
        url: Feng.ctxPath + '/wmsIntelligentCabinet1Stock/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsIntelligentCabinet1Stock.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsIntelligentCabinet1Stock.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsIntelligentCabinet1Stock.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsIntelligentCabinet1Stock.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsIntelligentCabinet1Stock.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsIntelligentCabinet1Stock.openEditDlg(data);
        }  else if (layEvent === 'cabinet') {
            WmsIntelligentCabinet1Stock.onCabinetItem(data);
        }
    });
});
