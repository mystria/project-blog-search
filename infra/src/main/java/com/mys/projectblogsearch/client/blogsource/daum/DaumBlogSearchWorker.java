package com.mys.projectblogsearch.client.blogsource.daum;

import com.mys.projectblogsearch.Constants;
import com.mys.projectblogsearch.client.BlogSearchWorker;
import com.mys.projectblogsearch.client.blogsource.daum.model.BlogSearchRequest;
import com.mys.projectblogsearch.client.blogsource.daum.model.SortType;
import com.mys.projectblogsearch.request.PortBlogListRequest;
import com.mys.projectblogsearch.response.PortBlogListResponse;
import com.mys.projectblogsearch.type.VendorType;
import com.mys.projectblogsearch.util.KeywordSeparatorUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DaumBlogSearchWorker implements BlogSearchWorker {

    private static final DaumBlogMapper MAPPER = DaumBlogMapper.INSTANCE;

    private final DaumFeignClient feignClient;

    @Override
    public VendorType getVendorType() {

        return VendorType.DAUM;

    }

    @Override
    public PortBlogListResponse search(@NotNull @Valid PortBlogListRequest request) {

        this.validateQuery(request.getQuery());
        BlogSearchRequest blogSearchRequest = MAPPER.toBlogSearchRequest(request);
        return MAPPER.toPortBlogListResponse(blogSearchRequest,
            feignClient.search(
                blogSearchRequest.getQuery(),
                Optional.ofNullable(blogSearchRequest.getSort())
                    .map(SortType::getValue)
                    .orElse(null),
                blogSearchRequest.getPage(),
                blogSearchRequest.getSize()));

    }

    private void validateQuery(String query) {

        // This is a limitation of our database structure

        List<String> words = KeywordSeparatorUtil.separateToStream(query)
            .filter(word -> word.length() > Constants.KEYWORD_SIZE_LIMIT)
            .toList();

        if (!words.isEmpty()) {
            throw new IllegalArgumentException("Too long words: " + String.join(", ", words));
        }

    }

}
