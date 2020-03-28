package com.otc.landmark.web.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.otc.landmark.web.constant.CommonConst;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

public class UtilsUploadFile {
	private static final Log logger = LogFactory.getLog(UtilsUploadFile.class);

	public static String uploadFile(HttpServletRequest req, MultipartFile multipartFile, String folderPath)
			throws Exception {
		BufferedOutputStream bfout = null;
		String dbFileName = "";
		try {
			// Convert relative path in war folder structure to an absolute file system path
			String uploadRootPath = req.getServletContext().getRealPath(folderPath);
			File uploadRootDir = new File(uploadRootPath);
			if (!uploadRootDir.exists()) {
				boolean test = uploadRootDir.mkdir();
				System.out.println(test);
			}
			String realFileName = multipartFile.getOriginalFilename();
			String fileExtention = realFileName.substring(realFileName.indexOf("."), realFileName.length());
			dbFileName = UUID.randomUUID().toString() + fileExtention;

			File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + dbFileName);
			if (!serverFile.exists()) {
				serverFile.createNewFile();
			}
			
			bfout = new BufferedOutputStream(new FileOutputStream(serverFile));
			bfout.write(multipartFile.getBytes());
		} catch (FileNotFoundException e) {
			logger.error("Not found upload file");
			throw new Exception("Not found upload file");
		} catch (IOException e) {
			logger.error("Error when wrile upload file");
			throw new Exception("Error when wrile upload file");
		} finally {
			if (bfout != null) {
				bfout.close();
			}
		}

		return folderPath + "/" + dbFileName;
	}

	public static boolean deleteFileUploadCkeditor(HttpServletRequest req, String namefile) {
		try {
			String uploadRootPath = req.getServletContext().getRealPath(CommonConst.UPLOAD_FILE_CKEDITOR);
			File serverFile = new File(uploadRootPath + File.separator + namefile);
			if (serverFile.exists()) {
				if (serverFile.delete()) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println("Fail to delete img");
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
