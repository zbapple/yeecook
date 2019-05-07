$(function () {
    $("#jqGrid").Grid({
        url: '../menu/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '种类名称', name: 'categoryName', index: 'category_Name', width: 80},
            {label: '菜谱名', name: 'menuName', index: 'menu_name', width: 80},
            {label: '菜谱图片', name: 'menuPicUrl', index: 'menu_pic_url', width: 80, formatter: function (value) {
                    return transImg(value);
                }
            }, {label: '菜谱下载', name: 'menuStrUrl', index: 'menu_str_url', width: 80},
            {label: '创建人', name: 'user', index: 'user', width: 80},
            {label: '烹调方式', name: 'cookMethod', index: 'cook_method', width: 80},
            {label: '烹调操作图片', name: 'cookPicUrl', index: 'cook_pic_url', width: 80, formatter: function (value) {
                    return transImg(value);
                }},
            {label: '配料', name: 'burden', index: 'burden', width: 80},
            {label: '下载烹饪程序', name: 'menuDownloader', index: 'menu_downloader', width: 80}]
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        menu: {menuPicUrl:'',cookPicUrl:'',auxiliaryMeans:'',menuStrUrl:'',menuDownloader:''},
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ]
        },

        q: {
            name: '',
            categoryName:''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.menu = {menuPicUrl:'',cookPicUrl:'',auxiliaryMeans:'',menuStrUrl:'',menuDownloader:''};
        },
        update: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            let url = vm.menu.id == null ? "../menu/save" : "../menu/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.menu),
                type: "POST",
                contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            let ids = getSelectedRows("#jqGrid");
            if (ids == null){
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../menu/delete",
                    params: JSON.stringify(ids),
                    type: "POST",
                    contentType: "application/json",
                    successCallback: function () {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        getInfo: function(id){
            Ajax.request({
                url: "../menu/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.menu = r.menu;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                postData: {'categoryName': vm.q.categoryName},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function() {
            vm.q = {
                name: '',
                categoryName:''
            }
            vm.reload();
        },
        handleSuccessmenuPicUrl: function (res, file) {
            vm.menu.menuPicUrl = file.response.url;
        },
        handleSuccessmenuStrUrl: function (res, file) {
            vm.menu.menuStrUrl = file.response.url;
        },
        handleSuccesscookPicUrl: function (res, file) {
            vm.menu.cookPicUrl = file.response.url;
        },
        handleSuccessauxiliaryMeans: function (res, file) {
            vm.menu.auxiliaryMeans = file.response.url;
        },
        handleSuccessmenuDownloader:function(res,file){
            vm.menu.menuDownloader=file.response.url;
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 20M。'
            });
        },

        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        }
    }
});