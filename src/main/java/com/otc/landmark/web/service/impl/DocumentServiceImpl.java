package com.otc.landmark.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otc.landmark.web.Utils.UtilsUploadFile;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.domain.Document;
import com.otc.landmark.web.dto.DocumentDto;
import com.otc.landmark.web.exception.ConstraintException;
import com.otc.landmark.web.repository.DocumentDao;
import com.otc.landmark.web.service.DocumentService;

@Service
@Transactional(rollbackFor = Exception.class)
public class DocumentServiceImpl implements DocumentService{

	@Autowired
    DocumentDao docDao;
	
	@Override
	public List<DocumentDto> findAll(int linkFile) {
		List<Document> documentList = docDao.findAll();
		List<DocumentDto> docList = new ArrayList<DocumentDto>();
		for (Document document : documentList) {
			DocumentDto docDto = new DocumentDto();
			docDto.setId(document.getId());
			docDto.setSubject(document.getSubject());
			docDto.setBody(document.getDescription());
			if(linkFile == 0) {
				docDto.setFilePath(document.getFirstPageImg());
			}else if(linkFile == 1){
				docDto.setFilePath(document.getFullFile());
			}else {
				docDto.setFilePath(document.getPreviewFile());
			}
			docList.add(docDto);
		}
		return docList;
	}

	@Override
	public DocumentDto findById(Long id, int linkFile) throws Exception {
		Document document = docDao.findById(id);
		DocumentDto docDto = new DocumentDto();
		docDto.setId(document.getId());
		docDto.setSubject(document.getSubject());
		docDto.setBody(document.getDescription());
		if(linkFile == 0) {
			docDto.setFilePath(document.getFirstPageImg());
		}else if(linkFile == 1){
			docDto.setFilePath(document.getFullFile());
		}else {
			docDto.setFilePath(document.getPreviewFile());
		}
		return docDto;
	}

	@Override
	public void saveDocument(HttpServletRequest req, DocumentDto docDto) throws Exception {
		try {
			Document doc = new Document();
			doc.setSubject(docDto.getSubject());
			doc.setDescription(docDto.getBody());
			//Handle split file pdf to 3 file
			// 1. upload full file 
			String pathFile = UtilsUploadFile.uploadFile(req, docDto.getFullFile(),
					CommonConst.UPLOAD_DOCS_FILE);
			doc.setFullFile(pathFile);
			// 2. upload file preview file (only 5 page of fullFile)
			int numPage = 5;
			String pathPreviewFile = UtilsUploadFile.splitPdf(req, numPage, docDto.getFullFile().getOriginalFilename(), pathFile, CommonConst.UPLOAD_DOCS_FILE);
			doc.setPreviewFile(pathPreviewFile);
			// 3. upload first page img (get first page and convert to img file)
			String firstPageImg = UtilsUploadFile.splitImgFromPdf(req, docDto.getFullFile().getOriginalFilename(), pathPreviewFile, CommonConst.UPLOAD_DOCS_FILE);
			doc.setFirstPageImg(firstPageImg);
			docDao.save(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("System Error. Please contact admin for further assistant");
		}
	}
	
	@Override
	public DocumentDto updateDocument(HttpServletRequest req, DocumentDto docDto) throws Exception {
		try {
			Document existingDocument = docDao.findById(docDto.getId());
			if (existingDocument == null) {
	               throw new Exception("Không tìm thấy bài viết");
	        }
			existingDocument.setSubject(docDto.getSubject());
			existingDocument.setDescription(docDto.getBody());
			//Handle split file pdf to 3 file
			if(docDto.getFullFile() != null) {
				// 1. upload full file 
				String pathFile = UtilsUploadFile.uploadFile(req, docDto.getFullFile(),
						CommonConst.UPLOAD_DOCS_FILE);
				UtilsUploadFile.deleteFileUploadDocument(req, existingDocument.getFullFile());
				existingDocument.setFullFile(pathFile);
				// 2. upload file preview file (only 5 page of fullFile)
				int numPage = 5;
				String pathPreviewFile = UtilsUploadFile.splitPdf(req, numPage, docDto.getFullFile().getOriginalFilename(), pathFile, CommonConst.UPLOAD_DOCS_FILE);
				UtilsUploadFile.deleteFileUploadDocument(req, existingDocument.getPreviewFile());
				existingDocument.setPreviewFile(pathPreviewFile);
				// 3. upload first page img (get first page and convert to img file)
				String firstPageImg = UtilsUploadFile.splitImgFromPdf(req, docDto.getFullFile().getOriginalFilename(), pathPreviewFile, CommonConst.UPLOAD_DOCS_FILE);
				UtilsUploadFile.deleteFileUploadDocument(req, existingDocument.getFirstPageImg());
				existingDocument.setFirstPageImg(firstPageImg);
					
				docDto.setFilePath(pathFile);
			}
			docDao.save(existingDocument);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("System Error. Please contact admin for further assistant");
		}
		return docDto;
	}
	
	@Override
	@Transactional
	public void deleteDocument(HttpServletRequest req, Long id, boolean forceDel) throws ConstraintException, Exception {
		try {
			Document existDoc = docDao.findById(id);
			if(existDoc == null) {
				throw new Exception("Không tìm thấy tài liệu");
			}
			
			docDao.delete(existDoc);
			UtilsUploadFile.deleteFileUploadDocument(req, existDoc.getFirstPageImg());
			UtilsUploadFile.deleteFileUploadDocument(req, existDoc.getPreviewFile());
			UtilsUploadFile.deleteFileUploadDocument(req, existDoc.getFullFile());
		}catch (ConstraintException e) {
			//write log
			throw e;
		} catch (Exception e) {
			//write log
			throw new Exception("System Error. Please contact admin for further assistant");
		}		
	}

}
