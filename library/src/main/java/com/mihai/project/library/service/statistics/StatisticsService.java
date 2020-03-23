package com.mihai.project.library.service.statistics;

import com.mihai.project.library.dto.statistics.TopBookDTO;
import com.mihai.project.library.dto.statistics.TopEmployeesDTO;
import com.mihai.project.library.entity.nativequery.TopBookRented;
import com.mihai.project.library.entity.nativequery.TopEmployees;
import com.mihai.project.library.entity.user.User;

import java.util.List;

public interface StatisticsService {

    List<TopBookRented> topBooksRented(TopBookDTO topBookDTO);

    List<TopEmployees> topEmployees(TopEmployeesDTO topEmployeesDTO);

    List<User> allUserThatAreLate();


}
