import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Cni, Passport, ReferenceDocumentControllerService } from 'src/app/models';
import * as constant from '../../../services/constants';
import { ApiClientService } from '../services/api-client.service';
import * as clientConstant from '../services/constant-client';

@Component({
  selector: 'app-contain-stepper',
  templateUrl: './contain-stepper.component.html',
  styleUrls: ['./contain-stepper.component.scss']
})
export class ContainStepperComponent implements OnInit {
  @Input()
  name: string =constant.tokenDefaultValue;

  @Output()
  data: EventEmitter<any> = new  EventEmitter<any>(constant.true_value);

  clicked: boolean=constant.true_value;

  currentStep = clientConstant.stepper_init_value;
  numSteps = clientConstant.stepper_max_value;

  constants: any = clientConstant;

  referenceDocumentFormGroup!: FormGroup;

  dataReference: any;

  GENDER: any ={
    male: Cni.GenderEnum.MALE,
    female: Cni.GenderEnum.FEMALE
  };
  gender?: string;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private api: ApiClientService
  ) {
    this.referenceDocumentFormGroup = formBuilder.group(
      {
        firstName: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_name)]),
        lastName: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_name)]),
        address: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_name)]),
        dateOfBirth: new FormControl(null, [Validators.required]),
        profession: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_name)]),
        fatherName: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_name)]),
        motherName: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_name)]),
        identificationPoste: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_name)]),
        country: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_name)]),
        idReference: new FormControl(null, [Validators.required]),
        documentNumber: new FormControl(null, [Validators.required,Validators.pattern(constant.pattern_number_code)]),
        deliveryDate: new FormControl(null, [Validators.required]),
        expireDate: new FormControl(null, [Validators.required]),
      }
    );
   }

   stepOne(){
    if(
      this.f[clientConstant.form_first_name].valid &&
      this.f[clientConstant.form_last_name].valid &&
      this.f[clientConstant.form_profession].valid &&
      this.f[clientConstant.form_date_of_birth].valid &&
      this.f[clientConstant.form_mother_name].valid &&
      this.f[clientConstant.form_father_name].valid &&
      (this.gender == this.GENDER.male || this.gender == this.GENDER.female)){
        let birthDate = new Date(this.f[clientConstant.form_date_of_birth].value);
        let date = new Date();
        if((date.getFullYear()-birthDate.getFullYear())<2){
          return constant.true_value;
        }
        return constant.false_value;
      }
      return constant.true_value;
   }

   stepTwo(){
    if(
      this.f[clientConstant.form_expire_date].valid &&
      this.f[clientConstant.form_delivery_date].valid &&
      this.f[clientConstant.form_id_reference].valid &&
      this.f[clientConstant.form_numero_document].valid){
        let deliveryDate = new Date(this.f[clientConstant.form_delivery_date].value);
        let expireDate = new Date(this.f[clientConstant.form_expire_date].value);
        if((expireDate.getFullYear()-deliveryDate.getFullYear())!=5){
          return constant.true_value;
        }
        if(this.name == constant.cni){
          if(this.f[clientConstant.form_identification_poste].valid)
            return constant.false_value;
          else
            return constant.true_value;
        }else if(this.name == constant.passport){
          if(this.f[clientConstant.form_country].valid)
            return constant.false_value;
          else
            return constant.true_value;
        }
        return constant.true_value;
      }
      return constant.true_value;
   }

   onNext(){
    this.onSubmit();
  }

  onPrevious(){
    this.currentStep--;
    if (this.currentStep < clientConstant.stepper_init_value) {
      this.currentStep = clientConstant.stepper_init_value;
    }

    let stepper = document.getElementById('stepper1');
    let steps = stepper?.getElementsByClassName('step');
    Array.from(steps!).forEach((step) => {
      this.removeClass(step, 'editing');
      this.removeClass(step, 'done');
    });
    this.onSetStepper();
  }

  private onSetStepper(){
    let stepper = document.getElementById('stepper1');
    let steps = stepper?.getElementsByClassName('step');
    Array.from(steps!).forEach((step, index) => {
      let stepNum = index + 1;
      if (stepNum === this.currentStep) {
        this.addClass(step, 'editing');
      } else {
        this.removeClass(step, 'editing');
      }
      if (stepNum < this.currentStep) {
        this.addClass(step, 'done');
      } else {
        this.removeClass(step, 'done');
      }
    });
  }

  ngOnInit(): void {
    if(this.name==constant.cni || this.name==constant.passport){
      this.api.getCurrentIssueClient(this.api.idClient).subscribe(
        response=>{
          if(response.body.statusTreatmentSystemeList == Cni.StatusTreatmentSystemeListEnum.FORM){
            this.f[clientConstant.form_id_reference].setValue(response.body.referenceNumber);
            this.f[clientConstant.form_numero_document].setValue(response.body.documentNumber);
            this.f[clientConstant.form_address].setValue(response.body.address);
            this.f[clientConstant.form_date_of_birth].setValue(response.body.dateOfBirth);
            this.f[clientConstant.form_delivery_date].setValue(response.body.deliveryDate);
            this.f[clientConstant.form_expire_date].setValue(response.body.expirationDate);
            this.f[clientConstant.form_father_name].setValue(response.body.nameOfFather);
            this.f[clientConstant.form_mother_name].setValue(response.body.nameOfMother);
            this.f[clientConstant.form_first_name].setValue(response.body.firstName);
            this.f[clientConstant.form_last_name].setValue(response.body.lastName);
            this.gender = response.body.gender;
            this.f[clientConstant.form_profession].setValue(response.body.profession);
            this.dataReference = response.body;
            if("posteIdentification" in response.body){
              this.f[clientConstant.form_identification_poste].setValue(response.body.posteIdentification);
            }else if("country" in response.body){
              this.f[clientConstant.form_country].setValue(response.body.country);
            }
            if(this.stepOne()){
              this.currentStep=1;
              this.onSetStepper();
            }else{
              this.currentStep=2;
              this.onSetStepper();
            }
          }else if(response.body.statusTreatmentSystemeList == Cni.StatusTreatmentSystemeListEnum.FINISH){
            this.router.navigate([constant.login_client_path]);
          }else{
            this.router.navigate([constant.client_preview_path]);
          }
        },
        error=>{
          this.router.navigate([constant.login_client_path]);
        }
      );
    }else{
      this.router.navigate([constant.passport_or_cni_path]);
    }
  }

  get f(): { [key: string]: AbstractControl } {
    return this.referenceDocumentFormGroup.controls;
  }

  private hasClass(elem: any, className: any) {
    return new RegExp(' ' + className + ' ').test(' ' + elem.className + ' ');
  }

  private addClass(elem: any, className: any) {
    if (!this.hasClass(elem, className)) {
        elem.className += ' ' + className;
    }
  }

  private removeClass(elem: any, className: any) {
    var newClass = ' ' + elem.className.replace( /[\t\r\n]/g, ' ') + ' ';
    if (this.hasClass(elem, className)) {
        while (newClass.indexOf(' ' + className + ' ') >= 0 ) {
            newClass = newClass.replace(' ' + className + ' ', ' ');
        }
        elem.className = newClass.replace(/^\s+|\s+$/g, '');
    }
  }

  private onSubmit(){
    let gender!: Cni.GenderEnum;
    if(this.gender==Cni.GenderEnum.MALE){
      gender = Cni.GenderEnum.MALE;
    }else if(this.gender==Cni.GenderEnum.FEMALE){
      gender = Cni.GenderEnum.FEMALE;
    }
    if(this.currentStep==3){
      this.dataReference.statusTreatmentSystemeList = Cni.StatusTreatmentSystemeListEnum.VALIDATE;
      this.dataReference.statusInTreatment =  Cni.StatusInTreatmentEnum.Ready;
    }else {
      this.dataReference.statusTreatmentSystemeList = Cni.StatusTreatmentSystemeListEnum.FORM;
      this.dataReference.statusInTreatment =  Cni.StatusInTreatmentEnum.Ready;
    }
    if(this.name==constant.cni){
			this.dataReference.documentNumber = this.returnCni(gender).documentNumber;
			this.dataReference.lastName = this.returnCni(gender).lastName;
			this.dataReference.firstName = this.returnCni(gender).firstName;
			this.dataReference.dateOfBirth = this.returnCni(gender).dateOfBirth;
			this.dataReference.gender = this.returnCni(gender).gender;
			this.dataReference.profession = this.returnCni(gender).profession;
			this.dataReference.nameOfFather = this.returnCni(gender).nameOfFather;
			this.dataReference.nameOfMother = this.returnCni(gender).nameOfMother;
			this.dataReference.deliveryDate = this.returnCni(gender).deliveryDate;
			this.dataReference.expirationDate = this.returnCni(gender).expirationDate;
			this.dataReference.address = this.returnCni(gender).address;
			this.dataReference.withdrawalDate = this.returnCni(gender).withdrawalDate;
			this.dataReference.posteIdentification = this.returnCni(gender).posteIdentification;
      this.saveCni(this.dataReference);
    }else if(this.name==constant.passport){
			this.dataReference.documentNumber = this.returnPassPort(gender).documentNumber;
      this.dataReference.lastName = this.returnPassPort(gender).lastName;
      this.dataReference.firstName = this.returnPassPort(gender).firstName;
      this.dataReference.dateOfBirth = this.returnPassPort(gender).dateOfBirth;
      this.dataReference.gender = this.returnPassPort(gender).gender;
      this.dataReference.profession = this.returnPassPort(gender).profession;
      this.dataReference.nameOfFather = this.returnPassPort(gender).nameOfFather;
      this.dataReference.nameOfMother = this.returnPassPort(gender).nameOfMother;
      this.dataReference.deliveryDate = this.returnPassPort(gender).deliveryDate;
      this.dataReference.expirationDate = this.returnPassPort(gender).expirationDate;
      this.dataReference.address = this.returnPassPort(gender).address;
      this.dataReference.withdrawalDate = this.returnPassPort(gender).withdrawalDate;
      this.dataReference.country = this.returnPassPort(gender).country;
      this.savePassPort(this.dataReference);
    }
  }

  private returnCni(gender: Cni.GenderEnum){
    return {
      idDocumentReference: constant.undefine_value,
      referenceNumber: this.f[clientConstant.form_id_reference].value,
      lastName: this.f[clientConstant.form_last_name].value,
      firstName: this.f[clientConstant.form_first_name].value,
      dateOfBirth: this.f[clientConstant.form_date_of_birth].value,
      gender: gender!,
      profession: this.f[clientConstant.form_profession].value,
      nameOfFather: this.f[clientConstant.form_father_name].value,
      nameOfMother: this.f[clientConstant.form_mother_name].value,
      deliveryDate: this.f[clientConstant.form_delivery_date].value,
      expirationDate: this.f[clientConstant.form_expire_date].value,
      address: this.f[clientConstant.form_address].value,
      statusTreatmentSystemeList: Cni.StatusTreatmentSystemeListEnum.FORM,
      statusInTreatment: Cni.StatusInTreatmentEnum.Ready,
      withdrawalDate: constant.undefine_value,
      documentNumber: this.f[clientConstant.form_numero_document].value,
      posteIdentification: this.f[clientConstant.form_identification_poste].value
    };
  }

  private returnPassPort(gender: Cni.GenderEnum){
    return {
      idDocumentReference: constant.undefine_value,
      referenceNumber: this.f[clientConstant.form_id_reference].value,
      lastName: this.f[clientConstant.form_last_name].value,
      firstName: this.f[clientConstant.form_first_name].value,
      dateOfBirth: this.f[clientConstant.form_date_of_birth].value,
      gender: gender!,
      profession: this.f[clientConstant.form_profession].value,
      nameOfFather: this.f[clientConstant.form_father_name].value,
      nameOfMother: this.f[clientConstant.form_mother_name].value,
      deliveryDate: this.f[clientConstant.form_delivery_date].value,
      expirationDate: this.f[clientConstant.form_expire_date].value,
      address: this.f[clientConstant.form_address].value,
      statusTreatmentSystemeList: Cni.StatusTreatmentSystemeListEnum.FORM,
      statusInTreatment: Cni.StatusInTreatmentEnum.Ready,
      withdrawalDate: constant.undefine_value,
      documentNumber: this.f[clientConstant.form_numero_document].value,
      country: this.f[clientConstant.form_country].value
    };
  }

  private saveCni(cni: any){
    this.api.saveCni(cni).subscribe(
      response=>{
        this.currentStep++;
        if (this.currentStep == this.numSteps) {
          setTimeout(
            ()=>{
              this.retirection()
            },
            5000
          );
          this.currentStep = this.numSteps;
        }
        this.onSetStepper();
      },
      error=>{
        console.log(error);
      }
    )
  }

  private savePassPort(passport: any){
    this.api.savePassPort(passport).subscribe(
      response=>{
        this.currentStep++;
        if (this.currentStep == this.numSteps) {
          setTimeout(
            ()=>{
              this.retirection()
            },
            5000
          );
          this.currentStep = this.numSteps;
        }
        this.onSetStepper();
      },
      error=>{
        console.log(error);
      }
    )
  }

  private retirection(){
    this.router.navigate([constant.client_preview_path]);
  }

  getInputClass(event : string){
    if(this.f[event].touched && this.f[event].errors != constant.null_value){
      return 'form-control is-invalid';
    }else if(this.f[event].valid){
      if(event=='dateOfBirth'){
        let birthDate = new Date(this.f[event].value);
          let date = new Date();
          if((date.getFullYear()-birthDate.getFullYear())<2 || (date.getFullYear()-birthDate.getFullYear())>=150){
            return 'form-control is-invalid';
          }
      }
      if(event=='expireDate' || event=='deliveryDate'){
        let deliveryDate = new Date(this.f[clientConstant.form_delivery_date].value);
        let expireDate = new Date(this.f[clientConstant.form_expire_date].value);
        if((expireDate.getFullYear()-deliveryDate.getFullYear())!=5){
          return 'form-control is-invalid';
        }
      }
      return 'form-control is-valid';
    }else if(this.f[event].untouched){
      return 'form-control';
    }else{
      return 'form-control is-invalid';
    }
  }

}
