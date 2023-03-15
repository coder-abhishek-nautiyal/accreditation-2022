import{Injectable}from'@angular/core';
import {Observable} from 'rxjs';
import {HttpClient,HttpHeaders} from '@angular/common/http';

@Injectable({
providedIn: 'root'
})
export class RestTemplateService {

constructor(private httpClient: HttpClient) { }


    public getServiceHeader(){
        const header={
          headers:new HttpHeaders()
          .set('Content-Type','application/json')
        };
        return header;
    }

  public getForEntity(path:any):Observable<any>{
    return this.httpClient.get(path,this.getServiceHeader());
  }

  public putForEntity(path:any,reqBody:any):Observable<any>{
    return this.httpClient.put(path,reqBody,this.getServiceHeader());
  }

  public deleteForEntity(path:any):Observable<any>{
    return this.httpClient.delete(path,this.getServiceHeader());
  }

  public postForEntity(path:any,reqBody:any):Observable<any>{
    return this.httpClient.post(path,reqBody,this.getServiceHeader());
  }


}


