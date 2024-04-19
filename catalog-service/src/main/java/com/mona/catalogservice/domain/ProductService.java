package com.mona.catalogservice.domain;

import com.mona.catalogservice.ApplicationProperties;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;

    ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public PagedResult<Product> getProducts(int pageNo) {
        // sorting data before querying
        Sort sort = Sort.by("name").ascending();

        // here page numbers start from 0 so return 0 if they are -ve or 0
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, applicationProperties.pageSize(), sort);
        // mapping product entity fields to abstracted version of product
        Page<Product> productsPage = productRepository.findAll(pageable).map(ProductMapper::toProduct);
        return new PagedResult<>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber() + 1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious());
    }

    public Optional<Product> getProductsByCode(String code) {

        return productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
