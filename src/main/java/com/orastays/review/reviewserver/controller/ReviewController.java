package com.orastays.review.reviewserver.controller;

import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.review.reviewserver.exceptions.FormExceptions;
import com.orastays.review.reviewserver.helper.ReviewConstant;
import com.orastays.review.reviewserver.helper.Util;
import com.orastays.review.reviewserver.model.RatingModel;
import com.orastays.review.reviewserver.model.ResponseModel;
import com.orastays.review.reviewserver.model.UserReviewModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Review", tags = "Review")
public class ReviewController extends BaseController {
	
private static final Logger logger = LogManager.getLogger(ReviewController.class);
	
	@PostMapping(value = "/add-review", produces = "application/json")
	@ApiOperation(value = "Add Review", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 202, message = "Token Required"),
			@ApiResponse(code = 203, message = "Token Expires!!!Please login to continue..."),
			@ApiResponse(code = 204, message = "Language Id Required"),
			@ApiResponse(code = 205, message = "Invalid Language ID"),
			@ApiResponse(code = 320, message = "Session expires!!! Please Login to continue..."),
			@ApiResponse(code = 321, message = "Please give User Token"),
			@ApiResponse(code = 1100, message = "Please give property Id"),
			@ApiResponse(code = 1101, message = "Invalid Property"),
			@ApiResponse(code = 1102, message = "Please give Booking Id"),
			@ApiResponse(code = 1103, message = "Booking Id invalid"),
			@ApiResponse(code = 1104, message = "Please write some comment"),
			@ApiResponse(code = 1105, message = "Please give rating"),
			@ApiResponse(code = 1106, message = "Invalid rating"),
			@ApiResponse(code = 1107, message = "Please give rating Id"),
			@ApiResponse(code = 1108, message = "Rating is not active"),
			@ApiResponse(code = 1109, message = "You have already reviewed this property"),
			@ApiResponse(code = 1110, message = "Please provide user type!!"),
			@ApiResponse(code = 1111, message = "Invalid user type!!"),})
	public ResponseEntity<ResponseModel> addReview(@RequestBody UserReviewModel userReviewModel) {
	
		if (logger.isInfoEnabled()) {
			logger.info("addReview -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userReviewModel, ReviewConstant.INCOMING, "Add Review", request);
		try {
			reviewService.addReview(userReviewModel);
			responseModel.setResponseBody(messageUtil.getBundle("review.add.success"));
			responseModel.setResponseCode(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Add Review -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Add Review -- "+Util.errorToString(e));
			}
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
	
	@PostMapping(value = "/fetch-review", produces = "application/json")
	@ApiOperation(value = "Fetch Review", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!") })
	public ResponseEntity<ResponseModel> fetchReview(@RequestBody UserReviewModel userReviewModel) {
	
		if (logger.isInfoEnabled()) {
			logger.info("fetchReview -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userReviewModel, ReviewConstant.INCOMING, "Fetch Review", request);
		try {
			List<UserReviewModel> userReviewModels = reviewService.fetchReview(userReviewModel);
			responseModel.setResponseBody(userReviewModels);
			responseModel.setResponseCode(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch Review -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch Review -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(ReviewConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(ReviewConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, ReviewConstant.OUTGOING, "Fetch Review", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchReview -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/fetch-rating", produces = "application/json")
	@ApiOperation(value = "Fetch Rating", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code = 202, message = "Token Required"),
			@ApiResponse(code = 203, message = "Token Expires!!!Please login to continue...") })
	public ResponseEntity<ResponseModel> fetchRating(@RequestParam(value = "userTypeId", required = true) String userTypeId) {
	
		if (logger.isInfoEnabled()) {
			logger.info("fetchRating -- START");
		}

		ResponseModel responseModel = new ResponseModel();
		Util.printLog(userTypeId, ReviewConstant.INCOMING, "Fetch Rating", request);
		try {
			List<RatingModel> ratingModels = reviewService.fetchRating(userTypeId);
			responseModel.setResponseBody(ratingModels);
			responseModel.setResponseCode(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch Rating -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch Rating -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(ReviewConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(ReviewConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, ReviewConstant.OUTGOING, "Fetch Rating", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchRating -- END");
		}
		
		if (responseModel.getResponseCode().equals(messageUtil.getBundle(ReviewConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
