$(function () {
    $("#jqGrid").Grid({
        url: '../menuPlan/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '', name: 'nideshopUserId', index: 'nideshop_user_id', width: 80,hidden:true},
                {label: '用户名称', name: 'nickName', index: 'nick_name', width: 80,},
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
            {label: '机构名称', name: 'cateringServiceOrgName', index: 'catering_service_org_name', width: 120},
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
            {  label:'操作',name:'check', width: 120,index:'check',sortable:false, formatter: function(value,col,row){
                    return  '<button  style="width: 40px;line-height: 30px;border: none;outline:none" onclick="vm.lookDetail('+  row.id  +')">查看</button>'+
                        '<button style="border: none;outline:none;width: 40px;line-height: 30px;margin-left: 10px" onclick="vm.openStatus(' + row.id +  ')">审核</button>'
                        +'<button style="border: none;outline:none;width: 40px;line-height: 30px;margin-left: 10px" onclick="vm.add(' + row.id +  ')">完善</button>';

                }
            },
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
        showBtn: true,
        showList: true,
        details: false,
        showfoods: false,
        visible: false,
        imgName: '',
        title: null,
        nutritionMenuTypelist:['老人餐A','老人餐B','月子餐类型A','月子餐类型B'],
        serviceStagelsit:['第一周疗养阶段','第二周疗养阶段','第三周疗养阶段','第四周疗养阶段'],
        uploadList: [],
        listType:{},
        servermenuPlan: {
            deptId:'',
            cateringServiceOrgName:'',
            nutritionMenuType:'',
            nickName:'',
            menuName:'',
            menuSn:'',
            serviceStage:'',
            serviceCycleSt:'',
            serviceCycleEt:'',
            inspectionCycle:'',
            foodlist:[],
            foodlistadd:[],
            foodlist1:[],
            foodlistadd1:[],
            foodlist2:[],
            foodlistadd2:[],
            menuCoverPics:{},
            menuCoverPics:'',
            nutritionPrinciple:'',
        },
        mPlan:[],
        menuPlan: {},
        menumap:[],
        listmap:[],
        foods:[],
        data3:[],
        targetKeys3: [],
        listStyle: {
            marginLeft: '10px',
            width: '250px',
            height: '300px'

        },
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ],
            nickName: [
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
            nickName:'',
            cateringServiceOrgName:'',
            nutritionMenuType:''
        },
        user:{},
        NutritionMenuTypes:[],
        CateringServiceOrgNames:[],
        Caterings:[],
        UserNames:[],
        menumapuser:{},
        menumaptype:[],
        menumapweight:{},
        menumapsys:{},
        menumapsysdept:{},
        dept:{},
        menumapst:'',
        menumapet:'',
        menutp:'',
        menutp1:'',
        menutp2:'',
        menutp3:'',
        menutp4:'',
        menutp5:'',
        menutp6:'',
        menumaplist:[],
        foodlist:[
            {
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:'',
                menuDate:''
            }
        ],
        foodlistadd:[
            {
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:'',
                menuDate:''
            }
        ],
        foodlist1:[
            {
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:'',
                menuDate:''
            }
        ],
        foodlistadd1:[
            {
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:'',
                menuDate:''
            }
        ],
        foodlist2:[
            {
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:'',
                menuDate:''
            }
        ],
        foodlistadd2:[
            {
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:'',
                menuDate:''
            }
        ],
        showcamera:true,
        ddww:[],
        showselect:false,
        zaolist:[],
        zaoaddlist:[],
        wulist:[],
        wuaddlist:[],
        wanlist:[],
        wanaddlist:[]
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
            vm.servermenuPlan ={
                    deptId:'',
                    cateringServiceOrgName:'',
                    nutritionMenuType:'',
                    nickName:'',
                    menuName:'',
                    menuSn:'',
                    serviceStage:'',
                    serviceCycleSt:'',
                    serviceCycleEt:'',
                    inspectionCycle:'',
                    foodlist:[],
                    foodlistadd:[],
                    foodlist1:[],
                    foodlistadd1:[],
                    foodlist2:[],
                    foodlistadd2:[],
                    menuCoverPics:{},
                    menuCoverPic:'',
                nutritionPrinciple:'',
            },
            vm.menuPlan = {menuStatus: '0'};

        },
        addfoods:function (){
            vm.showfoods = true;
        },
        /**
         *  查看详情操作
         */
        lookDetail: function (rowId) {
            vm.details = true;
            vm.title = "详情"
            console.log(rowId);
            Ajax.request({
                url: "../menuPlan/menuinfo/" + rowId,
                async: true,
                successCallback: function (r) {
                    vm.menumap = r.menumap;
                    vm.menumapuser=vm.menumap.infomsg;
                    vm.menumapsys=vm.menumap.sysuser;
                    vm.menumapsysdept=vm.menumap.sysdept;
                    console.log(vm.menumap);
                    vm.menumaptype=vm.menumap.menutype;
                    vm.menumapweight=vm.menumap.weight||'';
                    vm.menumapst=vm.menumap.serviceCycleSt;
                    vm.menumapet=vm.menumap.serviceCycleEt;
                    vm.zaolist=[];
                    vm.wulist=[];
                    vm.wanlist=[];
                    vm.zaoaddlist=[];
                    vm.wuaddlist=[];
                    vm.wanaddlist=[];
                  for (let i=0;i<vm.menumaptype.length;i++) {
                      if (vm.menumaptype[i].menuType == '0'){
                          vm.zaolist.push({
                              dishesName:vm.menumaptype[i].dishesName,
                              dishesCoverPic:vm.menumaptype[i].dishesCoverPic,
                              dishesCalories:vm.menumaptype[i].dishesCalories,
                              menuDate:vm.menumaptype[i].menuDate
                          });
                      } else if(vm.menumaptype[i].menuType == '1') {
                          vm.wulist.push({
                              dishesName:vm.menumaptype[i].dishesName,
                              dishesCoverPic:vm.menumaptype[i].dishesCoverPic,
                              dishesCalories:vm.menumaptype[i].dishesCalories,
                              menuDate:vm.menumaptype[i].menuDate
                          });
                      } else if(vm.menumaptype[i].menuType == '2'){
                          vm.wanlist.push({
                              dishesName:vm.menumaptype[i].dishesName,
                              dishesCoverPic:vm.menumaptype[i].dishesCoverPic,
                              dishesCalories:vm.menumaptype[i].dishesCalories,
                              menuDate:vm.menumaptype[i].menuDate
                          });
                      } else if (vm.menumaptype[i].menuType == '3') {
                          vm.zaoaddlist.push({
                              dishesName:vm.menumaptype[i].dishesName,
                              dishesCoverPic:vm.menumaptype[i].dishesCoverPic,
                              dishesCalories:vm.menumaptype[i].dishesCalories,
                              menuDate:vm.menumaptype[i].menuDate
                          });
                      }else if (vm.menumaptype[i].menuType == '4') {
                          vm.wuaddlist.push({
                              dishesName:vm.menumaptype[i].dishesName,
                              dishesCoverPic:vm.menumaptype[i].dishesCoverPic,
                              dishesCalories:vm.menumaptype[i].dishesCalories,
                              menuDate:vm.menumaptype[i].menuDate
                          });
                      }else if (vm.menumaptype[i].menuType == '5') {
                          vm.wanaddlist.push({
                              dishesName:vm.menumaptype[i].dishesName,
                              dishesCoverPic:vm.menumaptype[i].dishesCoverPic,
                              dishesCalories:vm.menumaptype[i].dishesCalories,
                              menuDate:vm.menumaptype[i].menuDate
                          });
                      }
                  }

                }
            });
        },
        update: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;}
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id);
            vm.getCaterings();

        },
        saveOrUpdate: function (event) {
            console.log(777888);
            var url = vm.servermenuPlan.id == null ? "../menuPlan/save" : "../menuPlan/update";
            vm.servermenuPlan.menuCoverPics=vm.uploadList;
            vm.servermenuPlan.foodlist=this.foodlist;
            vm.servermenuPlan.foodlistadd=this.foodlistadd;
            vm.servermenuPlan.foodlist1=this.foodlist1;
            vm.servermenuPlan.foodlistadd1=this.foodlistadd1;
            vm.servermenuPlan.foodlist2=this.foodlist2;
            vm.servermenuPlan.foodlistadd2=this.foodlistadd2;
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.servermenuPlan),
                type: "POST",
                contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                        vm.servermenuPlan={};
                        vm.zaolist=[];
                        vm.wulist=[];
                        vm.wanlist=[];
                        vm.zaoaddlist=[];
                        vm.wuaddlist=[];
                        vm.wanaddlist=[];
                        // vm.servermenuPlan.foodlist.splice(0,vm.servermenuPlan.foodlist.length);
                        // vm.servermenuPlan.foodlistadd.splice(0,vm.servermenuPlan.foodlistadd.length);
                        // vm.servermenuPlan.foodlist1.splice(0,vm.servermenuPlan.foodlist1.length);
                        // vm.servermenuPlan.foodlistadd1.splice(0,vm.servermenuPlan.foodlistadd1.length);
                        // vm.servermenuPlan.foodlist2.splice(0,vm.servermenuPlan.foodlist2.length);
                        // vm.servermenuPlan.foodlistadd2.splice(0,vm.servermenuPlan.foodlistadd2.length);
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
                    url: "../menuPlan/delete",
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
                url: "../menuPlan/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.servermenuPlan = r.menuPlan;
                    console.log( vm.servermenuPlan);
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
                    'nickName': vm.q.nickName,
                    'cateringServiceOrgName': vm.q.cateringServiceOrgName,
                    'nutritionMenuType': vm.q.nutritionMenuType,
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function () {
            vm.q = {
                menuName: '',
                nickName: '',
                cateringServiceOrgName: '',
                nutritionMenuType: ''
            }
            vm.reload();
        },
        handleSubmit: function (){
                vm.saveOrUpdate();
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        /**
         * 获取部门名字
         */
        getDeptName:function(){
            Ajax.request({
                url: "../sys/dept/info",
                async: true,
                successCallback: function (r) {
                    vm.CateringServiceOrgNames = r.list;
                }
            });
        },
        /**
         * 获取机构名字
         */
        getCateringServiceOrgNames: function () {
            Ajax.request({
                url: "../sys/dept/list/12",
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
                url:"../sys/dept/list/12",
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
         * 获取session用户
         */
        getUser: function () {
            Ajax.request({
                url: "../sys/user/info",
                async: true,
                successCallback: function (r) {
                    vm.user = r.user;
                    console.log(vm.user);
                }
            });
        },
        /**
         *  获取主表食谱类型
         */
        getNutritionMenuTypes: function () {
            Ajax.request({
                url: "../menuPlan/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.NutritionMenuTypes = r.list;

                }
            });
        },
        /**
         * 获取菜品类型
         */
        getCategory: function(){
            Ajax.request({
                url: "../dishes/queryType",
                async: true,
                successCallback: function (r) {
                    vm.category = r.listType;
                    console.log(vm.category)
                }
            });
        },
        /**
         *  审核窗口
         */
        openStatus: function (rowId) {
            // openWindow({
            //     type: 2,
            //     title: '审核',
            //     area: ['400px', '300px'],
            //     content: '../shop/menuplancheck.html?Id=' + rowId
            // })
            confirm('确定签约是否成功？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../menuPlan/updateInfo",
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
         * 打印
         */
        printDetail: function (rowId) {
            openWindow({
                type: 2,
                title: '<i class="fa fa-print"></i>打印签约',
                content: '../shop/menuplanPrint.html?Id=' + rowId
            })
        },
        adddd:function(){
            let that=this;
            Ajax.request({
                url:'../dishes/queryAll',
                successCallback: function (r) {
                    console.log(r);
                    that.ddww=r.list;
                }
            })
        },
        /**
         *  菜品选择穿梭框
         */
        getMockData: function () {
            let mockData=[];
            let list=this.ddww;
           for (let i = 0; i <= list.length-1; i++) {
                mockData.push({
                    key: list[i],
                    label: list[i].dishesName,
                    description: i,
                });
            }
            return mockData;
        },
        getTargetKeys: function () {
            return this.getMockData()
                .filter(() => {
                        1
                })
                .map(item => item);
        },
        handleChange3: function (newTargetKeys) {
            this.targetKeys3 = newTargetKeys;
        },
        render3: function (item) {
            return item.label ;
        },
        reloadMockdata: function () {
            this.data3 = this.getMockData();
            this.targetKeys3 = this.getTargetKeys();
        },
        /**
         *  上传组件
         */
        handleView(name) {
            this.imgName = name;
            this.visible = true;
        },
        handleRemove(file) {
            // 从 upload 实例删除数据
            const fileList = this.uploadList;
            // this.showcamera=true;
            this.uploadList.splice(fileList.indexOf(file), 1);
        },
        handleSuccess(res, file) {
            // 因为上传过程为实例，这里模拟添加 url
            file.menuCoverPic = res.url;
            file.name = res.url;
            vm.uploadList.add(file)
        },
        handleBeforeUpload() {
            const check = this.uploadList.length < 2;
            // this.showcamera=false;
            if (!check) {
                this.$Notice.warning({
                    title: '最多只能上传 1 张图片。'
                });
            }
            return check;
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
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        handleSuccessPicUrl: function (res, file) {
            vm.servermenuPlan.menuCoverPic = file.response.url;
        },
        /**
         *  添加早餐菜品
         */

        addfoodlist:function(){
            this.foodlist.push({
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:'',
                menuDate:''

            })},
        /**
         *  删除早餐餐品
         */
        deletefood:function(i){
            this.foodlist.splice(i,1);
        },
        /**
         * 添加早餐加餐
         */
        addfoodlistadd() {
            this.foodlistadd.push({
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:''
            })
        },
        /**
         * 删除早餐加餐
         */
        deletefoodadd:function (i) {
            this.foodlistadd.splice(i,1);
        },
        /**
         *  添加午餐菜品
         */

        addfoodlist1:function(){
            this.foodlist1.push({
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:''

            });
        },
        /**
         *  删除午餐餐品
         */
        deletefood1:function(i){
            this.foodlist1.splice(i,1);
        },
        /**
         * 添加午餐加餐
         */
        addfoodlistadd1() {
            this.foodlistadd1.push({
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:''
            })
        },
        /**
         * 删除午餐加餐
         */
        deletefoodadd1:function (i) {
            this.foodlistadd1.splice(i,1);
        },
        /**
         *  添加晚餐菜品
         */

        addfoodlist2:function(){
            this.foodlist2.push({
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:''

            });
        },
        /**
         *  删除晚餐餐品
         */
        deletefood2:function(i){
            this.foodlist2.splice(i,1);
        },
        /**
         * 添加晚餐加餐
         */
        addfoodlistadd2() {
            this.foodlistadd2.push({
                dishesId:'',
                dishesName:'',
                dishesCoverPic:'',
                dishesCalories:''
            })
        },
        /**
         * 删除晚餐加餐
         */
        deletefoodadd2:function (i) {
            this.foodlistadd2.splice(i,1);
        },
        cancelsumbit:function(){
            this.showfoods=false;
        },

        selectfood:function(event,i){
            let num=event.value;
            this.foodlist[i].dishesId=this.targetKeys3[num].id;
            this.foodlist[i].dishesCalories=this.targetKeys3[num].dishesCalories;
            this.foodlist[i].dishesCoverPic=this.targetKeys3[num].dishesCoverPic;
            this.foodlist[i].dishesName=this.targetKeys3[num].dishesName;
        },
        selectfood1:function(event,i){
            let num=event.value;
            this.foodlistadd[i].dishesId=this.targetKeys3[num].id;
            this.foodlistadd[i].dishesCalories=this.targetKeys3[num].dishesCalories;
            this.foodlistadd[i].dishesCoverPic=this.targetKeys3[num].dishesCoverPic;
            this.foodlistadd[i].dishesName=this.targetKeys3[num].dishesName;
        },
        selectfood2:function(event,i){
            let num=event.value;
            this.foodlist1[i].dishesId=this.targetKeys3[num].id;
            this.foodlist1[i].dishesCalories=this.targetKeys3[num].dishesCalories;
            this.foodlist1[i].dishesCoverPic=this.targetKeys3[num].dishesCoverPic;
            this.foodlist1[i].dishesName=this.targetKeys3[num].dishesName;
        },
        selectfood3:function(event,i){
            let num=event.value;
            this.foodlistadd1[i].dishesId=this.targetKeys3[num].id;
            this.foodlistadd1[i].dishesCalories=this.targetKeys3[num].dishesCalories;
            this.foodlistadd1[i].dishesCoverPic=this.targetKeys3[num].dishesCoverPic;
            this.foodlistadd1[i].dishesName=this.targetKeys3[num].dishesName;
        },
        selectfood4:function(event,i){
            let num=event.value;
            this.foodlist2[i].dishesId=this.targetKeys3[num].id;
            this.foodlist2[i].dishesCalories=this.targetKeys3[num].dishesCalories;
            this.foodlist2[i].dishesCoverPic=this.targetKeys3[num].dishesCoverPic;
            this.foodlist2[i].dishesName=this.targetKeys3[num].dishesName;
        },
        selectfood5:function(event,i){

            let num=event.value;
            this.foodlistadd2[i].dishesId=this.targetKeys3[num].id;
            this.foodlistadd2[i].dishesCalories=this.targetKeys3[num].dishesCalories;
            this.foodlistadd2[i].dishesCoverPic=this.targetKeys3[num].dishesCoverPic;
            this.foodlistadd2[i].dishesName=this.targetKeys3[num].dishesName;
        },
        created:function(){
            this.getUser();
        }

    },

    mounted() {
        // this.adddd();
        this.data3=this.getMockData();
        this.targetKeys3=this.getTargetKeys();
        this.uploadList = this.$refs.upload.fileList;
        this.getUser();
    }
});
