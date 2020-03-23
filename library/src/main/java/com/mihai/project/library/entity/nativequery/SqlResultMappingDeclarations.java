package com.mihai.project.library.entity.nativequery;

import javax.persistence.*;

@Entity
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "TopBook",
                classes = @ConstructorResult(
                        targetClass = TopBookRented.class,
                        columns = {
                                @ColumnResult(name = "title"),
                                @ColumnResult(name = "number")
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "TopEmployees",
                classes = @ConstructorResult(
                        targetClass = TopEmployees.class,
                        columns = {
                                @ColumnResult(name = "id"),
                                @ColumnResult(name = "username"),
                                @ColumnResult(name = "book_read")
                        }
                )
        )
})
public class SqlResultMappingDeclarations {
    @Id
    private int id;
}
