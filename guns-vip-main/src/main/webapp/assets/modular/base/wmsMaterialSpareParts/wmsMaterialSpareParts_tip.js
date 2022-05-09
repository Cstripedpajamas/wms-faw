layui.use(['table', 'admin', 'ax', 'func', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * 备品备件-物料信息表管理
     */
    var WmsMaterialSpareParts = {
        tableId: "wmsMaterialSparePartsTable"
    };

    /**
     * 初始化表格的列
     */
    WmsMaterialSpareParts.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'materialTypeId', hide: true, sort: true, title: '物料类型ID'},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '物料名称'},
            {field: 'materialSku', sort: true, title: '物料SKU'},
            {field: 'mBatch', sort: true, title: '批次'},
            {field: 'mUnit', sort: true, title: '单位'},
            {field: 'minPackageSize', sort: true, title: '规格（最小包装）'},
            {align: 'center', field: 'storageState', sort: true, templet: '#storageStateTpl', title: '存放状态'},
            {align: 'center', field: 'storageAddress', sort: true, templet: '#storageAddressTpl', title: '存放地址'},
            {align: 'center', field: 'dataState', sort: true, templet: '#statusTpl', title: '数据状态'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsMaterialSpareParts.tableId,
        url: Feng.ctxPath + '/wmsMaterialSpareParts/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsMaterialSpareParts.initColumn()
    });

    // 确定按钮点击事件
    $('#btnAdd').click(function () {
        WmsMaterialSpareParts.infoToFath();
    });
    /**
     * 传递选择的数据
     */
    WmsMaterialSpareParts.infoToFath = function () {
        var checkRows = table.checkStatus(WmsMaterialSpareParts.tableId);
        console.log(checkRows)
        if (checkRows.data.length === 0) {
            Feng.error("请选择补货类型！");
        } else {
            //给父页面传值
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layui.$('#parentIframe').val(JSON.stringify(checkRows.data));
            parent.layer.close(index);
        }
    };



});
