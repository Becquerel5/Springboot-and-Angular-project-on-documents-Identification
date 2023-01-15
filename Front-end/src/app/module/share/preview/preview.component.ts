import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cni, FileControllerService } from 'src/app/models';
import * as constant from '../../../services/constants';
import { ApiClientService } from '../../client/services/api-client.service';
import { environment } from 'src/environments/environment';
import { ApiAgentService } from '../../agent/services/api-agent.service';
import { FileStatus } from '../add-files/add-files.component';

@Component({
  selector: 'app-preview',
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.scss']
})
export class PreviewComponent implements OnInit {

  @Input()
  typeOfUserConnect: string= "";

  @Input()
  id!: number;

  treatementStep = [
    "Registration",
    "Validation",
    "Building",
    "Emition"
  ]
  stepSize=2;
  takeImage = false;

  idRef: number = 0;

  dataReference!: any;
  type: string = "";

  private img: string="../../../../assets/dist/img/user4-128x128.jpg"
  imageStatus: FileStatus = new FileStatus(false,false,false,false,false,"","","Image",0);

  ngOnInit() {
    this.imageStatus.setMessage(this.img);
    if(this.typeOfUserConnect == 'AGENT'){
      if(this.apiAgent.agent==null){
        this.router.navigate([constant.login_agent_path]);
      }
      this.api.getCurrentIssue(this.id).subscribe(
        response=>{
          this.dataReference = response.body;
          this.idRef = this.dataReference?.idDocumentReference!;
          if("country" in response.body){
            this.type = "passPort";
          }else{
            this.type = "cni";
          }
          this.setImage();
        },
        error=>{
          this.router.navigate([constant.login_agent_path]);
        }
      );
    }else if(this.typeOfUserConnect == 'CLIENT'){
      if(this.api.idClient==null){
        this.router.navigate([constant.login_client_path]);
      }
      this.api.getCurrentIssueClient(this.id).subscribe(
        response=>{
          this.dataReference = response.body;
          if("country" in response.body){
            this.type = "passPort";
          }else{
            this.type = "cni";
          }
          this.setImage();
        },
        error=>{
          if(this.typeOfUserConnect=='CLIENT')
            this.router.navigate([constant.login_client_path]);
        }
      );
    }else{
      this.router.navigate([constant.login_client_path]);
    }



  }

  private setImage(){
    if(this.typeOfUserConnect=="AGENT"){
      if(this.dataReference.imageadditionaldocument==null){
        this.imageStatus.setMessage(this.img);
      }else{
        this.img = this.dataReference.imageadditionaldocument.pathDocumentImage;
        this.apiAgent.image(this.img).subscribe(
          response=>{
            if(response.status==200){
              this.img = response.body.response;
              this.imageStatus.setMessage(this.img);
            }
          },error=>{
            //console.log(error)
          }
        );
      }
    }else if(this.typeOfUserConnect=="CLIENT"){
      this.imageStatus.setMessage(this.img);
    }
  }

  constructor(
    private apiAgent: ApiAgentService,
    public api: ApiClientService,
    private router: Router,
    private fileservice: FileControllerService){

  }

 onPrint(){
  if(this.typeOfUserConnect == "CLIENT")
    location.href = environment.basePath+'/users/export/pdf/'+this.id;
  if(this.typeOfUserConnect == "AGENT")
    location.href = environment.basePath+'/agent/export/pdf/'+this.dataReference.client.id;
 }

 onValidate(){
    this.dataReference.statusTreatmentSystemeList = Cni.StatusTreatmentSystemeListEnum.BUILD;
    this.dataReference.statusInTreatment =  Cni.StatusInTreatmentEnum.Ready;
    if(this.type == "cni"){
      this.saveCni(this.dataReference);
    }else if(this.type == "passPort"){
      this.savePassPort(this.dataReference);
    }
 }


 private saveCni(cni: any){
  this.api.saveCni(cni).subscribe(
    response=>{
      if(response.status==201){
        location.href = environment.basePath+'/agent/export/pdf/'+this.dataReference.client.id;
        this.router.navigate([constant.agent_entries_list_path]);
      }
    },
    error=>{
      console.log(error);
    }
  )
}

private savePassPort(passport: any){
  this.api.savePassPort(passport).subscribe(
    response=>{
      if(response.status==201){
        location.href = environment.basePath+'/agent/export/pdf/'+this.dataReference.client.id;
        this.router.navigate([constant.agent_entries_list_path]);
      }
    },
    error=>{
      console.log(error);
    }
  )
}

 onTakePhoto(){
  this.takeImage = true;
 }

 getImageFromComponent(event: any){
  this.takeImage = false;
  if(event==null){
  }
  else{
    this.imageStatus.setMessage(event);
    this.imageStatus.setUpload(true);
    this.imageStatus.setViewUploadBtn(false);
  }
 }

 closeTab(){
  this.takeImage = false;
 }

 uploadFile(){
  this.img = this.imageStatus.getMessage();
  this.uploadImage();
 }

 onReset(){
  this.imageStatus.setMessage(this.img);
  this.imageStatus.setUpload(false);
  this.imageStatus.setViewUploadBtn(false);
  this.imageStatus.setError(false);
 }

 private uploadImage(){
  this.imageStatus.setUpload(false);
  this.imageStatus.setViewUploadBtn(true);
  this.fileservice.uploadImage(this.img ,this.apiAgent.agent.id!,this.id,this.type).subscribe(
      response => {
          this.imageStatus.setUpload(false);
          this.imageStatus.setViewUploadBtn(false);
          this.imageStatus.setError(false);
      },
      error => {
        this.imageStatus.setError(false);
        this.imageStatus.setUpload(false);
        this.imageStatus.setViewUploadBtn(false);
      });

}
private updateStatus(loaded: number, total: number): number {
  return Math.round(100 * loaded / total);
}


}
