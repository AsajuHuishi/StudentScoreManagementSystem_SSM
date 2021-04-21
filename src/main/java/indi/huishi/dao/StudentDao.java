package indi.huishi.dao;

import indi.huishi.pojo.Statistics;
import indi.huishi.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 学生成绩管理系统 数据访问层
 * @author Huishi
 */
@Repository
public interface StudentDao{

    /**
     * 显示全部信息：分页查询
     *
     * @return
     */
    @Select("select * from student_score order by class_name, no limit #{begin},#{pageSize}")
    @ResultMap("studentMap")
    List<Student> showAll(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize);

    /**
     * 增加学生记录
     *
     * @param student
     * @return
     */
    @Insert("insert into student_score (no, name, score, class_name) values(#{no},#{name},#{score},#{className})")
    int addStudent(Student student) ;

    /**
     * 修改学生记录
     *
     * @param student
     * @return
     */
    @Select("update student_score set no=#{no}, name=#{name}, score=#{score}, class_name=#{className} where id=#{id}")
    void updateStudent(Student student);

    /**
     * 按id删除学生记录
     *
     * @param id
     * @return
     */
    @Delete("delete from student_score where id=#{id}")
    int deleteStudent(Integer id);

    /**
     * 根据学生id查询
     *
     * @param id
     * @return
     */
    @Select("select * from student_score where id=#{id}")
    @Results(id = "studentMap", value = {
            @Result(column = "class_name", property = "className")
    })
    Student queryById(String id);

    /**
     * 根据学生学号查询
     *
     * @param no
     * @return
     */
    @Select("select * from student_score where no=#{no}")
    @ResultMap("studentMap")
    Student queryByNo(String no);

    /**
     * 根据学生姓名查询,考虑重名情况，返回List集合
     *
     * @param name
     * @return
     */
    @Select("select * from student_score where name=#{name}")
    @ResultMap("studentMap")
    List<Student> queryByName(String name);


    /**
     * 统计(分班级统计学生数量,最高分,最低分,平均值)...
     *
     * @return
     */
    @Select("select class_name as className, count(*) as countStudent, max(score) as maxScore, min(score) as minScore, round(avg(score),4) as avgScore from student_score group by class_name order by score desc")
    List<Statistics> getStatistics();

    /**
     * 查询结果行数，用于分页
     * @return
     */
    @Select("select count(*) as totalCount from student_score")
    @Results(id = "totalCount",value = {
            @Result(column = "totalCount",property = "java.lang.Integer")
    })
    Integer getTotalCount();


}

