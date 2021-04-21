package indi.huishi.service.impl;

import indi.huishi.dao.StudentDao;
import indi.huishi.pojo.Page;
import indi.huishi.pojo.Statistics;
import indi.huishi.pojo.Student;
import indi.huishi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Huishi
 * @Date: 2021/4/17 22:57
 */
@Service("studentService")
@Transactional(rollbackFor = Exception.class)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    /**
     * 分页显示当前页的学生信息
     * @param pageNo 当前页数
     * @param pageSize 每页显示信息
     * @return 当前页
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Student> show(Integer pageNo, Integer pageSize){
        // 生成一个Page对象
        Integer pageTotalCount = studentDao.getTotalCount();
        Integer begin = (pageNo - 1) * pageSize;
        List<Student> list = studentDao.showAll(begin, pageSize);
        Integer pageTotal;
        if (pageTotalCount % pageSize == 0){
            pageTotal = pageTotalCount / pageSize;
        } else{
            pageTotal = pageTotalCount / pageSize + 1;
        }

        Page page = new Page();
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        page.setItems(list);
        page.setPageTotalCount(pageTotalCount);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(Student student){
        return studentDao.addStudent(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Student student){
        studentDao.updateStudent(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer id){
        return studentDao.deleteStudent(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Student queryByNo(String no){
        return studentDao.queryByNo(no);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Student queryById(String id){
        return studentDao.queryById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Student> queryByName(String name){
        return studentDao.queryByName(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Statistics> statistics(){
        return studentDao.getStatistics();
    }
}
