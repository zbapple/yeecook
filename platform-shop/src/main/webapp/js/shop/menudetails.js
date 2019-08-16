$(function () {
    $("#jqGrid").Grid({
        url: '../menudetails/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '餐单名称', name: 'menuname', index: 'menu_name', width: 80},
			{label: '餐单类型', name: 'menuType', index: 'menu_type', width: 80,formatter: function (value) {
                    if (value == '0') {
                        return '早餐';
                    } else if (value == '1') {
                        return '午餐';
                    } else if (value == '2') {
                        return '晚餐';
                    } else if (value == '3') {
                        return '早餐加餐';
                    } else if (value == '4'){
                        return '午餐加餐';
                    } else if (value == '5'){
                        return '晚餐加餐';
                    }
                    return '-';
                }},
			// {label: '餐品id', name: 'dishesId', index: 'dishes_id', width: 80},
			{label: '餐品名', name: 'dishesName', index: 'dishes_name', width: 80},
			// {label: '叶子节点', name: 'leafNode', index: 'leaf_node', width: 80},
			// {label: '父类id', name: 'fatherId', index: 'father_id', width: 80},
			{label: '用餐时间', name: 'mealTime', index: 'meal_time', width: 80},
			{label: '餐单日期', name: 'menuDate', index: 'menu_date', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		menuDetails: {},
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
			vm.menuDetails = {};
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
            let url = vm.menuDetails.id == null ? "../menudetails/save" : "../menudetails/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.menuDetails),
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
				    url: "../menudetails/delete",
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
                url: "../menudetails/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.menuDetails = r.menuDetails;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.menuname},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                menuname: ''
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