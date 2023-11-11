layui.use(['table', 'admin', 'ax', 'func','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * Ⅱ类柜周转箱信息表管理
     */
    var WmsCabinet2Turnover = {
        tableId: "wmsCabinet2TurnoverTable"
    };

    /**
     * 初始化表格的列
     */
    WmsCabinet2Turnover.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {align: 'center',field: 'turnoverNumber', sort: true, title: '周转箱编号'},
            {align: 'center',field: 'barcode', sort: true, title: '条码信息'},
            {align: 'center',field: 'storageStatus', sort: true, title: '存放状态',templet:'#storageStatus'},
            {align: 'center',field: 'locaRow', sort: true, title: '存放-排'},
            {align: 'center',field: 'locaCol', sort: true, title: '存放-列'},
            {align: 'center',field: 'locaLayer', sort: true, title: '存放-层'},
            {align: 'center',field: 'turnoverState', sort: true, title: '周转箱状态',templet:'#turnoverStates'},
            {align: 'center',field: 'dataState', sort: true, title: '数据状态',templet:'#statusTpl'},
            {align: 'center',field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsCabinet2Turnover.search = function () {
        var queryData = {};
        queryData['barcode'] = $("#condition").val();
        queryData['turnoverState'] = $("#turnoverState1").val();
        queryData['storageStatus'] = $("#storageStatu1").val();
        table.reload(WmsCabinet2Turnover.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsCabinet2Turnover.openAddDlg = function () {
        func.open({
            title: '添加Ⅱ类柜周转箱信息表',
            content: Feng.ctxPath + '/wmsCabinet2Turnover/add',
            tableId: WmsCabinet2Turnover.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsCabinet2Turnover.openEditDlg = function (data) {
        func.open({
            title: '修改Ⅱ类柜周转箱信息表',
            content: Feng.ctxPath + '/wmsCabinet2Turnover/edit?id=' + data.id,
            tableId: WmsCabinet2Turnover.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsCabinet2Turnover.exportExcel = function () {
        var checkRows = table.checkStatus(WmsCabinet2Turnover.tableId);
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
    WmsCabinet2Turnover.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsCabinet2Turnover/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsCabinet2Turnover.tableId);
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
        elem: '#' + WmsCabinet2Turnover.tableId,
        url: Feng.ctxPath + '/wmsCabinet2Turnover/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsCabinet2Turnover.initColumn()
    });
    table.on('checkbox(wmsCabinet2TurnoverTable)',function (obj) {
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
        WmsCabinet2Turnover.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsCabinet2Turnover.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsCabinet2Turnover.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsCabinet2Turnover.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsCabinet2Turnover.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsCabinet2Turnover.onDeleteItem(data);
        }
    });
    // 修改状态
    form.on('switch(status)', function (obj) {

        var id = obj.elem.value;
        var checked = obj.elem.checked ? "0" : "1";

        var ajax = new $ax(Feng.ctxPath + "/wmsCabinet2Turnover/editItem", function (data) {
            Feng.success("修改成功!");
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message);
            table.reload(WmsCabinet2Turnover.tableId);
        });
        ajax.set("id", id);
        ajax.set("dataState", checked);
        ajax.start();
    });
});
