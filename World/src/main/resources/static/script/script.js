/*    $(function(){
        $('#remote ul li').click(function(){
            var idx = $(this).index();
            var dist = idx*-2020;
            $('#main_imgs').animate({left:dist},300);
            $('#remote ul li').removeClass('selected');
            $('#remote ul li').eq(idx).addClass('selected');
        });
        $('#remote ul li:first').addClass('selected');
    });

 $(function(){
            var num =0;
            var timer;
            var flag=false;

            $('#remote ul li').click(function(){
                num = $(this).index();
                var dist = -2020 * num;
                $('#main_imgs').animate({ left : dist},500);

                $('#remote ul li').removeClass('selected');
                $('.auto').removeClass('selected');

                $(this).addClass('selected');
            });

       
            $('.auto').click(function(){
                $('#remote ul li').removeClass('selected');
                
                if(flag == false){  
                    $(this).html('■');  
                    timer = setInterval(function(){
                        flag = true;   
                        num ++;
                        if( num >7 ) num=0;
                        var dist = -2020 * num ;
                        $('#main_imgs').animate({ left : dist},500);  
                    }, 1500);
                    $('#remote ul li').off(); 
                }else{
                    flag = false;
                    clearInterval(timer);
                    $(this).html('▶');
                    $('#remote ul li').on('click',function(){
                        num = $(this).index();
                        var dist = -2020 * num;
                        $('#main_imgs').animate({ left : dist},500);
                        $('#remote ul li').removeClass('selected');     
                    }); 
                }
            });
        });*/
        
        
 /* Banner*/
    $(function(){
    	var num=0;
    	setInterval(function(){
            $('#imgs').animate({ left : num * -405 },1000);
                num++;
                if(num==5)num=0;
        }, 2000);
    });


       
        
function go_(){
	location.href = "adminProductList";
}

function go_search(comm){
	 if( document.frm.key.value == "" ){
		alert("검색버튼 사용시에는 검색어 입력이 필수입니다");
	 	return;
	} 
	var url = comm+"?page=1";   // 검색어로 검색한 결과의 1페이지로 이동
	document.frm.action = url;
	document.frm.submit();
	
}

function go_total(comm ){
	document.frm.key.value = "";
	document.frm.action = comm + "?page=1";
	document.frm.submit();
}

///////////////////////////////////////////////////////////////

function passCheck( lqseq ){
	var url = "passCheck?lqseq=" + lqseq;
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250, scrollbars=no";
    window.open(url, "passCheck", opt);
}

function enabled() {
	if(document.qna.check.checked==true) {
		document.qna.pass.disabled = false;
	} else {
		document.qna.pass.disabled = true;
		document.qna.pass.value="";
	}
}



                
            