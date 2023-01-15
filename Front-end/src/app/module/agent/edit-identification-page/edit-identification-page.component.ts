import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FileControllerService, Referencedocument } from 'src/app/models';
import { referenceDoController } from 'src/app/models/api/referenceDoController';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Additionaldocument } from 'src/app/models/model/additionaldocument';

@Component({
  selector: 'app-edit-identification-page',
  templateUrl: './edit-identification-page.component.html',
  styleUrls: ['./edit-identification-page.component.scss']
})
export class EditIdentificationPageComponent implements OnInit {

  idDocumentReference!: number;
  referencedocument!: Referencedocument;
  //additionaldocument!: Additionaldocument;
  additionaldocument: Additionaldocument = new Additionaldocument();

  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';



  public birthFile: any = File;
  public nationalFile: any = File;
  public lostFile: any = File;
  public imageFile: any = File;

  fileInfos?: Observable<any>;


  constructor(
    private referenceservice: referenceDoController,
    private router: Router,private route: ActivatedRoute,
    private fileservice: FileControllerService

    ) { }

  ngOnInit(): void {
    this.fileInfos = this.fileservice.getFiles();
    this.idDocumentReference = this.route.snapshot.params['idDocumentReference'];
   // this.referencedocument = new Referencedocument();
    this.referenceservice.getReferentDocById(this.idDocumentReference).subscribe( data=>{
      this.referencedocument = data;
    });
  }
  selectFile1(event:any){
    const file = event.target.files[0];
    this.birthFile=file;
    console.log(file);
  }

  selectFile2(event:any){
    const file = event.target.files[0];
    this.lostFile=file;
    console.log(file);
  }
  selectFile3(event:any){
    const file = event.target.files[0];
    this.nationalFile=file;
    console.log(file);
  }
  selectFile4(event:any){
    const file = event.target.files[0];
    this.imageFile=file;
    console.log(file);
  }

  selectFile(event: any) {
    this.selectedFiles = event.target.files;
    }

    upload(): void {
    this.progress = 0;

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
      this.currentFile = file;

      this.fileservice.upload(this.currentFile).subscribe(
        (event: any) => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.fileInfos = this.fileservice.getFiles();
        }
        },
        (err: any) => {
        console.log(err);
        this.progress = 0;

        if (err.error && err.error.message) {
          this.message = err.error.message;
        } else {
          this.message = 'Could not upload the file!';
        }

        this.currentFile = undefined;
        });
      }

      this.selectedFiles = undefined;
    }
    }
/*
  uploadbirthcertificate(): void {
    this.progress = 0;

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
      this.currentFile = file;

      const formData=new FormData();
      formData.append('birthCertificate_hecto',this.birthFile);


      this.fileservice.uploadBirthCertificate(this.currentFile).subscribe(
        (event: any) => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.fileInfos = this.fileservice.getFiles();
        }
        },
        (err: any) => {
        console.log(err);
        this.progress = 0;

        if (err.error && err.error.message) {
          this.message = err.error.message;
        } else {
          this.message = 'Could not upload the file!';
        }

        this.currentFile = undefined;
        });
      }

      this.selectedFiles = undefined;
    }
    }

  uploadlostcertificate(){

  }

  uploadNationalcertificate(){

  }

  uploadImagecertificate(){

  } */

}
