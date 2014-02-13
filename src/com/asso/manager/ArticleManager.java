package com.asso.manager;

import java.sql.SQLException;
import java.util.List;

import com.asso.model.Article;
import com.asso.model.Message;

public interface ArticleManager {

	void add(Article article) throws ClassNotFoundException, SQLException;

	List<Article> loadArticles(int categoryid) throws ClassNotFoundException,
			SQLException;

	List<Article> loadArticle(int articleid) throws ClassNotFoundException,
			SQLException;

	void update(Article article) throws ClassNotFoundException, SQLException;

	List<Article> loadArticles() throws ClassNotFoundException, SQLException;

	void deleteArticle(int articleid) throws ClassNotFoundException,
			SQLException;

	List<Message> loadMessages(int userid) throws ClassNotFoundException,
			SQLException;

	void update(Message message) throws ClassNotFoundException, SQLException;

	void add(Message message) throws ClassNotFoundException, SQLException;

}
