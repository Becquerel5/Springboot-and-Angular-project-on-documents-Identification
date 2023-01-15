import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiAgentService } from '../services/api-agent.service';
import * as constant from '../../../services/constants';

@Component({
  selector: 'app-preview-agent',
  templateUrl: './preview-agent.component.html',
  styleUrls: ['./preview-agent.component.scss']
})
export class PreviewAgentComponent implements OnInit {

  id!: number;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private api: ApiAgentService
  ) { }

  ngOnInit(): void {
    if(this.api.agent==null)
      this.router.navigate([constant.login_agent_path]);

    this.id = this.route.snapshot.params['id'];
  }

}
