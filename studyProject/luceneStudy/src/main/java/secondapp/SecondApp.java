package secondapp;

import entity.Article;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;
import util.LuceneUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 重构FirstAPP
 */
public class SecondApp {

    /**
     * 创建索引库
     */
    @Test
    public void createIndexDB()throws Exception{
        Article article = new Article(5,"动物","海豚是一种动物");
        Article article1 = new Article(6,"动物","大象是一种动物");
        Document document = LuceneUtil.javaBean2Document(article);
        Document document1 = LuceneUtil.javaBean2Document(article1);
        IndexWriter  indexWriter = new IndexWriter(LuceneUtil.getDirectory(),LuceneUtil.getIndexWriterConfig());
        indexWriter.addDocument(document);
        indexWriter.addDocument(document1);
        indexWriter.close();
    }

    /**
     * 根据关键字从索引库中查询符合条件的数据
     */
    @Test
    public void findIndexDB()throws Exception{
        String keyword = "动物";
        List<Article> articles = new ArrayList<Article>();
        QueryParser queryParser = new QueryParser("content",LuceneUtil.getAnalyzer());
        Query query = queryParser.parse(keyword);
        IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(LuceneUtil.getDirectory()));
        TopDocs topDocs = indexSearcher.search(query, 100);
        for (int i = 0; i < topDocs.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = topDocs.scoreDocs[i];
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            Article article = (Article) LuceneUtil.document2javaBean(document,Article.class);
            articles.add(article);
        }
        System.out.println(articles);
    }




}
