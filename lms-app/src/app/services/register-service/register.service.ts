import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiEndpoints } from 'src/app/config/api-endpoints';
import { RegisterUser } from 'src/app/models/register-user';
import { environment } from 'src/environments/environment';
import { RestTemplateService } from '../rest-template.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(public restTemplate: RestTemplateService) { }

  registerUser(user : RegisterUser):Observable<RegisterUser>{
    return this.restTemplate.postForEntity(environment.baseUrl+apiEndpoints.REGISTER_USER,user)
  }


}
