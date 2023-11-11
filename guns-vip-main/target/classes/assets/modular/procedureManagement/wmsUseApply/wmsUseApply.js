layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    var useApplyId=0;
    /**
     * 领用申请信息表管理
     */
    var WmsUseApply = {
        tableId: "wmsUseApplyTable"
    };

    /**
     * 初始化表格的列
     */
    WmsUseApply.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'processNumber', sort: true, title: '流程单号',align: "center"},
            {field: 'operator', sort: true, title: '人员信息',align: "center"},
            // {field: 'materialId', sort: true, title: '物料信息',align: "center"},
            {field: 'useMaterialType', sort: true, title: '领用物料类型'},
            {field: 'useMaterialName', sort: true, title: '领用物料描述'},
            {field: 'useMaterialSku', sort: true, title: '领用物料号'},
            {field: 'useplant', sort: true, title: '领用工厂'},
            {field: 'usediBatchNo', sort: true, title: '领用批次号'},
            {field: 'mNumber', sort: true, title: '物料数量',align: "center"},
            {field: 'userName', sort: true, title: '申请人员姓名'},
            {field: 'workTeam', sort: true, title: '所属组织编码'},
            {field: 'scrapMaterialSize', sort: true, title: '报废物料规格型号'},
            {field: 'materialSize', sort: true, title: '领用物料规格型号'},
            {field: 'approveduserName', sort: true, title: '审批人姓名'},
            {field: 'approvedserialNumber', sort: true, title: '审批人工号',align: "center"},
            // {field: 'scrapMaterialId', sort: true, title: '报废物料类型ID',align: "center"},
            {field: 'sMaterialType', sort: true, title: '报废物料类型'},
            {field: 'smaterialName', sort: true, title: '报废物料描述'},
            {field: 'smaterialSku', sort: true, title: '报废物料号'},
            {field: 'splant', sort: true, title: '报废工厂'},
            {field: 'usediBatchNo', sort: true, title: '报废批次号'},
            {field: 'scrapNum', sort: true, title: '报废数量',align: "center"},
            {field: 'processType', sort: true, title: '申请类型',templet:'#processType',align: "center"},
            {field: 'processReason', sort: true, title: '申请原因',align: "center"},
            {field: 'approvedBy', sort: true, title: '审批人',align: "center"},
            {field: 'dataTime', sort: true, title: '数据时间',align: "center"},
            {field: 'dataState', sort: true, title: '状态',templet: '#dataState',align: "center"},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsUseApply.search = function () {
        var queryData = {};
        queryData['processNumber'] = $("#condition").val();
        queryData['operator'] = $("#operator").val();
        queryData['useMaterialType'] = $("#useMaterialType").val();
        queryData['useMaterialName'] = $("#useMaterialName").val();
        queryData['useMaterialSku'] = $("#useMaterialSku").val();
        queryData['useplant'] = $("#useplant").val();
        queryData['usediBatchNo'] = $("#usediBatchNo").val();
        queryData['dataState'] = $("#dataState1").val();
        queryData['userName'] = $("#userName").val();
        queryData['workTeam'] = $("#workTeam").val();
        queryData['scrapMaterialSize'] = $("#scrapMaterialSize").val();
        queryData['materialSize'] = $("#materialSize").val();
        queryData['approveduserName'] = $("#approveduserName").val();
        queryData['approvedserialNumber'] = $("#approvedserialNumber").val();
        table.reload(WmsUseApply.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsUseApply.openAddDlg = function () {
        func.open({
            title: '添加领用申请信息表',
            content: Feng.ctxPath + '/wmsUseApply/add',
            tableId: WmsUseApply.tableId
        });
    };

    /**
    * 点击编辑
    *
    * @param data 点击按钮时候的行数据
    */
    WmsUseApply.openEditDlg = function (data) {
        func.open({
            title: '修改领用申请信息表',
            content: Feng.ctxPath + '/wmsUseApply/edit?id=' + data.id,
            tableId: WmsUseApply.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsUseApply.exportExcel = function () {
        var checkRows = table.checkStatus(WmsUseApply.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 手动推送接口数据按钮
     */
    WmsUseApply.pushData = function () {
        var operation = function () {
            console.log(useApplyId);
            var ajax = new $ax(Feng.ctxPath + "/wmsUseApply/pushDataToErp", function (data) {
                Feng.success("接口推送成功!");
            }, function (data) {
                Feng.error("接口推送失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", useApplyId);
            ajax.start();
        };
        Feng.confirm("是否手动推送接口信息?", operation);

    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    WmsUseApply.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsUseApply/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsUseApply.tableId);
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
        elem: '#' + WmsUseApply.tableId,
        url: Feng.ctxPath + '/wmsUseApply/list2',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsUseApply.initColumn()
    });
    table.on('checkbox(wmsUseApplyTable)',function (obj) {
        var isChecked=obj.checked;
        var tr=obj.tr;
        if (isChecked){
            tr.addClass('layui-table-click');
        }else{
            tr.removeClass('layui-table-click');
        }
        // 获取选中行的值
        var selectedRows = table.checkStatus('wmsUseApplyTable').data;
        useApplyId= selectedRows[0].id;

    });
    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsUseApply.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsUseApply.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsUseApply.exportExcel();
    });

    // 手动推送
    $('#btnFeedback').click(function () {
        WmsUseApply.pushData();
    });

    // 工具条点击事件
    table.on('tool(' + WmsUseApply.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsUseApply.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsUseApply.onDeleteItem(data);
        }
    });
});
