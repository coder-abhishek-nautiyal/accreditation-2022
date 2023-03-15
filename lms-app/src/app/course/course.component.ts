import { Component, OnInit } from '@angular/core';
import { Course } from '../models/course';
import { CourseService } from '../services/course-service/course.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  constructor(private courseService:CourseService) { }


  course: Course=new Course();
  courses: Array<Course>=[];
  courseData: any={};
  test:any;

  ngOnInit(): void {
  }


  addCourseDetails(){
    this.courseService.addCourse(this.course).subscribe(data=>{
      this.courses.push(data);
    },error=>{
      console.log(error);
    })
  }

  viewCourseList(){
    this.courseService.getAllCourses().subscribe(data=>{
      debugger;
      this.courses=data;
    },error=>{
      console.log(error);
    })
  }

  deleteCourseByCourseName(courseName:string){
    this.courseService.deleteCourseByCourseName(courseName).subscribe(data=>{
      debugger;
      let courseIndex=this.courses.findIndex(course=>course.courseName===courseName);
      this.courses.splice(courseIndex,1);
      debugger;
      this.viewCourseList();
    },error=>{
      console.log(error);
    })
  }

  updateCourseDetails(){
    this.courseService.updateCourse(this.course).subscribe(data=>{
      for(let index in this.courses){
        if(this.courses[index].courseId===this.course.courseId){
          this.courses[index].courseDescription=this.course.courseDescription;
          this.courses[index].courseDuration=this.course.courseDuration;
          this.courses[index].courseLaunchURL=this.course.courseLaunchURL;
          this.courses[index].courseTechnology=this.course.courseTechnology;
          this.courses[index].courseName=this.course.courseName;

        }
      }
      this.viewCourseList();
    },error=>{
      console.log(error);
    })
  }



}
