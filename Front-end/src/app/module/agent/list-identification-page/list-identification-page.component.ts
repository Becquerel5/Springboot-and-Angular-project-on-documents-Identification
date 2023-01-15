import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Referencedocument } from 'src/app/models';
import { referenceDoController } from 'src/app/models/api/referenceDoController';
import * as constant from '../../../services/constants';

@Component({
  selector: 'app-list-identification-page',
  templateUrl: './list-identification-page.component.html',
  styleUrls: ['./list-identification-page.component.scss']
})
export class ListIdentificationPageComponent implements OnInit {

  referencedocs?: Referencedocument[]=[];
  isEmpty: boolean = true;
  message: string = 'Please Wait';

  constructor( private referenceservice: referenceDoController,private router: Router
  ) { }

  ngOnInit(): void {
    this.getAllReferenceDocs();
  }

  private getAllReferenceDocs(){
    this.referenceservice.getAllReferenceDoc().subscribe(
      data=>{
        if(data!.length==0){
          this.isEmpty=true;
          this.message = "Zero entries .....";
        }else{
          this.isEmpty=false;
          this.referencedocs=data;
        }
      },error=>{

      }
    );
  }


  referencedocumentDetails(id: number){
    this.router.navigate([constant.agent_preview_path,id]);

  }

}
