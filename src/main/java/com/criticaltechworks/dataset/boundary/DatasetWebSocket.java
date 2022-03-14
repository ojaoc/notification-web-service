package com.criticaltechworks.dataset.boundary;

import com.criticaltechworks.dataset.boundary.dto.DatasetDTO;
import com.criticaltechworks.dataset.boundary.encoding.DatasetEncoder;
import com.criticaltechworks.dataset.control.DatasetService;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.context.ThreadContext;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RequiredArgsConstructor

@ServerEndpoint(value = "/websocket/dataset", encoders = DatasetEncoder.class)
@ApplicationScoped
public class DatasetWebSocket {

    private final List<Session> sessions = new CopyOnWriteArrayList<>();

    private final DatasetService datasetService;

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);

        // TODO: change this

        ManagedExecutor executor = ManagedExecutor.builder().maxAsync(1)
                .propagated(ThreadContext.CDI, ThreadContext.TRANSACTION)
                .build();

        ThreadContext threadContext = ThreadContext.builder()
                .propagated(ThreadContext.CDI,
                        ThreadContext.TRANSACTION)
                .build();

        executor.runAsync(threadContext.contextualRunnable(() -> {

            try {
                DatasetDTO currentDataset = datasetService.getCurrentDataset();
                session.getBasicRemote().sendObject(currentDataset);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }));
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("onMessage> " + message);
    }

    public void updateDataset(DatasetDTO datasetDTO) {
        sessions.forEach(session -> {
            session.getAsyncRemote().sendObject(datasetDTO, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }
}
