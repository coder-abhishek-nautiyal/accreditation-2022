import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/models/course';
import { CourseService } from 'src/app/services/course-service/course.service';

@Component({
  selector: 'app-update-course',
  templateUrl: './update-course.component.html',
  styleUrls: ['./update-course.component.css']
})
export class UpdateCourseComponent implements OnInit {

  course: Course=new Course();
  updateCourseForm!: FormGroup;
  errMessage = ''



  constructor(private fb: FormBuilder,private courseService:CourseService,private router:Router,private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    this.updateCourseForm = this.fb.group({
      courseId:['',[Validators.required,Validators.min(1)]],
      courseName:['',[Validators.required,Validators.minLength(20)]],
      courseDescription:['',[Validators.required,Validators.minLength(100)]],
      courseDuration:['',[Validators.required,Validators.min(1)]],
      courseTechnology:['',[Validators.required]],
      courseLaunchURL:['',[Validators.required]],
    }) 

    /*Below logic to set course Id and make it disabled for user to not edit*/
    this.activatedRoute.params.subscribe((params)=>{
      this.course.courseId=params['courseId'];
    })

  }

    updateCourseDetails(){
        this.courseService.updateCourse(this.course).subscribe(data=>{

          this.router.navigate(['/home']);
        },err=>{
          console.log(err);
          this.errMessage=err.error && err.error.message;

        })
  }



}
