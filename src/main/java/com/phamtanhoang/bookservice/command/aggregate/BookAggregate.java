package com.phamtanhoang.bookservice.command.aggregate;


import com.phamtanhoang.bookservice.command.command.CreateBookCommand;
import com.phamtanhoang.bookservice.command.command.DeleteBookCommand;
import com.phamtanhoang.bookservice.command.command.UpdateBookCommand;
import com.phamtanhoang.bookservice.command.event.BookCreatedEvent;
import com.phamtanhoang.bookservice.command.event.BookDeletedEvent;
import com.phamtanhoang.bookservice.command.event.BookUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class BookAggregate {

  @AggregateIdentifier
  private String bookId;
  private String name;
  private String author;
  private Boolean isReady;

  public BookAggregate() {

  }

  @CommandHandler
  public BookAggregate(CreateBookCommand createBookCommand) {

    BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
    BeanUtils.copyProperties(createBookCommand, bookCreatedEvent);
    AggregateLifecycle.apply(bookCreatedEvent);
  }

  @EventSourcingHandler
  public void on(BookCreatedEvent event) {
    this.bookId = event.getBookId();
    this.author = event.getAuthor();
    this.isReady = event.getIsReady();
    this.name = event.getName();
  }

  @CommandHandler
  public void handle(UpdateBookCommand updateBookCommand) {

    BookUpdatedEvent bookUpdatedEvent = new BookUpdatedEvent();
    BeanUtils.copyProperties(updateBookCommand, bookUpdatedEvent);
    AggregateLifecycle.apply(bookUpdatedEvent);
  }

  @EventSourcingHandler
  public void on(UpdateBookCommand event) {
    this.bookId = event.getBookId();
    this.author = event.getAuthor();
    this.isReady = event.getIsReady();
    this.name = event.getName();
  }

  @CommandHandler
  public void handle(DeleteBookCommand deleteBookCommand) {

    BookDeletedEvent bookDeletedEvent = new BookDeletedEvent();
    BeanUtils.copyProperties(deleteBookCommand, bookDeletedEvent);
    AggregateLifecycle.apply(bookDeletedEvent);
  }

  @EventSourcingHandler
  public void on(DeleteBookCommand event) {
    this.bookId = event.getBookId();
  }
}
