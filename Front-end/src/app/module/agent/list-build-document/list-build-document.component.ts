import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cni, Referencedocument } from 'src/app/models';
import { ApiAgentService } from '../services/api-agent.service';

@Component({
  selector: 'app-list-build-document',
  templateUrl: './list-build-document.component.html',
  styleUrls: ['./list-build-document.component.scss']
})
export class ListBuildDocumentComponent implements OnInit {

  referencedocs?: Referencedocument[]=[];
  isEmpty: boolean = true;
  message: string = 'Please Wait';

  data: any;

  constructor(
    private referenceservice: ApiAgentService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAllReferenceDocs();
  }

  private getAllReferenceDocs(){
    this.referenceservice.getDocument(Cni.StatusTreatmentSystemeListEnum.FORM,0,5).subscribe(
      data=>{
        this.data = data,
        console.log(data)
        /* if(data!.length==0){
          this.isEmpty=true;
          this.message = "Zero entries .....";
        }else{
          this.isEmpty=false;
        } */
      },error=>{

      }
    );
  }


}
