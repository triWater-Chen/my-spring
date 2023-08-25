package com.chen.myspring.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.beans.factory.config.BeanReference;
import com.chen.myspring.beans.factory.support.AbstractBeanDefinitionReader;
import com.chen.myspring.beans.factory.support.BeanDefinitionRegistry;
import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.beans.others.PropertyValue;
import com.chen.myspring.context.annotation.ClassPathBeanDefinitionScanner;
import com.chen.myspring.core.io.Resource;
import com.chen.myspring.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Chen
 * @description 读取配置在 xml 文件中的 bean 定义信息的实现类
 * @create 2023-08-16
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";
    public static final String SCOPE_ATTRIBUTE = "scope";
    public static final String LAZYINIT_ATTRIBUTE = "lazyInit";
    public static final String BASE_PACKAGE_ATTRIBUTE = "base-package";
    public static final String COMPONENT_SCAN_ELEMENT = "component-scan";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | DocumentException ex) {
            throw new BeansException("IOException parsing XML document from " + resource, ex);
        }
    }

    /**
     * 改用 dom4j 解析 xml 文件
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);

        Element root = document.getRootElement();

        // 解析 context:component-scan 标签并扫描指定包中的类，提取类信息，组装成 BeanDefinition
        Element componentScan = root.element(COMPONENT_SCAN_ELEMENT);
        if (componentScan != null) {
            String scanPath = componentScan.attributeValue(BASE_PACKAGE_ATTRIBUTE);
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        List<Element> beanList = root.elements(BEAN_ELEMENT);
        for (Element bean : beanList) {
            String beanId = bean.attributeValue(ID_ATTRIBUTE);
            String beanName = bean.attributeValue(NAME_ATTRIBUTE);
            String className = bean.attributeValue(CLASS_ATTRIBUTE);
            String initMethodName = bean.attributeValue(INIT_METHOD_ATTRIBUTE);
            String destroyMethodName = bean.attributeValue(DESTROY_METHOD_ATTRIBUTE);
            String beanScope = bean.attributeValue(SCOPE_ATTRIBUTE);
            String lazyInit = bean.attributeValue(LAZYINIT_ATTRIBUTE);

            Class<?> clazz;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new BeansException("Cannot find class [" + className + "]");
            }
            // id 优先于 name
            beanName = StrUtil.isNotEmpty(beanId) ? beanId : beanName;
            if (StrUtil.isEmpty(beanName)) {
                // 如果 id 和 name 都为空，将类名的第一个字母转为小写后作为 bean 的名称
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            beanDefinition.setLazyInit(Boolean.parseBoolean(lazyInit));
            if (StrUtil.isNotEmpty(beanScope)) {
                beanDefinition.setScope(beanScope);
            }

            List<Element> propertyList = bean.elements(PROPERTY_ELEMENT);
            for (Element property : propertyList) {
                String propertyNameAttribute = property.attributeValue(NAME_ATTRIBUTE);
                String propertyValueAttribute = property.attributeValue(VALUE_ATTRIBUTE);
                String propertyRefAttribute = property.attributeValue(REF_ATTRIBUTE);

                if (StrUtil.isEmpty(propertyNameAttribute)) {
                    throw new BeansException("The name attribute cannot be null or empty");
                }

                Object value = propertyValueAttribute;
                if (StrUtil.isNotEmpty(propertyRefAttribute)) {
                    value = new BeanReference(propertyRefAttribute);
                }
                PropertyValue propertyValue = new PropertyValue(propertyNameAttribute, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                // beanName 不能重名
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    /**
     * 扫描注解 Component 的类，提取信息，组装成 BeanDefinition
     */
    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }

//    protected void doLoadBeanDefinitions(InputStream inputStream) {
//        Document document = XmlUtil.readXML(inputStream);
//        Element root = document.getDocumentElement();
//        NodeList childNodes = root.getChildNodes();
//        for (int i = 0; i < childNodes.getLength(); i++) {
//            if (childNodes.item(i) instanceof Element) {
//                if (BEAN_ELEMENT.equals(((Element) childNodes.item(i)).getNodeName())) {
//                    // 解析 bean 标签
//                    Element bean = (Element) childNodes.item(i);
//                    String id = bean.getAttribute(ID_ATTRIBUTE);
//                    String name = bean.getAttribute(NAME_ATTRIBUTE);
//                    String className = bean.getAttribute(CLASS_ATTRIBUTE);
//                    String initMethodName = bean.getAttribute(INIT_METHOD_ATTRIBUTE);
//                    String destroyMethodName = bean.getAttribute(DESTROY_METHOD_ATTRIBUTE);
//
//                    Class<?> clazz = null;
//                    try {
//                        clazz = Class.forName(className);
//                    } catch (ClassNotFoundException e) {
//                        throw new BeansException("Cannot find class [" + className + "]");
//                    }
//                    // id 优先于 name
//                    String beanName = StrUtil.isNotEmpty(id) ? id : name;
//                    if (StrUtil.isEmpty(beanName)) {
//                        // 如果 id 和 name 都为空，将类名的第一个字母转为小写后作为 bean 的名称
//                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
//                    }
//
//                    BeanDefinition beanDefinition = new BeanDefinition(clazz);
//                    beanDefinition.setInitMethodName(initMethodName);
//                    beanDefinition.setDestroyMethodName(destroyMethodName);
//
//                    for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
//                        if (bean.getChildNodes().item(j) instanceof Element) {
//                            if (PROPERTY_ELEMENT.equals(((Element) bean.getChildNodes().item(j)).getNodeName())) {
//                                // 解析 property 标签
//                                Element property = (Element) bean.getChildNodes().item(j);
//                                String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
//                                String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
//                                String refAttribute = property.getAttribute(REF_ATTRIBUTE);
//
//                                if (StrUtil.isEmpty(nameAttribute)) {
//                                    throw new BeansException("The name attribute cannot be null or empty");
//                                }
//
//                                Object value = valueAttribute;
//                                if (StrUtil.isNotEmpty(refAttribute)) {
//                                    value = new BeanReference(refAttribute);
//                                }
//                                PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
//                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
//                            }
//                        }
//                    }
//                    if (getRegistry().containsBeanDefinition(beanName)) {
//                        // beanName 不能重名
//                        throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
//                    }
//                    // 注册 BeanDefinition
//                    getRegistry().registerBeanDefinition(beanName, beanDefinition);
//                }
//            }
//        }
//    }
}
