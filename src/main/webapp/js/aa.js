function loadUserMesg(){
	$.ajax({
		type:"get",
		url:"myInfo?t="+Math.random(),
		async:true,
		success:function(e){
			var view = eval(e);
			initInfo("#user-me",view)
		}
	});
	$.ajax({
		type:"get",
		url:"opponentInfo?t="+Math.random(),
		async:true,
		success:function(e){
			var view = eval(e);
			initInfo("#user-ot",view)
		}
	});
}

function initInfo(id,view){
	$(id+" #win").text(view.win);
	$(id+" #lose").text(view.lose);
	$(id+" #tie").text(view.tie);
	$(id+" #sum").text(view.sum);
	$(id+" .user-name p").text(view.name);
}
