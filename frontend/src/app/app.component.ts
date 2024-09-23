import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';
import Swal from 'sweetalert2';
import { TranslationService } from './translation.service';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  constructor(private translationService: TranslationService, private apiService: ApiService, public authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.validateToken();
  }

  switchLanguage(lang: string) {
    this.translationService.setLanguage(lang);
    const formData = new FormData();
    formData.append('language', lang);
    this.apiService.setLanguage(formData).subscribe(res => {
      console.log(res.message);
    })
  }

  toggleMenu() {
    var menu = document.getElementById("mobile-menu");
    if (menu!.classList.contains("hidden")) {
      menu!.classList.remove("hidden");
    } else {
      menu!.classList.add("hidden");
    }
  }

  logout() {
    this.authService.logout();
  }

  mobileLogOut() {
    this.toggleMenu();
    this.logout();
  }
}
