package com.bashiju.manage.utils;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * Description: Excel工具
 * @author yz
 * @Date 2017年8月3日
 */
public class ExcelUtil {

	/**
	 * Excel导入
	 * @param file 文件流
	 * @return 返回map数组
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static List<Map<String,Object>> importData(MultipartFile file)throws Exception{
		Workbook wb = null;
		if("application/vnd.ms-excel".equals(file.getContentType())){//1997-2003的模板
			wb = new HSSFWorkbook(file.getInputStream());
		}else if("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(file.getContentType()) 
				||"application/octet-stream".equals(file.getContentType())){//2003以后的模板
			wb = new XSSFWorkbook(file.getInputStream());
		}else{
			throw new RuntimeException("文件格式错误");
		}
		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		Row header = null;
		if(wb.getSheetAt(0)!=null){
			header = wb.getSheetAt(0).getRow(0);
		}
		if(header==null){
			return list;
		}
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sheet = wb.getSheetAt(i);
			if(!header.equals(sheet.getRow(0))){
				continue;
			}else{
				System.out.println(sheet.getLastRowNum());
				System.out.println(sheet.getPhysicalNumberOfRows());
				for(int j=2;j<sheet.getPhysicalNumberOfRows();j++){
					Map<String,Object> map =null;
					Row row = sheet.getRow(j);
					if(row==null || isRowEmpty(row)){
						continue;
					}
					for(int h=0;h<header.getPhysicalNumberOfCells();h++){
						if(map==null){
							map = new HashMap<String,Object>();
						}
						if(row.getCell(h)!=null && !"".equals(row.getCell(h))){
							map.put(header.getCell(h).toString().trim(), row.getCell(h).toString().trim());
						}else{
							map.put(header.getCell(h).toString().trim(), "");
						}
					}
					if(map!=null){
						list.add(map);
					}
				}
			}
		}
		return list;
	}
	

	/**
	 * Excel导入
	 * @param file 文件流
	 * @param classs 需要导出的类
	 * @return 返回对象数组
	 * @throws Exception
	 */
	public static List<?> importData2(MultipartFile file,Class<?> classs)throws Exception{
		return mapList2ObjectList(importData(file), classs.getName());
	}
	
