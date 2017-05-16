var can;
var ctx;
var paddingx = 10;
var paddingy = 10;
var data;
var mt;
var ml;
var currentType=1;
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
}

function life() {
	paintBoard();
	paintChess();
	paintIndex();
}
$(document).ready(main);

function main() {
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
	init("screen");
	next(data1,-1,4);
	for(var i = 0;i<data1.length;i++){
		for(var j = 0;j<data1[i].length;j++){
			if(data[i][j]!=1){
				play(j,i,data1[i][j]);
			}
		}
	}
}

function onPlay(e) {
	var x = e.clientX - ml;
	var y = e.clientY - mt;
	x = Math.floor((x - paddingx + 20) / 40);
	y = Math.floor((y - paddingy + 20) / 40);
	var d = data[y][x];
	if(d!=undefined&&d!=0){
		return;
	}
	play(x, y, currentType);
	console.log(y+","+x)
	currentType = -currentType;
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
	var s = "";
	for (var i = 0; i < data.length; i++) {
		for (var j = 0; j < data[i].length; j++) {
			s+=data[i][j]==0?"0":data[i][j]==1?"B":"W";
		}
	}
	var result = $.ajax({
		type:"get",
		url:"next?chessStr="+s+"&type="+type+"&deep="+deep,
		async:false
	});
	var resultView = eval(result);
	data[resultView.y][resultView.x] = type;
}
