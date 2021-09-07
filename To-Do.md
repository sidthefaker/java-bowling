# TO-DO

## 질문 삭제하기 기능 리팩토링 요구사항
- [x] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- [x] 로그인 사용자와 질문한 사람이 다를 경우 CannotDeleteException() 발생
- [x] 답변이 없는 경우 삭제 가능하다.
- [x] 질문자와 답변글의 모든 답변자가 같은 경우 삭제 가능하다.
- [x] 질문자와 답변자가 다른 경우 CannotDeleteException() 발생
- [x] 삭제시 질문과 답변의 삭제 상태를 변경한다.
- [x] 삭제시 질문과 답변의 삭제 이력을 남긴다. 