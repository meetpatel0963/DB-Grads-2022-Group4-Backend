package com.db.grad.javaapi.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(BookUserId.class)
@Table(name = "Book_User")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookUser {

    @Id
    private Long book_id;

    @Id
    private Long user_id;

}
