package com.eim.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;

/**
 * Created by Zy on 2018/11/1.
 */
@Controller
//@RequestMapping("/wechat")
@Slf4j
public class WeChatController {


    @Autowired
    private WxMpService wxMpService;


//    @GetMapping("/authorize")
//    public void authorize(@RequestParam("returnUrl")String returnUrl,){
//        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
//        //1,配置
//        //2,调用方法
//
//    }

    /** 微信授权路由 */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("state") String state, @RequestParam("url") String url){
        //这个方法的三个参数分别是授权后的重定向url、获取用户信息类型和state
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,
                                                                      WxConsts.OAuth2Scope.SNSAPI_USERINFO,
                                                                      URLEncoder.encode(state));
         log.info("【微信网页授权】获取code,redirectUrl={}",redirectUrl);
         return "redirect:" + redirectUrl;
    }

    @ResponseBody
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,@RequestParam("state") String state){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            log.error("【微信网页授权】{}",e);
        }
        //获取openId
         String openId = wxMpOAuth2AccessToken.getOpenId();
        // 获取accessToken
         String accessToken = wxMpOAuth2AccessToken.getAccessToken();
        // 获取微信用户的公开信息
         WxMpUser user = null;
         try {
             user = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,null);
         }catch (Exception e){
           log.info("【获取微信用户信息】获取微信用户信息失败,user={}",user);
         }
         return  user.toString();
    }

    /** 生成带url的二维码 */
//    @GetMapping("/generateqrcode4shopauth")
//    @ResponseBody
//    public void generateORCode4ShopAuth(HttpServletRequest request, HttpServletResponse response) {
//        //从session中获取当前店铺信息
//         Shop shop = (Shop) request.getSession().getAttribute("currentShop");
//         String content = "{aaashopIdaaa:" + shop.getShopId() + ",aaacreateTimeaaa:" + System.currentTimeMillis + "}";
//         if(shop != null && shop.getShopId() != null) {
//         try {
//         String longUrl = "http://wx.natappvip.cc/wechat/authorize" + "?url=http://wx.natappvip.cc/shopadmin/addshopauthmap" + "&state=" + URLEncoder.encode(content,"UTF-8");
//        // 将目标url转成短url
//         String shortUrl = ShortNetAddressUtil.generateShortUrl(longUrl);
//        // 生成二维码
//         BitMatrix qRcodeImg = QRCodeUtil.generateQRCodeStream(shortUrl, response);
//        // 将二维码以图片流形式输出到前端
//             MatrixToImageWriter.writeToStream(qRcodeImg, "png", response.getOutputStream());
//             }catch(Exception e) { e.printStackTrace(); }
//             }
//    }

}
