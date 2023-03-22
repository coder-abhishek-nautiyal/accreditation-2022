import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { CourseService } from 'src/app/services/course-service/course.service';

import { AddCourseComponent } from './add-course.component';

describe('AddCourseComponent', () => {
  let component: AddCourseComponent;
  let fixture: ComponentFixture<AddCourseComponent>;
  let courseService: CourseService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCourseComponent ],
      imports:[ReactiveFormsModule,
        FormsModule ,HttpClientTestingModule,RouterTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCourseComponent);
    courseService = TestBed.inject(CourseService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call addCourseDetails() with success', () => {
    let response={courseId:1,courseName:'Java',courseDescription:'course Desc',courseDuration:2,courseLaunchURL:'test',courseTechnology:'Java'};
    spyOn(courseService,'addCourse').and.returnValue(of(response));
    component.addCourseDetails();
    expect(component).toBeTruthy();
  });

  it('should call addCourseDetails() with error message', () => {
    let response={error:{
      message:"Exception"
    }};
    spyOn(courseService,'addCourse').and.returnValue(throwError(response));
    component.addCourseDetails();
    expect(component).toBeTruthy();
  });

  it('should call addCourseDetails() with error service unavailable', () => {
    let response={error:{
      error:"service unavailable"
    }};
    spyOn(courseService,'addCourse').and.returnValue(throwError(response));
    component.addCourseDetails();
    expect(component).toBeTruthy();
  });

});
