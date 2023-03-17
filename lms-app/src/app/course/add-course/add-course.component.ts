import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course-service/course.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  course: Course=new Course();
  addCourseForm!: NgForm;
  isSubmitted: boolean = false;


  constructor(private courseService:CourseService,private router:Router) { }

  ngOnInit(): void {
  }

    addCourseDetails(isValid:any){
      this.isSubmitted = true;

      if(isValid){
        this.courseService.addCourse(this.course).subscribe(data=>{
          this.router.navigate(['/home']);
        },error=>{
          console.log(error);
        })
    
      }
  }


}
