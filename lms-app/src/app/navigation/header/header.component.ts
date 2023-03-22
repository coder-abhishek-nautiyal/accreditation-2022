import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth-service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isUserLoggedIn : boolean= false ;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.loginSuccess.subscribe(response=>{
      this.isUserLoggedIn=response;
    });
  }

  logout(){
    this.authService.deleteAccessToken();
    this.authService.isUserLoggedIn=false;
    this.authService.loginSuccess.emit(false);
  }

}
