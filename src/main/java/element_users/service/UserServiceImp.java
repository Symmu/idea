package element_users.service;

import element_users.dao.UserDao;
import element_users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther:shuang
 * @Date:2021/2/26 - 02 - 26 - 13:53:35
 * @Description: vue_cli
 * @versin:1.0
 */
@Service
@Transactional
public class UserServiceImp implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> findByPage(Integer pageNow, Integer rows) {
        int start = (pageNow-1)*rows;
        return userDao.findByPage(start,rows);
    }

    @Override
    public Long findTotals() {
        return userDao.findTotals();
    }


}
