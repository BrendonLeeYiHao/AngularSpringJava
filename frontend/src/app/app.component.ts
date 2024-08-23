import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { User } from './model/userModel';
import { DatePipe } from '@angular/common';
import { ApiService } from './api.service';
import Swal from 'sweetalert2';
import { TranslationService } from './translation.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  constructor(private translationService: TranslationService) {}

  isLogin: boolean = false

  switchLanguage(lang: string) {
    this.translationService.setLanguage(lang);
  }

  toggleMenu() {
    var menu = document.getElementById("mobile-menu");
    if (menu!.classList.contains("hidden")) {
      menu!.classList.remove("hidden");
    } else {
      menu!.classList.add("hidden");
    }
  }
}
