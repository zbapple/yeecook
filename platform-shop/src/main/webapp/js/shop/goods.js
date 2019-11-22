$(function () {
    $("#jqGrid").Grid({
        url: '../goods/list',
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '供应商', name: 'companyName', index: 'supplier_id', width:80},
            // {label: '供应商logo',name:'companyLogo',index:'supplier_id',width:100,
            //     formatter: function (value) {
            //         return transImg(value);}
            // },
            {label: '商品类型', name: 'categoryName', index: 'category_id', width: 60},
            {label: '商品名称', name: 'name', index: 'name', width: 160},
            {label: '商品图片', name: 'primaryPicUrl', index: 'primary_pic_url', width: 80,
                formatter: function (value) {
                            return transImg(value);}},
            {label: '品牌', name: 'brandName', index: 'brand_id', width: 60},

            {
                label: '录入日期', name: 'addTime', index: 'add_time', width: 110, formatter: function (value) {
                    return transDate(value, 'yyyy-MM-dd hh:mm:ss');
                }
            },
            // {label: '属性类别', name: 'attributeCategoryName', index: 'attribute_category', width: 80},
            // {label: '激活码批次', name: 'batchId', index: 'batch_id', width: 80},
            {label: '零售价格', name: 'retailPrice', index: 'retail_price', width: 60},
            {label: '商品库存', name: 'goodsNumber', index: 'goods_number', width: 60},
            // {label: '销售量', name: 'sellVolume', index: 'sell_volume', width: 80},
            // {label: '市场价', name: 'marketPrice', index: 'market_price', width: 80},
            // {
            //     label: '热销', name: 'isHot', index: 'is_hot', width: 80, formatter: function (value) {
            //         return transIsNot(value);
            //     }
            // },
            {
                label: '是否上架', name: 'isOnSale', index: 'is_on_sale', width: 50,
                formatter: function (value) {
                    return transIsNot(value);
                }
            },
            {  label:'操作',name:'checking', width: 170,index:'checking',sortable:false, formatter: function(value,col,row){
                    return  '<button  style="width: 62px;line-height: 30px;border: none;outline:none;background-color: #f90;color: white;border-radius: 4px" onclick="vm.isSale('+  row.id  +')">上架/下架</button>'+
                        '<button style="border: none;outline:none;width: 60px;line-height: 30px;margin-left: 10px;background-color: #f90;color: white;border-radius: 4px" onclick="vm.setSpecification('+  row.id  +')">设置规格</button>'
                        +'<button style="border: none;outline:none;width: 60px;line-height: 30px;margin-left: 10px;background-color: #2db7f5;color: white;border-radius: 4px" onclick="vm.lookDetail('+  row.id  +')">查看商品</button>';

                }
            },
        ]

    });
    vm.getSuppliers();
    $('#goodsDesc').editable({
        inlineMode: false,
        alwaysBlank: true,
        height: '500px', //高度
        minHeight: '200px',
        language: "zh_cn",
        spellcheck: false,
        plainPaste: true,
        enableScript: false,
        imageButtons: ["floatImageLeft", "floatImageNone", "floatImageRight", "linkImage", "replaceImage", "removeImage"],
        allowedImageTypes: ["jpeg", "jpg", "png", "gif"],
        imageUploadURL: '../sys/oss/upload',
        imageUploadParams: {id: "edit"},
        imagesLoadURL: '../sys/oss/queryAll'
    });
    $('#jqGrid').css("textAlign","center");
});

