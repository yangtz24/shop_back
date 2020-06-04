
package com.ytz.shop.dto;

import com.ytz.shop.common.Constant;
import com.ytz.shop.pojo.Permission;
import com.ytz.shop.pojo.UserAdmin;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: AdminUserDetails
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/3/16 15:01
 */
@Data
@NoArgsConstructor
public class AdminUserDetails implements UserDetails {
    private UserAdmin userAdmin;
    private List<Permission> permissionList;
    public AdminUserDetails(UserAdmin userAdmin, List<Permission> permissionList) {
        this.userAdmin = userAdmin;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(permissionList == null) {
            return null;
        }
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return userAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userAdmin.getStatus().equals(Constant.STATUS_ENABLE);
    }
}
