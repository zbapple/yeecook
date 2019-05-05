$(function () {
    $("#jqGrid").Grid({
        url: '../newfood/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '菜名称', name: 'name', index: 'name', width: 80},
			{label: '视频URL', name: 'voidUrl', index: 'void_url', width: 80},
			{label: '标题一', name: 'tile1', index: 'tile_1', width: 80},
			{label: '标题一备注', name: 'tile1Remark', index: 'tile_1_remark', width: 80},
			{label: '研发大师图像', name: 'yfdsPic', index: 'yfds_pic', width: 80},
			{label: '研发大师名称', name: 'yfdsName', index: 'yfds_name', width: 80},
			{label: '研发大师描述', name: 'yfdsDesc', index: 'yfds_desc', width: 80},
			{label: '观看次数', name: 'plays', index: 'plays', width: 80},
			{label: '供应商图像', name: 'supplieImg', index: 'supplie_img', width: 80},
			{label: '供应商标题一', name: 'supplieTile', index: 'supplie_tile', width: 80},
			{label: '供应商标题描述', name: 'supplieDesc', index: 'supplie_desc', width: 80},
			{label: '供应商介绍', name: 'supplieInfo', index: 'supplie_info', width: 80},
			{label: '课程简介', name: 'tile2', index: 'tile_2', width: 80},
			{label: '课程简介内容', name: 'tile2Remark', index: 'tile_2_remark', width: 80},
			{label: '课程福利', name: 'tile3', index: 'tile_3', width: 80},
			{label: '课程内容', name: 'tile3Remark', index: 'tile_3_remark', width: 80},
			{label: '底部图片LOG', name: 'log', index: 'log', width: 80},
			{label: '底部介绍', name: 'logRemark', index: 'log_remark', width: 80},
			{label: '食材图片', name: 'foodImg', index: 'food_img', width: 80},
			{label: '食材标题', name: 'foodTile', index: 'food_tile', width: 80},
			{label: '食材描述', name: 'foodRemark', index: 'food_remark', width: 80},
			{label: '应用菜式', name: 'yycs', index: 'yycs', width: 80},
			{label: '应用菜式内容', name: 'yycsRemark', index: 'yycs_remark', width: 80},
			{label: '食材申请标题一', name: 'foodSqTile1', index: 'food_sq_tile1', width: 80},
			{label: '食材申请标题二', name: 'foodSqTile2', index: 'food_sq_tile2', width: 80},
			{label: '食材供应商图片', name: 'foodSupImg', index: 'food_sup_img', width: 80},
			{label: '食材供应商标题', name: 'foodSupTile', index: 'food_sup_tile', width: 80},
			{label: '食材供应商描述', name: 'foodSupRemark', index: 'food_sup_remark', width: 80},
			{label: '食材功能供应商介绍', name: 'foodSupInfo', index: 'food_sup_info', width: 80},
			{label: '页面URL地址', name: 'url', index: 'url', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		newFood: {},
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
			vm.newFood = {};
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
            let url = vm.newFood.id == null ? "../newfood/save" : "../newfood/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.newFood),
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
				    url: "../newfood/delete",
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
                url: "../newfood/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.newFood = r.newFood;
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