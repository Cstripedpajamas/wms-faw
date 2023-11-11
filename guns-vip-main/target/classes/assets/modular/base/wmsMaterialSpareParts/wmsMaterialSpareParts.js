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
            {field: 'materialName', sort: true, title: '描述'},
            {field: 'materialSku', sort: true, title: '物料号'},
            {field: 'mBatch', sort: true, title: '批次'},
            {field: 'mUnit', sort: true, title: '基本计量单位'},
            {field: 'minPackageSize', sort: true, title: '规格（最小包装）'},
            {align: 'center', field: 'storageState', sort: true, templet: '#storageStateTpl', title: '存放状态'},
            {align: 'center', field: 'storageAddress', sort: true, templet: '#storageAddressTpl', title: '存放地址'},
            {align: 'center', field: 'dataState', sort: true, templet: '#statusTpl', title: '数据状态'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsMaterialSpareParts.search = function () {
        var queryData = {};
        queryData['materialName'] = $("#materialName").val();
        table.reload(WmsMaterialSpareParts.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsMaterialSpareParts.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/wmsMaterialSpareParts/add';
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    WmsMaterialSpareParts.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/wmsMaterialSpareParts/edit?id=' + data.id;
    };

    /**
     * 导出excel按钮
     */
    WmsMaterialSpareParts.exportExcel = function () {
        var checkRows = table.checkStatus(WmsMaterialSpareParts.tableId);
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
    WmsMaterialSpareParts.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsMaterialSpareParts/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsMaterialSpareParts.tableId);
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
        elem: '#' + WmsMaterialSpareParts.tableId,
        url: Feng.ctxPath + '/wmsMaterialSpareParts/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsMaterialSpareParts.initColumn()
    });
    table.on('checkbox(wmsMaterialSparePartsTable)',function (obj) {
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
        WmsMaterialSpareParts.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsMaterialSpareParts.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsMaterialSpareParts.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsMaterialSpareParts.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsMaterialSpareParts.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsMaterialSpareParts.onDeleteItem(data);
        }
    });

    // 修改状态
    form.on('switch(status)', function (obj) {

        var id = obj.elem.value;
        var checked = obj.elem.checked ? "0" : "1";

        var ajax = new $ax(Feng.ctxPath + "/wmsMaterialSpareParts/editItem", function (data) {
            Feng.success("修改成功!");
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message);
            table.reload(WmsMaterialSpareParts.tableId);
        });
        ajax.set("id", id);
        ajax.set("dataState", checked);
        ajax.start();
    });
});
