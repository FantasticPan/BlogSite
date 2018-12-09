package com.pan.blog.service.impl;

import com.pan.blog.dao.SiteInfoRepository;
import com.pan.blog.entity.SiteInfo;
import com.pan.blog.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FantasticPan on 2018/11/25.
 */
@Service
public class SiteInfoServiceImpl implements SiteInfoService {

    @Autowired
    private SiteInfoRepository siteInfoRepository;

    @Override
    public void saveSiteInfo(SiteInfo siteInfo) {
        siteInfoRepository.save(siteInfo);
    }

    @Override
    public List<SiteInfo> findAll() {
        return siteInfoRepository.findAll();
    }
}
