# REST-Assured
### 학습 목표
- [] REST-Assured 개념
- [] REST-Assured 사용 방법

### 학습용 인수 조건
```
Feature: 스터디 관리

  Scenario: 스터디 생성
    When 스터디 생성 요청을 하면
    Then 스터디 생성이 성공한다.
  
  [Request]  
  POST /study HTTP/1.1
  accept: */*
  content-type: application/json; charset=UTF-8
  
  {
      "topic": "REST-Assured 스터디"
  }  
  
  [Response] 
  HTTP/1.1 201 
  Location: /study/1
  Content-Type: application/json
  Date: Thu, 11 Nov 2022 00:11:51 GMT
  
  {
      "id": 1,
      "name": "REST-Assured 스터디",
      "createdDate": "2022-08-11T09:11:51.997",
      "modifiedDate": "2022-08-11T09:11:51.997"
  } 
    
  Scenario: 스터디 목록 조회
    Given : 스터디 생성 요청을 하고
    When 스터디 목록 조회를 요청 하면
    Then 스터디 목록을 응답받는다.
    
  [Request]  
  GET /study HTTP/1.1
  accept: application/json
  host: localhost:49468
  
  [Response] 
  HTTP/1.1 200
  Content-Type: application/json
  Date: Thu, 11 Nov 2022 00:11:51 GMT
  
  [
    {
        "id": 1,
        "topic": "REST-Assured 스터디",
        "progress": "recruitment",
        "createdDate": "2022-08-11T09:11:51.997",
        "modifiedDate": "2022-08-11T09:11:51.997"
    },
    {
        "id": 2, 
        "topic": "Spring 스터디",
        "progress": "Proceeding",
        "createdDate": "2022-08-11T09:11:51.997",
        "modifiedDate": "2022-08-11T09:11:51.997"
    }
  ]
  
  Scenario: 스터디 수정
    Given : 스터디 생성 요청을 하고
    When 스터디 정보 수정을 요청하면 
    Then 스터디의 정보가 수정된다.
    
  [Request] 
  PUT /study/1 HTTP/1.1
  accept: */*
  content-type: application/json; charset=UTF-8
  content-length: 45
  host: localhost:49468
  
  {
      "topic": "Spring 스터디",
      "progress": "Proceeding"
  }
  
  [Response] 
  HTTP/1.1 200 
  Date: Thu, 11 Nov 2022 00:11:51 GMT
  
  
  Scenario: 스터디 삭제
    Given : 스터디 생성을 요청을 하고
    When 스터디 삭제를 요청하면 
    Then 스터디가 삭제된다. 
    
  [Request] 
  DELETE /study/1 HTTP/1.1
  accept: */*
  host: localhost:49468

  [Response]  
  HTTP/1.1 204 
  Date: Thu, 11 Nov 2022 00:11:51 GMT
  
```

### 학습을 위한 요구사항 
- [] 스터디(study)를 생성한다. 
  - [] 주제(topic)을 가진다.
    - [] 주제는 변경이 가능하다.
  - [] 스터디는 진행 상황을 가진다.
    - [] 모집중, recruitment(default)  
    - [] 진행중, Proceeding
    - [] 종료됨, Terminated 
    - [] 진행 상황은 변경이 가능하다.
      - [] 진행 상황은 되돌릴 수 없다. 
  - [] 스터디 목록을 확인할 수 있다.
  - [] 스터디를 삭제할 수 있다.
    - [] 모집중인 스터디만 삭제가 가능하다. 

### 참고  
- [REST-Assured 문서](https://rest-assured.io)
- [REST-Assured 깃허브 가이드](https://github.com/rest-assured/rest-assured)
- [REST-Assured이란?](https://www.guru99.com/rest-assured.html)
