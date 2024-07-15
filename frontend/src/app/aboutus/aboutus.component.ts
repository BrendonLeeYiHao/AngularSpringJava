import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { User } from '../model/userModel';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-aboutus',
  templateUrl: './aboutus.component.html',
  styleUrls: ['./aboutus.component.css']
})
export class AboutusComponent implements OnInit{

  constructor(private apiService: ApiService, private fb: FormBuilder, private datePipe: DatePipe) {

  }

  userList: any[] = [];
  isVisible = false;
  updatedForm!: FormGroup;
  response:any;
  passwordVisible = false;

  ngOnInit(): void {
      this.apiService.getAllUser().subscribe((res) => {
        this.userList = res;
        this.initializeForm();
      })
  }

  initializeForm() {
    this.updatedForm = this.fb.group({
      id: [null],
      name: [null],
      password: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      dob: [null],
      gender: [null]
    })
  }

  openUpdateModal(user:User){
    this.isVisible = true;
    this.updatedForm.controls['id'].setValue(user.id);
    this.updatedForm.controls['name'].setValue(user.name);
    this.updatedForm.controls['password'].setValue(user.password);
    this.updatedForm.controls['email'].setValue(user.email);
    const formattedDob = this.datePipe.transform(user.dob, 'yyyy-MM-dd');
    this.updatedForm.controls['dob'].setValue(formattedDob);
    this.updatedForm.controls['gender'].setValue(user.gender);
  }

  openDeleteModal(id:number){
    Swal.fire({
      title: 'Do you wish to delete account of ID ' + id,
      icon: 'question',
      showConfirmButton:true,
      confirmButtonColor: 'green',
      confirmButtonText: 'Yes',
      showCloseButton:true,
      showCancelButton:true,
      cancelButtonColor: '#d33',
      cancelButtonText: 'No'
    }).then((result) =>{
      if(result.isConfirmed){
        this.apiService.deleteUser(id).subscribe((res) => {
          this.response = res.message;
          Swal.fire('Success', this.response, "success");
          this.userList = this.userList.filter(user => user.id != id);
          this.initializeForm();
        })
      }
    })
  }

  handleCancel(){
    this.isVisible = false;
    this.initializeForm();
  }

  handleOk(){
    if(this.updatedForm.valid){
      const updatedUser: User = {
        id: this.updatedForm.value.id,
        name: this.updatedForm.value.name,
        password: this.updatedForm.value.password,
        email: this.updatedForm.value.email,
        dob: this.updatedForm.value.dob,
        gender: this.updatedForm.value.gender
      };

      this.apiService.updateUser(updatedUser).subscribe((res) =>{
        this.response = res.message;
        Swal.fire("Success", this.response, "success");    
        this.userList = this.userList.map(
          user => user.id === this.updatedForm.value.id ? {
            ...user, ...updatedUser
          } : user
        );
        this.isVisible = false;
        this.initializeForm();
      })
    }
    else{
      Object.values(this.updatedForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
          Swal.fire("Error","Please fill up the fields correctly!","error");
        }
      }); 
    }
  }
}
