function adminloginCheck(){  //관리자 로그인시 아이디 비번 입력 확인
	
	if(document.frm.id.value==""){
		   	alert("아이디를 입력하세요.");
		  	return false;
		}else if(document.frm.pwd.value==""){
		  	alert("비밀번호를 입력하세요.");
		   	return false;
		}else{
	document.frm.action = "adminlogin";
	document.frm.submit();
			return true;
		}  
}

function go_manage(comm){
	var url = "world.do?command=" + comm;   // 검색어로 검색한 결과의 1페이지로 이동
	document.frm.action = url;
	document.frm.submit();
}

function go_search(comm){
	 if( document.frm.key.value == "" ){
		alert("검색버튼 사용시에는 검색어 입력이 필수입니다");
	 	return;
	} 
	var url = comm + "?page=1";  
	document.frm.action = url;
	document.frm.submit();	
}

function go_total(comm ){
	document.frm.key.value = "";
	document.frm.action =  comm + "?page=1";  
	document.frm.submit();
}

function go_view( lqseq ){
	location.href = "adminQnaView?lqseq=" + lqseq;
}




//------ 공지사항 ------
function go_insertform(comm){ //공지추가 폼으로 이동
	document.frm.action = comm;
	document.frm.submit();
}

function go_insertnt(){ //공지추가 동작액션으로 이동
	if( document.insertnt.title.value==""){ 
		alert('제목을 입력하세요');
		document.insertnt.title.focus();
	}else if (document.insertnt.ncontent.value == "") {
		alert('공지할 사진을 입력하세요.'); 	
		document.insertnt.ncontent.focus();	
	}else{
	document.insertnt.action = "world.do?command=insertnotice";
	document.insertnt.submit();
	}
}

function go_detail(nseq){
	document.frm.action = "adminNoticeDetail?nseq=" + nseq;
	document.frm.submit();
}

function go_mov(){ // 공지사항 목록으로 이동
	location.href = "adminNotice";
}

function go_deletent(nseq){ //공지 삭제
	if(confirm('정말 삭제하시겠습니까?')){
		var url="world.do?command=noticeDelete&nseq="+nseq;
		location.href=url;
	}
}

function go_modnt(nseq){ // 공지 수정폼으로 이동
	document.frm.action = "noticeUpdateForm?nseq=" + nseq;
	document.frm.submit();
	var url="adminUpdateNoticeForm&nseq="+nseq;
	window.location.href = url;
}

function go_mod_savent(){ // 공지 수정 버튼 클릭
	if( document.frm.title.value==""){  
		alert('제목을 입력하세요');
		document.frm.title.focus();
	}else if( document.frm.ncontent.value==""){  
		alert('내용을 입력하세요');
		document.frm.ncontent.focus();
	}else{
		if(confirm('수정하시겠습니까?')){
			document.frm.action = "noticeUpdate";
			document.frm.submit();
		}
	}
}

function goToDetail(nseq) {
	window.location.href = "/adminnoticeDetail?nseq=" + nseq;
}

// 놀이기구 ----------------------------------------------------------------------------
function go_insertat(){
	if( document.insertat.atname.value==""){ 
		alert('놀이기구 이름을 입력하세요');
		document.insertat.atname.focus();
		
	}else if (document.insertat.acontent.value == "") {
		alert('놀이기구 설명을 입력하세요.'); 	
		document.insertat.acontent.focus();	
		
	}else if (document.insertat.act1.value == "") {
		alert('카테고리 1을 입력하세요.'); 	
		document.insertat.act1.focus();	
		
	}else if (document.insertat.act2.value == "") {
		alert('카테고리 2를 입력하세요.'); 	
		document.inserinsertattnt.act2.focus();	
		
	}else if (document.insertat.image.value == "") {
		alert('놀이기구 사진을 입력하세요.'); 	
		document.insertat.image.focus();	
		
	}else if (document.insertat.acontent.value == "") {
		alert('놀이기구 설명을 입력하세요.'); 	
		document.insertat.acontent.focus();	
		
	}else if (document.insertat.pnum.value == "") {
		alert('탑승인원 입력하세요.'); 	
		document.insertat.pnum.focus();	
		
	}else if (document.insertat.limitkey.value == "") {
		alert('제한사항 1을 입력하세요'); 	
		document.insertat.limitkey.focus();	
		
	}else if (document.insertat.acontent.value == "") {
		alert('제한사항 2를 입력하세요'); 	
		document.insertat.acontent.focus();	
		
	}else if (document.insertat.bestat.value == "") {
		alert('놀이기구 베스트 정보를 설정하세요.'); 	
		document.insertat.bestat.focus();
		
	}else if (document.insertat.aresult.value == "") {
		alert('놀이기구 운휴 정보를 입력하세요.'); 	
		document.insertat.aresult.focus();			
	}else{
	document.insertat.action = "insertat";
	document.insertat.submit();
	}
	
}



// 놀이기구 수정
function go_modat(aseq){ // 놀이기구 수정폼으로 이동
	var url="adminUpdateAttractionForm?aseq="+aseq;
	location.href=url;
}

// 놀이기구 수정버튼 클릭
function go_mod_saveat(){
	if( document.updateat.atname.value==""){ 
		alert('놀이기구 이름을 입력하세요');
		document.updateat.atname.focus();
		
	}else if (document.updateat.acontent.value == "") {
		alert('놀이기구 설명을 입력하세요.'); 	
		document.updateat.acontent.focus();	
		
	}else if (document.updateat.act1.value == "") {
		alert('카테고리 1을 입력하세요.'); 	
		document.updateat.act1.focus();	
		
	}else if (document.updateat.act2.value == "") {
		alert('카테고리 2를 입력하세요.'); 	
		document.updateat.act2.focus();	
		
	}else if (document.updateat.acontent.value == "") {
		alert('놀이기구 설명을 입력하세요.'); 	
		document.updateat.acontent.focus();	
		
	}else if (document.updateat.pnum.value == "") {
		alert('탑승인원 입력하세요.'); 	
		document.updateat.pnum.focus();	
		
	}else if (document.updateat.limitkey.value == "") {
		alert('제한사항 1을 입력하세요'); 	
		document.updateat.limitkey.focus();	
		
	}else if (document.updateat.acontent.value == "") {
		alert('제한사항 2를 입력하세요'); 	
		document.updateat.acontent.focus();		
	}else{
		if(confirm('수정하시겠습니까?')){
		document.updateat.action = "/updateAttraction";
		document.updateat.submit();
		}
	}
}

//놀이기구 삭제
function go_deleteat(aseq){
if(confirm('정말 삭제하시겠습니까?')){
	var url="attractionDelete&aseq="+aseq;
	location.href=url;
	}
}

//답글달기
function go_rep(lqseq){
	document.frm.action="adminQnaRepSave";
	document.frm.submit();
}

