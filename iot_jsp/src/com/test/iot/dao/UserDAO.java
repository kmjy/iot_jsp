package com.test.iot.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserDAO {
	public ArrayList<HashMap<String,Object>> selectUserList();
	public HashMap<String,Object> selectUser();
	
	
	
	int insertUser(Connection con, HashMap<String, String> user);
}


