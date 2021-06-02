package com.hellowx.controller;

import com.hellowx.pojo.GoodsCategory;
import com.hellowx.service.GoodsCategoryService;
import com.hellowx.utils.FtpUtils;
import com.hellowx.vo.GoodsCategoryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.net.ftp.FtpClient;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author MagicMushroom
 * @date 2021/5/22
 */
@RestController
@RequestMapping("/goods/category")
public class GoodsCategoryController {

    @Resource
    private GoodsCategoryService goodsCategoryService;

    @RequestMapping("/topCategory")
    public List<GoodsCategory> selectTopLevel(){
        return goodsCategoryService.selectTopCategory();
    }

    @RequestMapping("/secondaryCategory")
    public List<GoodsCategory> selectSecondaryCategory(Short parentId){
        return goodsCategoryService.selectSecondaryCategory(parentId);
    }

    @RequestMapping("/addCategory")
    public Map<String, Object> addCategory(GoodsCategory goodsCategory){
        int i =goodsCategoryService.insertGoodsCategory(goodsCategory);
        Map<String, Object> map=new HashMap<>();
        if (i>0){
            map.put("message","添加成功");
        }else {
            map.put("message","添加失败");
        }
        return map;
    }
    @RequestMapping("/categoryList")
    public List<GoodsCategoryVO> selectGoodsCategoryList(){
        return goodsCategoryService.selectCategoryList();
    }
    @RequestMapping("/upload")
    public String upload(@Param("file") MultipartFile file) throws IOException {
//        基础路径
        String basePath="www/images/";
//        存储的名称
        String name="goodsCategory_"+System.currentTimeMillis()+".png";
        try {
            FtpUtils.uploadImage((FileInputStream) file.getInputStream(),basePath+name);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return FtpUtils.BASE_IMG_PATH+name;
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(Short id){
        int i = goodsCategoryService.deleteCategory(id);
        if (i>0){
            return "删除成功!";
        }else {
            return "删除失败！！";
        }
    }
}
