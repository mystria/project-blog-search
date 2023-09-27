# Project Blog Search

<a href="https://github.com/mystria/project-blog-search"><img src="https://img.shields.io/github/stars/mystria/project-blog-search.svg?style=social" /></a>
<a href="https://github.com/mystria/project-blog-search/pulse"><img src="https://img.shields.io/github/commit-activity/m/mystria/project-blog-search.svg?label=commits" /></a>

_Project Blog Search_ 는 Hexagonal Architecture 기반으로 설계된 블로그 검색 API 이며 아래와 같은 기능을 제공합니다.  

- 블로그 검색 시 pagination 으로 결과 반환
  - GET /v1/blogs?query={QUERY}&offset={OFFSET}&limit={LIMIT}&sort={SORT}
  - query 로 검색어를 입력합니다.
  - offset / limit 을 전달하여 페이지 단위로 조회합니다.
  - offset 이 1일 경우 첫 페이지, 2일 경우 두 번째 페이지를 의미합니다.
  - limit 은 한 페이지에 표시할 블로그 수를 의미합니다.
  - sort 는 검색 우선순위 이며 optional 한 값입니다.
    - ACCURACY : 정확도순으로 정렬
    - RECENCY : 최신순으로 정렬
- 블로그 검색어 순위 반환
  - GET /v1/keywords/top10 
  - 가장 인기 있는 검색어 10위를 조회합니다.
  - 검색어 순위는 최소 5초, 최대 10초마다 갱신됩니다.
  - 검색어 순위는 검색어가 많이 검색된 순으로 정렬되어 표시됩니다.

## Requirements

- Java 17 혹은 그 이상의 버전을 요구합니다.
- Gradle 8.2 혹은 그 이상의 버전을 요구합니다.
- [Kakao API Key](https://developers.kakao.com/) 는 필수 입니다.
  - -Dclient.kakao.api-key={YOUR_API_KEY}
- [Naver API Key](https://developers.naver.com/main/) 는 선택 입니다.
  - -Dclient.naver.client-id={YOUR_CLIENT_ID}
  - -Dclient.naver.client-secret={YOUR_CLIENT_SECRET}

## How to run

- excutable jar 를 생성합니다. 제공하는 [[다운로드](http://naver.me/xoYt9iQ5)] 는 일정기간 이후 삭제됩니다.

      $ ./gradlew clean build

- bootstrap 모듈의 build(bootstrap-0.0.1-SNAPSHOT.jar) 가 실행 가능한 jar 파일입니다.
- jar 파일명을 변경하여 실행합니다. 여기서는 blog-search.jar 로 가정합니다.

      $ cp ./bootstrap/build/libs/bootstrap-0.0.1-SNAPSHOT.jar blog-search.jar
      $ java -jar -Dclient.kakao.api-key={YOUR_API_KEY} -Dclient.naver.client-id={YOUR_CLIENT_ID} -Dclient.naver.client-secret={YOUR_CLIENT_SECRET} blog-search.jar

- Swagger UI 를 통해 API 명세를 확인하고 테스트 할 수 있습니다.
  - http://localhost:8080/swagger-ui/index.html
- H2 Database Console 을 통해 데이터베이스를 조회 할 수 있습니다.
  - http://localhost:8080/h2-console
  - JDBC URL : jdbc:h2:mem:blog_search
  - UserName : sa
- Debug 로그를 조회하려면 옵션을 활성화 해주세요.
  - -Dlogging.level.com.mys=DEBUG
- 기본 실행 포트 번호는 8080 입니다. 아래와 같이 변경 가능합니다.
  - -Dserver.port=8081

## Limitations

- 검색어는 띄어쓰기를 허용하며, 띄어 쓴 단어는 각각의 검색어로 취급합니다.
- 단, 개별 검색어는 255 자 이하의 문자열이어야 합니다.
- offset 은 1 에서 50 까지의 정수를 입력해야 합니다.
- limit 은 1 에서 50 까지의 정수를 입력해야 합니다.
- 검색 결과의 totalCount 를 이용하여 page 를 계산해야 합니다. (totalCount 가 100 일 때 limit 이 10 을 적용하면, 총 10 개의 페이지가 존재할 수 있습니다.)
- 검색 결과의 totalCount 는 블로그 정보 제공 서비스의 영향으로 부정확할 수 있습니다.
- 검색어 순위는 완전한 실시간이 아닙니다. 검색어 순위는 최소 5초, 최대 10초마다 갱신됩니다.
- 기본적으로 Daum 블로그를 검색합니다.
- 블로그 정보를 제공하는 서비스의 상황에 따라 검색이 실패하거나 검색 결과가 없을 수 있습니다.
- Daum 블로그가 응답하지 않으면 잠시 에러가 반환 될 수 있으며, 에러가 지속되면 Naver 블로그로 검색합니다.
- 보안 관련 기능은 지원하지 않습니다. XSS, CSRF 등의 공격에 취약할 수 있습니다.

## Open Sources

- Service
  - [Spring Boot](https://spring.io/projects/spring-boot) - Spring Boot 3 기반으로, Spring 의 다양한 요소를 활용하여 서비스를 구현하였습니다.
  - [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - 데이터베이스를 생성 및 조회합니다. 검색어 정보를 관리합니다.
  - [H2 Database](https://www.h2database.com/html/main.html) - H2 Database 를 통해 서비스 내에 데이터베이스를 제공합니다.
  - [Spring Circuit Breaker](https://spring.io/projects/spring-cloud-circuitbreaker) - Circuit Breaker 를 제공합니다.
  - [Resilience4j](https://resilience4j.readme.io/) - Resilience4j 를 통해 Blog 검색의 Circuit Breaker 를 구현합니다.
  - [Spring OpenFeign](https://spring.io/projects/spring-cloud-openfeign) - HTTP Client 를 구현합니다. Blog DataSource 를 조회합니다.
  - [Caffeine Cache](https://github.com/ben-manes/caffeine) - Local Cache 를 이용하여 검색어 순위를 캐싱 및 버퍼링 합니다.
- Implementation
  - [Lombok](https://projectlombok.org/) - 코드를 간결하게 작성합니다.
  - [MapStruct](https://mapstruct.org/) - DTO, Entity 들 간의 변환을 간소화합니다.
  - [Swagger](https://swagger.io/) - API 명세를 작성 및 제공합니다.
  - [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/) - 간단한 데이터 처리에 활용합니다.
- Test
  - [JUnit5](https://junit.org/junit5/) - Spring Context 를 읽어 테스트를 할 경우에 사용합니다.
  - [Mockito](https://site.mockito.org/) - Static method 를 mocking 합니다.
  - [WireMock](http://wiremock.org/) - 외부 서비스를 mocking 합니다.
  - [Spock Framework](https://spockframework.org/) - Groovy 기반으로 비즈니스 로직을 테스트 합니다.
  - [Instancio](https://www.instancio.org/) - 테스트 작성시 임의의 객체 생성을 자동화합니다.

## Contact

- [GitHub Issues](https://github.com/mystria/project-blog-search/issues)
