package com.claimplat.adminapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claimplat.adminapi.service.MerchantService;
import com.claimplat.adminapi.vo.PageInfoVo;
import com.claimplat.adminapi.vo.merchant.MerchantAdd;
import com.claimplat.adminapi.vo.merchant.MerchantQuery;
import com.claimplat.adminapi.vo.merchant.MerchantUpdate;
import com.claimplat.adminapi.vo.merchant.MerchantVo;
import com.claimplat.common.constant.RestResult;
import com.claimplat.core.entity.Merchant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 商户信息管理的查改增
 * @author Joker
 *
 */
@Api(value = "商户管理")
@RestController("/merchant")
public class MerchantRest {
	
	@Autowired
	private MerchantService merchantService;
	
	/**
	 * 分页查询商户信息
	 * @param merQuery
	 * @return
	 */
	@ApiOperation(value = "查询商户", httpMethod = "POST", response = MerchantVo.class)
	@ApiImplicitParam(name = "token", paramType = "header")
	@PostMapping("/getMerchantList")	
	public RestResult getMerchantList(@ApiParam(value = "商户查询对象") @RequestBody MerchantQuery merQuery) {
		RestResult restResult = new RestResult();
		PageInfoVo<MerchantVo> page = merchantService.getMerchantList(merQuery);
		restResult.setData(page);
		return restResult;
	}
	
	/**
	 * 查询商户详情
	 * @param code
	 * @return
	 */
	@ApiOperation(value = "查询商户详情", httpMethod = "POST")
	@ApiImplicitParam(name = "token", paramType = "header")
	@PostMapping("/getMerchantDetails")	
	public RestResult getMerchantDetails(@ApiParam(value = "商户code") @RequestParam String code) {
		Merchant merchant = merchantService.getMerchantDetails(code);
		RestResult restResult = new RestResult();
		restResult.setData(merchant);
		return restResult;
	}	
	
	/**
	 * 修改商户信息
	 * @param merUpdate
	 * @return
	 */
	@ApiOperation(value = "修改商户信息", httpMethod = "POST")
	@ApiImplicitParam(name = "token", paramType = "header")
	@PostMapping("/updateMerchant")	
	public RestResult updateMerchant(@ApiParam(value = "商户基本信息") @RequestBody MerchantUpdate merUpdate) {
		merchantService.updateMerchant(merUpdate);
		return new RestResult();
	}
	
	/**
	 * 新增商户信息
	 * @param merUpdate
	 * @return
	 */
	@ApiOperation(value = "修改商户信息", httpMethod = "POST")
	@ApiImplicitParam(name = "token", paramType = "header")
	@PostMapping("/addMerchant")	
	public RestResult addMerchant(@ApiParam(value = "商户基本信息") @RequestBody MerchantAdd merAdd) {
		merchantService.addMerchant(merAdd);
		return new RestResult();
	}
}
