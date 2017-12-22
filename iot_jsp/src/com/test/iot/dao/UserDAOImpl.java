package com.test.iot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.iot.common.DBCon;

public class UserDAOImpl implements UserDAO {

	@Override
	public ArrayList<HashMap<String, Object>> selectUserList() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, Object>> userList = new ArrayList<HashMap<String, Object>>();
		String sql = "select * from user_info";

		try {
			ps = DBCon.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				HashMap<String, Object> hm = new HashMap<String, Object>();
				hm.put("userno", rs.getString("userno"));
				hm.put("username", rs.getString("username"));
				hm.put("userage", rs.getString("userage"));
				hm.put("userid", rs.getString("userid"));
				hm.put("userpwd", rs.getString("userpwd"));

				userList.add(hm);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return userList;
	}

	@Override
	public HashMap<String, Object> selectUser() {

		return null;
	}

}
