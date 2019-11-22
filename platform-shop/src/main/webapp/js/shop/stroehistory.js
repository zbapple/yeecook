$(function () {
    $("#jqGrid").Grid({
        url: '../stroe/historyList',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '门店名称', name: 'storeName', index: 'store_name', width: 80},
            {label: '门店logo', name: 'storePicrue', index: 'store_picrue', width: 60,
                formatter: function (value) {
                    return transImg(value);}},
            {label: '详细地址', name: 'address', index: 'address', width: 160},
            {label: '门店电话', name: 'storePhone', index: 'store_phone', width: 60},
            {label: '门店类型', name: 'storeType', index: 'store_type', width: 60,
                formatter: function (value) {
                    if (value == '1') {
                        return '营养餐';
                    } else if (value == '2') {
                        return '月子餐';
                    } else if (value == '3') {
                        return '长者餐';
                    }
                }},
            {label: '营业执照', name: 'businessLicense', index: 'business_license', width: 60,
                formatter: function (value) {
                    return transImg(value);}},
            // {label: '门店状态', name: 'storeStatus', index: 'store_status', width: 80,
            //     formatter: function (value) {
            //         if (value == '0') {
            //             return '<p style="color: red;font-weight: bold">暂停营业</p>';
            //         } else if (value == '1') {
            //             return '<p style="color: forestgreen;font-weight: bold">营业中</p>';
            //         } else if (value == '2') {
            //             return '<p style="color: gray;font-weight: bold">已关闭门店</p>';
            //         }
            //     }},
            {  label: '删除门店时间', name: 'deleteTime', index: 'deleteTime', width: 80},
            {  label:'操作',name:'checking', width: 100,index:'checking',sortable:false, formatter: function(value,col,row){
                    return '<button  style="width: 55px;line-height: 30px;border: none;outline:none;margin-left: 10px;background-color: #2db7f5;color: #fff;" onclick="vm.lookDetail('+  row.id  +')">查看详情</button>';
                }
            },
        ]
    });
    $('#jqGrid').css("textAlign","center");
    $('#jqGrid').css
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        details:false,
        title: null,
        uploadList: [],
        stroe: {
            storePicrue:null,
            country:'',
            licensePic:null,
            businessLicense:null,
            storeTime:'',
            storeTimes:[],
            province:'',
            city:'',
            distrct:'',
            sendingfee:'',
            deliveryfee:'',
            storeStatus:'',
            isDelete:'',
            realisticPicture:'',
            picList:'',
            stroeGrade:'',
            storeType:'',
        },
        storeTypeList: ['营养餐','月子餐','长者餐'],
        q: {
            name: '',
            storeName:'',
            storeType:'',
        }
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
                    url: "../stroe/back",
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
        deleteAll:function(){
            let ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../stroe/deleteAll",
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
        getInfo: function (id) {
            Ajax.request({
                url: "../stroe/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.stroe = r.stroe;
                    vm.stroe.storeType = r.stroe.storeType;
                    if (vm.stroe.storeType == '1') {
                        vm.stroe.storeType = '营养餐';
                    } else if (vm.stroe.storeType == '2') {
                        vm.stroe.storeType = '月子餐';
                    } else if (vm.stroe.storeType == '3') {
                        vm.stroe.storeType = '老人餐';
                    }
                    vm.uploadList = [];
                    vm.stroe.realisticPicture = r.stroe.realisticPicture;
                    if (vm.stroe.realisticPicture != 0) {
                        let str = vm.stroe.realisticPicture;
                        let arr = str.split(";");
                        for (let i = 0; i < arr.length; i++) {
                            vm.uploadList.push({
                                realisticPicture: arr[i],
                                name: arr[i]
                            })
                        }
                    }else {
                        vm.uploadList=[];
                    }
                }
            });
        },
        lookDetail: function (rowId) {
            vm.details = true;
            vm.title = "门店详情";
            vm.getInfo(rowId);
            vm.uploadList=[];
        },
        reload: function (event) {
            vm.showList = true;
            vm.details = false;
            if (vm.q.storeType =='营养餐'){
                vm.q.storeType = 1
            }else if (vm.q.storeType =='月子餐'){
                vm.q.storeType = 2
            }else if (vm.q.storeType =='长者餐'){
                vm.q.storeType = 3
            }
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'name': vm.q.name,
                    'storeName': vm.q.storeName,
                    'storeType': vm.q.storeType,
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function () {
            vm.q = {
                name:'',
                storeName: '',
                storeType: '',
            }
            vm.reload();
        },
        /**
         * 上传组件
         */
        handleView(name) {
            this.imgName = name;
            this.visible = true;
        },
        handleRemove(file) {
            // 从 upload 实例删除数据
            const fileList = this.uploadList;
            this.uploadList.splice(fileList.indexOf(file), 1);
        },
        handleSuccess(res, file) {
            // 因为上传过程为实例，这里模拟添加 url
            file.realisticPicture = res.url;
            file.name = res.url;
            vm.uploadList.add(file);
        }, handleBeforeUpload() {
            const check = this.uploadList.length < 5;
            if (!check) {
                this.$Notice.warning({
                    title: '最多只能上传 5 张图片。'
                });
            }
            return check;
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        }
    }
});