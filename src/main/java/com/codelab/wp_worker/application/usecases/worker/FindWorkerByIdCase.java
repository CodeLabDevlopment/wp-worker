package com.codelab.wp_worker.application.usecases.worker;

import com.codelab.wp_worker.application.gateway.PersistenceGateway;
import com.codelab.wp_worker.domain.model.Worker;

public record FindWorkerByIdCase(PersistenceGateway persistenceGateway) {

    public Worker execute(Long workerId) {
        return this.persistenceGateway.findWorkerById(workerId);
    }

}
