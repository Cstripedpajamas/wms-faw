layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    var pushDataId=0;
    /**
     * 采购订单信息表管理
     */
    var WmsPurchaseOrderInfo = {
        tableId: "wmsPurchaseOrderInfoTable"
    };

    /**
     * 初始化表格的列
     */
    WmsPurchaseOrderInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'client', sort: true, title: '应用环境'},
            {field: 'purchasereqno', sort: true, title: '采购申请号'},
            {field: 'itemno', sort: true, title: '采购申请行项号'},
            {field: 'purdocno', sort: true, title: '采购凭证号'},
            {field: 'purdocitemno', sort: true, title: '采购凭证行项'},
            {field: 'purNumber', sort: true, title: '采购单号'},//订单行项ID/发货单ID
            // {align: 'center', field: 'type', templet: '#typeTpl', sort: true, title: '类型'},
            //{field: 'materialTypeId',  hide: true,sort: true, title: '物料类型ID'},
            {field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '描述'},
            {field: 'materialSku', sort: true, title: '物料号'},
            {field: 'sizecol', sort: true, title: '规格型号'},
            {field: 'mUnit', sort: true, title: '基本计量单位'},
            {field: 'mNumber', sort: true, title: '数量'},
            {field: 'unitdes', sort: true, title: '计量单位描述'},
            {field: 'arrivalTime', sort: true, title: '交货日程日期'},
            {align: 'center', field: 'arrivalState', templet: '#arrivalStateTpl', sort: true, title: '到货状态'},
            //{field: 'purstockbillid', sort: true, title: ''},
            {field: 'buyliststrdes', sort: true, title: '采购凭证类型描述'},
            {field: 'createdby', sort: true, title: '创建人'},
            {field: 'createddate', sort: true, title: '创建日期'},
            {field: 'docDate', sort: true, title: '凭证日期'},
            {field: 'estimatedpriceindic', sort: true, title: '估价标识'},
            {field: 'matBrand', sort: true, title: '材料牌号'},
            {field: 'netvalue', sort: true, title: '订单的净价总值'},
            {field: 'ordpriceunit', sort: true, title: '采购价格计量单位'},
            {field: 'ordtype', sort: true, title: '采购凭证类型'},
            {field: 'plant', sort: true, title: '工厂'},
            {field: 'price', sort: true, title: '价格'},
            {field: 'promotion', sort: true, title: '生产厂家/品牌'},
            {field: 'proposerdesc', sort: true, title: '申请人描述'},
            {field: 'proposerid', sort: true, title: '申请人ID'},
            {field: 'purgrp', sort: true, title: '采购组'},
            {field: 'purgrpdesc', sort: true, title: '采购组描述'},
            {field: 'purOrg', sort: true, title: '采购组织'},
            {field: 'purdocheaderid', sort: true, title: '订单头ID'},
            {field: 'remark', sort: true, title: '备注'},
            {field: 'remark1', sort: true, title: '备注1'},
            {field: 'storelocation', sort: true, title: '存储地点'},
            {field: 'vendordesc', sort: true, title: '供应商描述'},
            {field: 'vendorno', sort: true, title: '供应商代码'},
            {field: 'phone', sort: true, title: '创建人电话'},
            {field: 'reqphone', sort: true, title: '采购申请创建人电话'},
            {field: 'plantdes', sort: true, title: '工厂描述'},
            {field: 'storelocationdes', sort: true, title: '存储地点描述'},
            {field: 'createdbydesc', sort: true, title: '创建人描述'},
            {field: 'statedesc', sort: true, title: '状态描述'},
            {field: 'diOperType', sort: true, title: '操作类型'},
            {field: 'diBatchNo', sort: true, title: '批次号'},
            {field: 'diUpdatetime', sort: true, title: '更新时间'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsPurchaseOrderInfo.search = function () {
        var queryData = {};
        queryData['purNumber'] = $("#purNumber").val();
        queryData['purdocitemno'] = $("#purdocitemno").val();
        queryData['purdocno'] = $("#purdocno").val();
        queryData['sizecol'] = $("#sizecol").val();
        queryData['arrivalState']=$("#arrivalState").val();
        queryData['storelocation'] = $("#storelocation").val();
        queryData['vendordesc'] = $("#vendordesc").val();
        queryData['materialSku'] = $("#materialSku").val();
        queryData['materialName'] = $("#materialName").val();
        queryData['diBatchNo'] = $("#diBatchNo").val();
        queryData['purchasereqno'] = $("#purchasereqno").val();
        table.reload(WmsPurchaseOrderInfo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsPurchaseOrderInfo.openAddDlg = function () {
        window.location.href = Feng.ctxPath + '/wmsPurchaseOrderInfo/add';
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    WmsPurchaseOrderInfo.openEditDlg = function (data) {
        window.location.href = Feng.ctxPath + '/wmsPurchaseOrderInfo/edit?id=' + data.id;
    };

    /**
     * 导出excel按钮
     */
    WmsPurchaseOrderInfo.exportExcel = function () {
        var checkRows = table.checkStatus(WmsPurchaseOrderInfo.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 手动推送接口数据按钮
     */
    WmsPurchaseOrderInfo.pushData = function () {
        var operation = function () {
            console.log(pushDataId);
            var ajax = new $ax(Feng.ctxPath + "/wmsPurchaseOrderInfo/pushDataToErp", function (data) {
                Feng.success("接口推送成功!");
            }, function (data) {
                Feng.error("接口推送失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", pushDataId);
            ajax.start();
        };
        Feng.confirm("是否手动推送接口信息?", operation);

    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    WmsPurchaseOrderInfo.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsPurchaseOrderInfo/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsPurchaseOrderInfo.tableId);
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
        elem: '#' + WmsPurchaseOrderInfo.tableId,
        url: Feng.ctxPath + '/wmsPurchaseOrderInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsPurchaseOrderInfo.initColumn()
    });

    table.on('checkbox(wmsPurchaseOrderInfoTable)',function (obj) {
        var isChecked=obj.checked;
        var tr=obj.tr;
        if (isChecked){
            tr.addClass('layui-table-click');
        }else{
            tr.removeClass('layui-table-click');
        }

        // 获取选中行的值
        var selectedRows = table.checkStatus('wmsPurchaseOrderInfoTable').data;
        pushDataId= selectedRows[0].id;
        // for (var i = 0; i < selectedRows.length; i++) {
        //     var row = selectedRows[i];
        //     console.log(row);
        //     console.log(row.purchasereqno);
        // }

    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WmsPurchaseOrderInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsPurchaseOrderInfo.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsPurchaseOrderInfo.exportExcel();
    });

    // 手动推送接口数据
    $('#btnPushData').click(function () {
        WmsPurchaseOrderInfo.pushData();
    });


    // 更新
    $('#upload').click(function () {
        WmsPurchaseOrderInfo.upload();
    });

    /**
     * 更新
     */
    WmsPurchaseOrderInfo.upload = function () {
        Feng.success("更新成功")
    };

    // 工具条点击事件
    table.on('tool(' + WmsPurchaseOrderInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsPurchaseOrderInfo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsPurchaseOrderInfo.onDeleteItem(data);
        }
    });
});
