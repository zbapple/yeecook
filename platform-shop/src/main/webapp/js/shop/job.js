$(function () {
    $("#jqGrid").Grid({
        url: '../job/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '职位名称', name: 'jobTitle', index: 'job_title', width: 80},
			{label: '职位描述', name: 'jobInfo', index: 'job_info', width: 80},
            {label: '职位要求', name: 'jobRequire', index: 'job_require', width: 120},
			{label: '职位类别', name: 'jobCategory', index: 'job_category', width: 80},
			{label: '发布时间', name: 'releaseTime', index: 'release_time', width: 80,
                formatter: function (value) {
                    return transDate(value).substring(0, 10);
                }},
			{label: '部门名称', name: 'deptName', index: 'dept_name', width: 80},
			{label: '发布', name: 'isRelease', index: 'is_release', width: 80},
			{label: '创建人', name: 'createUser', index: 'create_user', width: 80},
			{label: '审核', name: 'isStatus', index: 'is_status', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		job: {},
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
			vm.job = {};
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
            let url = vm.job.id == null ? "../job/save" : "../job/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.job),
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
				    url: "../job/delete",
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
                url: "../job/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.job = r.job;
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