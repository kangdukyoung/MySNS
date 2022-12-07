//userId
var principalId = $("#principalId").val();
//전송 데이터(JSON)
var data = {};

var ws ;
//mid는 user id
var mid = document.getElementById('mid');
var btnLogin = document.getElementById('btnLogin');
var btnSend = document.getElementById('btnSend');
var talk = document.getElementById('talk');
var msg = document.getElementById('msg');


btnLogin.onclick = function(){
    let url = "ws://" + location.host + "/chat/" + principalId
    ws = new WebSocket(url);

    ws.onmessage = function(msg){

        var data = JSON.parse(msg.data);
        var css;

        if(data.id == principalId){
            css = 'class=me';
        }else{
            css = 'class=other';
        }

        var item = `<div ${css} >
		                <span><b>${data.id}</b></span> [${data.date}]<br/>
                      <span>${data.message}</span>
						</div>`;

        talk.innerHTML += item;
        talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
    }
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
        data.id = principalId;
        data.message = msg.value;
        data.date = new Date().toLocaleString();
        var temp = JSON.stringify(data);
        ws.send(temp);
    }
    msg.value ='';

}










