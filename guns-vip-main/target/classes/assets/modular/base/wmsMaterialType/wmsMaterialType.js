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
            {type: 'checkbox'},
            {type: 'button',title: '查看图片',toolbar: "#imageButton",width: 100,field: 'id'},
            {field: 'id', hide: true, title: '记录ID'},
            //{align: 'center',field: 'type',  templet: '#typeTpl',sort: true, title: '类型'},
            {align: 'center',field: 'materialType', sort: true, title: '物料类型'},
            {field: 'materialName', sort: true, title: '描述'},
            {field: 'materialSku', sort: true, title: '物料号'},
            {field: 'sizes', sort: true, title: '规格型号'},
            {field: 'plant', sort: true, title: '工厂'},
            {field: 'mUnit', sort: true, title: '基本计量单位'},
            {field: 'diOperType', sort: true, title: '操作类型'},
            {field: 'diBatchNo', sort: true, title: '批次号'},
            {field: 'diUpdatetime', sort: true, title: '更新时间'},
            {field: 'delflagforclientmtl', sort: true, title: '停用标识'},
            {field: 'industrystnddes', sort: true, title: '制造路线'},
            {field: 'pagefromat', sort: true, title: '可配置物料标识'},
            {field: 'isconfflag', sort: true, title: '可配置物料标识'},
            {field: 'datauser', sort: true, title: '创建对象的用户代码'},
            {field: 'purgrp', sort: true, title: '采购组代码'},
            {field: 'procuretype', sort: true, title: '获取类型'},
            {field: 'specprocuretype', sort: true, title: '特殊获取类型'},
            {field: 'mrpcontroller', sort: true, title: 'MRP控制者'},
            {field: 'valctg', sort: true, title: '估价类别'},
            {field: 'croplant', sort: true, title: '可配置物料'},
            {field: 'spmtlstatus', sort: true, title: 'C标识'},
            {field: 'typeName', sort: true, title: '智能柜柜口'},
            {field: 'sortType', sort: true, title: '分拣类型',templet: '#sortType'},
            {field: 'packageType', sort: true, title: '包装类型'},
            {field: 'packageNumber', sort: true, title: '包装数量'},
            {align: 'center',field: 'turnoverType', sort: true, title: '周转箱类型',templet: '#turnoverType'},
            {align: 'center',field: 'turnoverLatticeType', sort: true, title: '周转箱格口类型',templet: '#turnoverLatticeType'},
            {align: 'center',field: 'label', sort: true, title: 'RFID标识',templet: '#label'},
            {align: 'center',field: 'source', sort: true, title: '来源',templet: '#source'},
            {align: 'center',field: 'dataState', sort: true, templet: '#statusTpl', title: '数据状态'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'right', toolbar: '#tableBar', title: '操作',fixed: 'right',width:180}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsMaterialType.search = function () {
        var queryData = {};
        // queryData['type'] = $("#type").val();
        queryData['materialType'] = $("#materialType").val();
        queryData['materialName'] = $("#materialName").val();
        queryData['materialSku'] = $("#materialSKU").val();
        queryData['plant'] = $("#plant").val();
        queryData['diBatchNo'] = $("#diBatchNo").val();
        queryData['label'] = $("#labelTag").val();
        // queryData['dataState']=$("#dataState").val();
        queryData['sizes'] = $("#sizes").val();
        queryData['sortType'] = $("#sortTypeTag").val();
        table.reload(WmsMaterialType.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsMaterialType.openAddDlg = function () {
        func.open({
            title: '添加物料类型信息表',
            content: Feng.ctxPath + '/wmsMaterialType/add',
            tableId: WmsMaterialType.tableId
        });
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    WmsMaterialType.openEditDlg = function (data) {
        func.open({
            title: '修改物料类型信息表',
            content: Feng.ctxPath + '/wmsMaterialType/edit?id=' + data.id,
            tableId: WmsMaterialType.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsMaterialType.exportExcel = function () {
        var checkRows = table.checkStatus(WmsMaterialType.tableId);
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
    WmsMaterialType.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsMaterialType/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WmsMaterialType.tableId);
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
        elem: '#' + WmsMaterialType.tableId,
        url: Feng.ctxPath + '/wmsMaterialType/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsMaterialType.initColumn()
    });

    table.on('checkbox(wmsMaterialTypeTable)',function (obj) {
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
        WmsMaterialType.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsMaterialType.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsMaterialType.exportExcel();
    });

    // 更新
    $('#upload').click(function () {
        WmsMaterialType.upload();
    });

    /**
     * 更新
     */
    WmsMaterialType.upload = function () {
        Feng.success("更新成功")
    };

    /**
     * 添加物料信息
     * */

    WmsMaterialType.addMaterialInfo = function(data){
        layer.prompt({title: '请输入RFID条码', formType: 0,maxlength:8}, function(pass, index){
            var isNum = /^\d+$/.test(pass.substr(1,pass.length));
            if (pass.startsWith('B') &&  isNum ){

                data['materialSerialNumber'] = pass;
                console.log(data)
                var ajax = new $ax(Feng.ctxPath + "/wmsMaterialTool/addItemFromMaterialType", function (data) {
                    Feng.success("添加成功!");
                }, function (data) {
                    Feng.error("添加失败!" + data.responseJSON.message);
                    table.reload(WmsMaterialType.tableId);
                });
                ajax.set(data);
                ajax.start();

                layer.close(index);
            }
            else {
                Feng.error("您输入的条码不是RFID条码,请检查");



            }

        });
    }
    // 工具条点击事件
    table.on('tool(' + WmsMaterialType.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsMaterialType.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsMaterialType.onDeleteItem(data);
        }else if (layEvent === 'add'){
           WmsMaterialType.addMaterialInfo(data)
        }else if (layEvent === 'showImage'){

            console.log("2222222222222222222");
            console.log(obj.data.id);
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                area: ['auto', 'auto'],
                skin: 'layui-layer-nobg',
                shadeClose: true,
                content: '<img src="'+Feng.ctxPath + "/wmsMaterialType/checkImage?id="+obj.data.id+'" style="width: 500px; height: 500px;">'
                // content: '<img src="' + imageUrl + '" style="max-width: 100%; max-height: 100%;">'
            });
        }
    });

    form.on('switch(status)', function (obj) {

        var id = obj.elem.value;
        var checked = obj.elem.checked ? "0" : "1";

        var ajax = new $ax(Feng.ctxPath + "/wmsMaterialType/editItem", function (data) {
            Feng.success("修改成功!");
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message);
            table.reload(WmsMaterialType.tableId);
        });
        ajax.set("id", id);
        ajax.set("dataState", checked);
        ajax.start();
    });
});
