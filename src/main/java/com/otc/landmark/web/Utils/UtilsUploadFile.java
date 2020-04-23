package com.otc.landmark.web.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.web.multipart.MultipartFile;

import com.otc.landmark.web.constant.CommonConst;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
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

	public static String splitPdf(HttpServletRequest req, int numPage, String fileName, String pathFullFile, String folderPath) {
		
		String fileExtention = fileName.substring(fileName.indexOf("."), fileName.length());
		String dbFileName = UUID.randomUUID().toString() + fileExtention;
		pathFullFile = pathFullFile.replace(folderPath + "/", "");   
		String uploadRootPath = req.getServletContext().getRealPath(folderPath);
		try {
			PDDocument document = PDDocument.load(new File(uploadRootPath + File.separator + pathFullFile));
		    try {
	            Splitter splitter = new Splitter();
	            splitter.setStartPage(1);
	            splitter.setEndPage(numPage);
	            splitter.setSplitAtPage(numPage); 
	            List<PDDocument> splittedList = splitter.split(document);
	           if(splittedList.get(0) != null) {
	        	   PDDocument doc = splittedList.get(0);
	               doc.save(uploadRootPath +"\\split_"+ dbFileName); 
	               doc.close();                    
	            }     
	            System.out.println("Save successful file : " + fileName);
	            return folderPath +"/split_"+ dbFileName;
	        } catch (Exception e) {
	        	System.out.println("Fail to Save Split file pdf");
				e.printStackTrace();
	        }
		    document.close();
		} catch (IOException e1) {
			System.out.println("Fail to load PDDocument");
			e1.printStackTrace();
		}
		return null;
	}
	
	public static String splitImgFromPdf(HttpServletRequest req, String fileName, String pathFullFile, String folderPath) {
		
		String dbImgName = UUID.randomUUID().toString();
		pathFullFile = pathFullFile.replace(folderPath + "/", "");   
		String uploadRootPath = req.getServletContext().getRealPath(folderPath);
		try {
			PDDocument document = PDDocument.load(new File(uploadRootPath + File.separator + pathFullFile));
			PDFRenderer pdfRenderer = new PDFRenderer(document);
		    try {
		    	BufferedImage bImage = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
                ImageIO.write(bImage, "png", new File(uploadRootPath +"\\split_"+ dbImgName+".png"));
                System.out.println("Save successful file : " + "\\split_"+ dbImgName+".png");
                return folderPath +"/split_"+ dbImgName+".png";   
	        } catch (Exception e) {
	        	System.out.println("Fail to Split preview file pdf");
				e.printStackTrace();
	        }
		    document.close();
		} catch (IOException e1) {
			System.out.println("Fail to load PDDocument");
			e1.printStackTrace();
		}
		return null;
		
	}
	
	public static boolean deleteFileUploadDocument(HttpServletRequest req, String namefile) {
		try {
			String uploadRootPath = req.getServletContext().getRealPath(CommonConst.UPLOAD_DOCS_FILE);
			namefile = namefile.replace(CommonConst.UPLOAD_DOCS_FILE + "/", "");   
			File serverFile = new File(uploadRootPath + File.separator + namefile);
			if (serverFile.exists()) {
				if (serverFile.delete()) {
					System.out.println("Delete file sucesss!!!");
					return true;
				} else {
					System.out.println("Fail to delete file: " + uploadRootPath + File.separator + namefile);
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println("Fail to delete file");
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
