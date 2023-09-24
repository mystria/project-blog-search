package com.mys.projectblogsearch.db.dao;

import com.mys.projectblogsearch.db.model.KeywordCountEntity;
import com.mys.projectblogsearch.db.repository.KeywordCountRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeywordCountDao {

    private final KeywordCountRepository repository;

    public KeywordCountEntity save(KeywordCountEntity entity) {

        return repository.save(entity);
    }

    public List<KeywordCountEntity> findTop10KeywordCounts() {

        return IterableUtils.toList(repository.findAll());
    }

}