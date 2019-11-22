$(function () {
    let goodsId = getQueryString("goodsId");
    let url = '../goodsspecification/list';
    if (goodsId) {
        url += '?goodsId=' + goodsId;
    }
    $("#jqGrid").Grid({
        url: url,
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {
                label: '商品', name: 'goodsName', index: 'goods_id', width: 80,align: 'center',
                cellattr:function(rowId, tv, rawObject, cm, rdata) {
                    return 'id=\'goodsName' + rowId + "\'";
                },
            },
            // {label: '规格', name: 'specificationName', index: 'specification_id', width: 80},
            {label: '规格', name: 'value', index: 'value', width: 80},
            {label: '规格库存', name: 'specificationNumber', index: 'specification_number', width: 80},
            {label: '规格价格', name: 'specificationPrice', index: 'specification_price', width: 80},
            {
                label: '规格图片', name: 'picUrl', index: 'pic_url', width: 80, formatter: function (value) {
                    return transImg(value);
                }
            },
            {label: '是否启用', name: 'isSale', index: 'is_sale', width: 80,
                formatter: function (value) {
                    return transIsNot(value);
                }},
            {  label:'操作',name:'checking', width: 170,index:'checking',sortable:false, formatter: function(value,col,row){
                    return  '<button  style="width: 62px;line-height: 30px;border: none;outline:none;background-color: #f90;color: white;border-radius: 4px" onclick="vm.isOnSale('+  row.id  +')">是/否</button>'
                }
            },
        ],
        gridComplete:function() {
            let gridName = 'jqGrid';
            Merger(gridName, 'goodsName');
        }
    });
    $('#jqGrid').css("textAlign","center");
});
function Merger(gridName, CellName) {
    //得到显示到界面的id集合
    let mya = $("#" + gridName + "").getDataIDs();
    //当前显示多少条
    let length = mya.length;
    for (let i = 0; i < length; i++) {
        //从上到下获取一条信息
        let before = $("#" + gridName + "").jqGrid('getRowData', mya[i]);
        //定义合并行数
        let rowSpanTaxCount = 1;
        for (j = i + 1; j <= length; j++) {
            //和上边的信息对比 如果值一样就合并行数+1 然后设置rowspan 让当前单元格隐藏
            let end = $("#" + gridName + "").jqGrid('getRowData', mya[j]);
            let cellNames = CellName.split(",");
            if(cellNames.length == 1){
                if (before[cellNames[0]] == end[cellNames[0]] ) {
                    rowSpanTaxCount++;
                    $("#" + gridName + "").setCell(mya[j], cellNames[0], '', { display: 'none' });
                } else {
                    rowSpanTaxCount = 1;
                    break;
                }
                $("#" + cellNames[0] + "" + mya[i] + "").attr("rowspan", rowSpanTaxCount);//最后合并需要合并的行与合并的行数
                $("#" + cellNames[0] + "" + mya[i] + "").css("vertical-align","middle");

            }else{
                if (before[cellNames[0]] == end[cellNames[0]] ) {
                    if(before[cellNames[1]] == end[cellNames[1]]){
                        rowSpanTaxCount++;
                        $("#" + gridName + "").setCell(mya[j], cellNames[1], '', { display: 'none' });
                    }else{
                        rowSpanTaxCount = 1;
                        break;
                    }
                } else {
                    rowSpanTaxCount = 1;
                    break;
                }
                $("#" + cellNames[1] + "" + mya[i] + "").attr("rowspan", rowSpanTaxCount);//最后合并需要合并的行与合并的行数
                $("#" + cellNames[1] + "" + mya[i] + "").css("vertical-align","middle");
            }
        }
    }
}

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        goodsSpecification: {
            value:'',
            specificationNumber:'',
            specificationPrice:'',
            picUrl:'',
            isSale: 1,
            isDefault:0,
        },
        ruleValidate: {
            name: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ]
        },
        q: {
            name: ''
        },
        goodss: []
        // specifications: []
    },
    methods: {

        // getSpecification: function () {
        //     Ajax.request({
        //         url: "../specification/queryAll",
        //         async: true,
        //         successCallback: function (r) {
        //             vm.specifications = r.list;
        //         }
        //     });
        // },
        getGoodss: function () {
            Ajax.request({
                url: "../goods/queryAll/",
                async: true,
                successCallback: function (r) {
                    vm.goodss = r.list;

                }
            });
        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.goodsSpecification = {
                value:'',
                picUrl:'',
                isSale: 1,
                isDefault:0,
            };
            vm.getGoodss();
        },
        update: function (event) {
            var id = getSelectedRow("#jqGrid");
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.goodsSpecification.id == null ? "../goodsspecification/save" : "../goodsspecification/update";

            Ajax.request({
                type: "POST",
                url: url,
                contentType: "application/json",
                params: JSON.stringify(vm.goodsSpecification),
                successCallback: function (r) {
                    alert('操作成功', function (index) {
                        vm.reload();
                    });
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows("#jqGrid");
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                Ajax.request({
                    type: "POST",
                    url: "../goodsspecification/delete",
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
        isOnSale:function(rowId){
            if (rowId !=null) {
                Ajax.request({
                    url: "../goodsspecification/info/" + rowId,
                    async: true,
                    successCallback: function (r) {
                        vm.goodsSpecification=r.goodsSpecification;
                        vm.goodsSpecification.isSale=r.goodsSpecification.isSale;
                        if (vm.goodsSpecification.isSale == 0) {
                            confirm('确定启用该商品规格吗', function () {
                                Ajax.request({
                                    type: "POST",
                                    url: "../goodsspecification/enSale",
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
                        } else if (vm.goodsSpecification.isSale == 1) {
                            confirm('确定关闭该商品规格吗', function () {
                                Ajax.request({
                                    type: "POST",
                                    url: "../goodsspecification/unSale",
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
        getInfo: function (id) {
            Ajax.request({
                url: "../goodsspecification/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.goodsSpecification = r.goodsSpecification;
                    // vm.getSpecification();
                    vm.getGoodss();
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
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
        handleSuccess: function (res, file) {
            vm.goodsSpecification.picUrl = file.response.url;
        },
        eyeImage: function () {
            var url = vm.goodsSpecification.picUrl;
            eyeImage(url);
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