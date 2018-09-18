$(function () {
    $("#jqGrid").Grid({
        url: '../supplier/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '公司名称', name: 'companyName', index: 'company_name', width: 80},
			{label: '开户银行', name: 'openingBank', index: 'opening_bank', width: 80},
			{label: '银行账号', name: 'bankAccount', index: 'bank_account', width: 80},
			{label: '收款户名', name: 'beneficiaryName', index: 'beneficiary_name', width: 80},
			{label: '电话', name: 'telephone', index: 'telephone', width: 80},
			{label: '手机', name: 'mobile', index: 'mobile', width: 80},
			{label: '部门', name: 'deptName', index: 'dept_name', width: 80},
            {label: '部门ID', name: 'deptId', index: 'dept_id', width: 80},
			{label: '地址', name: 'address', index: 'address', width: 80}]
    });
    vm.getDepts();
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		supplier: {},
		ruleValidate: {
            companyName: [
				{required: true, message: '供应商公司名称', trigger: 'blur'}
			],
            openingBank: [
                {required: true, message: '开户银行', trigger: 'blur'}
            ]
		},
		q: {
            companyName: '',
            deptName:''
		},
        depts:[]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.supplier = {};
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
            let url = vm.supplier.id == null ? "../supplier/save" : "../supplier/update";

            if(vm.supplier.deptName!=null){
                let user=vm.supplier.deptName.split(",");
                vm.supplier.deptId=user[0];
                vm.supplier.deptName=user[1];
            }

            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.supplier),
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
				    url: "../supplier/delete",
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
        /**
         * 获取供应商部门
         */
        getDepts: function () {
            Ajax.request({
                url: "../sys/dept/list/2",
                async: true,
                successCallback: function (r) {
                    vm.depts = r.list;
                }
            });
        },
		getInfo: function(id){
            Ajax.request({
                url: "../supplier/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.supplier = r.supplier;
                    if(vm.supplier.deptName!=null){
                        vm.supplier.deptName= vm.supplier.deptId+","+vm.supplier.deptName;
                    }
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'companyName': vm.q.companyName,
                    'deptName': vm.q.deptName
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');

		},
        reloadSearch: function() {
            vm.q = {
                companyName:'',
                deptName:''
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