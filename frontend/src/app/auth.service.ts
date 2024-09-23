import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ApiService } from './api.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { TranslationService } from './translation.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private apiService: ApiService, private router: Router, private translationService: TranslationService) { }

  private currentUserToken = new BehaviorSubject<string | null>(localStorage.getItem('token') || null);
  public currentToken$ = this.currentUserToken.asObservable();

  isLoggedIn() {
    return !!localStorage.getItem('token');
  }

  updateCurrentUser(token: string) {
    this.currentUserToken.next(token);
  }

  logout() {
    localStorage.removeItem('token');
    this.currentUserToken.next(null);
    this.router.navigate(['/login']);
  }

  validateToken() {
    const token = this.currentUserToken.getValue();
    if (token) {
      const formData = new FormData();
      formData.append('token', token);
      this.apiService.validateToken(formData).subscribe({
        next: (res) => {
          console.log(res.data);
        },
        error: (error) => {
          this.logout();
          Swal.fire({
            title: this.translationService.translates('warning'), 
            text: this.translationService.translates('session_token_expired'), 
            icon: "warning",
            confirmButtonText: this.translationService.translates('ok')
          });
        }
      })
    }
  }
}