package ServiceTest;

import com.RandomNumber.RandomNumber.components.RandomNumberConsumer;
import com.RandomNumber.RandomNumber.entity.randomNumber;
import com.RandomNumber.RandomNumber.repository.randomNumberRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class consumerTests {

    private RandomNumberConsumer randomNumberConsumer;
    private randomNumberRepo randomNumberRepo;

    @BeforeEach
    public void setup() {
        randomNumberRepo = mock(randomNumberRepo.class);
    }

    @Test
    public void testReceiveRandomNumber() throws Exception {
        // Mock incoming message
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = JsonNodeFactory.instance.objectNode();
        jsonNode.put("id", "123");
        jsonNode.put("randomNumber", 42);
        String message = objectMapper.writeValueAsString(jsonNode);

        // Invoke the method
        randomNumberConsumer.receiveRandomNumber(message);

        // Verify that randomNumberRepo.save() is called with the correct randomNumber object
        randomNumber expectedRandomNumber = new randomNumber();
        expectedRandomNumber.setId(123);
        expectedRandomNumber.setNum(42);
        expectedRandomNumber.setDoubleNum(84);
        verify(randomNumberRepo).save(expectedRandomNumber);
    }
}
