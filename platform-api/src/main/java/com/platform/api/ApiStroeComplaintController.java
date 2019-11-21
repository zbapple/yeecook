package com.platform.api;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.LoginUser;
import com.platform.dao.ApiStroeComplaintMapper;
import com.platform.entity.UserVo;
import com.platform.oss.OSSFactory;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.platform.entity.StroeComplaintVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller
 *
 * @author zoubin
 * @email 9379248@qq.com
 * @date 2019-10-29 14:42:33
 */
@Api(tags = "门店投诉")
@RestController
@RequestMapping("/api/stroecomplaint")
public class ApiStroeComplaintController extends ApiBaseAction {
    @Autowired
    private ApiStroeComplaintMapper stroeComplaintService;
    @Autowired
    private ApiUploadController apiUploadController;
    @ApiOperation(value = "用户投诉")
        @PostMapping("add")
    public Object add(@LoginUser UserVo loginuser, MultipartFile images,Integer stroeid, Integer complaitypes) throws Exception {
    Map<String,Object> result=new HashMap<>();
        String url=null;
//    for(int i=0;i<imagelist.size();i++){
//
//    }
        try {
             url= OSSFactory.build().upload(images);
        }catch (Exception e){
            result.put("flag",0);
            return  result;
        }
    StroeComplaintVo stroeComplai=new StroeComplaintVo();
    stroeComplai.setStroeId(stroeid);
    stroeComplai.setComplaintPic(url);
    stroeComplai.setComplaintypes(complaitypes);
    stroeComplai.setUserid(loginuser.getUserId());
    stroeComplaintService.save(stroeComplai);
    result.put("flag",1);
    return result;
    }
    @ApiOperation(value = "用户投诉")
    @PostMapping("addstr")
    public  Object addstr(@LoginUser UserVo logiuser){
        Map<String,Object> result=new HashMap<>();
        JSONObject addstrjson=this.getJsonRequest();
        Map addmap=new HashMap();
        addmap.put("stroeid",addstrjson.getInteger("stroeid"));
        addmap.put("complaitypes",addstrjson.getInteger("complaitypes"));
        addmap.put("userid",logiuser.getUserId());
        List<StroeComplaintVo> vos=stroeComplaintService.queryList(addmap);
        if (vos.size()!=0){
            Integer id=0;
            for(StroeComplaintVo stroeComplaintVoItem:vos){
                id=stroeComplaintVoItem.getId();
            }
            StroeComplaintVo stroeComplai=new StroeComplaintVo();
            Date date=new Date();
            stroeComplai.setId(id);
            stroeComplai.setAddtime(new java.sql.Date(date.getTime()));
            stroeComplai.setConnet(addstrjson.getString("connet"));
            stroeComplai.setComplaintType(addstrjson.getString("complaitype"));
            stroeComplaintService.update(stroeComplai);
            result.put("flag",1);
        }
        return result;
    }
}
