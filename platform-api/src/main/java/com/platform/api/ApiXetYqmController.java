package com.platform.api;

import com.github.pagehelper.PageHelper;
import com.platform.annotation.LoginUser;
import com.platform.entity.SysPrinterUserVo;
import com.platform.entity.SysPrinterVo;
import com.platform.entity.UserVo;
import com.platform.entity.XetYqmVo;
import com.platform.printer.yly.Methods;
import com.platform.service.ApiSysPrinterService;
import com.platform.service.ApiXetYqmService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API登录授权
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:31
 */
@Api(tags = "课程激活码打印")
@RestController
@RequestMapping("/api/xetyqm")
public class ApiXetYqmController extends ApiBaseAction {
    @Autowired
    private ApiXetYqmService apiXetYqmService;

    @Autowired
    private ApiSysPrinterService sysPrinterService;

    /**
     * 　　index
     */
    @ApiOperation(value = "搜索可以打印的激活码列表")
    @PostMapping("list")
    public Object list(@LoginUser UserVo loginUser,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> resultObj = new HashMap();
        Map param = new HashMap();
        param.put("userId", loginUser.getUserId());
        //查询列表数据
        PageHelper.startPage(0, 10, false);
        List<XetYqmVo> xetYqmVoList = apiXetYqmService.query1(param);
        ApiPageUtils pageUtil = new ApiPageUtils(xetYqmVoList, 0, size, page);
        resultObj.put("count", pageUtil.getCount());
        resultObj.put("totalPages", pageUtil.getTotalPages());
        resultObj.put("numsPerPage", pageUtil.getNumsPerPage());
        resultObj.put("currentPage", pageUtil.getCurrentPage());
        resultObj.put("data", xetYqmVoList);
        return toResponsSuccess(resultObj);
    }



    /**
     * 　　clearhistory
     */
    @PostMapping("print")
    public Object print(@LoginUser UserVo loginUser,String batchName,String batchId,@RequestParam(value = "printSum", defaultValue = "1")Integer printSum) {
        Map param = new HashMap();
        param.put("userId", loginUser.getUserId());
        param.put("batchName", batchName);
        param.put("batchId", batchId);
        PageHelper.startPage(0, printSum, false);
        List<XetYqmVo> xetYqmVoList = apiXetYqmService.queryList(param);

        if(xetYqmVoList.size()<=0) {
            return toResponsFail("没有可用的邀请码!");
        }

        //获取打印机参数，和用户对应的打印机
        List<SysPrinterUserVo> list= sysPrinterService.getSysPrinterUserVo(xetYqmVoList.get(0).getUserId(),"0001");
        SysPrinterVo sysPrinterVo=sysPrinterService.getSysPrinterVo("易联云");
        String errMsg=""; //打印异常信息
        int errSum=0; //打印异常个数

        List ids=new ArrayList();
        XetYqmVo map=new XetYqmVo();
        map.setIsUse("已使用");
        map.setIds(ids);
        map.setUseUserId(loginUser.getWeixin_openid());
        map.setUseUserName(loginUser.getNickname());

        for (int i = 0; i < xetYqmVoList.size(); i++) {
            XetYqmVo xetYqmVo=xetYqmVoList.get(i);
            StringBuffer c=new StringBuffer();
//            c.append("<PW>");
//            c.append("080");
//            c.append("</PW>");
            c.append("<center>");
            c.append(xetYqmVo.getBatchName());
            c.append("</center>");
            c.append("<QR>");
            c.append(xetYqmVo.getInvitationCodeUrl());
            c.append("</QR>");
            c.append("<center>");
            c.append("请扫描二维码激活");
            c.append("</center>");
            c.append("<center>");
            c.append("《宜厨科技》");
            c.append("</center>");
            try {
                print(list,sysPrinterVo,c.toString(),xetYqmVo.getInvitationCode());
                ids.add(xetYqmVo.getId());
            } catch (Methods.PrinterException e) {
                e.printStackTrace();

                if(!StringUtils.isNotEmpty(errMsg)){
                    errMsg=e.getMessage();
                }
                errSum++;
            }
        }
        int i=0;
        if(ids.size()>0) {
             i = apiXetYqmService.updateBatch(map);
        }
        if(StringUtils.isNotEmpty(errMsg)){
            return toResponsFail(errMsg+"计划打印："+printSum+"个"+";未打印："+errSum+"个;");
        }else {
            return toResponsSuccess("打印成功!" +"计划打印："+printSum+"个;"+ "已打印：" + i + "个;");
        }
    }

    public void print(List<SysPrinterUserVo> list,SysPrinterVo sysPrinterVo,String count,String num) throws Methods.PrinterException {
        Methods.getInstance().init(sysPrinterVo.getAppId(),sysPrinterVo.getAppKey());
        Methods.getInstance().getFreedomToken();
        for (SysPrinterUserVo sysPrinterUserVo:list){
            Methods.getInstance().addPrinter(sysPrinterUserVo.getMachineCode(),sysPrinterUserVo.getMachineKey());
            Methods.getInstance().print(sysPrinterUserVo.getMachineCode(),count,num);
            break;
        }
    }

}
