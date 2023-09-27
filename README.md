# Project Blog Search

<a href="https://github.com/mystria/project-blog-search"><img src="https://img.shields.io/github/stars/mystria/project-blog-search.svg?style=social" /></a>
<a href="https://github.com/mystria/project-blog-search/pulse"><img src="https://img.shields.io/github/commit-activity/m/mystria/project-blog-search.svg?label=commits" /></a>

_Project Blog Search_ 는 Hexagonal Architecture 기반으로 설계된 블로그 검색 API 이며 아래와 같은 기능을 제공합니다.  

- 블로그 검색 시 pagination 으로 결과 반환
  - offset / limit 을 전달하여 페이지를 조회 가능합니다.
  - offset 이 1 일 경우 첫 페이지, 2 일 경우 두번째 페이지를 의미합니다.성
  - limit 은 한 페이지에 표시할 블로그 수를 의미합니다.
  - sort 는 검색 우선순위 이며 optional 한 값입니다.
    - ACCURACY : 정확도 순으로 정렬
    - RECENCY : 최신 순으로 정렬
- 블로그 검색어 순위 반환
  - 가장 인기있는 검색어 10위를 조회 가능합니다.
  - 검색어 순위는 최소 5초, 최대 10초마다 갱신됩니다.
  - 검색어 순위는 검색어가 많이 검색된 순으로 정렬됩니다.

## Requirements

- Java 17 혹은 그 이상의 버전을 요구합니다.
- Gradle 8.2 혹은 그 이상의 버전을 요구합니다.
- [Kakao API Key](https://developers.kakao.com/) 는 필수 입니다.
  - -Dclient.kakao.api-key={YOUR_API_KEY}
- [Naver API Key](https://developers.naver.com/main/) 는 선택 입니다.
  - -Dclient.naver.client-id={YOUR_CLIENT_ID}
  - -Dclient.naver.client-secret={YOUR_CLIENT_SECRET}
- Debug 로그를 조회하려면 옵션을 활성화 해주세요.
  - -Dlogging.level.com.mys=DEBUG

## How to run

    $ java -jar -Dclient.kakao.api-key={YOUR_API_KEY} -Dclient.naver.client-id={YOUR_CLIENT_ID} -Dclient.naver.client-secret={YOUR_CLIENT_SECRET} bootstrap-0.0.1-SNAPSHOT.jar

- Swagger UI 를 통해 API 를 테스트 할 수 있습니다.
  - http://localhost:8080/swagger-ui/index.html
- H2 Database Console 을 통해 데이터베이스를 조회 할 수 있습니다.
  - http://localhost:8080/h2-console
  - JDBC URL : jdbc:h2:mem:blog_search
  - UserName : sa

## Limitations

- 검색어는 띄워쓰기를 허용하며, 띄워쓴 단어는 각각의 검색어로 취급합니다.
- 단, 개별 검색어는 255 자 이하의 문자열이어야 합니다.
- offset 은 1 에서 50 까지의 정수를 입력해야 합니다.
- limit 은 1 에서 50 까지의 정수를 입력해야 합니다.
- 검색 결과의 totalCount 를 이용하여 page 를 계산해야 합니다. (totalCount 가 100 일 때 limit 이 10 을 적용하면, 총 페이지는 10 페이지가 존재할 수 있습니다.)
- 검색 결과의 totalCount 는 블로그 정보 제공 서비스의 영향으로 부정확할 수 있습니다.
- 검색어 순위는 완전한 실시간이 아닙니다. 검색어 순위는 최소 5초, 최대 10초마다 갱신됩니다.
- 기본적으로 Daum 블로그를 검색합니다.
- 블로그 정보를 제공하는 서비스의 상황에 따라 검색이 실패하거나 검색 결과가 없을 수 있습니다.
- Daum 블로그가 응답하지 않으면 잠시 에러가 반환 될 수 있으며, 에러가 지속되면 Naver 블로그로 검색합니다.

## Contact

- [GitHub Issues](https://github.com/mystria/project-blog-search/issues)
