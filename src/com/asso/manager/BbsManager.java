package com.asso.manager;

import java.sql.SQLException;
import java.util.List;

import com.asso.model.Comment;
import com.asso.model.Topic;

public interface BbsManager {

	void add(Topic _topic) throws ClassNotFoundException, SQLException;

	void add(Comment _comment) throws ClassNotFoundException, SQLException;

	void update(Topic _topic) throws ClassNotFoundException, SQLException;

	void update(Comment _comment) throws ClassNotFoundException, SQLException;

	List<Topic> loadTopics() throws ClassNotFoundException, SQLException;

	List<Topic> loadTopicsWithComments() throws ClassNotFoundException,
			SQLException;

	void delete(Topic _topic) throws ClassNotFoundException, SQLException;

	void delete(Comment _comment) throws ClassNotFoundException, SQLException;

	int getCommentsCountByTopicId(int _topicid) throws ClassNotFoundException,
			SQLException;

	Topic loadTopicWithCommentsByTopicId(int _topicid)
			throws ClassNotFoundException, SQLException;

	Topic loadTopicById(int _topicid) throws ClassNotFoundException,
			SQLException;

	Comment loadCommentById(int _commentid) throws ClassNotFoundException,
			SQLException;

	List<Comment> loadCommentsByTopicId(int _topicid)
			throws ClassNotFoundException, SQLException;

	

}
