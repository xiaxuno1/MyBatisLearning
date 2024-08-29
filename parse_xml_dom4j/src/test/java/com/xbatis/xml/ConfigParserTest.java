package com.xbatis.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import javax.xml.parsers.SAXParser;
import java.io.InputStream;
import java.util.List;

//config解析器测试
public class ConfigParserTest {
    @Test
    public void xbatis_parse_xml_01_test() throws Exception{
        //实例化sax reader，用于读取流为Document 类对象，处理xml
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("xbatis-config.xml"));
        /*
        获取 指定节点 的 指定属性 的值
        */
        //Element 是 XML 处理库中的一个类，通常代表 XML 文档中的一个节点（元素）。它提供了方法来访问和操作 XML 元素的属性、子节点等内容。
        //selectSingleNode 是用于在 XML 文档中根据 XPath 表达式选择单个节点的方法。XPath 是一种用于在 XML 文档中查找信息的语言。selectSingleNode 方法根据给定的 XPath 表达式返回文档中匹配的第一个节点。如果没有找到匹配的节点，它返回 null。
        //(Element) 是将 selectSingleNode 方法返回的节点转换为 Element 类型。这是因为 selectSingleNode 方法返回的是一个 Node 对象（它可以是 Element、Text、Comment 等），需要通过类型转换将其转换为 Element 类型，以便访问其特定的属性和方法。
        Element nodeEle = (Element) document.selectSingleNode("/configuration/environments");
        String defaultVal = nodeEle.attributeValue("default");
        System.out.println(defaultVal); //production

        /*
        *获取指定节点 下的 指定子元素element 的内容
        * */
        String xpath = "/configuration/environments/environment[@id='"+defaultVal+"']"; //根据上面的defaultVal获取
        //System.out.println(xpath);
        //选择节点
        Element nodeEnvironment = (Element) document.selectSingleNode(xpath);
        Element  transactionManager = nodeEnvironment.element("transactionManager"); //获取Element下的指定孩子
        String transactionManagerType = transactionManager.attributeValue("type");
        System.out.println("transactionManager的内容为："+transactionManagerType);

        /*
        * 获取指定节点下的 所有子节点
        * */
        Element  dataSource = nodeEnvironment.element("dataSource"); //获取Element下的指定孩子
        List<Element> propertyLists  =  dataSource.elements();
        //遍历所有的子节点
        propertyLists.forEach(propertyList->{
            String name = propertyList.attributeValue("name");
            String value = propertyList.attributeValue("value");
            System.out.println("name: "+name+"   value: "+value);
        });
    }

    /*
    * 读取CarMapper
    * */
    @Test
    public void xbatis_parse_xml_mpper_01_test() throws Exception{
        String xpath = "/mapper";
        SAXReader saxReader =new SAXReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("CarMapper.xml");
        Document document =saxReader.read(is);
        Element mapper = (Element) document.selectSingleNode(xpath);
        String namespace = mapper.attributeValue("namespace"); //获取namespace属性内容
        System.out.println(namespace);
        //获取sql语句，转译为jdbc格式
        List<Element> elementsSqls = mapper.elements();
        elementsSqls.forEach(elementsSql->{
            String id = elementsSql.attributeValue("id");
            String resultType = elementsSql.attributeValue("resultType");
            //获取sql语句，node的内容
            String sql = elementsSql.getTextTrim();
            System.out.println("id: "+id);
            //insert into t_car (car_num,brand,guide_price,produce_time,car_type) values
            // (#{carNum},#{brand},#{guidePrice},#{produceTime},
            System.out.println(sql);
            //转译
            String jdbcSql = sql.replaceAll("#\\{[0-9A-Za-z_$]*}","?"); //正则匹配$表示字符串结尾，\\转义字符
            System.out.println(jdbcSql);
        });
    }
}
