
// import {openAuth}  from './dingtalk-design-libs/biz/openAuth';
layui.use(['form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;



    // Ⅰ类柜和备品备件申请
    $("#toolApplyPage").click(function () {

        window.location.href="http://10.43.3.89:8099/faw/apply/toolApply";
    });

    // Ⅱ类柜申请
    $("#spaceApplyPage").click(function () {
        window.location.href="http://10.43.3.89:8099/faw/apply/spareApply";
    });

    // Ⅰ类柜归还申请
    $("#toolReturnApplyPage").click(function () {
        window.location.href="http://10.43.3.89:8099/faw/apply/toolReturnApply";
    });

    // 技术发展部申请
    $("#technologyApplyPage").click(function () {
        window.location.href="http://10.43.3.89:8099/faw/apply/technologyApply";
    });

    // 技术发展部申请二类柜
    $("#technologyApplyIIPage").click(function () {
        window.location.href="http://10.43.3.89:8099/faw/apply/technologyApplyII";
    });

//     openAuth({
//         clientId:'dingotsmhynm0kuntqmp', // 应用ID(唯一标识)
//         corpId:'dinga8bdd73a3a19347eee0f45d8e4f7c288', // 当前组织的corpId
//         rpcScope:'Calendar.Event.Write,Calendar.Event.Read,Contact.User.Read',
//         fieldScope:'Contact.User.mobile',
//         type:0 // 0 标识授权个人信息；1 标识授权组织信息
//     }).then((res)=>{
// // 处理返回数据
//         alert(res);
//     })
});