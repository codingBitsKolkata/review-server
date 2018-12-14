package com.orastays.review.reviewserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.orastays.review.reviewserver.helper.MessageUtil;
import com.orastays.review.reviewserver.service.ReviewService;

public class BaseController {

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;
	
	@Autowired
	protected ReviewService reviewService;
	
	@Autowired
	protected MessageUtil messageUtil;
}
