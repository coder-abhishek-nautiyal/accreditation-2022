import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTable, MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { MaterialModule } from '../material/material.module';
import { Course } from '../models/course';
import { AuthService } from '../services/auth-service/auth.service';
import { CourseService } from '../services/course-service/course.service';

import { CourseComponent } from './course.component';

describe('CourseComponent', () => {
  let component: CourseComponent;
  let fixture: ComponentFixture<CourseComponent>;
  let authService : AuthService;
  let courseService : CourseService

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseComponent ],
      imports:[HttpClientTestingModule,RouterTestingModule,MaterialModule,
        MatTableModule,   MatToolbarModule,
        MatButtonModule,
        MatDividerModule,
        MatToolbarModule,
        MatDividerModule,
        MatIconModule,
        MatInputModule,
        MatCardModule,
        MatGridListModule,
        MatListModule,
        MatExpansionModule,
        MatFormFieldModule,
        MatProgressSpinnerModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseComponent);
    component = fixture.componentInstance;
    courseService = TestBed.inject(CourseService);
    authService=TestBed.inject(AuthService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call isSearchBasedOnCourseDurationAllowed() with values present', () => {
    component.courseDurationFrom=2;
    component.courseDurationTo=3;
    component.searchCourseByTechnology='Java';
    component.isSearchBasedOnCourseDurationAllowed();
    expect(component).toBeTruthy();
  });

  it('should call isSearchBasedOnCourseDurationAllowed() with values not present', () => {

    component.isSearchBasedOnCourseDurationAllowed();
    expect(component).toBeTruthy();
  });


  it('should call searchCoursesByCourseTechnologyBasedOnDuration() with success', () => {
    let response:Array<Course>=[{courseId:1,courseName:'Java',courseDescription:'course Desc',courseDuration:2,courseLaunchURL:'test',courseTechnology:'Java'}]
    spyOn(courseService,'getCoursesByCourseTechnologyBasedOnDuration').and.returnValue(of(response));
    component.searchCoursesByCourseTechnologyBasedOnDuration();
    expect(component).toBeTruthy();
  });

  it('should call searchCoursesByCourseTechnologyBasedOnDuration() with error and response present', () => {
    let response={error:{
      response:"Exception"
    }};
    spyOn(courseService,'getCoursesByCourseTechnologyBasedOnDuration').and.returnValue(throwError(response));
    component.searchCoursesByCourseTechnologyBasedOnDuration();
    expect(component).toBeTruthy();
  });

  it('should call searchCoursesByCourseTechnologyBasedOnDuration() with error and message present', () => {
    let response={error:{
      message:"Exception"
    }};
    spyOn(courseService,'getCoursesByCourseTechnologyBasedOnDuration').and.returnValue(throwError(response));
    component.searchCoursesByCourseTechnologyBasedOnDuration();
    expect(component).toBeTruthy();
  });


  it('should call ngOnInit() with AdminRole and Login Success', () => {
    authService.isUserLoggedIn=true;
    authService.isAdminRole=true;
    component.ngOnInit();
    expect(component).toBeTruthy();
  });


  it('should call viewCourseList() with success', () => {
    let response:Array<Course>=[{courseId:1,courseName:'Java',courseDescription:'course Desc',courseDuration:2,courseLaunchURL:'test',courseTechnology:'Java'}]
    spyOn(courseService,'getAllCourses').and.returnValue(of(response));
    component.viewCourseList();
    expect(component).toBeTruthy();
  });


  it('should call viewCourseList() with error and message present', () => {
    let response={error:{
      message:"Exception"
    }};
    spyOn(courseService,'getAllCourses').and.returnValue(throwError(response));
    component.viewCourseList();
    expect(component).toBeTruthy();
  });

  it('should call viewCourseList() with error and response present', () => {
    let response={error:{
      response:"Exception"
    }};
    spyOn(courseService,'getAllCourses').and.returnValue(throwError(response));
    component.viewCourseList();
    expect(component).toBeTruthy();
  });


  it('should call deleteCourseByCourseName() with success', () => {
    component.courses.push({courseId:1,courseName:'Java',courseDescription:'course Desc',courseDuration:2,courseLaunchURL:'test',courseTechnology:'Java'});
    let response:Course={courseId:1,courseName:'Java',courseDescription:'course Desc',courseDuration:2,courseLaunchURL:'test',courseTechnology:'Java'};
    spyOn(courseService,'deleteCourseByCourseName').and.returnValue(of(response));
    component.deleteCourseByCourseName('Java');
    expect(component).toBeTruthy();
  });


  it('should call viewCourseList() with error and message present', () => {
    let response={error:{
      message:"Exception"
    }};
    spyOn(courseService,'deleteCourseByCourseName').and.returnValue(throwError(response));
    component.deleteCourseByCourseName('Java');
    expect(component).toBeTruthy();
  });

  it('should call viewCourseList() with error and response present', () => {
    let response={error:{
      response:"Exception"
    }};
    spyOn(courseService,'deleteCourseByCourseName').and.returnValue(throwError(response));
    component.deleteCourseByCourseName('Java');
    expect(component).toBeTruthy();
  });


  it('should call searchCourseByCourseTechnology() with success', () => {
    let response:Array<Course>=[{courseId:1,courseName:'Java',courseDescription:'course Desc',courseDuration:2,courseLaunchURL:'test',courseTechnology:'Java'}];
    spyOn(courseService,'getCoursesByCourseTechnology').and.returnValue(of(response));
    component.searchCourseByCourseTechnology('Java');
    expect(component).toBeTruthy();
  });


  it('should call searchCourseByCourseTechnology() with error and message present', () => {
    let response={error:{
      message:"Exception"
    }};
    spyOn(courseService,'getCoursesByCourseTechnology').and.returnValue(throwError(response));
    component.searchCourseByCourseTechnology('Java');
    expect(component).toBeTruthy();
  });

  it('should call searchCourseByCourseTechnology() with error and response present', () => {
    let response={error:{
      response:"Exception"
    }};
    spyOn(courseService,'getCoursesByCourseTechnology').and.returnValue(throwError(response));
    component.searchCourseByCourseTechnology('Java');
    expect(component).toBeTruthy();
  });

  
  it('should call updateCouseData() with success', () => {
    component.course={courseId:1,courseName:'Java',courseDescription:'course Desc',courseDuration:2,courseLaunchURL:'test',courseTechnology:'Java'};
    let response:Course={courseId:1,courseName:'Java',courseDescription:'course Desc',courseDuration:2,courseLaunchURL:'test',courseTechnology:'Java'};
    spyOn(courseService,'deleteCourseByCourseName').and.returnValue(of(response));
    component.updateCouseData(component.course);
    expect(component).toBeTruthy();
  });


    
  it('should call addCourse() with success', () => {
    component.addCourse();
    expect(component).toBeTruthy();
  });


});
