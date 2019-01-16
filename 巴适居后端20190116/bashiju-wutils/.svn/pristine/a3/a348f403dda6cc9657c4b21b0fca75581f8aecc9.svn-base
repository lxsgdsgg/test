package com.bashiju.wutils.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FtpUtil {
	
	/**
	 * FTP服务器地址
	 * */
    private static String HOSTNAME= "116.55.242.11";
    /**
     * FTP服务器端口号默认为21
     */
    private static Integer PORT= 2001 ;
    /**
     * FTP登录账号
     */
    private static String USERNAME= "bsjftp";
    /**
     * FTP登录密码
     */
    private static String PASSWORD= "Bsjfile@2018@2019";
	/**
	 * FTP上传成功后的返回路径
	 */
    private final static String FILEURL="https://s.bashiju.com/d/";
	
    	private static final Logger logger=LoggerFactory.getLogger(FtpUtil.class);
    
    	private static ThreadLocal<FTPClient> ftpClientThreadLocal = new ThreadLocal<FTPClient>(); //线程局部变量
        /**
         * 初始化ftp服务器
         */
    	private static boolean initFtpClient() {
        	if(ftpClientThreadLocal.get()!=null&&ftpClientThreadLocal.get().isConnected()) {
        		return true;
        	}
            FTPClient ftpClient =new FTPClient();
            boolean flag=false;
            ftpClient.setControlEncoding("utf-8");
            try {
                ftpClient.connect(HOSTNAME, PORT); //连接FTP服务器              
                ftpClient.login(USERNAME, PASSWORD); //登录FTP服务器
                int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
                if(!FTPReply.isPositiveCompletion(replyCode)){
                	ftpClient.disconnect();
                	logger.warn("FTP服务器连接失败，错误代码{}。", replyCode); 
                	return flag;
                }
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
                ftpClientThreadLocal.set(ftpClient);
                flag=true;
            }catch (MalformedURLException e) { 
            	logger.warn("FTP服务器连接失败，错误原因{}。", e.getMessage()); 
            }catch (IOException e) {
            	logger.warn("FTP服务器连接失败，错误代码{}。",  e.getMessage()); 
            }            
            return flag;
        }

/*    	static ExecutorService pool =new ThreadPoolExecutor(2, 4, 2L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
    			new ThreadPoolExecutor.DiscardOldestPolicy());
    	
        public static  String uploadFiles(MultipartFile file,String sourceId,String sourceType,String fileType,
        		boolean isShuiYin){
        	long start=System.currentTimeMillis();
        	Future<String> res=pool.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					System.out.println("线程呢"+Thread.currentThread().getName());
					return uploadFiles( file, sourceId, sourceType, fileType, isShuiYin);
				}
			});
        	try {
        		String dd=res.get();
        		 System.out.println("多线程执行时间--"+(System.currentTimeMillis()-start));
				return dd;
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
        }*/
        /**
         * 上传文件
         * @param file 输入文件流 
         * @param sourceId 资源ID
         * @param sourceType 资源类型（小区、房源等）具体参考枚举类【FtpSourceTypeEnum】
         * @param fileType 文件类型 具体参考枚举类【FtpFileTypeEnum】
         * @param isShuiYin 是否添加水印
         * @return 成功-返回路径；失败返回null
         */
        public static  String uploadFile(MultipartFile file,String sourceId,String sourceType,String fileType,boolean isShuiYin){
        	long start=System.currentTimeMillis();
        	String prefix =FtpOtherUtil.validateFile(file,fileType);//验证图片格式和尺寸
            String pathname=sourceType+"/"+sourceId+"-"+fileType;
            String fileName=new Date().getTime()+"."+prefix;
				InputStream inputStream = null;
	        try {
	        	
				System.out.println("开始上传文件");
				
					if (!initFtpClient()) {
						logger.warn("FTP服务器第一次连接失败，再连一次。");
						if (!initFtpClient()) {
							logger.warn("上传文件时FTP服务器第二次连接失败，不再连接。"); 
							return null;
						}
						;//如果连接失败，再连接一次,不行的话返回null。
					}

				CreateDirecroty(pathname);
				inputStream = file.getInputStream();
				if (isShuiYin) {
					inputStream = FtpOtherUtil.addShuiYin(inputStream);
				}
				//上传文件
				if (!ftpClientThreadLocal.get().storeFile(fileName, inputStream)) {
					//上传失败
					logger.warn("上传文件失败,其原因是{}",ftpClientThreadLocal.get().getReplyString());
					return null;
				}
				inputStream.close();
				return FILEURL + pathname + "/" + fileName;
			} catch (Exception e) {
				logger.warn("上传文件失败,程序有异常,其原因是{}",e);				
               return null;
            }finally{
               if(ftpClientThreadLocal.get()!=null&&ftpClientThreadLocal.get().isConnected()){ 
                    try{
                    	ftpClientThreadLocal.get().disconnect();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                } 
                if(null != inputStream){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } 
                   System.out.println("单线程执行时间--"+(System.currentTimeMillis()-start));
                } 
            }
          
        }
        
        //改变目录路径
         private static boolean changeWorkingDirectory(String directory) {
                boolean flag = true;
                try {
                	String[] dirs=directory.split("/");
                	for(String dir:dirs) {
                		 flag = ftpClientThreadLocal.get().changeWorkingDirectory(dir);
                         if (flag) {
                           System.out.println("进入文件夹" + dir + " 成功！");

                         } else {
                             System.out.println("进入文件夹" + dir + " 失败！开始创建文件夹");
                         }
                	}
                   
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                return flag;
            }

        //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
         private static boolean CreateDirecroty(String remote) throws IOException {
            boolean success = true;
            String directory = remote + "/";
            // 如果远程目录不存在，则递归创建远程服务器目录
            if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
                int start = 0;
                int end = 0;
                if (directory.startsWith("/")) {
                    start = 1;
                } else {
                    start = 0;
                }
                end = directory.indexOf("/", start);
                String path = "";
                String paths = "";
                while (true) {
                    String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                    path = path + "/" + subDirectory;
                    if (!existFile(path)) {
                        if (makeDirectory(subDirectory)) {
                            changeWorkingDirectory(subDirectory);
                        } else {
                            System.out.println("创建目录[" + subDirectory + "]失败");
                            changeWorkingDirectory(subDirectory);
                        }
                    } else {
                        changeWorkingDirectory(subDirectory);
                    }

                    paths = paths + "/" + subDirectory;
                    start = end + 1;
                    end = directory.indexOf("/", start);
                    // 检查所有目录是否创建完毕
                    if (end <= start) {
                        break;
                    }
                }
            }
            return success;
        }

      //判断ftp服务器文件是否存在    
         private static boolean existFile(String path) throws IOException {
                boolean flag = false;
                FTPFile[] ftpFileArr = ftpClientThreadLocal.get().listFiles(path);
                if (ftpFileArr.length > 0) {
                    flag = true;
                }
                return flag;
            }
        //创建目录
         private static boolean makeDirectory(String dir) {
            boolean flag = true;
            try {
                flag = ftpClientThreadLocal.get().makeDirectory(dir);
                if (flag) {
                    System.out.println("创建文件夹" + dir + " 成功！");

                } else {
                    System.out.println("创建文件夹" + dir + " 失败！");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return flag;
        }
        
        /** * 下载文件 * 
        * @param pathname FTP服务器文件目录 * 
        * @param filename 文件名称 * 
        * @param localpath 下载后的文件路径 * 
        * @return */
         private  boolean downloadFile(String pathname, String filename, String localpath){ 
            boolean flag = false; 
            OutputStream os=null;
            try { 
                System.out.println("开始下载文件");
                initFtpClient();
                //切换FTP目录 
                ftpClientThreadLocal.get().changeWorkingDirectory(pathname); 
                FTPFile[] ftpFiles = ftpClientThreadLocal.get().listFiles(); 
                for(FTPFile file : ftpFiles){ 
                    if(filename.equalsIgnoreCase(file.getName())){ 
                        File localFile = new File(localpath + "/" + file.getName()); 
                        os = new FileOutputStream(localFile); 
                        ftpClientThreadLocal.get().retrieveFile(file.getName(), os); 
                        os.close(); 
                    } 
                } 
                ftpClientThreadLocal.get().logout(); 
                flag = true; 
                System.out.println("下载文件成功");
            } catch (Exception e) { 
                System.out.println("下载文件失败");
                e.printStackTrace(); 
            } finally{ 
                if(ftpClientThreadLocal.get().isConnected()){ 
                    try{
                        ftpClientThreadLocal.get().disconnect();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                } 
                if(null != os){
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } 
                } 
            } 
            return flag; 
        }
        
        /**
         * 删除文件
  
         * @param sourceId 资源ID
         * @param sourceType 资源类型（小区、房源等）具体参考枚举类【FtpSourceTypeEnum】
         * @param fileType 文件类型 具体参考枚举类【FtpFileTypeEnum】
         * @param filename 文件名（包含后缀,如：x.png）
         * @return boolean 成功-true; 失败-false
         * @throws Exception 
         */ 
        public static boolean deleteFile(String sourceId,String sourceType,String fileType, String filename){ 
        	String pathname=sourceType+"/"+sourceId+"-"+fileType;
            boolean flag = false; 
            try { 
                if(!initFtpClient()) {
                	logger.warn("删除文件时FTP服务器第一次连接失败，再连一次。"); 
                	if(!initFtpClient()) {
                		logger.warn("删除文件时FTP服务器第二次连接失败，不再连接。"); 
                		return flag;
                	};//如果连接失败，再连接一次,不行的话返回false。
                }      
                //切换FTP目录 
                changeWorkingDirectory(pathname); 
                int replyCode=ftpClientThreadLocal.get().dele(filename); 
                if(!FTPReply.isPositiveCompletion(replyCode)){
                	logger.warn("删除文件失败,其原因是{}",ftpClientThreadLocal.get().getReplyString());                   
    				ftpClientThreadLocal.get().logout();
                	return flag;
                }
				ftpClientThreadLocal.get().logout();
                flag = true; 
            } catch (Exception e) { 
            	logger.warn("删除文件时程序有异常，原因为：{}",e); 
                e.printStackTrace(); 
            } finally {
                if(ftpClientThreadLocal.get().isConnected()){ 
                    try{
                        ftpClientThreadLocal.get().disconnect();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                } 
            }
            return flag; 
        }
        
}