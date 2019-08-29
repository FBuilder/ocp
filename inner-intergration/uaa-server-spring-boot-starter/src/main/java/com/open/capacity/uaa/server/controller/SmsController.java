package com.open.capacity.uaa.server.controller;

import com.open.capacity.common.util.StringUtils;
import com.open.capacity.common.web.Result;
import com.open.capacity.uaa.server.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信提供
 * @author zlt
 * @date 2018/12/18
 */
@RestController
public class SmsController {

    public final static String SYSMSG_LOGIN_PWD_MSG="验证码：{0}，3分钟内有效";

    @Autowired
    private ValidateCodeService validateCodeService;

    @RequestMapping("/sms/send")
    public Result sendSms(@RequestParam(value = "mobile",required = false) String mobile) {
        String content = SmsController.SYSMSG_LOGIN_PWD_MSG.replace("{0}", StringUtils.generateRamdomNum());
//        SendMsgResult sendMsgResult = MobileMsgConfig.sendMsg(mobile, content);

        validateCodeService.saveImageCode(mobile, StringUtils.generateRamdomNum());
        return  Result.succeed(  StringUtils.generateRamdomNum(), "发送成功");
    }

}