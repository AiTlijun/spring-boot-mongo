package com.example.springbootmongo.repository;

import com.example.springbootmongo.bean.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer>, PagingAndSortingRepository<User, Integer>, JpaSpecificationExecutor {
    /**
     * @param ids
     * @function 自定义JPQL
     */
    // 注意更新和删除是需要加事务的， 并且要加上 @Modify的注解
    @Modifying
    @Transactional
    @Query("delete from User where id in (?1)")
    void deleteBatch(List<Integer> ids);

    // 这个是通过spring data拼接关键字进行的操作
    void deleteUserByIdIn(List<Integer> ids);
}
