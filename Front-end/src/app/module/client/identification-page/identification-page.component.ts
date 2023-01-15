import { Component, OnInit } from '@angular/core';

import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Cni, Passport, Referencedocument } from 'src/app/models';
import { referenceDoController } from 'src/app/models/api/referenceDoController';
import * as constant from '../../../services/constants';
import * as clientConstant from '../services/constant-client';


@Component({
  selector: 'app-identification-page',
  templateUrl: './identification-page.component.html',
  styleUrls: ['./identification-page.component.scss']
})
export class IdentificationPageComponent implements OnInit {


  name: string = constant.tokenDefaultValue;

  loading : boolean = constant.true_value;

  constructor(
    private route: ActivatedRoute,
    private router: Router

  ) {
   }


  ngOnInit(): void {
    if(this.route.snapshot.params[constant.name]==constant.cni){
      this.name = this.route.snapshot.params[constant.name];
      this.loading = constant.true_value;
    }else if(this.route.snapshot.params[constant.name]==constant.passport){
      this.name = this.route.snapshot.params[constant.name];
      this.loading = constant.true_value;

    }else{
      this.router.navigate([constant.passport_or_cni_path]);
    }
  }


  onSubmit(event: any){
  }




}
