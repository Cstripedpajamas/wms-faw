layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    var int = self.setInterval(function clock() {
        $.ajax({
            url: Feng.ctxPath + "/redirect/getData",
            success: function (data) {
                $("#LocationId").val(data.LocationId);
                $("#twoOrderId").val(data.twoOrderId);
                $("#twoHUNumber").val(data.twoHUNumber);
                $("#twoLocationId").val(data.twoLocationId);
                $("#threeOrderId").val(data.threeOrderId);
                $("#threeHUNumber").val(data.threeHUNumber);
                $("#threeLocationId").val(data.threeLocationId);
                $("#fourOrderId").val(data.fourOrderId);
                $("#fourScrapNumber").val(data.fourScrapNumber);

                $("#sort_1").val(data.sort_1);
                $("#sort_2").val(data.sort_2);
                $("#sort_3").val(data.sort_3);
                $("#sort_4_1").val(data.sort_4_1);
                $("#sort_4_2").val(data.sort_4_2);
                // console.log(data)
            }
        })
    }, 1000);

    // 门禁 常开
    $("#KeppOpen").click(function () {
        $.ajax({
            url: Feng.ctxPath + "/redirect/KeppOpen",
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("门禁 常开 成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    });

    // 门禁 常闭
    $("#KeppClose").click(function () {
        $.ajax({
            url: Feng.ctxPath + "/redirect/KeppClose",
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("门禁 常闭 成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    });

    // 门禁 发送
    $("#SendDoor").click(function () {
        var doorCodeId = $("#doorCodeId").val();
        console.log(doorCodeId)
        if (doorCodeId.length == 0) {
            Feng.error("请先输入控制代码");
            return false;
        }
        $.ajax({
            url: Feng.ctxPath + "/redirect/SendDoor?"+"doorCodeId="+doorCodeId,
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("门禁代码发送完成");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    });

    // 绿灯报警
    $("#success").click(function () {
        $.ajax({
            url: Feng.ctxPath + "/redirect/alarmMsg?"+"tag="+ "green",
            success: function (data) {
                if (data.code === 200) {
                    Feng.success("下发成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    })

    // 黄灯报警
    $("#warning").click(function () {
        $.ajax({
            url: Feng.ctxPath + "/redirect/alarmMsg?"+"tag="+ "yellow",
            success: function (data) {
                if (data.code === 200) {
                    Feng.success("下发成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    })

    // 红灯报警
    $("#error").click(function () {
        $.ajax({
            url: Feng.ctxPath + "/redirect/alarmMsg?"+"tag="+ "red",
            success: function (data) {
                if (data.code === 200) {
                    Feng.success("下发成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    })

    $("#reset").click(function () {
        $.ajax({
            url: Feng.ctxPath + "/redirect/reset",
            success: function (data) {
                if (data.code === 200) {
                    Feng.success("复位成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    })

    // 关闭格口
    $("#LockClosed").click(function () {
        var LocationId = $("#LocationId").val();
        console.log(LocationId)
        if (LocationId.length == 0) {
            Feng.error("请先发送打开格口请求");
            return false;
        }
        $.ajax({
            url: Feng.ctxPath + "/redirect/LockClosed",
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("关闭格口成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    });

    // 完成入库
    $("#InboundResult").click(function () {
        var twoOrderId = $("#twoOrderId").val();
        if (twoOrderId.length == 0) {
            Feng.error("请先发送入库请求");
            return false;
        }
        $.ajax({
            url: Feng.ctxPath + "/redirect/InboundResult",
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("完成入库成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    });

    // 完成出库
    $("#OutboundResult").click(function () {
        var threeOrderId = $("#threeOrderId").val();
        if (threeOrderId.length == 0) {
            Feng.error("请先发送出库请求");
            return false;
        }
        $.ajax({
            url: Feng.ctxPath + "/redirect/OutboundResult",
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("完成出库成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    });

    // 完成投入
    $("#ScrapCountResult").click(function () {
        var fourOrderId = $("#fourOrderId").val();
        if (fourOrderId.length == 0) {
            Feng.error("请先发送开始投入请求");
            return false;
        }
        $.ajax({
            url: Feng.ctxPath + "/redirect/ScrapCountResult",
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("完成投入成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    });

    $("#StaffInfoOne").click(function () {
        $.ajax({
            url: Feng.ctxPath + "/redirect/StaffInfoOne",
            data: {"userId": $("#userId").val()},
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    });

    $("#StaffInfoTwo").click(function () {
        $.ajax({
            url: Feng.ctxPath + "/redirect/StaffInfoTwo",
            data: {"userId": $("#userId").val()},
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    });

    $("#sendBatchRun").click(function () {
        $.ajax({
            url: Feng.ctxPath + "/redirect/run_batch",
            data: {"userId": $("#userId").val()},
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    });

    // 立库申请入库
    $("#reqSubmit").click(function () {
        let obj = {};
        obj.Id = $("#req_1").val();
        obj.Type = $("#req_2").val();
        obj.BoxType = $("#req_3").val();
        obj.LatticeType = $("#req_4").val();
        obj.Sku = $("#req_5").val();
        obj.Batch = $("#req_6").val();
        obj.Qty = $("#req_7").val();
        obj.Hits = $("#req_8").val();
        obj.BoxCode = $("#req_9").val();
        console.log(obj)
        console.log(JSON.stringify(obj))
        $.ajax({
            contentType:"application/json",
            url: Feng.ctxPath + "/redirect/reqSubmit",
            data: JSON.stringify(obj),
            type:"post",
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    })

// 测试打印
    $("#printTest").click(function () {
       let  obj = {};
       let type =  $("#turnType").val();
       obj.type = type;
       if (type == ""){
           Feng.error("请选择需要打印的类型~");
           return false;
       }
        $.ajax({
            contentType:"application/json",
            url: Feng.ctxPath + "/redirect/printTest",
            type:"post",
            dataType: "json",
            data: JSON.stringify(obj),
            success: function (data) {
                if (data.code == 200) {
                    Feng.success("成功");
                } else {
                    Feng.error(data.message);
                }
            }
        })
    })

// 打印
    $("#print").click(function () {
        let obj = {};
      let end =   $("#endNumber").val();
      let start = $("#startNumber").val();
      let type =  $("#turnType").val();
        obj.type = type;
      obj.start = start;
      obj.end = end;
      console.log(end,start,type)
      if (end == "" || start == "" ||type == ""){
          Feng.error("请填写数字或打印类型");
          return false;
      }
      if (+start > + end){
          Feng.error("起始值不能大于结束值");
          return false;
      }
        var operation = function () {
            $.ajax({
                contentType:"application/json",
                url: Feng.ctxPath + "/redirect/print",
                type:"post",
                data: JSON.stringify(obj),
                dataType: "json",
                success: function (data) {
                    if (data.code == 200) {
                        Feng.success("成功");
                    } else {
                        Feng.error(data.message);
                    }
                }
            })
        };
        Feng.confirm("是否打印?", operation);

    })

});