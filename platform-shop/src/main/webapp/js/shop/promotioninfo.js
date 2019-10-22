$(function () {
    let parentId = getQueryString("parentId");
    let url = '../promotioninfo/list';
    if (parentId) {
        url += '?parentId=' + parentId;
    }
    $("#jqGrid").Grid({
        url: url,
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			// {label: '推广员姓名', name: 'promotionName', index: 'parent_id', width: 80},
			{label: '用户昵称', name: 'nickname', index: 'user_id', width: 60},
            {label: '用户头像', name: 'avatar', index: 'user_id', width: 60,
                formatter: function (value) {
                    return transImg(value);}
            },
            {label: '购买时间', name: 'payTime', index: 'pay_time', width: 60,
                formatter: function (value) {
                    return transDate(value);
                }},
            {label: '商品名称', name: 'goodsName', index: 'goods_name', width: 100},
            {label: '商品数量', name: 'number', index: 'number', width: 60},
            {label: '订单金额', name: 'orderPrice', index: 'order_price', width: 60},
        ]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		promotionInfo: {},
		ruleValidate: {
            nickname: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
            nickname: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.promotionInfo = {};
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
            let url = vm.promotionInfo.id == null ? "../promotioninfo/save" : "../promotioninfo/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.promotionInfo),
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
				    url: "../promotioninfo/delete",
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
                url: "../promotioninfo/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.promotionInfo = r.promotionInfo;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'nickname': vm.q.nickname},
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