$(function () {
    $("#jqGrid").Grid({
        url: '../ordersupplier/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '供应商id', name: 'supplierId', index: 'supplier_id', width: 80},
			{label: '供应商订单号', name: 'orderSupSn', index: 'order_sup_sn', width: 80},
			{label: '用户订单编号', name: 'orderSn', index: 'order_sn', width: 80},
			{label: '用户id', name: 'userId', index: 'user_id', width: 80},
			{label: '订单状态', name: 'orderStatus', index: 'order_status', width: 80},
			{label: '收件人', name: 'consignee', index: 'consignee', width: 80},
			{label: '国家', name: 'country', index: 'country', width: 80},
			{label: '省份', name: 'province', index: 'province', width: 80},
			{label: '城市', name: 'city', index: 'city', width: 80},
			{label: '地区', name: 'district', index: 'district', width: 80},
			{label: '详细地址', name: 'address', index: 'address', width: 80},
			{label: '手机号码', name: 'mobile', index: 'mobile', width: 80},
			{label: '用户备注', name: 'postscript', index: 'postscript', width: 80},
			{label: '快递公司ID', name: 'shippingId', index: 'shipping_id', width: 80},
			{label: '快递公司', name: 'shippingName', index: 'shipping_name', width: 80},
			{label: '运费', name: 'shippingFee', index: 'shipping_fee', width: 80},
			{label: '实际需要支付的金额', name: 'actualPrice', index: 'actual_price', width: 80},
			{label: '订单总价', name: 'orderPrice', index: 'order_price', width: 80},
			{label: '商品总价', name: 'goodsPrice', index: 'goods_price', width: 80},
			{label: '新增时间', name: 'addTime', index: 'add_time', width: 80},
			{label: '确认时间', name: 'confirmTime', index: 'confirm_time', width: 80},
			{label: '付款时间', name: 'payTime', index: 'pay_time', width: 80},
			{label: '配送费用', name: 'freightPrice', index: 'freight_price', width: 80},
			{label: '使用的优惠券id', name: 'couponId', index: 'coupon_id', width: 80},
			{label: '优惠价格', name: 'couponPrice', index: 'coupon_price', width: 80},
			{label: '快递单号', name: 'shippingNo', index: 'shipping_no', width: 80},
			{label: '配送费用', name: 'fullCutPrice', index: 'full_cut_price', width: 80},
			{label: '订单类型 1：普通订单 2：团购订单 3：砍价订单 4: 直接购买', name: 'orderType', index: 'order_type', width: 80},
			{label: '是否已经打印：0未打印，1已打印', name: 'isPrinter', index: 'is_printer', width: 80},
			{label: '供应商部门id', name: 'deptId', index: 'dept_id', width: 80}]
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		orderSupplier: {},
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
			vm.orderSupplier = {};
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
            let url = vm.orderSupplier.id == null ? "../ordersupplier/save" : "../ordersupplier/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.orderSupplier),
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
				    url: "../ordersupplier/delete",
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
                url: "../ordersupplier/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.orderSupplier = r.orderSupplier;
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