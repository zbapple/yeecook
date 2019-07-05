$(function () {
    $("#jqGrid").Grid({
        url: '../menuplan/alist',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '', name: 'nideshopUserId', index: 'nideshop_user_id', width: 80,hidden:true},
            {label: '用户名称', name: 'userName', index: 'username', width: 80},
            {label: '食谱头像', name: 'dishesCoverPic', index: 'dishes_cover_pic', width: 80,
                formatter: function (value) {
                    return transImg(value);}
            },
            {label: '主表食谱类型', name: 'nutritionMenuType', index: 'nutrition_menu_type', width: 80},
            {label: '食谱类型', name: 'menuType', index: 'menu_type', width: 80},
            {
                label: '计划开始时间', name: 'serviceCycleSt', index: 'service_cycle_st', width: 80,
                formatter: function (value) {
                    return transDate(value).substring(0, 10);
                }
            },
            {label: '机构名称', name: 'cateringServiceOrgName', index: 'catering_service_org_name', width: 80},
            {label: '食谱名称', name: 'menuName', index: 'menu_name', width: 80},
            {
                label: '计划结束时间', name: 'serviceCycleEt', index: 'service_cycle_et', width: 80,
                formatter: function (value) {
                    return transDate(value).substring(0, 10);
                }
            },
            {
                label: '状态', name: 'menuStatus', index: 'menu_status', width: 80,
                formatter: function (value) {
                    return value === 0 ?
                        '<span class="label label-danger">未签约</span>' :
                        '<span class="label label-success">已签约</span>';
                }
            }

        ]
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        menuPlan: {},
        sdfd:{},
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.menuPlan = {};
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
            let url = vm.menuPlan.id == null ? "../menuplan/save" : "../menuplan/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.menuPlan),
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
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../menuplan/delete",
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
        getInfo: function (id) {
            Ajax.request({
                url: "../menuplan/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.menuPlan = r.menuPlan;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.menuName,
                    'userName':vm.q.userName,
                    'nutritionMenuType':vm.q.nutritionMenuType,
                    'caterName':vm.q.cateringServiceOrgName
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function () {
            vm.q = {
                menuName: '',
                userName:'',
                nutritionMenuType:'',
                cateringServiceOrgName:''
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
        }
    }
});