package com.phamtanhoang.bookservice.command.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Book {
  @Id
  private String bookId;
  private String name;
  private String author;
  private Boolean isReady;

}
