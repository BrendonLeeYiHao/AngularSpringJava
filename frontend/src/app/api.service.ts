import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './model/userModel';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private SERVER_URL = "http://localhost:8080"

  constructor(private httpClient: HttpClient) { }

  public register(user: User): Observable<any> {
    return this.httpClient.post(this.SERVER_URL + "/user/register", user);
  }

  public getUserById(id: number): Observable<any> {
    return this.httpClient.get(`${this.SERVER_URL}/user/get-details/${id}`);
  }

  public updateUser(user: User): Observable<any> {
    return this.httpClient.put(this.SERVER_URL + "/user/update", user);
  }

  public deleteUser(id: number): Observable<any> {
    return this.httpClient.delete(`${this.SERVER_URL}/user/delete/${id}`);
  }

  public getAllUser(): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + "/user/get-details");
  }

  public login(user: User): Observable<any> {
    return this.httpClient.post(this.SERVER_URL + "/user/login", user);
  }
}
