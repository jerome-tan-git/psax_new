package com.asso.dao;

import java.util.List;

import com.asso.model.MemberInfo;
import com.asso.model.Uploadfilefolders;
import com.asso.model.Uploadfiles;
import com.asso.model.User;

public interface UserDao {
	public void save(User user);
//	public List<User> loadAllUsers();
//	public boolean checkUserExistsWithName(String username);
	public int checkUserExistsWithNamePassword(User user);
	/**Results---
	 * 1.exists
	 * 2.name wrong
	 * 3.only password wrong
	 * */
	public void saveMember(User user);	
	public int getUserIdWithName(User user);
	public void saveMemberInfo(int memid, User user);
	public int getMemberIdWithUserId(int userid);
	public void updateMemberInfoWithId(MemberInfo minfo);
	public List<MemberInfo> loadMemberInfoWithUserId(User user);
	User loadUserWithNamePassword(User _user);
	List<Uploadfiles> loadUploadfilesByUserId(int _userid);
	void save(Uploadfiles _uploadfiles);
	void delete(Uploadfiles _uploadfiles);
	User loadUser(int _userid);
	void update(User user);
	List<User> loadAllUsers();
	void delete(User user);
	List<Uploadfilefolders> loadAllUploadfilefolders();
	void save(Uploadfilefolders _upff);
	void delete(Uploadfilefolders _upff);
	List<Uploadfiles> loadUploadfilesByFolderId(int _folderid);
	List<Uploadfiles> loadUploadfilesByFolderIdUserId(int _folderid, int _userid);
	
	
	
}
