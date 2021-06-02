package com.hellowx.mapper;

import com.hellowx.pojo.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author MagicMushroom
 * @date 2021/5/22
 */
@Mapper
public interface GoodsCategoryMapper {
    /**
     * 查询顶级分类
     * @param level 1 - 顶级 2 - 次级 3 - 次次级
     * @return 分类列表
     */
    List<GoodsCategory> selectGoodsCategoryByLevel(Byte level);

    /**
     * 根据父ID 查询子分类
     * @param parentId 父ID
     * @return 分类列表
     */
    List<GoodsCategory> selectGoodsCategoryByParentId(Short parentId);

    /**
     *  添加一条记录
     * @param goodsCategory 商品对象
     * @return 影响行数
     */
    int insertOne(GoodsCategory goodsCategory);

    /**
     *  查询所有的分类列表
     * @param parentId 父 Id
     * @param level 等级
     * @return 列表
     */
    List<GoodsCategory> selectGoodsCategoryByParentIdAndLevel(Short parentId,Byte level);

    /**
     * 通过id删除一条分类记录
     * @param id id
     * @return 影响行数
     */
    int deleteById(Short id);
}