	/**
	 * Excel导出模板(2003版以前xls文件)
	 * @param dropDowns 下拉数据集合
	 * @param fileName 文件名
	 * @param title 表头，使用map<属性名,表头>的形式，如：id:主键
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static void exportModelXLS(Map<String,List<String>> dropDowns,Map<String,String> title,String fileName,HttpServletResponse response)throws Exception{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet st = wb.createSheet(fileName);
		
		//设置表头
		Row row = st.createRow(0);
		Row row2 = st.createRow(1);
		int ss = 0;
		for (Map.Entry<String, String> entry : title.entrySet()) {
			row.createCell(ss).setCellValue(entry.getKey());
			row2.createCell(ss).setCellValue(entry.getValue());
			if(dropDowns.containsKey(entry.getKey()) && dropDowns.get(entry.getKey())!=null &&  dropDowns.get(entry.getKey()).size()>0) {
				String[] value = dropDowns.get(entry.getKey()).toArray(new String[dropDowns.get(entry.getKey()).size()]);
				st = createExplicitListXLS(st, 2, 65535, ss, ss, value);
			}
			st.autoSizeColumn(ss);
			ss +=1;
		}
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + java.net.URLEncoder.encode(fileName, "UTF-8") + ".xls" + "\" ");
		OutputStream out = response.getOutputStream();  
		wb.write(out);
		out.close();  
	}
	
	/**
	 * Excel导出模板(2003版以后xlsx文件)
	 * @param dropDowns 下拉数据集合
	 * @param title 表头，使用map<属性名,表头>的形式，如：id:主键
	 * @param fileName 文件名
	 * @param datas 数据集，使导出的模板文件里包含数据，可以为空
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static void exportModelXLSX(Map<String,List<String>> dropDowns,Map<String,String> title
			,String fileName,List<Map<String,String>> datas,HttpServletResponse response)throws Exception{
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet st = wb.createSheet(fileName);
		
		//设置表头
		Row row = st.createRow(0);
		Row row2 = st.createRow(1);
		int ss = 0;
		for (Map.Entry<String, String> entry : title.entrySet()) {
			row.createCell(ss).setCellValue(entry.getKey());
			row2.createCell(ss).setCellValue(entry.getValue());
			if(dropDowns!=null && dropDowns.size()>0 && dropDowns.containsKey(entry.getKey()) && dropDowns.get(entry.getKey())!=null &&  dropDowns.get(entry.getKey()).size()>0) {
				String[] value = dropDowns.get(entry.getKey()).toArray(new String[dropDowns.get(entry.getKey()).size()]);
				st = createExplicitListXLSX(st, 2, 65535, ss, ss, value);
			}
			st.autoSizeColumn(ss);
			ss +=1;
		}
		if(datas!=null && datas.size()>0) {
				int row_index = 1;
			for (int i = 0; i < datas.size(); i++) {
				row = st.createRow(row_index+i+1);
				Object obj = datas.get(i);
				int sd = 0;
				for (Map.Entry<String, String> entry : title.entrySet()) {
//					if(i==0) {
//						row.createCell(sd).setCellValue(entry.getKey());
//						row2.createCell(sd).setCellValue(entry.getValue());
//						if(dropDowns!=null && dropDowns.size()>0 && dropDowns.containsKey(entry.getKey()) && dropDowns.get(entry.getKey())!=null &&  dropDowns.get(entry.getKey()).size()>0) {
//							String[] value = dropDowns.get(entry.getKey()).toArray(new String[dropDowns.get(entry.getKey()).size()]);
//							st = createExplicitListXLSX(st, 2, 65535, sd, sd, value);
//						}
//					}
					Object value = getValue(obj, entry.getKey());
					if(value!=null && !"".equals(value) && !"null".equals(value)){
						if(String.class.getName().equals(value.getClass().getName())){
							row.createCell(sd).setCellValue(value.toString());
						}else if(Date.class.getName().equals(value.getClass().getName())){
							Date d = (Date)value;
							DateFormat df3 = DateFormat.getTimeInstance();
							if("0:00:00".equals(df3.format(d))){
								row.createCell(sd).setCellValue(dateFormat("yyyy-MM-dd").format(value));
							}else{
								row.createCell(sd).setCellValue(dateFormat("yyyy-MM-dd HH:mm:ss").format(value));
							}
						}else{
							row.createCell(sd).setCellValue(value.toString());
						}
					}
					st.autoSizeColumn(sd);
					sd +=1;
				}
			}
		}
//		else if(dropDowns!=null && dropDowns.size()>0) {
//			for (Map.Entry<String, String> entry : title.entrySet()) {
//				row.createCell(ss).setCellValue(entry.getKey());
//				row2.createCell(ss).setCellValue(entry.getValue());
//				if(dropDowns.containsKey(entry.getKey()) && dropDowns.get(entry.getKey())!=null &&  dropDowns.get(entry.getKey()).size()>0) {
//					String[] value = dropDowns.get(entry.getKey()).toArray(new String[dropDowns.get(entry.getKey()).size()]);
//					st = createExplicitListXLSX(st, 2, 65535, ss, ss, value);
//				}
//				st.autoSizeColumn(ss);
//				ss +=1;
//			}
//		}
		
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + java.net.URLEncoder.encode(fileName, "UTF-8") + ".xlsx" + "\" ");
		OutputStream out = response.getOutputStream();  
		wb.write(out);
		out.close();  
	}
	
	/**
	 * Excel导出
	 * @param list 导出数据集合
	 * @param fileName 文件名
	 * @param title 表头，使用map<属性名,表头>的形式，如：id:主键
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static void exportData(List<?> list,String fileName,Map<String,String> title,HttpServletResponse response)throws Exception{
		XSSFWorkbook wb = new XSSFWorkbook();
		Sheet st = wb.createSheet(fileName);
		
		//设置表头
		Row row = st.createRow(0);
		int ss = 0;
		for (Map.Entry<String, String> entry : title.entrySet()) {
			row.createCell(ss).setCellValue(entry.getValue());
			st.autoSizeColumn(ss);
			ss +=1;
		}
		for (int i = 0; i < list.size(); i++) {
			row = st.createRow(i+1);
			Object obj = list.get(i);
			int sd = 0;
			for (Map.Entry<String, String> entry : title.entrySet()) {
				Object value = getValue(obj, entry.getKey());
				if(value!=null && !"".equals(value) && !"null".equals(value)){
					if(String.class.getName().equals(value.getClass().getName())){
						row.createCell(sd).setCellValue(value.toString());
					}else if(Date.class.getName().equals(value.getClass().getName())){
						Date d = (Date)value;
						DateFormat df3 = DateFormat.getTimeInstance();
						if("0:00:00".equals(df3.format(d))){
							row.createCell(sd).setCellValue(dateFormat("yyyy-MM-dd").format(value));
						}else{
							row.createCell(sd).setCellValue(dateFormat("yyyy-MM-dd HH:mm:ss").format(value));
						}
					}else{
						row.createCell(sd).setCellValue(value.toString());
					}
				}
				st.autoSizeColumn(sd);
				sd +=1;
			}
		}
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + java.net.URLEncoder.encode(fileName, "UTF-8") + ".xlsx" + "\" ");
		OutputStream out = response.getOutputStream();  
		wb.write(out);
		out.close();  
	}
	

	/**
	 * 将map数组转为对象数组
	 * @param list map数组
	 * @param className 需要转的类全路径，例如：com.asdkv3.xsd.beanpcacries001.Document
	 * @return
	 * @throws Exception
	 */
	private static List<?> mapList2ObjectList(List<Map<String,Object>> list,String className)throws Exception{
		List<Object> pss = new LinkedList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Object ps = (Object) DataUtil.excute(list.get(i), className);
			pss.add(ps);
		}
		return pss;
	}
	
	/**
	 * 根据get方法获取值
	 * @param obj
	 * @param filedName
	 * @return
	 * @throws Exception
	 */
	private static Object getValue(Object obj,String filedName)throws Exception{
		String[] fdName = filedName.split("\\.");//主子复合类使用child.name这种方式获取
		if(fdName.length>1){
			Object chiledClass = getValue(obj, fdName[0]);
			return getValue(chiledClass, fdName[1]);
		}else{
			Class<?> clazz = obj.getClass();
			try {
				Method l1 = null;
				Object parameterType = null;
				if("java.util.HashMap".equals(clazz.getTypeName())) {//map
					l1 = clazz.getMethod("get", Object.class);
					parameterType = filedName;
				}else {//其他默认存在get方法
					l1 = clazz.getMethod(getFieldGetName(filedName), null);
				}
				if(l1!=null){
					return l1.invoke(obj, parameterType);
				}else{
					return null;
				}
			} catch (NoSuchMethodException e) {
				throw new RuntimeException("没有找到get方法"+filedName);
			}
		}
	}
		
   /**
	 * 根据属性名称获取get方法名称
	 * @param s
	 * @return
	 */
   private static String getFieldGetName(String s){
       if(Character.isUpperCase(s.charAt(0)))
           return "get"+s;
       else
           return "get"+(new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
   }
   
   /**
	 * 获取时间格式化格式
	 * @param pattern
	 * @return
	 */
   private static SimpleDateFormat dateFormat(String pattern){
		SimpleDateFormat format = null;
		if(format==null){
			format = new SimpleDateFormat();
		}
		format.applyPattern(pattern);
		return format;
	}
   
   
   public static boolean isRowEmpty(Row row) {
	    for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
	        Cell cell = row.getCell(c);
	        if (cell != null && cell.getCellType() != CellType.BLANK)
	            return false;
	    }
	    return true;
	}
   
   /**
	 * 添加下拉菜单信息（2003版以前XLS文件）
	 * @Description: 添加下拉菜单信息   
	 * @param sheet 待添加的工作表
	 * @param firstRow 开始添加行
	 * @param lastRow 截止添加行
	 * @param firstCol 开始添加单元格
	 * @param lastCol 截止添加单元格
	 * @param value 添加的菜单列表
	 * @return: HSSFSheet
	 */
	private static HSSFSheet createExplicitListXLS(HSSFSheet sheet,int firstRow, int lastRow, int firstCol, int lastCol,String...value) {
		//生成下拉列表
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);
		//生成下拉框内容
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(value);
		
		//绑定下拉框和作用区域
		HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);
		//对sheet页生效
		sheet.addValidationData(data_validation);
		return sheet;
	}
	
	/**
	 * 添加下拉菜单信息（2003版以后XLSX文件）
	 * @Description: 添加下拉菜单信息   
	 * @param sheet 待添加的工作表
	 * @param firstRow 开始添加行
	 * @param lastRow 截止添加行
	 * @param firstCol 开始添加单元格
	 * @param lastCol 截止添加单元格
	 * @param value 添加的菜单列表
	 * @return: XSSFSheet
	 */
	private static XSSFSheet createExplicitListXLSX(XSSFSheet sheet,int firstRow, int lastRow, int firstCol, int lastCol,String...value) {
		//生成下拉列表
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);
		XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
		//生成下拉框内容
	    XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
	            .createExplicitListConstraint(value);
		//绑定下拉框和作用区域
		XSSFDataValidation data_validation = (XSSFDataValidation) dvHelper.createValidation(
                dvConstraint, regions);
		// 07默认setSuppressDropDownArrow(true);
        // validation.setSuppressDropDownArrow(true);
		//对sheet页生效
		sheet.addValidationData(data_validation);
		return sheet;
	}
   
   /**
    * @ClassName:  Title   
    * @Description:表头信息   
    * @author: yangz
    * @date:   2018年11月29日 下午6:52:21   
    */
   public class Title{
	   /**
	    * 属性
	    */
	   private String key;
	   
	   /**
	    * 描述
	    */
	   private String value;
	   
		public Title() {
			super();
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
   }
   
   /**
    * 
    * @ClassName:  DropDownInfo   
    * @Description:导出的下来选择信息   
    * @author: yangz
    * @date:   2018年11月30日 上午9:27:48   
    */
   public class DropDownInfo{
	   
	   /**
	    * 属性
	    */
	   private String key;
	   /**
	    * 要下拉的数据列
	    */
	   private List<String> datas;
	   
	   
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public List<String> getDatas() {
			return datas;
		}
		public void setDatas(List<String> datas) {
			this.datas = datas;
		}
	   
   }
   
}
