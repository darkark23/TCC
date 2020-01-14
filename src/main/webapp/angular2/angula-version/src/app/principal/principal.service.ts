import { Injectable } from '@angular/core';

@Injectable()
export class PrincipalService {
  getNoticias() {
    return ['Noticia01', 'Noticia02', 'Noticia03'];
  }
}
