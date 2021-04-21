package indi.huishi.controller;

import indi.huishi.pojo.Page;
import indi.huishi.pojo.Statistics;
import indi.huishi.pojo.Student;
import indi.huishi.service.StudentService;
import indi.huishi.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制器
 * @Author: Huishi
 * @Date: 2021/4/17 23:29
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    private final String REDIRECT_PATH = "redirect:/student/show.action?pageNo=";
    private Integer pageSize = 8;

    /**
     * 跳转到指定页面显示
     * @param pageNo
     * @return
     */
    @RequestMapping("/show.action")
    public ModelAndView showAll (String pageNo, HttpSession session, HttpServletRequest request){
        Integer pageNo2 = WebUtils.parseInt(pageNo, 1);
        Page<Student> studentPage = studentService.show(pageNo2, pageSize);
//        System.out.println(request.getServletPath());
//        System.out.println(request.getContextPath());
//        System.out.println(request.getRequestURL());
//        /student/show.action
//                /shizuo
//        http://localhost:8080/shizuo/student/show.action
        // 保存当前url
        studentPage.setUrl(request.getRequestURL().toString());
        ModelAndView view = new ModelAndView("menu/show");
        view.addObject("page",studentPage);
        // 将page保存在Session里面
        session.setAttribute("studentPage",studentPage);
        return view;
    }

    /**
     * 根据要修改的id查询该学生信息
     * @param stuId
     * @return
     */
    @RequestMapping("/queryById.action")
    public ModelAndView queryById(String stuId){
        Student student = studentService.queryById(stuId);
//        System.out.println("queryById"+student);
        ModelAndView view = new ModelAndView("menu/update");
        view.addObject("student",student);
        return view;
    }

    /**
     * 根据修改之后的学生信息更新数据库，重定向回主页
     * @param student 来自接收的表单信息
     * @return
     */
    @RequestMapping("/update.action")
    public String updateById (Student student, HttpSession session){
//        System.out.println("updateById"+student);
        studentService.update(student);
        // 获取当前页
        Page<Student> studentPage = (Page)session.getAttribute("studentPage");
        Integer pageNo = studentPage.getPageNo();
        // 重定向 显示全部
        System.out.println(REDIRECT_PATH + pageNo);
        return REDIRECT_PATH + pageNo;
    }

    /**
     * 删除该学生信息，重定向回主页
     * @param stuId
     * @return
     */
    @RequestMapping("/delete.action")
    public String deleteById(String stuId, HttpSession session){
        Integer id = Integer.parseInt(stuId);
        int delete = studentService.delete(id);
        // 获取当前页
        Page<Student> studentPage = (Page)session.getAttribute("studentPage");
        Integer pageNo = studentPage.getPageNo();
        // 重定向 显示全部
        return REDIRECT_PATH + pageNo;
    }

    /**
     * 接收按学号查询的 学号，返回查找结果
     * @param
     * @return
     */
    @RequestMapping("/queryByNo.action")
    public ModelAndView queryByNo(@RequestParam("queryNo") String no){
        System.out.println("输入学号"+no);
        Student student = studentService.queryByNo(no);
        if (student==null){
            System.out.println("您查找的学号不存在！");
            ModelAndView modelAndView = new ModelAndView("menu/queryByNo");
            modelAndView.addObject("status","404");//学号不存在状态码
            modelAndView.addObject("previous",no);//学号不存在状态码
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("menu/queryResult");
            List<Student> studentList = new ArrayList<>();
            studentList.add(student);
            modelAndView.addObject("stuList", studentList);
            modelAndView.addObject("status","200");//学号不存在状态码
            return modelAndView;
        }
    }

    /**
     * 按姓名查询，返回查找结果
     * @param
     * @return
     */
    @RequestMapping("/queryByName.action")
    public ModelAndView queryByName(@RequestParam("queryName") String name){
        System.out.println("输入姓名"+name);
        List<Student> studentList = studentService.queryByName(name);
        if (studentList.size()==0){
            System.out.println("您查找的学生不存在！");
            ModelAndView modelAndView = new ModelAndView("menu/queryByName");
            modelAndView.addObject("status","404");//学号不存在状态码
            modelAndView.addObject("previous",name);//学号不存在状态码
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("menu/queryResult");
            modelAndView.addObject("stuList",studentList);
            modelAndView.addObject("status","200");//学号不存在状态码
            return modelAndView;
        }
    }

    /**
     * 添加 学生信息 返回到主页
     */
    @RequestMapping("/add.action")
    public String add(Student student, HttpSession session, Model model) throws RuntimeException{
        // 首先查询学号是否已被使用（学号是unique）
        Student query = studentService.queryByNo(student.getNo());
        System.out.println("查询"+query);
        // 获取当前页
        Page<Student> studentPage = (Page) session.getAttribute("studentPage");
        Integer pageNo = studentPage.getPageNo();

        if(query == null) {// 学号未被使用
            int add = studentService.add(student);
            if (add == 0) {
                System.out.println("添加失败");
                model.addAttribute("status", "400");
                return "menu/add";
            }else{
                System.out.println("添加成功");
                return REDIRECT_PATH + pageNo;
            }
        }else{
            System.out.println("该学号已被使用");
            model.addAttribute("status", "404");
            return "menu/add";
        }
    }

    /**
     * 统计学生信息
     */
    @RequestMapping("/stat.action")
    public ModelAndView stat(){
        List<Statistics> list = studentService.statistics();
        ModelAndView view = new ModelAndView("menu/stat");
        view.addObject("stat",list);
        return view;
    }


}
