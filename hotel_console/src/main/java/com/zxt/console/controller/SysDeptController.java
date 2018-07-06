package com.zxt.console.controller;

import com.zxt.common.constant.Constant;
import com.zxt.common.result.R;
import com.zxt.hotel.entity.SysDept;
import com.zxt.hotel.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;


/**
 * 部门管理
 *
 */
@Controller
@RequestMapping("/dept")
public class SysDeptController  {
	@Autowired
	private SysDeptService sysDeptService;

	@RequestMapping("/page")
	public String page(){
		return "components/dept";
	}

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<SysDept> list(){
		List<SysDept> deptList = sysDeptService.queryList(new HashMap<String, Object>());

		return deptList;
	}

	/**
	 * 选择部门(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@ResponseBody
	public R select(){
		// todo userId
		Long userId = 1l;
		List<SysDept> deptList = sysDeptService.queryList(new HashMap<String, Object>());
		//添加一级部门
		if(userId == Constant.SUPER_ADMIN){
			SysDept root = new SysDept();
			root.setDeptId(0L);
			root.setName("一级部门");
			root.setParentId(-1L);
			root.setOpen(true);
			deptList.add(root);
		}

		return R.ok().put("deptList", deptList);
	}

	/**
	 * 上级部门Id(管理员则为0)
	 */
	@RequestMapping("/info")
	@ResponseBody
	public R info(){
		// todo userId
		Long userId = 1l;
		long deptId = 0;
		if(userId != Constant.SUPER_ADMIN){
			List<SysDept> deptList = sysDeptService.queryList(new HashMap<String, Object>());
			Long parentId = null;
			for(SysDept sysDeptEntity : deptList){
				if(parentId == null){
					parentId = sysDeptEntity.getParentId();
					continue;
				}

				if(parentId > sysDeptEntity.getParentId().longValue()){
					parentId = sysDeptEntity.getParentId();
				}
			}
			deptId = parentId;
		}

		return R.ok().put("deptId", deptId);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{deptId}")
	@ResponseBody
	public R info(@PathVariable("deptId") Long deptId){
		SysDept dept = sysDeptService.selectById(deptId);
		return R.ok().put("dept", dept);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public R save(@RequestBody SysDept dept){
		sysDeptService.insert(dept);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public R update(@RequestBody SysDept dept){
		sysDeptService.updateById(dept);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public R delete(long deptId){
		//判断是否有子部门
		List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
		if(deptList.size() > 0){
			return R.error("请先删除子部门");
		}
		sysDeptService.deleteById(deptId);
		return R.ok();
	}
	
}
