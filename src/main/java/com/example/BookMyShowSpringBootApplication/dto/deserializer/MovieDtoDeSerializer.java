//package com.example.BookMyShowSpringBootApplication.dto.deserializer;
//
//
//import com.example.BookMyShowSpringBootApplication.dto.MovieResponseDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.kafka.common.errors.SerializationException;
//import org.apache.kafka.common.serialization.Deserializer;
//
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//public class MovieDtoDeSerializer implements Deserializer<List<MovieResponseDto>> {
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public List<MovieResponseDto> deserialize(String s, byte[] bytes) {
//        try {
//            if(bytes == null) return null;
//            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8),
//                    objectMapper.getTypeFactory().constructCollectionType(List.class, MovieResponseDto.class));
//        } catch (Exception e) {
//            throw new SerializationException("Error while de-serializing Movie " + e.toString());
//        }
//    }
//
//}
//
