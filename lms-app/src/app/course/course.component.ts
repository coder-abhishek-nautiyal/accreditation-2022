import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTable, MatTableDataSource } from '@angular/material/table';
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
  dataSource: MatTableDataSource<Course>;
  courseData: any={};
  isUserLoggedIn: boolean=false;
  isAdminRole: boolean=false;
  username: string;
  displayedColumns: string[] = ['courseId', 'courseName', 'courseDescription', 'courseDuration','courseTechnology','courseLaunchURL'];
  @ViewChild(MatTable) table: MatTable<Course>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  errMessage :string = '' 
  panelOpenState : boolean = false;
  searchCourseByTechnology : string = ''
  courseDurationFrom: number;
  courseDurationTo: number;
  resultsLength : number  = 0;

  constructor(private courseService:CourseService,private authService: AuthService,private router: Router) { }


  ngOnInit(): void {
    this.isUserLoggedIn=this.authService.isUserLoggedIn;
    this.username=this.authService.username;
    this.isAdminRole=this.authService.isAdminRole;
    this.isAdminRole && this.updateDisplayedColumns();
    this.isUserLoggedIn && this.viewCourseList();
    this.isUserLoggedIn && this.authService.loginSuccess.emit(true);
    this.dataSource = new MatTableDataSource(this.courses);

  }


  updateDisplayedColumns(){
    this.displayedColumns.push("actions");
  }

  viewCourseList(){
    this.courseService.getAllCourses().subscribe(data=>{
      if(!data.length){
        /*Since if string response or no data received it should be blank*/
        this.courses=[];
        this.resultsLength = 0;

      }else{
        this.courses=data;
        this.resultsLength = data.length;
      }
      this.dataSource.data=this.courses;
      this.dataSource.paginator=this.paginator;
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
      this.dataSource.data=this.courses;
      this.dataSource.paginator=this.paginator;
      this.resultsLength = this.courses.length;
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
      this.dataSource.data=this.courses;
      this.dataSource.paginator=this.paginator;
      this.resultsLength = data.length;
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
        this.resultsLength = 0;
      }else{
        this.courses=data;
        this.resultsLength = data.length;
      }
      this.dataSource.data=this.courses;
      this.dataSource.paginator=this.paginator;
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
