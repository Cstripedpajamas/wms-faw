layui.use(['table', 'admin', 'ax', 'func','form'], function () {
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
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'materialTypeId',  hide: true,sort: true, title: '物料类型ID'},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '描述'},
            {field: 'materialSku', sort: true, title: '物料号'},
            {field: 'sizes', sort: true, title: '规格型号'},
            {field: 'plant', sort: true, title: '工厂'},
            {field: 'diBatchNo', sort: true, title: '批次号'},
            {field: 'materialSerialNumber', sort: true, title: 'RFID编码'},
            {field: 'mUnit', sort: true, title: '基本计量单位'},
            {align: 'center',field: 'materialState', templet: '#materialStateTpl', sort: true, title: '物料状态'},
            {align: 'center',field: 'storageState',  templet: '#storageStateTpl',sort: true, title: '存放状态'},
            {align: 'center',field: 'storageAddress',  templet: '#storageAddressTpl',sort: true, title: '存放地址'},
            {align: 'center',field: 'dataState', templet: '#dataState',sort: true, title: '数据状态'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsMaterialTool.search = function () {
        var queryData = {};
        queryData['materialType'] = $("#materialType").val();
        queryData['materialName'] = $("#materialName").val();
        queryData['materialSku'] = $("#materialSku").val();
        queryData['materialSerialNumber'] = $("#materialSerialNumber").val();
        queryData['plant'] = $("#plant").val();
        queryData['diBatchNo'] = $("#diBatchNo").val();
        queryData['sizes'] = $("#sizes").val();
        queryData['storageState'] = $("#storageState1").val();
        queryData['storageAddress'] = $("#storageAddress").val();
        queryData['dataState'] = $("#dataState").val();
        table.reload(WmsMaterialTool.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsMaterialTool.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/wmsMaterialTool/add';
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsMaterialTool.openEditDlg = function (data) {
        func.open({
            title: '修改物料类型信息表',
            content: Feng.ctxPath + '/wmsMaterialTool/edit?id=' + data.id,
            tableId: WmsMaterialTool.tableId
        });
        // window.location.href = Feng.ctxPath + '/wmsMaterialTool/edit?id=' + data.id;
    };

    /**
     * 导出excel按钮
     */
    WmsMaterialTool.exportExcel = function () {
        var checkRows = table.checkStatus(WmsMaterialTool.tableId);
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
    WmsMaterialTool.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsMaterialTool/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsMaterialTool.tableId);
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
        elem: '#' + WmsMaterialTool.tableId,
        url: Feng.ctxPath + '/wmsMaterialTool/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsMaterialTool.initColumn()
    });
    table.on('checkbox(wmsMaterialToolTable)',function (obj) {
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
        WmsMaterialTool.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsMaterialTool.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsMaterialTool.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WmsMaterialTool.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsMaterialTool.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsMaterialTool.onDeleteItem(data);
        }
    });

    // 修改状态
    form.on('switch(status)', function (obj) {

        var id = obj.elem.value;
        var checked = obj.elem.checked ? "0" : "1";

        var ajax = new $ax(Feng.ctxPath + "/wmsMaterialTool/editItem", function (data) {
            Feng.success("修改成功!");
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message);
            table.reload(WmsMaterialTool.tableId);
        });
        ajax.set("id", id);
        ajax.set("dataState", checked);
        ajax.start();
    });
});
