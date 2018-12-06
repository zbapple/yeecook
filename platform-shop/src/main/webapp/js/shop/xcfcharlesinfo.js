$(function () {
    $("#jqGrid").Grid({
        url: '../xcfcharlesinfo/list',
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '课程', name: 'course', index: 'course', width: 150},
			{label: '售价', name: 'price', index: 'price', width: 40},
			{label: '销量', name: 'sales', index: 'sales', width: 40},
			{label: '讲师', name: 'lecturer', index: 'lecturer', width: 80},
			{label: '抓取时间', name: 'addtime', index: 'addtime', width: 100 ,formatter: function (value) {
                    return transDate(value);
                }},
			{label: '年', name: 'years', index: 'years', width: 40},
			{label: '月', name: 'month', index: 'month', width: 40},
			{label: '日', name: 'day', index: 'day', width: 40},
			{label: '周', name: 'weeks', index: 'weeks', width: 40}]
    });

    $(".export-excel").on("click", function (e) {
        e.preventDefault();
        var filename = "下厨房课程销售统计";
        var name=vm.q.name;
        var years=vm.q.years;
        var month=vm.q.month;
        var day=vm.q.day;
        var weeks=vm.q.weeks;
        var sales=vm.q.sales;
        var lecturer =vm.q.lecturer;
        window.open("../xcfcharlesinfo/exclorder?" +
            "name=" +name+
            "&years="+years+
            "&month="+month+
            "&day="+day+
            "&weeks="+weeks+
            "&sales="+sales+
            "&lecturer="+lecturer+
            "&filename="+filename
        );
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		xcfCharlesInfo: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: '',
            years:'',
            month:'',
            day:'',
            weeks:'',
            sales:'',
            lecturer:''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},

		getInfo: function(id){
            Ajax.request({
                url: "../xcfcharlesinfo/info/"+id,
                async: true,
                successCallback: function (r) {
                    vm.xcfCharlesInfo = r.xcfCharlesInfo;
                }
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name,
                    'years': vm.q.years,
                    'month': vm.q.month,
                    'day': vm.q.day,
                    'weeks': vm.q.weeks,
                    'sales': vm.q.sales,
                    'lecturer': vm.q.lecturer
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        reloadSearch: function() {
            vm.q = {
                name: '',
                years:'',
                month:'',
                day:'',
                weeks:'',
                sales:'',
                lecturer:''
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
        }
	}
});