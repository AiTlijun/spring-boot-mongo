package com.example.springbootmongo.serviceImpl;

import com.example.springbootmongo.entity.UserLog;
import com.example.springbootmongo.mapper.UserLogMapper;
import com.example.springbootmongo.service.UserLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserLog record) {
        return userLogMapper.insert(record);
    }

    @Override
    public int insertSelective(UserLog record) {
        return userLogMapper.insertSelective(record);
    }

    @Override
    public UserLog selectByPrimaryKey(Integer id) {
        return userLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserLog record) {
        return userLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserLog record) {
        return userLogMapper.updateByPrimaryKey(record);
    }
}
