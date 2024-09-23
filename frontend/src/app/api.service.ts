import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './model/userModel';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private SERVER_URL = "http://localhost:8080"
  // private SERVER_URL = "https://javaspringbackend.azurewebsites.net"
  // private SERVER_URL = "https://localhost:7277"

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

  public getAllUser(): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + "/user/get-details");
  }

  public login(user: User): Observable<any> {
    return this.httpClient.post(this.SERVER_URL + "/user/login", user);
  }


  // With DTO (Currently In Use)
  public registerDTO(user: User): Observable<any> {
    return this.httpClient.post(this.SERVER_URL + "/user/register-dto", user);
  }

  public getAllUserDTO(): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + "/user/get-details-dto");
  }

  public updateUserDTO(user: User): Observable<any> {
    return this.httpClient.put(this.SERVER_URL + "/user/update-dto", user);
  }

  public deleteUser(id: number): Observable<any> {
    return this.httpClient.delete(`${this.SERVER_URL}/user/delete/${id}`);
  }

  public loginDTO(user: User): Observable<any> {
    return this.httpClient.post(this.SERVER_URL + "/user/login-dto", user);
  }

  public setLanguage(formData: any): Observable<any> {
    return this.httpClient.put(this.SERVER_URL + "/language/locale", formData);
  }
  // public loginDTO(loginRequest: any): Observable<any> {
  //   return this.httpClient.post(this.SERVER_URL + "/api/login", loginRequest);
  // }

  public uploadFile(formData: any): Observable<any> {
    return this.httpClient.post(this.SERVER_URL + "/file/upload", formData);
  }

  public getFile(id: number): Observable<Blob> {
    return this.httpClient.get(`${this.SERVER_URL}/file/get-file/${id}`, { responseType: 'blob'});
  }

  public getAllFiles(): Observable<any> {
    return this.httpClient.get(`${this.SERVER_URL}/file/get-files`)
  }

  public deleteFile(id: number): Observable<any> {
    return this.httpClient.delete(`${this.SERVER_URL}/file/delete/${id}`);
  }

  public validateToken(formData: any): Observable<any> {
    return this.httpClient.post(this.SERVER_URL + "/user/validate-token", formData);
  }
}
