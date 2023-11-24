package org.exampleAsyn;

import com.azure.core.util.BinaryData;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusMessageBatch;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;
public class AsyncApp {
    String connectionString= "Endpoint=sb://niuniutest.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=YLcJCtCUoUnRlXhG0EEI9Wu03CPg2EIoV+ASbOWIurE=";
    String queueName = "myqueue";

    public static void main(String[] args) throws InterruptedException {
        AsyncApp sample = new AsyncApp();
        sample.run();
    }

    private void run () {
        AtomicBoolean sampleSuccessful = new AtomicBoolean(false);
        CountDownLatch countdownLatch = new CountDownLatch(1);

        ServiceBusSenderClient sender = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();


        final ServiceBusMessageBatch messageBatch = sender.createMessageBatch();
        IntStream.range(0, 10)
                .mapToObj(index -> new ServiceBusMessage(BinaryData.fromString("Hello world! " + index)))
                .forEach( messageBatch::tryAddMessage );


        sender.sendMessages(messageBatch);

        sender.close();
    }
}


