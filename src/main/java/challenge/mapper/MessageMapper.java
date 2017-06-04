package challenge.mapper;

import org.springframework.jdbc.core.RowMapper;

import challenge.model.Message;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements RowMapper<Message> {
	/**
     * Mapper method to map the tweet data to the Tweet object.
     *
     * @param rs     : ResultSet object based on the database.
     * @param rowNum : Row Number the row number of record
     * @return : A Tweet model
     * @throws SQLException if error in retrieving data from h2
     */
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message tweet = new Message();
        tweet.setId(rs.getInt("id"));
        tweet.setPerson_id(rs.getInt("person_id"));
        tweet.setContent(rs.getString("content"));
        return tweet;
    }
}