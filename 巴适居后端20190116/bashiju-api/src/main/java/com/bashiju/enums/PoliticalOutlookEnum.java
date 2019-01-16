/**  
 * All rights Reserved, Designed By www.bashiju.com
 * @Title:  PoliticalOutlookEnum.java   
 * @Package com.bashiju.enums      
 * @author: zuoyuntao     
 * @date:   2018年8月7日 下午3:52:51   
 * @version V1.0 
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 注意：本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目*/

package com.bashiju.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 政治面貌枚举类
 * @ClassName:PoliticalOutlookEnum
 * @Description:（详细描述）
 * @author:zuoyuntao
 * @date:2018年8月7日 下午3:52:51
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved.
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */

public enum PoliticalOutlookEnum {
	/**中共党员**/
	MemberCPOC("01","中共党员"),
	/**中共预备党员**/
	ProbationaryPartyMembers("02","中共预备党员"),
	/**共青团员**/
	CommunistYouthLeagueMember("03","共青团员"),
	/**民革党员**/
	PartyMembersOfTheCivilRevolution("04","民革党员"),
	/**民盟盟员**/
	LeagueMemberOfTheNLD("05","民盟盟员"),
	/**民建会员**/
	CivilConstructionMembers("06","民建会员"),
	/**民进会员**/
	MembersOfPRC("07","民进会员"),
	/**农工党党员**/
	MemberPeasantLabourParty("08","农工党党员"),
	/**致公党党员**/
	MembersOfTheZhiGongParty("09","致公党党员"),
	/**九三学社社员**/
	_93SocietyMembers("10","九三学社社员"),
	/**台盟盟员**/
	LeagueMemberOfTheLeagueOfTaiwan("11","台盟盟员"),
	/**无党派人士**/
	NonPartyPersonage("12","无党派人士"),//（无党派人士是指没有参加任何党派、对社会有积极贡献和一定影响的人士，其主体是知识分子。具体到我国，在新民主主义革命时期，一般称无党无派的知名人士为社会贤达。1949年中国人民政治协商会议成立后，专门设立了无党派民主人士界别。）
	/**群众**/
	TheMasses("13","群众");//（现称普通居民，与居民身份证相对应）
	
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 枚举map对象
	 */
	public static Map<String , String> enumMap;
	/**
	 * 枚举list对象
	 */
	public static List<Map<String , String>> enumList;
	/**
	 * @Title:  TransferProcessEnum   
	 * @Description: 构造器（外部塞入枚举值）  
	 * @param:  @param code
	 * @param:  @param desc  
	 * @throws
	 */
	PoliticalOutlookEnum(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	static {
        enumMap = new TreeMap<String, String>();
        
        enumList=new ArrayList<>();
        
        for (PoliticalOutlookEnum item : PoliticalOutlookEnum.values()) {
        	//将枚举值放入Map对象中
            enumMap.put(item.getCode(), item.getDesc());
            
            Map<String,String> map = new HashMap<String,String>();
            
            map.put("value", item.getCode());
            map.put("name", item.getDesc());
            //将枚举值放入List集合中
            enumList.add(map);
        }
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
