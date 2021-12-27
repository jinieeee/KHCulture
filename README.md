# KH문화센터(KHCulture)

개발기간: 2021.10.11 ~ 2021.12.10
개발환경: CSS, HTML5, JAVA, JavaScript, MyBatis, Oracle Cloud, Spring boot, jQuery   
팀원: 김현주, 양정하, 이지은, 장영재, 황재윤   
   
### 프로젝트 소개 :  [웹페이지 이동](http://146.56.187.50:8080/main)

코로나19의 장기화에 따라 코로나19를 예방하여 일상생활을 해야하는 시기가 도래했고 이에 따라 오프라인 취미&문화생활이 나아질 것으로 기대하여, 롯데문화센터 웹사이트를 참고한 문화센터 강좌 수강 웹사이트를 구현했다.

### 설계

[요구사항정의서](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6c58e0e7-4296-49b2-ad4d-70ad3697e5e6/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%EC%A0%95%EC%9D%98%EC%84%9C.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211227%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211227T134830Z&X-Amz-Expires=86400&X-Amz-Signature=c1e602678709ea79f1e6ef4e2bd3f8fba8d79d5205163852909c52f24e3386fe&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22%25EC%259A%2594%25EA%25B5%25AC%25EC%2582%25AC%25ED%2595%25AD%25EC%25A0%2595%25EC%259D%2598%25EC%2584%259C.jpg%22&x-id=GetObject)  
[프로세스맵](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/6870223f-ee17-4544-a400-f55cdcb2735e/%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4%EB%A7%B5.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211227%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211227T134901Z&X-Amz-Expires=86400&X-Amz-Signature=300942389123a28aa27b544135ca1d0d5178d5fb3a393c00f8605149a73e0416&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22%25ED%2594%2584%25EB%25A1%259C%25EC%2584%25B8%25EC%258A%25A4%25EB%25A7%25B5.png%22&x-id=GetObject)  
[DB설계](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/9eab4980-812d-4fc3-8eec-de546df28231/DB%EC%84%A4%EA%B3%84.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211227%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211227T134931Z&X-Amz-Expires=86400&X-Amz-Signature=1c9ae117971da26c68176f81e5a8332deb7f04bb2b56598e8589d41d7ae1c2ee&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22DB%25EC%2584%25A4%25EA%25B3%2584.png%22&x-id=GetObject)  
[테이블정의서](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a1589bc0-549f-4022-bb58-a94b5848cacc/%ED%85%8C%EC%9D%B4%EB%B8%94%EC%A0%95%EC%9D%98%EC%84%9C.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211227%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211227T134950Z&X-Amz-Expires=86400&X-Amz-Signature=d25ce17add8da64c216494ac578b897ac3122098347cfcba849c7aa59f29fbd0&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22%25ED%2585%258C%25EC%259D%25B4%25EB%25B8%2594%25EC%25A0%2595%25EC%259D%2598%25EC%2584%259C.jpg%22&x-id=GetObject)  
[UML](https://www.notion.so/Ji-eun-Lee-263f726a952a415ca850fa7fd613c369#21c42137734a487b9660a42a1164da88)

### 주요기능

- 회원가입 및 로그인
- 게시판(공지사항, 수강후기)
- 마이페이지(구매내역, 1:1문의, 회원정보변경, 장바구니)
- 관리자페이지(강사관리, 회원관리, 공지사항관리, 강좌관리)
- 강좌관리(강좌, 강사 등록)
- 강좌신청(결제, 취소)
- 강좌검색(강좌검색, 강좌스케줄)

### 담당기능 : 로그인 & 회원가입, 회원정보변경 / 회원 및 공지사항 관리(관리자)


![localhost_8006_main](https://user-images.githubusercontent.com/89566406/143870631-814f5d2a-fc7a-48a7-83ac-d9b33d2ef51c.png)


- **내비게이터**
thymeleaf의 spring-security 문법을 활용하여 비로그인/회원로그인 시에는 마이페이지가 보이고, 관리자로 로그인 시에는 관리자페이지가 노출됩니다. 비로그인시에 메뉴바의 마이페이지 경로를 클릭할 수는 있지만 해당 경로에 접근하면 로그인 페이지로 이동합니다.

- **메인페이지**
bx-slider를 사용하여 간단하게 이미지슬라이드를 구현한 화면입니다. 공지사항 게시글과 연결되어 클릭시에 해당 페이지로 이동합니다. 강좌검색 기능과 추천강좌도 각각의 페이지와 연결되어있습니다.

---

![localhost_8006_member_login](https://user-images.githubusercontent.com/89566406/143870678-c00827af-1e02-4841-bb8e-6f7d66e88ce5.png)


- **로그인**
로그인 화면에서 아이디 찾기/비밀번호 찾기/회원가입 각 경로로 이동할 수 있도록 만들었습니다. 로그인 요청 시에 Spring-security에서 요청을 가로채어 인증된 사용자인지 DB와 대조 후 Authentication에 저장하고 세션에서 유지합니다. 이 과정에서 인증성공과 실패 핸들러를 추가하여 상황에 따라 다르게 동작하도록 커스터마이징했습니다.

---

![localhost_8006_admin_memberList](https://user-images.githubusercontent.com/89566406/143870751-9cc5bba9-4fb0-4cdd-9c29-50dd4f1ef474.png)

- **회원관리**
회원등급, 계정잠금, 계정탈퇴(탈퇴기준일), 검색어로 조회 가능한 화면입니다. 기본 조회는 DB의 회원목록 전체이며 리스트에서 다중선택 후 비밀번호 초기화, 등급변경, 계정삭제가 가능합니다.
계정 삭제는 탈퇴한 회원에 한해 가능하며, 탈퇴 1년 경과한 회원의 정보를 삭제관리하기 위해 추가한 기능입니다.

---

![localhost_8006_admin_noticeList](https://user-images.githubusercontent.com/89566406/143870789-1274af8a-3576-424c-9e70-5235d7a0e024.png)

- **공지사항 관리**
제목, 내용에 따른 키워드와 '내가 작성한 공지사항' 기준으로 조회할 수 있습니다. 관리자페이지에서 기본적으로 수정, 삭제가 가능하며 추가로 '메인등록' 기능을 구현했습니다. 메인등록 클릭 후 이미지를 삽입하면 메인페이지의 이미지 슬라이드에 해당 공지사항이 href와 함께 등록됩니다.

---

- **그 외 구현기능**
회원가입 & 아이디 / 비밀번호 찾기 (이메일 인증)
회원정보수정

### Notions 
[개발자 포트폴리오](https://ballistic-ozraraptor-6a0.notion.site/Ji-eun-Lee-263f726a952a415ca850fa7fd613c369)

### 개발환경 : Spring Boot, Oracle Cloud

Frontend

- HTML5
- CSS3
- JavaScript
- jQuery
- Thymeleaf

Backend

- java
- Oracle SQL
