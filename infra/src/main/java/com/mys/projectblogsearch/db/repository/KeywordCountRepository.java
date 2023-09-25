package com.mys.projectblogsearch.db.repository;

import com.mys.projectblogsearch.db.model.KeywordCountEntity;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordCountRepository extends JpaRepository<KeywordCountEntity, BigInteger> {

    @Query(value = "SELECT T.keyword, SUM(T.count) AS totalCount FROM KEYWORD_COUNT T GROUP BY T.keyword ORDER BY totalCount DESC LIMIT :limit", nativeQuery = true)
    List<GroupedKeywordCount> findTopKeywordsWithCount(@Param("limit") Integer limit);

    interface GroupedKeywordCount {

        // Projection : https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections

        String getKeyword();

        Integer getTotalCount();

    }

}
