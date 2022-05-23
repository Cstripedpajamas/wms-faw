layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * Ⅱ类柜盘点任务信息表管理
     */
    var WmsCabinet2CheckTask = {
        tableId: "wmsCabinet2CheckTaskTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet2CheckTask.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {align: 'center',field: 'taskNumber', sort: true, title: '任务编号'},
            {align: 'center',field: 'stockId', sort: true, title: '库位信息ID'},
            {align: 'center',field: 'locaNumber', sort: true, title: '库位编号'},
            {align: 'center',field: 'turnoverId', sort: true, title: '周转箱信息ID'},
            {align: 'center',field: 'operator', sort: true, title: '操作人'},
            {align: 'center',field: 'operationTime', sort: true, title: '操作时间'},
            {align: 'center',field: 'taskState', sort: true, title: '任务状态',templet:'#taskState'},
            {align: 'center',field: 'createTime', sort: true, title: '创建时间'},
            {align: 'center',field: 'updateTime', sort: true, title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作',templet:'#tableBar'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet2CheckTask.search = function () {
        var queryData = {};
        queryData['taskNumber'] = $("#condition").val();
        table.reload(WmsCabinet2CheckTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet2CheckTask.openAddDlg = function () {
        func.open({
            title: '添加Ⅱ类柜盘点任务信息表',
            content: Feng.ctxPath + '/wmsCabinet2CheckTask/add',
            tableId: WmsCabinet2CheckTask.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet2CheckTask.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅱ类柜盘点任务信息表',
            content: Feng.ctxPath + '/wmsCabinet2CheckTask/edit?id=' + data.id,
            tableId: WmsCabinet2CheckTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet2CheckTask.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet2CheckTask.tableId);
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
    WmsCabinet2CheckTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/requestMsg/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet2CheckTask.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    /***
     * 盘点业务
     * */
    WmsCabinet2CheckTask.Inventory = function (data) {
            var ajax = new $ax(Feng.ctxPath + "/requestMsg/Inventory", function (data) {
                if (data.code == 200){
                    Feng.success(data.data)
                    table.reload(WmsCabinet2CheckTask.tableId);
                }
                else {
                    Feng.error(data.message)
                }
            }, function (data) {
                Feng.error("失败!" + data.responseJSON.message + "!");
            });
            ajax.set(data);
            ajax.start();
    };
    WmsCabinet2CheckTask.finish = function (data) {
        console.log(data)
        var ajax = new $ax(Feng.ctxPath + "/requestMsg/InventoryFinish", function (data) {
            if (data.code == 200){
                Feng.success("开始入库")
                table.reload(WmsCabinet2CheckTask.tableId);
            }
            else {
                Feng.error(data.message)
            }
        }, function (data) {
            Feng.error("失败!" + data.responseJSON.message + "!");
        });
        ajax.set(data);
        ajax.start();
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsCabinet2CheckTask.tableId,
        url: Feng.ctxPath + '/wmsCabinet2CheckTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet2CheckTask.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsCabinet2CheckTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet2CheckTask.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet2CheckTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet2CheckTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet2CheckTask.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet2CheckTask.onDeleteItem(data);
        }else if (layEvent === 'inventory'){
            WmsCabinet2CheckTask.Inventory(data);
        }else if(layEvent === 'finish'){
            WmsCabinet2CheckTask.finish(data);
        }
    });
});
