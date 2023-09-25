package com.mys.projectblogsearch.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "keyword_count", indexes = {
    @Index(columnList = "keyword", name = "idx_keyword_count_keyword")
})
public class KeywordCountEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, columnDefinition = "bigint unsigned")
    private BigInteger id;

    @Column(nullable = false, updatable = false,
        columnDefinition = "varchar(255) comment '검색어'")
    private String keyword;

    @Column(nullable = false, updatable = false,
        columnDefinition = "int comment '검색 횟수'")
    private Integer count;

}
