layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅱ类柜物料补货阈值配置表管理
     */
    var WmsIntelligentCabinet2Conf = {
        tableId: "wmsIntelligentCabinet2ConfTable"
    };

    /**
     * 初始化表格的列
     */
    WmsIntelligentCabinet2Conf.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            // {align: 'center',field: 'type', templet: '#typeTpl',sort: true, title: '类型'},
            {field: 'materialTypeId', hide: true,sort: true, title: '物料类型ID'},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '描述'},
            {field: 'materialSku', sort: true, title: '物料号'},
            {field: 'sizes', sort: true, title: '规格型号'},
            {field: 'account', sort: true, title: '操作人工号'},
            {field: 'name', sort: true, title: '操作人姓名'},
            {field: 'plant', sort: true, title: '工厂'},
            {field: 'diBatchNo', sort: true, title: '批次'},
            {field: 'replenishmentThreshold', sort: true, title: '补货阈值'},
            {field: 'operator', sort: true, title: '操作人'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsIntelligentCabinet2Conf.search = function () {
        var queryData = {};
        queryData['materialName'] = $("#materialName").val();
        queryData['materialType'] = $("#materialType").val();
        queryData['materialSku'] = $("#materialSku").val();
        queryData['plant'] = $("#plant").val();
        queryData['diBatchNo'] = $("#diBatchNo").val();
        queryData['sizes'] = $("#sizes").val();
        queryData['account'] = $("#account").val();
        queryData['name'] = $("#name").val();
        table.reload(WmsIntelligentCabinet2Conf.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsIntelligentCabinet2Conf.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/wmsIntelligentCabinet2Conf/add';
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsIntelligentCabinet2Conf.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/wmsIntelligentCabinet2Conf/edit?id=' + data.id;
    };

    /**
     * 导出excel按钮
     */
    WmsIntelligentCabinet2Conf.exportExcel = function () {
        var checkRows = table.checkStatus(WmsIntelligentCabinet2Conf.tableId);
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
    WmsIntelligentCabinet2Conf.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsIntelligentCabinet2Conf/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsIntelligentCabinet2Conf.tableId);
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
        elem: '#' + WmsIntelligentCabinet2Conf.tableId,
        url: Feng.ctxPath + '/wmsIntelligentCabinet2Conf/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsIntelligentCabinet2Conf.initColumn()
    });
    table.on('checkbox(wmsIntelligentCabinet2ConfTable)',function (obj) {
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
        WmsIntelligentCabinet2Conf.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsIntelligentCabinet2Conf.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsIntelligentCabinet2Conf.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsIntelligentCabinet2Conf.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsIntelligentCabinet2Conf.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsIntelligentCabinet2Conf.onDeleteItem(data);
        }
    });
});
