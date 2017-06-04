package challenge.dao;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import challenge.mapper.FollowersMapper;
import challenge.mapper.MessageMapper;
import challenge.mapper.PopluarMapper;
import challenge.mapper.UserMapper;
import challenge.model.Follower;
import challenge.model.Message;
import challenge.model.People;
import challenge.model.PopularUser;

@Repository
public class UserDaoImpl implements UserDao {

	    @Autowired
	    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	    /**
	     * {@inheritDoc}
	     */
	    public String getUserHandle(HttpServletRequest request) {
	        byte[] decodedBytes = Base64.getDecoder().decode(request.getHeader("Authorization").split(" ")[1]);
	        String authorizationContents = new String(decodedBytes);
	        return authorizationContents.split(":")[0];
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Transactional
		public List<Message> getUserMessages(String handle, String searchParameter) {
	    	
	    	
	        StringBuilder query = new StringBuilder("select distinct t.* from messages t inner join followers f on t.person_id = f.person_id where (t.person_id = :userId or (t.person_id = f.person_id and f.follower_person_id = :userId))");
	        
	        String personId = Integer.toString(getPersonIdFromHandle(handle));
	        
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("userId", personId);

	        if (searchParameter != null && !searchParameter.trim().isEmpty()) {
	            query.append("and t.content like :searchParameter");
	            params.put("searchParameter", "%" + searchParameter + "%");
	        }
	        
	        SqlParameterSource sqlParameters = new MapSqlParameterSource(params);
	        return this.namedParameterJdbcTemplate.query(query.toString(), sqlParameters, new MessageMapper());
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Transactional
		public List<People> getUserFollowersList(String handle) {
	        String query = "select p.* from people p, followers f where p.id = f.follower_person_id and f.person_id = :userId";
	        String personId = Integer.toString(getPersonIdFromHandle(handle));
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("userId", personId);
	        SqlParameterSource sqlParameters = new MapSqlParameterSource(params);
	        return this.namedParameterJdbcTemplate.query(query, sqlParameters, new UserMapper());
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Transactional
		public List<People> getUserFollowingList(String handle) {
	        String query = "select p.* from people p, followers f where p.id = f.person_id and f.follower_person_id = :userId";
	        String personId = Integer.toString(getPersonIdFromHandle(handle));
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("userId", personId);
	        SqlParameterSource sqlParameters = new MapSqlParameterSource(params);
	        return this.namedParameterJdbcTemplate.query(query, sqlParameters, new UserMapper());
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Transactional 
	    public void addFollower(String followerPersonHandle, String handle) {
	    	Integer personId = getPersonIdFromHandle(handle);
	    	Integer followerPersonId = getPersonIdFromHandle(followerPersonHandle);
	        
	    	//Check to not follow himself
	    	if(followerPersonId == personId)
	    		return;
	        Map<String, Integer> params = new HashMap<String, Integer>();
	        params.put("personId", personId);
	        params.put("followerPersonId", followerPersonId);
	        SqlParameterSource sqlParameters = new MapSqlParameterSource(params);

	        String existing_query = "Select f.* from followers f where f.person_id = :personId and f.follower_person_id = :followerPersonId limit 1";
	        List<Follower> existingRow = this.namedParameterJdbcTemplate.query(existing_query, sqlParameters, new FollowersMapper());
	        if (existingRow == null || existingRow.size() == 0) {
	            String query = "Insert into followers (person_id, follower_person_id) values (:personId, :followerPersonId)";
	            this.namedParameterJdbcTemplate.update(query, sqlParameters);
	        }
	    }

	    /**
	     * <p>
	     * Method to remove a record from the table based on the user ids
	     *
	     * @param followerPersonHandle : The handle of the follower
	     * @param personId : The target id
	     */
	    @Transactional
		public void deleteUserFollowing(String followerPersonHandle, String handle) {
	    	int personId = getPersonIdFromHandle(handle);
	    	int followerPersonId = getPersonIdFromHandle(followerPersonHandle);
	        
	        String query = "Delete from followers where person_id = :personId and follower_person_id = :followerPersonId";
	        Map<String, Integer> paramsMap = new HashMap<String, Integer>();
	        paramsMap.put("personId", personId);
	        paramsMap.put("followerPersonId", followerPersonId);
	        SqlParameterSource sqlParams = new MapSqlParameterSource(paramsMap);
	        this.namedParameterJdbcTemplate.update(query, sqlParams);
	    }

	    /**
	     * {@inheritDoc}
	     */
	    @Transactional
		public List<PopularUser> findPopularUsers() {
	    	
			String sql = "select a.id, b.follower_id, count from(select id, max(fcount) count from "
					+ "(select c.person_id id,d.person_id follower_id,count(d.follower_person_id) fcount "
					+ "from followers c, followers d where c.follower_person_id = d.person_id "
					+ "group by c.person_id,d.person_id order by fcount desc) group by id ) a , "
					+ "(select c.person_id id,d.person_id follower_id,count(d.follower_person_id) fcount from followers c, "
					+ "followers d where c.follower_person_id = d.person_id group by c.person_id,d.person_id order by fcount desc) b "
					+ "where a.id=b.id and a.count = b.fcount";
			return namedParameterJdbcTemplate.query(sql, new PopluarMapper());
	    }
	    
	    /**
	     * Helper method to retrieve the personId based on the handle
	     * @param handle the user handle
	     * @return the personId
	     */
		private int getPersonIdFromHandle(String handle) {
			Map<String, String> parametersMap = new HashMap<String, String>();
			parametersMap.put("handle", handle);
			SqlParameterSource sqlParams = new MapSqlParameterSource(parametersMap);
			
			int personId = this.namedParameterJdbcTemplate.queryForObject("select id from people where handle = :handle limit 1", sqlParams, Integer.class);
			return personId;
		}
	}
