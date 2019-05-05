package com.platform.controller;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.platform.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.NewFoodEntity;
import com.platform.service.NewFoodService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-03-13 11:32:51
 */
@RestController
@RequestMapping("newfood")
public class NewFoodController {
    @Autowired
    private NewFoodService newFoodService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("newfood:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<NewFoodEntity> newFoodList = newFoodService.queryList(query);
        int total = newFoodService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(newFoodList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("newfood:info")
    public R info(@PathVariable("id") Integer id) {
        NewFoodEntity newFood = newFoodService.queryObject(id);

        return R.ok().put("newFood", newFood);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("newfood:save")
    public R save(@RequestBody NewFoodEntity newFood) {
        newFoodService.save(newFood);
        if(StringUtils.isNullOrEmpty(newFood.getUrl())) {
            newFood.setUrl("https://www.yeecook.com:8081/#/?activate=");
        }
        if(StringUtils.isNullOrEmpty(newFood.getFoodUrl())) {
            newFood.setFoodUrl("https://www.yeecook.com:8081/#/pages/tabbar/tabbar-1/scsq/scsq?activate=");
        }
        if(StringUtils.isNullOrEmpty(newFood.getSupplieUrl())) {
            newFood.setSupplieUrl("https://www.yeecook.com:8081/#/pages/tabbar/tabbar-1/gys/gys?activate=");
        }
        newFood.setUrl(newFood.getUrl() + newFood.getId());
        newFood.setFoodUrl(newFood.getFoodUrl() + newFood.getId());
        newFood.setSupplieUrl(newFood.getSupplieUrl() + newFood.getId());
        newFoodService.update(newFood);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("newfood:update")
    public R update(@RequestBody NewFoodEntity newFood) {
        newFoodService.update(newFood);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("newfood:delete")
    public R delete(@RequestBody Integer[] ids) {
        newFoodService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<NewFoodEntity> list = newFoodService.queryList(params);

        return R.ok().put("list", list);
    }

    @RequestMapping("/imgDownLoad")
    public R fileDownLoad(HttpServletResponse response,
                          @RequestParam int id,@RequestParam int format, @RequestParam int width, @RequestParam int height) {

        NewFoodEntity newFood = newFoodService.queryObject(id);
        if(newFood==null||format==0){
            return R.error("生成失败！");
        }

            String text="";
        switch (format){
            case 1:
                text=newFood.getSupplieUrl();
                break;
            case 2:
                text=newFood.getUrl();
                break;
            case 3:
                text=newFood.getFoodUrl();
                break;


        }

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码

        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(text,
                    BarcodeFormat.QR_CODE, width==0?200:width, height==0?200:width, hints);
        } catch (WriterException e) {
            e.printStackTrace();
            return R.error("生成失败！");
        }


        try {
            ServletOutputStream out = response.getOutputStream();
            MatrixToImageWriter.writeToFile(bitMatrix, "png", out);
            out.flush();
            out.close();//关闭输出流
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("生成失败！");
        }
        return R.ok();
    }
}
