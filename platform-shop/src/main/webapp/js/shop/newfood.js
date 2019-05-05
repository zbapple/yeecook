
$(function () {
    $("#jqGrid").Grid({
        url: '../newfood/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '菜名称', name: 'name', index: 'name', width: 120},
            {label: '标题一', name: 'tile1', index: 'tile_1', width: 150},

            {
                label: '研发大师图像', name: 'yfdsPic', index: 'yfds_pic', width: 80, formatter: function (value) {
                    return transImg(value);
                }
            },
            {label: '研发大师名称', name: 'yfdsName', index: 'yfds_name', width: 80},

            {label: '观看次数', name: 'plays', index: 'plays', width: 80},

            {
                label: '食材图片', name: 'foodImg', index: 'food_img', width: 80, formatter: function (value) {
                    return transImg(value);
                }
            },

            {
                label: '菜谱页面URL', name: 'url', index: 'url', width: 150, formatter: function (value) {
                    return "<a href='javascript:void(0)' style='color:blue;' onclick='vm.gotoPost(\"" + value + "\")'>" + value + "</a>";
                }
            },{
                label: '食材页面URL', name: 'foodUrl', index: 'url', width: 150, formatter: function (value) {
                    return "<a href='javascript:void(0)' style='color:blue;' onclick='vm.gotoPost(\"" + value + "\")'>" + value + "</a>";
                }
            },{
                label: '商家页面URL', name: 'supplieUrl', index: 'url', width: 150, formatter: function (value) {
                    return "<a href='javascript:void(0)' style='color:blue;' onclick='vm.gotoPost(\"" + value + "\")'>" + value + "</a>";
                }
            }]
    });
    $(".export-excel").on("click", function (e) {
        e.preventDefault();

        let ids = getSelectedRows("#jqGrid");
        if (ids == null) {
            this.$Notice.warning({
                title: '请选择数据',
                desc: '请勾选需要打印的页面！'
            });
            return;
        }

        var width=vm.q.width;
        var height=vm.q.height;
        if(width==null||width==''){
            width=200
        }
        if(height==null||height==''){
            height=200
        }
        window.open("../newfood/imgDownLoad?" +
            "id=" +ids+
            "&width="+width+
            "&height="+height+
            "&format="+1
        );
    });
    $(".export-excel2").on("click", function (e) {
        e.preventDefault();

        let ids = getSelectedRows("#jqGrid");
        if (ids == null) {
            this.$Notice.warning({
                title: '请选择数据',
                desc: '请勾选需要打印的页面！'
            });
            return;
        }

        let width=vm.q.width;
        let height=vm.q.height;
        if(width==null||width==''){
            width=200
        }
        if(height==null||height==''){
            height=200
        }

        window.open("../newfood/imgDownLoad?" +
            "id=" +ids+
            "&width="+width+
            "&height="+height+
            "&format="+2
        );
    });
    $(".export-excel3").on("click", function (e) {
        e.preventDefault();

        let ids = getSelectedRows("#jqGrid");
        if (ids == null) {
            this.$Notice.warning({
                title: '请选择数据',
                desc: '请勾选需要打印的页面！'
            });
            return;
        }

        var width=vm.q.width;
        var height=vm.q.height;
        if(width==null||width==''){
            width=200
        }
        if(height==null||height==''){
            height=200
        }
        window.open("../newfood/imgDownLoad?" +
            "id=" +ids+
            "&width="+width+
            "&height="+height+
            "&format="+3
        );
    });
});

let vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        imgName: '',
        listurl1: '',
        listurl2: '',
        visible: false,
        newFood: {},
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ],
            foodId: [
                {required: true, message: '请选择食材', trigger: 'blur'}
            ],
            yfdsInfo: [
                {required: true, message: '请选择研发大师', trigger: 'blur'}
            ],
            supplieId: [
                {required: true, message: '请选择商家', trigger: 'blur'}
            ],
        },
        q: {
            name: '',
            width:'',
            hheight:''
        },
        yfdsInfos: [],
        supplies: [],
        foods: [],
        file: null,
        loadingStatus: false
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.newFood = {
                tile3Remark: '[{\n' +
                    '\timage: \'https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190310/1225313925f102.png\',\n' +
                    '\ttitle: \'免费激活视频\',\n' +
                    '\turl: \'fx/fx?\'\n' +
                    '}, {\n' +
                    '\timage: \'https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190310/122519176bce42.png\',\n' +
                    '\ttitle: \'申请免费食材\',\n' +
                    '\turl: \'scsq/scsq?\'\n' +
                    '}, {\n' +
                    '\timage: \'https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190310/122505678eb2e5.png\',\n' +
                    '\ttitle: \'微信交流群\'\n' +
                    '}]',
                yycsRemark: '[{\n' +
                    '\timage: \'https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190128/011921413450ca.png\',\n' +
                    '\ttitle: \'鱼蛋牛丸粉\'\n' +
                    '}, {\n' +
                    '\timage: \'https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190128/011938789fc356.png\',\n' +
                    '\ttitle: \'猪杂汤粉\'\n' +
                    '}, {\n' +
                    '\timage: \'https://yeecook-shop-pl.oss-cn-shenzhen.aliyuncs.com/upload/20190128/01200028651be8.png\',\n' +
                    '\ttitle: \'酱香汤粉\'\n' +
                    '}]',
                tile2:'----  课程简介  ----',
                tile3:'----  课程福利  ----',
                yycs:'应用菜式'


            };
            this.getYfdsInfos();
            this.getSupplies()
            this.getFoods();
        }
        ,handleStart: function (event, file, fileList) {
            this.loadingStatus = true;
        },
        update: function (event) {
            let id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            this.getYfdsInfos();
            this.getSupplies();
            this.getFoods();
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
        gotoPost: function (value) {
            openWindow({
                type: 2,
                title: "页面预览",
                area: ['30%', '600px'],
                content: [value]
            })
        },
        del: function (event) {
            let ids = getSelectedRows("#jqGrid");
            if (ids == null) {
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
        getInfo: function (id) {
            Ajax.request({
                url: "../newfood/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.newFood = r.newFood;
                }
            });
        },
        getYfdsInfos: function () {
            Ajax.request({
                url: "../yfdsinfo/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.yfdsInfos = r.list;
                }
            });
        },
        getSupplies: function () {
            Ajax.request({
                url: "../supplieinfo/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.supplies = r.list;
                }
            });
        },
        getFoods: function () {
            Ajax.request({
                url: "../foodinfo/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.foods = r.list;
                }
            });
        },
        foodsOnChange: function (a) {

            if (typeof(a.value.id) == "undefined") {
                return;
            }
            vm.newFood.foodId = a.value.id;
            vm.newFood.foodTile = a.value.foodTile;
            vm.newFood.foodRemark = a.value.foodRemark;
            vm.newFood.foodImg = a.value.foodImg;


        },
        yfdsOnChange: function (a) {

            if (typeof(a.value.id) == "undefined") {
                return;
            }
            vm.newFood.yfdsInfos1 = a.value.id;
            vm.newFood.yfdsInfo = a.value.id;
            vm.newFood.yfdsName = a.value.yfdsName;
            vm.newFood.yfdsDesc = a.value.yfdsDesc;
            vm.newFood.yfdsPic = a.value.yfdsPic;


        },
        supplieOnChange: function (a) {

            if (typeof(a.value.id) == "undefined") {
                return;
            }
            vm.newFood.supplieId = a.value.id;
            vm.newFood.supplieTile = a.value.supplieTile;
            vm.newFood.supplieDesc = a.value.supplieDesc;
            vm.newFood.supplieInfo = a.value.supplieInfo;
            vm.newFood.supplieImg = a.value.supplieImg;

            vm.newFood.foodSupTile = a.value.supplieTile;
            vm.newFood.foodSupRemark = a.value.supplieDesc;
            vm.newFood.foodSupInfo = a.value.supplieInfo;
            vm.newFood.foodSupImg = a.value.supplieImg;


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
        reloadSearch: function () {
            vm.q = {
                name: ''
            }
            vm.reload();
        },
        handleFormatError: function (file) {
            this.loadingStatus = false;
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 文件格式不正确.'
            });
        },
        fileUpErr: function (e){
            this.loadingStatus = false;
            this.$Notice.warning({
                title: '网络异常',
                desc: '上传失败'
            });
        },
        handleMaxSize: function (file) {
            this.loadingStatus = false;
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大'
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
        eyeImagePicUrl: function () {
            var url = vm.newFood.yfdsPic;
            eyeImage(url);
        },
        eyeImageUrl1: function () {
            var url = vm.listurl1;
            eyeImage(url);
        },
        eyeImageUrl2: function () {
            var url = vm.listurl2;
            eyeImage(url);
        },
        eyeImagefood: function () {
            var url = vm.newFood.foodImg;
            eyeImage(url);
        },
        eyeImageLog: function () {
            var url = vm.newFood.log;
            eyeImage(url);
        },
        eyeImageSupp: function () {
            var url = vm.newFood.supplieImg;
            eyeImage(url);
        },
        eyeImageSup: function () {
            var url = vm.newFood.foodSupImg;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        },
        handleSuccessPicUrl: function (res, file) {
            vm.newFood.yfdsPic = file.response.url;
            this.$Notice.warning({
                title: '成功',
                desc: '上传成功！'
            });
        }, handleSuccessVoidUrl: function (res, file) {
            vm.newFood.voidUrl = file.response.url;
            this.loadingStatus = false;
            this.$Notice.warning({
                title: '成功',
                desc: '上传成功！'
            });
        }, handleSuccessUrl1: function (res, file) {
            vm.listurl1 = file.response.url;
            this.$Notice.warning({
                title: '成功',
                desc: '上传成功！'
            });
        }, handleSuccessUrl2: function (res, file) {
            vm.listurl2 = file.response.url;
            this.$Notice.warning({
                title: '成功',
                desc: '上传成功！'
            });
        }
        , handleSuccessSuppUrl: function (res, file) {
            vm.newFood.supplieImg = file.response.url;
            this.$Notice.warning({
                title: '成功',
                desc: '上传成功！'
            });
        }, handleSuccessLogUrl: function (res, file) {
            vm.newFood.log = file.response.url;
            this.$Notice.warning({
                title: '成功',
                desc: '上传成功！'
            });
        }, handleSuccessfoodUrl: function (res, file) {
            vm.newFood.foodImg = file.response.url;
            this.$Notice.warning({
                title: '成功',
                desc: '上传成功！'
            });
        }, handleSuccessSupUrl: function (res, file) {
            vm.newFood.foodSupImg = file.response.url;
            this.$Notice.warning({
                title: '成功',
                desc: '上传成功！'
            });
        }
    }
});