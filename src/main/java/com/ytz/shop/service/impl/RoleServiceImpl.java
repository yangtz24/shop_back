package com.ytz.shop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ytz.shop.common.Constant;
import com.ytz.shop.pojo.Role;
import com.ytz.shop.repository.PermissionRepository;
import com.ytz.shop.repository.RoleRepository;
import com.ytz.shop.service.RoleService;
import com.ytz.shop.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: RoleServiceImpl
 * @Description: 角色业务处理
 * @author: yangtianzeng
 * @date: 2020/4/14 10:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Page<Role> list(Integer pageNum, Integer pageSize, String key) {
        if (pageNum != null && pageNum != 0) {
            pageNum = pageNum - 1;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Specification<Role> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StrUtil.isNotEmpty(key)) {
                Predicate p3 = criteriaBuilder.like(root.get("name"), key + "%");
                list.add(p3);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        Page<Role> roles = roleRepository.findAll(specification, pageable);
        Iterator<Role> iterator = roles.iterator();
        iterator.forEachRemaining(role -> {
            if (role.getStatus().equals(Constant.STATUS_ENABLE)) {
                role.setState(true);
            } else {
                role.setState(false);
            }
            // 处理日期
            LocalDateTime dateTime = role.getCreateTime();
            role.setCreateTimeStr(DateUtil.date(dateTime));
        });
        return roles;
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public int modifyStatus(Long id, Integer status) {
        int result = roleRepository.updateStatusById(id, status);
        return result;
    }

    @Override
    public Role getPermissionsByRoleId(Long roleId) {
        Optional<Role> optional = roleRepository.findById(roleId);
        Role role = optional.get();
        return role;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
