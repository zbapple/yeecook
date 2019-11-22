$(function () {
    $("#jqGrid").Grid({
        url: '../stroe/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '门店名称', name: 'storeName', index: 'store_name', width: 80},
            {label: '门店logo', name: 'storePicrue', index: 'store_picrue', width: 60,
                formatter: function (value) {
                    return transImg(value);}},
			// {label: '国家', name: 'country', index: 'country', width: 80},
			// {label: '省', name: 'province', index: 'province', width: 80},
			// {label: '城市', name: 'city', index: 'city', width: 80},
			// {label: '区', name: 'distrct', index: 'distrct', width: 80},
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
			// {label: '部门id', name: 'departmentid', index: 'departmentid', width: 80},
			// {label: '供应商id', name: 'supplierid', index: 'supplierid', width: 80},
			// {label: '经度', name: 'longitude', index: 'Longitude', width: 80},
			// {label: '纬度', name: 'latitude', index: 'Latitude', width: 80},
			// {label: '起送费', name: 'sendingfee', index: 'sendingfee', width: 80},
			// {label: '配送费', name: 'deliveryfee', index: 'deliveryfee', width: 80},
			// {label: '门店实景图片', name: 'realisticPicture', index: 'realistic_picture', width: 80},
			{label: '营业执照', name: 'businessLicense', index: 'business_license', width: 60,
                formatter: function (value) {
                    return transImg(value);}},
            // {label: '食品经营许可证', name: 'licensePic', index: 'license_pic', width: 60,
            //     formatter: function (value) {
            //         return transImg(value);}},
			{label: '门店状态', name: 'storeStatus', index: 'store_status', width: 80,
                formatter: function (value) {
                    if (value == '0') {
                        return '<p style="color: red;font-weight: bold">暂停营业</p>';
                    } else if (value == '1') {
                        return '<p style="color: forestgreen;font-weight: bold">营业中</p>';
                    }
                }},
            {  label:'操作',name:'checking', width: 100,index:'checking',sortable:false, formatter: function(value,col,row){
                    return  '<button style="border: none;outline:none;width:90px;line-height: 30px;background-color: #f16543;color: #fff;" onclick="vm.updateStatus(' + row.id +  ')">暂停/开始营业</button>'+
                        '<button  style="width: 56px;line-height: 30px;border: none;outline:none;margin-left: 10px;background-color: #2db7f5;color: #fff;" onclick="vm.lookDetail('+  row.id  +')">查看详情</button>';
                }
            },

			// {label: '门店营业时间', name: 'storeTime', index: 'store_time', width: 80}
		]
    });
    $('#jqGrid').css("textAlign","center");
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        details:false,
        title: null,
        uploadList: [],
        imgName: '',
        visible: false,
        provinceArr: [],
        citiesArr: [],
        distrctArr:[],
        areaId: '',
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
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			],
            storeName: [
                {required: true, message: '门店名称不能为空', trigger: 'blur'}
            ],
            address: [
                {required: true, message: '地址不能为空', trigger: 'blur'}
            ],
            storePicrue: [
                {required: true, message: '门店logo不能为空', trigger: 'blur'}
            ],
            storePhone: [
                {required: true, message: '门店电话不能为空', trigger: 'blur'}
            ]
		},
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
		add: function () {
			vm.showList = false;
			vm.details = false;
			vm.title = "新增门店";
			vm.stroe = {
                storePicrue:null,
                country:'中国',
                licensePic:null,
                businessLicense:null,
                storeTimes:[],
                realisticPicture:'',
                picList:'',
                province:'',
                city:'',
                distrct:'',
                sendingfee:'',
                deliveryfee:'',
                storeStatus:1,
                isDelete:0,
                storeType:''
			};
			vm.getArea(1);
			vm.uploadList=[];
		},
		update: function (event) {
            let id = getSelectedRow("#jqGrid");
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.details = false;
            vm.title = "修改";
            vm.getInfo(id);
            vm.getArea(1);
            vm.uploadList=[];

		},
		saveOrUpdate: function (event) {
            let url = vm.stroe.id == null ? "../stroe/save" : "../stroe/update";
            vm.stroe.storeTimes=this.date;
            vm.stroe.picList=vm.uploadList;
            if (vm.stroe.storeType == '营养餐'){
                vm.stroe.storeType= '1';
            }else if (vm.stroe.storeType == '月子餐'){
                vm.stroe.storeType= '2';
            } else if(vm.stroe.storeType == '老人餐'){
                vm.stroe.storeType= '3';
            }
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.stroe),
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

			confirm('确定要删除选中的记录(将会进入回收箱)？', function () {
                Ajax.request({
				    url: "../stroe/delete",
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
                url: "../stroe/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.stroe = r.stroe;
                    console.log(vm.stroe);
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
        /**
         * 查询时间
         */
        selectTime:function(date){
              vm.stroe.storeTimes=date;
              this.date=date;
        },
        /**
         * 查看详情
         */
        lookDetail:function(rowId){
            vm.details=true;
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
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
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
        reloadSearch: function() {
            vm.q = {
                name: '',
                storeName:'',
                storeType:''
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
         * 省
         */
        getArea:function(areaId){
            Ajax.request({
                url: "../sys/region/getAllProvice?areaId=" + areaId,
                async: true,
                successCallback: function (r) {
                    vm.provinceArr = r.list;
                    this.provinceArr=vm.provinceArr;
                }
            });
        },
        /**
         * 市
         */
        getCity:function(val){
            let areaId = null;
            for (let i = 0; i < this.provinceArr.length; i++) {
                if (this.provinceArr[i].name == val) {
                    areaId = this.provinceArr[i].id;
                }
            }
            if (areaId != null) {
                let that=this;
                Ajax.request({
                    url: "../sys/region/getAllCity?areaId=" + areaId,
                    async: false,
                    successCallback: function (r) {
                        vm.citiesArr = r.list;
                        this.citiesArr=vm.citiesArr;
                    }
                });
            }
        },
        /**
         * 区县
         */
        getDistrct:function(val){
                let id = null;
                for (let i = 0; i < this.citiesArr.length; i++) {
                    if (this.citiesArr[i].name == val) {
                        id = this.citiesArr[i].id;
                    }
                }
                if (id != null) {
                Ajax.request({
                    url: "../sys/region/getChildrenDistrict?areaId=" + id,
                    async: true,
                    successCallback: function (r) {
                        vm.distrctArr = r.list;
                    }
                });
            }
        },
        /**
         * 修改状态
         */
        updateStatus:function(rowId){
            confirm('确定修改状态？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../stroe/updateStatus",
                    params: JSON.stringify(rowId),
                    contentType: "application/json",
                    type: 'POST',
                    successCallback: function () {
                        alert('提交成功', function (index) {
                            vm.reload();
                        });
                    }
                });
            });
        },
        /**
         * 跳转门店管理
         */
        stroehistory:function(){
            openWindow({
                type: 2,
                title: '门店回收站',
                content: '../shop/stroehistory.html'
            })
        },
        /**
		 * 图片组件
         */
        handleSuccessPicUrl: function (res, file) {
            vm.stroe.storePicrue = file.response.url;
        },
        handleSuccessfoodUrl: function (res, file) {
            vm.stroe.licensePic = file.response.url;
        },
        handleSuccessBannerUrl: function (res, file) {
            vm.stroe.businessLicense = file.response.url;
        },
		handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        eyeImagePicUrl: function () {
            var url = vm.stroe.storePicrue;
            eyeImage(url);
        },
        eyeImageBannerUrl: function () {
            var url = vm.stroe.businessLicense;
            eyeImage(url);
        },
        eyeImagefood: function () {
            var url = vm.stroe.licensePic;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
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
	},
    mounted() {
        this.uploadList = this.$refs.upload.fileList;
    }
});