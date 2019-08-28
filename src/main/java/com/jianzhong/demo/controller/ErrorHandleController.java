package com.jianzhong.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
class ErrorHandleController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        int statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception error = (Exception)request.getAttribute("javax.servlet.error.exception");
        String message = error.getMessage();
        System.out.print(message);
        if(statusCode == 401){
            return "/401?message="+message;
        }else if(statusCode == 404){
            return "/404?message="+message;
        }else if(statusCode == 403){
            return "/403?message="+message;
        }else{
            return "/500?message="+message;
        }

    }

    @Override
    public String getErrorPath()
    {
        return "/error";
    }
}