$(function () {
    $("#jqGrid").Grid({
        url: '../userhealthreport/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '用户名称', name: 'nickname', index: 'nickname', width: 80},
			{label: '检测时间', name: 'detectionTime', index: 'detection_time', width: 80,
                formatter: function (value) {
                    return transDate(value).substring(0, 10);
                }},
			{label: '更新时间', name: 'updateTime', index: 'update_time', width: 80,formatter: function (value) {
                    return transDate(value).substring(0, 10);
                }},
			{label: '体重', name: 'weight', index: 'weight', width: 80},
			{label: 'BMI', name: 'bmi', index: 'BMI', width: 80},
			{label: '体脂率', name: 'bodyFatRade', index: 'body_fat_rade', width: 80},
			{label: '皮下脂肪率', name: 'subFatPercentage', index: 'sub_fat_percentage', width: 80},
			{label: '基础代谢量', name: 'basicMetabolism', index: 'basic_metabolism', width: 80},
			{label: '内脏脂肪等级', name: 'visFatGrade', index: 'vis_fat_grade', width: 80},
			{label: '体水分率', name: 'bodyWaterRate', index: 'body_water_rate', width: 80},
			{label: '蛋白质', name: 'protein', index: 'protein', width: 80},
			{label: '骨量', name: 'boneMass', index: 'bone_mass', width: 80},
			{label: '骨骼肌率', name: 'skeletalMuscle', index: 'skeletal_muscle', width: 80},
            {
                label: '身体体型', name: 'bodyShape', index: 'body_shape', width: 80,
                formatter: function (value) {
                    if (value == '0') {
                        return  '轻体重';
                    } else if (value == '1') {
                        return  '健康体重';
                    } else if (value == '2') {
                        return  '超重';
                    } else if (value == '3') {
                        return  '肥胖';
                    }
                    return ''
                }
            },
            {label: '身体年龄', name: 'bodyAge', index: 'body_age', width: 80},
            {label: '健康评分', name: 'sclscore', index: 'sclscore', width: 80}
		]
    });
    vm.getUserNames();
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		userHealthReport: {},
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
			vm.userHealthReport = {};
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
            let url = vm.userHealthReport.id == null ? "../userhealthreport/save" : "../userhealthreport/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.userHealthReport),
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
				    url: "../userhealthreport/delete",
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
                url: "../userhealthreport/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.userHealthReport = r.userHealthReport;
                }
            });
		},
		/**
		获取用户名
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