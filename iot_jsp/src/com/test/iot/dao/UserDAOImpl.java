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
			ps = DBCon.getCon().prepareStatement(sql);   //prepare 이 sql 의 문자열을 받아서 갖고 있는다  
			rs = ps.executeQuery();     // prepare가 갖고 있는 쿼리문 실행 후 resultSet 에 값을 저장해두기 //결과값을 받아와야 할경우 (select) //excuteUpdate 결과값을 받아오지 않아도 될때 (delete , insert ,Update)
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

	
	///insert 
	
	public int insertUser(Connection con,HashMap<String, String> user) {
		String sql = "insert into user_info(uiid, uiname, uiage, uipwd, cino, uiregdate, address)";
		sql += " values(?,?,?,?,1,now(),?)";
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, user.get("uiid"));
			ps.setString(2, user.get("uiname"));
			ps.setString(3, user.get("uiage"));
			ps.setString(4, user.get("uipwd"));
			ps.setString(5, user.get("address"));
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
