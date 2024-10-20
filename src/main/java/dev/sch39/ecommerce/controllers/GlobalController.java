package dev.sch39.ecommerce.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalController {
  @ModelAttribute("servlet")
  HttpServletRequest getRequestServlet(HttpServletRequest request) {
    return request;
  }

  @ModelAttribute("normalizedUrl")
  String getNormalizedUrl(HttpServletRequest request) {
    String url = request.getServletPath();
    return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
  }
}
