$(function () {
    $("#jqGrid").Grid({
        url: '../activationcodelog/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '关联服务id', name: 'serveInfoId', index: 'serve_info_id', width: 80},
			{label: '激活时间', name: 'activationTime', index: 'activation_time', width: 80},
			{label: '激活用户id', name: 'userId', index: 'user_id', width: 80},
			{label: '激活用户姓名', name: 'userName', index: 'user_name', width: 80},
			{label: '备注', name: 'remark', index: 'remark', width: 80},
			{label: '激活码', name: 'activationCode', index: 'activation_code', width: 80},
			{label: '激活终端', name: 'activateTerminal', index: 'activate_terminal', width: 80},
			{label: '订单编号', name: 'orderSn', index: 'order_sn', width: 80},
			{label: '关联服务名称', name: 'serveInfoName', index: 'serve_info_name', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		activationCodeLog: {},
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
			vm.activationCodeLog = {};
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
            let url = vm.activationCodeLog.id == null ? "../activationcodelog/save" : "../activationcodelog/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.activationCodeLog),
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
				    url: "../activationcodelog/delete",
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
                url: "../activationcodelog/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.activationCodeLog = r.activationCodeLog;
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