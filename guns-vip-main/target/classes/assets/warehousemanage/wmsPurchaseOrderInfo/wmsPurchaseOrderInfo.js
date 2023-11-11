layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 采购订单信息表管理
     */
    var WmsPurchaseOrderInfo = {
        tableId: "wmsPurchaseOrderInfoTable"
    };

    /**
     * 初始化表格的列
     */
    WmsPurchaseOrderInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'purNumber', sort: true, title: '采购单号'},
            {field: 'type', sort: true, title: '类型（1工具 2备品备件）'},
            //{field: 'materialTypeId', sort: true, title: '物料类型ID'},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '描述'},
            {field: 'materialSku', sort: true, title: '物料号'},
            {field: 'mUnit', sort: true, title: '基本计量单位'},
            {field: 'mNumber', sort: true, title: '数量'},
            {field: 'arrivalTime', sort: true, title: '到货时间'},
            {field: 'arrivalState', sort: true, title: '到货状态（0初始 1入库 2接收 3退还）'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {field: 'receivedQuantity', sort: true, title: '接收数量'},
            {field: 'printNum', sort: true, title: '打印数量'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsPurchaseOrderInfo.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(WmsPurchaseOrderInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsPurchaseOrderInfo.openAddDlg = function () {
        func.open({
            title: '添加采购订单信息表',
            content: Feng.ctxPath + '/wmsPurchaseOrderInfo/add',
            tableId: WmsPurchaseOrderInfo.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsPurchaseOrderInfo.openEditDlg = function (data) {
        func.open({
            title: '修改采购订单信息表',
            content: Feng.ctxPath + '/wmsPurchaseOrderInfo/edit?id=' + data.id,
            tableId: WmsPurchaseOrderInfo.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsPurchaseOrderInfo.exportExcel = function () {
        var checkRows = table.checkStatus(WmsPurchaseOrderInfo.tableId);
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
    WmsPurchaseOrderInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsPurchaseOrderInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsPurchaseOrderInfo.tableId);
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
        elem: '#' + WmsPurchaseOrderInfo.tableId,
        url: Feng.ctxPath + '/wmsPurchaseOrderInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsPurchaseOrderInfo.initColumn()
    });
    table.on('checkbox(wmsPurchaseOrderInfoTable)',function (obj) {
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
        WmsPurchaseOrderInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsPurchaseOrderInfo.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsPurchaseOrderInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsPurchaseOrderInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsPurchaseOrderInfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsPurchaseOrderInfo.onDeleteItem(data);
        }
    });
});
