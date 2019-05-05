$(function () {
    $("#jqGrid").Grid({
        url: '../yfdsinfo/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '研发大师图像', name: 'yfdsPic', index: 'yfds_pic', width: 80,formatter: function (value) {
                    return transImg(value);
                }},
			{label: '研发大师名称', name: 'yfdsName', index: 'yfds_name', width: 80},
			{label: '研发大师描述', name: 'yfdsDesc', index: 'yfds_desc', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		yfdsInfo: {},
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
			vm.yfdsInfo = {};
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
            let url = vm.yfdsInfo.id == null ? "../yfdsinfo/save" : "../yfdsinfo/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.yfdsInfo),
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
				    url: "../yfdsinfo/delete",
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
                url: "../yfdsinfo/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.yfdsInfo = r.yfdsInfo;
                }
            });
		},
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 文件格式不正确.'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大'
            });
        },
        handleSuccessSupUrl: function (res, file) {
            vm.yfdsInfo.yfdsPic = file.response.url;
            this.$Notice.warning({
                title: '成功',
                desc: '上传成功！'
            });
        },
        eyeImageSup: function () {
            var url = vm.yfdsInfo.yfds_pic;
            eyeImage(url);
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