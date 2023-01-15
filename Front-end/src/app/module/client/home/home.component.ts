import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as constant from '../../../services/constants';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private route: Router) { }

  ngOnInit(): void {
  }

  redirectory(){
    this.route.navigate([constant.login_client_path]);
  }

}
