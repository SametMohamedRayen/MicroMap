import { HttpClient } from '@angular/common/http';
import { Node } from '@angular/compiler';
import { Call } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CallsService {
  getAllNodes():Observable<Node[]> {
    return this.http.get<Node[]>(`${this.apiServerUrl}`);
  }
  getAllNodesNames():Observable<string[]> {
    return this.http.get<string[]>(`${this.apiServerUrl}/names`);
  }
  addNode(formData: FormData): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}`,formData);
  }
  deleteNode(name: string) :Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/${name}`);
  }
  updateCall(id: number, formData:FormData) :Observable<void>{
    return this.http.put<void>(`${this.apiServerUrl}/call/${id}`,formData);
  }
  addCall(formData: FormData) :Observable<Call> {
    return this.http.post<Call>(`${this.apiServerUrl}/call`,formData);
  }
  deleteCall(id: string) :Observable<void> {
      return this.http.delete<void>(`${this.apiServerUrl}/call/${id}`);
  }
  private apiServerUrl=environment.apiBaseUrl;
  public getAllCalls():Observable<Call[]> {
    return this.http.get<Call[]>(`${this.apiServerUrl}/call`);
}
  constructor(private http: HttpClient) { }
}
