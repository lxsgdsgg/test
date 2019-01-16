package com.bashiju.base.mapper;

import java.util.List;

import com.bashiju.base.pojo.SysAdminEmailList;

public interface AdminMapper {
		public List<SysAdminEmailList> queryAdminInfo(String headModuleId);
}
