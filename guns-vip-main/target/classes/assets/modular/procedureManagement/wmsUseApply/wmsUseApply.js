layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 领用申请信息表管理
     */
    var WmsUseApply = {
        tableId: "wmsUseApplyTable"
    };

    /**
     * 初始化表格的列
     */
    WmsUseApply.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'processNumber', sort: true, title: '流程单号',align: "center"},
            {field: 'operator', sort: true, title: '人员信息',align: "center"},
            {field: 'materialId', sort: true, title: '物料信息',align: "center"},
            {field: 'mNumber', sort: true, title: '物料数量',align: "center"},
            {field: 'scrapMaterialId', sort: true, title: '报废物料类型ID',align: "center"},
            {field: 'scrapNum', sort: true, title: '报废数量',align: "center"},
            {field: 'processType', sort: true, title: '申请类型',templet:'#processType',align: "center"},
            {field: 'processReason', sort: true, title: '申请原因',align: "center"},
            {field: 'dataTime', sort: true, title: '数据时间',align: "center"},
            {field: 'dataState', sort: true, title: '状态',templet: '#dataState',align: "center"},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsUseApply.search = function () {
        var queryData = {};
        queryData['processNumber'] = $("#condition").val();
        table.reload(WmsUseApply.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsUseApply.openAddDlg = function () {
        func.open({
            title: '添加领用申请信息表',
            content: Feng.ctxPath + '/wmsUseApply/add',
            tableId: WmsUseApply.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsUseApply.openEditDlg = function (data) {
        func.open({
            title: '修改领用申请信息表',
            content: Feng.ctxPath + '/wmsUseApply/edit?id=' + data.id,
            tableId: WmsUseApply.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsUseApply.exportExcel = function () {
        var checkRows = table.checkStatus(WmsUseApply.tableId);
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
    WmsUseApply.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsUseApply/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsUseApply.tableId);
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
        elem: '#' + WmsUseApply.tableId,
        url: Feng.ctxPath + '/wmsUseApply/list2',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsUseApply.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsUseApply.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsUseApply.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsUseApply.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsUseApply.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsUseApply.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsUseApply.onDeleteItem(data);
        }
    });
});
