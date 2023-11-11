layui.use(['table', 'admin', 'ax', 'func', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * 人员信息表管理
     */
    var WmsUser = {
        tableId: "wmsUserTable"
    };

    /**
     * 初始化表格的列
     */
    WmsUser.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '记录ID'},
            {field: 'accountcode', sort: true, title: '员工IAM账号（IAMID）'},
            {field: 'serialNumber', sort: true, title: '员工ID'},
            {field: 'uPwd', sort: true, title: '访问密码'},
            {field: 'userName', sort: true, title: '用户姓名'},
            {field: 'workTeam', sort: true, title: '所属组织编码'},
            {field: 'jobResponsibility', sort: true, title: '岗位'},
            {field: 'phoneNumber', sort: true, title: '手机号'},
            {field: 'emailaddress', sort: true, title: '电子邮件地址'},
            {field: 'classofpositions', sort: true, title: '职级'},
            {field: 'fawclaofpos', sort: true, title: '解放职级'},
            {field: 'directorid', sort: true, title: '直接上级ID'},
            {field: 'directorname', sort: true, title: '直接上级姓名'},
            {field: 'objectstatus', sort: true, title: '员工状态'},
            {field: 'mdmtype', sort: true, title: '主数据人员类别'},
            {field: 'deptlevel', sort: true, title: '外部人员解放部门编码'},
            {field: 'idInfo', sort: true, title: '识别信息码'},
            {align: 'center', field: 'userTypeEx', sort: true, title: '人员类型'},
            {align: 'center', field: 'dataState', sort: true, templet: '#statusTpl', title: '数据状态'},
            {field: 'createTime', sort: true, title: '数据时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WmsUser.search = function () {
        var queryData = {};
        queryData['userName'] = $("#userName").val();
        queryData['serialNumber'] = $("#serialNumber").val();
        queryData['workTeam'] = $("#workTeam").val();
        queryData['objectstatus'] = $("#objectstatus").val();
        switch ($("#center").val()) {
            case "管理员":
                queryData['userType'] ="A";
                break;
            case "维修人员":
                queryData['userType'] ="B";
                break;
            case "操作员":
                queryData['userType'] ="C";
                break;
        }
        table.reload(WmsUser.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WmsUser.openAddDlg = function () {
        func.open({
            title: '添加人员信息',
            content: Feng.ctxPath + '/wmsUser/add',
            tableId: WmsUser.tableId
        });
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    WmsUser.openEditDlg = function (data) {
        func.open({
            title: '修改人员信息',
            content: Feng.ctxPath + '/wmsUser/edit?id=' + data.id,
            tableId: WmsUser.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    WmsUser.exportExcel = function () {
        var checkRows = table.checkStatus(WmsUser.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };
    /**
     * 更新
     */
    WmsUser.upload = function () {
        Feng.success("更新成功")
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    WmsUser.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/wmsUser/delete", function (data) {
                if (data.code == 200) {
                    Feng.success("删除成功!");
                    table.reload(WmsUser.tableId);
                } else {
                    Feng.error(data.message);
                }
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.set("serialNumber", data.serialNumber);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + WmsUser.tableId,
        url: Feng.ctxPath + '/wmsUser/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WmsUser.initColumn()
    });

    table.on('checkbox(wmsUserTable)',function (obj) {
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
        WmsUser.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        WmsUser.openAddDlg();
    });

    // 导出excel
    $('#btnExp').click(function () {
        WmsUser.exportExcel();
    });

    // 更新
    $('#upload').click(function () {
        WmsUser.upload();
    });

    // 工具条点击事件
    table.on('tool(' + WmsUser.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WmsUser.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WmsUser.onDeleteItem(data);
        }
    });

    // 修改状态
    form.on('switch(status)', function (obj) {

        var id = obj.elem.value;
        var checked = obj.elem.checked ? "0" : "1";

        var ajax = new $ax(Feng.ctxPath + "/wmsUser/editItem", function (data) {
            Feng.success("修改成功!");
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message);
            table.reload(WmsUser.tableId);
        });
        ajax.set("id", id);
        ajax.set("dataState", checked);
        ajax.start();
    });
});
