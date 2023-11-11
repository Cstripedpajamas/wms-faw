layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 归还申请信息表管理
     */
    var WmsReturnApply = {
        tableId: "wmsReturnApplyTable"
    };

    /**
     * 初始化表格的列
     */
    WmsReturnApply.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {align: 'center',field: 'processNumber', sort: true, title: '流程单号'},
            {align: 'center',field: 'userName', sort: true, title: '人员姓名'},
            {align: 'center',field: 'workTeam', sort: true, title: '所属组织编码'},
            {align: 'center',field: 'sizes', sort: true, title: '规格型号'},
            {align: 'center',field: 'objectstatus', sort: true, title: '使用状态'},
            // {align: 'center',field: 'materialId', sort: true, title: '非消耗品工具信息'},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '描述'},
            {field: 'materialSku', sort: true, title: '物料号'},
            {field: 'plant', sort: true, title: '工厂'},
            {field: 'diBatchNo', sort: true, title: '批次号'},
            {align: 'center',field: 'mNumber', sort: true, title: '物料数量'},
            {align: 'center',field: 'returnReason', sort: true, title: '归还原因'},
            {align: 'center',field: 'approvedBy', sort: true, title: '归还原因'},
            {align: 'center',field: 'dataTime', sort: true, title: '数据时间'},
            {align: 'center',field: 'dataState', sort: true, title: '状态',templet: '#dataState'},
            {toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsReturnApply.search = function () {
        var queryData = {};
        queryData['processNumber'] = $("#condition").val();
        queryData['materialType'] = $("#useMaterialType").val();
        queryData['materialName'] = $("#useMaterialName").val();
        queryData['materialSku'] = $("#useMaterialSku").val();
        queryData['plant'] = $("#plant").val();
        queryData['diBatchNo'] = $("#diBatchNo").val();
        queryData['dataState'] = $("#dataState1").val();
        queryData['userName'] = $("#userName").val();
        queryData['workTeam'] = $("#workTeam").val();
        queryData['sizes'] = $("#sizes").val();
        queryData['objectstatus'] = $("#objectstatus").val();
        table.reload(WmsReturnApply.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsReturnApply.openAddDlg = function () {
        func.open({
            title: '添加归还申请信息表',
            content: Feng.ctxPath + '/wmsReturnApply/add',
            tableId: WmsReturnApply.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsReturnApply.openEditDlg = function (data) {
        func.open({
            title: '修改归还申请信息表',
            content: Feng.ctxPath + '/wmsReturnApply/edit?id=' + data.id,
            tableId: WmsReturnApply.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsReturnApply.exportExcel = function () {
        var checkRows = table.checkStatus(WmsReturnApply.tableId);
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
    WmsReturnApply.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsReturnApply/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsReturnApply.tableId);
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
        elem: '#' + WmsReturnApply.tableId,
        url: Feng.ctxPath + '/wmsReturnApply/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsReturnApply.initColumn()
    });
    table.on('checkbox(wmsReturnApplyTable)',function (obj) {
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
        WmsReturnApply.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsReturnApply.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsReturnApply.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsReturnApply.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsReturnApply.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsReturnApply.onDeleteItem(data);
        }
    });
});
