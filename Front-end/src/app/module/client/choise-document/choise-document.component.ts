import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as constant from '../../../services/constants';
import { ApiClientService } from '../services/api-client.service';
import { ReferenceDocumentControllerService, Cni, Passport } from 'src/app/models';

@Component({
  selector: 'app-choise-document',
  templateUrl: './choise-document.component.html',
  styleUrls: ['./choise-document.component.scss']
})
export class ChoiseDocumentComponent implements OnInit {

  loading : boolean = constant.true_value;
  idClient: number = 0;
  email!: string;

  //inject document controller here

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private apiClient: ReferenceDocumentControllerService,
    private api: ApiClientService
  ) { }

  ngOnInit(): void {
    this.email = this.api.emailClient;
    this.idClient = this.route.snapshot.params[constant.id];
    this.api.getCurrentIssueClient(this.idClient).subscribe(
      response=>{
        if(response.status==200){
          this.api.idClient=this.idClient;
          if(response.body.statusTreatmentSystemeList == Cni.StatusTreatmentSystemeListEnum.FORM){
            if("posteIdentification" in response.body){
              this.router.navigate([constant.client_form_path,constant.cni]);
            }else if("country" in response.body){
              this.router.navigate([constant.client_form_path,constant.passport]);
            }
          }else if(response.body.statusTreatmentSystemeList == Cni.StatusTreatmentSystemeListEnum.FINISH){
            this.router.navigate([constant.login_client_path]);
          }else{
            this.router.navigate([constant.client_preview_path]);
          }
        }
      },error=>{
        console.log(error.body);
      }
    );
  }

  onSetPassPort(){
    this.initPassport();
  }
  onSetCni(){
    this.initCni();
  }

  private initPassport(){
    this.api.initPassPort(this.idClient).subscribe(
      response=>{
        if(response.status==204){
          this.api.idClient=this.idClient;
          this.router.navigate([constant.client_form_path,constant.passport]);
        }
      }
    );
  }

  private initCni(){
    this.api.initCni(this.idClient).subscribe(
      response=>{
        if(response.status==204){
          this.api.idClient=this.idClient;
          this.router.navigate([constant.client_form_path,constant.cni]);
        }
      }
    );

  }

}
