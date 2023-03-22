import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing'
import { CourseService } from './course.service';
import { Course } from 'src/app/models/course';

describe('CourseService', () => {
  let service: CourseService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule]

    });
    
    service = TestBed.inject(CourseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  
  it('should call addCourse()', () => {
    let request:Course={courseId:1,courseName:'Java',courseDescription:'Java',courseDuration:3,courseTechnology:'Java',courseLaunchURL:'test.com'}
    service.addCourse(request);
    expect(service).toBeTruthy();
  });

  
  it('should call deleteCourseByCourseName()', () => {
    let request:Course={courseId:1,courseName:'Java',courseDescription:'Java',courseDuration:3,courseTechnology:'Java',courseLaunchURL:'test.com'}
    service.deleteCourseByCourseName(request.courseName);
    expect(service).toBeTruthy();
  });

    
  it('should call deleteCourseById()()', () => {
    let request:Course={courseId:1,courseName:'Java',courseDescription:'Java',courseDuration:3,courseTechnology:'Java',courseLaunchURL:'test.com'}
    service.deleteCourseById(request.courseId);
    expect(service).toBeTruthy();
  });

    
  it('should call updateCourse()', () => {
    let request:Course={courseId:1,courseName:'Java',courseDescription:'Java',courseDuration:3,courseTechnology:'Java',courseLaunchURL:'test.com'}
    service.updateCourse(request);
    expect(service).toBeTruthy();
  });

    
  it('should call getCoursesByCourseTechnology()', () => {
    let request:Course={courseId:1,courseName:'Java',courseDescription:'Java',courseDuration:3,courseTechnology:'Java',courseLaunchURL:'test.com'}
    service.getCoursesByCourseTechnology(request.courseTechnology);
    expect(service).toBeTruthy();
  });


    
  it('should call getCoursesByCourseTechnologyBasedOnDuration()', () => {
    let request:Course={courseId:1,courseName:'Java',courseDescription:'Java',courseDuration:3,courseTechnology:'Java',courseLaunchURL:'test.com'}
    service.getCoursesByCourseTechnologyBasedOnDuration(request.courseTechnology,1,2);
    expect(service).toBeTruthy();
  });



});
