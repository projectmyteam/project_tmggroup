package com.otc.landmark.web.service.impl;

import com.otc.landmark.web.Utils.DTOConvert;
import com.otc.landmark.web.Utils.DateUtil;
import com.otc.landmark.web.Utils.Utility;
import com.otc.landmark.web.Utils.UtilsUploadFile;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.domain.News;
import com.otc.landmark.web.dto.CategoryDto;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.EntrySearchDto;
import com.otc.landmark.web.dto.PageWrapperDto;
import com.otc.landmark.web.exception.ConstraintException;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.repository.impl.CategoryDaoImpl;
import com.otc.landmark.web.service.EntryService;
import com.otc.landmark.web.service.NewsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EntryServiceImpl implements EntryService {

    private static final Log logger = LogFactory.getLog(EntryServiceImpl.class);

    @Autowired
    EntryDao entryDao;
    
    @Autowired
    CategoryDao categoryDao;

	@Autowired
    NewsService newsService;

	@Override
	public List<EntryDto> getAll() {
		List<EntryDto> entryDtos = new ArrayList<EntryDto>();
		DTOConvert.convertListEntry2DTO(entryDao.findAll(), entryDtos);
		return entryDtos;
	}

	@Override
	public void saveEntry(HttpServletRequest req, EntryDto entryDto) throws Exception {
		try {
			Entry entry = new Entry();
			Category category = null;
			entry.setSubject(entryDto.getSubject());
			entry.setBody(entryDto.getBody());
			//Handle entry do not have subCategory
			if(entryDto.getSubCategoryId() == null) {
				category = categoryDao.findById(entryDto.getCategoryId());
			}else {
				category = categoryDao.findById(entryDto.getSubCategoryId());
			}
			entry.setCategory(category);
			entry.setCategoryId(entryDto.getCategoryId());
//			entry.setSubCategoryId(entryDto.getSubCategoryId());
			entry.setAvatar(entryDto.getAvatarPath());
			entry.setCreatedDate(DateUtil.getSystemDateTime());
			entry.setCreatedBy(1L); // need modify
			// upload image
			String pathFile = UtilsUploadFile.uploadFile(req, entryDto.getAvatarFile(),
					CommonConst.UPLOAD_ENTRY_AVARTA);
			entry.setAvatar(pathFile);
			entryDao.save(entry);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("System Error. Please contact admin for further assistant");
		}

    }

    @Override
    public EntryDto getById(Long id) {
    	Entry entry = entryDao.findById(id);
        EntryDto entryDto = new EntryDto();
        DTOConvert.convertEntry2DTO(entry, entryDto);

        return entryDto;
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
            existingEntry.setCategory(categoryDao.findById(entryDto.getSubCategoryId()));
            existingEntry.setCategoryId(entryDto.getCategoryId());
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
//                    Category category = entry.getCategory();
                    //System.out.println(category);
                    String name = entry.getCategory().getCategoryName();
                    Long id = entry.getCategory().getCategoryId();
                    dto.setSubCategoryId(entry.getCategory().getCategoryId());
                    dto.setCategoryId(entry.getCategoryId());
//                    dto.setCategory(entry.getCategory());
                    dto.setAvatarPath(entry.getAvatar());
                    EntryDto entryDto = new EntryDto();
                    CategoryDto categoryDto = new CategoryDto();
                    entryDto.setId(entry.getId());
                    entryDto.setSubject(entry.getSubject());
                    entryDto.setBody(entry.getBody());
                    DTOConvert.convertCategory2DTO(entry.getCategory(), categoryDto);
                    entryDto.setCategoryDto(categoryDto);
                    entryDto.setSubCategoryId(entry.getCategory().getCategoryId());
                    entryDto.setCategoryId(entry.getCategoryId());
                    entryDto.setAvatarPath(entry.getAvatar());
                    String createDateStr = DateUtil.getCreateDate(entry.getCreatedDate());
                    entryDto.setYear(createDateStr.substring(0, 4));
                    entryDto.setMonth(createDateStr.substring(4, 6));
                    entryDto.setDay(createDateStr.substring(6, createDateStr.length()));
                    entryDtos.add(entryDto);
                }
            }
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }

        return entryDtos;
    }


    @Override
    public PageWrapperDto<EntryDto> search(int page, int pageSize, EntrySearchDto searchDto) throws Exception {
        PageWrapperDto<EntryDto> pageWrapperDto = new PageWrapperDto<EntryDto>(page, pageSize);
        
        genSearchCondition(searchDto);
        
        try {
            int count = entryDao.countByEntrySearchDto(searchDto);

            List<EntryDto> data = new ArrayList<EntryDto>();
            if (count > 0) {
                int offsetSQL = Utility.calculateOffsetSQL(page, pageSize);
                data = getEntryByEntrySearchDtoWithOffSet(searchDto, offsetSQL, pageSize);
            }
            pageWrapperDto.setDataAndCalculatePaging(data, count);
        } catch (Exception e) {
            logger.error(e);
            throw new Exception("System Error. Please contact admin for further assistant");
        }
        return pageWrapperDto;
    }

	@Override
    public List<EntryDto> getEntryByEntrySearchDtoWithOffSet(EntrySearchDto searchDto, int offset, int pageSize) throws Exception {
        List<EntryDto> entryDtos = new ArrayList<EntryDto>();
        try {
            List<Entry> entries = entryDao.findEntryByEntrySearchWithOffset(searchDto, offset, pageSize);
            if (entries != null && !entries.isEmpty()) {
                for (Entry entry : entries) {
                    EntryDto dto = new EntryDto();
                    dto.setId(entry.getId());
                    dto.setSubject(entry.getSubject());
                    dto.setBody(entry.getBody());
                    dto.setSubCategoryId(entry.getCategory().getCategoryId());
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
	@Transactional
	public void deleteEntry(Long id, boolean forceDel) throws ConstraintException, Exception {
		try {
			Entry existEntry = entryDao.findEntryAndNewsById(id);
			if(existEntry == null) {
				throw new Exception("Không tìm thấy bài viết");
			}
			if(existEntry.getNews() != null) {
				News linkNews = existEntry.getNews();
				if(forceDel) {
					newsService.deleteNews(linkNews.getId());
				}else {
					throw new ConstraintException(linkNews.getSubject() + "||" + linkNews.getEntry().getId());
				}
			}
			entryDao.delete(existEntry);
		}catch (ConstraintException e) {
			//write log
			throw e;
		} catch (Exception e) {
			//write log
			throw new Exception("System Error. Please contact admin for further assistant");
		}
	}

	@Override
	public List<EntryDto> getNewestEntries(Long subcategoryId) throws Exception {
		List<EntryDto> entryDtos = new ArrayList<EntryDto>();
		try {
			List<Entry> entries = entryDao.findNewestEntries(subcategoryId, 3);
			DTOConvert.convertListEntry2DTO(entries, entryDtos);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("System Error. Please contact admin for further assistant");
		}

		return entryDtos;
	}

	@Override
	public List<EntryDto> getRelativeEntries(Long entryId, Long subcategoryId) throws Exception {
		List<EntryDto> entryDtos = new ArrayList<EntryDto>();
		try {
			List<Entry> entries = entryDao.findRelativeEntries(entryId, subcategoryId, 3);
			DTOConvert.convertListEntry2DTO(entries, entryDtos);
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("System Error. Please contact admin for further assistant");
		}

		return entryDtos;
	}
	
	private void genSearchCondition(EntrySearchDto searchDto) {
		if(Utility.checkString(searchDto.getSearchValue())) {
			searchDto.setSubject(searchDto.getSearchValue());
		}
	}
	
}
