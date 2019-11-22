$(function () {
    $("#jqGrid").Grid({
        url: '../supplier/historyList',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '供应商名称', name: 'companyName', index: 'company_name', width: 80},
            {label: '供应商logo',name:'companyLogo',index:'company_logo',width:80,
                formatter: function (value) {
                    return transImg(value);}
            },
            {label: '供应商地址', name: 'address', index: 'address', width: 160},
            {label: '电话', name: 'telephone', index: 'telephone', width: 70},
            // {label: '手机', name: 'mobile', index: 'mobile', width: 80},
            // {label: '开户银行', name: 'openingBank', index: 'opening_bank', width: 80},
            // {label: '银行账号', name: 'bankAccount', index: 'bank_account', width: 80},
            // {label: '收款户名', name: 'beneficiaryName', index: 'beneficiary_name', width: 80},
            {label: '部门', name: 'deptName', index: 'dept_name', width: 60},
            {label: '供应商类型', name: 'companyType', index: 'company_type', width: 60,
                formatter: function (value) {
                    if (value == '1') {
                        return '商城供应商';
                    } else if (value == '2') {
                        return '订餐供应商';
                    }else {
                        return '-';
                    }
                }},
            {  label: '删除门店时间', name: 'deleteTime', index: 'deleteTime', width: 100},
            {  label:'操作',name:'checking', width: 120,index:'checking',sortable:false, formatter: function(value,col,row){
                    return '<button  style="width: 56px;line-height: 30px;border: none;outline:none;margin-left: 10px;background-color: #2db7f5;color: #fff;" onclick="vm.lookDetail('+  row.id  +')">查看详情</button>';
                }
            },
            // {label: '部门ID', name: 'deptId', index: 'dept_id', width: 80},
        ]
    });
    vm.getDepts();
    $('#jqGrid').css("textAlign","center");
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        details:false,
        title: null,
        supplier:{},
        ruleValidate: {
            companyName: [
                {required: true, message: '供应商公司名称', trigger: 'blur'}
            ],
            openingBank: [
                {required: true, message: '开户银行', trigger: 'blur'}
            ]
        },
        q: {
            companyName: '',
            deptName:''
        },
        depts:[]
    },
    methods: {
        query: function () {
            vm.reload();
        },
        back: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要恢复选中的记录？', function () {

                Ajax.request({
                    type: "POST",
                    url: "../supplier/back",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        deleteAll: function (event) {
            let ids = getSelectedRows("#jqGrid");
            if (ids == null){
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../supplier/deleteAll",
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
         * 获取供应商部门
         */
        getDepts: function () {
            Ajax.request({
                url: "../sys/dept/list/2",
                async: true,
                successCallback: function (r) {
                    vm.depts = r.list;
                }
            });
        },
        getInfo: function(id){
            Ajax.request({
                url: "../supplier/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.supplier = r.supplier;
                    if(vm.supplier.deptName!=null){
                        vm.supplier.deptName= vm.supplier.deptId+","+vm.supplier.deptName;
                    }
                    vm.supplier.companyType = r.supplier.companyType;
                    if (vm.supplier.companyType == '1') {
                        vm.supplier.companyType = '商城供应商';
                    } else if (vm.supplier.companyType == '2') {
                        vm.supplier.companyType = '订餐供应商';
                    } else{
                        vm.supplier.companyType = '暂无数据';
                    }
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            vm.details = false;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'companyName': vm.q.companyName,
                    'deptName': vm.q.deptName
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');

        },
        reloadSearch: function() {
            vm.q = {
                companyName:'',
                deptName:''
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
        /**
         * 查看详情
         */
        lookDetail:function(rowId){
            vm.details=true;
            vm.title = "供应商详情";
            vm.getInfo(rowId);
        },
    }
});