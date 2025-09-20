package com.codelab.wp_worker.factory;

import com.codelab.wp_worker.domain.model.Worker;

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

}
