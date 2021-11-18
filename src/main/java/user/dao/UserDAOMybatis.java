package user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import user.bean.UserDTO;

@Repository
@Transactional
public class UserDAOMybatis implements UserDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDTO checkId(String id) {
		return sqlSession.selectOne("userSQL.getUser", id);
	}

	@Override
	public void write(UserDTO userDTO) {
		sqlSession.insert("userSQL.write", userDTO);
	}

	@Override
	public List<UserDTO> getUserList() {
		List<UserDTO> list = sqlSession.selectList("userSQL.getUserList");
		return list;
		
	}

	@Override
	public UserDTO getUser(String searchId) {
		return sqlSession.selectOne("userSQL.getUser", searchId);
	}

	@Override
	public void modify(UserDTO userDTO) {
		sqlSession.update("userSQL.modify", userDTO);
	}

	@Override
	public void delete(String searchId) {
		sqlSession.delete("userSQL.delete", searchId);
	}
	
}
