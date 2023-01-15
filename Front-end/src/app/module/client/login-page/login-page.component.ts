import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Client, ClientRestControllerService, LoginBean, LoginControllerService } from 'src/app/models';
import * as constant from '../../../services/constants';
import * as clientConstant from '../services/constant-client';
import { AuthentificationClientService } from '../services/authentification-client.service';
import { ApiClientService } from '../services/api-client.service';

@Component({
  selector: 'login-client-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  clientLoginStep = constant.false_value;
  clientFormGroup!: FormGroup;
  clicked: boolean=constant.true_value;
  private id! : number;

  constructor(
    private auth: AuthentificationClientService,
    private router: Router,
    private formBuilder: FormBuilder,
    private api: LoginControllerService,
    private apiClient: ClientRestControllerService,
    private privateApi: ApiClientService
    //private toast: ToastrService,
    ) {
      this.clientFormGroup = formBuilder.group(
        {
          email: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_email)]),
          validationCode: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_code_validation)]),
        }
      );
    }

  ngOnInit(): void {
    this.auth.disableHeaderBar();

  }

  get f(): { [key: string]: AbstractControl } {
    return this.clientFormGroup.controls;
  }

  getEmailButtonClass(): string{
    if(this.f[clientConstant.email].touched && this.f[clientConstant.email].errors != constant.null_value){
      return 'form-control is-invalid';
    }else if(this.f[clientConstant.email].valid){
      return 'form-control is-valid';
    }else if(this.f[clientConstant.email].untouched){
      return 'form-control';
    }else{
      return 'form-control is-invalid';
    }
  }

  getValidtionCodeButtonClass(): string{
    if(this.f[clientConstant.code].touched && this.f[clientConstant.code].errors != constant.null_value){
      return 'form-control is-invalid';
    }else if(this.f[clientConstant.code].valid){
      return 'form-control is-valid';
    }else if(this.f[clientConstant.code].untouched){
      return 'form-control';
    }else{
      return 'form-control is-invalid';
    }
  }

  onLogin(){
    this.clicked=!this.clicked;
    this.api.connectClient(this.f['email'].value).subscribe(
      response=>{
        this.id = response.id;
        this.privateApi.emailClient = this.f['email'].value;
        if(response.personStatus== Client.PersonStatusEnum.ACTIVE){
          this.router.navigate([constant.passport_or_cni_path, this.id]);
        }
        else{
          this.clientLoginStep = !this.clientLoginStep;
          this.clicked=!this.clicked;
        }

      },
      error=>{
        console.log(error)
        if(error.status== 400){
          //this.toast.warning(error.error,constant.warning)
        }else{
          //this.toast.error("Check your connexion",constant.error)
        }
        this.clicked=!this.clicked;
      }
    );
  }

  onValidCode(){
    this.clicked=!this.clicked;
    let client: LoginBean = this.initUserBean();
    client.login = this.f[clientConstant.email].value;
    client.password = this.f[clientConstant.code].value;
    this.apiClient.validateClient(client).subscribe(
      response=>{
        this.router.navigate([constant.passport_or_cni_path,this.id]);
      },
      error=>{

        console.log(error)
        if(error.status== 400){
          //this.toast.warning(error.error,constant.warning)
        }else{
          //this.toast.error("Check your connexion",constant.error)
        }
        this.clicked=!this.clicked;
      }
    );


  }

  private initUserBean(): LoginBean{
    return {
      password: constant.tokenDefaultValue,
      login: constant.tokenDefaultValue
    };
  }


}
