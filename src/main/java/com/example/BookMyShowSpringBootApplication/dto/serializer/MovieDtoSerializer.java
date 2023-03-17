package com.example.BookMyShowSpringBootApplication.dto.serializer;

import com.example.BookMyShowSpringBootApplication.dto.MovieDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;


public class MovieDtoSerializer implements Serializer<MovieDto> {

    @Override
       public byte[] serialize(String s, MovieDto movieDto) {
            byte[] retVal = null;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                retVal = objectMapper.writeValueAsString(movieDto).getBytes();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return retVal;
        }
}
