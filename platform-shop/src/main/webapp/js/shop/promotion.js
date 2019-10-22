$(function () {
    $("#jqGrid").Grid({
        url: '../promotion/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '推广人员姓名', name: 'promotionName', index: 'promotion_name', width: 80},
			{label: '推广人员的手机号码', name: 'promotionTelphone', index: 'promotion_telphone', width: 80},
			{label: '微信名称', name: 'wxName', index: 'wx_name', width: 80},
			{label: '微信头像', name: 'wxListPic', index: 'wx_list_pic', width: 80},
			{label: '所在单位', name: 'affiliatedUnit', index: 'affiliated_unit', width: 80},
			{label: '推广码', name: 'promotionYard', index: 'promotion_yard', width: 100},
            {  label:'操作',name:'check', width: 120,index:'check',sortable:false, formatter: function(value,col,row){
                    return  '<button  style="width: 80px;line-height: 30px;border: none;outline:none" onclick="vm.getUser('+  row.id  +')">推广用户</button>'

                }
            },]
    });
    vm.getUsers();
    $('#jqGrid').css("textAlign","center");
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
        UserNames:[],
		promotion: {},
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
		    vm.getUsers();
			vm.showList = false;
			vm.title = "新增";
			vm.promotion = {};
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
       getUser: function (rowId) {
           openWindow({
               type: 2,
               title: '关联用户',
               content: '../shop/promotioninfo.html?parentId=' + rowId
           })
	   },
		saveOrUpdate: function (event) {
            let url = vm.promotion.id == null ? "../promotion/save" : "../promotion/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.promotion),
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
				    url: "../promotion/delete",
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
        /**
         * 获取用户名
         */
        getUsers: function () {
            Ajax.request({
                url: "../user/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.UserNames = r.list;
                }
            });
        },
		getInfo: function(id){
            Ajax.request({
                url: "../promotion/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.promotion = r.promotion;
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