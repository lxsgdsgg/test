package com.bashiju.www.pojo.service.out.comm;

import java.io.Serializable;
import java.util.List;

import org.apache.yetus.audience.InterfaceAudience.Private;

import com.bashiju.www.pojo.comm.QueryCondition;

import lombok.Data;

/**
 * 
 *  首页主题对象
 * @ClassName:  ThemeResult   
 * @Description:  首页主题对象
 * @author: wangkaifa
 * @date:   2018年8月18日 上午11:13:16       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class ThemeResult implements Serializable {
	private static final long serialVersionUID = 4413783853134548511L;
	/**编号:1,2(大图)3,4,5**/
	private Integer code;
	/**标题**/
	private String title;
	/**简介**/
	private String summary;
	/**封面图片地址**/
	private String coverUrl;
	/**搜索条件对象**/
	private List<QueryCondition> conditionData;
}
