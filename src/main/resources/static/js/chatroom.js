var enterRoom = document.getElementById('enterRoom');

var principalId = $("#principalId").val();
var roomId = $("#roomId").val();
var roomRestrict = $("#room-restrict").text();
var roomExist = $("#room-exist").text();

enterRoom.onclick = function(){

    var data = {
        room_exist : roomExist,
        room_restrict : roomRestrict
    };
    if(roomExist >= roomRestrict){
        alert("인원이 다 찼습니다.")
        location.reload();
    }else {

        $.ajax({
            type: "post",
            url: `/api/chatroom/${roomId}/up`,
            data: JSON.stringify(data), //(자바스크립트 데이터를 JSON으로 변환하여 보낸다.)
            contentType: "application/json; charset=utf-8",   //보낼 데이터의 형식
            dataType: "json" //응답받을 데이터의 형식
        }).done(res => {
            alert(res);
        }).fail(error => {
            console.log(error);
        });
    }
}


// //채팅방 만들기
// function createRoom(){
//
//     // let commentInput = $(`#storyCommentInput-${imageId}`);
//     // let commentList = $(`#storyCommentList-${imageId}`);
//     let roomList = $("#room-list");
//     let rname= $("#room-name").text();
//     let rcreator = $("#room-creator").text();
//
//     let data = {
//         room_name: rname,
//         room_creator: rcreator
//     }
//     alert(JSON.stringify(data));
//
//     $.ajax({
//         type: "post",
//         url: "/api/chatroom/",
//         data: JSON.stringify(data), //(자바스크립트 데이터를 JSON으로 변환하여 보낸다.)
//         contentType: "application/json; charset=utf-8",   //보낼 데이터의 형식
//         dataType: "json" //응답받을 데이터의 형식
//     }).done(res => {
//         let chatroom= res.data;
//
//         let content = `
//           <div class="sl__item__contents__comment" id="storyCommentItem-${chatroom.id}">
//             <p>
//               방장: ${chatroom.creator}
//             </p>
//             <button class="delete-btn" onclick="deleteComment(${chatroom.id})">삭제</i></button>
//           </div>
//         `;
//
//         roomList.prepend(content);
//     }).fail(error => {
//         alert(error);
//     });
//
// }

