import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiEndpoints } from 'src/app/config/api-endpoints';
import { LoginUser } from 'src/app/models/login-user';
import { environment } from 'src/environments/environment';
import { RestTemplateService } from '../rest-template.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(public restTemplate: RestTemplateService) { }

  validateLogin(user : LoginUser):Observable<any>{
    return this.restTemplate.postForEntity(environment.baseUrl+apiEndpoints.VALIDATE_LOGIN,user)
  }

}
