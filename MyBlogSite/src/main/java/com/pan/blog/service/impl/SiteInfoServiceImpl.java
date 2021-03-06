package com.pan.blog.service.impl;

import com.pan.blog.dao.SiteInfoRepository;
import com.pan.blog.entity.SiteInfo;
import com.pan.blog.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by FantasticPan on 2018/11/25.
 */
@Service
public class SiteInfoServiceImpl implements SiteInfoService {

    @Autowired
    private SiteInfoRepository siteInfoRepository;

    @Transactional
    @Override
    public void saveSiteInfo(SiteInfo siteInfo) {
        siteInfoRepository.save(siteInfo);
    }

    @Override
    public List<SiteInfo> findAll() {
        return siteInfoRepository.findAll();
    }

    @Override
    public void visitSizeIncrease() {
        List<SiteInfo> siteInfoList = this.findAll();
        SiteInfo siteInfo = siteInfoList.get(0);
        siteInfo.setVisitSize(siteInfo.getVisitSize() + 1);
        this.saveSiteInfo(siteInfo);
    }
}
