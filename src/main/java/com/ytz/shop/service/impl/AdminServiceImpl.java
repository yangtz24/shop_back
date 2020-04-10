package com.ytz.shop.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ytz.shop.pojo.Permission;
import com.ytz.shop.pojo.UserAdmin;
import com.ytz.shop.repository.PermissionRepository;
import com.ytz.shop.repository.UserAdminRepository;
import com.ytz.shop.service.AdminService;
import com.ytz.shop.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: AdminServiceImpl
 * @Description: 后台用户 业务实现
 * @author: yangtianzeng
 * @date: 2020/4/8 14:28
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private UserAdminRepository userAdminRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PermissionRepository permissionRepository;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public UserAdmin register(UserAdmin userAdmin) {
        UserAdmin admin = new UserAdmin();
        BeanUtils.copyProperties(userAdmin, admin);
        admin.setCreateTime(new Date());
        admin.setStatus(UserAdmin.STATUS_ENABLE);
        //判断是否有重名的用户
        UserAdmin admin1 = userAdminRepository.findByUsername(userAdmin.getUsername());
        UserAdmin admin2 = userAdminRepository.findByNickName(userAdmin.getNickName());
        if (ObjectUtil.isNotEmpty(admin1) || ObjectUtil.isNotEmpty(admin2)) {
            return null;
        }
        //密码加密
        String encodePassword = passwordEncoder.encode(userAdmin.getPassword());
        admin.setPassword(encodePassword);

        //添加
        UserAdmin uAdmin = userAdminRepository.save(admin);
        return uAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码输入不正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = jwtTokenUtil.generateToken(userDetails);
           // userAdminRepository.updateLoginDate(username, new Date());
        } catch (AuthenticationException e) {
            e.printStackTrace();
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public UserAdmin getAdminByUsername(String username) {
        UserAdmin userAdmin = userAdminRepository.findByUsername(username);
        if (ObjectUtil.isNotEmpty(userAdmin)) {
            return userAdmin;
        }
        return null;
    }

    @Override
    public List<Permission> getPermissionList(Long adminId) {
        List<Permission> permissionList = permissionRepository.findByAdminIdPermissionList(adminId);
        if (CollUtil.isNotEmpty(permissionList)) {
            return permissionList;
        }
        return null;
    }
}
