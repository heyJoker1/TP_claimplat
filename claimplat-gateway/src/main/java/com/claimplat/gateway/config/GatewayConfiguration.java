package com.claimplat.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.claimplat.gateway.exception.JsonSentinelGatewayBlockExceptionHandler;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Spring Cloud Gateway网关整合Sentinel限流
 * @author Joker
 */
@Configuration
public class GatewayConfiguration {
	private final List<ViewResolver> viewResolvers;
	private final ServerCodecConfigurer serverCodecConfigurer;

	public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
			ServerCodecConfigurer serverCodecConfigurer) {
		this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
		this.serverCodecConfigurer = serverCodecConfigurer;
	}

	/*自定义异常*/
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public JsonSentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
		return new JsonSentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
	}

	@Bean
	@Order(-5)
	public GlobalFilter sentinelGatewayFilter() {
		return new SentinelGatewayFilter();
	}

	@PostConstruct
	public void doInit() {
		initCustomizedApis();
		initGatewayRules();
		initDegradeRule();
		initSystemRule();
	}

	/*自定义分组代码（也可在配置文件配置）*/
	private void initCustomizedApis() {
		Set<ApiDefinition> definitions = new HashSet<>();
		ApiDefinition api1 = new ApiDefinition("Service_API")
				.setPredicateItems(new HashSet<ApiPredicateItem>() {{
					add(new ApiPathPredicateItem().setPattern("/serviceapi/**")
							.setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_PREFIX));
				}});

		ApiDefinition api2 = new ApiDefinition("Offline_API")
				.setPredicateItems(new HashSet<ApiPredicateItem>() {{
					add(new ApiPathPredicateItem().setPattern("/offline/**")
							.setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_PREFIX));
				}});

		definitions.add(api1);
		definitions.add(api2);
		GatewayApiDefinitionManager.loadApiDefinitions(definitions);
	}

	/*配置限流规则*/
	private void initGatewayRules() {
		Set<GatewayFlowRule> rules = new HashSet<>();
		rules.add(new GatewayFlowRule("Service_API")
				.setResourceMode(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_PREFIX)
				.setCount(1) 		// 限流阈值
				.setIntervalSec(1)  // 统计时间窗口，单位是秒，默认是 1 秒
				);

		rules.add(new GatewayFlowRule("Offline_API")
				.setResourceMode(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_PREFIX)
				.setCount(5) 		
				.setIntervalSec(1)
				.setParamItem(new GatewayParamFlowItem()
						.setParseStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_PREFIX)
						.setFieldName("ack"))  //指定参数线流
				);
		GatewayRuleManager.loadRules(rules);
	}
	
	/*设置降级规则*/
	private static void initDegradeRule() {
		List<DegradeRule> rules = new ArrayList<DegradeRule>();
		DegradeRule rule = new DegradeRule();
		rule.setResource("Offline_API");			  //资源名
		rule.setCount(1);							  //平均响应时间阈值
		rule.setGrade(RuleConstant.DEGRADE_GRADE_RT); //降级模式，根据评价响应时间降级
		rule.setTimeWindow(5);						  //设置降级时间
		rules.add(rule);
		DegradeRuleManager.loadRules(rules);
	}
	
	/*设置系统规则规则*/
	private static void initSystemRule() {
		List<SystemRule> rules = new ArrayList<SystemRule>();
		SystemRule rule = new SystemRule();
		rule.setHighestSystemLoad(3.0);
		rule.setHighestCpuUsage(0.6);
		rule.setAvgRt(10);
		rule.setQps(20);
		rule.setMaxThread(500);
		rules.add(rule);
		SystemRuleManager.loadRules(Collections.singletonList(rule));
	}
}
