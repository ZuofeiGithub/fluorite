/**
 *  企业管理管理初始化
 */
var TblCompany = {
    id: "TblCompanyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TblCompany.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '序号', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '公司名字', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '公司地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
        {title: '公司法人', field: 'legalPerson', visible: true, align: 'center', valign: 'middle'},
        {title: '公司账户', field: 'account', visible: true, align: 'center', valign: 'middle'},

        {title: '公司排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
        {title: '公司状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
        {title: '公司电话', field: 'telphone', visible: true, align: 'center', valign: 'middle'},
        {title: '成立日期', field: 'liveday', visible: true, align: 'center', valign: 'middle'},
        {title: '人数上限', field: 'personLimit', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
TblCompany.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        TblCompany.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加 企业管理
 */
TblCompany.openAddTblCompany = function () {
    var index = layer.open({
        type: 2,
        title: '添加 企业管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblCompany/tblCompany_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看 企业管理详情
 */
TblCompany.openTblCompanyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: ' 企业管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tblCompany/tblCompany_update/' + TblCompany.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 企业管理
 */
TblCompany.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tblCompany/delete", function (data) {
            Feng.success("删除成功!");
            TblCompany.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tblCompanyId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询 企业管理列表
 */
TblCompany.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TblCompany.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TblCompany.initColumn();
    var table = new BSTable(TblCompany.id, "/tblCompany/list", defaultColunms);
    table.setPaginationType("client");
    TblCompany.table = table.init();
});
