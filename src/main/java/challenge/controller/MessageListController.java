package challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import challenge.dao.UserDao;
import challenge.model.Message;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Controller which handles the endpoints for the tweets of the user.
 */
@RestController
public class MessageListController {

    @Autowired
    private UserDao userDao;

    /**
     * <p>
     * Method to get the current users tweets as well as search for a tweet based on the parameter. 
     *
     * @param request : The HTTPRequest object
     * @return : a <code>List</code> object of all the tweets
     */
    @RequestMapping(value = "/tweets", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getTweets(HttpServletRequest request, @RequestParam(value = "search", required = false) String searchKey) {
        String userId = userDao.getUserHandle(request);
        return userDao.getUserMessages(userId, searchKey);
    }
}