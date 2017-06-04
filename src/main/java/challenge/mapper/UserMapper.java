package challenge.mapper;

import org.springframework.jdbc.core.RowMapper;

import challenge.model.People;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class UserMapper implements RowMapper<People> {
	
	/**
     * Mapper method to map the user data to the User object.
     *
     * @param rs     : ResultSet object based on the database.
     * @param rowNum : Row Number the row number of record
     * @return : A User model
     * @throws SQLException if error in retrieving data from h2
     */
    @Override
    public People mapRow(ResultSet rs, int rowNum) throws SQLException {
        People user = new People();
        user.setId(rs.getInt("id"));
        user.setHandle(rs.getString("handle"));
        user.setName(rs.getString("name"));
        return user;
    }
}