$(function () {
    $("#jqGrid").Grid({
        url: '../applicationrecord/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '申请食材id', name: 'newFoodId', index: 'new_food_id', width: 80},
			{label: '申请人所在省份', name: 'province', index: 'province', width: 80},
			{label: '申请人所在城市', name: 'city', index: 'city', width: 80},
			{label: '申请人姓名', name: 'name', index: 'name', width: 80},
			{label: '手机号码', name: 'phone', index: 'phone', width: 80},
			{label: '申请食材名称', name: 'newFoodName', index: 'new_food_name', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		applicationRecord: {},
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
			vm.applicationRecord = {};
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
            let url = vm.applicationRecord.id == null ? "../applicationrecord/save" : "../applicationrecord/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.applicationRecord),
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
				    url: "../applicationrecord/delete",
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
                url: "../applicationrecord/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.applicationRecord = r.applicationRecord;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                name: ''
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