package com.songsong.music.category.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryDao {
    @Select("SELECT category_name FROM category WHERE category_id = #{categoryId}")
    String selectCategoryNameById(int categoryId);
}
