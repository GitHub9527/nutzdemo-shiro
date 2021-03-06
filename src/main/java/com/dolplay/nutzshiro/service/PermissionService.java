package com.dolplay.nutzshiro.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.service.IdEntityService;

import com.dolplay.nutzshiro.annotation.Authorization;
import com.dolplay.nutzshiro.domain.Permission;
import com.dolplay.nutzshiro.domain.Role;

@IocBean(fields = { "dao" })
public class PermissionService extends IdEntityService<Permission> {
	public PermissionService() {
		super();
	}

	public PermissionService(Dao dao) {
		super(dao);
	}

	public List<Permission> list() {
		return query(null, null);
	}
	
	public Map<Long,String> map(){
		Map<Long, String> map = new HashMap<Long, String>();
		List<Permission> permissions = query(null, null);
		for(Permission permission:permissions){
			map.put(permission.getId(), permission.getName());
		}
		return map;
	}

	public void insert(Permission permission){
		dao().insert(permission);
	}
	
	public Permission view(Long id){
		return fetch(id);
	}
	
	public void update(Permission permission){
		dao().update(permission);
	}
}
