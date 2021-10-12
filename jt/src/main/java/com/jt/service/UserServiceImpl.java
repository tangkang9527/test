package com.jt.service;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    //根据u/p查询数据库
    @Override
    public String findUserByUP(User user) {
        //1.将密码加密
        byte[] bytes = user.getPassword().getBytes();
        String md5Pass = DigestUtils.md5DigestAsHex(bytes);
        System.out.println(md5Pass);
        user.setPassword(md5Pass);
        //2.根据用户名和密文查询数据库
        User userDB = userMapper.findUserByUP(user);
        //3.判断userDB是否有值
        if(userDB == null){
            //查询没有数据.返回null
            return null;
        }
        //秘钥特点: 唯一性,迷惑性  UUID:几乎可以保证唯一性
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 分页Sql:  每页10条
     *      select * from user limit 起始位置,查询条数
     * 第一页:
     *      select * from user limit 0,10  0-9 含头不含尾
     * 第二页:
     *      select * from user limit 10,10
     * 第三页:
     *      select * from user limit 20,10
     * 第N页:
     *      select * from user limit (页数-1)条数,条数
     * @param pageResult
     * @return
     */
    @Override
    public PageResult getUserListByPage(PageResult pageResult) {
        //1.总数
        long total = userMapper.findTotal();
        //2.分页结果
        int size = pageResult.getPageSize();    //条数
        int start = (pageResult.getPageNum()-1) * size; //起始位置
        String query = pageResult.getQuery();   //查询条件
        //查询分页数据
        List<User> userList =
                userMapper.findUserListByPage(start,size,query);
        //将返回值结果进行封装
        return pageResult.setTotal(total).setRows(userList);
    }

    @Override
    public void updateStatusById(User user) {

        userMapper.updateStatusById(user);
    }

    /**
     *   业务:实现业务数据封装
     *   1.密码加密
     *   2.设定默认状态
     *   3.设定默认时间
     */
    @Override
    public void saveUser(User user) {
        String password = user.getPassword();
        String MD5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        //获取当前时间
        Date date = new Date();
        user.setPassword(MD5Pass).setStatus(true)
                .setCreated(date).setUpdated(date);
        userMapper.saveUser(user);
    }

    @Override
    public User findUserById(Integer id) {

        return userMapper.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        //设定当前时间
        user.setUpdated(new Date());
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUserById(Integer id) {

        userMapper.deleteUserById(id);
    }
}
