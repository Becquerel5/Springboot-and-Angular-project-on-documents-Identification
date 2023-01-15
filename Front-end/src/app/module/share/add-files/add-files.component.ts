import { Component, OnInit, Input } from '@angular/core';
import { FileControllerService, Referencedocument } from 'src/app/models';
import { referenceDoController } from 'src/app/models/api/referenceDoController';
import { HttpEventType, HttpResponse, HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as constant from '../../../services/constants';
import { ApiAgentService } from '../../agent/services/api-agent.service';
import { Router } from '@angular/router';
import { ApiClientService } from '../../client/services/api-client.service';
import { AnyNaptrRecord } from 'dns';

export class FileStatus{
  private view: boolean = false;
  private error: boolean = false;
  private viewProgress: boolean = false;
  private viewUploadBtn: boolean = false;
  private upload: boolean = false;
  private message: string = "";
  private filePath: string | ArrayBuffer | null;
  private typeName: string = "";
  private progress: number = 0;
  private file!: File;

  constructor(viewUploadBtn: boolean, error: boolean, viewProgress: boolean, view: boolean, upload: boolean, message: string, filePath: string, typeName: string, progress: number){
    this.error = error;
    this.viewUploadBtn = viewUploadBtn;
    this.viewProgress = viewProgress;
    this.view = view;
    this.upload = upload;
    this.message = message;
    this.filePath = filePath;
    this.typeName = typeName;
    this.progress = progress;

  }

  getViewProgress(){
    return this.viewProgress;
  }

  getViewUploadBtn(){
    return this.viewUploadBtn;
  }

  getError(){
    return this.error;
  }

  getView(){
    return this.view;
  }

  getUpload(){
    return this.upload;
  }

  getMessage(){
    return this.message;
  }

  getFilePath(){
    return this.filePath;
  }

  getTypeName(){
    return this.typeName;
  }

  getFile(){
    return this.file;
  }

  getProgress(){
    return this.progress;
  }

  setViewProgress(viewProgress: boolean){
    this.viewProgress = viewProgress;
  }

  setViewUploadBtn(viewUploadBtn: boolean){
    this.viewUploadBtn = viewUploadBtn;
  }

  setView(view: boolean){
    this.view = view;
  }

  setError(error: boolean){
    this.error = error;
  }
  setUpload(upload: boolean){
    this.upload = upload;
  }

  setMessage(message: string){
    this.message = message;
  }

  setFilePath(filePath: string | ArrayBuffer | null){
    this.filePath = filePath;
  }

  setTypeName(typeName: string){
    this.typeName = typeName;
  }


  setFile(file: File){
    this.file = file;
  }

  setProgress(progress: number){
    this.progress = progress;
  }
}

@Component({
  selector: 'app-add-files',
  templateUrl: './add-files.component.html',
  styleUrls: ['./add-files.component.scss']
})
export class AddFilesComponent implements OnInit {
  @Input()
  type!: string;

  @Input()
  id!: number;


  dataReference: any;
  birthCertificateStatus: FileStatus = new FileStatus(false,false,false,false,false,"","","Birth Certificate",0);
  lostCertificateStatus: FileStatus = new FileStatus(false,false,false,false,false,"","","Lost Certificate",0);
  nationalCertificateStatus: FileStatus = new FileStatus(false,false,false,false,false,"","","National Certificate",0);
  fileStatus: FileStatus = new FileStatus(false,false,false,false,false,"","","",0);

  fileInfos?: Observable<any>;


  constructor(
    private api: ApiAgentService,
    private apiClient: ApiClientService,
    private router: Router,
    private fileservice: FileControllerService

    ) { }

  ngOnInit(): void {
    if(this.api.agent==null){
      this.router.navigate([constant.login_agent_path]);
    }else if(this.id == 0){
      this.router.navigate([constant.agent_entries_list_path]);
    }
  }


  private onGetReferenceDocument(){
    this.apiClient.getCurrentIssue(this.id).subscribe(
      response=>{
      },
      error=>{
        this.router.navigate([constant.login_agent_path]);
      }
    );
  }

  selectBirthCertificateFile(event: any) {
    if(event.target.files[0] != null){
      this.birthCertificateStatus.setView(true);
      this.birthCertificateStatus.setUpload(true);
      this.birthCertificateStatus.setViewUploadBtn(false);
      this.birthCertificateStatus.setProgress(0);
      this.birthCertificateStatus.setFilePath(event?.target?.files[0].name);
      this.birthCertificateStatus.setFile(event?.target?.files[0]);
    }
  }

  selectLostCertificateFile(event: any) {
    if(event.target.files[0] != null){
      this.lostCertificateStatus.setView(true);
      this.lostCertificateStatus.setUpload(true);
      this.lostCertificateStatus.setViewUploadBtn(false);
      this.lostCertificateStatus.setProgress(0);
      this.lostCertificateStatus.setFilePath(event?.target?.files[0].name);
      this.lostCertificateStatus.setFile(event?.target?.files[0]);
    }
  }

  selectNationalCertificateFile(event: any) {
    if(event.target.files[0] != null){
      this.nationalCertificateStatus.setView(true);
      this.nationalCertificateStatus.setUpload(true);
      this.nationalCertificateStatus.setViewUploadBtn(false);
      this.nationalCertificateStatus.setProgress(0);
      this.nationalCertificateStatus.setFilePath(event?.target?.files[0].name);
      this.nationalCertificateStatus.setFile(event?.target?.files[0]);
    }
  }

  onPreview(fileStatus: FileStatus){
    this.fileStatus = fileStatus;
    let mimeType = this.fileStatus.getFile().type;
    if (mimeType.match(/image\/*/) == null) {
      //not image
    }else{
    }
    let reader = new FileReader();
    reader.readAsDataURL(this.fileStatus.getFile());
    reader.onload = (_event) => {
      this.fileStatus.setFilePath(reader.result);
    }
  }

  onUploadFile(fileStatus: FileStatus){
    if(fileStatus.getTypeName()==='Birth Certificate'){
      this.uploadBirthCertificate();
    }else if(fileStatus.getTypeName()==='Lost Certificate'){
      this.uploadLostCertificate();
    }else if(fileStatus.getTypeName()==='National Certificate'){
      this.uploadNationalCertificate();
    }
  }

  private uploadBirthCertificate(){
    this.birthCertificateStatus.setUpload(false);
    this.birthCertificateStatus.setViewUploadBtn(true);
    this.birthCertificateStatus.setProgress(0);
    this.birthCertificateStatus.setViewProgress(true);
    this.birthCertificateStatus.setMessage("");
    let formData=new FormData();
      formData.delete('files');
      formData.append('files',this.birthCertificateStatus.getFile());
       this.fileservice.uploadBirthCertificate(formData,this.api.agent.id!,this.id,this.type).subscribe(
        (event: any) => {
          if (event.type === HttpEventType.UploadProgress) {
            this.birthCertificateStatus.setProgress(this.updateStatus(event.loaded, event.total!));
          } else if (event instanceof HttpResponse) {
            this.birthCertificateStatus.setMessage(event.body.reponse);
            this.birthCertificateStatus.setUpload(false);
            this.birthCertificateStatus.setViewUploadBtn(false);
            this.birthCertificateStatus.setError(false);
          }
          if(this.birthCertificateStatus.getProgress()==100){
            setTimeout(
              ()=>{
                this.birthCertificateStatus.setViewProgress(false);
              },
              5000
            );
          }
        },
        (err: any) => {
          this.birthCertificateStatus.setMessage(err.error.message);
          this.birthCertificateStatus.setError(true);
          this.birthCertificateStatus.setUpload(true);
          this.birthCertificateStatus.setViewUploadBtn(false);
        });
  }

  private uploadLostCertificate(){
    this.lostCertificateStatus.setUpload(false);
    this.lostCertificateStatus.setViewUploadBtn(true);
    this.lostCertificateStatus.setProgress(0);
    this.lostCertificateStatus.setViewProgress(true);
    this.lostCertificateStatus.setMessage("");
    let formData=new FormData();
      formData.delete('files');
      formData.append('files',this.lostCertificateStatus.getFile());
       this.fileservice.uploadLostcertificate(formData,this.api.agent.id!,this.id,this.type).subscribe(
        (event: any) => {
          if (event.type === HttpEventType.UploadProgress) {
            this.lostCertificateStatus.setProgress(this.updateStatus(event.loaded, event.total!));
          } else if (event instanceof HttpResponse) {
            this.lostCertificateStatus.setMessage(event.body.reponse);
            this.lostCertificateStatus.setUpload(false);
            this.lostCertificateStatus.setViewUploadBtn(false);
            this.lostCertificateStatus.setError(false);
          }
          if(this.lostCertificateStatus.getProgress()==100){
            setTimeout(
              ()=>{
                this.lostCertificateStatus.setViewProgress(false);
              },
              5000
            );
          }
        },
        (err: any) => {
          this.lostCertificateStatus.setMessage(err.error.message);
          this.lostCertificateStatus.setError(true);
          this.lostCertificateStatus.setUpload(true);
          this.lostCertificateStatus.setViewUploadBtn(false);
        });
  }

  private uploadNationalCertificate(){
    this.nationalCertificateStatus.setUpload(false);
    this.nationalCertificateStatus.setViewUploadBtn(true);
    this.nationalCertificateStatus.setProgress(0);
    this.nationalCertificateStatus.setViewProgress(true);
    this.nationalCertificateStatus.setMessage("");
    let formData=new FormData();
      formData.delete('files');
      formData.append('files',this.nationalCertificateStatus.getFile());
       this.fileservice.uploadNationalcertificate(formData,this.api.agent.id!,this.id,this.type).subscribe(
        (event: any) => {
          if (event.type === HttpEventType.UploadProgress) {
            this.nationalCertificateStatus.setProgress(this.updateStatus(event.loaded, event.total!));
          } else if (event instanceof HttpResponse) {
            this.nationalCertificateStatus.setMessage(event.body.reponse);
            this.nationalCertificateStatus.setUpload(false);
            this.nationalCertificateStatus.setViewUploadBtn(false);
            this.nationalCertificateStatus.setError(false);
          }
          if(this.nationalCertificateStatus.getProgress()==100){
            setTimeout(
              ()=>{
                this.nationalCertificateStatus.setViewProgress(false);
              },
              5000
            );
          }
        },
        (err: any) => {
          this.nationalCertificateStatus.setMessage(err.error.message);
          this.nationalCertificateStatus.setError(true);
          this.nationalCertificateStatus.setUpload(true);
          this.nationalCertificateStatus.setViewUploadBtn(false);
        });
  }

  private updateStatus(loaded: number, total: number): number {
    return Math.round(100 * loaded / total);
  }

  /**
   * format bytes
   * @param bytes (File size in bytes)
   * @param decimals (Decimals point)
   */
  formatBytes(bytes: number | undefined) {
    if (bytes == null) {
      return '';
    }else if (bytes === 0) {
      return '0 Bytes';
    } else if (bytes != null) {
      let decimals: any = 2;
      const k = 1024;
      const dm = decimals <= 0 ? 0 : decimals || 2;
      const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
      const i = Math.floor(Math.log(bytes) / Math.log(k));
      return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
    }else{
      return '';
    }
  }

}
