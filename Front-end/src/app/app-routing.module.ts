import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import * as constant from './services/constants';

const routes: Routes = [
  {
    path:  constant.app_module_list_client,
    redirectTo:  constant.agent_entries_list_path,
    pathMatch:  constant.full
  },
  {
    path:  constant.app_module_list_agent_emit,
    redirectTo:  constant.agent_entries_list_emitP,
    pathMatch:  constant.full
  },
  {
    path:  constant.app_module_list_agent_build,
    redirectTo:  constant.agent_entries_list_buildP,
    pathMatch:  constant.full
  },
  {
    path:  constant.agent,
    redirectTo: constant.login_agent_path,
    pathMatch:  constant.full
  },
  {
    path:  constant.tokenDefaultValue,
    redirectTo: constant.tokenDefaultValue,
    pathMatch:  constant.full
  },
  {
    path:  constant.login,
    redirectTo: constant.login_client_path,
    pathMatch:  constant.full
  },
  {
    path:  constant.client_form,
    redirectTo:
      constant.client_form_path+
      constant.slash+
      constant.two_point+
      constant.name,/* "/form/:name" */
    pathMatch:  constant.full
  },
  {
    path:  constant.client_preview,
    redirectTo: constant.client_preview_path,
    pathMatch:  constant.full
  },
  {
    path:  constant.passport_or_cni,
    redirectTo:
      constant.passport_or_cni_path+
      constant.slash+
      constant.two_point+
      constant.id,
    pathMatch:  constant.full
  },
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
