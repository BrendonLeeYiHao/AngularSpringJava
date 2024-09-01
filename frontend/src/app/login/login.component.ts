import { Component } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { User } from '../model/userModel';
import { ApiService } from '../api.service';
import Swal from 'sweetalert2';
import { TranslationService } from '../translation.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private fb: UntypedFormBuilder, private apiService: ApiService, private translationService: TranslationService) {
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      password: [null, [Validators.required]]
    });
  }

  validateForm!: UntypedFormGroup;
  response: any;

  submitForm() {
    if (this.validateForm.valid) {
      const user: User = {
        id: 0,
        name: this.validateForm.value.name,
        password: this.validateForm.value.password,
        email: "",
        dob: "",
        gender: ""
      }
      this.apiService.loginDTO(user).subscribe((res) => {
        this.response = res.message;
        if (res.message == "true") {
          Swal.fire({
            title: this.translationService.translates('success'), 
            text: this.translationService.translates('login_successfully'), 
            icon: "success",
            confirmButtonText: this.translationService.translates('ok')
          });
        }
        else {
          Swal.fire({
            title: this.translationService.translates('error'), 
            text: this.translationService.translates('incorrect_credentials'), 
            icon: "error",
            confirmButtonText: this.translationService.translates('ok')
          });
        }
      })
    } else {
      Object.values(this.validateForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }
}
