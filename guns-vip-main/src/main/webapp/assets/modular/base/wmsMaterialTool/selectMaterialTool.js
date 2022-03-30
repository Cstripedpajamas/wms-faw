layui.use(['table', 'admin', 'ax', 'func', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * 工具-物料信息表管理
     */
    var WmsMaterialTool = {
        tableId: "wmsMaterialToolTable"
    };

    /**
     * 初始化表格的列
     */
    WmsMaterialTool.initColumn = function () {
        return [[
            {type: 'radio'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'materialTypeId', hide: true, title: '物料类型ID'},
            {field: 'materialType', title: '物料类型'},
            {field: 'materialName', title: '物料名称'},
            {field: 'materialSku', title: '物料SKU'},
            {field: 'materialSerialNumber', title: '物料编码'},
            {field: 'mUnit', title: '单位'},
            {align: 'center', field: 'materialState', templet: '#materialStateTpl', title: '物料状态'},
            {align: 'center', field: 'storageState', templet: '#storageStateTpl', title: '存放状态'},
            {align: 'center', field: 'storageAddress', templet: '#storageAddressTpl', title: '存放地址'},
            {align: 'center', field: 'dataState', templet: '#statusTpl', title: '数据状态'},
            {field: 'createTime', title: '数据时间'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsMaterialTool.search = function () {
        var queryData = {};
        queryData['materialName'] = $("#materialName").val();
        table.reload(WmsMaterialTool.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsMaterialTool.tableId,
        url: Feng.ctxPath + '/wmsMaterialTool/list?dataState=0',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsMaterialTool.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsMaterialTool.search();
    });

    // 确定按钮点击事件
    $('#btnAdd').click(function () {
        WmsMaterialTool.infoToFath();
    });

    /**
     * 传递选择的数据
     */
    WmsMaterialTool.infoToFath = function () {
        var checkRows = table.checkStatus(WmsMaterialTool.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择工具！");
        } else {
            //给父页面传值
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layui.$('#parentIframe').val(JSON.stringify(checkRows.data));
            parent.layer.close(index);
        }
    };
});
