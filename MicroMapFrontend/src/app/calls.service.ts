import { HttpClient, HttpHeaders } from '@angular/common/http';


import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Call } from './call';
import { KeycloakService } from 'keycloak-angular';
import { environment } from 'environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class CallsService {
  private apiServerUrl=environment.apiBaseUrl+"/api/v1";
  constructor(private http: HttpClient , private keycloakService:KeycloakService) { }
  
  updateCall(id: number, formData:FormData) :Observable<void>{
    
    return this.http.put<void>(`${this.apiServerUrl}/call/update/${id}`,formData);
  }
  addCall(formData: FormData) :Observable<Call> {
    return this.http.post<Call>(`${this.apiServerUrl}/call/add`,formData);
  }
  deleteCall(id: string) :Observable<void> {
      return this.http.delete<void>(`${this.apiServerUrl}/call/delete/${id}`);
  }
  deleteAll() :Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/call/delete`);
}
  public getAllCalls():Observable<Call[]> {
    console.log(this.keycloakService.getToken());
    return this.http.get<Call[]>(`${this.apiServerUrl}/call/getall`);
}


}
