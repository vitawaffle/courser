package by.vitalylobatsevich.courser.database.repository;

import by.vitalylobatsevich.courser.database.entity.AppEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PagingRepository<T extends AppEntity, ID> extends AppRepository<T, ID> {

    Page<T> findAll(Pageable pageable);

}
