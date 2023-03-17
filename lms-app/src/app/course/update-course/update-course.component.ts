import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
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
  updateCourseForm!: NgForm;
  isSubmitted: boolean = false;



  constructor(private courseService:CourseService,private router:Router,private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    /*Below logic to set course Id and make it disabled for user to not edit*/
    this.activatedRoute.params.subscribe((params)=>{
      this.course.courseId=params['courseId'];
    })

  }

    updateCourseDetails(isValid:any){
      this.isSubmitted = true;
      if (isValid) {
        this.courseService.updateCourse(this.course).subscribe(data=>{

          this.router.navigate(['/home']);
        },error=>{
          console.log(error);
        })
      }
  }



}
