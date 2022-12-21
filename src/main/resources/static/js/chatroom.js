
//채팅방 입장
function enterRoom(e,roomId){

    let roomExist = $(`#room-exist-${roomId}`).text();
    let roomRestrict = $(`#room-restrict-${roomId}`).text();



    let data = {
        room_exist : roomExist,
        room_restrict : roomRestrict
    };

    if(Number(roomExist) >= Number(roomRestrict)){
        alert("인원이 다 찼습니다.");
        e.preventDefault();
        return false;
    }

    $.ajax({
        type: "post",
        url: `/api/chatroom/${roomId}/up`,
        data: JSON.stringify(data), //(자바스크립트 데이터를 JSON으로 변환하여 보낸다.)
        contentType: "application/json; charset=utf-8",   //보낼 데이터의 형식
        dataType: "json" //응답받을 데이터의 형식
    }).done(res => {

        let before = $(`#room-exist-${roomId}`).text();
        let plusOne = Number(before)+1;
        $(`#room-exist-${roomId}`).text(plusOne);


    }).fail(error => {
        console.log(error);
    });




}



//채팅방 삭제
function deleteRoom(roomId){
    alert("삭제되었습니다.");
}



// //패스워드 모달
// function popup(obj) {
//     $(obj).css("display", "flex");
//
// }
// function closePopup(obj) {
//     $(obj).css("display", "none");
// }



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

