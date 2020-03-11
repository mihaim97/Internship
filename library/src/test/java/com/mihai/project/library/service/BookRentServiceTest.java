package com.mihai.project.library.service;

import com.mihai.project.library.service.rent.BookRentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BookRentServiceTest {

    @Mock
    private BookRentService bookRentResource;

    @Test
    public void given_whenMarkBookRentAsLateIfExist_returnEmpty(){
        Assertions.assertEquals(new ArrayList<>().size(), bookRentResource.markBookRentAsLateIfExist().size());
    }

}
