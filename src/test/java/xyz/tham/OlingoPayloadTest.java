package xyz.tham;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.serialization.ODataDeserializerException;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.format.ContentType;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;

public class OlingoPayloadTest {
    @Test
    public void testHello() {
        System.out.println("Called from test");
    }

    @Test
    public void testOlingoParsing() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("olingo_payload.json").getFile());
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ODataClient oDataClient = ODataClientFactory.getClient();
            Instant start = Instant.now();

            ClientEntitySet entitySet = oDataClient.getReader().readEntitySet(inputStream, ContentType.APPLICATION_JSON);
            System.out.println("Entries size: " + entitySet.getEntities().size());

            Instant finish = Instant.now();
            long timeTaken = Duration.between(start, finish).toSeconds();

            System.out.println("Time taken: " + timeTaken);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ODataDeserializerException e) {
            e.printStackTrace();
        }
    }
}
