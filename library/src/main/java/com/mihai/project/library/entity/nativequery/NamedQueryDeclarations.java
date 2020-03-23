package com.mihai.project.library.entity.nativequery;


import javax.persistence.*;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name = "TopBook",
                query = "select bk.title as title , count(br.book) as number from book_rent br " +
                        "inner join books bk on br.book = bk.id " +
                        "where br.date_rent between :startDate and :endDate " +
                        "group by br.book " +
                        "order by count(br.book) desc ",
                resultSetMapping = "TopBook"),
        @NamedNativeQuery(name = "TopEmployees",
                query = "select br.emp_id as id, us.username as username, count(br.emp_id) as book_read " +
                        "from book_rent br inner join appusers us on br.emp_id = us.id " +
                        "where br.date_rent between :startDate and :endDate " +
                        "group by br.emp_id " +
                        "order by count(br.emp_id) desc ",
                resultSetMapping = "TopEmployees")
})
public class NamedQueryDeclarations {
    @Id
    private int id;
}
