package com.RandomNumber.RandomNumber.repository;

import com.RandomNumber.RandomNumber.entity.randomNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface randomNumberRepo extends JpaRepository<randomNumber, Long>   {

}
