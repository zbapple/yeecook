$(function () {
    $("#jqGrid").Grid({
        url: '../usernutritionmenu/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '用户id', name: 'nideshopUserId', index: 'nideshop_user_id', width: 80},
			{label: '餐单名称', name: 'menuName', index: 'menu_name', width: 80},
            {label: '主表餐单类型',name: 'nutritionMenuType', index:'nutrition_menu_type',width:80},
			{label: '餐单封面图片', name: 'menuCoverPic', index: 'menu_cover_pic', width: 80,
                formatter: function (value) {
                    return transImg(value);
                }
			},
			{label: '营养原理', name: 'nutritionPrinciple', index: 'nutrition_principle', width: 80},
			{label: '膳食服务机构id', name: 'cateringServiceOrgId', index: 'catering_service_org_id', width: 80},
			{label: '膳食服务机构名称', name: 'cateringServiceOrgName', index: 'catering_service_org_name', width: 80},
			{label: '服务周期开始时间', name: 'serviceCycleSt', index: 'service_cycle_st', width: 80,
                formatter: function (value) {
                    return transDate(value).substring(0,10);
                }},
			{label: '服务周期结束时间', name: 'serviceCycleEt', index: 'service_cycle_et', width: 80,
                formatter: function (value) {
                    return transDate(value).substring(0,10);
                }},
            {
                label: '服务阶段', name: 'serviceStage', index: 'service_stage', width: 80
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
		userNutritionMenu: {},
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
			vm.userNutritionMenu = {};
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
            let url = vm.userNutritionMenu.id == null ? "../usernutritionmenu/save" : "../usernutritionmenu/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.userNutritionMenu),
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
				    url: "../usernutritionmenu/delete",
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
                url: "../usernutritionmenu/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.userNutritionMenu = r.userNutritionMenu;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.nideshopUserId},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                nideshopUserId: ''
            }
            vm.reload();
        },
        handleSuccessmenuCoverPic: function (res, file) {
            vm.userNutritionMenu.menuCoverPic = file.response.url;
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