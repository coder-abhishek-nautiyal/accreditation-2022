import { Injectable } from '@angular/core';
import {RestTemplateService} from '../rest-template.service';
import {apiEndpoints} from '../../config/api-endpoints';
import {constants} from '../../config/constants';
import { Course } from 'src/app/models/course';
import {  Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class CourseService {

  courseObjToUpdate = new Course();


  constructor(private restTemplate: RestTemplateService) { }

  setCourseObjToUpdate(course:Course){
    this.courseObjToUpdate=course;
  }

  addCourse(course : Course):Observable<Course>{
  return this.restTemplate.postForEntity(environment.baseUrl+apiEndpoints.ADD_COURSE,course);
  }


  getAllCourses():Observable<Array<Course>>{
    return this.restTemplate.getForEntity(environment.baseUrl+apiEndpoints.GET_ALL_COURSE);
  }

    
  deleteCourseByCourseName(courseName : string):Observable<Course>{
    return this.restTemplate.deleteForEntity(environment.baseUrl+apiEndpoints.DELETE_COURSE_BY_COURSE_NAME+`/${courseName}`);
  }

      
  deleteCourseById(courseId:number):Observable<Course>{
    return this.restTemplate.deleteForEntity(environment.baseUrl+apiEndpoints.DELETE_COURSE_BY_ID+`/${courseId}`);
  }
  
  updateCourse(course : Course):Observable<Course>{
    return this.restTemplate.putForEntity(environment.baseUrl+apiEndpoints.UPDATE_COURSE,course);
  }

  getCoursesByCourseTechnology(courseTechnology : string):Observable<Array<Course>>{
    return this.restTemplate.getForEntity(environment.baseUrl+apiEndpoints.GET_COURSES_BY_COURSE_TECHNOLOGY+`/${courseTechnology}`);
  }

  getCoursesByCourseTechnologyBasedOnDuration(courseTechnology : string,courseDurationFrom:number,courseDurationTo:number):Observable<Array<Course>>{
    return this.restTemplate.getForEntity(environment.baseUrl+apiEndpoints.GET_COURSES_BY_COURSE_TECHNOLOGY_DURATION_BASED+`/${courseTechnology}/${courseDurationFrom}/${courseDurationTo}`);
  }




}
