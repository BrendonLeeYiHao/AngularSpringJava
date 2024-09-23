import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { ApiService } from '../api.service';
import Swal from 'sweetalert2';
import { TranslationService } from '../translation.service';

@Component({
  selector: 'app-fileupload',
  templateUrl: './fileupload.component.html',
  styleUrls: ['./fileupload.component.css']
})
export class FileuploadComponent implements OnInit {

  uploadForm!:UntypedFormGroup;

  constructor(private apiService: ApiService, private fb: UntypedFormBuilder, private translationService: TranslationService) {
    this.uploadForm = this.fb.group({
      uploadImage: [null, Validators.required]
    })
  }

  ngOnInit(): void {
    this.getImages();
  }

  image!: File;
  fileSizeError!: boolean;
  selectedImage: any;
  response: any;

  imageUrl: any;

  onChange(event: any) {
    this.fileSizeError = false;
    if(event.target.files[0] != undefined) {
      this.image = event.target.files[0];
      const maxSize = 2000000;
      if(this.image.size > maxSize) {
        this.fileSizeError = true;
        this.uploadForm.controls['uploadImage'].reset();
        this.selectedImage = null;
      } else {
        const reader = new FileReader();
        reader.onload = (e: any) => {
          this.selectedImage = e.target.result;
        };
        reader.readAsDataURL(this.image);
      }
    } else {
      this.selectedImage = null;
    }
  }

  upload() {
    if(this.uploadForm.valid) {
      const formData = new FormData();
      formData.append('file', this.image);
      this.apiService.uploadFile(formData).subscribe({
        next: (res) => {
          this.response = res.data;
          Swal.fire({
            title: this.translationService.translates('success'),
            text: this.translationService.translates("file_uploaded_successfully"),
            icon: "success",
            confirmButtonText: this.translationService.translates('ok')
          });
          this.imageList = [...this.imageList, {id: res.data.id, name: res.data.name, content: res.data.content, image: URL.createObjectURL(this.image)}];
          this.uploadForm.reset();
          this.selectedImage = null;
        },
        error: (error) => {
          console.log(error);
        }
      })
    }
    else {
      this.fileSizeError = false;
      Object.values(this.uploadForm.controls).forEach(control => {
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

  getImage() {
    this.apiService.getFile(1).subscribe(
      res => {
        console.log(res);
        const url = URL.createObjectURL(res);
        this.imageUrl = url;
      }
    )
  }

  imageList: { id: number, name: string, content: ArrayBuffer, image: string }[] = [];
  imagesTempList: any[] = [];

  getImages() {
    this.apiService.getAllFiles().subscribe(res => {
        this.imagesTempList = res.data;
        this.imageList = this.imagesTempList.map(item => {
          let imageUrl = '';
          if (item.content) {
            const binaryString = window.atob(item.content);

            // Convert binary string to a Uint8Array
            const bytes = new Uint8Array(binaryString.length);
            for (let i = 0; i < binaryString.length; i++) {
                bytes[i] = binaryString.charCodeAt(i);
            }

            // Create a Blob from the Uint8Array
            const blob = new Blob([bytes], { type: 'image/png' });
            imageUrl = URL.createObjectURL(blob);
          }
          return { ...item, image: imageUrl };
        })
      }
    )
  }

  deleteFile(id: number) {
    this.apiService.deleteFile(id).subscribe({
      next: (res) => {
        Swal.fire({
          title: this.translationService.translates('success'), 
          text: this.translationService.translates('delete_successfully'), 
          icon: "success",
          confirmButtonText: this.translationService.translates('ok')
        });
        this.imageList = this.imageList.filter(item => item.id != id);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }
}