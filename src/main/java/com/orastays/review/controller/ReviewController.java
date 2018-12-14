package com.orastays.review.controller;

import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.review.reviewserver.exceptions.FormExceptions;
import com.orastays.review.reviewserver.helper.ReviewConstant;
import com.orastays.review.reviewserver.helper.Util;
import com.orastays.review.reviewserver.model.ResponseModel;
import com.orastays.review.reviewserver.model.UserReviewModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Review", description = "Rest API for Adding Review", tags = "Review")
public class ReviewController extends BaseController {
	
private static final Logger logger = LogManager.getLogger(ReviewController.class);
	
	@PostMapping(value = "/add-review", produces = "application/json")
	@ApiOperation(value = "Add Review", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 202, message = "Token Required"),
			@ApiResponse(code = 203, message = "Token Expires!!!Please login to continue..."),
			@ApiResponse(code = 204, message = "Language Id Required"),
			@ApiResponse(code = 205, message = "Invalid Language ID") })
	public ResponseEntity<ResponseModel> addReview(@RequestBody UserReviewModel userReviewModel) {
	
		if (logger.isInfoEnabled()) {
			logger.info("addReview -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		
		try {
			UserReviewModel userReviewModel2 = reviewService.addReview(userReviewModel);
			responseModel.setResponseBody(userReviewModel2);
			responseModel.setResponseCode(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {
			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseModel.setResponseCode(messageUtil.getBundle(ReviewConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(ReviewConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, ReviewConstant.OUTGOING, "Add Review", request);

		if (logger.isInfoEnabled()) {
			logger.info("addReview -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
