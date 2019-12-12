package com.claimplat.serviceapi.service;

import org.springframework.stereotype.Service;

import com.claimplat.common.bean.forward.request.thridpart.ShuiDiProcessThridpartForwardRequest;
import com.claimplat.common.bean.forward.response.thridpart.BaseThridpartForwardResponse;
import com.claimplat.common.enums.ComponentForwaderTypeEnum;
import com.claimplat.common.enums.ThridpartTypeEnum;
import com.claimplat.common.exception.BusinessException;
import com.claimplat.common.exception.ValidateException;
import com.claimplat.core.entity.Merchant;
import com.claimplat.serviceapi.componentforward.ComponentForwarder;
import com.claimplat.serviceapi.componentforward.bean.ForwardContext;
/**
 * 水滴第三方报案等相关业务操作
 * @author Joker
 *
 */
@Service
public class ShuiDiService extends BaseService{

	public BaseThridpartForwardResponse push(ShuiDiProcessThridpartForwardRequest request) {
		if(request == null) {
			throw new IllegalArgumentException("请求数据不能为空");
		}
		
		try {
			Merchant merchant = validateAndFetchMerchant(ThridpartTypeEnum.SHUT_DI);
			ForwardContext context = new ForwardContext();
			context.setMerchant(merchant);
			context.setRequest(request);
			ComponentForwarder componentForwarder = componentForwarderFactory.getComponentForwarder(ComponentForwaderTypeEnum.SHUIDI_PROCESS);
			componentForwarder.execute(context);
			
			BaseThridpartForwardResponse response = (BaseThridpartForwardResponse) context.getResponse();
			return response;
		} catch(IllegalArgumentException | IllegalStateException | ValidateException e){
			throw e; 
		} catch (Exception e) {
			throw new BusinessException("水滴案件状态推送操作出错",e);
		}
	}

}
