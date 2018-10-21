$(function () {
    $("#jqGrid").Grid({
        url: '../activationcard/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '激活时间', name: 'activationTime', index: 'activation_time', width: 80},
			{label: '失效时间', name: 'disabledTime', index: 'disabled_time', width: 80},
			{label: '激活IP地址', name: 'activationIp', index: 'activation_ip', width: 80},
			{label: '激活终端', name: 'activation terminal', index: 'activation_ terminal', width: 80},
			{label: '激活用户id', name: 'userId', index: 'user_id', width: 80},
			{label: '激活码', name: 'activationCode', index: 'activation_code', width: 80},
			{label: '是否激活：0未激活，1已激活', name: 'isActivation', index: 'is_activation', width: 80},
			{label: '上次服务时间', name: 'serveLastTime', index: 'serve_last_time', width: 80},
			{label: '下次服务时间', name: 'serveNextTime', index: 'serve_next_time', width: 80},
			{label: '已服务次数', name: 'haveServe count', index: 'have_serve_ count', width: 80},
			{label: '激活订单编号', name: 'orderSn', index: 'order_sn', width: 80},
			{label: '关联服务id', name: 'serveId', index: 'serve_id', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		activationCard: {},
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
			vm.activationCard = {};
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
            let url = vm.activationCard.id == null ? "../activationcard/save" : "../activationcard/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.activationCard),
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
				    url: "../activationcard/delete",
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
                url: "../activationcard/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.activationCard = r.activationCard;
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