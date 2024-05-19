# 💯 Spring 개인 과제 일정 관리 앱 서버 만들기

Spring 입문주차 첫 번째 개인과제로 일정 작성, 조회, 수정, 삭제 기능이 가능한 일정 관리 앱 서버입니다.

HTTP 요청에 대한 응답과 DB에서 데이터를 관리하는 기능이 가능한 상태이며, postman을 사용하여 테스트 하고 있습니다.

## ✅ 구현 기능

일정 작성, 수정, 조회 시 반환 받은 일정 정보에 비밀번호는 제외 되어있습니다.<br>
일정 수정, 삭제 시 선택한 일정의 비밀번호와 요청할 때 함께 보낸 비밀번호가 일치할 경우에만 가능합니다.

- level 1: 일정 작성
  - 할일 제목, 할일 내용, 담당자, 비밀번호, 작성일을 저장할 수 있습니다.
  - 저장된 일정 정보를 반환 받아 확인할 수 있습니다.
- level 2: 선택한 일정 조회
  - 선택한 일정의 정보를 조회할 수 있습니다.
- level 3: 일정 목록 조회
  - 등록된 일정 전체를 조회할 수 있습니다.
  - 조회된 일정 목록은 작성일 기준 내림차순으로 정렬 되어있습니다.
- level 4: 선택한 일정 수정
  - 선택한 일정의 할일 제목, 할일 내용, 담당자을 수정할 수 있습니다.
  - 서버에 일정 수정을 요청할 때 비밀번호를 함께 전달합니다.
  - 수정된 일정의 정보를 반환 받아 확인할 수 있습니다.
- level 5: 선택한 일정 삭제
  - 선택한 일정을 삭제할 수 있습니다.
  - 서버에 일정 삭제를 요청할 때 비밀번호를 함께 전달합니다.

## 💻 개발 환경

- 언어: Java
- 버전: JDK 17
- Framework: SpringBoot 3.2.5
- DB: MySQL 8.0.28

## 📝 Use Case Diagram

![Use Case Diagram drawio](https://github.com/ryurbsgks5114/SpringAssignment01/assets/165640275/848f79f9-e05e-473a-a878-141f911ea98c)

## 💾 ERD

![ERD](https://github.com/ryurbsgks5114/SpringAssignment01/assets/165640275/2b11ff99-9eab-4525-a8bd-c5385033e819)

## ✍ API 명세서

[GitBook API 명세서](https://app.gitbook.com/o/eDxVzDlMrVlsJED181bD/s/bhp0Xb4lEsxszyYsmqpq/spring-assignment/api-reference)
