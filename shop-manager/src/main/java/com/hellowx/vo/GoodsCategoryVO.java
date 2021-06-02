package com.hellowx.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author MagicMushroom
 * @date 2021/5/22
 */
@Data
public class GoodsCategoryVO implements Serializable {
    /**
     * 子分类
     */
    private List<GoodsCategoryVO> children;
    /**
     *  商品分类Id
     */
    private Short id;
    /**
     * 商品分类名称
     */
    private String name;
    /**
     * 手机端显示的商品分类名
     */
    private String mobileName;
    /**
     *父id
     */
    private Short parentId;
    /**
     *家族图谱
     */
    private String parentIdPath;
    /**
     *等级
     */
    private Byte level;
    /**
     *顺序排序
     */
    private Byte sortOrder;
    /**
     *是否显示
     */
    private Byte isShow;
    /**
     *分类图片
     */
    private String image;
    /**
     *是否推荐为热门分类
     */
    private Byte isHot;
    /**
     * 分类分组默认0
     */
    private Byte catGroup;
    /**
     * 分佣比例
     */
    private Byte commissionRate;

    /**
     * t_goods_category
     */
    private static final long serialVersionUID = 1L;

}
