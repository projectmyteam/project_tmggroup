package com.otc.landmark.web.service.impl;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class CategoryServiceImpl {

}
