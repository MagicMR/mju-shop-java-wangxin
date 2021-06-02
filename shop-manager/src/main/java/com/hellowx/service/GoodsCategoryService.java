package com.hellowx.service;

import com.hellowx.pojo.GoodsCategory;
import com.hellowx.vo.GoodsCategoryVO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *  商品分类
 * @author MagicMushroom
 * @date 2021/5/22
 */
public interface GoodsCategoryService {

    /**
     *  商品管理-商品分类-获取顶级分类
     * @return 顶级分类列表
     */
     List<GoodsCategory> selectTopCategory();

    /**
     *  查询次级分类
     * @param parentId 父Id
     * @return 次级分类列表
     */
    List<GoodsCategory> selectSecondaryCategory(Short parentId);

    /**
     * 添加分类
     * @param goodsCategory 商品分类对象
     * @return 影响行数
     */
    int insertGoodsCategory(GoodsCategory goodsCategory);

    /**
     *  查询所有的产品分类
     * @return 产品分类列表
     */
    List<GoodsCategoryVO> selectCategoryList();

    /**
     *  删除分类
     * @param id 分类Id
     * @return 影响行数
     */
    int deleteCategory(Short id);
}
