import { HttpClient } from '@angular/common/http';
import { Call } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CallsService {
  delete(id: string) :Observable<void> {
      return this.http.delete<void>(`${this.apiServerUrl}call/${id}`);
  }
  private apiServerUrl=environment.apiBaseUrl;
  public getAll():Observable<Call[]> {
    return this.http.get<Call[]>(`${this.apiServerUrl}call`);
}
  constructor(private http: HttpClient) { }
}
