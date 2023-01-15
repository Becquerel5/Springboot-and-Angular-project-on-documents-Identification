import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Route, Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { HomeComponent } from './home/home.component';
import { IdentificationPageComponent } from './identification-page/identification-page.component';
import { PreviewClientComponent } from './preview-client/preview-client.component';
import { ChoiseDocumentComponent } from './choise-document/choise-document.component';
import * as constant from '../../services/constants';


const home: Route={
  path: constant.tokenDefaultValue,
  component: HomeComponent
};
const loginClient: Route={
  path: constant.login,
  component: LoginPageComponent
};
const previewClient: Route={
  path: constant.client_preview,
  component: PreviewClientComponent
};
const formClient: Route={
  path: constant.client_form+constant.slash+constant.two_point+constant.name,
  component: IdentificationPageComponent
};
const cniOrPassPort: Route={
  path: constant.passport_or_cni+constant.slash+constant.two_point+constant.id,
  component: ChoiseDocumentComponent
};

const routes : Routes = [
  home,
  loginClient,
  formClient,
  previewClient,
  cniOrPassPort
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ]
})
export class ClientRoutingModule { }
