package com.codelab.wp_worker.application.usecases.worker;

import com.codelab.wp_worker.application.gateway.PersistenceGateway;
import com.codelab.wp_worker.domain.model.Worker;

import java.util.List;

public record ListWorkersCase(PersistenceGateway persistenceGateway) {

    public List<Worker> execute() {
        return persistenceGateway.findAllWorkers();
    }

}
