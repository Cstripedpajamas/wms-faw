layui.use(['table', 'admin', 'ax', 'func','form','upload'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;
    var upload = layui.upload;

    /**
     * 立库-周转箱信息表管理
     */
    var WmsWarehouseTurnover = {
        tableId: "wmsWarehouseTurnoverTable"
    };

    /**
     * 初始化表格的列
     */
    WmsWarehouseTurnover.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'turnoverNumber', sort: true, title: '周转箱编号',minWidth: 160},
            {field: 'turnoverType', sort: true, title: '周转箱类型',templet: '#turnoverTypeTpl',minWidth: 120},
            {field: 'barcode', sort: true, title: '条码信息',minWidth: 120},
            {field: 'storageStatus', sort: true, title: '存放状态',templet: '#storageStatusTpl',minWidth: 120},
            {field: 'locaRow', sort: true, title: '存放-排'},
            {field: 'locaCol', sort: true, title: '存放-列'},
            {field: 'locaLayer', sort: true, title: '存放-层'},
            {field: 'turnoverState', sort: true, title: '周转箱状态',templet: '#turnoverStateTpl',minWidth: 120},
            {field: 'turnoverMouthQuantity', sort: true, title: '格口数量',minWidth: 120},
            {field: 'affWarehouse', sort: true, title: '归属仓库',minWidth: 120,templet: '#affWarehouse'},
            {field: 'dataState', sort: true, title: '数据状态',templet: '#dataStateTpl'},
            {field: 'createTime', sort: true, title: '数据时间',minWidth: 160},
            {align: 'center', toolbar: '#tableBar', title: '操作',minWidth: 160}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsWarehouseTurnover.search = function () {
        var queryData = {};
        queryData['barcode'] = $("#barcode").val();
        queryData['affWarehouse'] = $("#affWarehouse2").val();
        console.log( $("#affWarehouse2").val());
        table.reload(WmsWarehouseTurnover.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsWarehouseTurnover.openAddDlg = function () {
        func.open({
            title: '添加立库-周转箱信息表',
            content: Feng.ctxPath + '/wmsWarehouseTurnover/add',
            tableId: WmsWarehouseTurnover.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsWarehouseTurnover.openEditDlg = function (data) {
        func.open({
            title: '修改立库-周转箱信息表',
            content: Feng.ctxPath + '/wmsWarehouseTurnover/edit?id=' + data.id,
            tableId: WmsWarehouseTurnover.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsWarehouseTurnover.exportExcel = function () {
        var checkRows = table.checkStatus(WmsWarehouseTurnover.tableId);
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
    WmsWarehouseTurnover.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseTurnover/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsWarehouseTurnover.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    /**
     * 点击详情
     *
     * @param data 点击按钮时候的行数据
     */
    WmsWarehouseTurnover.onDetailItem = function (data) {
        func.open({
            title: '周转箱绑定货物',
            content: Feng.ctxPath + '/wmsWarehouseTurnoverBind/details?id=' + data.id,
            tableId: WmsWarehouseTurnover.tableId
        });
    };

    // 模板下载
    $("#btnTemplate").click(function () {
        window.location.href = Feng.ctxPath + "/wmsWarehouseTurnover/exportOut"
    });

    // 导入excel
    var uploadInst = upload.render({
        elem: '#btnExp2'
        ,url: Feng.ctxPath + '/wmsWarehouseTurnover/exportIn'
        ,accept: 'file'
        ,done: function (res) {
            Feng.success("导入成功~");
            table.reload(WmsWarehouseTurnover.tableId, {url: Feng.ctxPath + "/wmsWarehouseTurnover/list"});
        }
        ,error: function () {
            //请求异常回调
        }
    });

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsWarehouseTurnover.tableId,
        url: Feng.ctxPath + '/wmsWarehouseTurnover/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsWarehouseTurnover.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsWarehouseTurnover.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsWarehouseTurnover.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsWarehouseTurnover.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsWarehouseTurnover.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsWarehouseTurnover.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsWarehouseTurnover.onDeleteItem(data);
        } else if (layEvent === 'detail') {
            WmsWarehouseTurnover.onDetailItem(data);
        }
    });

    //监听指定开关
    form.on('switch(dataStateSwitch)', function(data){
        var ajax = new $ax(Feng.ctxPath + "/wmsWarehouseTurnover/editItem");
        ajax.set("id", data.value);
        ajax.set("dataState", this.checked ? '1' : '2');
        ajax.start();
    });
});
