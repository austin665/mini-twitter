package challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import challenge.dao.UserDao;
import challenge.model.People;
import challenge.model.PopularUser;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Class which acts as the end points for all the controllers.
 */
@RestController
public class FollowersController {

    @Autowired
    private UserDao userDao;

    /**
     * Method to get the list of all followers for the current user.
     *
     * @param request : The HTTPRequest object
     * @return : a <code> List </code> of current user's followers
     */
    @RequestMapping(value = "/followers", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<People> getUserFollowers(HttpServletRequest request) {
        String userId = userDao.getUserHandle(request);
        return userDao.getUserFollowersList(userId);
    }

    /**
     * <p>
     * Method to get the list of all users the current user is following
     *
     * @param request : The HTTPRequest object
     * @return : a <code> List </code> of users the current user is following
     */
    @RequestMapping(value = "/following", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<People> getUserFollowing(HttpServletRequest request) {
        String userId = userDao.getUserHandle(request);
        return userDao.getUserFollowingList(userId);
    }

    /**
     * <p>
     * Method to start following a new user. 
     * It inserts a record to the followers table with the user ids.
     *
     * @param request : The HTTPRequest object
     */
    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    public void startFollowingUser(HttpServletRequest request, @RequestParam(value = "person") String personHandle) {
        String userId = userDao.getUserHandle(request);
        userDao.addFollower(userId, personHandle);
    }

    /**
     * <p>
     * Method to unfollow a user from the users the current user is following based on the user id. 
     * Deletes a record in the followers table where a mapping already exists. 
     * @param request : The HTTPRequest object
     */
    @RequestMapping(value = "/unfollow", method = RequestMethod.GET)
    public void removeUserFollowing(HttpServletRequest request, @RequestParam(value = "person") String personHandle) {
        String userId = userDao.getUserHandle(request);
        userDao.deleteUserFollowing(userId, personHandle);
    }

    /**
     * <p>
     * Method to get the list of all the users followed by their popular user.
     * @return : a <code> List </code> of all the users with their popular users.
     */
    @RequestMapping(value = "/popular-users", method = RequestMethod.GET)
    public List<PopularUser> popularUsers(HttpServletRequest request) {
        return userDao.findPopularUsers();
    }
}
