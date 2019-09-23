package com.test.base.jsoup;

import com.fast.BaseApplication;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: cyb
 * @Date: 2019/8/7 19:50
 * @Version 1.0
 */
@SpringBootTest(classes = BaseApplication.class)
@RunWith(SpringRunner.class)
public class JsoupTest {
    private Logger logger = LoggerFactory.getLogger(JsoupTest.class);

    @Test
    public void parse() {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>" ;
        Document doc = Jsoup.parse(html);
        logger.info(doc.toString());
    }
}
