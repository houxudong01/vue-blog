package com.simpleblog.vueblog.config;

import com.google.common.base.Predicates;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author:hxd
 * @date:2020/5/31
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerConfig {
    @Bean
    public Docket docket(SwaggerProperties properties) {

        ApiInfo info = new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .contact(properties.getContact())
                .license(properties.getLicense())
                .licenseUrl(properties.getLicenseUrl())
                .version(properties.getVersion())
                .extensions(properties.getVendorExtensions())
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(info)
                .select()
                .paths(Predicates.not(PathSelectors.regex(properties.getExcluded())))
                .build();
    }
}
