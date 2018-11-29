package com.pan.blog.repository;

import com.pan.blog.entity.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by FantasticPan on 2018/11/28.
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {

    /**
     * 模糊查询(去重)
     *
     * @param title
     * @param Summary
     * @param content
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(
            String title, String Summary, String content, String tags, Pageable pageable);

    /**
     * 根据博客 id 查询 EsBlog
     * @param blogId
     * @return
     */
    EsBlog findByBlogId(Long blogId);
}
