import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthentificationService } from './services/authentification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  color: string="aqua";
  name: string="Titeuf";
  sigle: string="";
  isLogged: boolean = false;
  loading: boolean = true;
  private isLoggedSubscription: Subscription | undefined;
  logging: boolean = false;
  message: string | null = null;

  constructor(
    private auth: AuthentificationService,
    private route: Router,
    ) {

     }

     getColor(){
      return "background-color: "+this.color+";"
     }

  ngOnInit(): void {
    this.isLoggedSubscription = this.auth.isUserSubject.subscribe(
      (isLogged: boolean)=>{
        this.isLogged=isLogged;
      }
    );
  }
}
