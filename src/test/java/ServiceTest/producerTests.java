package ServiceTest;

import com.RandomNumber.RandomNumber.components.RandomNumberProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.RandomNumber.RandomNumber.repository.randomNumberRepo;
import com.RandomNumber.RandomNumber.entity.randomNumber;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class producerTests {
    private Queue randomNumbersQueue;
    private RabbitTemplate rabbitTemplate;
    private randomNumberRepo randomNumberRepo;
    private RandomNumberProducer randomNumberProducer;
    
    // @Mock
    // private Queue randomNumbersQueue;
    // @Mock
    // private RabbitTemplate rabbitTemplate;
    // @Mock
    // private randomNumberRepo randomNumberRepo;
    // @InjectMocks
   // private RandomNumberProducer randomNumberProducer;
    
        @BeforeEach
    public void setup() {
        randomNumbersQueue = mock(Queue.class);
        rabbitTemplate = mock(RabbitTemplate.class);
        randomNumberRepo = mock(randomNumberRepo.class);
    }

    @Test
    public void testSendRandomNumber() {
        // Mock random number generation
        randomNumber randomNum = new randomNumber();
        randomNum.setNum(42);
        when(randomNumberRepo.save(any())).thenReturn(randomNum);

        // Invoke the method
        randomNumberProducer.sendRandomNumber();

        // Verify that randomNumberRepo.save() is called with the correct randomNumber object
        verify(randomNumberRepo).save(any());

        // Verify that rabbitTemplate.convertAndSend() is called with the correct arguments
        verify(rabbitTemplate).convertAndSend(eq(randomNumbersQueue.getName()), anyString());
    }
}