var ztree;

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        details:false,
        title: null,
        uploadList: [],
        imgName: '',
        visible: false,
        user:{},
        goods: {
            primaryPicUrl: '',
            listPicUrl: '',
            categoryId: '',
            isOnSale: 1,
            isNew: 1,
            isAppExclusive: 0,
            isLimited: 0,
            isHot: 0,
            categoryName: '',
        },
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ],
            supplierId: [
                {required: true, message: '供应商不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: '',
            supplierId:''
        },
        goodsSpecificationEntityList:{},
        suppliers:[],//供应商名称
        brands: [],//品牌
        macros: [],//商品单位
        attributeCategories: []//属性类别
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.details = false;
            vm.title = "新增";
            vm.uploadList = [];
            vm.goods = {
                primaryPicUrl: '',
                listPicUrl: '',
                categoryId: '',
                isOnSale: 1,
                isNew: 1,
                isAppExclusive: 0,
                isLimited: 0,
                isHot: 0,
                categoryName: ''
            };
            $('#goodsDesc').editable('setHTML', '');
            vm.getCategory();
            vm.brands = [];
            vm.macros = [];
            vm.attributeCategories = [];
            // vm.attribute = [];
            vm.getBrands();
            vm.getMacro();
            vm.getAttributeCategories();
            // vm.getAttributes('');
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.details = false;
            vm.title = "修改";
            vm.uploadList = [];
            vm.getInfo(id);
            vm.getBrands();
            vm.getMacro();
            vm.getAttributeCategories();
            vm.getGoodsGallery(id);

        },
        /**
         * 查看详情
         */
        lookDetail:function(rowId){
            vm.details=true;
            vm.title = "商品详情";
            vm.getInfo(rowId);
            vm.uploadList=[];
        },
        /**
         * 获取供应商
         */
        getSuppliers: function () {
            Ajax.request({
                url: "../supplier/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.suppliers = r.list;
                }
            });
        },
        /**
         * 获取品牌
         */
        getBrands: function () {
            Ajax.request({
                url: "../brand/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.brands = r.list;
                }
            });
        },
        /**
         * 获取单位
         */
        getMacro: function () {
            Ajax.request({
                url: "../sys/macro/queryMacrosByValue?value=goodsUnit",
                async: true,
                successCallback: function (r) {
                    vm.macros = r.list;
                }
            });
        },
        C: function () {
            Ajax.request({
                url: "../sys/user/info",
                async: true,
                successCallback: function (r) {
                    vm.user = r.user;
                }
            });
        },
        getGoodsGallery: function (id) {//获取商品顶部轮播图
            Ajax.request({
                url: "../goodsgallery/queryAll?goodsId=" + id,
                async: true,
                successCallback: function (r) {
                    vm.handleSuccessPicUrluploadList = r.list;
                }
            });
        },
        getAttributeCategories: function () {
            Ajax.request({
                url: "../attributecategory/queryAll",
                async: true,
                successCallback: function (r) {
                    vm.attributeCategories = r.list;
                }
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.goods.id == null ? "../goods/save" : "../goods/update";
            vm.goods.goodsDesc = $('#goodsDesc').editable('getHTML');
            vm.goods.goodsImgList = vm.uploadList;
            if(vm.goods.supplierId == null){
                vm.goods.supplierId=vm.user.deptId;
            }
            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.goods),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        setSpecification: function (rowId) {
            openWindow({
                type: 2,
                title: '商品规格',
                content: '../shop/goodsspecification.html?goodsId=' + rowId
            })
        },
        // openPro: function () {
        //     var id = getSelectedRow("#jqGrid");
        //     if (id == null) {
        //         return;
        //     }
        //     openWindow({
        //         type: 2,
        //         title: '产品设置',
        //         content: '../shop/product.html?goodsId=' + id
        //     });
        // },
        // unSale: function () {
        //     var id = getSelectedRow("#jqGrid");
        //     if (id == null) {
        //         return;
        //     }
        //     confirm('确定要下架选中的商品？', function () {
        //
        //         Ajax.request({
        //             type: "POST",
        //             url: "../goods/unSale",
        //             contentType: "application/json",
        //             params: JSON.stringify(id),
        //             successCallback: function (r) {
        //                 alert('操作成功', function (index) {
        //                     vm.reload();;
        //                 });
        //             }
        //         });
        //
        //     });
        // },
        // enSale: function () {
        //     var id = getSelectedRow("#jqGrid");
        //     if (id == null) {
        //         return;
        //     }
        //     confirm('确定要上架选中的商品？', function () {
        //         Ajax.request({
        //             type: "POST",
        //             url: "../goods/enSale",
        //             params: JSON.stringify(id),
        //             contentType: "application/json",
        //             type: 'POST',
        //             successCallback: function () {
        //                 alert('提交成功', function (index) {
        //                     vm.reload();
        //                 });
        //             }
        //         });
        //     });
        // },
        /**
         *  上下架状态修改
         */
        isSale:function(rowId){
            if (rowId !=null) {
                Ajax.request({
                    url: "../goods/info/" + rowId,
                    async: true,
                    successCallback: function (r) {
                           vm.goods=r.goods;
                           vm.goods.isOnSale=r.goods.isOnSale;
                            if (vm.goods.isOnSale == 0) {
                                confirm('确定上架该商品吗', function () {
                                    Ajax.request({
                                        type: "POST",
                                        url: "../goods/enSale",
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
                            } else if (vm.goods.isOnSale == 1) {
                                confirm('确定下架该商品吗', function () {
                                    Ajax.request({
                                        type: "POST",
                                        url: "../goods/unSale",
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
                            }
                    }
                });
            }
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录(将会进入回收箱)？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../goods/delete",
                    contentType: "application/json",
                    params: JSON.stringify(ids),
                    successCallback: function (r) {
                        alert('操作成功', function (index) {
                            vm.reload();;
                        });
                    }
                });

            });
        },
        getInfo: function (id) {
            Ajax.request({
                url: "../goods/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.goods = r.goods;
                    $('#goodsDesc').editable('setHTML', vm.goods.goodsDesc);
                    vm.getCategory();
                    vm.uploadList=vm.goods.goodsImgList;
                    vm.goodsSpecificationEntityList=vm.goods.goodsSpecificationEntityList;
                }
            });
        },
        getSupplier: function (){
            Ajax.request({
                url: "../supplier/name/",
                async: true,
                successCallback: function (r) {
                    vm.user = r.supplier;
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            vm.details = false;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name,
                    'supplierId': vm.q.supplierId
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        reloadSearch: function() {
            vm.q = {
                name:'',
                supplierId:''
            }
            vm.reload();
        },
        getCategory: function () {
            //加载分类树
            Ajax.request({
                url: "../category/queryAll",
                async: true,
                successCallback: function (r) {
                    ztree = $.fn.zTree.init($("#categoryTree"), setting, r.list);
                    var node = ztree.getNodeByParam("id", vm.goods.categoryId);
                    if (node) {
                        ztree.selectNode(node);
                        vm.goods.categoryName = node.name;
                    } else {
                        node = ztree.getNodeByParam("id", 0);
                        ztree.selectNode(node);
                        vm.goods.categoryName = node.name;
                    }
                }
            });
        },
        categoryTree: function () {
            openWindow({
                title: "选择类型",
                area: ['300px', '450px'],
                content: jQuery("#categoryLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.goods.categoryId = node[0].id;
                    vm.goods.categoryName = node[0].name;

                    layer.close(index);
                }
            });
        },
        /**
         * 跳转回收站
         */
       goodsHistory :function(){
            openWindow({
                type: 2,
                title: '商品回收站',
                content: '../shop/goodshistory.html'
            })
        },
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
            file.imgUrl = res.url;
            file.name = res.url;
            vm.uploadList.add(file);
        },
        handleBeforeUpload() {
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
            vm.goods.primaryPicUrl = file.response.url;
        },
        handleSuccessListPicUrl: function (res, file) {
            vm.goods.listPicUrl = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.goods.primaryPicUrl;
            eyeImage(url);
        },
        eyeImageListPicUrl: function () {
            var url = vm.goods.listPicUrl;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        }
    },
    mounted() {
        this.uploadList = this.$refs.upload.fileList;
        this.getSupplier();
    }
});