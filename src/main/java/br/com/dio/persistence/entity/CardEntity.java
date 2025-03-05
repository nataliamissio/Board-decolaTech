package br.com.dio.persistence.entity;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class CardEntity {

  private Long id;
  private String name;
  private int order;
  private BoardColumnKindEnum kind;

}
