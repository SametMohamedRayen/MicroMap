import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from "src/environments/environment";
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class MicroserviceService {
 
  private apiServerUrl=environment.baseurl;
    constructor(private http: HttpClient ){}
    public getNames():Observable<string[]>{
      return this.http.get<string[]>(`${this.apiServerUrl}/api/v1/microservice/names`);
  }
  public post(data:FormData):Observable<void>{
    return this.http.post<void>(`${this.apiServerUrl}/api/v1/microservice`,data);
}
public delete(name:string):Observable<void>{
  return this.http.delete<void>(`${this.apiServerUrl}/api/v1/microservice/${name}`);
}
}
