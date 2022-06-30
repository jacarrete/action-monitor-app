package com.betvictor.listenerservice;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import com.impossibl.postgres.jdbc.PGDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.sql.DataSource;
import java.sql.Statement;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ListenerServiceApplication {

    private final PGDataSource pgDataSource;

    public static void main(String[] args) {
        SpringApplication.run(ListenerServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStart() {
        pgNotifyToWebSocket(pgDataSource);
    }

    public void pgNotifyToWebSocket(DataSource dataSource) {

        PGNotificationListener listener = (int processId, String channelName, String payload)
                -> log.info("notification = " + payload);

        try (PGConnection connection = (PGConnection) dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("LISTEN test");
            statement.close();
            connection.addNotificationListener(listener);
            while (true) {
                Thread.sleep(500);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

}
