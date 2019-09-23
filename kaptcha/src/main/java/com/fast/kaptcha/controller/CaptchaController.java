package com.fast.kaptcha.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RequestMapping({"/captcha"})
@Controller
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @GetMapping({"/captchaImage"})
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");
            String type = request.getParameter("type");
            String code = null;
            BufferedImage bi = null;
            if ("math".equals(type)) {
                String capText = this.captchaProducerMath.createText();
                String capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = this.captchaProducerMath.createImage(capStr);
            } else if ("char".equals(type)) {
                code = this.captchaProducer.createText();
                bi = this.captchaProducer.createImage(code);
            }
            session.setAttribute("KAPTCHA_SESSION_KEY", code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
        }
        return null;
    }
}
