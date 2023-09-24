package com.mys.projectblogsearch.db.repository;

import com.mys.projectblogsearch.db.model.KeywordCountEntity;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordCountRepository extends JpaRepository<KeywordCountEntity, BigInteger> {

}
