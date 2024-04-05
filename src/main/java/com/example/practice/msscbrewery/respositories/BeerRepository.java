package com.example.practice.msscbrewery.respositories;

import com.example.practice.msscbrewery.domain.Beer;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface BeerRepository extends ListPagingAndSortingRepository<Beer, UUID>, ListCrudRepository<Beer, UUID> {
}
