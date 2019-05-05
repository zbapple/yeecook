$(function () {
    $("#jqGrid").Grid({
        url: '../dzdp/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '商家名称', name: 'name', index: 'name', width: 80},
			{label: '省份', name: 'province', index: 'province', width: 80},
			{label: '城市', name: 'city', index: 'city', width: 80},
			{label: '区域', name: 'areas', index: 'areas', width: 80},
			{label: '分类', name: 'categories', index: 'categories', width: 80},
			{label: '电话', name: 'phones', index: 'phones', width: 80}]
    });


    $(".export-excel").on("click", function (e) {
        e.preventDefault();
        var filename = "商户信息统计";
        var name=vm.q.name;
        var areas=vm.q.areas;
        var categories=vm.q.categories;

        window.open("../dzdp/exclorder?" +
            "name=" +name+
            "&areas="+areas+
            "&categories="+categories+
            "&filename="+filename
        );
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		dzdp: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: '',
            areas: '',
            categories: '',
            phones:''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.dzdp = {};
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
            let url = vm.dzdp.id == null ? "../dzdp/save" : "../dzdp/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.dzdp),
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
				    url: "../dzdp/delete",
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
                url: "../dzdp/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.dzdp = r.dzdp;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name,'areas': vm.q.areas,'categories': vm.q.categories,'phones': vm.q.phones==false?'':vm.q.phones},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                name: '',
                areas: '',
                categories: '',
                phones:''
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