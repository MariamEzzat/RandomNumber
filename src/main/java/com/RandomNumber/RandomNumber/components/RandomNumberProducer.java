package com.RandomNumber.RandomNumber.components;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.RandomNumber.RandomNumber.repository.randomNumberRepo;
import com.RandomNumber.RandomNumber.entity.randomNumber;

import java.util.Random;
@Component
public class RandomNumberProducer {

    @Autowired
    private Queue randomNumbersQueue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private  randomNumberRepo randomNumberRepo;

    @Scheduled(fixedRate = 10000)
    public void sendRandomNumber() {
        Random randNumber = new Random();
        //database saving integer
        int randomNum = randNumber.nextInt(1000);

        randomNumber num=new randomNumber();
        num.setNum(randomNum);
        randomNumber savedNumber=randomNumberRepo.save(num);
        String jsonObj = "{\"id\": \"" + savedNumber.getId() + "\", \"randomNumber\": " + randomNum + "}";


        rabbitTemplate.convertAndSend(randomNumbersQueue.getName(), jsonObj);
        System.out.println("Sent message with random number: " + randomNum);




    }
}
