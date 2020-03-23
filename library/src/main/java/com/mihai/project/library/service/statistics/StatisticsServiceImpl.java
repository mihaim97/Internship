package com.mihai.project.library.service.statistics;

import com.mihai.project.library.dao.BookRentDAO;
import com.mihai.project.library.dao.UserDAO;
import com.mihai.project.library.dto.statistics.TopBookDTO;
import com.mihai.project.library.dto.statistics.TopEmployeesDTO;
import com.mihai.project.library.entity.nativequery.TopBookRented;
import com.mihai.project.library.entity.nativequery.TopEmployees;
import com.mihai.project.library.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private BookRentDAO bookRentDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public List<TopBookRented> topBooksRented(TopBookDTO topBookDTO) {
        if (topBookDTO.getTopBook() != null) {
            return bookRentDAO.statisticsTopBookRent(topBookDTO.getTopBook(), topBookDTO.getStartDate(), topBookDTO.getEndDate());
        } else {
            return bookRentDAO.statisticsTopBookRent(topBookDTO.getStartDate(), topBookDTO.getEndDate());
        }
    }

    @Override
    @Transactional
    public List<TopEmployees> topEmployees(TopEmployeesDTO topEmployeesDTO) {
        return bookRentDAO.statisticsTopEmployees(topEmployeesDTO.getStartDate(), topEmployeesDTO.getEndDate());
    }

    @Override
    @Transactional
    public List<User> allUserThatAreLate() {
        return userDAO.statisticsAllUserThatAreLate();
    }

}
