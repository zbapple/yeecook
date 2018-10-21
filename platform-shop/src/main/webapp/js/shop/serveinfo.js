$(function () {
    $("#jqGrid").Grid({
        url: '../serveinfo/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '服务项目名称', name: 'name', index: 'name', width: 80},
			{label: '服务类型id', name: 'type', index: 'type', width: 80},
			{label: '服务规则描述', name: 'desc', index: 'desc', width: 80},
			{label: '激活有效期', name: 'activationValidity', index: 'activation_validity', width: 80},
			{label: '总服务次数', name: 'serveCount', index: 'serve_count', width: 80},
			{label: '服务有效期', name: 'serveValidity', index: 'serve_validity', width: 80},
			{label: '自身关联商品id', name: 'serveGoodsid', index: 'serve_goodsid', width: 80},
			{label: '服务关联商品集合', name: 'serveGoodsids', index: 'serve_goodsids', width: 80},
			{label: '新增时间', name: 'addTime', index: 'add_time', width: 80},
			{label: '修改时间', name: 'updataTime', index: 'updata_time', width: 80},
			{label: '操作用户id', name: 'userId', index: 'user_id', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		serveInfo: {},
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
			vm.serveInfo = {};
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
            let url = vm.serveInfo.id == null ? "../serveinfo/save" : "../serveinfo/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.serveInfo),
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
				    url: "../serveinfo/delete",
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
                url: "../serveinfo/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.serveInfo = r.serveInfo;
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