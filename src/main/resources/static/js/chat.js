//user 정보 불러오기
var principalId = $("#principalId").val();
var principalName = $("#principalName").val();

//채팅방 정보 불러오기
var roomId = $("#roomId").val();

var data = {};
var ws ;

var btnSend = document.getElementById('btnSend');
var talk = document.getElementById('talk');
var msg = document.getElementById('msg');

let url = "ws://" + location.host + "/chatroom/" + roomId + "/mychat/" + principalId;
ws = new WebSocket(url);

ws.onmessage = function(msg){
    var data = JSON.parse(msg.data);
    var css;

    if(data.username == principalName){
        css = 'class=me';
    }else{
        css = 'class=other';
    }

    var item = `<div ${css}>
                      <span><b>${data.username}</b>
                      </span> [${data.date}]<br/>
                      <span>${data.message}</span>
                </div>`;

    talk.innerHTML += item;
    talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
}




msg.onkeyup = function(ev){
    if(ev.keyCode == 13){
        submit();
    }
}

btnSend.onclick = function(){
    submit();
}

function submit(){
    if(msg.value.trim() == ''){
        alert("한글자 이상을 입력하세요.");
    }else{
        data.username = principalName;
        data.message = msg.value;
        data.date = new Date().toLocaleString();
        var temp = JSON.stringify(data);
        ws.send(temp);
    }
    msg.value ='';

}










