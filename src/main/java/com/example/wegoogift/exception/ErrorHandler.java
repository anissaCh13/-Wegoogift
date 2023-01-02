package com.example.wegoogift.exception;

import com.example.wegoogift.exception.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(CompanieNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleCompanieNotFound(CompanieNotFound ex){
        return new ApiErrorResponse(404, "Companie Id doesn't exist.");
    }

    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleUserNotFound(UserNotFound ex){
        return new ApiErrorResponse(404, "User Id doesn't exist.");
    }

    @ExceptionHandler(CompanyBalanceError.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorResponse handleCompanyBalanceError(CompanyBalanceError ex){
        return new ApiErrorResponse(403, "Company balance can't allow this deposit.");
    }

    @ExceptionHandler(UserOutOfCompany.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorResponse handleUserOutOfCompany(UserOutOfCompany ex){
        return new ApiErrorResponse(403, "User is not part of this company.");
    }
}
