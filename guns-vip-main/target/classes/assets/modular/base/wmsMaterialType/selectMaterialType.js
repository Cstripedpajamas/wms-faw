layui.use(['table', 'admin', 'ax', 'func', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * 物料类型信息表管理
     */
    var WmsMaterialType = {
        tableId: "wmsMaterialTypeTable"
    };

    /**
     * 初始化表格的列
     */
    WmsMaterialType.initColumn = function () {
        return [[
            {type: 'radio'},
            {field: 'id', hide: true, title: '记录ID'},
            // {align: 'center', field: 'type', templet: '#typeTpl', title: '类型'},
            {align: 'center', field: 'materialType', title: '物料类型'},
            {field: 'materialName', title: '描述'},
            {field: 'materialSku', title: '物料号'},
            {field: 'mUnit', title: '基本计量单位'},
            {field: 'typeName', title: '格口类型'},
            {field: 'label', title: 'RFID标识',templet: '#label'},
            {align: 'center', field: 'dataState', templet: '#statusTpl', title: '数据状态'},
            {field: 'createTime', title: '数据时间'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsMaterialType.search = function () {
        var queryData = {};
        queryData['materialName'] = $("#materialName").val();
        queryData['materialSku'] = $("#materialSku").val();
        queryData['dataState'] = '0';
        queryData['type'] = $("#type").val();
        table.reload(WmsMaterialType.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsMaterialType.tableId,
        // url: Feng.ctxPath + '/wmsMaterialType/list?dataState=0&type=' + $("#type").val(),

        url: Feng.ctxPath + '/wmsMaterialType/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsMaterialType.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsMaterialType.search();
    });

    // 确定按钮点击事件
    $('#btnAdd').click(function () {
        WmsMaterialType.infoToFath();
    });

    /**
     * 传递选择的数据
     */
    WmsMaterialType.infoToFath = function () {
        var checkRows = table.checkStatus(WmsMaterialType.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择物料类型！");
        } else {
            //给父页面传值
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layui.$('#parentIframe').val(JSON.stringify(checkRows.data));
            parent.layer.close(index);
        }
    };
});
