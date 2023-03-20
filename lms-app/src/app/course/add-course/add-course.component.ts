import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
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
  addCourseForm: FormGroup;
  errMessage = ''



  constructor(private fb: FormBuilder,private courseService:CourseService,private router:Router) { }

  ngOnInit(): void {
    this.addCourseForm = this.fb.group({
      courseName:['',[Validators.required,Validators.minLength(20)]],
      courseDescription:['',[Validators.required,Validators.minLength(100)]],
      courseDuration:['',[Validators.required,Validators.min(1)]],
      courseTechnology:['',[Validators.required]],
      courseLaunchURL:['',[Validators.required]],
    })
  }

    addCourseDetails(){
        this.courseService.addCourse(this.course).subscribe(data=>{
          this.router.navigate(['/home']);
        },err=>{
          console.log(err);
          this.errMessage=err.error && err.error.message;

        })

  }


}
