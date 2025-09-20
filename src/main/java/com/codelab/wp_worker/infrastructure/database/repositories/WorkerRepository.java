package com.codelab.wp_worker.infrastructure.database.repositories;

import com.codelab.wp_worker.infrastructure.database.entity.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {

}
