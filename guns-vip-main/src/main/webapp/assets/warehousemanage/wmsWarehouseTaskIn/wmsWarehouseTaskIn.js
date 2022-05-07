layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 立库-仓库任务管理信息表-入仓管理
     */
    var WmsWarehouseTaskIn = {
        tableId: "wmsWarehouseTaskInTable"
    };

    /**
     * 初始化表格的列
     */
    WmsWarehouseTaskIn.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'messageId', sort: true, title: '消息识别ID',minWidth: 160},
            {field: 'orderType', sort: true, title: '订单类别',templet: '#orderTypeTpl'},
            {field: 'taskMg', sort: true, title: '任务信息'},
            {field: 'goodsType', sort: true, title: '入仓货物类型',templet: '#goodsTypeTpl'},
            {field: 'turnoverType', sort: true, title: '周转箱类型',templet: '#turnoverTypeTpl'},
            {field: 'turnoverNumber', sort: true, title: '周转箱编号'},
            {field: 'tBarcode', sort: true, title: '周转箱条码'},
            // {field: 'aLatticeCode', sort: true, title: 'A格口编号'},
            // {field: 'aLatticeQr', sort: true, title: 'A格口条码'},
            // {field: 'aLatticeStatus', sort: true, title: 'A格口状态',templet: '#aLatticeStatusTpl'},
            // // {field: 'aLatticeMaterialTypeId', sort: true, title: '物料类型ID'},
            // // {field: 'aLatticeMaterialId', sort: true, title: '物料信息ID'},
            // {field: 'aLatticeMaterialType', sort: true, title: '物料类型'},
            // {field: 'aLatticeMaterialName', sort: true, title: '物料名称'},
            // {field: 'aLatticeMaterialSku', sort: true, title: '物料SKU'},
            // {field: 'aLatticeMUnit', sort: true, title: '单位'},
            // {field: 'aLatticeMaterialSerialNumber', sort: true, title: '物料编码'},
            // {field: 'aLatticeMBatch', sort: true, title: '批次'},
            // {field: 'aLatticeMNumber', sort: true, title: '数量'},
            // {field: 'bLatticeCode', sort: true, title: 'B格口编号'},
            // {field: 'bLatticeQr', sort: true, title: 'B格口条码'},
            // {field: 'bLatticeStatus', sort: true, title: 'B格口状态',templet: '#bLatticeStatusTpl'},
            // // {field: 'bLatticeMaterialTypeId', sort: true, title: '物料类型ID'},
            // // {field: 'bLatticeMaterialId', sort: true, title: '物料信息ID'},
            // {field: 'bLatticeMaterialType', sort: true, title: '物料类型'},
            // {field: 'bLatticeMaterialName', sort: true, title: '物料名称'},
            // {field: 'bLatticeMaterialSku', sort: true, title: '物料SKU'},
            // {field: 'bLatticeMUnit', sort: true, title: '单位'},
            // {field: 'bLatticeMaterialSerialNumber', sort: true, title: '物料编码'},
            // {field: 'bLatticeMBatch', sort: true, title: '批次'},
            // {field: 'bLatticeMNumber', sort: true, title: '数量'},
            {field: 'turnoverMouthQuality', sort: true, title: '周转箱格口数量'},
            {field: 'mark', sort: true, title: '备注'},
            {field: 'spareField', sort: true, title: '备用字段'},
            {field: 'reqTag', sort: true, title: '请求标记',templet: '#reqTagTpl'},
            {field: 'reqStatus', sort: true, title: '请求状态',templet: '#reqStatusTpl'},
            {field: 'resTag', sort: true, title: '结果标记',templet: '#resTagTpl'},
            {field: 'resStatus', sort: true, title: '结果状态',templet: '#resStatusTpl'},
            {field: 'locaNumber', sort: true, title: '入仓仓位编号'},
            {field: 'taskFeedback', sort: true, title: '任务反馈'},
            {field: 'createTime', sort: true, title: '创建时间',minWidth: 160},
            {field: 'reqTime', sort: true, title: '请求时间',minWidth: 160},
            {field: 'dataTime', sort: true, title: '数据时间',minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth: 160}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsWarehouseTaskIn.search = function () {
        var queryData = {};
        queryData['messageId'] = $("#condition").val();
        table.reload(WmsWarehouseTaskIn.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsWarehouseTaskIn.openAddDlg = function () {
        func.open({
            title: '添加立库-仓库任务管理信息表-入仓',
            content: Feng.ctxPath + '/wmsWarehouseTaskIn/add',
            tableId: WmsWarehouseTaskIn.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsWarehouseTaskIn.openEditDlg = function (data) {
        func.open({
            title: '修改立库-仓库任务管理信息表-入仓',
            content: Feng.ctxPath + '/wmsWarehouseTaskIn/edit?id=' + data.id,
            tableId: WmsWarehouseTaskIn.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsWarehouseTaskIn.exportExcel = function () {
        var checkRows = table.checkStatus(WmsWarehouseTaskIn.tableId);
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
    WmsWarehouseTaskIn.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseTaskIn/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsWarehouseTaskIn.tableId);
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
        elem: '#' + WmsWarehouseTaskIn.tableId,
        url: Feng.ctxPath + '/wmsWarehouseTaskIn/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehouseTaskIn.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsWarehouseTaskIn.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsWarehouseTaskIn.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsWarehouseTaskIn.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsWarehouseTaskIn.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsWarehouseTaskIn.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsWarehouseTaskIn.onDeleteItem(data);
        }
    });
});
