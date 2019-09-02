$(function () {
    let videoId = getQueryString("videoId");
    let url = '../cloudclassroomgood/list';
    if (videoId) {
        url += '?videoId=' + videoId;
    }
    $("#jqGrid").Grid({
        url: url,
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '视频课件', name: 'videoTitle', index: 'video_id', width: 80},
			{label: '商品', name: 'goodsName', index: 'goods_id', width: 80},
			{label: '商品图片', name:'primaryPicUrl',index:'goods_id',width:80,
                formatter: function (value) {
                    return transImg(value);}
			}
		]
    });
    $('#jqGrid').css("textAlign","center");
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		cloudClassroomGood: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
            videoTitle: ''
		},
        good:[],
        goods: [],
        videos:[],
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getVideos:function(){
            Ajax.request({
                url: "../cloudclassroom/queryAll/",
                async: true,
                successCallback: function (r) {
                    vm.videos = r.list;
                }
            });
		},
        getGoods: function () {
            Ajax.request({
                url: "../goods/queryAll/",
                async: true,
                successCallback: function (r) {
                    vm.goods = r.list;
                }
            });
        },
        selectGood:function(val){
            for(let i=0;i<this.goods.length;i++){
                if(this.goods[i].id==val){
                    this.cloudClassroomGood.primaryPicUrl=this.goods[i].primaryPicUrl;
                }
            }
        },
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.cloudClassroomGood = {};
            vm.getGoods();
            vm.getVideos();
		},
		update: function (event) {
            let id = getSelectedRow("#jqGrid");
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
            let url = vm.cloudClassroomGood.id == null ? "../cloudclassroomgood/save" : "../cloudclassroomgood/update";
            Ajax.request({
			    url: url,
                params: JSON.stringify(vm.cloudClassroomGood),
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
				    url: "../cloudclassroomgood/delete",
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
                url: "../cloudclassroomgood/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.cloudClassroomGood = r.cloudClassroomGood;
                    vm.getGoods();
                    vm.getVideos();
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'videoTitle': vm.q.videoTitle},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                videoTitle: ''
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
        //上传组件
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
            vm.cloudClassroomGood.primaryPicUrl = file.response.url;
        },
        eyeImage: function () {
            var url = vm.cloudClassroomGood.primaryPicUrl;
            eyeImage(url);
        },
	},
	mounted(){
        this.getGoods();
    }
});