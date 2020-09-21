package com.scau.controller.infoController;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scau.Result.Result;
import com.scau.entity.Account;
import com.scau.entity.vo.AccountVO;
import com.scau.service.AccountService;
import com.scau.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 登陆 前端控制器
 * </p>
 *
 * @author wyn
 * @since 2020-09-21
 */

@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {

    @Autowired
    private AccountService accountService;

    @PostMapping("login")
    public Result login(@RequestBody AccountVO accountVO){
        String account=accountVO.getAccount();
        String password= MD5.encrypt(accountVO.getPassword());
        if(StringUtils.isEmpty(account)){
            log.error("账号为空");
            return Result.error();
        }
        if(StringUtils.isEmpty(password)){
            log.error("密码为空");
            return Result.error();
        }
        QueryWrapper<Account> wrapper=new QueryWrapper<>();
        wrapper.eq("account",account);
        Account one = accountService.getOne(wrapper);
        if(one==null){
            log.error("该账号不存在");
            return Result.error();
        }
        if(!one.getPassword().equals(password)){
            log.error("密码错误");
            return Result.error();
        }
        return Result.ok();
    }


}
