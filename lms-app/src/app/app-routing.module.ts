import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCourseComponent } from './course/add-course/add-course.component';
import { CourseComponent } from './course/course.component';
import { UpdateCourseComponent } from './course/update-course/update-course.component';
import { AuthGuardService } from './guards/auth-guard.service';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: CourseComponent },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  { path: 'add-course', component: AddCourseComponent ,canActivate:[AuthGuardService]},
  { path: 'update-course/:courseId', component: UpdateCourseComponent,canActivate:[AuthGuardService] } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
