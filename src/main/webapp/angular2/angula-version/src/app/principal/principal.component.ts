import { Component, OnInit } from '@angular/core';

import { PrincipalService } from './principal.service';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent implements OnInit {

  noticias: string[] = [];

  constructor(private principalService: PrincipalService) {
  }

  ngOnInit() {
    this.noticias = this.principalService.getNoticias();
  }

}
