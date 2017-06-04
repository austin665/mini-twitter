package challenge.mapper;

import org.springframework.jdbc.core.RowMapper;

import challenge.model.Follower;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowersMapper implements RowMapper<Follower> {

    /**
     * Mapper method to map object from followers to user.
     *
     * @param rs     : ResultSet object based on the database.
     * @param rowNum : Row Number the row number of record
     * @return : A Follower POJO
     * @throws SQLException if error in retrieving data from h2
     */
    @Override
    public Follower mapRow(ResultSet rs, int rowNum) throws SQLException {
        Follower followers = new Follower();
        followers.setId(rs.getInt("id"));
        followers.setPerson_id(rs.getInt("person_id"));
        followers.setFollower_person_id(rs.getInt("follower_person_id"));
        return followers;
    }
}
