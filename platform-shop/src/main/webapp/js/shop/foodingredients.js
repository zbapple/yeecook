$(function () {
    let dishesId = getQueryString("dishesId");
    let url = '../foodingredients/list';
    if (dishesId) {
        url += '?dishesId=' + dishesId;
    }
    $("#jqGrid").Grid({
        url: url,
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			// {label: '食材id', name: 'foodMaterialId', index: 'food_material_id', width: 80},
            {label: '菜品名称', name: 'dishesname', index: 'dishes_name', width: 80},
			{label: '食材名字', name: 'foodMaterialName', index: 'food_material_name', width: 80},
			{label: '食材数量', name: 'foodMaterialNum', index: 'food_material_num', width: 80},
			{label: '食材总卡路里', name: 'foodMaterialSumcal', index: 'food_material_sumcal', width: 80}
		]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		foodIngredients: {},
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
			vm.foodIngredients = {};
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
            let url = vm.foodIngredients.id == null ? "../foodingredients/save" : "../foodingredients/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.foodIngredients),
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
				    url: "../foodingredients/delete",
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
                url: "../foodingredients/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.foodIngredients = r.foodIngredients;
                    console.log(vm.foodIngredients)
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.dishesname},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                foodMaterialName: ''
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