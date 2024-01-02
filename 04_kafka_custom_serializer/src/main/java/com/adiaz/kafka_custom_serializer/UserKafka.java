package com.adiaz.kafka_custom_serializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserKafka {
  private Long id;
  private String name;
  private String surname;

  public UserKafka() {
    super();
  }
}
