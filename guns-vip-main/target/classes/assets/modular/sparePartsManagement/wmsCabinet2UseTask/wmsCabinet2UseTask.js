layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅱ类柜领用任务信息表管理
     */
    var WmsCabinet2UseTask = {
        tableId: "wmsCabinet2UseTaskTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet2UseTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {align: 'center',field: 'taskNumber', sort: true, title: '任务编号'},
            // {align: 'center',field: 'useRequestId', sort: true, title: '领用申请ID'},
            // {align: 'center',field: 'taskNumber', sort: true, title: '申请任务编号'},
            // {align: 'center',field: 'processNumber', sort: true, title: '流程单号'},
            // {align: 'center',field: 'sMaterialTypeId', sort: true, title: '报废-物料类型ID'},
            // {align: 'center',field: 'sMaterialId', sort: true, title: '物料信息ID'},
            {field: 'sMaterialType', sort: true, title: '报废物料类型'},
            {field: 'smaterialName', sort: true, title: '报废物料描述'},
            {field: 'smaterialSku', sort: true, title: '报废物料号'},
            {field: 'materialSize', sort: true, title: '报废规格型号'},
            {field: 'useMaterialSize', sort: true, title: '领用规格型号'},
            {field: 'userName', sort: true, title: '操作人姓名'},
            {field: 'workTeam', sort: true, title: '所属组织编码'},
            {align: 'center',field: 'sNumber', sort: true, title: '报废数量'},
            // {align: 'center',field: 'useMaterialTypeId', sort: true, title: '领用-物料类型ID'},
            // {align: 'center',field: 'useMaterialId', sort: true, title: '物料信息ID'},
            {field: 'useMaterialType', sort: true, title: '领用物料类型'},
            {field: 'useMaterialName', sort: true, title: '领用物料描述'},
            {field: 'useMaterialSku', sort: true, title: '领用物料号'},
            {align: 'center',field: 'useNumber', sort: true, title: '领用数量'},
            // {align: 'center',field: 'stockId', sort: true, title: '库存信息ID'},
            {align: 'center',field: 'locaNumber', sort: true, title: '库位编号'},
            {align: 'center',field: 'operator', sort: true, title: '操作人'},
            // {align: 'center',field: 'operationTime', sort: true, title: '操作时间'},
            {align: 'center',field: 'taskState', sort: true, title: '任务状态',templet:'#taskState'},
            {align: 'center',field: 'interfaceState', sort: true, title: '接口状态',templet:'#interfaceState'},
            {align: 'center',field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center',field: 'updateTime', sort: true, title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet2UseTask.search = function () {
        var queryData = {};
        queryData['taskNumber'] = $("#condition").val();
        queryData['taskState'] = $("#taskState1").val();
        queryData['smaterialName'] = $("#smaterialName").val();
        queryData['materialSize'] = $("#materialSize").val();
        queryData['useMaterialSize'] = $("#useMaterialSize").val();
        queryData['smaterialSku'] = $("#smaterialSku").val();
        queryData['useMaterialSku'] = $("#useMaterialSku").val();
        queryData['useMaterialName'] = $("#useMaterialName").val();
        queryData['userName'] = $("#userName").val();
        queryData['workTeam'] = $("#workTeam").val();
        table.reload(WmsCabinet2UseTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet2UseTask.openAddDlg = function () {
        func.open({
            title: '添加Ⅱ类柜领用任务信息表',
            content: Feng.ctxPath + '/wmsCabinet2UseTask/add',
            tableId: WmsCabinet2UseTask.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet2UseTask.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅱ类柜领用任务信息表',
            content: Feng.ctxPath + '/wmsCabinet2UseTask/edit?id=' + data.id,
            tableId: WmsCabinet2UseTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet2UseTask.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet2UseTask.tableId);
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
    WmsCabinet2UseTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCabinet2UseTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet2UseTask.tableId);
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
        elem: '#' + WmsCabinet2UseTask.tableId,
        url: Feng.ctxPath + '/wmsCabinet2UseTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet2UseTask.initColumn()
    });
    table.on('checkbox(wmsCabinet2UseTaskTable)',function (obj) {
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
        WmsCabinet2UseTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet2UseTask.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet2UseTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet2UseTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet2UseTask.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet2UseTask.onDeleteItem(data);
        }
    });
});
