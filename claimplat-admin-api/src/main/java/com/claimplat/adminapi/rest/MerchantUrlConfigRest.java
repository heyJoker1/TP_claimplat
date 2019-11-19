package com.claimplat.adminapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.claimplat.adminapi.service.MerchantUrlConfigService;
import com.claimplat.adminapi.vo.PageInfoVo;
import com.claimplat.adminapi.vo.merchanturl.MerchantUrlAdd;
import com.claimplat.adminapi.vo.merchanturl.MerchantUrlQuery;
import com.claimplat.adminapi.vo.merchanturl.MerchantUrlUpdate;
import com.claimplat.adminapi.vo.merchanturl.MerchantUrlVo;
import com.claimplat.adminapi.vo.order.OrderQuery;
import com.claimplat.common.constant.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "商户通知地址管理")
@RestController("/merchantUrlConfig")
public class MerchantUrlConfigRest {
	
	@Autowired
	private MerchantUrlConfigService merchantUrlConfigService;
	
	/**
	 * 查询接口地址
	 * @param merchantUrlQuery
	 * @return
	 */
	@PostMapping("/getUrlConfig")
	@ApiImplicitParam(name = "token",paramType = "header")
	@ApiOperation(value = "接口数据查询",httpMethod = "POST",response = MerchantUrlVo.class)
	public RestResult getUrlConfig(@ApiParam(value = "接口数据查询对象") @RequestBody MerchantUrlQuery merchantUrlQuery) {
		PageInfoVo<MerchantUrlVo> page = merchantUrlConfigService.getUrlConfig(merchantUrlQuery);
		RestResult restResult = new RestResult();
		restResult.setData(page);
		return restResult;
	}
	
	/**
	 * 新增接口数据请求
	 * @param merchantUrlAdd
	 * @return
	 */
	@PostMapping("/addUrlConfig")
	@ApiImplicitParam(name = "token",paramType = "header")
	@ApiOperation(value = "新增接口",httpMethod = "POST")
	public RestResult addUrlConfig(@ApiParam(value = "新增接口数据对象") @RequestBody MerchantUrlAdd merchantUrlAdd) {
		merchantUrlConfigService.addUrlConfig(merchantUrlAdd);
		return new RestResult();
	}
	
	/**
	 * 修改接口数据请求
	 * @param merchantUrlUpdate
	 * @return
	 */
	@PostMapping("/updateUrlConfig")
	@ApiImplicitParam(name = "token",paramType = "header")
	@ApiOperation(value = "修改接口",httpMethod = "POST")
	public RestResult updateUrlConfig(@ApiParam(value = "修改接口数据对象") @RequestBody MerchantUrlUpdate merchantUrlUpdate) {
		merchantUrlConfigService.updateUrlConfig(merchantUrlUpdate);
		return new RestResult();
	}
	
	/**
	 * 删除通知地址信息
	 * @param code
	 * @return
	 */
	@PostMapping("/deleteUrlConfig")
	@ApiImplicitParam(name = "token",paramType = "header")
	@ApiOperation(value = "删除接口",httpMethod = "POST")
	public RestResult deleteUrlConfig(@ApiParam(value = "code") @RequestBody String code) {
		merchantUrlConfigService.deleteUrlConfig(code);
		return new RestResult();
	}
}
