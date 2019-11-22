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
                        '<span class="label label-danger">未签约</span>':
                        '<span class="label label-success">已签约</span>';
                }
            },
            {  label:'操作',name:'checking', width: 120,index:'checking',sortable:false, formatter: function(value,col,row){
                    return  '<button  style="width: 40px;line-height: 30px;border: none;outline:none" onclick="vm.lookDetail('+  row.id  +')">查看</button>'+
                        '<button style="border: none;outline:none;width: 40px;line-height: 30px;margin-left: 10px" onclick="vm.openStatus(' + row.id +  ')">审核</button>'
                        +'<button style="border: none;outline:none;width: 40px;line-height: 30px;margin-left: 10px" onclick="vm.saveDetail(' + row.id +  ')">完善</button>';

                }
            },
            ]
    });
    vm.getCateringServiceOrgNames();
    vm.getNutritionMenuTypes();
    vm.getUserNames();
    $('#jqGrid').css("textAlign","center");
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        details: false,
        addFoods:false,
        updateFoods:false,
        updateInfo:false,
        saveDetails:false,
        insert:false,
        foodsDetails:false,
        visible: false,
        imgName: '',
        title: null,
        arr:[],
        num:'',
        id:'',
        begin:"",
        end:"",
        nutritionMenuTypeList:['月子餐','老人餐','营养餐'],
        serviceStageList:['第一周疗养阶段','第二周疗养阶段','第三周疗养阶段','第四周疗养阶段'],
        uploadList: [],
        listType:{},
        userReport:{},
        menuPlan: {
            userId:'',
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
            menuCoverPics:[{

            }],
            menuCoverPic:'',
            nutritionPrinciple:'',
            menuStatus: '',
        },
        menuMap:[],
        foods:[],
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ],
            nickName: [
                {required: true, message: '用户名称不能为空', trigger: 'blur'}
            ],
            cateringServiceOrgName: [
                {required: true, message: '机构名称不能为空', trigger: 'blur'}
            ],
            nutritionMenuType: [
                {required: true, message: '计划类型不能为空', trigger: 'blur'}
            ],
            menuSn:[
                {required: true, message: '套餐编号不能为空', trigger: 'blur'}
            ],
            menuName:[
                {required: true, message: '套餐名称不能为空', trigger: 'blur'}
            ]
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
        UserNames:[],
        menuInfoMsg:{},
        menumaptype:[],
        menumapweight:{},
        menuSysdept:{},
        menumapst:'',
        menumapet:'',
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
        dishes:[
            {
                id:'',
                dishesCalories:'',
                dishesCoverPic:'',
                dishesName:''
            }        ],
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
            vm.insert=true;
            vm.showList = false;
            vm.uploadList = [];
            vm.title = "新增计划";
            vm.menuPlan ={
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
                menuStatus: '0',
            }
            vm.arr=[];
            vm.userReport={};
        },
        /**
         *  查看详情操作
         */
        lookDetail: function (rowId) {
            vm.details = true;
            vm.foodsDetails=false;
            vm.title = "详情"
            this.id=rowId;
            Ajax.request({
                url: "../menuPlan/menuInfo/" + rowId,
                async: true,
                successCallback: function (r) {
                    vm.menuMap = r.menuMap;
                    vm.menuInfoMsg=vm.menuMap.infoMsg;
                    vm.menuSysdept=vm.menuMap.sysDept;
                    vm.menumaptype=vm.menuMap.menuType;
                    vm.menumapweight=vm.menuMap.weight||'';
                    vm.menumapst=vm.menuMap.serviceCycleSt;
                    vm.menumapet=vm.menuMap.serviceCycleEt;
                    vm.getAll(vm.menumapst, vm.menumapet);
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
        checkFoods :function(event){
          vm.foodsDetails=true;
          vm.title ="当天餐品"
            let mid=this.id;
            let todays=event;
            let  me=mid+","+todays;
            Ajax.request({
                url: "../menuPlan/todayInfo/",
                async: true,
                contentType: "application/json;charset=UTF-8",
                params:JSON.stringify(me),
                type: "POST",
                successCallback: function (r) {
                    vm.menuPlan=r.menuPlan;
                    vm.foodlist=vm.menuPlan.foodlist;
                    vm.foodlistadd=vm.menuPlan.foodlistadd;
                    vm.foodlist1=vm.menuPlan.foodlist1;
                    vm.foodlistadd1=vm.menuPlan.foodlistadd1;
                    vm.foodlist2=vm.menuPlan.foodlist2;
                    vm.foodlistadd2=vm.menuPlan.foodlistadd2;
                }
            });
        },
        updateFood:function(event){
            vm.getUserNames();
            vm.updateFoods=true;
            vm.title="修改餐品";
            vm.getDishes();
            let mid=this.id;
            let todays=event;
            let  me=mid+","+todays;
            Ajax.request({
                url: "../menuPlan/todayInfo/",
                async: true,
                contentType: "application/json;charset=UTF-8",
                params:JSON.stringify(me),
                type: "POST",
                successCallback: function (r) {
                    vm.menuPlan=r.menuPlan;
                    vm.foodlist=vm.menuPlan.foodlist;
                    console.log(vm.foodlist);
                    vm.foodlistadd=vm.menuPlan.foodlistadd;
                    vm.foodlist1=vm.menuPlan.foodlist1;
                    vm.foodlistadd1=vm.menuPlan.foodlistadd1;
                    vm.foodlist2=vm.menuPlan.foodlist2;
                    vm.foodlistadd2=vm.menuPlan.foodlistadd2;
                }
            });
        },
        addFood:function(event){
            vm.getUserNames();
            vm.addFoods=true;
            vm.title = "添加餐品";
            vm.getDishes();
            // let mid=this.id;
            // let todays=event;
            // let  me=mid+","+todays;
            // Ajax.request({
            //     url: "../menuPlan/todayInfo/",
            //     async: true,
            //     contentType: "application/json;charset=UTF-8",
            //     params:JSON.stringify(me),
            //     type: "POST",
            //     successCallback: function (r) {
            //         vm.menuPlan=r.menuPlan;
            //         vm.foodlist=vm.menuPlan.foodlist;
            //         vm.foodlistadd=vm.menuPlan.foodlistadd;
            //         vm.foodlist1=vm.menuPlan.foodlist1;
            //         vm.foodlistadd1=vm.menuPlan.foodlistadd1;
            //         vm.foodlist2=vm.menuPlan.foodlist2;
            //         vm.foodlistadd2=vm.menuPlan.foodlistadd2;
            //     }
            // });

        },
        update: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;}
                vm.updateInfo=true;
                vm.showList = false;
                vm.title = "修改";
            this.id=id;
            Ajax.request({
                url: "../menuPlan/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.uploadList = [];
                            vm.uploadList.push({
                                menuCoverPic:r.menuPlan.menuCoverPic
                            })

                    vm.menuPlan = r.menuPlan;
                            console.log(vm.menuPlan);
                    vm.getAll(vm.menuPlan.serviceCycleSt,vm.menuPlan.serviceCycleEt);
                }
            });
               vm.getCateringServiceOrgNames();


        },
        saveOrUpdate: function (event) {
            var url = vm.menuPlan.id == null ? "../menuPlan/save" : "../menuPlan/update";
            vm.menuPlan.menuCoverPics=vm.uploadList;
            Ajax.request({
                url: url,
                params: JSON.stringify(vm.menuPlan),
                type: "POST",
                contentType: "application/json",
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                        vm.menuPlan={};
                        vm.zaolist=[];
                        vm.wulist=[];
                        vm.wanlist=[];
                        vm.zaoaddlist=[];
                        vm.wuaddlist=[];
                        vm.wanaddlist=[];
                    });
                }
            });
        },
        /**
         * 完善
         */
        saveDetail:function(rowId){
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;}
                vm.arr=[];
            vm.saveDetails=true;
            vm.title = "完善";
            vm.getInfo(id);
            vm.getDishes();
            vm.menuPlan.menuCoverPics = vm.uploadList;
            Ajax.request({
                    url: "../menuPlan/menuInfo/" + rowId,
                    async: true,
                    successCallback: function (r) {
                        vm.menumapst=r.menuMap.serviceCycleSt;
                        vm.menumapet=r.menuMap.serviceCycleEt;
                        this.begin=vm.menumapst;
                        this.end=vm.menumapet;
                        if (this.begin !=null && this.end !=null) {
                            vm.getAll(this.begin, this.end);
                        }else {
                            alert('请重新选择');
                        }
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
                    vm.menuPlan = r.menuPlan;
                }
            });
        },
        //返回上一页
        reloadpage:function(event){
            if (vm.insert == true) {
            vm.showList=false;
            vm.details = false;
            vm.addFoods = false;
            vm.updateFoods=false;
            vm.saveDetails=false;
            vm.foodsDetails=false;
            vm.updateInfo=false;
            vm.insert=true;
            vm.title = "新增计划";
        }else if (vm.saveDetails ==true){
                vm.showList=false;
                vm.details = false;
                vm.addFoods = false;
                vm.saveDetails=true;
                vm.title = "完善"
                vm.foodsDetails=false;
                vm.updateFoods=false;
                vm.updateInfo=false;
                vm.insert=false;
            }else if (vm.details == true){
                vm.showList=false;
                vm.details = true;
                vm.title = "详情"
                vm.addFoods = false;
                vm.saveDetails=false;
                vm.foodsDetails=false;
                vm.updateFoods=false;
                vm.updateInfo=false;
                vm.insert=false;
            }else if (vm.updateInfo == true) {
                vm.showList=false;
                vm.details = false;
                vm.title = "修改"
                vm.addFoods = false;
                vm.saveDetails=false;
                vm.foodsDetails=false;
                vm.updateFoods=false;
                vm.updateInfo=true;
                vm.update();
                vm.insert=false;
            }else {
                alert("请返回重新操作");
            }
        },

        reload: function (event) {
            vm.showList = true;
            vm.details = false;
            vm.addFoods = false;
            vm.updateFoods=false;
            vm.saveDetails=false;
            vm.insert=false;
            vm.updateInfo=false;
            vm.foodsDetails=false;
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
        // handleSubmit: function (){
        //         vm.saveOrUpdate();
        // },
        handleReset: function (name) {
            handleResetForm(this, name);
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
         * 获取选定机构id
         */
            selectId:function(val){
            for(let i=0;i<this.CateringServiceOrgNames.length;i++){
                if(this.CateringServiceOrgNames[i].name==val){
                    this.menuPlan.deptId=this.CateringServiceOrgNames[i].deptId;
                }
            }
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
                    this.UserNames=vm.UserNames;
                }
            });
        },
        /**
         * 获取用户身体数据
         */
        getUserReport :function(val) {
            let id = null;
            for (let i = 0; i < this.UserNames.length; i++) {
                if (this.UserNames[i].nickname == val) {
                    id = this.UserNames[i].id;
                }
            }
            if (id != null) {
                Ajax.request({
                    url: "../userhealthreport/queryUserReport/" + id,
                    async: true,
                    successCallback: function (r) {
                        if (r.userReport !=null) {
                            if (r.userReport.bmi < 18.5) {
                                r.userReport.bodyShape = "轻体重";
                            } else if (r.userReport.bmi >= 18.5 && r.userReport.bmi < 24.0) {
                                r.userReport.bodyShape = "健康体重";
                            } else if (r.userReport.bmi >= 24 && r.userReport.bmi < 28) {
                                r.userReport.bodyShape = "超重";
                            } else if (r.userReport.bmi >= 28) {
                                r.userReport.bodyShape = "肥胖";
                            }
                        }else {
                            r.userReport={};
                        }
                        vm.userReport = r.userReport;
                    }
                });
            }else{
                vm.userReport={};
            }
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
                }
            });
        },
        /**
         *  审核窗口
         */
        openStatus: function (rowId) {
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
        /**
         * 餐品
         */
        getDishes:function(){
            let that=this;
            Ajax.request({
                url:'../dishes/queryAll',
                successCallback: function (r) {
                    vm.dishes=r.list;
                    this.dishes=vm.dishes;
                }
            })
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
            this.uploadList.splice(fileList.indexOf(file), 1);
        },
        handleSuccess(res, file) {
            // 因为上传过程为实例，这里模拟添加 url
            file.menuCoverPic = res.url;
            file.name = res.url;
            vm.uploadList.add(file)
        },
        handleBeforeUpload() {
            const check = this.uploadList.length < 1;
            if (!check) {
                this.$Notice.warning({
                    title: '封面最多只能上传 1 张图片。'
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
            vm.menuPlan.menuCoverPic = file.response.url;
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
        selectfood:function(event,i){
                let num=event.value;
            if (this.foodlist[i].dishesName != null){
                this.foodlist[i].dishesId=this.dishes[num].id;
                this.foodlist[i].dishesCalories=this.dishes[num].dishesCalories;
                this.foodlist[i].dishesCoverPic=this.dishes[num].dishesCoverPic;
                this.foodlist[i].dishesName=this.dishes[num].dishesName;
                }else{
                this.foodlist[i].dishesCoverPic=null;
                this.foodlist[i].dishesCalories=null;
            }
        },
        selectfood1:function(event,i){
            let num=event.value;
            if (this.foodlistadd[i].dishesName != null){
                this.foodlistadd[i].dishesId=this.dishes[num].id;
                this.foodlistadd[i].dishesCalories=this.dishes[num].dishesCalories;
                this.foodlistadd[i].dishesCoverPic=this.dishes[num].dishesCoverPic;
                this.foodlistadd[i].dishesName=this.dishes[num].dishesName;
            }else {
                this.foodlistadd[i].dishesCoverPic = null;
                this.foodlistadd[i].dishesCalories = null;
            }
        },
        selectfood2:function(event,i){
            let num=event.value;
            if (this.foodlist1[i].dishesName != null){
                this.foodlist1[i].dishesId=this.dishes[num].id;
                this.foodlist1[i].dishesCalories=this.dishes[num].dishesCalories;
                this.foodlist1[i].dishesCoverPic=this.dishes[num].dishesCoverPic;
                this.foodlist1[i].dishesName=this.dishes[num].dishesName;
            }else {
                this.foodlist1[i].dishesCoverPic = null;
                this.foodlist1[i].dishesCalories = null;
            }
            },
        selectfood3:function(event,i){
            let num=event.value;
            if (this.foodlistadd1[i].dishesName != null){
                this.foodlistadd1[i].dishesId=this.dishes[num].id;
                this.foodlistadd1[i].dishesCalories=this.dishes[num].dishesCalories;
                this.foodlistadd1[i].dishesCoverPic=this.dishes[num].dishesCoverPic;
                this.foodlistadd1[i].dishesName=this.dishes[num].dishesName;
            }else {
                this.foodlistadd1[i].dishesCoverPic = null;
                this.foodlistadd1[i].dishesCalories = null;
            }
        },
        selectfood4:function(event,i){
            let num=event.value;
            if (this.foodlist2[i].dishesName != null){
                this.foodlist2[i].dishesId=this.dishes[num].id;
                this.foodlist2[i].dishesCalories=this.dishes[num].dishesCalories;
                this.foodlist2[i].dishesCoverPic=this.dishes[num].dishesCoverPic;
                this.foodlist2[i].dishesName=this.dishes[num].dishesName;
            }else {
                this.foodlist2[i].dishesCoverPic = null;
                this.foodlist2[i].dishesCalories = null;
            }
        },
        selectfood5:function(event,i){

            let num=event.value;
            if (this.foodlistadd2[i].dishesName != null){
                this.foodlistadd2[i].dishesId=this.dishes[num].id;
                this.foodlistadd2[i].dishesCalories=this.dishes[num].dishesCalories;
                this.foodlistadd2[i].dishesCoverPic=this.dishes[num].dishesCoverPic;
                this.foodlistadd2[i].dishesName=this.dishes[num].dishesName;
            }else {
                this.foodlistadd2[i].dishesCoverPic = null;
                this.foodlistadd2[i].dishesCalories = null;
            }
        },
        getSt(date) {
            let begin = date.substring(5,10);
            this.begin=date;
            this.getAll(this.begin,this.end);

        },
        getEt(date) {
            let end = date.substring(5,10);
            this.end=date;
            this.getAll(this.begin,this.end);

        },
        getAll(begin, end){// 开始日期和结束日期
            if(!begin || !end){			// 判空
                console.log('有时间为空');
                return false;
            }
            //不足补0
            appendZero=function (obj) {
                if (obj < 10) {
                    return '0' + obj;
                } else {
                    return obj;
                }
            }
            // 转换
            Date.prototype.format = function(){
                let s = "";				// 定义一个字符串，目的：时间格式按照我们的要求拼接
                let month = appendZero(this.getMonth() + 1);
                let day = appendZero(this.getDate());
                s += this.getFullYear() + "-";
                s += month  + "-";
                s += day;
                return s;			// 得到的格式如 "2019-09-19"
            }
            let ab = begin.split('-');			// 把日期参数分割
            let ae = end.split('-');
            let db = new Date();
            db.setUTCFullYear(ab[0], ab[1]-1, ab[2]);			// 返回符合UTC的时间格式
            let de = new Date();
            de.setUTCFullYear(ae[0], ae[1]-1, ae[2]);
            let unixDb = db.getTime();
            let unixDe = de.getTime();
            let arr = [];
            for(let k = unixDb; k <= unixDe;){
                arr.push(new Date(k).format("yyyy/MM/dd"));
                k = k + 24 * 60 * 60 * 1000;
            }
            this.arr=arr;
        }
    },
    mounted() {
        this.uploadList = this.$refs.upload.fileList;
        this.getUser();
    }
});
