package com.mys.projectblogsearch.v1.response;

import com.mys.projectblogsearch.v1.error.DomainError;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StandardFailureResponse {

    private Set<DomainError> errors;

}
