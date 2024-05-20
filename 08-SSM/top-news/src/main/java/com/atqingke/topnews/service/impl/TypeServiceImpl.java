package com.atqingke.topnews.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atqingke.topnews.pojo.Type;
import com.atqingke.topnews.service.TypeService;
import com.atqingke.topnews.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author pengbin
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-05-20 14:11:59
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




