layui.use(['table', 'admin', 'ax', 'func','laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var laydate = layui.laydate;

    /**
     * 立库-周转箱绑定货物信息表管理
     */
    var WmsWarehouseTurnoverBind = {
        tableId: "wmsWarehouseTurnoverBindTable"
    };
    //日期范围

        // laydate.render({
        //     elem: '#test1',
        //     type:'datetime',
        //     rang:"~",
        //     format: 'yyyy-MM-dd-HHmm'
        // });

    laydate.render({
        elem: '#time'
        ,type: 'datetime'
        ,range: '~'
        ,format: 'yyyy-MM-dd'
    });

    /**
     * 初始化表格的列
     */
    WmsWarehouseTurnoverBind.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true,align:"center", title: '记录ID'},
            {field: 'turnoverId', sort: true, title: '周转箱信息ID'},
            {field: 'latticeCode', sort: true, title: '格口编号'},
            {field: 'latticeState', sort: true, title: '格口状态',templet: '#latticeState'},
            {field: 'goodsType', sort: true, title: '货物类型',templet: '#goodsTypeTpl'},
            // {field: 'materialTypeId', sort: true, title: '物料类型ID'},
            // {field: 'materialId', sort: true, title: '物料信息ID'},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '物料名称'},
            {field: 'materialSku', sort: true, title: '物料SKU'},
            {field: 'mUnit', sort: true, title: '单位'},
            {field: 'materialSerialNumber', sort: true, title: '物料编码'},
            {field: 'mBatch', sort: true, title: '批次'},
            {field: 'mNumber', sort: true, title: '数量'},
            {field: 'createTime', sort: true, title: '数据时间',minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsWarehouseTurnoverBind.search = function () {
        var queryData = {};
        queryData['materialType'] = $("#materialType").val();
        queryData['materialName'] = $("#materialName").val();
        queryData['goodsType'] = $("#goodsType").val();
        queryData['startTime'] = $("#time").val().split("~")[0].trim();
        queryData['endTime'] = $("#time").val().split("~")[1].trim();
        table.reload(WmsWarehouseTurnoverBind.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsWarehouseTurnoverBind.openAddDlg = function () {
        func.open({
            title: '添加立库-周转箱绑定货物信息表',
            content: Feng.ctxPath + '/wmsWarehouseTurnoverBind/add',
            tableId: WmsWarehouseTurnoverBind.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsWarehouseTurnoverBind.openEditDlg = function (data) {
        func.open({
            title: '修改立库-周转箱绑定货物信息表',
            content: Feng.ctxPath + '/wmsWarehouseTurnoverBind/edit?id=' + data.id,
            tableId: WmsWarehouseTurnoverBind.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsWarehouseTurnoverBind.exportExcel = function () {
        var checkRows = table.checkStatus(WmsWarehouseTurnoverBind.tableId);
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
    WmsWarehouseTurnoverBind.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseTurnoverBind/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsWarehouseTurnoverBind.tableId);
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
        elem: '#' + WmsWarehouseTurnoverBind.tableId,
        url: Feng.ctxPath + '/wmsWarehouseTurnoverBind/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehouseTurnoverBind.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsWarehouseTurnoverBind.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsWarehouseTurnoverBind.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsWarehouseTurnoverBind.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsWarehouseTurnoverBind.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsWarehouseTurnoverBind.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsWarehouseTurnoverBind.onDeleteItem(data);
        }
    });
});
