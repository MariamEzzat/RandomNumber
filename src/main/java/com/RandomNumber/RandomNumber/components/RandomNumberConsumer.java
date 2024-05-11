package com.RandomNumber.RandomNumber.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.RandomNumber.RandomNumber.repository.randomNumberRepo;
import com.RandomNumber.RandomNumber.entity.randomNumber;

import java.util.Optional;

@Component
public class RandomNumberConsumer {

    @Autowired
    private  randomNumberRepo randomNumberRepo;


    @RabbitListener(queues = "randomNumbersQueue")
    public void receiveRandomNumber(String message) throws JsonProcessingException {
        System.out.println("Received message: " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(message);

        // Extract values from JSON node
        String id = jsonNode.get("id").asText();
        int randomNumber = jsonNode.get("randomNumber").asInt();

        // Multiply the random number by two
        int result = randomNumber * 2;

        //Optional<randomNumber> num=randomNumberRepo.findById(Long.valueOf(id));


        randomNumber savedNumber=new randomNumber();
        savedNumber.setId(Integer.parseInt(id));
        savedNumber.setNum(randomNumber);
        savedNumber.setDoubleNum(result);

        randomNumberRepo.save(savedNumber);

    }
}
