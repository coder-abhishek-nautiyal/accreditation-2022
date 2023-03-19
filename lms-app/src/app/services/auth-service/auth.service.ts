import { EventEmitter, Injectable } from '@angular/core';
import { resolve } from 'dns';
import { constants } from 'src/app/config/constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isUserLoggedIn : boolean=false;
  isUserRole: boolean=false;
  isAdminRole: boolean=false;
  username: string;
  email: string;
  loginSuccess = new EventEmitter<boolean>();


  constructor() { }

  init():Promise<boolean>{
    if(this.access_token){
      return this.validateTokenExpiry();
    }else{
      return Promise.resolve(false);
    }
  }


  get access_token(): string | any {
    return localStorage.getItem('accessToken');
  }


  set access_token(token:string) {
    localStorage.setItem('accessToken',token)
  }

  deleteAccessToken = () => {
    localStorage.removeItem('accessToken')
  }

  extractUserDetails(token : string){

    this.access_token=token; // we are setting token value in local storage
    let payLoadData=JSON.parse(atob(token.split('.')[1]));

    this.username=payLoadData.username;
    this.email=payLoadData.email;

    this.setRoleAccess(payLoadData.role);


  }

  setRoleAccess(userRole:string){
    if(userRole==='ROLE_USER'){
      this.isUserRole=true;
      this.isAdminRole=false;
    }

    if(userRole==='ROLE_ADMIN'){
      this.isAdminRole=true;
      this.isUserRole=false;
    }

    if(constants.roles.includes(userRole)){
      this.isUserLoggedIn=true // set attribute true if role is matching with preferred role
      this.loginSuccess.emit(true); //emitting event to header component
    }

  }

  validateTokenExpiry(){
    const currentTime=Math.floor(Date.now()/1000);
    if(this.access_token!=null && this.access_token!="null"){
      let payLoadData=JSON.parse(atob(this.access_token.split('.')[1]));
      const tokenExpiryTime=payLoadData.exp;
      const remainingTime=tokenExpiryTime-currentTime;
      if(remainingTime<=0){
        this.deleteAccessToken();
        this.loginSuccess.emit(false); //emitting event to header component

      }else{
        this.extractUserDetails(this.access_token);
        this.loginSuccess.emit(true); //emitting event to header component
        return Promise.resolve(true)

      }

    }else{
      this.loginSuccess.emit(false);
    }
    return Promise.resolve(false);

  }


}
