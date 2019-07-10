$(function () {
    $("#jqGrid").Grid({
        url: '../menuplan/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '', name: 'nideshopUserId', index: 'nideshop_user_id', width: 80,hidden:true},
                {label: '用户名称', name: 'userName', index: 'username', width: 80,},
            {label: '用户头像', name: 'avatar', index: 'avatar', width: 80,
                formatter: function (value) {
                    return transImg(value);}
                    },
            {label: '食谱类型', name: 'nutritionMenuType', index: 'nutrition_menu_type', width: 80},
            {
                label: '计划开始时间', name: 'serviceCycleSt', index: 'service_cycle_st', width: 80,
                formatter: function (value) {
                    return transDate(value).substring(0, 10);
                }
            },
            {label: '机构名称', name: 'cateringServiceOrgName', index: 'catering_service_org_name', width: 80},
            {label: '食谱名称', name: 'menuName', index: 'menu_name', width: 80},
            {
                label: '计划结束时间', name: 'serviceCycleEt', index: 'service_cycle_et', width: 80,
                formatter: function (value) {
                    return transDate(value).substring(0, 10);
                }
            },
            {label: '食谱编号', name: 'menuSn', index: 'menu_sn', width: 80 },
            {
                label: '状态', name: 'menuStatus', index: 'menu_status', width: 80,
                formatter: function (value) {
                    return value === 0 ?
                        '<span class="label label-danger">未签约</span>' :
                        '<span class="label label-success">已签约</span>';
                }
            },
            {  label:'操作',name:'check', width: 80,index:'check',sortable:false, formatter: function(value,col,row){
                    return  '<button  style="width: 30px;" onclick="vm.lookDetail('+  row.id  +')">查看</button>'

                }
            },
            // {  label:'初始体重',name:'weight',index:'weight'},
            // {  label:'周期',name:'inspectionCycle',index:'inspection_cycle'}
            ]
    });
    vm.getCateringServiceOrgNames();
    vm.getCaterings();
    vm.getNutritionMenuTypes();
    vm.getUserNames();
    $('#jqGrid').css("textAlign","center");
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        animal: '爪哇犀牛',
        showList: true,
        details: false,
        showfoods: false,
        visible: false,
        imgName: '',
        title: null,
        uploadList: [],
        menuPlan: {},
        sdfd:{},
        data3:[],
        targetKeys3: [],
        listStyle: {
            marginLeft: '150px',
            width: '250px',
            height: '300px'

        },
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ],
            username: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ],
            cateringServiceOrgName: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ],
            nutritionMenuType: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ],

        },
        q: {
            name:'',
            userName:'',
            cateringServiceOrgName:'',
            nutritionMenuType:''
        },
        NutritionMenuTypes:[],
        CateringServiceOrgNames:[],
        Caterings:[],
        UserNames:[],
        showcamera:true,
        foodlist:[{
            foodlistname:'',
            foodlistsrc:'',
            foodlistpwl:''
        }],
        foodlistadd:[
            {
                foodlistname:'',
                foodlistsrc:'',
                foodlistpwl:''
            }
        ]
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.showfoods = false;
            vm.uploadList = [];
            vm.title = "新增套餐";
            vm.menuPlan = {menuStatus: '0'};
        },
        addfoods:function (){
            vm.showfoods = true;
        },
        update: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.uploadList = [];
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            let url = vm.menuPlan.id == null ? "../menuplan/save" : "../menuplan/update";
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.menuPlan),
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
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    url: "../menuplan/delete",
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
                url: "../menuplan/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.menuPlan = r.menuPlan;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            vm.details = false;
            vm.showfoods =false;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'name': vm.q.menuName,
                    'userName': vm.q.userName,
                    'cateringServiceOrgName': vm.q.cateringServiceOrgName,
                    'nutritionMenuType': vm.q.nutritionMenuType
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function () {
            vm.q = {
                menuName: '',
                userName: '',
                cateringServiceOrgName: '',
                nutritionMenuType: ''
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
         * 获取餐品名
         */
        getFoods: function (){
          Ajax.request({
             url:"../"
          });
        },
        /**
         * 获取机构名字
         */
        getCateringServiceOrgNames: function () {
            Ajax.request({
                url: "../menuplan/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.CateringServiceOrgNames = r.list;
                }
            });
        },
        /**
         * 添加机构名字
         */
        getCaterings: function (){
            Ajax.request({
                url:"../sys/dept/list",
                async: true,
                successCallback: function(r) {
                    vm.Caterings = r.list;
                }
            });
        },
        /**
         * 获取用户名
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
        /**
         *  获取主表食谱类型
         */
        getNutritionMenuTypes: function () {
            Ajax.request({
                url: "../menuplan/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.NutritionMenuTypes = r.list;
                }
            });
        },
        /**
         *  查看详情操作
         */
        lookDetail: function (rowId) {
            vm.details = true;
            vm.title = "详情"
            console.log(rowId);
            Ajax.request({
                url: "../menuplan/info/" + rowId,
                async: true,
                successCallback: function (r) {
                    vm.menuPlan = r.menuPlan;
                }
            });
        },
        /**
         *  审核窗口
         */
        openStatus: function () {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            openWindow({
                type: 2,
                title: '审核',
                area: ['400px', '300px'],
                content: '../shop/menuplancheck.html?Id=' + id
            })
        },
        /**
         * 打印
         */
        printDetail: function (rowId) {
            openWindow({
                type: 2,
                title: '<i class="fa fa-print"></i>打印签约',
                content: '../shop/menuplanPrint.html?Id=' + rowId
            })
        },
        /**
         *  菜品选择穿梭框
         */
        getMockData: function () {
            let mockData=[];
           for (let i = 1; i <= 20; i++) {
                mockData.push({
                    key: i.toString(),
                    label: '早餐'  ,
                    description: '菜品' + i,
                    disabled: Math.random() * 3 < 1
                });
            }
            return mockData;
        },
        getTargetKeys: function () {
            return this.getMockData()
                .filter(() => Math.random() * 2 > 1)
                .map(item => item.key);
        },
        handleChange3: function (newTargetKeys) {
            this.targetKeys3 = newTargetKeys;
        },
        render3: function (item) {
            return item.label + ' - ' + item.description;
        },
        reloadMockdata: function () {
            this.data3 = this.getMockData();
            this.targetKeys3 = this.getTargetKeys();
        },
        /**
         *  上传组件
         */
        saveOrUpdate: function (event) {
            var url = vm.goods.id == null ? "../menuplan/save" : "../menuplan/update";
            vm.menuPlan.menuCoverPic = vm.uploadList;
        },
        handleView(name) {
            this.imgName = name;
            this.visible = true;
        },
        handleRemove(file) {
            // 从 upload 实例删除数据
            const fileList = this.uploadList;
            this.showcamera=true;
            this.uploadList.splice(fileList.indexOf(file), 1);
        },
        handleSuccess(res, file) {
            // 因为上传过程为实例，这里模拟添加 url
            file.imgUrl = res.url;
            file.name = res.url;
            vm.uploadList.add(file);
        },
        handleBeforeUpload() {
            const check = this.uploadList.length < 2;
            this.showcamera=false;
            if (!check) {
                this.$Notice.warning({
                    title: '最多只能上传 1 张图片。'
                });
            }
            return check;
        },
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate();
            });
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
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        handleSuccessPicUrl: function (res, file) {
            vm.menuPlan.menuCoverPic = file.response.url;
        },
        handleSuccessListPicUrl: function (res, file) {
            vm.menuPlan.menuCoverPic = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.menuPlan.menuCoverPic;
            eyeImage(url);
        },
        eyeImageListPicUrl: function () {
            var url = vm.menuPlan.menuCoverPic;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        },
        // 添加早餐菜品
        addfoodlist:function(){
            this.foodlist.push({
                foodlistname:'',
                    foodlistsrc:'',
                foodlistpwl:''

            });
        },
        //删除早餐餐品
        deletefood:function(i){
            this.foodlist.splice(i,1);
        },
        //添加早餐加餐
        addfoodlistadd() {
            this.foodlistadd.push({
                foodlistname:'',
                foodlistsrc:'',
                foodlistpwl:''
            })
        },
        //删除早餐加餐
        deletefoodadd:function (i) {
            this.foodlistadd.splice(i,1);
        }
    },
    mounted() {
        // this.uploadList = this.$refs.upload.fileList;
        this.data3=this.getMockData();
        console.log(this.data3)
        this.targetKeys3=this.getTargetKeys();
        console.log(this.targetKeys3);
    }
});