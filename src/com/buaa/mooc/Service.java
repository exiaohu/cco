package com.buaa.mooc;

import com.buaa.mooc.dao.EduAdminDao;
import com.buaa.mooc.dao.StudentDao;
import com.buaa.mooc.dao.TeacherDao;
import com.buaa.mooc.entity.EduAdmin;
import com.buaa.mooc.entity.Student;
import com.buaa.mooc.entity.Teacher;
import com.buaa.mooc.entity.User;

/**
 * Created by huxia on 2017/6/27.
 */
public class Service {
    private StudentDao studentDao = new StudentDao();
    private TeacherDao teacherDao = new TeacherDao();
    private EduAdminDao eduAdminDao = new EduAdminDao();

    /**
     * @功能 验证登陆
     * @param id 登录名
     * @param password 密码
     * @return 返回用户
     */
    public User login(Integer id, String password) {
        User user;
        try {
            user = studentDao.findById(id);
            if (user != null && ((Student) user).getPassword().trim().equals(password.trim())) {
                return user;
            }
            user = teacherDao.findById(id);
            if (user != null && ((Teacher) user).getPassword().trim().equals(password.trim())) {
                return user;
            }
            user = eduAdminDao.findById(id);
            if (user != null && ((EduAdmin) user).getPassword().trim().equals(password.trim())) {
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
