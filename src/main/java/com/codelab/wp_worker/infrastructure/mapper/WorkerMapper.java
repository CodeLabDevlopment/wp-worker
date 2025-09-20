package com.codelab.wp_worker.infrastructure.mapper;

import com.codelab.wp_worker.domain.model.Worker;
import com.codelab.wp_worker.infrastructure.database.entity.WorkerEntity;

public final class WorkerMapper {

    public static Worker toDomain(WorkerEntity workerEntity) {
        return new Worker(
                workerEntity.getId(),
                workerEntity.getName(),
                workerEntity.getDailyIncome()
        );
    }

}
