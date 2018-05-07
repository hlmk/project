package app;

import entity.Article;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * 第一个Lucene例子
 */
public class FirstApp {


    /**
     * 创建索引库
     * 将Articel对象放入索引库中的原始记录表中，从而形成词汇表
     */
    @Test
    public void createIndexDB() throws Exception{
        //创建Article对象
        Article article = new Article(1,"水果","苹果是一种水果");
        //创建Document对象
        Document document = new Document();
        //将Article对象中的三个属性值分别绑定到document对象中
        /**
         * 参数一：document对象中的属性名叫xid，Article对象中的属性名叫id，项目中提倡相同
         * 参数二：document对象中的属性xid的值，与Article对象中相同
         * 参数三：是否将xid属性值存入由原始记录表中转入词汇表
         *           Store.YES  表示该属性值会存入词汇表
         *           Store.NO   表示该属性值不会存入词汇表
         */
        document.add(new TextField("xid",article.getId().toString(), Field.Store.YES));
        document.add(new TextField("xtitle",article.getTitle(), Field.Store.YES));
        document.add(new TextField("xcontent",article.getContent(), Field.Store.YES));

        Directory directory = FSDirectory.open(Paths.get("D:/IndexDBDBDB"));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        //创建IndexWriter字符流对象
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        indexWriter.addDocument(document);
        indexWriter.close();
    }


    /**
     * 根据关键字从索引库中搜索符合条件的内容
     */
    @Test
    public void fincIndexDB()throws Exception{
        //准备工作
        String keyWord = "水";
        List<Article> articles = new ArrayList<Article>();
        Directory directory = FSDirectory.open(Paths.get("D:/IndexDBDBDB"));
//        Version version = Version.LUCENE_7_3_0;
        Analyzer analyzer = new StandardAnalyzer();

        IndexReader indexReader = DirectoryReader.open(directory);
        //创建IndexSearcher字符流对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //创建查询解析器对象
        /**
         *  参数一：对document对象中的那个属性进行搜索
         */
        QueryParser parser = new QueryParser("xcontent", analyzer);
        Query query = parser.parse(keyWord);

        int MAX_RECORD = 100;
        //根据关键字去索引库中的词汇表搜索
        /**
         * 参数一：表示封装关键字查询对象，其他QueryParser表示查询解析器
         * 参数二：MAX_RECORD表示如果根据关键字搜索出来的结果比较多，只取前MAX_RECORD个内容，不足MAX_RECORD个数的话，以实际为准。
         */
        TopDocs search = indexSearcher.search(query, MAX_RECORD);
        //迭代词汇表中符合条件的编号
        for (int i = 0; i < search.scoreDocs.length ; i++) {
            //取出封装编号和分数的ScoreDoc对象
            ScoreDoc scoreDoc = search.scoreDocs[i];
            //取出每一个编号，例如：0,1,2
            int no = scoreDoc.doc;
            //根据编号去索引库中的原始记录表中查询对应的document对象
            Document document = indexSearcher.doc(no);
            //获取doc对象中的三个值
            String xid = document.get("xid");
            String xtitle = document.get("xtitle");
            String xcontent = document.get("xcontent");
            //封装到Article对象中
            Article article = new Article(Integer.parseInt(xid),xtitle,xcontent);
            articles.add(article);
        }
        System.out.println("====================== " + articles);
    }

}
