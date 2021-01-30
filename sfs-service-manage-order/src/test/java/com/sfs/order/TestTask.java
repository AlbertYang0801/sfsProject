package com.sfs.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author yjw
 * @date 2020/6/24 23:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTask {


    /**
     * 测试取出当前时间的前一分钟
     */
    @Test
    public void testTime() throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(GregorianCalendar.DAY_OF_WEEK,1);
        Date time = calendar.getTime();

        //格式化日期
        String format = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss").format(time);
        System.out.println(format);


    }



}
