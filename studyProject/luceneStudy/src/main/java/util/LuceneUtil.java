package util;

import entity.Article;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Paths;

/**
 * 工具类
 */
public class LuceneUtil {

    private static Directory directory;
    private static Analyzer analyzer;
    private static IndexWriterConfig indexWriterConfig;

    static {
        try {
            directory =  FSDirectory.open(Paths.get("D:/IndexDBDBDB"));
            analyzer = new StandardAnalyzer();
            indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //不让外界new该帮助类
    private LuceneUtil(){}

    //将JavaBean转为Document对象
    public static Document javaBean2Document(Object obj)throws Exception{
        //创建document对象
        Document document = new Document();
        //获取obj字节码对象
        Class objClass = obj.getClass();
        //通过字节码对象获取私有属性
        Field[] fields = objClass.getDeclaredFields();
        //迭代
        for (Field field : fields) {
            //强力反射
            field.setAccessible(true);
            //获取属性名
            String name = field.getName();
            //人工拼接方法名
            String methodName = "get" + name.substring(0,1).toUpperCase() + name.substring(1);
            //获取方法
            Method method = objClass.getMethod(methodName, null);
            //执行方法
            String value = method.invoke(obj, null).toString();
            //加入到Document对象中去，这时javabean对象与document对象属性相同
            document.add(new TextField(name,value, org.apache.lucene.document.Field.Store.YES));
        }
        return document;
    }


    /**
     * document对象转Javabean对象
     * @param document
     * @return
     */
    public static Object document2javaBean(Document document,Class clazz)throws Exception{
        Object obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            String value = document.get(name);
            //封装javabean对应的属性中去，通过set方法
            BeanUtils.setProperty(obj,name,value);
        }
        return obj;
    }


    public static void main(String[] args) {
        try {
            Article article = new Article(2,"水果","香蕉是一种水果");
            Document document = LuceneUtil.javaBean2Document(article);

            System.out.println("===========================");

            Article a = (Article) LuceneUtil.document2javaBean(document, Article.class);
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Directory getDirectory() {
        return directory;
    }

    public static void setDirectory(Directory directory) {
        LuceneUtil.directory = directory;
    }

    public static Analyzer getAnalyzer() {
        return analyzer;
    }

    public static void setAnalyzer(Analyzer analyzer) {
        LuceneUtil.analyzer = analyzer;
    }

    public static IndexWriterConfig getIndexWriterConfig() {
        return indexWriterConfig;
    }

    public static void setIndexWriterConfig(IndexWriterConfig indexWriterConfig) {
        LuceneUtil.indexWriterConfig = indexWriterConfig;
    }
}
