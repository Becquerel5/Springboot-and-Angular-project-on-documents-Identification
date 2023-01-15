import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Route, RouterModule, Routes } from '@angular/router';
import { LoginPageAgentComponent } from './login-page/login-page.component';
import { WorkingComponent } from './working/working.component';
import { ListIdentificationPageComponent } from './list-identification-page/list-identification-page.component';
import { PreviewAgentComponent } from './preview-agent/preview-agent.component';
import * as constant from '../../services/constants';
import { ListEmitDocumentComponent } from './list-emit-document/list-emit-document.component';
import { ListBuildDocumentComponent } from './list-build-document/list-build-document.component';

const loginAgent: Route={
  path: constant.login_agent,
  component: LoginPageAgentComponent
};
//update-referenceDoc
const list: Route={
  path: constant.agent_entries_list,
  component: ListIdentificationPageComponent,
  data: {
    title: "List Of Entries",
    breadcrumb: [
      { routerLink: null, text: "List" }
    ]
  }
};

const listEmit: Route={
  path: constant.agent_entries_list_emit,
  component: ListEmitDocumentComponent,
  data: {
    title: "List Of Entries For Emit",
    breadcrumb: [
      { routerLink: null, text: "List" }
    ]
  }
};

const listBuild: Route={
  path: constant.agent_entries_list_build,
  component: ListBuildDocumentComponent,
  data: {
    title: "List Of Entries To Build",
    breadcrumb: [
      { routerLink: null, text: "List" }
    ]
  }
};

const preview: Route={
  path: constant.agent_preview+constant.slash+constant.two_point+constant.id,
  component: PreviewAgentComponent
};


const routes: Routes = [
  loginAgent,
  preview,
  list,
  listEmit,
  listBuild,
  /* {path:'edit-referenceDoc/:id',component:EditIdentificationPageComponent}, */
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
})
export class AgentRoutingModule { }
