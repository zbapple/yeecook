var vm =new Vue({
    el:'#print',
    data:{
        num:1,
        id:4,
        sysPrinterUser:{
            printName:'',
        },
        state:'',
    },
    mounted(){
       this.getInfo();
       this.getStatus();
    },

    methods:{
        print:function(){
            let id=this.num;
            openWindow({
                type: 2,
                title: '打印',
                area: ['660px', '460px'],
                content: '../shop/printdetail.html?Id=' + id
            })
        },
        getInfo:function(){
            let id= this.id;
            Ajax.request({
                url: "../sysprinteruser/info/" + id,
                async: true,
                successCallback: function (r) {
                    vm.sysPrinterUser = r.sysPrinterUser;
                    vm.sysPrinterUser.printName=vm.sysPrinterUser.printName.substring(3,5);
                }
            });
        },

        getStatus:function () {
            let that=this;
            Ajax.request({
                url: "../printer/status/",
                async: true,
                type: "POST",
                successCallback: function (r) {
                    that.state=r.state;
                }
            });
        }
    }
})