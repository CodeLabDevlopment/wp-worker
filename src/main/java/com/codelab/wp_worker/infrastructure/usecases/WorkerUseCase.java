package com.codelab.wp_worker.infrastructure.usecases;

import com.codelab.wp_worker.application.gateway.PersistenceGateway;
import com.codelab.wp_worker.application.usecases.worker.FindWorkerByIdCase;
import com.codelab.wp_worker.application.usecases.worker.ListWorkersCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerUseCase {

    @Bean
    public ListWorkersCase listWorkersCase(PersistenceGateway persistenceGateway) {
        return new ListWorkersCase(persistenceGateway);
    }

    @Bean
    public FindWorkerByIdCase findWorkerByIdCase(PersistenceGateway persistenceGateway) {
        return new FindWorkerByIdCase(persistenceGateway);
    }

}
