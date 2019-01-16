package com.bashiju.www.pojo.service.out.comm;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *  各图片输出对象
 * @ClassName:  ImgResult   
 * @Description:   各图片输出对象
 * @author: liwen
 * @date:   2018年10月17日 下午17:00:01       
 * @Copyright: 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class ImgResult implements Serializable {
	private static final long serialVersionUID = -5328118803327814261L;
	/**区分图片编号**/
	private int code ;
	/**区分图片描述**/
	private String codeDesc ;
	/**图片使用类型**/
	private String type;
	/**主标题**/
	private String mainTitle;
	/**副标题**/
	private String smallTitle;
	/**图片路径**/
	private String path;
	/**条件**/
	private String conditionData;
	
	
	
}
