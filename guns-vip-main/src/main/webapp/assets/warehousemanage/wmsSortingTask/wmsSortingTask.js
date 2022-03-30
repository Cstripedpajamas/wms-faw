layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 机械手分拣任务表管理
     */
    var WmsSortingTask = {
        tableId: "wmsSortingTaskTable"
    };

    /**
     * 初始化表格的列
     */
    WmsSortingTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'taskNumber', sort: true, title: '任务编号'},
            {field: 'turnoverType', sort: true, title: '周转箱类型(A单格口/B双格口)'},
            {field: 'turnoverNumber', sort: true, title: '周转箱编号'},
            {field: 'barcode', sort: true, title: '周转箱条码'},
            {field: 'latticeCode', sort: true, title: '分拣格口（A/B）'},
            {field: 'sortingMaterialType', sort: true, title: '分拣物料类型'},
            {field: 'sortingNum', sort: true, title: '分拣数量'},
            {field: 'taskState', sort: true, title: '任务状态（0初始 1开始分拣 2分拣完成 3结束）'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'reqTime', sort: true, title: '请求时间'},
            {field: 'dataTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsSortingTask.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        table.reload(WmsSortingTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsSortingTask.openAddDlg = function () {
        func.open({
            title: '添加机械手分拣任务表',
            content: Feng.ctxPath + '/wmsSortingTask/add',
            tableId: WmsSortingTask.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsSortingTask.openEditDlg = function (data) {
        func.open({
            title: '修改机械手分拣任务表',
            content: Feng.ctxPath + '/wmsSortingTask/edit?id=' + data.id,
            tableId: WmsSortingTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsSortingTask.exportExcel = function () {
        var checkRows = table.checkStatus(WmsSortingTask.tableId);
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
    WmsSortingTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsSortingTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsSortingTask.tableId);
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
        elem: '#' + WmsSortingTask.tableId,
        url: Feng.ctxPath + '/wmsSortingTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsSortingTask.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsSortingTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsSortingTask.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsSortingTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsSortingTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsSortingTask.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsSortingTask.onDeleteItem(data);
        }
    });
});
