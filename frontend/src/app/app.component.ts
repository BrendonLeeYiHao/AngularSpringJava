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
export class AppComponent implements OnInit {
  
  constructor(private fb: UntypedFormBuilder, private apiService: ApiService, private datePipe: DatePipe, private translationService: TranslationService) {
    this.registerForm = this.fb.group({
        name: [null, [Validators.required, Validators.pattern("^(?!.*[^a-zA-Z ])(?=[A-Z].*)(?!.* {2}.*)(?!.*[A-Z]{2}.*)(?!.*[a-z][A-Z].*)(?!.*[A-Z] .*)(?!.* [a-z].*).{2,79}[a-z]$")]],
        password: [null, [Validators.required]],
        email: [null, [Validators.required, Validators.email]],
        dob: [null, [Validators.required]],
        gender: [null, [Validators.required]]
      })
  }

  title = "App";
  registerForm!: UntypedFormGroup;
  response: any;
  passwordVisible = false;
  showDate!: Date;
  searchInput: any;
  userProfile: any;
  messageTitle: any;

  ngOnInit(): void {
    const today = new Date();
    const minDate = new Date(today.getFullYear() - 18, today.getMonth(), today.getDate() + 1);
    this.showDate = minDate;
  }

  register() {
    if(this.registerForm.valid) {
      const newUser: User = {
        id: 0,
        name: this.registerForm.value.name,
        password: this.registerForm.value.password,
        email: this.registerForm.value.email,
        dob: this.datePipe.transform(this.registerForm.value.dob, 'yyyy-MM-dd'),
        gender: this.registerForm.value.gender
      }

      this.apiService.register(newUser).subscribe((res) => {
        this.response = res.message;
        console.log(this.response);
        Swal.fire(this.translationService.translates("success"), this.response, "success");
      })
    }
    else {
      Object.values(this.registerForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
          Swal.fire("Error","Please fill up the fields correctly!","error");
        }
      }); 
    }
  }


  disabledDate = (current: Date): boolean => {
    const today = new Date();
    const minDate = new Date(today.getFullYear() - 18, today.getMonth(), today.getDate() + 1);
    return current.getTime() > minDate.getTime();
  }

  searchById() {
    if(this.searchInput != null) {
      this.apiService.getUserById(this.searchInput).subscribe((res) => {
        console.log(res);
        this.userProfile = res;
        this.registerForm.controls['name'].setValue(this.userProfile.name);
        this.registerForm.controls['password'].setValue(this.userProfile.password);
        this.registerForm.controls['email'].setValue(this.userProfile.email);
        const retrievedDate = new Date(this.userProfile.dob);
        this.registerForm.controls['dob'].setValue(retrievedDate);
        this.registerForm.controls['gender'].setValue(this.userProfile.gender);
      })
    }
  }

  switchLanguage(lang: string) {
    this.translationService.setLanguage(lang);
  }
}
