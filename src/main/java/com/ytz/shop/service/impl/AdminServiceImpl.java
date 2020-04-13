package com.ytz.shop.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    @Override
    public Page<UserAdmin> list(Integer pageNum, Integer pageSize, String key, String mobile, Integer status) {
        if (pageNum != 0) {
            pageNum = pageNum - 1;
        }
        // 2.0 之前
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(pageNum, pageSize, sort);
        // SpringBoot2.0使用方式
        Pageable pageable =PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        Specification<UserAdmin> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StrUtil.isNotEmpty(mobile)) {
                Predicate p1 = criteriaBuilder.equal(root.get("mobile"), mobile);
                list.add(p1);
            }
            if  (ObjectUtil.isNotEmpty(status)) {
                Predicate p2 = criteriaBuilder.equal(root.get("status"), status);
                list.add(p2);
            }
            if (StrUtil.isNotEmpty(key)) {
                Predicate p3 = criteriaBuilder.like(root.get("username"), key + "%");
                list.add(p3);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        Page<UserAdmin> admins = userAdminRepository.findAll(specification, pageable);
        Iterator<UserAdmin> iterator = admins.iterator();
        iterator.forEachRemaining(admin -> {
            if (admin.getStatus().equals(UserAdmin.STATUS_ENABLE)) {
                admin.setState(true);
            } else {
                admin.setState(false);
            }
        });
        return admins;
        /*Page<UserAdmin> userAdmins = userAdminRepository.findAll(pageable);
        Iterator<UserAdmin> userAdminIterator = userAdmins.iterator();
        return userAdminIterator;*/
    }

    @Override
    public UserAdmin detail(Long id) {
        // UserAdmin$HibernateProxy$pUOzwGtW["hibernateLazyInitializer"])
        return userAdminRepository.getOne(id);
    }

    @Override
    public UserAdmin add(UserAdmin admin) {
        admin.setCreateTime(new Date());
        admin.setStatus(UserAdmin.STATUS_ENABLE);
        UserAdmin userAdmin = userAdminRepository.save(admin);
        return userAdmin;
    }

    @Override
    public int edit(UserAdmin userAdmin, Long id) {
        userAdmin.setId(id);
        userAdmin.setUpdateTime(new Date());
        int result = userAdminRepository.update(userAdmin);
        return result;
    }

    @Override
    public int modifyStatus(Long id, Integer status) {
        int result = userAdminRepository.updateStatus(id, status);
        return result;
    }

    @Override
    public void remove(Long id) {
        userAdminRepository.deleteById(id);
    }
}
