package com.mys.projectblogsearch.db.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mys.projectblogsearch.db.model.KeywordCountEntity;
import com.mys.projectblogsearch.db.repository.JpaMarker;
import com.mys.projectblogsearch.db.repository.KeywordCountRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import javax.sql.DataSource;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

@Tag("integration-test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaRepositories(basePackageClasses = {JpaMarker.class})
class KeywordCountRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private KeywordCountRepository repository;

    @Test
    void loadContextSuccessfully() {

        assertNotNull(dataSource);
        assertNotNull(jdbcTemplate);
        assertNotNull(entityManager);
        assertNotNull(repository);

    }

    @Test
    void save() {

        // given
        var entity = Instancio.of(KeywordCountEntity.class)
            .create();

        // when
        var result = repository.save(entity);

        //then
        assertEquals(entity.getKeyword(), result.getKeyword());
        assertEquals(entity.getCount(), result.getCount());
        assertNotNull(result.getId());
        assertNotNull(result.getCreatedAt());

    }

    @Test
    void findTopKeywordsWithCount() {

        // given

        var fruits = List.of("사과", "딸기", "복숭아", "바나나", "참외", "감", "배", "포도", "수박", "귤", "망고", "자두", "오렌지", "레몬", "키위");
        var entities = Instancio.ofList(KeywordCountEntity.class)
            .size(100)
            .generate(Select.field(KeywordCountEntity.class, "keyword"),
                gen -> gen.oneOf(fruits))
            .create();

        // when
        repository.saveAll(entities);
        var result1 = repository.findTopKeywordsWithCount(10);
        var result2 = repository.findTopKeywordsWithCount(5);

        //then
        assertEquals(10, result1.size());
        assertTrue(fruits.contains(result1.get(0).getKeyword()));
        assertTrue(result1.get(0).getTotalCount() > 0);
        for (int i = 0; i < result1.size() - 1; i++) {
            assertTrue(result1.get(i).getTotalCount() >= result1.get(i + 1).getTotalCount());
        }
        assertEquals(5, result2.size());

    }

}
