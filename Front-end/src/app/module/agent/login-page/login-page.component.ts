import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginBean, LoginControllerService } from 'src/app/models';
import { AuthentificationService } from 'src/app/services/authentification.service';
import * as constant from '../../../services/constants';
import { ApiAgentService } from '../services/api-agent.service';

@Component({
  selector: 'login-page-agent',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageAgentComponent implements OnInit {

  agentLoginFormGroup!: FormGroup;
  clicked: boolean=constant.true_value;

  constructor(
    private auth: AuthentificationService,
    private router: Router,
    private formBuilder: FormBuilder,
    private api: LoginControllerService,
    private apiAgent: ApiAgentService
    //private toast: ToastrService,
    ) {
      this.agentLoginFormGroup = formBuilder.group(
        {
          login: new FormControl(null, [Validators.required, Validators.maxLength(50), Validators.minLength(3)]),
          password: new FormControl(null, [Validators.required, Validators.maxLength(16), Validators.minLength(6)]),
        }
      );
    }

  ngOnInit(): void {
    this.auth.disableHeaderBar();
  }


  get f(): { [key: string]: AbstractControl } {
    return this.agentLoginFormGroup.controls;
  }

  onLogin(){
    this.clicked=!this.clicked;
    let agent: LoginBean = this.initUserBean();
    agent.login = this.f[constant.login].value;
    agent.password = this.f[constant.password].value;
    this.api.connectAgent(agent).subscribe(
      response=>{
        this.apiAgent.agent = response;
        this.router.navigate([constant.agent_entries_list_path]);
      },
      error=>{
        console.error(error);
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
      password: "",
      login: ""
    };
  }

  getPasswordClass(): string{
    if(this.f['password'].touched && this.f['password'].errors != null){
      return 'form-control is-invalid';
    }else if(this.f['password'].valid){
      return 'form-control is-valid';
    }else if(this.f['password'].untouched){
      return 'form-control';
    }else{
      return 'form-control';
    }
  }
  getLoginClass(): string{
    if(this.f['login'].touched && this.f['login'].errors != null){
      return 'form-control is-invalid';
    }else if(this.f['login'].valid){
      return 'form-control is-valid';
    }else if(this.f['login'].untouched){
      return 'form-control';
    }else{
      return 'form-control';
    }
  }

}
