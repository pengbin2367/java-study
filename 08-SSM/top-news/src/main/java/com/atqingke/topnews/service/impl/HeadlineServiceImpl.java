package com.atqingke.topnews.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atqingke.topnews.pojo.Headline;
import com.atqingke.topnews.service.HeadlineService;
import com.atqingke.topnews.mapper.HeadlineMapper;
import org.springframework.stereotype.Service;

/**
* @author pengbin
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-05-20 14:11:59
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

}




