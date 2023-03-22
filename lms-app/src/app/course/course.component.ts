import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { Router } from '@angular/router';
import { Course } from '../models/course';
import { AuthService } from '../services/auth-service/auth.service';
import { CourseService } from '../services/course-service/course.service';


@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {



  course: Course=new Course();
  courses: Array<Course>=[];
  courseData: any={};
  isUserLoggedIn: boolean=false;
  isAdminRole: boolean=false;
  username: string;
  displayedColumns: string[] = ['courseId', 'courseName', 'courseDescription', 'courseDuration','courseTechnology','courseLaunchURL'];
  @ViewChild(MatTable) table: MatTable<Course>;
  errMessage :string = '' 
  panelOpenState : boolean = false;
  searchCourseByTechnology : string = ''
  courseDurationFrom: number;
  courseDurationTo: number;

  constructor(private courseService:CourseService,private authService: AuthService,private router: Router) { }


  ngOnInit(): void {
    this.isUserLoggedIn=this.authService.isUserLoggedIn;
    this.username=this.authService.username;
    this.isAdminRole=this.authService.isAdminRole;
    this.isAdminRole && this.updateDisplayedColumns();
    this.isUserLoggedIn && this.viewCourseList();
    this.isUserLoggedIn && this.authService.loginSuccess.emit(true);


  }


  updateDisplayedColumns(){
    this.displayedColumns.push("actions");
  }

  viewCourseList(){
    this.courseService.getAllCourses().subscribe(data=>{
      this.courses=data;
      this.errMessage='';
    },err=>{
      console.log(err);
      this.errMessage=err.error && (err.error.response || err.error.message || err.error.error);

    })
  }

  deleteCourseByCourseName(courseName:string){
    this.courseService.deleteCourseByCourseName(courseName).subscribe(data=>{
      let courseIndex=this.courses.findIndex(course=>course.courseName===courseName);
      this.courses.splice(courseIndex,1);
      this.table && this.table.renderRows();
      this.errMessage='';
    },err=>{
      console.log(err);
      this.errMessage=err.error && (err.error.response || err.error.message  || err.error.error);
    })
  }

  searchCourseByCourseTechnology(courseTechnology:string){
    this.courseService.getCoursesByCourseTechnology(courseTechnology).subscribe(data=>{
      this.courses=data;
      this.table && this.table.renderRows();
      this.errMessage='';
    },err=>{
      console.log(err);
      this.errMessage=err.error && (err.error.response || err.error.message  || err.error.error);
    })
  }


  updateCouseData(course: Course){
    this.courseService.setCourseObjToUpdate(course);
    this.router.navigate(['/update-course/', course.courseId]);
  }


  addCourse() {
    this.router.navigate(['add-course']);
  }

  searchCoursesByCourseTechnologyBasedOnDuration(){
    this.courseService.getCoursesByCourseTechnologyBasedOnDuration(this.searchCourseByTechnology,this.courseDurationFrom,this.courseDurationTo).subscribe(data=>{
      if(!data.length){
        /*Since if string response or no data received it should be blank*/
        this.courses=[];
      }else{
        this.courses=data;
      }
      this.table && this.table.renderRows();
      this.errMessage='';
    },err=>{
      console.log(err);
      this.errMessage=err.error && (err.error.response || err.error.message  || err.error.error);
    })
  }

  isSearchBasedOnCourseDurationAllowed(){
    if(!this.searchCourseByTechnology || !this.courseDurationFrom || !this.courseDurationTo){
      return true;
    }else{
      return false;
    }
  }


}
