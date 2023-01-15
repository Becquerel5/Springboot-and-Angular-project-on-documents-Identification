import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageComponent } from './login-page/login-page.component';
import { IdentificationPageComponent } from './identification-page/identification-page.component';
import { ShareModule } from '../share/share.module';
import { NavigationComponent } from './navigation/navigation.component';
import { ClientRoutingModule } from './client-routing.module';
import { WorkingClientComponent } from './working/working.component';
import { HomeComponent } from './home/home.component';
import { PreviewClientComponent } from './preview-client/preview-client.component';
import { ChoiseDocumentComponent } from './choise-document/choise-document.component';
import { ContainStepperComponent } from './contain-stepper/contain-stepper.component';



@NgModule({
  declarations: [
    LoginPageComponent,
    IdentificationPageComponent,
    NavigationComponent,
    WorkingClientComponent,
    HomeComponent,
    PreviewClientComponent,
    ChoiseDocumentComponent,
    ContainStepperComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    ShareModule
  ],
  exports: [
    LoginPageComponent,
    IdentificationPageComponent,
    PreviewClientComponent,
    NavigationComponent,
    WorkingClientComponent,
    HomeComponent,
    ChoiseDocumentComponent,
    ContainStepperComponent
  ]
})
export class ClientModule { }
