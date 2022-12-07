//채팅방 만들기
function createRoom(){

    let commentInput = $(`#storyCommentInput-${imageId}`);
    let commentList = $(`#storyCommentList-${imageId}`);

    let rname= $("#room-name").text();
    let rcreator = $("#room-creator").text();

    let data = {
        room_name: rname,
        room_creator: rcreator
    }
    alert(JSON.stringify(data));

    $.ajax({
        type: "post",
        url: "/api/chatroom/",
        data: JSON.stringify(data), //(자바스크립트 데이터를 JSON으로 변환하여 보낸다.)
        contentType: "application/json; charset=utf-8",   //보낼 데이터의 형식
        dataType: "json" //응답받을 데이터의 형식
    }).done(res => {
        let chatroom= res.data;

        // let comment = res.data;
        //
        // let content = `
        //   <div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
        //     <p>
        //       <img class="commenter_image" src="/upload/${comment.user.profileImageUrl}" onerror="this.src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'" />
        //       ${comment.user.username}: ${comment.content}
        //     </p>
        //     <button class="delete-btn" onclick="deleteComment(${comment.id})">삭제</i></button>
        //   </div>
        // `;
        // commentList.prepend(content);
    }).fail(error => {
        alert(error);
    });

}

function enterRoom(){

}
