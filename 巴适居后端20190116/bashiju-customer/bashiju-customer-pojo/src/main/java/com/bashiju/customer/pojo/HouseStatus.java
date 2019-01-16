package com.bashiju.customer.pojo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class HouseStatus {
	/**全部:1**/
	private String isAll;
	/**有效：1、公盘，2、私盘,3、有效**/
	private Integer effective;
	/**成交：1、我售，2、我租，3、成交**/
	private Integer deal;
	/**失效：1、暂缓，2、未知，3、他售，4、他租，5、撤单，6、已驳回，7、自住，8、空置，9、出租中**/
	private List<Integer> invalid;
	
	
	
	public String getIsAll() {
		return isAll;
	}



	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}



	public Integer getEffective() {
		return effective;
	}



	public void setEffective(Integer effective) {
		this.effective = effective;
	}



	public Integer getDeal() {
		return deal;
	}



	public void setDeal(Integer deal) {
		this.deal = deal;
	}



	public List<Integer> getInvalid() {
		return invalid;
	}



	public void setInvalid(List<Integer> invalid) {
		this.invalid = invalid;
	}


	/**
	 * 生成房源状态sql语句
	 * @Title: toSQl   
	 * @Description: TODO(生成房源状态sql语句)   
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String toSql() {
		StringBuilder conditionSql=new StringBuilder();
		if (StringUtils.isNotEmpty(isAll)) {
			return "";
		}
		if (effective!=null&&effective>0) {
			conditionSql.append(" sh.statusId='yx' ");
			if (effective==1) {
				conditionSql.append(" and  sh.discStatusId =1 ");
			}
			if (effective==2) {
				conditionSql.append(" and  sh.discStatusId =2 ");
			}
		}else if (deal!=null&&deal>0) {
			if (effective==1) {
				conditionSql.append(" sh.statusId ='ws' ");
			}
			if (effective==2) {
				conditionSql.append(" sh.statusId ='wz' ");
			}
			if (effective==3) {
				conditionSql.append(" sh.statusId in ('ws','wz') ");
			}
		} else if (invalid!=null&&invalid.size()>0) {
			conditionSql.append(" sh.statusId in (");
			for (int i = 0; i < invalid.size(); i++) {
				switch (invalid.get(i)) {
				case 1:
					conditionSql.append("'zh'");
					break;

				case 2:
					conditionSql.append("'wz'");
					break;
				case 3:
					conditionSql.append("'ts'");
					break;
				case 4:
					conditionSql.append("'tz'");
					break;
				case 5:
					conditionSql.append("'cd'");
					break;
				case 6:
					conditionSql.append("'ybh'");
					break;
				case 7:
					conditionSql.append("'zz'");
					break;
				case 8:
					conditionSql.append("'kz'");
					break;
				case 9:
					conditionSql.append("'czz'");
					break;
				}
				
				if (invalid.size()-i>1) {
					conditionSql.append(",");
				}
			}
			conditionSql.append(")");
			
		}
		return conditionSql.toString();
	}

}
