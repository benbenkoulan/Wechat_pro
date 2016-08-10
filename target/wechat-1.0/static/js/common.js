/**
 * js获取项目根路径
 * @returns
 */
function getRootPath(){
    //获取当前网址，如：http://lz553814861.6655.la/wechat/zcgoods/to_zcgoods
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： wechat/zcgoods/to_zcgoods
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如：http://lz553814861.6655.la
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/wechat
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    //返回http://lz553814861.6655.la/wechat
    return(localhostPaht+projectName);
}