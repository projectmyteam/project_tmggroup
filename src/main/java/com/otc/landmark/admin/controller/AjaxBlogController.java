package com.otc.landmark.admin.controller;

import com.otc.landmark.web.Utils.UtilsUploadFile;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.dto.JsonFileUpload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping(value = UrlConst.ADMIN+UrlConst.BLOG)
public class AjaxBlogController {


    @RequestMapping(value = "upload_ckeditor", method = RequestMethod.POST, produces = {
            MimeTypeUtils.APPLICATION_JSON_VALUE})
    public ResponseEntity<JsonFileUpload> uploadCkEditor(HttpServletRequest req, @RequestParam("upload")
            MultipartFile multipartFile) {
        try {
            String pathFile = UtilsUploadFile.uploadFile(req, multipartFile, CommonConst.UPLOAD_FILE_CKEDITOR);
            JsonFileUpload jsonFileUpload = new JsonFileUpload(pathFile);
            return new ResponseEntity<JsonFileUpload>(jsonFileUpload, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<JsonFileUpload>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        	return new ResponseEntity<JsonFileUpload>(HttpStatus.BAD_REQUEST);
		}
    }

    @RequestMapping(value = "filebrowse", method = RequestMethod.GET)
    public ModelAndView fileBrowse(HttpServletRequest httpServletRequest, @RequestParam("CKEditorFuncNum") String funcNum) {
        ModelAndView mav = new ModelAndView("otc.filebrowse.view");
        File folder = new File(httpServletRequest.getServletContext().getRealPath(CommonConst.UPLOAD_FILE_CKEDITOR));
        mav.addObject("files", folder.listFiles());
        mav.addObject("funcNum", funcNum);
        return mav;
    }

    @RequestMapping(value = UrlConst.AJAX+UrlConst.DELETEFILECKEDITOR, method = RequestMethod.POST)
    public ResponseEntity<String> deleteFileCkeditor(HttpServletRequest req,
                                                     @RequestParam("namefile") String namefile,
                                                     HttpServletResponse resp) {
        boolean checkactfile = UtilsUploadFile.deleteFileUploadCkeditor(req, namefile);
        if(checkactfile) {
            return new ResponseEntity<String>("true", HttpStatus.OK);
        }
        return new ResponseEntity<String>("false", HttpStatus.OK);
    }



}
