package com.scau.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scau.entity.Account;
import com.scau.mapper.AccountMapper;
import com.scau.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 账号 服务实现类
 * </p>
 *
 * @author wyn
 * @since 2020-09-21
 */
@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {


}
