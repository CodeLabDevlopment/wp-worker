package com.codelab.wp_worker.application.gateway;

import com.codelab.wp_worker.domain.model.Worker;

import java.util.List;

public interface PersistenceGateway {

    List<Worker> findAllWorkers();

    Worker findWorkerById(Long workerId);

}
