layui.use(['table', 'admin', 'ax', 'func','upload'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var upload = layui.upload;
    /**
     * 立库-仓库库存信息表管理
     */
    var WmsWarehouseStock = {
        tableId: "wmsWarehouseStockTable"
    };

    /**
     * 初始化表格的列
     */
    WmsWarehouseStock.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'locaNumber', sort: true, title: '库位编号'},
            {field: 'roadway', sort: true, title: '巷道',templet: '#roadwayTpl'},
            {field: 'locaRow', sort: true, title: '地址-排'},
            {field: 'locaCol', sort: true, title: '地址-列'},
            {field: 'locaLayer', sort: true, title: '地址-层'},
            {field: 'locaEquipment', sort: true, title: '库位设备码'},
            {field: 'locaState', sort: true, title: '库位状态',templet: '#locaStateTpl'},
            // {field: 'turnoverId', sort: true, title: '周转箱信息ID'},
            {field: 'mark', sort: true, title: '备注',minWidth: 160},
            {field: 'spareField', sort: true, title: '备用字段',minWidth: 160},
            {field: 'createTime', sort: true, title: '数据时间',minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth: 160}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsWarehouseStock.search = function () {
        var queryData = {};
        queryData['locaState'] = $("#condition").val();
        table.reload(WmsWarehouseStock.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsWarehouseStock.openAddDlg = function () {
        func.open({
            title: '添加立库-仓库库存信息表',
            content: Feng.ctxPath + '/wmsWarehouseStock/add',
            tableId: WmsWarehouseStock.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsWarehouseStock.openEditDlg = function (data) {
        func.open({
            title: '修改立库-仓库库存信息表',
            content: Feng.ctxPath + '/wmsWarehouseStock/edit?id=' + data.id,
            tableId: WmsWarehouseStock.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsWarehouseStock.exportExcel = function () {
        var checkRows = table.checkStatus(WmsWarehouseStock.tableId);
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
    WmsWarehouseStock.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseStock/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsWarehouseStock.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 库存同步
    WmsWarehouseStock.inventorySyn = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseStock/inventorySyn", function (data) {
                Feng.success("同步成功!");
                table.reload(WmsWarehouseStock.tableId);
            }, function (data) {
                Feng.error("同步失败!" + data.responseJSON.message + "!");
            });
            ajax.start();
        };
        Feng.confirm("是否确定同步当前库存?", operation);
    };

    // 模板下载
    $("#btnTemplate").click(function () {
        window.location.href = Feng.ctxPath + "/wmsWarehouseStock/exportOut"
    });

    // 导入excel
    var uploadInst = upload.render({
        elem: '#btnExp2'
        ,url: Feng.ctxPath + '/wmsWarehouseStock/exportIn'
        ,accept: 'file'
        ,done: function (res) {
            Feng.success("导入成功~");
            table.reload(WmsWarehouseStock.tableId, {url: Feng.ctxPath + "/wmsWarehouseStock/list"});
        }
        ,error: function () {
            //请求异常回调
        }
    });

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsWarehouseStock.tableId,
        url: Feng.ctxPath + '/wmsWarehouseStock/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehouseStock.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsWarehouseStock.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsWarehouseStock.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsWarehouseStock.exportExcel();
    });
    // 库存同步
    $('#btnExp3').click(function () {
        WmsWarehouseStock.inventorySyn();
    });



    // 工具条点击事件
    table.on('tool(' + WmsWarehouseStock.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsWarehouseStock.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsWarehouseStock.onDeleteItem(data);
        }
    });
});
