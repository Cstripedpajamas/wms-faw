layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅱ类柜库存信息表管理
     */
    var WmsCabinet2Stock = {
        tableId: "wmsCabinet2StockTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet2Stock.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {align: 'center',field: 'locaNumber', sort: true, title: '库位编号'},
            {align: 'center',field: 'locaRow', sort: true, title: '地址-排'},
            {align: 'center',field: 'locaCol', sort: true, title: '地址-列'},
            {align: 'center',field: 'locaLayer', sort: true, title: '地址-层'},
            // {align: 'center',field: 'locaEquipment', sort: true, title: '库位设备码'},
            {align: 'center',field: 'sizes', sort: true, title: '规格型号'},
            {align: 'center',field: 'locationState', sort: true, title: '库位状态',templet:'#locationState'},
            // {align: 'center',field: 'turnoverId', sort: true, title: '周转箱信息ID'},
            {align: 'center',field: 'barcode', sort: true, title: '条码信息'},
            // {align: 'center',field: 'materialTypeId', sort: true, title: '物料类型ID'}
            // {align: 'center',field: 'materialId', sort: true, title: '物料信息ID'},
            {align: 'center',field: 'materialName', sort: true, title: '描述'},
            {align: 'center',field: 'materialSku', sort: true, title: '物料号'},
            {align: 'center',field: 'plant', sort: true, title: '工厂'},
            {align: 'center',field: 'mBatch', sort: true, title: '批次'},
            {align: 'center',field: 'mNumber', sort: true, title: '数量'},
            {align: 'center',field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet2Stock.search = function () {
        var queryData = {};
        queryData['locaNumber'] = $("#condition").val();
        queryData['materialName'] = $("#materialName").val();
        queryData['materialSku'] = $("#materialSku").val();
        queryData['mBatch'] = $("#mBatch").val();
        queryData['locationState'] = $("#locationState1").val();
        queryData['plant'] = $("#plant").val();
        queryData['sizes'] = $("#sizes").val();
        table.reload(WmsCabinet2Stock.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet2Stock.openAddDlg = function () {
        func.open({
            title: '添加Ⅱ类柜库存信息表',
            content: Feng.ctxPath + '/wmsCabinet2Stock/add',
            tableId: WmsCabinet2Stock.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet2Stock.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅱ类柜库存信息表',
            content: Feng.ctxPath + '/wmsCabinet2Stock/edit?id=' + data.id,
            tableId: WmsCabinet2Stock.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet2Stock.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet2Stock.tableId);
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
    WmsCabinet2Stock.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCabinet2Stock/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet2Stock.tableId);
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
        elem: '#' + WmsCabinet2Stock.tableId,
        url: Feng.ctxPath + '/wmsCabinet2Stock/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet2Stock.initColumn()
    });
    table.on('checkbox(wmsCabinet2StockTable)',function (obj) {
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
        WmsCabinet2Stock.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet2Stock.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet2Stock.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet2Stock.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet2Stock.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet2Stock.onDeleteItem(data);
        }
    });
});
