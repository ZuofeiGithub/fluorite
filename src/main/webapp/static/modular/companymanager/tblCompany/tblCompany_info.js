/**
 * 初始化 企业管理详情对话框
 */
var TblCompanyInfoDlg = {
    tblCompanyInfoData : {}
};

/**
 * 清除数据
 */
TblCompanyInfoDlg.clearData = function() {
    this.tblCompanyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblCompanyInfoDlg.set = function(key, val) {
    this.tblCompanyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblCompanyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TblCompanyInfoDlg.close = function() {
    parent.layer.close(window.parent.TblCompany.layerIndex);
}

/**
 * 收集数据
 */
TblCompanyInfoDlg.collectData = function() {
    this
    .set('id')
    .set('address')
    .set('legalPerson')
    .set('account')
    .set('password')
    .set('logo')
    .set('name')
    .set('sort')
    .set('status')
    .set('telphone')
    .set('comment')
    .set('liveday')
    .set('personLimit')
    .set('accessToken');
}

/**
 * 提交添加
 */
TblCompanyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblCompany/add", function(data){
        Feng.success("添加成功!");
        window.parent.TblCompany.table.refresh();
        TblCompanyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblCompanyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TblCompanyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblCompany/update", function(data){
        Feng.success("修改成功!");
        window.parent.TblCompany.table.refresh();
        TblCompanyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblCompanyInfoData);
    ajax.start();
}

$(function() {
    // 初始化头像上传
    var avatarUp = new $WebUpload("logo");
    avatarUp.init();
});
