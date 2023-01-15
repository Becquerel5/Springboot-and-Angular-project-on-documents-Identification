import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiClientService } from '../services/api-client.service';

@Component({
  selector: 'app-preview-client',
  templateUrl: './preview-client.component.html',
  styleUrls: ['./preview-client.component.scss']
})
export class PreviewClientComponent implements OnInit {

  id!: number;

  constructor(
    private api: ApiClientService
   ) {
   }

  ngOnInit(): void {
    this.id = this.api.idClient;


  }

}
