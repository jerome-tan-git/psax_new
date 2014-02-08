package com.asso.manager.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.asso.dao.FormDao;
import com.asso.manager.FormManager;
import com.asso.model.Article;
import com.asso.model.Fields;
import com.asso.model.Form;

@Component("formManager")
public class FormManagerImpl implements FormManager {

	//For WEB test
	private FormDao formDao ;
		
	
	public FormDao getFormDao() {
		return formDao;
	}
	@Resource(name="formDao")
	public void setFormDao(FormDao formDao) {
		this.formDao = formDao;
	}
	
	@Override
	public void addForm(Form _form) throws ClassNotFoundException, SQLException {		
		formDao.saveForm(_form);				
	}
	@Override
	public void updateForm(Form _form) throws ClassNotFoundException, SQLException {		
		formDao.updateForm(_form);		
	}
//	
//	@Override
//	public List<Article> loadArticles(int categoryid) throws ClassNotFoundException, SQLException{
//		return articleDao.loadArticles(categoryid);		
//	}
	
	
	@Override
	public List<Form> loadForms() throws ClassNotFoundException, SQLException{		
		return formDao.loadAllForms();
	}
	@Override
	public List<Form> loadValidForms() throws ClassNotFoundException, SQLException{		
		return formDao.load2showForms();
	}
	@Override
	public Form loadFormById(int _formid) throws ClassNotFoundException, SQLException{		
		return formDao.loadForm(_formid);
	}
	@Override
	public Form loadFormWithFieldsById(int _formid) throws ClassNotFoundException, SQLException{
		Form f = formDao.loadForm(_formid);
		f.setFields(formDao.loadFieldsByFormId(_formid));
		return f;
	}
//
//	@Override
//	public List<Article> loadArticle(int articleid) throws ClassNotFoundException, SQLException{
//		return articleDao.loadArticle(articleid);	
//	}
//	
//	@Override
//	public void deleteArticle(int articleid) throws ClassNotFoundException, SQLException{
//		Article art = new Article();
//		art.setId(articleid);
//		articleDao.delete(art);
//	}
//	
	
}
