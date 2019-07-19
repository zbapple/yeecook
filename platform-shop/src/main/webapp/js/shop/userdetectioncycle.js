$(function () {
    $("#jqGrid").Grid({
        url: '../userdetectioncycle/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '用户id', name: 'nideshopUserId', index: 'nideshop_user_id', width: 80},
			{label: '检测开始日期', name: 'inspectionStartDate', index: 'inspection_start_date', width: 80,
                formatter: function (value) {
                    return  transDate(value).substring(0,10);
                }},
			{label: '检测结束日期', name: 'inspectionEndDate', index: 'inspection_end_date', width: 80,
                formatter: function (value) {
                    return  transDate(value).substring(0,10);
                }},
			{label: '已检测次数', name: 'inspectionNum', index: 'inspection_num', width: 80},
			{label: '检测执行日期', name: 'inspectionTime', index: 'inspection_time', width: 80,
                formatter: function (value) {
                    return  transDate(value).substring(0,10);
                }},
			{label: '检测周期', name: 'inspectionCycle', index: 'inspection_cycle', width: 80},
            {label: '下次检测时间',name:'nextTime',index:'next_time',width:80,
               formatter:function (value) {
                   return transDate(value).substring(0,10);
               }
            },
			]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		userDetectionCycle: {},
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
			vm.userDetectionCycle = {};
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
            let url = vm.userDetectionCycle.id == null ? "../userdetectioncycle/save" : "../userdetectioncycle/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.userDetectionCycle),
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
				    url: "../userdetectioncycle/delete",
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
                url: "../userdetectioncycle/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.userDetectionCycle = r.userDetectionCycle;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.nideshopUserId},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                nideshopUserId: ''
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