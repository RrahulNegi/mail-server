package com.mailservice.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mailservice.bean.MailBean;


@Repository("mailDao")
public class MailDaoImpl implements MailDao{

	@Autowired
	MongoOperations mongoOperation;
	@Override
	public MailBean getMailData(int id) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("mailId").is(id));
		MailBean mailData= mongoOperation.findOne(query,MailBean.class);
		return mailData;
	}

	

}
