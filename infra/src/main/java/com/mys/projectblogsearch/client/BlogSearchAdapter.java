package com.mys.projectblogsearch.client;

import com.mys.projectblogsearch.BlogSearchPort;
import com.mys.projectblogsearch.type.VendorType;
import com.mys.projectblogsearch.request.PortBlogListRequest;
import com.mys.projectblogsearch.response.PortBlogListResponse;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlogSearchAdapter implements BlogSearchPort {

    private final List<BlogSearchWorker> vendorWorkers;

    private Map<VendorType, BlogSearchWorker> vendorWorkerMap;

    @PostConstruct
    void postConstruct() {

        vendorWorkerMap = Optional.ofNullable(vendorWorkers).orElse(List.of()).stream()
            .map(worker -> Map.entry(worker.getVendorType(), worker))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    @Override
    public PortBlogListResponse search(PortBlogListRequest request) {

        return pickProperWorker(request).search(request);

    }

    private BlogSearchWorker pickProperWorker(PortBlogListRequest request) {

        BlogSearchWorker worker = vendorWorkerMap.get(request.getVendorType());
        if (Objects.nonNull(worker)) {
            return worker;
        } else {
            throw new NotImplementedException("Not implemented vendor type: " + request.getVendorType());
        }

    }

}
