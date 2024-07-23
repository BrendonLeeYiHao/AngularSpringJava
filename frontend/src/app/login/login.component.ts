import { Component } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { User } from '../model/userModel';
import { ApiService } from '../api.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private fb: UntypedFormBuilder, private apiService: ApiService) {
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
      this.apiService.login(user).subscribe((res) => {
        this.response = res.message;
        if (res.message == "true") {
          Swal.fire("Success", "Login Successfully!", "success");
        }
        else {
          Swal.fire("Error", "Incorrect Name or Password!", "error");
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
