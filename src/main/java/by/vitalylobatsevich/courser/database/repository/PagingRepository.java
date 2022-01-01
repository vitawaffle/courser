package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.CourserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PagingRepository<T extends CourserEntity, ID> extends CourserRepository<T, ID> {

    Page<T> findAll(Pageable pageable);

}
