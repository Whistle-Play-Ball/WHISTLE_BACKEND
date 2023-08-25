<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>

commentpost{
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 900px;
}

commentpostcontainer{
    display: flex;
    flex-direction: column;
    border-bottom: 1px solid #DCDCDC;
    width: 700px;
    margin: 40px 0 30px 0;
    height: 400px;
}

.commentPostSources{
    display: flex;
    justify-content: center;
    align-items: center;
    border: 3px solid #DCDCDC;
    height: 30px;
    width: 80px;
    margin-left: 5px;
    padding: 1px 10px 1px 10px;
}

.Answer1234Input{
    border: 3px solid #DCDCDC;
    height: 30px;
    width: 150px;
}

youranswercontainer{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    font-weight: bold;
    margin: 0 0 10px 0;
}

Answer1234{
    margin: 30px 0 20px 10px ;
    display: flex;
    align-items: center;
    justify-content: left;
    flex-direction: column;
    justify-content: left;
    width: 900px;
}
Answer12{
    width: 600px;
    display: flex;
    gap: 10px;
    flex-direction: row;
}
Answer34{
    width: 600px;
    display: flex;
    gap: 10px;
    flex-direction: row;
}
AnswerZero{
    display: flex;
    justify-content: center;
    align-items: center;
}

AnswerFirst{
    display: flex;
    justify-content: center;
    align-items: center;
}

AnswerSecond{
    display: flex;
    justify-content: center;
    align-items: center;
}

AnswerThird{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 900px;
}
#commentIdInput{
    border: 3px solid #DCDCDC;
    background-color: white;
}

#idInput{
    border: 3px solid #DCDCDC;
    background-color: white;
}

#nameInput{
    border: 3px solid #DCDCDC;
    background-color: white;
}

#commentInput{
    border: 3px solid #DCDCDC;
    width: 572px;
    height: 200px;
    background-color: #eeeded;
}

commentpostbutton{
    display: flex;
    justify-content: right;
}

#commentUpdate_btn{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 150px;
    height: 40px;
    color: black;
    font-size: 18px;
    font-weight: 3px;
    background-color: #eeeded;
    border: 3px solid #DCDCDC;
    margin: 0 60px 100px 0 ;
}

input::placeholder{
    padding-left: 5%;
    color: #8b8b8b;
    font-weight: 3px;
}

</style>
    </head>    
    <body>
        <header>
            <%@ include file="/WEB-INF/view/header/header.jsp" %>
        </header>
    
        <commentPostcontainer>
            <yourAnswerContainer>
                <Answer1234>
                    <Answer12>
                        <AnswerZero height="50">
                            <sourceFirst class="commentPostSources" height="50">commentId</sourceFirst>
                            <sourceFisrt height="50"><input type="number" name="commentId" id="commentIdInput" class="Answer1234Input"></sourceFisrt>
                        </AnswerZero>
                    </Answer12>
                </Answer1234>
                
                <AnswerThird height="50">
                    <sourceThird height="50"><input type="text" name="comment" id="commentInput"></sourceThird>
                </AnswerThird>
            </yourAnswerContainer>
            <commentpostbutton>
                <button type="button" id="commentUpdate_btn">댓글 수정</button>
            </commentpostbutton>
            <script>
                $(document).ready(function () {
                    // 버튼 클릭 시
                    $("#commentUpdate_btn").click(function () {
                        var commentId = $('#commentIdInput').val();
                        console.log("commentUpdate_btn clicked");
                        
                        var jsonData = {};

                        var commentValue = $('#commentInput').val();
                        if (commentValue) {
                            jsonData["comment"] = commentValue;
                        }

                        $.ajax({
                            url: "/api/comments/" + commentId,
                            type: "PATCH",
                            data: JSON.stringify(jsonData),
                            contentType: "application/json; charset=utf-8",
                            success: function (response) {
                                // 서버 응답 처리
                                console.log("Response from server: " + response);
                                alert("댓글이 성공적으로 수정되었습니다.")
                                window.location.href = "/board/detail?id="+commentId;
                                },
                                error: function (error) {
                                
        
                                // 서버 응답이 오류인 경우 
                                console.log("Error from server: " + error.statusText);
                                alert("정보를 확인 해주세요");
                            }
                        });
                    });
                });
            </script>
        </commentPostcontainer>

        <commentDeleteContainer>
            <%@ include file="/WEB-INF/view/comment/commentDelete.jsp" %>
        </commentDeleteContainer>
    </body>
</html>