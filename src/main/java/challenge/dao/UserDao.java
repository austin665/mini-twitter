package challenge.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import challenge.model.Message;
import challenge.model.People;
import challenge.model.PopularUser;

public interface UserDao {
		
		/**
		  * This is a helper function to get the user handle from the request
		  *
		  * @param request : The HTTPRequest
		  * @return : The user handle
		*/
		String getUserHandle(HttpServletRequest request);

	    /**
	     * <p>
	     * This method returns the messages from the database for the specified user 
	     * as well as for the users following.
	     * It also supports the search key.
	     * 
	     * @param handle : The current user's handle
	     * @param searchkey : The search key based on the request.
	     * @return : The list of messages
	     */
	    List<Message> getUserMessages(String handle, String searchkey);

	    /**
	     * <p>
	     * This method returns the current user's followers from the database
	     *
	     * @param handle : The current user's handle
	     * @return : The list of person objects.
	     */
	    List<People> getUserFollowersList(String handle);


	    /**
	     * This method returns the people the current user is following from the database
	     *
	     * @param handle : The current user's handle
	     * @return : The list of person objects.
	     */
	    List<People> getUserFollowingList(String handle);

	    /**
	     * <p>
	     * This method inserts a row to the followers database with two ids.
	     *
	     * @param handle : The current user's handle
	     * @param personId : The handle of the target person to follow.
	     */
	    void addFollower(String handle, String person);
	    
	    /**
	     * <p>
	     * This method deletes record from the followers.
	     *
	     * @param handle : The current user's handle
	     * @param personId : The handle of the target person to unfollow.
	     */
	    void deleteUserFollowing(String handle, String personId);
	    
	    /**
	     * Method to find the popular users.
	     *
	     * @return : The list of users and their followers.
	     */
	    List<PopularUser> findPopularUsers();
}
