package com.test.iot.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.iot.common.DBCon;
import com.test.iot.dao.UserDAO;
import com.test.iot.dao.UserDAOImpl;

public class UserServiceImpl implements UserService { // 1 콘쉽오 해주기
	private UserDAO ud = new UserDAOImpl();

	@Override
	public ArrayList<HashMap<String, Object>> getUserList() {
		Connection con = DBCon.getCon();
		ArrayList<HashMap<String, Object>> userList = ud.selectUserList();

		return userList;
	}

	@Override
	public HashMap<String, Object> getUser() {

		return null;
	}

	/// insert

	public int insertUser(HashMap<String, String> user) {

		Connection con = DBCon.getCon();
		int result = 0;
		result = ud.insertUser(con, user);
		try {
			con.commit();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con = null;
		return result;
	}

}
