$(function () {
    $("#jqGrid").Grid({
        url: '../newmenu/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '等级', name: 'nlevel', index: 'nlevel', width: 80},
			{label: '分娩方式', name: 'deliveryWay', index: 'delivery_way', width: 80},
			{label: '喂哺方式', name: 'feedingWay', index: 'feeding_way', width: 80},
			{label: '月子时长', name: 'liliLength', index: 'lili_length', width: 80},
			{label: '总能量值', name: 'theTotalEnergy', index: 'the_total_energy', width: 80},
			{label: '浏览量', name: 'wxViews', index: 'wx_views', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		newMenu: {nlevel:'',deliveryWay:'',feedingWay:'',liliLength:'',theTotalEnergy:''},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
            nlevel: '',
            deliveryWay:''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.newMenu = {nlevel:'',deliveryWay:'',feedingWay:'',liliLength:'',theTotalEnergy:''};
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
            let url = vm.newMenu.id == null ? "../newmenu/save" : "../newmenu/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.newMenu),
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
				    url: "../newmenu/delete",
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
                url: "../newmenu/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.newMenu = r.newMenu;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'nlevel': vm.q.nlevel},
                postData: {'deliveryWay': vm.q.deliveryWay},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                nlevel: '',
                deliveryWay:''
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