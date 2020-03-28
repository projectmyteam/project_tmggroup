package com.otc.landmark.web.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.otc.landmark.web.Utils.DateUtil;
import com.otc.landmark.web.Utils.Utility;
import com.otc.landmark.web.Utils.UtilsUploadFile;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.PageWrapperDto;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.service.EntryService;

@Service
public class EntryServiceImpl implements EntryService {

    private static final Log logger = LogFactory.getLog(EntryServiceImpl.class);

    @Autowired
    EntryDao entryDao;

    @Override
    public void saveEntry(HttpServletRequest req, EntryDto entryDto) throws Exception {
        try {
            Entry entry = new Entry();
            entry.setSubject(entryDto.getSubject());
            entry.setBody(entryDto.getBody());
            entry.setCategoryId(entryDto.getCategoryId());
            entry.setSubCategoryId(entryDto.getSubCategoryId());
            entry.setAvatar(entryDto.getAvatarPath());
            entry.setCreatedDate(DateUtil.getSystemDateTime());
            entry.setCreatedBy(1L); // need modify
            // upload image
            String pathFile = UtilsUploadFile.uploadFile(req, entryDto.getAvatarFile(),
                    CommonConst.UPLOAD_ENTRY_AVARTA);
            entry.setAvatar(pathFile);
            entryDao.save(entry);
        } catch (Exception e) {
            throw new Exception("System Error. Please contact admin for further assistant");
        }

    }

    @Override
    public EntryDto getById(Long id) {
        Entry entry = entryDao.findById(id);
        EntryDto dto = new EntryDto();
        dto.setSubject(entry.getSubject());
        dto.setBody(entry.getBody());
        dto.setCategoryId(entry.getCategoryId());
        dto.setSubCategoryId(entry.getSubCategoryId());
        dto.setAvatarPath(entry.getAvatar());
        dto.setId(entry.getId());

        return dto;
    }

    @Override
    public void updateEntry(HttpServletRequest req, EntryDto entryDto) throws Exception {

        // Update entry field
        try {
            Entry existingEntry = entryDao.findById(entryDto.getId());
            if (existingEntry == null) {
                throw new Exception("Không tìm thấy bài viết");
            }
            existingEntry.setSubject(entryDto.getSubject());
            existingEntry.setBody(entryDto.getBody());
            existingEntry.setCategoryId(entryDto.getCategoryId());
            existingEntry.setSubCategoryId(entryDto.getSubCategoryId());
            if (entryDto.getAvatarFile() != null) {
                String pathFile = UtilsUploadFile.uploadFile(req, entryDto.getAvatarFile(),
                        CommonConst.UPLOAD_ENTRY_AVARTA);
                existingEntry.setAvatar(pathFile);

            }
            existingEntry.setUpdatedDate(DateUtil.getSystemDateTime());
            // hard code
            existingEntry.setUpdatedBy(1L);
            entryDao.save(existingEntry);
            //update avatarPath for dto because avatarPath could not mapped by FE
            entryDto.setAvatarPath(existingEntry.getAvatar());
        } catch (Exception e) {
            throw new Exception("System Error. Please contact admin for further assistant");
        }

    }

    @Override
    public List<EntryDto> getEntryBySubCategoryId(Long subcategoryId) throws Exception {
        List<EntryDto> entryDtos = new ArrayList<EntryDto>();
        try {
            List<Entry> entries = entryDao.findEntryBySubCateId(subcategoryId);
            if (entries != null && !entries.isEmpty()) {
                entryDtos = new ArrayList<EntryDto>();
                for (Entry entry : entries) {
                    EntryDto dto = new EntryDto();
                    dto.setId(entry.getId());
                    dto.setSubject(entry.getSubject());
                    dto.setBody(entry.getBody());
                    dto.setSubCategoryId(entry.getSubCategoryId());
                    dto.setCategoryId(entry.getCategoryId());
                    dto.setAvatarPath(entry.getAvatar());
                    String createDateStr = DateUtil.getCreateDate(entry.getCreatedDate());
                    dto.setYear(createDateStr.substring(0, 4));
                    dto.setMonth(createDateStr.substring(4, 6));
                    dto.setDay(createDateStr.substring(6, createDateStr.length()));
                    entryDtos.add(dto);
                }
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }

        return entryDtos;
    }


    @Override
    public PageWrapperDto<EntryDto> search(int page, int pageSize, EntryDto entryDto) throws Exception {
        PageWrapperDto<EntryDto> pageWrapperDto = new PageWrapperDto<EntryDto>(page, pageSize);
        try {
            int count = entryDao.countBySubCategoryId(entryDto.getSubCategoryId());

            List<EntryDto> data = new ArrayList<EntryDto>();
            if (count > 0) {
                int offsetSQL = Utility.calculateOffsetSQL(page, pageSize);
                data = getEntryBySubCategoryIdWithOffSet(entryDto.getSubCategoryId(), offsetSQL, pageSize);
            }
            pageWrapperDto.setDataAndCalculatePaging(data, count);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("System Error. Please contact admin for further assistant");
        }
        return pageWrapperDto;
    }


    @Override
    public List<EntryDto> getEntryBySubCategoryIdWithOffSet(Long subcategoryId, int offset, int pageSize) throws Exception {
        List<EntryDto> entryDtos = new ArrayList<EntryDto>();
        try {
            List<Entry> entries = entryDao.findEntryBySubCateIdWithOffset(subcategoryId, offset, pageSize);
            if (entries != null && !entries.isEmpty()) {
                for (Entry entry : entries) {
                    EntryDto dto = new EntryDto();
                    dto.setId(entry.getId());
                    dto.setSubject(entry.getSubject());
                    dto.setBody(entry.getBody());
                    dto.setSubCategoryId(entry.getSubCategoryId());
                    dto.setCategoryId(entry.getCategoryId());
                    dto.setAvatarPath(entry.getAvatar());
                    String createDateStr = DateUtil.getCreateDate(entry.getCreatedDate());
                    dto.setYear(createDateStr.substring(0, 4));
                    dto.setMonth(createDateStr.substring(4, 6));
                    dto.setDay(createDateStr.substring(6, createDateStr.length()));
                    entryDtos.add(dto);
                }
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }

        return entryDtos;
    }


}
