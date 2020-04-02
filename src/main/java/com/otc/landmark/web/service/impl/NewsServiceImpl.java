package com.otc.landmark.web.service.impl;

import com.otc.landmark.web.Utils.DateUtil;
import com.otc.landmark.web.Utils.UtilsUploadFile;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.domain.News;
import com.otc.landmark.web.dto.NewsDto;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.repository.NewsDao;
import com.otc.landmark.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
    NewsDao newsDao;
	
	@Autowired
    EntryDao entryDao;

	@Override
	public void saveNews(HttpServletRequest req, NewsDto newsDto) throws Exception {
		try {
			News news = new News();
			news.setSubject(newsDto.getSubject());
			news.setCategoryId(newsDto.getCategoryId());
			news.setSubCategoryId(newsDto.getSubCategoryId());
			Entry linkEntry = entryDao.findById(newsDto.getEntryId());
			news.setEntry(linkEntry);
//			news.setEntryId(newsDto.getEntryId());
			// upload image
			String pathFile = UtilsUploadFile.uploadFile(req, newsDto.getAvatarFile(), CommonConst.UPLOAD_NEWS_AVARTA);
			news.setAvatar(pathFile);
			news.setCreatedBy(1L);
			news.setCreatedDate(DateUtil.getSystemDateTime());
			newsDao.save(news);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("System Error. Please contact admin for further assistant");
		}

	}

	@Override
	public NewsDto getById(Long id) throws Exception {
		NewsDto newsDto = new NewsDto();
		try {
			News news = newsDao.findById(id);
			// set data to newDto
			newsDto.setId(news.getId());
			newsDto.setSubject(news.getSubject());
			newsDto.setSubCategoryId(news.getSubCategoryId());
			newsDto.setCategoryId(news.getCategoryId());
			newsDto.setEntryId(news.getEntry().getId());
			newsDto.setAvatarPath(news.getAvatar());
		} catch (Exception e) {
			throw new Exception("System Error. Please contact admin for further assistant");
		}

		return newsDto;
	}

	@Override
	@Transactional
	public void updateNews(HttpServletRequest req, NewsDto newsDto) throws Exception {
		try {
			News existingNews = newsDao.findById(newsDto.getId());
			if (existingNews == null) {
				throw new Exception("Không tìm thấy bản tin");
			}
			existingNews.setSubject(newsDto.getSubject());
			existingNews.setCategoryId(newsDto.getCategoryId());
			existingNews.setSubCategoryId(newsDto.getSubCategoryId());
			//existingNews.setEntryId(newsDto.getEntryId());
			if(existingNews.getEntry().getId() != newsDto.getEntryId()) {
				existingNews.setEntry(entryDao.findById(newsDto.getEntryId()));
			}
			if (newsDto.getAvatarFile() != null) {
				String pathFile = UtilsUploadFile.uploadFile(req, newsDto.getAvatarFile(),
						CommonConst.UPLOAD_NEWS_AVARTA);
				existingNews.setAvatar(pathFile);
			}
			existingNews.setUpdatedDate(DateUtil.getSystemDateTime());
			// hard code
			existingNews.setUpdatedBy(1L);
			//newsDao.save(existingNews);
			//update avatarPath for dto because avatarPath could not mapped by FE
			newsDto.setAvatarPath(existingNews.getAvatar());
		} catch (Exception e) {
			throw new Exception("System Error. Please contact admin for further assistant");
		}

	}

	@Override
	public void deleteNews(Long id) throws Exception {
		try {
			News existingNews = newsDao.findById(id);
			if(existingNews == null) {
				throw new Exception("Không tìm thấy bản tin");
			}
			newsDao.delete(existingNews);
		} catch (Exception e) {
			//write log
			throw new Exception("System Error. Please contact admin for further assistant");
		}
	}

}
