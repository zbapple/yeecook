$(function () {
    $("#jqGrid").Grid({
        url: '../menu/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '种类名称', name: 'categoryName', index: 'category_Name', width: 80},
            {label: '菜谱名', name: 'menuName', index: 'menu_name', width: 80},
            {label: '创建人', name: 'user', index: 'user', width: 80},
            {label: '菜谱图片', name: 'menuPicUrl', index: 'menu_pic_url', width: 100, formatter: function (value) {
                    return transImg(value);
                }
            }]
    });

});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        menu: {menuPicUrl:''},
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
        update: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "详情";

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
                postData: {'name': vm.q.name ,
                           'categoryName': vm.q.categoryName
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function() {
            vm.q = {
                name: '',
                categoryName: ''
            }
            vm.reload();
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },openURl:function(url){
            window.open(url);
        }
    }
});