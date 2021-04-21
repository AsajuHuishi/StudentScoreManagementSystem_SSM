package indi.huishi.service;


import indi.huishi.pojo.Page;
import indi.huishi.pojo.Statistics;
import indi.huishi.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 学生成绩管理系统 Service层
 * @author Huishi
 */

public interface StudentService {

    /**
     * 分页查询
     * @return
     */
    Page<Student> show(Integer pageNo, Integer pageSize);

    int add(Student student);

    void update(Student student);

    int delete(Integer id);

    Student queryById(String id);

    Student queryByNo(String no);

    List<Student> queryByName(String name);

    List<Statistics> statistics();
}
