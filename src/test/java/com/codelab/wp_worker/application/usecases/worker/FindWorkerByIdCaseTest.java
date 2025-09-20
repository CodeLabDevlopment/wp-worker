package com.codelab.wp_worker.application.usecases.worker;

import com.codelab.wp_worker.application.gateway.PersistenceGateway;
import com.codelab.wp_worker.domain.model.Worker;
import com.codelab.wp_worker.factory.WorkerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindWorkerByIdCaseTest {

    @Mock
    PersistenceGateway persistenceGateway;
    @InjectMocks
    FindWorkerByIdCase findWorkerByIdCase;

    @Test
    @DisplayName("Should find a worker by id")
    void case01() {
        Worker workerExpected = WorkerFactory.createWorker();

        when(persistenceGateway.findWorkerById(1L)).thenReturn(workerExpected);

        Worker worker = findWorkerByIdCase.execute(1L);

        assertAll("Assert worker",
                () -> assertNotNull(worker),
                () -> assertEquals(workerExpected.id(), worker.id()),
                () -> assertEquals(workerExpected.name(), worker.name()),
                () -> assertEquals(workerExpected.dailyIncome(), worker.dailyIncome())
        );
        verify(persistenceGateway, times(1)).findWorkerById(anyLong());
    }

}