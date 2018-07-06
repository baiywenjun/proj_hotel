package com.zxt.hotel.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zxt.hotel.entity.SysAdmin;
import com.zxt.hotel.pojo.SysAdminExtVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管理员 Mapper 接口
 * </p>
 *
 * @author wenjun
 * @since 2018-05-18
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    List<SysAdminExtVO> querySysAdminExtList(Page page, @Param("ew") Wrapper wrapper);

}
