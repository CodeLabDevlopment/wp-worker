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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListWorkersCaseTest {

    @Mock
    PersistenceGateway persistenceGateway;
    @InjectMocks
    ListWorkersCase listWorkersCase;

    @Test
    @DisplayName("Should list all workers")
    void case01() {
        List<Worker> workerExpected = WorkerFactory.createWorkers();

        when(persistenceGateway.findAllWorkers()).thenReturn(workerExpected);

        List<Worker> worker = listWorkersCase.execute();

        assertAll("Assert worker",
                () -> assertNotNull(worker),
                () -> assertEquals(workerExpected.size(), worker.size())
        );
        verify(persistenceGateway, times(1)).findAllWorkers();
    }

}