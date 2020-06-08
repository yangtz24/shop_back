package com.ytz.shop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ytz.shop.pojo.Permission;
import com.ytz.shop.repository.jpa.PermissionRepository;
import com.ytz.shop.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PermissionServiceImpl
 * @Description: 权限业务处理类
 * @author: yangtianzeng
 * @date: 2020/4/15 14:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getParentAndChildren() {
        List<Permission> permissions = permissionRepository.findByParentId(0L);
        return permissions;
    }

    @Override
    public Page<Permission> list(Integer pageNum, Integer pageSize, String key) {
        if (pageNum != null && pageNum != 0) {
            pageNum = pageNum - 1;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Specification<Permission> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StrUtil.isNotEmpty(key)) {
                Predicate p3 = criteriaBuilder.like(root.get("name"), key + "%");
                list.add(p3);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        Page<Permission> permissions = permissionRepository.findAll(specification, pageable);
        /*Iterator<Permission> iterator = roles.iterator();
        iterator.forEachRemaining(permission -> {
            if (permission.getStatus().equals(Constant.STATUS_ENABLE)) {
                permission.setState(true);
            } else {
                permission.setState(false);
            }
            // 处理日期
            LocalDateTime dateTime = permission.getCreateTime();
            permission.setCreateTimeStr(DateUtil.date(dateTime));
        });*/
        return permissions;
    }
}
