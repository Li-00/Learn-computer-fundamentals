package com.example.eight_part_essay.stream;

import com.csvreader.CsvReader;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * @author: zy
 * @date: 2022/2/26 13:54
 * @since JDK 1.8
 * 通过流上传本地文件到网络
 * 场景：实现多个不同的csv文件导入导出
 * 使用BufferedOutputStream代替OutputStream写入ServletOutputStream,一定程度较少IO次数
 * BufferedOutputStream默认每次缓存8m数据后执行写入
 */

public class Upload {

    //导出功能 表头识别  直接从传参模板DO获取字段赋值到string[]写入csv文件
    Example example = new Example("zzy", 22, true);
    ArrayList<Example> list = new ArrayList<>();
    Field[] headers = Example.class.getDeclaredFields();

    public Upload() throws FileNotFoundException {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //将csv写入到response的输出流中，供页面下载该Csv文件
        ServletOutputStream servletOutputStream = null;
        //定义输出流写入ServletOutputStream
        BufferedOutputStream outputStream = new BufferedOutputStream(servletOutputStream);
        outputStream.write(headers.toString().getBytes());
        //准备将Csv的输出流通过response输出到页面下载
        response.setContentType("application/csv");
        //设置导出csv的名称―如果开启Content-disposition，默认浏览器会进行下载操作
        String fileName = "example";
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode( fileName + ".csv", "UTF8"));


        //将存储元素遍历与实例类映射后写入outputStream
        for (Example example : list) {
//            outputStream.write(example.toMapping().getBytes());
        }


    }

    //建立字段映射，一般写Impl
    public String toMapping() {
        StringBuilder sb= new StringBuilder();
        sb.append((example.name) + ", ");
        sb.append((example.id) + ",");
        return sb.toString();
    }

    //导出功能  用了反射去获取对象的属性进行动态映射
    static class export {
        CsvReader reader = new CsvReader("d:\\detail.csv", ',', StandardCharsets.UTF_8);;
        {
            try {
                //工具类readCSV方法关键代码  逐行读入数据并通过反射方法与实体建立映射
                while (reader.readRecord()) {
                    Object obj = null;
                    List<String> values = Arrays.asList(reader.getValues());
                    Method method = obj.getClass().getDeclaredMethod( "mapping", List.class);
                    Object result0bj = method.invoke(obj, values);

                }
            } catch (IOException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        export() throws FileNotFoundException {
        }


    }

    //mapping类，定义在Impl中在执行readSCV方法时执行动态绑定
    public Example mapping(List<String>values) {
        Example example = new Example();
        example.setId(Integer.parseInt(values.get(0)));
        example.setName(values.get(1));
        return example;
    }

}
