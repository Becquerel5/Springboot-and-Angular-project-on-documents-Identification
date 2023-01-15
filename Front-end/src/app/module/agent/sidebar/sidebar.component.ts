import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as constant from '../../../services/constants';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToValidation(){
    this.router.navigate([constant.agent_entries_list_path]);
  }

  goToBuilding(){
    this.router.navigate([constant.agent_entries_list_buildP]);
  }

  goToEmit(){
    this.router.navigate([constant.agent_entries_list_emitP]);
  }

}
