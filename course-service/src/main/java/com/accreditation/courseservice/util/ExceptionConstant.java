package com.accreditation.courseservice.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionConstant {

    public static final String COURSE_NAME_ALREADY_PRESENT = "Course Name already present";
    public static final String COURSE_NAME_DOES_NOT_EXIST = "Course Name doesn't exist";
    public static final String COURSE_ID_DOES_NOT_EXIST = "Course ID doesn't exist";
    public static final String COURSE_TECHNOLOGY_DOES_NOT_EXIST = "Course Technology doesn't exist";
    public static final String COURSE_DURATION_FROM_SHOULD_BE_LESS_THAN_TO = "Course Duration from should be less than Course Duration To";
    public static final String COURSE_NAME_SHOULD_BE_MINIMUM_20 = "Course Name should be of minimum 20 characters";
    public static final String COURSE_DESCRIPTION_SHOULD_BE_MINIMUM_100 = "Course Description should be of minimum 100 characters";
    public static final String COURSE_TECHNOLOGY_IS_MANDATORY = "Course Technology is mandatory";
    public static final String COURSE_NAME_IS_MANDATORY = "Course Name is mandatory";
    public static final String COURSE_DESCRIPTION_IS_MANDATORY = "Course Description is mandatory";
    public static final String COURSE_LAUNCH_URL_IS_MANDATORY = "Course Launch URL is mandatory";
    public static final String COURSE_DURATION_SHOULD_BE_NUMERIC_GREATER_THAN_ZERO = "Course Duration should be numeric and greater than 0";
    public static final String COURSE_ID_SHOULD_BE_NUMERIC_GREATER_THAN_ZERO = "Course Id should be numeric and greater than 0";
    public static final String EMAIL_ID_IS_MANDATORY = "Email id is mandatory";
    public static final String PASSWORD_IS_MANDATORY = "Password is mandatory";
    public static final String EMAIL_ID_IS_NOT_VALID = "Enter valid email with @ and .com";
    public static final String PASSWORD_SHOULD_BE_ALPHANUMERIC = "Password should be alphanumeric";
    public static final String PASSWORD_MINIMUM_LENGTH_ERROR = "Password should be at least 8 characters";
    public static final String SESSION_EXPIRED_LOGIN_AGAIN = "Session expired, Please login and try again";
    public static final String TOKEN_MISSING_LOGIN_AGAIN = "Looks like Authentication Token missing, Please login and try again";
    public static final String EXCEPTION_MESSAGE_KEY= "message";





}
