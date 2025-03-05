package br.com.dio.persistence.entity;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class BoardColumnEntity {

  private Long id;
  private String name;
  private int order;
  private BoardColumnKindEnum kind;
  private BoardEntity board = new BoardEntity();

}
