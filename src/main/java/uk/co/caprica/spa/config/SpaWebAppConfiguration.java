/*
 * This file is part of Spa.
 *
 * Copyright (C) 2019
 * Caprica Software Limited (capricasoftware.co.uk)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.caprica.spa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Single page web application configuration.
 * <p>
 * Due to how this web application is configured, we need to make sure that the resource handler mappings are tried
 * before the controller mappings (by default the resource handler mappings are tried <em>after</em> the controller
 * mappings.
 * <p>
 * This is necessary because we use the catch-all "/**" mapping to route everything to the single page web application
 * for client routing - if the ordering was not properly set the request for "index.html" for the single page web
 * application would be infinitely looped to the catch-all controller until the stack overflowed.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"uk.co.caprica.spa.controller"})
public class SpaWebAppConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");

        // We want the resource handlers to be tried before the controllers (the particular number does not really
        // matter, but it must be lower than the corresponding controller registry order)
        registry.setOrder(-1000);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirect index.html to  the root, we simply do not want index.html in the address bar
        registry.addRedirectViewController("index.html", "/");

        // A catch-all for the web-service API routes to respond with the BAD_REQUEST status code
        registry.addStatusController("/api/**", HttpStatus.BAD_REQUEST);

        // A catch-all for everything else to forward to the single page web application, client routing will take over
        registry.addViewController("/**").setViewName("forward:/assets/index.html");

        // We want the controllers to be tried after the resource handlers (the particular number does not really
        //  matter, but it must be higher than the corresponding resource registry order)
        registry.setOrder(1000);
    }
}
