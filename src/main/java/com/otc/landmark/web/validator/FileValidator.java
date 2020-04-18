package com.otc.landmark.web.validator;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.constant.MessageList;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.NewsDto;

@Component("fileValidator")
public class FileValidator implements Validator{
	
	private static final String[] IMG_MIME_TYPE = new String[] {"image/jpeg", "image/png",
			"image/x-icon", "image/bmp"};
	private static final long MAX_UPLOAD_SIZE = 10485760; //10 MB
	private static final long MB_UNIT = 1048576;

	@Override
	public boolean supports(Class<?> clazz) {
		return (EntryDto.class.equals(clazz) || NewsDto.class.equals(clazz) || MessageList.class.equals(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		MultipartFile avatarFile = null;
		if(target instanceof EntryDto) {
			avatarFile = ((EntryDto) target).getAvatarFile();
		}else if(target instanceof NewsDto) {
			avatarFile = ((NewsDto) target).getAvatarFile();
		}
		
		if(avatarFile == null || avatarFile.isEmpty()) {
			errors.rejectValue("avatarFile", "message.upload.file.required", null, CommonConst.EMPTY);
		}else if(avatarFile.getSize() > MAX_UPLOAD_SIZE) {
			String[] errorArgs = new String[1];
			errorArgs[0] = String.valueOf(MAX_UPLOAD_SIZE / MB_UNIT);
			errors.rejectValue("avatarFile", "message.upload.exceed.file.size", errorArgs, CommonConst.EMPTY);
		}else if(!Arrays.asList(IMG_MIME_TYPE).contains(avatarFile.getContentType())) {
			errors.rejectValue("avatarFile", "message.invalid.file.type", null, CommonConst.EMPTY);
		}
	}

}
