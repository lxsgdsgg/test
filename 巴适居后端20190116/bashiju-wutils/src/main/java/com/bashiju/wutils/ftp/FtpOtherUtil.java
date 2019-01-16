package com.bashiju.wutils.ftp;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 文件上传下载工具类
 * @ClassName  FtpUtil   
 * @Description 
 * @author zhaobin
 * @date   2018年5月29日 上午11:56:06   
 *     
 * @Copyright 2018 www.bashiju.com Inc. All rights reserved. 
 * 本内容仅限于云南巴士居网络服务公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class FtpOtherUtil {

	/**
	 * 是否添加水印
	 * @param input
	 * @return
	 * @throws Exception
	 */
	  static InputStream addShuiYin(InputStream input) throws Exception {

		Image srcImg = ImageIO.read(input);
		/*int width=srcImg.getWidth(null);
		if(width==-1) {
			width=300;
		}
		int height=srcImg.getHeight(null);
		if(height==-1) {
			height=200;
		}
		BufferedImage buffImg = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
		// 2、得到画笔对象
        Graphics2D g = buffImg.createGraphics();
        // 3、设置对线段的锯齿状边缘处理
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(srcImg, 0, 0, null);
     // 5、设置水印文字颜色
        g.setColor(Color.red);
        // 6、设置水印文字Font
        g.setFont(new java.awt.Font("宋体", java.awt.Font.BOLD, 60));
        // 7、设置水印文字透明度
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.55f));
        // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
        g.drawString("巴适居",  buffImg.getWidth()/2 , buffImg.getHeight()/2);*/
		int width = srcImg.getWidth(null);
        int heigth = srcImg.getHeight(null);
        BufferedImage bufferImage = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bufferImage.createGraphics();
        g.drawImage(srcImg, 0, 0, width, heigth, null);
        URL logoPath = new URL("https://s.bashiju.com/i/public/shuiyin.png");
        Image logo = ImageIO.read(logoPath);
        int widthReal = logo.getWidth(null);
        int heigthReal = logo.getHeight(null);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.1f));//0.1f 表示水印透明度
        // 因为水印已经倾斜，所以不做画布倾斜
         g.rotate(Math.toRadians(-30), bufferImage.getWidth() / 2,//-30 表示水印旋转角度
         bufferImage.getHeight() / 2);

        int x = -width / 2;
        int y = -heigth / 2;

        while (x < width * 1.5) {
            y = -heigth / 2;

            while (y < heigth * 1.5) {
                g.drawImage(logo, x, y, null);

                y += heigthReal + 50;//50 表示列间距，值越大密度越小
            }

            x += widthReal + 50;//50 表示行间距，值越大密度越小
        }

        //输出图片
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferImage, "jpg", out);
        ByteArrayInputStream inputStream=new ByteArrayInputStream(out.toByteArray());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Thumbnails.of(inputStream).scale(1f).outputQuality(0.25f).toOutputStream(baos);
        InputStream file=new ByteArrayInputStream(baos.toByteArray());
        return file;
	}
	  
	  /**
	 * @throws Exception 
	 * @throws  
	 * @throws Exception 
	   * 
	   */
		 static String validateFile(MultipartFile file,String type) {
	        String loacalFileName=file.getOriginalFilename();
	        String[]  name=splitFileName(loacalFileName);
	        String prefix=name[1];
	        int size=0;
	        boolean flag=false;
	        String types=null;
	    		Resource resource = new ClassPathResource("properties/resource.properties");
		    	Properties props = null;
				try {
					props = PropertiesLoaderUtils.loadProperties(resource);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        switch (type) {
		  			case "pic":
				    	types =(String)props.get("imageTypes");
				    	size=Integer.parseInt(props.get("imagesize").toString());
		  				break;
		  			case "video":
				    	types =(String)props.get("videoTypes");
				    	size=Integer.parseInt(props.get("videosize").toString());
		  				break;
		  			default:
				    	types =(String)props.get("fileTypes");
				    	size=Integer.parseInt(props.get("filesize").toString());
		  				break;
		        } 
		        if(file.getSize()>size*1048576) {
		        	throw new RuntimeException("附件尺寸超出限制:"+size+"MB");
		        }
		    	String[] typesStr = types.split("\\|");
		    	for(String typeStr :typesStr){
		    		if(typeStr.equals(prefix)){
		    			flag=true;
		    			break;
		    		}
		    	}
		    	if(!flag) {
		    		throw new RuntimeException("文件只能上传格式:"+types);
		    	}
	    	 return prefix;	    	
		}
	  

		/**
		 * 取得文件名及文件后缀名 [0]为文件名  [1]为文件后缀
		 * @param fileName
		 * @return
		 */
		public static String[] splitFileName(String fileName){
			String fileAndExt[] ={fileName.substring(0,fileName.lastIndexOf(".")),
					fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase()};
			return fileAndExt;
		}
  
}