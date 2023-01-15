import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginPageAgentComponent } from './login-page/login-page.component';
import { ListIdentificationPageComponent } from './list-identification-page/list-identification-page.component';
import { EditIdentificationPageComponent } from './edit-identification-page/edit-identification-page.component';
import { AddFilePageComponent } from './add-file-page/add-file-page.component';
import { FactoryPageComponent } from './factory-page/factory-page.component';
import { SearchIdentificationPageComponent } from './search-identification-page/search-identification-page.component';
import { EmitPageComponent } from './emit-page/emit-page.component';
import { ShareModule } from '../share/share.module';
import { ApiAgentService } from './services/api-agent.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorAgentService } from './services/interceptor-agent.service';
import { AgentRoutingModule } from './agent-routing.module';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { WorkingComponent } from './working/working.component';
import { BreadcrumbAgentComponent } from './breadcrumb-agent/breadcrumb-agent.component';
import {MatTableModule} from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
import { PreviewAgentComponent } from './preview-agent/preview-agent.component';
import { ListBuildDocumentComponent } from './list-build-document/list-build-document.component';
import { ListEmitDocumentComponent } from './list-emit-document/list-emit-document.component';


@NgModule({
  declarations: [
    LoginPageAgentComponent,
    ListIdentificationPageComponent,
    EditIdentificationPageComponent,
    AddFilePageComponent,
    FactoryPageComponent,
    SearchIdentificationPageComponent,
    EmitPageComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    WorkingComponent,
    BreadcrumbAgentComponent,
    PreviewAgentComponent,
    ListBuildDocumentComponent,
    ListEmitDocumentComponent,

  ],
  imports: [
    CommonModule,
    AgentRoutingModule,
    ShareModule,
    MatTableModule,
    HttpClientModule
  ],
  providers: [
    ApiAgentService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorAgentService,
      multi:true
    }
  ],
  exports: [
    LoginPageAgentComponent,
    ListIdentificationPageComponent,
    EditIdentificationPageComponent,
    AddFilePageComponent,
    FactoryPageComponent,
    SearchIdentificationPageComponent,
    EmitPageComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    PreviewAgentComponent,
    BreadcrumbAgentComponent,
    ListBuildDocumentComponent,
    ListEmitDocumentComponent
  ]
})
export class AgentModule { }
