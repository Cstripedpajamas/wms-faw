layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 盘点差异记录表管理
     */
    var WmsCheckDifferenceRecordInfo = {
        tableId: "wmsCheckDifferenceRecordInfoTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCheckDifferenceRecordInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {align: 'center', field: 'checkWarehouse', templet: '#checkWarehouseTpl', sort: true, title: '盘点仓库'},
            {field: 'checkTask', sort: true, title: '盘点任务'},
            {field: 'locationInfo', sort: true, title: '库位信息'},
            {align: 'differenceType', field: 'differenceType', templet: '#differenceTypeTpl', sort: true, title: '差异类型'},
            {field: 'differenceDescribe', sort: true, title: '差异描述'},
            {field: 'operator', sort: true, title: '操作人'},
            {field: 'operationTime', sort: true, title: '操作时间'},
            {field: 'createTime', sort: true, title: '数据时间'},
            // {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCheckDifferenceRecordInfo.search = function () {
        var queryData = {};
        queryData['condition'] = $("#condition").val();
        queryData['locationInfo'] = $("#locationInfo").val();
        queryData['differenceDescribe'] = $("#differenceDescribe").val();
        queryData['differenceType'] = $("#differenceType").val();
        queryData['checkWarehouse'] = $("#checkWarehouse").val();
        table.reload(WmsCheckDifferenceRecordInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCheckDifferenceRecordInfo.openAddDlg = function () {
        func.open({
            title: '添加盘点差异记录表',
            content: Feng.ctxPath + '/wmsCheckDifferenceRecordInfo/add',
            tableId: WmsCheckDifferenceRecordInfo.tableId
        });
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    WmsCheckDifferenceRecordInfo.openEditDlg = function (data) {
        func.open({
            title: '修改盘点差异记录表',
            content: Feng.ctxPath + '/wmsCheckDifferenceRecordInfo/edit?id=' + data.id,
            tableId: WmsCheckDifferenceRecordInfo.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCheckDifferenceRecordInfo.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCheckDifferenceRecordInfo.tableId);
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
    WmsCheckDifferenceRecordInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCheckDifferenceRecordInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCheckDifferenceRecordInfo.tableId);
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
        elem: '#' + WmsCheckDifferenceRecordInfo.tableId,
        url: Feng.ctxPath + '/wmsCheckDifferenceRecordInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCheckDifferenceRecordInfo.initColumn()
    });
    table.on('checkbox(wmsCheckDifferenceRecordInfoTable)',function (obj) {
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
        WmsCheckDifferenceRecordInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCheckDifferenceRecordInfo.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCheckDifferenceRecordInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCheckDifferenceRecordInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCheckDifferenceRecordInfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCheckDifferenceRecordInfo.onDeleteItem(data);
        }
    });
});
