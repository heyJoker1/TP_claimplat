package com.claimplat.adminapi.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claimplat.adminapi.service.UserService;
import com.claimplat.adminapi.vo.LoginVo;
import com.claimplat.common.constant.RestResult;
import com.claimplat.common.enums.UserPwdStatusEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("用户接口")
@RestController
@RequestMapping("/user")
public class UserRest extends BaseRest{
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录
	 */
	@PostMapping("/login")
	@ApiOperation(value = "登录", httpMethod = "POST")
	public RestResult login(@ApiParam(value = "用户名", required = true) @RequestParam(value = "userName") String userName,
			@ApiParam(value = "密码", required = true) @RequestParam(value = "password")String password,
			HttpServletResponse response,HttpServletRequest request) {
		
		String token = userService.login(userName,password,request);
		
		UserPwdStatusEnum status = userService.isFlagUpdatePwd(userName);
		
		LoginVo loginVo = new LoginVo(status,token);
		
		RestResult restResult = new RestResult();
		restResult.setData(loginVo);
		
		return restResult;
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@PostMapping("logout")
	@ApiOperation(value = "注销",httpMethod = "POST")
	@ApiImplicitParam(name = "token",paramType = "header")
	public RestResult logout(HttpServletRequest request) {
		
		RestResult restResult = new RestResult();
		
		String token = request.getHeader("token");
		userService.logout(token);
		return restResult;
	}
	
	
	
	/**
	 * 验证密码
	 */
	@PostMapping("/checkPassword")
	@ApiOperation(value = "checkPassword", httpMethod = "POST")
	@ApiImplicitParam(name = "token",paramType = "header")
	public RestResult checkPassword(@ApiParam(value = "密码", required = true) @RequestParam(value = "password")String password,
			HttpServletResponse response,HttpServletRequest request) {
		
		RestResult restResult = new RestResult();
		
		String token = request.getHeader("token");
		
		userService.checkPassword(token,password);
		
		return restResult;
	}
	
	
	
	/**
	 * 修改密码
	 */
	@PostMapping("/updatePassword")
	@ApiOperation(value = "updatePassword", httpMethod = "POST")
	@ApiImplicitParam(name = "token",paramType = "header")
	public RestResult updatePassword(@ApiParam(value = "密码", required = true) @RequestParam(value = "password")String password,
			HttpServletResponse response,HttpServletRequest request) {
		
		RestResult restResult = new RestResult();
		
		String token = request.getHeader("token");
		
		userService.updatePassword(token,password);
		
		return restResult;
	}
}
