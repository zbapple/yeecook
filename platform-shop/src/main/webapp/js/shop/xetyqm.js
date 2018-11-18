$(function () {
    $("#jqGrid").Grid({
        url: '../xetyqm/list',
        colModel: [
			{label: '批次', name: 'batchId', index: 'batch_id', width: 80},
			{label: '批次名称', name: 'batchName', index: 'batch_name', width: 80},
			{label: 'invitationCode', name: 'invitationCode', index: 'Invitation_code', key: true, hidden: true},
			{label: '邀请码连接', name: 'invitationCodeUrl', index: 'Invitation_code_url', width: 80},
			{label: '是否使用', name: 'isUse', index: 'is_use', width: 80},
			{label: '使用人id', name: 'useUserId', index: 'use_user_id', width: 80},
			{label: '使用人昵称', name: 'useUserName', index: 'use_user_name', width: 80},
			{label: '邀请码标题', name: 'invitationCodeTitle', index: 'Invitation_code_title', width: 80},
			{label: '使用须知', name: 'useNotice', index: 'use_notice', width: 80},
			{label: '申请人', name: 'proposer', index: 'proposer', width: 80},
			{label: '申请原因', name: 'pursueReason', index: 'pursue_reason', width: 80},
			{label: '生效时间', name: 'effectiveTime', index: 'effective_time', width: 80},
			{label: '失效时间', name: 'deadTime', index: 'dead_time', width: 80},
			{label: '生成时间', name: 'generatedTime', index: 'generated_time', width: 80},
			{label: '主键', name: 'id', index: 'id', width: 80},
			{label: '用户id', name: 'userId', index: 'user_id', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		xetYqm: {},
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
			vm.xetYqm = {};
		},
		update: function (event) {
            let invitationCode = getSelectedRow("#jqGrid");
			if (invitationCode == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(invitationCode)
		},
		saveOrUpdate: function (event) {
            let url = vm.xetYqm.invitationCode == null ? "../xetyqm/save" : "../xetyqm/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.xetYqm),
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
            let invitationCodes = getSelectedRows("#jqGrid");
			if (invitationCodes == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
                Ajax.request({
				    url: "../xetyqm/delete",
                    params: JSON.stringify(invitationCodes),
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
		getInfo: function(invitationCode){
            Ajax.request({
                url: "../xetyqm/info/"+invitationCode,
                async: true,
                successCallback: function (r) {
                    vm.xetYqm = r.xetYqm;
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