# 게시판 프로젝트

## 프로젝트 개발 환경

- IDE(IntelliJ)
- Spring
- MySQL
- JPA

## 프로젝트 기능 및 설계

- 회원가입 기능
  - 모든 사용자는 회원가입을 할 수 있다.
  - 회원가입시 아이디는 유일해야한다.

- 로그인 기능
  - 로그인시 아이디와 패스워드가 일치해야한다.

- 게시글 작성 기능
  - 로그인한 멤버가 글을 작성할 수 있다.
  - 멤버는 게시글 제목(텍스트), 게시글 내용(텍스트)를 작성할 수 있다.

- 게시글 조회 기능
  - 모든 사용자(로그인 하지 않아도)는 게시글을 조회 할 수 있다.

- 게시글 수정 기능
  - 게시글을 작성한 멤버만 해당 게시글을 수정할 수 있다.

- 게시글 삭제 기능
  - 게시글을 작성한 멤버만 해당 게시글을 삭제할 수 있다.

- 게시글 목록 조회 기능
  - 로그인하지 않은 사용자도 게시글을 조회할 수 있다.
  - 게시글은 종류가 많을수 있으므로 paging 처리를 한다.

- 댓글 작성 기능
  - 로그인한 모든 멤버는 게시글에 댓글을 작성할 수 있다.

- 댓글 수정 기능
  - 댓글 작성자는 댓글을 수정할 수 있다.

- 댓글 삭제 기능
  - 댓글 작성자는 댓글을 삭제할 수 있다.

[//]: # (- 게시글 검색 기능)
[//]: # (  - 게시글 제목, 게시글 내용, 작성자로 검색한다.)

## ERD

![ERD](https://d2sqqdb3t4xrq5.cloudfront.net/upload/C6YbcgfiHvXjDZWzr/c3lmcVhQdFhDWGpaU0FCdUhfcVNnUU1hZUNTamJja3FLRkwucG5n)

## 주차별 개발 계획 (2024년 2월 19일 ~ 2024년 3월 )

#### 2주차 : 회원가입 및 로그인

#### 3주차 : 게시글 CRUD

#### 4주차 : 댓글 CRUD

#### 5주차 : 리팩토링 및 추가 기능 구현


## Tech Stack
<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>