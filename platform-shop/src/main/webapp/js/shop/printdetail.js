let vm =new Vue({
    el:'#printdetail',
    data:{
        id:'',
        textdata:''
    },
    mounted(){
        let  Id=getQueryString("Id");
        this.id=Id;
        // this.getdata();
        this.checkNum();
    },
    methods:{
        getdata:function(){
            // let that=this;
            // let judge_div=/<div(.*?)<\/div>/;
            // let judge_font=/<font(.*?)<\/font>/;
            // let judge_b=/<b>(.*?)<\/b>/;
            var printdata='';
            let that=this;
            console.log($('#content'));
            for(let i=0;i<$('#content')[0].childNodes.length;i++){
                if($('#content')[0].childNodes[i].nodeName=='#text'){
                    printdata=printdata+$('#content')[0].childNodes[i].textContent;
                }else if($('#content')[0].childNodes[i].nodeName=='B'){
                    let B_data=''
                    for(let j=0;j<$('#content')[0].childNodes[i].childNodes.length;j++){
                        if($('#content')[0].childNodes[i].childNodes[j].nodeName=='#text'){
                            B_data=B_data+$('#content')[0].childNodes[i].childNodes[j].textContent;
                        }else{
                            if($('#content')[0].childNodes[i].childNodes[j].size=='5'){
                                B_data=B_data+"<FS>"+$('#content')[0].childNodes[i].childNodes[j].innerText+"</FS>";
                            }else{
                                B_data=B_data+$('#content')[0].childNodes[i].childNodes[j].innerText;
                            }
                        }
                    }
                    printdata="<FB>"+B_data+"</FB>";
                }else if($('#content')[0].childNodes[i].nodeName=='FONT'){
                    let F_data=''
                    for(let j=0;j<$('#content')[0].childNodes[i].childNodes.length;j++) {
                        if ($('#content')[0].childNodes[i].childNodes[j].nodeName == '#text') {
                            F_data = F_data + $('#content')[0].childNodes[i].childNodes[j].textContent;
                        } else if ($('#content')[0].childNodes[i].childNodes[j].nodeName == 'B') {
                            F_data = F_data + "<FB>" + $('#content')[0].childNodes[i].childNodes[j].innerText + "</FB>";
                        }
                    }
                    if($('#content')[0].childNodes[i].size=='5'){
                        printdata=printdata+"<FS>"+F_data+"</FS>"
                    }else{
                        printdata=+printdata+F_data;
                    }
                }else if($('#content')[0].childNodes[i].nodeName=='DIV') {
                    let D_data='';
                    for(let j=0;j<$('#content')[0].childNodes[i].childNodes.length;j++) {
                        if ($('#content')[0].childNodes[i].childNodes[j].nodeName == '#text') {
                            D_data = D_data + $('#content')[0].childNodes[i].childNodes[j].textContent;
                        } else if ($('#content')[0].childNodes[i].childNodes[j].nodeName == 'B') {
                            for(let k=0;k<$('#content')[0].childNodes[i].childNodes[j].length;k++){
                                if($('#content')[0].childNodes[i].childNodes[j].childNodes[k].nodeName=='#text'){
                                    D_data = D_data + $('#content')[0].childNodes[i].childNodes[j].childNodes[k].textContent;
                                }else if($('#content')[0].childNodes[i].childNodes[j].childNodes[k].nodeName=='FONT'){
                                    if($('#content').childNodes[i].childNodes[j].childNodes[k].size=='5'){
                                        D_data=D_data+"<FS>"+$('#content')[0].childNodes[i].childNodes[j].childNodes[k].innerText +"</FS>";
                                    }else{
                                        D_data=D_data+$('#content')[0].childNodes[i].childNodes[j].childNodes[k].innerText;
                                    }
                                }

                            }
                            D_data = D_data + "<FB>" + $('#content')[0].childNodes[i].childNodes[j].innerText + "</FB>"

                        } else if ($('#content')[0].childNodes[i].childNodes[j].nodeName == 'FONT') {
                            for (let k = 0; k < $('#content')[0].childNodes[i].childNodes[j].childNodes.length; k++) {
                                if ($('#content')[0].childNodes[i].childNodes[j].childNodes[k].nodeName == '#text') {
                                    D_data = D_data + $('#content')[0].childNodes[i].childNodes[j].childNodes[k].textContent;
                                } else if ($('#content')[0].childNodes[i].childNodes[j].childNodes[k].nodeName == 'B') {
                                    D_data = D_data + "<FB>" + $('#content')[0].childNodes[i].childNodes[j].childNodes[k].innerText + "</FB>"
                                }
                            }
                            if ($('#content')[0].childNodes[i].childNodes[j].size == '5') {
                                D_data = "<FS>" + D_data + "</FS>";
                            }
                        }
                    }
                    //if($('#content').childNodes[0].css())
                    if($('#content').children().eq(i).css("textAlign")=='center'){
                        D_data="<center>"+D_data+"</center>";
                    }else{
                        D_data=D_data+"\n";
                    }
                        //console.log($('#content').children().eq(i).css("textAlign"));
                    printdata=printdata+D_data;

                }

            }
            that.textdata=printdata;
            console.log(printdata);
            if(that.textdata!=''||that.textdata!=null){
                Ajax.request({
                    type: "POST",
                    url:"../printer/prt",
                    async: true,
                    contentType: "application/json",
                    params:{"count":that.id,"content":that.textdata},
                    successCallback: function(r) {let index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                    }
                });
            }
        },
        oncenter:function(e){
            document.execCommand("JustifyCenter",false,null);
            e.preventDefault();
        },
        onblod:function(e){
            document.execCommand("Bold",false,null);
        },
        onleft:function(e){
            document.execCommand("JustifyLeft",false,null);
            e.preventDefault();
        },
        onfontD:function(e){
            document.execCommand('FontSize',false,"5");
            e.preventDefault();
        },
        onfontX:function(e){
            document.execCommand('FontSize',false,"4");
            e.preventDefault();
        },
        onreset:function(){
            document.execCommand('Undo',false,0);
        }
    }
})


