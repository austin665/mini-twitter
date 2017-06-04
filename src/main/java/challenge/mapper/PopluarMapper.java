package challenge.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import challenge.model.PopularUser;

public class PopluarMapper implements RowMapper<PopularUser>{
	
	/**
     * Mapper method to map the user data to the User object.
     *
     * @param rs     : ResultSet object based on the database.
     * @param rowNum : Row Number the row number of record
     * @return : A User model
     * @throws SQLException if error in retrieving data from h2
     */
    @Override
    public PopularUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        PopularUser user = new PopularUser();
        user.setUserId(rs.getInt("id"));
        user.setFollowerUserId(rs.getInt("follower_id"));
        return user;
    }
}
