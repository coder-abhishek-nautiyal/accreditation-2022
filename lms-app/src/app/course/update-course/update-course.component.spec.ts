import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { CourseService } from 'src/app/services/course-service/course.service';

import { UpdateCourseComponent } from './update-course.component';

describe('UpdateCourseComponent', () => {
  let component: UpdateCourseComponent;
  let fixture: ComponentFixture<UpdateCourseComponent>;
  let courseService : CourseService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCourseComponent ],
      imports:[ReactiveFormsModule,
        FormsModule,HttpClientTestingModule,RouterTestingModule ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCourseComponent);
    courseService = TestBed.inject(CourseService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  
  it('should call addCourseDetails() with success', () => {
    let response={courseId:1,courseName:'Java',courseDescription:'course Desc',courseDuration:2,courseLaunchURL:'test',courseTechnology:'Java'};
    spyOn(courseService,'updateCourse').and.returnValue(of(response));
    component.updateCourseDetails();
    expect(component).toBeTruthy();
  });

  it('should call updateCourseDetails() with error', () => {
    let response={error:{
      message:"Exception"
    }};
    spyOn(courseService,'updateCourse').and.returnValue(throwError(response));
    component.updateCourseDetails();
    expect(component).toBeTruthy();
  });

});
