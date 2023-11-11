layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 立库-仓库任务管理信息表-出仓管理
     */
    var WmsWarehouseTaskOut = {
        tableId: "wmsWarehouseTaskOutTable"
    };

    /**
     * 初始化表格的列
     */
    WmsWarehouseTaskOut.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            // {field: 'messageId', sort: true, title: '消息识别ID',minWidth: 160},
            {field: 'orderType', sort: true, title: '订单类别',templet: '#orderTypeTpl'},
            {field: 'taskMg', sort: true, title: '任务信息'},
            {field: 'sizes', sort: true, title: '规格型号'},
            {field: 'goodsType', sort: true, title: '出仓货物类型',templet: '#goodsTypeTpl'},
            // {field: 'materialTypeId', sort: true, title: '物料类型ID'},
            {field: 'materialSku', sort: true, title: '物料号'},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '描述'},
            {field: 'mBatch', sort: true, title: '批次'},
            {field: 'mNumber', sort: true, title: '数量'},
            {field: 'sortingInfo', sort: true, title: '出仓分拣',templet: '#sortingInfoTpl'},
            {field: 'turnoverType', sort: true, title: '周转箱类型',templet: '#turnoverTypeTpl'},
            {field: 'turnoverNumber', sort: true, title: '周转箱编号'},
            {field: 'barcode', sort: true, title: '周转箱条码'},
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
            // {field: 'bLatticeMaterialName', sort: true, title: '描述'},
            // {field: 'bLatticeMaterialSku', sort: true, title: '物料SKU'},
            // {field: 'bLatticeMUnit', sort: true, title: '单位'},
            // {field: 'bLatticeMaterialSerialNumber', sort: true, title: '物料编码'},
            // {field: 'bLatticeMBatch', sort: true, title: '批次'},
            // {field: 'bLatticeMNumber', sort: true, title: '数量'},
            {field: 'reqTag', sort: true, title: '请求标记',templet: '#reqTagTpl'},
            {field: 'reqStatus', sort: true, title: '请求状态',templet: '#reqStatusTpl'},
            {field: 'resTag', sort: true, title: '结果标记',templet: '#resTagTpl'},
            {field: 'resStatus', sort: true, title: '结果状态',templet: '#resStatusTpl'},
            {field: 'locaNumber', sort: true, title: '出仓库位编号'},
            {field: 'taskFeedback', sort: true, title: '任务反馈'},
            {field: 'createTime', sort: true, title: '创建时间',minWidth: 160},
            {field: 'reqTime', sort: true, title: '请求时间',minWidth: 160},
            {field: 'dataTime', sort: true, title: '数据时间',minWidth: 160},
            {field: 'mark', sort: true, title: '备注',minWidth: 160},
            {field: 'spareField',hide: true, sort: true, title: '备用字段',minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth: 160}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsWarehouseTaskOut.search = function () {
        var queryData = {};
        queryData['taskMg'] = $("#condition").val();
        queryData['resStatus'] = $("#resStatus1").val();
        queryData['orderType'] = $("#orderType").val();
        queryData['materialSku'] = $("#materialSku").val();
        queryData['materialType'] = $("#materialType").val();
        queryData['materialName'] = $("#materialName").val();
        queryData['sortingInfo'] = $("#sortingInfo").val();
        queryData['sizes'] = $("#sizes").val();
        table.reload(WmsWarehouseTaskOut.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsWarehouseTaskOut.openAddDlg = function () {
        func.open({
            title: '添加立库-仓库任务管理信息表-出仓',
            content: Feng.ctxPath + '/wmsWarehouseTaskOut/add',
            tableId: WmsWarehouseTaskOut.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsWarehouseTaskOut.openEditDlg = function (data) {
        func.open({
            title: '修改立库-仓库任务管理信息表-出仓',
            content: Feng.ctxPath + '/wmsWarehouseTaskOut/edit?id=' + data.id,
            tableId: WmsWarehouseTaskOut.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsWarehouseTaskOut.exportExcel = function () {
        var checkRows = table.checkStatus(WmsWarehouseTaskOut.tableId);
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
    WmsWarehouseTaskOut.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseTaskOut/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsWarehouseTaskOut.tableId);
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
        elem: '#' + WmsWarehouseTaskOut.tableId,
        url: Feng.ctxPath + '/wmsWarehouseTaskOut/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehouseTaskOut.initColumn()
    });

    table.on('checkbox(wmsWarehouseTaskOutTable)',function (obj) {
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
        WmsWarehouseTaskOut.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsWarehouseTaskOut.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsWarehouseTaskOut.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsWarehouseTaskOut.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsWarehouseTaskOut.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsWarehouseTaskOut.onDeleteItem(data);
        }
    });
});
