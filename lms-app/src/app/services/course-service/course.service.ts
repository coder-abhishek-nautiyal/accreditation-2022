import { Injectable } from '@angular/core';
import {RestTemplateService} from '../rest-template.service';
import {apiEndpoints} from '../../config/api-endpoints';
import {constants} from '../../config/constants';
import { Course } from 'src/app/models/course';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(public restTemplate: RestTemplateService) { }

  addCourse(course : Course):Observable<Course>{
  return this.restTemplate.postForEntity(constants.courseServiceBaseURL+apiEndpoints.ADD_COURSE,course);
  }


  getAllCourses():Observable<Array<Course>>{
    return this.restTemplate.getForEntity(constants.courseServiceBaseURL+apiEndpoints.GET_ALL_COURSE);
  }

    
  deleteCourseByCourseName(courseName : string):Observable<Course>{
    return this.restTemplate.deleteForEntity(constants.courseServiceBaseURL+apiEndpoints.DELETE_COURSE_BY_COURSE_NAME+`/${courseName}`);
  }

      
  deleteCourseById(courseId:number):Observable<Course>{
    return this.restTemplate.deleteForEntity(constants.courseServiceBaseURL+apiEndpoints.DELETE_COURSE_BY_ID+`/${courseId}`);
  }
  
  updateCourse(course : Course):Observable<Course>{
    return this.restTemplate.putForEntity(constants.courseServiceBaseURL+apiEndpoints.UPDATE_COURSE,course);
  }

  getCoursesByCourseTechnology(courseTechnology : string):Observable<Course>{
    return this.restTemplate.getForEntity(constants.courseServiceBaseURL+apiEndpoints.GET_COURSES_BY_COURSE_TECHNOLOGY+`/${courseTechnology}`);
  }

  getCoursesByCourseTechnologyBasedOnDuration(courseTechnology : string,courseDurationFrom:number,courseDurationTo:number):Observable<Course>{
    return this.restTemplate.getForEntity(constants.courseServiceBaseURL+apiEndpoints.GET_COURSES_BY_COURSE_TECHNOLOGY_DURATION_BASED+`/${courseTechnology}/${courseDurationFrom}/${courseDurationTo}`);
  }



}
