package com.asso.dao;


import com.asso.model.Score;
import com.asso.model.ScoreExamItem;
import com.asso.model.ScoreExamRef;


public interface ScoreDao {
	public void save(Score score);
	public void save(ScoreExamItem scoreexamitem);
	public void save(ScoreExamRef scoreexamref);
//	public List<User> loadAllUsers();
//	public boolean checkUserExistsWithName(String username);
//	public int checkUserExistsWithNamePassword(User user);
//	/**Results---
//	 * 1.exists
//	 * 2.name wrong
//	 * 3.only password wrong
//	 * */
//	public void saveMember(User user);	
//	public int getUserIdWithName(User user);
//	public void saveMemberInfo(int memid, User user);
//	public int getMemberIdWithUserId(int userid);
//	public void updateMemberInfoWithId(MemberInfo minfo);
//	public List<MemberInfo> loadMemberInfoWithUserId(User user);
//	
//	
	void update(Score score);
	int getScoreId(Score score);
	
}
