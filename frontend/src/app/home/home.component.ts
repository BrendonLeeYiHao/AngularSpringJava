import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { UntypedFormBuilder, Validators, UntypedFormGroup, AbstractControl } from '@angular/forms';
import Swal from 'sweetalert2';
import { ApiService } from '../api.service';
import { User } from '../model/userModel';
import { TranslationService } from '../translation.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  registerForm!: UntypedFormGroup;
  response: any;
  passwordVisible = false;
  showDate!: Date;
  searchInput: any;
  userProfile: any;
  messageTitle: any;
  existingNameList: string[] = [];
  userList: any[] = [];
  errorMsg: any = {};

  constructor(private fb: UntypedFormBuilder, private apiService: ApiService, private datePipe: DatePipe, private translationService: TranslationService) {
    this.registerForm = this.fb.group({
        name: [null, [Validators.required, Validators.pattern("^(?!.*[^a-zA-Z ])(?=[A-Z].*)(?!.* {2}.*)(?!.*[A-Z]{2}.*)(?!.*[a-z][A-Z].*)(?!.*[A-Z] .*)(?!.* [a-z].*).{2,79}[a-z]$"),
          (control: AbstractControl<any, any>) => this.uniqueName(control, this.existingNameList)
        ]],
        password: [null, [Validators.required]],
        email: [null, [Validators.required, Validators.email]],
        dob: [null, [Validators.required]],
        gender: [null, [Validators.required]]
      })
  }

  ngOnInit(): void {
    this.apiService.getAllUserDTO().subscribe((res) => {
      this.userList = res.data;
      this.existingNameList = this.userList.map(user => user.name);
    })
    const today = new Date();
    const minDate = new Date(today.getFullYear() - 18, today.getMonth(), today.getDate() + 1);
    this.showDate = minDate;

    this.registerForm.valueChanges.subscribe(() => {
      this.clearErrorMessages();
    })
  }

  private clearErrorMessages(): void {
    if(this.registerForm.get('name')) {
      delete this.errorMsg.name;
    }
    if(this.registerForm.get('password')) {
      delete this.errorMsg.password;
    }
    if(this.registerForm.get('email')) {
      delete this.errorMsg.email;
    }
    if(this.registerForm.get('dob')) {
      delete this.errorMsg.dob;
    }
    if(this.registerForm.get('gender')) {
      delete this.errorMsg.gender;
    }
  }

  uniqueName(control: AbstractControl, existingNameList:string[]): {[key:string]:any} | null{
    const name = control.value;  
    if(existingNameList.includes(name)){
      return { uniqueName: true};
    }
    return null;
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

      this.apiService.registerDTO(newUser).subscribe({
        next: (res) => {
          this.response = res.message;
          if (this.response) {
              Swal.fire({
              title: this.translationService.translates("success"),
              text: this.translationService.translates("register_successfully"),
              icon: 'success',
              confirmButtonText: this.translationService.translates("ok")
            });
            this.existingNameList.push(newUser.name);
            this.registerForm.reset();
          }
        },
        error: (err) => {
          this.errorMsg = err.error.error;
          // console.log(err);
          // For ASP.NET
          // this.errorMsg = err.error.errors;
          // in HTML, need to use toString() as given is array
        }
      })
    }
    else {
      Object.values(this.registerForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
          Swal.fire({
            title: this.translationService.translates('error'),
            text: this.translationService.translates("please_fill_up_the_fields_correctly"),
            icon: "error",
            confirmButtonText: this.translationService.translates('ok')
          });
        }
      }); 
    }
  }


  disabledDate = (current: Date): boolean => {
    const today = new Date();
    const minDate = new Date(today.getFullYear() - 18, today.getMonth(), today.getDate() + 1);
    return current.getTime() > minDate.getTime();
  }
}