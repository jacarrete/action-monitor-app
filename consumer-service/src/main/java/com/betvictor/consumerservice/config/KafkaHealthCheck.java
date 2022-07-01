package com.betvictor.consumerservice.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterOptions;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaHealthCheck {

    private KafkaAdmin kafkaAdmin;

    public KafkaHealthCheck(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
    }

    @Bean
    public AdminClient kafkaAdminClient() {
        return AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    @Bean
    public HealthIndicator kafkaHealthIndicator(AdminClient kafkaAdminClient) {
        final DescribeClusterOptions options = new DescribeClusterOptions()
                .timeoutMs(1000);

        return new AbstractHealthIndicator() {
            @Override
            protected void doHealthCheck(Health.Builder builder) {
                // When Kafka is not connected, describeCluster() method throws
                // an exception which in turn sets this indicator as being DOWN.
                kafkaAdminClient.describeCluster(options);

                builder.up().build();
            }
        };
    }

}
