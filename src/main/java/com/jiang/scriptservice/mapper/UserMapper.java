package com.jiang.scriptservice.mapper;

import com.jiang.scriptservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author jiang
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 添加用户
     * @param user
     */
    void insert(User user);

    /**
     * 查询用户
     * @param user
     * @return
     */
    User select(User user);

    /**
     * 通过Token查找用户
     * @param token
     * @return
     */
    User findByToken(String token);


    /**
     * 通过ID查找
     * @param creatorId
     * @return
     */
    User findById(int creatorId);

}
