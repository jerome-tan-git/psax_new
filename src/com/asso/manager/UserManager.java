package com.asso.manager;

import java.sql.SQLException;
import java.util.List;

import com.asso.model.MemberCenterColumn;
import com.asso.model.MemberInfo;
import com.asso.model.Uploadfilefolders;
import com.asso.model.Uploadfiles;
import com.asso.model.User;

public interface UserManager {

	public abstract int exists(User user) throws ClassNotFoundException,
			SQLException;

	public abstract void add(User user) throws ClassNotFoundException,
			SQLException;

	public void addMember(User user) throws ClassNotFoundException, SQLException;

	public void editMember(MemberInfo minfo) throws ClassNotFoundException,
			SQLException;

	public List<MemberInfo> loadMember(User user) throws ClassNotFoundException, SQLException;

	public int getUserId(User user);

	User loadUser(User user) throws ClassNotFoundException, SQLException;

	void addUploadfiles(Uploadfiles uploadfile) throws ClassNotFoundException,
			SQLException;

	List<Uploadfiles> loadUploadedFilesByUserid(int _userid)
			throws ClassNotFoundException, SQLException;

	void deleteUploadedFile(int _uploadedfileid) throws ClassNotFoundException,
			SQLException;

	void update(User user) throws ClassNotFoundException, SQLException;

	List<User> loadusers() throws ClassNotFoundException, SQLException;

	void delete(int userid) throws ClassNotFoundException, SQLException;

	User loadUserByid(int _userid) throws ClassNotFoundException, SQLException;

	List<Uploadfilefolders> loadUploadeFileFolders()
			throws ClassNotFoundException, SQLException;

	void addUploadfolder(Uploadfilefolders upff) throws ClassNotFoundException,
			SQLException;

	void deleteUploadfolder(Uploadfilefolders upff)
			throws ClassNotFoundException, SQLException;

	List<Uploadfiles> loadUploadedFilesByFolderid(int _folderid)
			throws ClassNotFoundException, SQLException;

	List<Uploadfiles> loadUploadedFilesByFolderidUserid(int _folderid,
			int _userid) throws ClassNotFoundException, SQLException;

	List<MemberCenterColumn> loadMemberCenterColumns()
			throws ClassNotFoundException, SQLException;

	void updateMemberCenterColumn(MemberCenterColumn _mcc)
			throws ClassNotFoundException, SQLException;




}