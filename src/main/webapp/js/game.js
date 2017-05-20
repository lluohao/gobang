var can;
var ctx;
var paddingx = 10;
var paddingy = 10;
var data;
var mt;
var ml;
var currentType=1;
var me = 1;
var com = -1;
var deep = 4;
var now = -1;
var step = 0;
function init(id) {
	can = document.getElementById(id);
	mt = can.offsetTop;
	ml = can.offsetLeft;
	ctx = can.getContext("2d");
	var size_x = 700;
	var size_y = 700;
	paddingx = (size_x - (14 * 40)) / 2;
	paddingy = (size_y - (14 * 40)) / 2;
	data = new Array();
	for(var i = 0; i < 15; i++) {
		data[i] = new Array();
	}
	can.onclick = onPlay;
	setInterval(life, 20);
}

function play(x, y, type) {
	data[y][x] = type;
	if(type!=0){
		step++;
	}
}

function playToNetWork(x, y, type){
	$.ajax({
		type:"get",
		url:"play?x="+x+"&y="+y+"&type="+type,
		async:true,
		success:function(e){
			console.log(e.code+":"+e.message);
			if(e.code==200){
				now=com;
			}
		}
	});
}

function life() {
	paintBoard();
	paintChess();
	paintIndex();
}
$(document).ready(main);

function main(){
	init("screen");
}

function initFirst(){
	me = $("#first").val();
	me = parseInt(me);
	com = -me;
	deep = $("#diff").val();
	deep = parseInt(deep);
}

function newgame() {
	initFirst();
	var data1 = [
		 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 1, -1, 1, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	];
	
	var s = "";
	for (var i = 0; i < data1.length; i++) {
		for (var j = 0; j < data1[i].length; j++) {
			s+=data1[i][j]==0?"0":data1[i][j]==1?"B":"W";
		}
	}
	$.ajax({
		type:"get",
		url:"newComputer?type="+me+"&diff="+deep+"&str="+s,
		async:true,
		success:function(e){
			var obj = eval(e);
			console.log(obj.message);
		}
	});
	for(var i = 0;i<data1.length;i++){
		for(var j = 0;j<data1[i].length;j++){
			play(j,i,data1[i][j]);
		}
	}
	circle();
	console.log("finish init")
}

function circle(){
	setInterval(getNext,500);
}

function getNext(){
	if(now==com){
		next();
	}
}

function onPlay(e) {
	if(now==com){
		return;
	}
	var x = e.clientX - ml;
	var y = e.clientY - mt;
	x = Math.floor((x - paddingx + 20) / 40);
	y = Math.floor((y - paddingy + 20) / 40);
	var d = data[y][x];
	if(d!=undefined&&d!=0){
		return;
	}
	play(x, y, now);
	console.log(y+","+x)
	playToNetWork(x, y, now);
	now = com;
	getNext();
}

function paintBoard() {
	ctx.fillStyle = "#DDD";
	ctx.fillRect(0, 0, 700, 700);
	ctx.strokeStyle = "#000";
	for(var i = 0; i < 15; i++) {
		line(paddingx, i * 40 + paddingy, paddingx + 14 * 40, i * 40 + paddingy);
		line(paddingx + i * 40, paddingy, paddingy + i * 40, 14 * 40 + paddingy);
	}
}

function paintChess() {
	for(var i = 0; i < data.length; i++) {
		for(var j = 0; j < data.length; j++) {
			if(data[i][j] == -1) {
				ctx.fillStyle = "#FFF";
				ctx.strokeStyle = "#FFF"
				fillArc(paddingx + j * 40, paddingy + i * 40, 18);
			} else if(data[i][j] == 1) {
				ctx.fillStyle = "#000";
				ctx.strokeStyle = "#000"
				fillArc(paddingx + j * 40, paddingy + i * 40, 18);
			}
		}
	}
}

function paintIndex(){
	ctx.font = "20px Arial"
	ctx.fillStyle="#000"
	for(var i = 0;i<15;i++){
		ctx.fillText((i+1)+"",paddingx+i*40-5,paddingy-10,20);
		ctx.fillText((i+1)+"",paddingx-30,paddingy+i*40+5,20);
	}
}

function fillArc(x, y, r) {
	ctx.beginPath();
	ctx.arc(x, y, r, 0, Math.PI * 2);
	ctx.fill();
	ctx.closePath();
}

function line(sx, sy, ex, ey) {
	ctx.beginPath();
	ctx.moveTo(sx, sy);
	ctx.lineTo(ex, ey);
	ctx.stroke();
	ctx.closePath();
}

function next(data,type,deep){
	var result = $.ajax({
		type:"get",
		url:"step?step="+(step+1)+"&t="+(Math.floor(Math.random()*100000)),
		async:true,
		success:function(e){
			var resultView = eval(e);
			//console.log(resultView.code+"："+resultView.x+","+resultView.y);
			if(resultView.code==200){
				console.log("load the next:"+resultView.x+","+resultView.y);
				play(resultView.x,resultView.y,now);
				now=-now;
			}
		}
	});
	
}
