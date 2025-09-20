package com.codelab.wp_worker.factory;

import com.codelab.wp_worker.domain.model.Worker;
import com.codelab.wp_worker.infrastructure.database.entity.WorkerEntity;

import java.util.List;

public final class WorkerFactory {

    public static Worker createWorker() {
        return new Worker(1L, "John Doe", 100.0);
    }

    public static List<Worker> createWorkers() {
        return List.of(
                new Worker(1L, "John Doe", 100.0),
                new Worker(2L, "Jane Smith", 150.0),
                new Worker(3L, "Alice Johnson", 200.0)
        );
    }

    public static WorkerEntity createWorkerEntity() {
        return new WorkerEntity(1L, "John Doe", 100.0);
    }

    public static List<WorkerEntity> createWorkerEntities() {
        return List.of(
                new WorkerEntity(1L, "John Doe", 100.0),
                new WorkerEntity(2L, "Jane Smith", 150.0),
                new WorkerEntity(3L, "Alice Johnson", 200.0)
        );
    }

}
