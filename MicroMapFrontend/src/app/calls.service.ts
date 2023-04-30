import { HttpClient } from '@angular/common/http';


import { Injectable } from '@angular/core';
import { environment } from 'environments/environment.prod';
import { Observable } from 'rxjs';
import { Call } from './call';

@Injectable({
  providedIn: 'root'
})
export class CallsService {
  private apiServerUrl=environment.apiBaseUrl+"/api/v1";
  constructor(private http: HttpClient) { }
  
  updateCall(id: number, formData:FormData) :Observable<void>{
    return this.http.put<void>(`${this.apiServerUrl}/call/${id}`,formData);
  }
  addCall(formData: FormData) :Observable<Call> {
    return this.http.post<Call>(`${this.apiServerUrl}/call`,formData);
  }
  deleteCall(id: string) :Observable<void> {
      return this.http.delete<void>(`${this.apiServerUrl}/call/${id}`);
  }

  public getAllCalls():Observable<Call[]> {
    return this.http.get<Call[]>(`${this.apiServerUrl}/call`);
}

}
