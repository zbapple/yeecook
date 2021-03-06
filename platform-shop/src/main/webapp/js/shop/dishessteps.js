$(function () {
    let dishesId = getQueryString("dishesId");
    let url = '../dishessteps/list';
    if (dishesId) {
        url += '?dishesId=' + dishesId;
    }
    $("#jqGrid").Grid({
        url: url,
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '菜品名称', name: 'dishesname', index: 'dishes_name', width: 80},
			{label: '菜品步骤', name: 'stepsNum', index: 'steps_num', width: 80},
			{label: '菜品步骤图片', name: 'stepsPic', index: 'steps_pic', width: 80,
                formatter: function (value) {
                    return transImg(value);}},
			{label: '步骤描述', name: 'stepsDescribe', index: 'steps_describe', width: 80}
			]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		dishesSteps: {},
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
			vm.dishesSteps = {};
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
            let url = vm.dishesSteps.id == null ? "../dishessteps/save" : "../dishessteps/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.dishesSteps),
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
				    url: "../dishessteps/delete",
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
                url: "../dishessteps/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.dishesSteps = r.dishesSteps;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.dishesname},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                dishesname: ''
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
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleSuccessstepsPic: function (res, file) {
            vm.dishesSteps.stepsPic = file.response.url;
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 20m。'
            });
        },
        eyeImagestepsPic: function () {
            var url =vm.dishesSteps.stepsPic;
            eyeImage(url);
        }
	}
});