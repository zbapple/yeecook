package com.platform.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * csv文件读写工具类
 *
 * @author by YangLD
 * @date 2018/7/10
 */
public class CsvUtils {

    private static final Logger logger = LoggerFactory.getLogger("CsvUtils.class");

    /**
     * 写csv文件 (一次性写  数据不宜过大)
     *
     * @param objectList  对象
     * @param fileHeader  头说明
     * @param fileName    文件名称(不要后缀.csv)
     * @return File 文件
     */
    public static File writeCsv(List<Object> objectList, String[] fileHeader, String fileName) {
        // 这里显式地配置一下CSV文件的Header，然后设置跳过Header（要不然读的时候会把头也当成一条记录）
        CSVFormat format = CSVFormat.DEFAULT.withHeader(fileHeader).withRecordSeparator("\n");
        // 这个是定位   判断某个字段的数据应该放在records数组中的那个位子
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < fileHeader.length; i++) {
            map.put(fileHeader[i], i);
        }
        File csvFile = new File(fileName);
        try {
            // 获取对象的PropertyDescriptor
            Map<String, PropertyDescriptor> descriptorMap = null;
            // 附加
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"));
            CSVPrinter printer = new CSVPrinter(bw, format);
            for (Object object : objectList) {
                if(null==descriptorMap){
                    descriptorMap = CsvUtils.getCsvFieldMapPropertyDescriptor(object.getClass());
                }
                String[] records = new String[fileHeader.length];
                for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                    if (descriptorMap.containsKey(stringIntegerEntry.getKey())) {
                        records[map.get(stringIntegerEntry.getKey())] = (String) descriptorMap.get(stringIntegerEntry.getKey()).getReadMethod().invoke(object);
                    }
                }
                printer.printRecord(Arrays.asList(records));
            }
            bw.flush();
            bw.close();
            printer.close();
        } catch (Exception e) {
            logger.error("CsvUtils.writeCsv,写csv文件失败,message:{}", e.getMessage(), e);
            throw new RRException("写csv文件失败");

        }
        return csvFile;
    }

    /**
     * 获取对应对象中包含CsvCsvField字段的 PropertyDescriptor
     *
     * @param tClass 对象的class
     * @return Map
     * @throws Exception 异常
     */
    public static Map<String, PropertyDescriptor> getCsvFieldMapPropertyDescriptor(Class tClass) throws Exception {
        Map<String, PropertyDescriptor> descriptorMap = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(tClass);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            // 获取该字段赋值过来的  字段名称
            if (propertyDescriptor.getWriteMethod() == null) {
                continue;
            }
            Field field = tClass.getDeclaredField(propertyDescriptor.getName());
            CsvField csvField = field.getAnnotation(CsvField.class);
            if (csvField == null) {
                continue;
            }
            String fieldMetaName = csvField.name();
            if (!StringUtils.isNotEmpty(fieldMetaName)) {
                continue;
            }
            descriptorMap.put(fieldMetaName, propertyDescriptor);
        }
        return descriptorMap;
    }

    /**
     * 读取csv文件  (一次性读取文件不宜过大)
     *
     * @param file 文件
     * @param headers  csv列头
     * @param tClass   返回对象的类型
     * @return CSVRecord 列表
     **/
    public static <T> List<T> readCSV(MultipartFile file, String[] headers, Class<T> tClass) {
        //创建CSVFormat
        CSVFormat format = CSVFormat.DEFAULT.withHeader(headers);
        // 获取对象的PropertyDescriptor
        List<T> tList = new ArrayList<>();
        try {
            Map<String, PropertyDescriptor> descriptorMap = CsvUtils.getCsvFieldMapPropertyDescriptor(tClass);
            File file1=FileUtils.multipartToFile(file);

            FileReader fileReader = new FileReader(file1);
            //创建CSVParser对象
            CSVParser parser = new CSVParser(fileReader, format);
            Map<String, Integer> map = parser.getHeaderMap();
            for (CSVRecord record : parser) {
                T t = tClass.newInstance();
                for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                    if (descriptorMap.containsKey(stringIntegerEntry.getKey()) && record.size() > stringIntegerEntry.getValue()) {
                        descriptorMap.get(stringIntegerEntry.getKey()).getWriteMethod().invoke(t, record.get(stringIntegerEntry.getValue()));
                    }
                }
                tList.add(t);
            }
            parser.close();
            fileReader.close();
        } catch (Exception e) {
            logger.error("CsvUtils.readCSV,读取csv文件,message:{}", e.getMessage(), e);
            throw new RRException("读取csv文件失败");
        }
        return tList;
    }

    public static <T> List<T> readCSV(String filePath, String[] headers, Class<T> tClass) {
        //创建CSVFormat
        CSVFormat format = CSVFormat.DEFAULT.withHeader(headers);
        // 获取对象的PropertyDescriptor
        List<T> tList = new ArrayList<>();
        try {
            Map<String, PropertyDescriptor> descriptorMap = CsvUtils.getCsvFieldMapPropertyDescriptor(tClass);
            FileReader fileReader = new FileReader(filePath);
            //创建CSVParser对象
            CSVParser parser = new CSVParser(fileReader, format);
            Map<String, Integer> map = parser.getHeaderMap();
            for (CSVRecord record : parser) {
                T t = tClass.newInstance();
                for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                    if (descriptorMap.containsKey(stringIntegerEntry.getKey()) && record.size() > stringIntegerEntry.getValue()) {
                        descriptorMap.get(stringIntegerEntry.getKey()).getWriteMethod().invoke(t, record.get(stringIntegerEntry.getValue()));
                    }
                }
                tList.add(t);
            }
            parser.close();
            fileReader.close();
        } catch (Exception e) {
            logger.error("CsvUtils.readCSV,读取csv文件,message:{}", e.getMessage(), e);
            throw new RRException("读取csv文件失败");
        }
        return tList;
    }

    public static void main(String[] args) throws Exception {
        String[] fileHeader = {"name", "sex"};
        // 测试写
        List<Object> list = new ArrayList<>();
//        for (int i = 0; i < 1000000; i++) {
//            MsgResponse msgResponse = new MsgResponse();
//            msgResponse.setCode("姓名44444888");
//            msgResponse.setMsg("性别44444488");
//            list.add(msgResponse);
//        }
//        long writeTimeStart = System.currentTimeMillis();
//        CsvUtils.writeCsv(list, fileHeader, "d:\\workbook.csv");
//        logger.info("写入时间：" + (System.currentTimeMillis() - writeTimeStart));
////        测试读
//        long readTimeStart = System.currentTimeMillis();
//        List<MsgResponse> m = CsvUtils.readCSV("d:\\workbook.csv", fileHeader, MsgResponse.class);
//        logger.info("读取时间：" + (System.currentTimeMillis() - readTimeStart));
////        for (MsgResponse msgResponse : m) {
////            logger.info(msgResponse.getCode() + "               " + msgResponse.getMsg());
////        }
    }

}