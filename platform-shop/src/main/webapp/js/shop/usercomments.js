$(function () {
    $("#jqGrid").Grid({
        url: '../usercomments/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '用户名称', name: 'nickname', index: 'nickname', width: 80},
			{label: '用户评论', name: 'userComment', index: 'user_comment', width: 80},
			{label: '课件id', name: 'videoId', index: 'video_id', width: 80},
			{label: '评论分数', name: 'commentsScore', index: 'comments_score', width: 80},
			{label: '评论时间', name: 'commentsTime', index: 'comments_time', width: 80,
				 formatter: function (value) {
                    return transDate(value).substring(0, 10);
                }},
			{label: '回复类型 0是 1否', name: 'replyType', index: 'reply_type', width: 80},
			{label: '回复id', name: 'replyId', index: 'reply_id', width: 80}]
    });
    vm.getUserNames();
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		userComments: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		},
        UserNames:[]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.userComments = {};
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
            let url = vm.userComments.id == null ? "../usercomments/save" : "../usercomments/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.userComments),
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
				    url: "../usercomments/delete",
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
                url: "../usercomments/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.userComments = r.userComments;
                }
            });
		},
        /**
		 * 获取用户
         */
        getUserNames: function () {
            Ajax.request({
                url: "../user/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.UserNames = r.list;

                }
            });
        },
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.nickname},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                nickname: ''
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