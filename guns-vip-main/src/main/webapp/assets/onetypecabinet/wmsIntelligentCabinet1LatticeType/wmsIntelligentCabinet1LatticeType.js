layui.use(['table', 'admin', 'ax', 'func','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * Ⅰ类柜格口类型信息表管理
     */
    var WmsIntelligentCabinet1LatticeType = {
        tableId: "wmsIntelligentCabinet1LatticeTypeTable"
    };

    /**
     * 初始化表格的列
     */
    WmsIntelligentCabinet1LatticeType.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'typeName', sort: true, title: '类型名称'},
            {field: 'typeStandards', sort: true, title: '类型规格'},
            {field: 'remarks', sort: true, title: '备注'},
            {field: 'dataState', sort: true, title: '数据状态',templet: '#dataStateTpl'},
            {field: 'createTime', sort: true, title: '数据时间', minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsIntelligentCabinet1LatticeType.search = function () {
        var queryData = {};
        queryData['typeName'] = $("#typeName").val();
        table.reload(WmsIntelligentCabinet1LatticeType.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsIntelligentCabinet1LatticeType.openAddDlg = function () {
        func.open({
            title: '添加Ⅰ类柜格口类型信息表',
            content: Feng.ctxPath + '/wmsIntelligentCabinet1LatticeType/add',
            tableId: WmsIntelligentCabinet1LatticeType.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsIntelligentCabinet1LatticeType.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅰ类柜格口类型信息表',
            content: Feng.ctxPath + '/wmsIntelligentCabinet1LatticeType/edit?id=' + data.id,
            tableId: WmsIntelligentCabinet1LatticeType.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsIntelligentCabinet1LatticeType.exportExcel = function () {
        var checkRows = table.checkStatus(WmsIntelligentCabinet1LatticeType.tableId);
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
    WmsIntelligentCabinet1LatticeType.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsIntelligentCabinet1LatticeType/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsIntelligentCabinet1LatticeType.tableId);
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
        elem: '#' + WmsIntelligentCabinet1LatticeType.tableId,
        url: Feng.ctxPath + '/wmsIntelligentCabinet1LatticeType/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsIntelligentCabinet1LatticeType.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsIntelligentCabinet1LatticeType.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsIntelligentCabinet1LatticeType.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsIntelligentCabinet1LatticeType.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsIntelligentCabinet1LatticeType.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsIntelligentCabinet1LatticeType.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsIntelligentCabinet1LatticeType.onDeleteItem(data);
        }
    });

    //监听指定开关
    form.on('switch(dataStateSwitch)', function(data){
        var ajax = new $ax(Feng.ctxPath + "/wmsIntelligentCabinet1LatticeType/editItem");
        ajax.set("id", data.value);
        ajax.set("dataState", this.checked ? '1' : '2');
        ajax.start();
    });

});
