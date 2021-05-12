# GraphQL - Spring boot kotlin example (MVC) 

## 참고
밑의 링크 내용을 따라서 java 코드를 kotlin 으로 따라해 봄
- https://github.com/philip-jvm/learn-spring-boot-graphql

## 참고 링크들
- Spring Boot GraphQL: https://github.com/graphql-java-kickstart/graphql-spring-boot
- GraphQL open issue for standardizing Date and DateTime scalar: https://github.com/graphql/graphql-spec/issues/579
- Introduction to extended scalars: https://www.graphql-java.com/blog/introducing-extended-scalars/
- Java Extended scalars github: https://github.com/graphql-java/graphql-java-extended-scalars

## 로컬서 돌릴때 볼 것들
- playground: http://localhost:8080/playground
- graphiql: http://localhost:8080/graphiql
- voyager: http://localhost:8080/voyager

## spring security 부분
http header (application.yml > graphql.playground.headers) 쪽에 user_id, user_roles 를 설정하면 해당 헤더의 값을 보고 filter 를 타도록 구성이 되어있음

## 추후 봐야할 것
- reactive stack 에서는?
- security 적용
- apollo federation 살펴보기
