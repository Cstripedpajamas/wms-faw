layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 立库-周转箱绑定货物信息表管理
     */
    var WmsWarehouseTurnoverBind = {
        tableId: "wmsWarehouseTurnoverBindTable"
    };

    /**
     * 初始化表格的列
     */
    WmsWarehouseTurnoverBind.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            // {field: 'turnoverId', sort: true, title: '周转箱信息ID'},
            {field: 'latticeCode', sort: true, title: '格口编号'},
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
        queryData['latticeCode'] = $("#condition").val();
        table.reload(WmsWarehouseTurnoverBind.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 添加新增按钮
     */
    WmsWarehouseTurnoverBind.add = function () {
        var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseTurnoverBind/addItem", function (data) {
            Feng.success("添加成功!");
            table.reload(WmsWarehouseTurnoverBind.tableId);
        }, function (data) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",Feng.getUrlParam("id"));
        ajax.start();
    }

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsWarehouseTurnoverBind.tableId,
        url: Feng.ctxPath + '/wmsWarehouseTurnoverBind/findLattice?id=' + Feng.getUrlParam("id"),
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehouseTurnoverBind.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsWarehouseTurnoverBind.search();
    });


    $('#btnAdd').click(function () {
        WmsWarehouseTurnoverBind.add()
    });

});
