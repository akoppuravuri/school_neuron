package com.sn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sn.common.WebContextHolder;
import com.sn.service.PostService;


/**
 * The Class UserSelfServiceController is the major controller for self-service module.
 */
@Controller
@RequestMapping(value = "/sn")
public class PostController {
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	private PostService postService;
	
	public MessageSource getMessages() {
		return messages;
	}
	
	@Value("${profilePhotoRealPath}")
	private String porfilePhotoPath;
	
	@RequestMapping(value = "/submitPost", method = RequestMethod.POST)
	public @ResponseBody String submitPost(ModelMap model,@RequestParam(value = "post") String post,
			@RequestParam(value = "postClass") String postClass,
			@RequestParam(value = "type") String type) {
		return postService.submitPost(post, Integer.parseInt(postClass), type);
	}
	
	@RequestMapping(value = "/addLike", method = RequestMethod.POST)
	public @ResponseBody String addLike(ModelMap model,@RequestParam(value = "postId") Integer postId,
			@RequestParam(value = "likeCount") Integer likeCount) {
		return postService.addLike(postId, likeCount, WebContextHolder.get().getLoggedInUser().getUid());
	}
	
	@RequestMapping(value = "/submitComment", method = RequestMethod.POST)
	public @ResponseBody String submitComment(ModelMap model,@RequestParam(value = "postId") Integer postId,
			@RequestParam(value = "comment") String comment,
			@RequestParam(value = "commentCount") Integer commentCount) {
		return postService.submitComment(postId, comment, commentCount, WebContextHolder.get().getLoggedInUser().getUid());
	}
	
	@RequestMapping(value = "/schedulePost", method = RequestMethod.POST)
	public @ResponseBody String schedulePost(ModelMap model,@RequestParam(value = "post") String post,
			@RequestParam(value = "postClass") String postClass,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "date") String date,
			@RequestParam(value = "time") String time) {
		return postService.schedulePost(post, Integer.parseInt(postClass), type, date, time);
	}
}
