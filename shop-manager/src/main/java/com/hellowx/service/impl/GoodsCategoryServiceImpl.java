package com.hellowx.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hellowx.mapper.GoodsCategoryMapper;
import com.hellowx.pojo.GoodsCategory;
import com.hellowx.service.GoodsCategoryService;
import com.hellowx.vo.GoodsCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author MagicMushroom
 * @date 2021/5/22
 */
@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public List<GoodsCategory> selectTopCategory() {
        return goodsCategoryMapper.selectGoodsCategoryByLevel((byte) 1);
    }

    @Override
    public List<GoodsCategory> selectSecondaryCategory(Short parentId) {
        return goodsCategoryMapper.selectGoodsCategoryByParentId(parentId);
    }

    @Override
    public int insertGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryMapper.insertOne(goodsCategory);
    }

    @Override
    public List<GoodsCategoryVO> selectCategoryList() {
//        查询所有顶级分类
        List<GoodsCategory> gcList01 =  goodsCategoryMapper.selectGoodsCategoryByParentIdAndLevel((short)0,(byte)1);
//        要返回的顶级分类列表
        List<GoodsCategoryVO> gcvList01 = new ArrayList<>();
//        遍历，添加二级分类
        for (GoodsCategory gc01 : gcList01) {
//            每一个顶级分类
            GoodsCategoryVO gcv01 = new GoodsCategoryVO();
//            属性复制
            BeanUtils.copyProperties(gc01,gcv01);
//            二级分类列表
            List<GoodsCategoryVO> gcvList02 = new ArrayList<>();
//            查询所有二级分类
            List<GoodsCategory> gcList02 = goodsCategoryMapper.selectGoodsCategoryByParentIdAndLevel(gcv01.getId(), (byte) 2);
            for (GoodsCategory gc02 : gcList02) {
//                每一个二级分类
                GoodsCategoryVO gcv02 = new GoodsCategoryVO();
                BeanUtils.copyProperties(gc02,gcv02);
//                三级分类列表
                List<GoodsCategoryVO> gcvList03=new ArrayList<>();
//                查询指定的三级分类
                List<GoodsCategory> gcList03 = goodsCategoryMapper.selectGoodsCategoryByParentIdAndLevel(gcv02.getId(), (byte) 3);
                for (GoodsCategory gc03 : gcList03) {
//                    每一个三级分类对象
                    GoodsCategoryVO gcv03 = new GoodsCategoryVO();
                    BeanUtils.copyProperties(gc03,gcv03);
//                    将查询到的三级分类添加进列表中
                    gcvList03.add(gcv03);
                }
//                给每一个二级分类添加三级分类
                gcv02.setChildren(gcvList03);
//                将查询到的二级分类添加进列表中
                gcvList02.add(gcv02);
            }
            gcv01.setChildren(gcvList02);
            gcvList01.add(gcv01);
        }
        return gcvList01;
////================================JDK8新特性================================
////        查询所有分类
//        List<GoodsCategory> list = goodsCategoryMapper.selectGoodsCategoryByParentId(null);
////        将List<GoodsCategory> 转成List<GoodsCategoryVO>
//        List<GoodsCategoryVO> gcvList =list.stream().map(e->{
//            GoodsCategoryVO gcv = new GoodsCategoryVO();
//            BeanUtils.copyProperties(e,gcv);
//            return gcv;
//        }).collect(Collectors.toList());
//        /*
//          将List<GoodsCategoryVo>转成Map<parentId,List<GoodsCategoryVo>>
//               按parentId分组，key为parentId，value为parentId对应的List
//         */
//        Map<Short,List<GoodsCategoryVO>> map =
//                gcvList.stream().collect(Collectors.groupingBy(GoodsCategoryVO::getParentId));
//        /*
//         * 循环，将childrenList 赋值
//         */
//        gcvList.forEach(e->e.setChildren(map.get(e.getId())));
//        /*
//         * 拦截器，返回Level=1 的list
//         */
//
//        return gcvList.stream().filter(e->1==e.getLevel())
//                .collect(Collectors.toList());
    }

    @Override
    public int deleteCategory(Short id) {
        return goodsCategoryMapper.deleteById(id);
    }

}
